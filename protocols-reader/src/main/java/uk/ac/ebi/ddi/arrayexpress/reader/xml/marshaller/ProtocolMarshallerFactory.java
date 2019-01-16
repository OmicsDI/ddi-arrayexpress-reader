package uk.ac.ebi.ddi.arrayexpress.reader.xml.marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.ddi.arrayexpress.reader.model.protocols.ModelConstants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Created by IntelliJ IDEA.
 * User: yperez
 * Date: 13-Aug-2010
 * Time: 14:15:35
 * To change this template use File | Settings | File Templates.
 */
public class ProtocolMarshallerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolMarshallerFactory.class);

    private static ProtocolMarshallerFactory instance = new ProtocolMarshallerFactory();

    private static JAXBContext jc = null;

    private ProtocolMarshallerFactory() {
    }

    public static ProtocolMarshallerFactory getInstance() {
        return instance;
    }

    public Marshaller initializeMarshaller() {

        try {
            // Lazy caching of the JAXB Context.
            if (jc == null) {
                jc = JAXBContext.newInstance(ModelConstants.MODEL_PKG);
            }

            //create unmarshaller
            Marshaller pm = jc.createMarshaller();
            LOGGER.info("Marshaller Initialized");

            return pm;

        } catch (JAXBException e) {
            LOGGER.error("UnimodMarshaller.initializeMarshaller", e);
            throw new IllegalStateException("Could not initialize marshaller", e);
        }
    }
}
