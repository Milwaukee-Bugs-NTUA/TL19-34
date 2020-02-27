package gr.ntua.ece.softeng19b.data.model;

import java.sql.Timestamp;

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
