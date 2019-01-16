package uk.ac.ebi.ddi.arrayexpress.reader.xml.unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.ddi.arrayexpress.reader.model.experiments.ModelConstants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * yperez
 */
public class ExperimentUnmarshallerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentUnmarshallerFactory.class);

    private static ExperimentUnmarshallerFactory instance = new ExperimentUnmarshallerFactory();

    private static JAXBContext jc = null;

    private ExperimentUnmarshallerFactory() {
    }

    public static ExperimentUnmarshallerFactory getInstance() {
        return instance;
    }

    public Unmarshaller initializeUnmarshaller() {

        try {
            // Lazy caching of the JAXB Context.
            if (jc == null) {
                jc = JAXBContext.newInstance(ModelConstants.MODEL_PKG);
            }

            //create unmarshaller
            Unmarshaller pum = jc.createUnmarshaller();
            LOGGER.info("Unmarshaller Initialized");

            return pum;

        } catch (JAXBException e) {
            LOGGER.error("UnmarshallerFactory.initializeUnmarshaller", e);
            throw new IllegalStateException("Could not initialize unmarshaller", e);
        }
    }
}
