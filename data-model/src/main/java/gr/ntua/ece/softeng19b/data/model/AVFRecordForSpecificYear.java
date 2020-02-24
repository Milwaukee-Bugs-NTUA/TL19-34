package gr.ntua.ece.softeng19b.data.model;

public class AVFRecordForSpecificYear extends AbstractEntsoeRecord {

    private double dayAheadTotalLoadForecastValue;
    private double actualTotalLoadValue;

    public AVFRecordForSpecificYear() {
        super(DataSet.ActualVSForecastedTotalLoad);
    }

    public double getDayAheadTotalLoadForecastValue() {
        return dayAheadTotalLoadForecastValue;
    }

    public void setDayAheadTotalLoadForecastValue(double dayAheadTotalLoadForecastValue) {
        this.dayAheadTotalLoadForecastValue = dayAheadTotalLoadForecastValue;
    }
    
    public double getActualTotalLoadValue() {
        return actualTotalLoadValue;
    }

    public void setActualTotalLoadValue(double actualTotalLoadValue) {
        this.actualTotalLoadValue = actualTotalLoadValue;
    }
}
