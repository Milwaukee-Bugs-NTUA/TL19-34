package gr.ntua.ece.softeng19b.client;

import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificYear;


import java.io.Reader;
import java.util.List;

public interface ResponseBodyProcessor {

    /* Actual Total Load related consume methods */
    List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader);
    List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader);
    List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader);
    /* Day Ahead Total Load Forecast related consume methods */
    List<DATLFRecordForSpecificDay> consumeDayAheadTotalLoadForecastRecordsForSpecificDay(Reader reader);
    List<DATLFRecordForSpecificMonth> consumeDayAheadTotalLoadForecastRecordsForSpecificMonth(Reader reader);
    List<DATLFRecordForSpecificYear> consumeDayAheadTotalLoadForecastRecordsForSpecificYear(Reader reader);
    /* Aggregated Generation Per Type related consume methods */
    List<AGPTRecordForSpecificDay> consumeAggregatedGenerationPerTypeRecordsForSpecificDay(Reader reader);
    List<AGPTRecordForSpecificMonth> consumeAggregatedGenerationPerTypeRecordsForSpecificMonth(Reader reader);
    List<AGPTRecordForSpecificYear> consumeAggregatedGenerationPerTypeRecordsForSpecificYear(Reader reader);
}
