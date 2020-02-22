package gr.ntua.ece.softeng19b.data.model;

public class DATLFRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int day;
    private double dayAheadTotalLoadForecastValue;

    public DATLFRecordForSpecificMonth() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public double getDayAheadTotalLoadForecastValue() {
        return dayAheadTotalLoadForecastValue;
    }

    public void setDayAheadTotalLoadForecastValue(double dayAheadTotalLoadForecastValue) {
        this.dayAheadTotalLoadForecastValue = dayAheadTotalLoadForecastValue;
    }
}
