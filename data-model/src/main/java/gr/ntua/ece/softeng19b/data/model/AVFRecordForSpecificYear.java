package gr.ntua.ece.softeng19b.data.model;

public class AVFRecordForSpecificYear extends AbstractEntsoeRecord {

    private double dayAheadTotalLoadForecastByMonthValue;
    private double actualTotalLoadByMonthValue;

    public AVFRecordForSpecificYear() {
        super(DataSet.ActualVSForecastedTotalLoad);
    }

    public double getDayAheadTotalLoadForecastByMonthValue() {
        return dayAheadTotalLoadForecastByMonthValue;
    }

    public void setDayAheadTotalLoadForecastByMonthValue(double dayAheadTotalLoadForecastByMonthValue) {
        this.dayAheadTotalLoadForecastByMonthValue = dayAheadTotalLoadForecastByMonthValue;
    }
    
    public double getActualTotalLoadByMonthValue() {
        return actualTotalLoadByMonthValue;
    }

    public void setActualTotalLoadByMonthValue(double actualTotalLoadByMonthValue) {
        this.actualTotalLoadByMonthValue = actualTotalLoadByMonthValue;
    }
}
