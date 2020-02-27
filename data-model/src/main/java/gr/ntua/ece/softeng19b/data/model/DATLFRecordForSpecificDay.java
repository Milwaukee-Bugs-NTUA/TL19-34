package gr.ntua.ece.softeng19b.data.model;

public class DATLFRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private double dayAheadTotalLoadForecastValue;
    private Timestamp dateTimeUTC, updateTimeUTC;

    public DATLFRecordForSpecificDay() {
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
    public Timestamp getDateTimeUTC() {
        return dateTimeUTC;
    }

    public void setDateTimeUTC(Timestamp dateTimeUTC) {
        this.dateTimeUTC = dateTimeUTC;
    }    


    public Timestamp getUpdateTimeUTC() {
        return updateTimeUTC;
    }

    public void setUpdateTimeUTC(Timestamp updateTimeUTC) {
        this.dateTimeUTC = updateTimeUTC;
    }    

}
