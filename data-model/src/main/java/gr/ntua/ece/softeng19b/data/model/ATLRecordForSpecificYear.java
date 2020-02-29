package gr.ntua.ece.softeng19b.data.model;

public class ATLRecordForSpecificYear extends AbstractEntsoeRecord {

    private double actualDataLoadByMonthValue;
    
   
    public ATLRecordForSpecificYear() {
        super(DataSet.ActualTotalLoad);
    }

    public double getActualTotalLoadByMonthValue() {
        return actualDataLoadByMonthValue;
    }

    public void setActualTotalLoadByMonthValue(double actualDataLoadByMonthValue) {
        this.actualDataLoadByMonthValue = actualDataLoadByMonthValue;
    }
}
