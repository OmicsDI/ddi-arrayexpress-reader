package uk.ac.ebi.ddi.arrayexpress.reader.utils;

import java.util.List;

/**
 * @author ypriverol
 */
public class Constants {

    public static String ARRAYEXPRESS_URL = "https://www.ebi.ac.uk/arrayexpress/experiments/";

    public static String ORGANISM_TAG     = "Organism";

    public enum ArrayExpressType{
        DNA_ASSAY(new String[]{"DNA assay", "DNA-seq", "Amplicon sequencing", "ChIP-seq", "methylation profiling by high throughput sequencing"}, "Genomics"),
        METABOLOMIC_PROFILING(new String[]{"Metabolomic profiling"}, "Metabolomics"),
        PROTEIN_ASSAY(new String[]{"Protein assay", "proteomic profiling by array", "antigen profiling"}, "Proteomics"),
        RNA_ASSAY(new String[]{"RNA assay", "RNA-seq of coding RNA",
                "RNA-seq of non coding RNA",
                "microRNA profiling by array",
                "RNAi profiling by array", "translation profiling",
                "ChIP-chip by tiling array"}, "Transcriptomics");

        final String[] type;
        final String omicsType;

        ArrayExpressType(String[] type, String omicsType){
            this.type = type;
            this.omicsType = omicsType;
        }

        public String[] getType() {
            return type;
        }

        public String getOmicsType() {
            return omicsType;
        }

        public static ArrayExpressType getByType(String type){
            for(ArrayExpressType valueArr: values())
               for(String value: valueArr.getType())
                   if(value.equalsIgnoreCase(type))
                       return valueArr;
            return RNA_ASSAY;
        }
    }
}
