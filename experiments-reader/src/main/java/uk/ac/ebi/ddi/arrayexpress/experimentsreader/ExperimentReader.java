package uk.ac.ebi.ddi.arrayexpress.experimentsreader;

import uk.ac.ebi.ddi.arrayexpress.experimentsreader.model.experiments.Experiments;
import uk.ac.ebi.ddi.arrayexpress.experimentsreader.xml.unmarshaller.ExperimentUnmarshallerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;


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

    public ExperimentReader(InputStream inputStream) {
        try {
            // create unmarshaller
            this.unmarshaller = ExperimentUnmarshallerFactory.getInstance().initializeUnmarshaller();
            experiments = (Experiments) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new IllegalArgumentException("Error unmarshalling InputStream: " + e.getMessage(), e);
        }
    }

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
