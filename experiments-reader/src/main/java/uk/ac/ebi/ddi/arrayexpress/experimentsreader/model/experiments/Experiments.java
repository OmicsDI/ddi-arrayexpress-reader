
package uk.ac.ebi.ddi.arrayexpress.experimentsreader.model.experiments;

import uk.ac.ebi.ddi.arrayexpress.experimentsreader.model.IArrayExpress;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "experiment"
})
@XmlRootElement(name = "experiments")
public class Experiments implements Serializable, IArrayExpress {

    private static final long serialVersionUID = 100L;
    @XmlElement(required = true)
    protected List<Experiment> experiment;
    @XmlAttribute(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar retrieved;

    /**
     * Gets the value of the experiment property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the experiment property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExperiment().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Experiment }
     */
    public List<Experiment> getExperiment() {
        if (experiment == null) {
            experiment = new ArrayList<Experiment>();
        }
        return this.experiment;
    }

    /**
     * Gets the value of the retrieved property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getRetrieved() {
        return retrieved;
    }

    /**
     * Sets the value of the retrieved property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setRetrieved(XMLGregorianCalendar value) {
        this.retrieved = value;
    }

}
