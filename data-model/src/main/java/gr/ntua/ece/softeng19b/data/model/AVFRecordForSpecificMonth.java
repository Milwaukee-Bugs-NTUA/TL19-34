package gr.ntua.ece.softeng19b.data.model;

public class AVFRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int day;
    private double dayAheadTotalLoadForecastByDayValue;
    private double actualTotalLoadByDayValue;    

    public AVFRecordForSpecificMonth() {
        super(DataSet.ActualVSForecastedTotalLoad);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getDayAheadTotalLoadForecastByDayValue() {
        return dayAheadTotalLoadForecastByDayValue;
    }

    public void setDayAheadTotalLoadForecastByDayValue(double dayAheadTotalLoadForecastByDayValue) {
        this.dayAheadTotalLoadForecastByDayValue = dayAheadTotalLoadForecastByDayValue;
    }

    public double getActualTotalLoadByDayValue() {
        return actualTotalLoadByDayValue;
    }

    public void setActualTotalLoadByDayValue(double actualTotalLoadByDayValue) {
        this.actualTotalLoadByDayValue = actualTotalLoadByDayValue;
    }
}
