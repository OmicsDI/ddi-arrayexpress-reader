
package uk.ac.ebi.ddi.arrayexpress.reader.model.experiments;

import uk.ac.ebi.ddi.arrayexpress.reader.model.IArrayExpress;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigInteger;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * <complexType>
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attribute name="arraydesignprovider" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       <attribute name="bioassaydatacubes" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       <attribute name="bioassays" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       <attribute name="dataformat" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       <attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       <attribute name="isderived" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       <attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "bioassaydatagroup")
public class Bioassaydatagroup implements Serializable, IArrayExpress {

    private static final long serialVersionUID = 100L;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String arraydesignprovider;
    @XmlAttribute(required = true)
    protected BigInteger bioassaydatacubes;
    @XmlAttribute(required = true)
    protected BigInteger bioassays;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String dataformat;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String id;
    @XmlAttribute(required = true)
    protected BigInteger isderived;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;

    /**
     * Gets the value of the arraydesignprovider property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getArraydesignprovider() {
        return arraydesignprovider;
    }

    /**
     * Sets the value of the arraydesignprovider property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setArraydesignprovider(String value) {
        this.arraydesignprovider = value;
    }

    /**
     * Gets the value of the bioassaydatacubes property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getBioassaydatacubes() {
        return bioassaydatacubes;
    }

    /**
     * Sets the value of the bioassaydatacubes property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setBioassaydatacubes(BigInteger value) {
        this.bioassaydatacubes = value;
    }

    /**
     * Gets the value of the bioassays property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getBioassays() {
        return bioassays;
    }

    /**
     * Sets the value of the bioassays property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setBioassays(BigInteger value) {
        this.bioassays = value;
    }

    /**
     * Gets the value of the dataformat property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDataformat() {
        return dataformat;
    }

    /**
     * Sets the value of the dataformat property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDataformat(String value) {
        this.dataformat = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the isderived property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getIsderived() {
        return isderived;
    }

    /**
     * Sets the value of the isderived property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setIsderived(BigInteger value) {
        this.isderived = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

}
