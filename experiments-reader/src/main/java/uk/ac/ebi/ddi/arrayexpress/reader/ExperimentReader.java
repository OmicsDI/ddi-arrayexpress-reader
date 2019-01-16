package uk.ac.ebi.ddi.arrayexpress.reader;

import uk.ac.ebi.ddi.arrayexpress.reader.model.experiments.Experiments;
import uk.ac.ebi.ddi.arrayexpress.reader.xml.unmarshaller.ExperimentUnmarshallerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


/**
 * Created by IntelliJ IDEA.
 * User: yperez
 * Date: 18-Jul-2011
 * Time: 12:13:31
 */
public class ExperimentReader {

    /**
     * internal unmashaller
     */
    private Unmarshaller unmarshaller = null;
    /**
     * internal xml extractor
     */

    private Experiments experiments = null;


    /*public PrideModReader(URL url) {
        this(FileUtils.getFileFromURL(url));
    } */
    public ExperimentReader(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Xml file to be indexed must not be null");
        }
        // create extractor
        // this.extractor = new UnimodExtractor(xml);

        try {
            // create unmarshaller
            this.unmarshaller = ExperimentUnmarshallerFactory.getInstance().initializeUnmarshaller();
            experiments = (Experiments) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new IllegalArgumentException("Error unmarshalling XML file: " + e.getMessage(), e);
        }
    }

    public Experiments getExperiments() {
        return experiments;
    }
}
