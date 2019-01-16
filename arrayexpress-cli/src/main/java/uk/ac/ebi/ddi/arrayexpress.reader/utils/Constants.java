package uk.ac.ebi.ddi.arrayexpress.reader.utils;

import uk.ac.ebi.ddi.xml.validator.utils.Field;

/**
 * @author ypriverol
 */
public class Constants {

    public static final String ARRAYEXPRESS_URL = "https://www.ebi.ac.uk/arrayexpress/experiments/";

    public static final String ORGANISM_TAG     = "Organism";

    public static final String[] CELL_TYPE      = new String[]{"cell type", "organ/cell type"};

    public static final String[] DISEASE        = new String[]{"DiseaseState"};

    public static final String[] TISSUE         = new String[]{"OrganismPart"};

    public enum ArrayExpressType {
        DNA_ASSAY(new String[]{"DNA assay", "DNA-seq", "Amplicon sequencing", "ChIP-seq",
                "methylation profiling by high throughput sequencing"}, "Genomics"),
        METABOLOMIC_PROFILING(new String[]{"Metabolomic profiling"}, "Metabolomics"),
        PROTEIN_ASSAY(new String[]{"Protein assay", "proteomic profiling by array", "antigen profiling",
                "proteomic profiling by mass spectrometer"}, "Proteomics"),
        RNA_ASSAY(new String[]{"RNA assay", "RNA-seq of coding RNA",
                "RNA-seq of non coding RNA",
                "microRNA profiling by array",
                "RNAi profiling by array", "translation profiling",
                "ChIP-chip by tiling array"}, "Transcriptomics");

        final String[] type;
        final String omicsType;

        ArrayExpressType(String[] type, String omicsType) {
            this.type = type;
            this.omicsType = omicsType;
        }

        public String[] getType() {
            return type;
        }

        public String getOmicsType() {
            return omicsType;
        }

        public static ArrayExpressType getByType(String type) {
            for (ArrayExpressType valueArr: values()) {
                for (String value : valueArr.getType()) {
                    if (value.equalsIgnoreCase(type)) {
                        return valueArr;
                    }
                }
            }
            return RNA_ASSAY;
        }
    }

    public enum Protocols {
        NORMALIZATION(new String[] {"normalization data transformation protocol"}, Field.DATA,
                "Data Transformation", 2),
        BIOASSAY_DATA(new String[] {"bioassay_data_transformation"}, Field.DATA,
                "Assay Data Transformation", 0),
        NUCLEIC_ACID_EXTRACTION(new String[] {"nucleic_acid_extraction", "nucleic acid extraction protocol"},
                Field.SAMPLE, "Nucleic Acid Extraction", 4),
        NUCLEIC_ACID_SEQUENCING(new String[] {"nucleic acid sequencing protocol"}, Field.SAMPLE,
                "Sequencing", 5),
        LABELLING(new String[]{"labeling", "labelling protocol", "nucleic acid labeling protocol"},
                Field.SAMPLE, "Labeling", 3),
        FEATURE_EXTRACTION(new String[]{"feature_extraction"}, Field.DATA, "Feature Extraction", 1),
        HYBRIDIZATION(new String[]{"nucleic acid hybridization to array protocol", "hybridization",
                "hybridization protocol"}, Field.SAMPLE, "Hybridization", 2),
        TREATMENT(new String[]{"sample treatment protocol", "treatment protocol"}, Field.SAMPLE,
                "Sample Treatment", 1),
        GROWTH(new String[]{"grow", "growth protocol", "growth", "growth condition"}, Field.SAMPLE,
                "Growth Protocol", 0),
        ARRAY_SACANING(new String[]{"array scanning protocol", "array scanning and feature extraction protocol"},
                Field.SAMPLE, "Scaning", 6),
        IMAGE_ADQUISITION(new String[]{"image_aquisition", "image_acquisition"}, Field.DATA,
                "Image Adquisition", 5),
        SAMPLE_PROCESSING(new String[]{"specified_biomaterial_action"}, Field.SAMPLE,
                "Sample Processing", 1),
        LIBRARY_CONSTRUCTION(new String[]{"nucleic acid library construction protocol"}, Field.SAMPLE,
                "Library Construction", 3);

        final String[] types;
        final Field field;
        final String name;
        final int level;

        Protocols(String[] types, Field field, String name, int level) {
            this.types = types;
            this.field = field;
            this.name = name;
            this.level = level;
        }

        public String[] getTypes() {
            return types;
        }

        public Field getField() {
            return field;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public static Protocols getByType(String type) {
            for (Protocols protocolArr: values()) {
                for (String value : protocolArr.getTypes()) {
                    if (value.equalsIgnoreCase(type)) {
                        return protocolArr;
                    }
                }
            }
            return null;

        }
    }
}
