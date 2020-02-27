package gr.ntua.ece.softeng19b.data.model;

import java.sql.Time;
import java.sql.Timestamp;

public class AGPTRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private double actualGenerationOutputValue;
    private String productionType;
    private Timestamp dateTimeUTC, updateTimeUTC;

    public AGPTRecordForSpecificDay() {
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

    public Timestamp getDateTimeUTC() {
        return dateTimeUTC;
    }

    public void setDateTimeUTC(Timestamp dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }

    public void setDateTimeUTC(String string) {
        this.dateTimeUTC = Timestamp.valueOf(string);
    }

    public Timestamp getUpdateTimeUTC() {
        return updateTimeUTC;
    }

    public void setUpdateTimeUTC(Timestamp updateTimeUTC) {
        this.updateTimeUTC = updateTimeUTC;
    }
    
    public void setUpdateTimeUTC(String string) {
        this.updateTimeUTC = Timestamp.valueOf(string);
    }
}