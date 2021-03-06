package gr.ntua.ece.softeng19b.data.model;

public class DATLFRecordForSpecificMonth extends AbstractEntsoeRecord {

    private int day;
    private double dayAheadTotalLoadForecastByDayValue;

    public DATLFRecordForSpecificMonth() {
        super(DataSet.DayAheadTotalLoadForecast);
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
}
