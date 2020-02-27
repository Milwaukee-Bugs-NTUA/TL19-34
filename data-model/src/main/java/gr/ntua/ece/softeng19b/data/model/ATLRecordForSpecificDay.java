package gr.ntua.ece.softeng19b.data.model;

public class ATLRecordForSpecificDay extends AbstractEntsoeRecord {

    private int day;
    private double actualTotalLoadValue;
    private Timestamp dateTimeUTC, updateTimeUTC;

    public ATLRecordForSpecificDay() {
        super(DataSet.ActualTotalLoad);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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


    public Timestamp getUpdateTimeUTC() {
        return updateTimeUTC;
    }

    public void setUpdateTimeUTC(Timestamp updateTimeUTC) {
        this.dateTimeUTC = updateTimeUTC;
    }    

}
