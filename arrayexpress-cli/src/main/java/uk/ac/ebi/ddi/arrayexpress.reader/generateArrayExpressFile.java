package uk.ac.ebi.ddi.arrayexpress.reader;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.ac.ebi.ddi.arrayexpress.reader.model.experiments.*;
import uk.ac.ebi.ddi.arrayexpress.reader.model.protocols.Protocol;
import uk.ac.ebi.ddi.arrayexpress.reader.model.protocols.Protocols;
import uk.ac.ebi.ddi.arrayexpress.reader.utils.ArrayExpressUtils;
import uk.ac.ebi.ddi.arrayexpress.reader.utils.Constants;
import uk.ac.ebi.ddi.xml.validator.parser.marshaller.OmicsDataMarshaller;
import uk.ac.ebi.ddi.xml.validator.parser.model.*;
import uk.ac.ebi.ddi.xml.validator.parser.model.Date;
import uk.ac.ebi.ddi.xml.validator.utils.*;
import uk.ac.ebi.ddi.xml.validator.utils.Field;

import java.io.File;

import java.io.FileWriter;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * This program takes a ArrayExpress Experiment File and Protocols and generate for all the
 * OmicsDI file.
 *
 * @author Yasset Perez-Riverol
 */

public class generateArrayExpressFile {

    private static final Logger logger = LoggerFactory.getLogger(generateArrayExpressFile.class);

    private static final String NOT_AVAILABLE = "Not available";

    private static final String DATABASE_NAME = "ArrayExpress";

    private static final String ARRAYEXPRESS_DESCRIPTION = "ArrayExpress Archive of Functional Genomics Data stores data from high-throughput functional genomics experiments, and provides these data for reuse to the research community.";

    /**
     * This program take an output folder as a parameter an create different EBE eyes files for
     * all the project in MetabolomicsWorkbench. It loop all the project in MetabolomeWorkbench and
     * print them to the give output
     *
     * @param args
     */
    public static void main(String[] args){

        // Definite command line
        CommandLineParser parser = new PosixParser();
        Options options = new Options();

        //Help page
        String helpOpt = "help";
        options.addOption("h", helpOpt, false, "print help message");

        String experimentFileStr = "experimentFile";
        options.addOption(experimentFileStr, true, "Input File with all the ArrayExpress experiments.");

        String protocolFileStr  = "protocolFile";
        options.addOption(protocolFileStr, true, "Input File with all the ArrayExpress protocols.");

        String omicsDIFileStr = "omicsDIFile";
        options.addOption(omicsDIFileStr, true, "Output File for omicsDI");

        // Parse command line
        CommandLine line = null;
        try {
            line = parser.parse(options, args);
            if (line.hasOption(helpOpt) || !line.hasOption(protocolFileStr) ||
                    !line.hasOption(experimentFileStr)
                    || !line.hasOption(omicsDIFileStr)) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("validatorCLI", options);
            }else{
                File omicsDIFile = new File (line.getOptionValue(omicsDIFileStr));
                Experiments experiments = new ExperimentReader(new File (line.getOptionValue(experimentFileStr))).getExperiments();
                Protocols protocols = new ProtocolReader(new File (line.getOptionValue(protocolFileStr))).getProtocols();
                generate(experiments, protocols, omicsDIFile);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception ex){
            ex.getStackTrace();
        }


    }

    /**
     * Performs the EB-eye generation of a defined public project, submission summary, and output directory.
     * @throws Exception
     */
    public static void generate(Experiments experiments, Protocols protocols, File outputFile) throws Exception {

        OmicsDataMarshaller mm = new OmicsDataMarshaller();

        Database database = new Database();
        database.setDescription(ARRAYEXPRESS_DESCRIPTION);
        database.setName(DATABASE_NAME);
        database.setReleaseDate(experiments.getRetrieved().toString());
        Entries entries = new Entries();

        try{
            Map<String, uk.ac.ebi.ddi.arrayexpress.reader.model.protocols.Protocol> protocolMap = createProtocolMap(protocols);
            if (experiments != null && experiments.getExperiment() != null && outputFile != null) {

            experiments.getExperiment().stream().forEach( ex -> {

                if(ex.getUser() != null && ex.getUser().contains(BigInteger.valueOf(1))){
                    Entry entry = new Entry();

                    entry.setId(ex.getAccession());
                    entry.setName(ex.getName());
                    entry.setDescription(ex.getDescription());

                    //Add Dates
                    if(ex.getReleasedate() != null && !ex.getReleasedate().toString().isEmpty())
                        entry.addDate(Field.PUBLICATION.getName(),ex.getReleasedate().toString());
                    if(ex.getLastupdatedate() != null && !ex.getLastupdatedate().isEmpty())
                        entry.addDate(Field.PUBLICATION_UPDATED.getName(),ex.getLastupdatedate());
                    if(ex.getSubmissiondate() != null && !ex.getSubmissiondate().isEmpty())
                        entry.addDate(Field.SUBMISSION_DATE.getName(),ex.getSubmissiondate());

                    //Add Protocol information as additional fields

                    Map<Integer, Protocol> sampleProtocol = new TreeMap<Integer, Protocol>();
                    Map<Integer, Protocol> dataProtocol   = new TreeMap<Integer, Protocol>();

                    for(uk.ac.ebi.ddi.arrayexpress.reader.model.experiments.Protocol protocolId: ex.getProtocol()){
                        Protocol protocol = protocolMap.get(protocolId.getAccession());
                        if(protocol != null){
                            System.out.println(protocol.getName());
                            entry.addAdditionalField(protocol.getType(), protocol.getText());
                            if(protocol.getSoftware() != null && !protocol.getSoftware().isEmpty()){
                                entry.addAdditionalField(Field.SOFTWARE_INFO.getName(), protocol.getSoftware());
                            }
                            if(protocol.getHardware() != null && !protocol.getHardware().isEmpty()){
                                entry.addAdditionalField(Field.INSTRUMENT.getName(), protocol.getHardware());
                            }

                            Constants.Protocols protocolConstant = Constants.Protocols.getByType(protocol.getType());

                            if(protocolConstant != null)
                                if(protocolConstant.getField() == Field.SAMPLE)
                                    sampleProtocol.put(protocolConstant.getLevel(), protocol);
                                else if(protocolConstant.getField() == Field.DATA)
                                    dataProtocol.put(protocolConstant.getLevel(), protocol);

                            }
                    }

                    if(!sampleProtocol.isEmpty()){
                        String sampleProtocolStr = "";
                        for(Protocol sampleProtcolValue: sampleProtocol.values())
                            sampleProtocolStr = sampleProtocolStr + " " + Constants.Protocols.getByType(sampleProtcolValue.getType()).getName() + " - " + sampleProtcolValue.getText() + "\n";
                        sampleProtocolStr = sampleProtocolStr.trim();
                        entry.addAdditionalField(Field.SAMPLE.getName(), sampleProtocolStr);
                    }

                    if(!dataProtocol.isEmpty()){
                        String dataProtocolStr = "";
                        for(Protocol dataProtcolValue: dataProtocol.values())
                            dataProtocolStr = dataProtocolStr + " " + Constants.Protocols.getByType(dataProtcolValue.getType()).getName() + " - " + dataProtcolValue.getText() + "\n";
                        dataProtocolStr = dataProtocolStr.trim();
                        entry.addAdditionalField(Field.DATA.getName(), dataProtocolStr);
                    }

                    if(ex.getExperimentalfactor() != null){
                        for(Experimentalfactor factor: ex.getExperimentalfactor()){
                            if(factor != null && factor.getName() != null && ArrayExpressUtils.cotainsValue(Constants.CELL_TYPE, factor.getName())){
                                String[] values = ArrayExpressUtils.refineValues(factor.getValue());
                                for(String value: values)
                                         entry.addAdditionalField(Field.CELL_TYPE_FIELD.getName(), value);
                            }
                        }
                    }

                    entry.addAdditionalField(Field.LINK.getName(), Constants.ARRAYEXPRESS_URL + entry.getId());

                    for(String type: ex.getExperimenttype()){
                        if(type != null && !type.isEmpty())
                            entry.addAdditionalField(Field.OMICS.getName(), Constants.ArrayExpressType.getByType(type).getOmicsType());
                        entry.addAdditionalField(Field.SUBMITTER_KEYWORDS.getName(), type);
                    }

                    if(ex.getProvider() != null && !ex.getProvider().isEmpty()){
                        for(Provider provider: ex.getProvider()){
                            if(provider != null && provider.getContact() != null && !provider.getContact().isEmpty()){
                                entry.addAdditionalField(Field.SUBMITTER.getName(), provider.getContact());
                                if(provider.getEmail() != null && !provider.getEmail().isEmpty()){
                                    entry.addAdditionalField(Field.SUBMITTER_EMAIL.getName(), provider.getEmail());
                                }
                            }
                        }
                    }
                    if(ex.getBibliography() != null && !ex.getBibliography().isEmpty()){
                        for(Bibliography biblio: ex.getBibliography()){
                            if(biblio != null && biblio.getAccession() != null){
                                entry.addCrossReferenceValue(Field.PUBMED.getName(), biblio.getAccession());
                            }
                        }
                    }
                    for(Sampleattribute sampleattribute: ex.getSampleattribute()){
                        if(sampleattribute != null && sampleattribute.getCategory() != null){
                            if(sampleattribute.getCategory().equalsIgnoreCase(Constants.ORGANISM_TAG))
                                entry.addAdditionalField(Field.SPECIE_FIELD.getName(), sampleattribute.getValue());
                        }
                    }

                    entries.addEntry(entry);

                }else{
                    logger.error("Project " + ex.getAccession() + " is still private, not generating EB-eye XML.");
                }

            });
            database.setEntryCount(entries.getEntry().size());

            database.setEntries(entries);
            mm.marshall(database, new FileWriter(outputFile));
            }
        }catch(Exception e){
            logger.debug(e.getMessage());
        }
    }

    static Map<String, uk.ac.ebi.ddi.arrayexpress.reader.model.protocols.Protocol> createProtocolMap(Protocols protocols){
        if(protocols != null){
            return protocols.getProtocol().parallelStream().collect(Collectors.toMap(Protocol::getAccession, Function.identity()));
        }
        return null;
    }

}
