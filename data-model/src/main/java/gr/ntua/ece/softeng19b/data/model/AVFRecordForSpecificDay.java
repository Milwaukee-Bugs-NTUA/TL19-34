package gr.ntua.ece.softeng19b.data.model;

import java.sql.Timestamp;

public class AVFRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private double dayAheadTotalLoadForecastValue;
    private double actualTotalLoadValue;
    private Timestamp dateTimeUTC, updateTimeUTC;

    public AVFRecordForSpecificDay() {
        super(DataSet.ActualVSForecastedTotalLoad);
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
    
    public double getActualTotalLoadValue() {
        return actualTotalLoadValue;
    }

    public void setActualTotalLoadValue(double actualTotalLoadValue) {
        this.actualTotalLoadValue = actualTotalLoadValue;
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
