package gr.ntua.ece.softeng19b.data.model;

public class AGPTRecordForSpecificYear extends AbstractEntsoeRecord {

    private double actualGenerationOutputByMonthValue;
    private String productionType;

    public AGPTRecordForSpecificYear() {
        super(DataSet.AggregatedGenerationPerType);
    }

    public double getActualGenerationOutputByMonthValue() {
        return actualGenerationOutputByMonthValue;
    }

    public void setActualGenerationOutputByMonthValue(double actualGenerationOutputByMonthValue) {
        this.actualGenerationOutputByMonthValue = actualGenerationOutputByMonthValue;
    }

    public String getProductionType(){
        return productionType;
    }

    public void setProductionType(String productionType){
        this.productionType = productionType;
    }
}
