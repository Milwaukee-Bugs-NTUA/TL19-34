package gr.ntua.ece.softeng19b.client;

import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;

import java.io.Reader;
import java.util.List;

public interface ResponseBodyProcessor {

    List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader);
    List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader);
}
