package gr.ntua.ece.softeng19b.data.model;

public class DATLFRecordForSpecificYear extends AbstractEntsoeRecord {

    private double dayAheadTotalLoadForecastValue;

    public DATLFRecordForSpecificYear() {
        super(DataSet.DayAheadTotalLoadForecast);
    }

    public double getDayAheadTotalLoadForecastValue() {
        return dayAheadTotalLoadForecastValue;
    }

    public void setDayAheadTotalLoadForecastValue(double dayAheadTotalLoadForecastValue) {
        this.dayAheadTotalLoadForecastValue = dayAheadTotalLoadForecastValue;
    }
}
