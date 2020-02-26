package gr.ntua.ece.softeng19b.data.model;

public class AGPTRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int day;
    private double actualGenerationOutputValue;
    private String productionType;

    public AGPTRecordForSpecificMonth() {
        super(DataSet.AggregatedGenerationPerType);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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
