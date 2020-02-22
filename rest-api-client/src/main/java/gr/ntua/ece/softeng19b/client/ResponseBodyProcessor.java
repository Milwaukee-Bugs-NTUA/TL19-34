package gr.ntua.ece.softeng19b.client;

import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;

import java.io.Reader;
import java.util.List;

public interface ResponseBodyProcessor {

    List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader);
    List<DATLFRecordForSpecificDay> consumeDayAheadTotalLoadForecastRecordsForSpecificDay(Reader reader);
    List<DATLFRecordForSpecificMonth> consumeDayAheadTotalLoadForecastRecordsForSpecificMonth(Reader reader);
}
