package gr.ntua.ece.softeng19b.data.model;

public class AGPTRecordForSpecificYear extends AbstractEntsoeRecord {

    private double actualGenerationOutputValue;
    private String productionType;

    public AGPTRecordForSpecificYear() {
        super(DataSet.AggregatedGenerationPerType);
    }

    public double getActualGenerationOutputValue() {
        return actualGenerationOutputValue;
    }

    public void setActualGenerationOutputValue(double actualGenerationOutputValue) {
        this.actualGenerationOutputValue = actualGenerationOutputValue;
    }

    public String getProductionType(){
        return productionType;
    }

    public void setProductionType(String productionType){
        this.productionType = productionType;
    }
}
