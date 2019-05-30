
package uk.ac.ebi.ddi.arrayexpress.reader.model.experiments;

import uk.ac.ebi.ddi.arrayexpress.reader.model.IArrayExpress;

import javax.xml.bind.annotation.*;
import java.io.Serializable;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attribute name="contact" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       <attribute name="email" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       <attribute name="role" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "provider")
public class Provider implements Serializable, IArrayExpress {

    private static final long serialVersionUID = 100L;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String contact;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String email;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String role;

    /**
     * Gets the value of the contact property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setContact(String value) {
        this.contact = value;
    }

    /**
     * Gets the value of the email property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the role property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRole(String value) {
        this.role = value;
    }

}
