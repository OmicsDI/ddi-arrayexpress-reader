package uk.ac.ebi.ddi.arrayexpress.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.ddi.arrayexpress.reader.model.protocols.Protocols;
import uk.ac.ebi.ddi.arrayexpress.reader.xml.unmarshaller.ProtocolUnmarshallerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


/**
 * Created by IntelliJ IDEA.
 * User: yperez
 * Date: 18-Jul-2011
 * Time: 12:13:31
 */
public class ProtocolReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolReader.class);

    /**
     * internal unmashaller
     */
    private Unmarshaller unmarshaller = null;
    /**
     * internal xml extractor
     */

    private Protocols protocols = null;


    /*public PrideModReader(URL url) {
        this(FileUtils.getFileFromURL(url));
    } */
    public ProtocolReader(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Xml file to be indexed must not be null");
        }
        // create extractor
        // this.extractor = new UnimodExtractor(xml);

        try {
            // create unmarshaller
            this.unmarshaller = ProtocolUnmarshallerFactory.getInstance().initializeUnmarshaller();
            protocols = (Protocols) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new IllegalArgumentException("Error unmarshalling XML file: " + e.getMessage(), e);
        }
    }

    public Protocols getProtocols() {
        return protocols;
    }
}
