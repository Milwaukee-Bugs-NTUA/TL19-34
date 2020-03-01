package gr.ntua.ece.softeng19b.client;

public class ImportResult {

    private /*final*/ long totalRecordsInFile;
    private /*final*/ long totalRecordsImported;
    private /*final*/ long totalRecordsInDatabase;

    public ImportResult(){
        
    }

    public ImportResult(long totalRecordsInFile, long totalRecordsImported, long totalRecordsInDatabase) {
        this.totalRecordsInFile = totalRecordsInFile;
        this.totalRecordsImported = totalRecordsImported;
        this.totalRecordsInDatabase = totalRecordsInDatabase;
    }

    public long getTotalRecordsInFile() {
        return totalRecordsInFile;
    }

    public long getTotalRecordsImported() {
        return totalRecordsImported;
    }

    public long getTotalRecordsInDatabase() {
        return totalRecordsInDatabase;
    }

    public void setTotalRecordsInFile(long totalRecordsInFile) {
        this.totalRecordsInFile = totalRecordsInFile;
    }

    public void setTotalRecordsImported(long totalRecordsImported) {
        this.totalRecordsImported = totalRecordsImported;
    }

    public void setTotalRecordsInDatabase(long totalRecordsInDatabase) {
        this.totalRecordsInDatabase = totalRecordsInDatabase;
    }
}
