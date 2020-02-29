package gr.ntua.ece.softeng19b.client;

import com.google.gson.stream.JsonReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificYear;


import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public enum Format implements ResponseBodyProcessor {

    JSON {
        /* Actual Total Load related consume methods */
        //for date
        @Override
        public List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualTotalLoadRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        //for month
        @Override
        public List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualTotalLoadRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        //for year
        @Override
        public List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualTotalLoadRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        /* Day Ahead Total Load Forecast related consume methods */
        //for date
        @Override
        public List<DATLFRecordForSpecificDay> consumeDayAheadTotalLoadForecastRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readDayAheadTotalLoadForecastRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        //for month
        @Override
        public List<DATLFRecordForSpecificMonth> consumeDayAheadTotalLoadForecastRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readDayAheadTotalLoadForecastRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        //for year
        @Override
        public List<DATLFRecordForSpecificYear> consumeDayAheadTotalLoadForecastRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readDayAheadTotalLoadForecastRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        /* Aggregated Generation Per Type related consume methods */
        //for day
        @Override
        public List<AGPTRecordForSpecificDay> consumeAggregatedGenerationPerTypeRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readAggregatedGenerationPerTypeRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        //for month
        @Override
        public List<AGPTRecordForSpecificMonth> consumeAggregatedGenerationPerTypeRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readAggregatedGenerationPerTypeRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        //for year
        @Override
        public List<AGPTRecordForSpecificYear> consumeAggregatedGenerationPerTypeRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readAggregatedGenerationPerTypeRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        /* Actual Total Load VS Day Ahead Forecast related consume methods */
        //for date
        @Override
        public List<AVFRecordForSpecificDay> consumeActualvsForecastRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualvsForecastRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        //for month
        @Override
        public List<AVFRecordForSpecificMonth> consumeActualvsForecastRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualvsForecastRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        //for year
        @Override
        public List<AVFRecordForSpecificYear> consumeActualvsForecastRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualvsForecastRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    },
    CSV {
        /* Actual Total Load related consume methods */
        //for date
        @Override
        public List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readActualTotalLoadRecordsForSpecificDay(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        //for month
        @Override
        public List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readActualTotalLoadRecordsForSpecificMonth(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        //for year
        @Override
        public List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readActualTotalLoadRecordsForSpecificYear(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        /* Day Ahead Total Load Forecast related consume methods */
        //for date
        @Override
        public List<DATLFRecordForSpecificDay> consumeDayAheadTotalLoadForecastRecordsForSpecificDay(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readDayAheadTotalLoadForecastRecordsForSpecificDay(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        //for month
        @Override
        public List<DATLFRecordForSpecificMonth> consumeDayAheadTotalLoadForecastRecordsForSpecificMonth(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readDayAheadTotalLoadForecastRecordsForSpecificMonth(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        //for year
        @Override
        public List<DATLFRecordForSpecificYear> consumeDayAheadTotalLoadForecastRecordsForSpecificYear(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readDayAheadTotalLoadForecastRecordsForSpecificYear(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        
        /* Aggregated Generation Per Type related consume methods */
        //for date
        @Override
        public List<AGPTRecordForSpecificDay> consumeAggregatedGenerationPerTypeRecordsForSpecificDay(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readAggregatedGenerationPerTypeRecordsForSpecificDay(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        //for month
        @Override
        public List<AGPTRecordForSpecificMonth> consumeAggregatedGenerationPerTypeRecordsForSpecificMonth(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readAggregatedGenerationPerTypeRecordsForSpecificMonth(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        //for year
        @Override
        public List<AGPTRecordForSpecificYear> consumeAggregatedGenerationPerTypeRecordsForSpecificYear(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readAggregatedGenerationPerTypeRecordsForSpecificYear(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

        /* Actual Total Load VS Day Ahead Forecast related get methods */
        //for date
        @Override
        public List<AVFRecordForSpecificDay> consumeActualvsForecastRecordsForSpecificDay(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readActualvsForecastRecordsForSpecificDay(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        //for month
        @Override
        public List<AVFRecordForSpecificMonth> consumeActualvsForecastRecordsForSpecificMonth(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readActualvsForecastRecordsForSpecificMonth(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        //for year
        @Override
        public List<AVFRecordForSpecificYear> consumeActualvsForecastRecordsForSpecificYear(Reader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                return readActualvsForecastRecordsForSpecificYear(bufferedReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    };

    /*****  READ *****/
    /* Actual Total Load related read methods */
    //for day
    // JSON
    private static List<ATLRecordForSpecificDay> readActualTotalLoadRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualTotalLoadRecordForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static ATLRecordForSpecificDay readActualTotalLoadRecordForSpecificDay(JsonReader reader)
            throws IOException {
        ATLRecordForSpecificDay rec = new ATLRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DateTimeUTC":
                    rec.setDateTimeUTC(reader.nextString());
                    break;
                case "ActualTotalLoadValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpdateTimeUTC(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }    
    // CSV
    private static List<ATLRecordForSpecificDay> readActualTotalLoadRecordsForSpecificDay(BufferedReader reader)
            throws IOException {
        List<ATLRecordForSpecificDay> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readActualTotalLoadRecordForSpecificDay(csvRecord));
        }
        return result;
    }
    // CSV
    private static ATLRecordForSpecificDay readActualTotalLoadRecordForSpecificDay(CSVRecord csvRecord)
            throws IOException {
        ATLRecordForSpecificDay rec = new ATLRecordForSpecificDay();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setDay(Integer.valueOf(csvRecord.get("Day")));
        rec.setDateTimeUTC(csvRecord.get("DateTimeUTC"));
        rec.setActualTotalLoadValue(Double.valueOf(csvRecord.get("ActualTotalLoadValue")));
        rec.setUpdateTimeUTC(csvRecord.get("UpdateTimeUTC"));
        return rec;
    }

    //for month
    // JSON
    private static List<ATLRecordForSpecificMonth> readActualTotalLoadRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualTotalLoadRecordForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static ATLRecordForSpecificMonth readActualTotalLoadRecordForSpecificMonth(JsonReader reader)
            throws IOException {
        ATLRecordForSpecificMonth rec = new ATLRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ActualTotalLoadByDayValue":
                    rec.setActualTotalLoadByDayValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<ATLRecordForSpecificMonth> readActualTotalLoadRecordsForSpecificMonth(BufferedReader reader)
            throws IOException {
        List<ATLRecordForSpecificMonth> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readActualTotalLoadRecordForSpecificMonth(csvRecord));
        }
        return result;
    }
    // CSV
    private static ATLRecordForSpecificMonth readActualTotalLoadRecordForSpecificMonth(CSVRecord csvRecord)
            throws IOException {
        ATLRecordForSpecificMonth rec = new ATLRecordForSpecificMonth();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setDay(Integer.valueOf(csvRecord.get("Day")));
        rec.setActualTotalLoadByDayValue(Double.valueOf(csvRecord.get("ActualTotalLoadByDayValue")));
        return rec;
    }

    //for year
    // JSON
    private static List<ATLRecordForSpecificYear> readActualTotalLoadRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualTotalLoadRecordForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static ATLRecordForSpecificYear readActualTotalLoadRecordForSpecificYear(JsonReader reader)
            throws IOException {
        ATLRecordForSpecificYear rec = new ATLRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "ActualTotalLoadByMonthValue":
                    rec.setActualTotalLoadByMonthValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<ATLRecordForSpecificYear> readActualTotalLoadRecordsForSpecificYear(BufferedReader reader)
            throws IOException {
        List<ATLRecordForSpecificYear> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readActualTotalLoadRecordForSpecificYear(csvRecord));
        }
        return result;
    }
    // CSV
    private static ATLRecordForSpecificYear readActualTotalLoadRecordForSpecificYear(CSVRecord csvRecord)
            throws IOException {
        ATLRecordForSpecificYear rec = new ATLRecordForSpecificYear();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setActualTotalLoadByMonthValue(Double.valueOf(csvRecord.get("ActualTotalLoadByMonthValue")));
        return rec;
    }

    /* Day Ahead Total Load Forecast related read methods */
    //for day
    // JSON
    private static List<DATLFRecordForSpecificDay> readDayAheadTotalLoadForecastRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<DATLFRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readDayAheadTotalLoadForecastRecordForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static DATLFRecordForSpecificDay readDayAheadTotalLoadForecastRecordForSpecificDay(JsonReader reader)
            throws IOException {
        DATLFRecordForSpecificDay rec = new DATLFRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DateTimeUTC":
                    rec.setDateTimeUTC(reader.nextString());
                    break;
                case "DayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpdateTimeUTC(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<DATLFRecordForSpecificDay> readDayAheadTotalLoadForecastRecordsForSpecificDay(BufferedReader reader)
            throws IOException {
        List<DATLFRecordForSpecificDay> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readDayAheadTotalLoadForecastRecordForSpecificDay(csvRecord));
        }
        return result;
    }
    // CSV
    private static DATLFRecordForSpecificDay readDayAheadTotalLoadForecastRecordForSpecificDay(CSVRecord csvRecord)
            throws IOException {
        DATLFRecordForSpecificDay rec = new DATLFRecordForSpecificDay();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setDay(Integer.valueOf(csvRecord.get("Day")));
        rec.setDateTimeUTC(csvRecord.get("DateTimeUTC"));
        rec.setDayAheadTotalLoadForecastValue(Double.valueOf(csvRecord.get("DayAheadTotalLoadForecastValue")));
        rec.setUpdateTimeUTC(csvRecord.get("UpdateTimeUTC"));
        return rec;
    }

    //for month
    // JSON
    private static List<DATLFRecordForSpecificMonth> readDayAheadTotalLoadForecastRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<DATLFRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readDayAheadTotalLoadForecastRecordForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static DATLFRecordForSpecificMonth readDayAheadTotalLoadForecastRecordForSpecificMonth(JsonReader reader)
            throws IOException {
        DATLFRecordForSpecificMonth rec = new DATLFRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DayAheadTotalLoadForecastByDayValue":
                    rec.setDayAheadTotalLoadForecastByDayValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<DATLFRecordForSpecificMonth> readDayAheadTotalLoadForecastRecordsForSpecificMonth(BufferedReader reader)
            throws IOException {
        List<DATLFRecordForSpecificMonth> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readDayAheadTotalLoadForecastRecordForSpecificMonth(csvRecord));
        }
        return result;
    }
    // CSV
    private static DATLFRecordForSpecificMonth readDayAheadTotalLoadForecastRecordForSpecificMonth(CSVRecord csvRecord)
            throws IOException {
        DATLFRecordForSpecificMonth rec = new DATLFRecordForSpecificMonth();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setDay(Integer.valueOf(csvRecord.get("Day")));
        rec.setDayAheadTotalLoadForecastByDayValue(Double.valueOf(csvRecord.get("DayAheadTotalLoadForecastByDayValue")));
        return rec;
    }

    //for year
    // JSON
    private static List<DATLFRecordForSpecificYear> readDayAheadTotalLoadForecastRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<DATLFRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readDayAheadTotalLoadForecastRecordForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static DATLFRecordForSpecificYear readDayAheadTotalLoadForecastRecordForSpecificYear(JsonReader reader)
            throws IOException {
        DATLFRecordForSpecificYear rec = new DATLFRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "DayAheadTotalLoadForecastByMonthValue":
                    rec.setDayAheadTotalLoadForecastByMonthValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<DATLFRecordForSpecificYear> readDayAheadTotalLoadForecastRecordsForSpecificYear(BufferedReader reader)
            throws IOException {
        List<DATLFRecordForSpecificYear> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readDayAheadTotalLoadForecastRecordForSpecificYear(csvRecord));
        }
        return result;
    }
    // CSV
    private static DATLFRecordForSpecificYear readDayAheadTotalLoadForecastRecordForSpecificYear(CSVRecord csvRecord)
            throws IOException {
        DATLFRecordForSpecificYear rec = new DATLFRecordForSpecificYear();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setDayAheadTotalLoadForecastByMonthValue(Double.valueOf(csvRecord.get("DayAheadTotalLoadForecastByMonthValue")));
        return rec;
    }

    /* Aggregated Generation Per Type related read methods */
    //for day
    // JSON
    private static List<AGPTRecordForSpecificDay> readAggregatedGenerationPerTypeRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<AGPTRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readAggregatedGenerationPerTypeRecordForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static AGPTRecordForSpecificDay readAggregatedGenerationPerTypeRecordForSpecificDay(JsonReader reader)
            throws IOException {
        AGPTRecordForSpecificDay rec = new AGPTRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ProductionType":
                    rec.setProductionType(reader.nextString());
                    break;
                case "DateTimeUTC":
                    rec.setDateTimeUTC(reader.nextString());
                    break;
                case "ActualGenerationOutputValue":
                    rec.setActualGenerationOutputValue(reader.nextDouble());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpdateTimeUTC(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<AGPTRecordForSpecificDay> readAggregatedGenerationPerTypeRecordsForSpecificDay(BufferedReader reader)
            throws IOException {
        List<AGPTRecordForSpecificDay> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readAggregatedGenerationPerTypeRecordForSpecificDay(csvRecord));
        }
        return result;
    }
    // CSV
    private static AGPTRecordForSpecificDay readAggregatedGenerationPerTypeRecordForSpecificDay(CSVRecord csvRecord)
            throws IOException {
        AGPTRecordForSpecificDay rec = new AGPTRecordForSpecificDay();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setDay(Integer.valueOf(csvRecord.get("Day")));
        rec.setProductionType(csvRecord.get("ProductionType"));
        rec.setDateTimeUTC(csvRecord.get("DateTimeUTC"));
        rec.setActualGenerationOutputValue(Double.valueOf(csvRecord.get("ActualGenerationOutputValue")));
        rec.setUpdateTimeUTC(csvRecord.get("UpdateTimeUTC"));
        return rec;
    }

    //for month
    // JSON
    private static List<AGPTRecordForSpecificMonth> readAggregatedGenerationPerTypeRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<AGPTRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readAggregatedGenerationPerTypeRecordForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static AGPTRecordForSpecificMonth readAggregatedGenerationPerTypeRecordForSpecificMonth(JsonReader reader)
            throws IOException {
        AGPTRecordForSpecificMonth rec = new AGPTRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "ProductionType":
                    rec.setProductionType(reader.nextString());
                    break;
                case "ActualGenerationOutputByDayValue":
                    rec.setActualGenerationOutputByDayValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<AGPTRecordForSpecificMonth> readAggregatedGenerationPerTypeRecordsForSpecificMonth(BufferedReader reader)
            throws IOException {
        List<AGPTRecordForSpecificMonth> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readAggregatedGenerationPerTypeRecordForSpecificMonth(csvRecord));
        }
        return result;
    }
    // CSV
    private static AGPTRecordForSpecificMonth readAggregatedGenerationPerTypeRecordForSpecificMonth(CSVRecord csvRecord)
            throws IOException {
        AGPTRecordForSpecificMonth rec = new AGPTRecordForSpecificMonth();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setDay(Integer.valueOf(csvRecord.get("Day")));
        rec.setProductionType(csvRecord.get("ProductionType"));
        rec.setActualGenerationOutputByDayValue(Double.valueOf(csvRecord.get("ActualGenerationOutputByDayValue")));
        return rec;
    }

    //for year
    // JSON
    private static List<AGPTRecordForSpecificYear> readAggregatedGenerationPerTypeRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<AGPTRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readAggregatedGenerationPerTypeRecordForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static AGPTRecordForSpecificYear readAggregatedGenerationPerTypeRecordForSpecificYear(JsonReader reader)
            throws IOException {
        AGPTRecordForSpecificYear rec = new AGPTRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "ProductionType":
                    rec.setProductionType(reader.nextString());
                    break;
                case "ActualGenerationOutputByMonthValue":
                    rec.setActualGenerationOutputByMonthValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<AGPTRecordForSpecificYear> readAggregatedGenerationPerTypeRecordsForSpecificYear(BufferedReader reader)
            throws IOException {
        List<AGPTRecordForSpecificYear> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readAggregatedGenerationPerTypeRecordForSpecificYear(csvRecord));
        }
        return result;
    }
    // CSV
    private static AGPTRecordForSpecificYear readAggregatedGenerationPerTypeRecordForSpecificYear(CSVRecord csvRecord)
            throws IOException {
        AGPTRecordForSpecificYear rec = new AGPTRecordForSpecificYear();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setProductionType(csvRecord.get("ProductionType"));
        rec.setActualGenerationOutputByMonthValue(Double.valueOf(csvRecord.get("ActualGenerationOutputByMonthValue")));
        return rec;
    }

    /* Actual Total Load VS Day Ahead Forecast related read methods */
    //for day
    // JSON
    private static List<AVFRecordForSpecificDay> readActualvsForecastRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<AVFRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualvsForecastRecordForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static AVFRecordForSpecificDay readActualvsForecastRecordForSpecificDay(JsonReader reader)
            throws IOException {
        AVFRecordForSpecificDay rec = new AVFRecordForSpecificDay();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DateTimeUTC":
                    rec.setDateTimeUTC(reader.nextString());
                    break;
                case "DayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                case "ActualTotalLoadValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;
                case "UpdateTimeUTC":
                    rec.setUpdateTimeUTC(reader.nextString());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<AVFRecordForSpecificDay> readActualvsForecastRecordsForSpecificDay(BufferedReader reader)
            throws IOException {
        List<AVFRecordForSpecificDay> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readActualvsForecastRecordForSpecificDay(csvRecord));
        }
        return result;
    }
    // CSV
    private static AVFRecordForSpecificDay readActualvsForecastRecordForSpecificDay(CSVRecord csvRecord)
            throws IOException {
        AVFRecordForSpecificDay rec = new AVFRecordForSpecificDay();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setDay(Integer.valueOf(csvRecord.get("Day")));
        rec.setDateTimeUTC(csvRecord.get("DateTimeUTC"));
        rec.setDayAheadTotalLoadForecastValue(Double.valueOf(csvRecord.get("DayAheadTotalLoadForecastValue")));
        rec.setActualTotalLoadValue(Double.valueOf(csvRecord.get("ActualTotalLoadValue")));
        rec.setUpdateTimeUTC(csvRecord.get("UpdateTimeUTC"));
        return rec;
    }

    //for month
    // JSON
    private static List<AVFRecordForSpecificMonth> readActualvsForecastRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<AVFRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualvsForecastRecordForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static AVFRecordForSpecificMonth readActualvsForecastRecordForSpecificMonth(JsonReader reader)
            throws IOException {
        AVFRecordForSpecificMonth rec = new AVFRecordForSpecificMonth();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "Day":
                    rec.setDay(reader.nextInt());
                    break;
                case "DayAheadTotalLoadForecastByDayValue":
                    rec.setDayAheadTotalLoadForecastByDayValue(reader.nextDouble());
                    break;
                case "ActualTotalLoadByDayValue":
                    rec.setActualTotalLoadByDayValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<AVFRecordForSpecificMonth> readActualvsForecastRecordsForSpecificMonth(BufferedReader reader)
            throws IOException {
        List<AVFRecordForSpecificMonth> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readActualvsForecastRecordForSpecificMonth(csvRecord));
        }
        return result;
    }
    // CSV
    private static AVFRecordForSpecificMonth readActualvsForecastRecordForSpecificMonth(CSVRecord csvRecord)
            throws IOException {
        AVFRecordForSpecificMonth rec = new AVFRecordForSpecificMonth();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setDay(Integer.valueOf(csvRecord.get("Day")));
        rec.setDayAheadTotalLoadForecastByDayValue(Double.valueOf(csvRecord.get("DayAheadTotalLoadForecastByDayValue")));
        rec.setActualTotalLoadByDayValue(Double.valueOf(csvRecord.get("ActualTotalLoadByDayValue")));
        return rec;
    }

    //for year
    // JSON
    private static List<AVFRecordForSpecificYear> readActualvsForecastRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<AVFRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualvsForecastRecordForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }
    // JSON
    private static AVFRecordForSpecificYear readActualvsForecastRecordForSpecificYear(JsonReader reader)
            throws IOException {
        AVFRecordForSpecificYear rec = new AVFRecordForSpecificYear();
        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "AreaName":
                    rec.setAreaName(reader.nextString());
                    break;
                case "AreaTypeCode":
                    rec.setAreaTypeCode(reader.nextString());
                    break;
                case "MapCode":
                    rec.setMapCode(reader.nextString());
                    break;
                case "ResolutionCode":
                    rec.setResolutionCode(reader.nextString());
                    break;
                case "Year":
                    rec.setYear(reader.nextInt());
                    break;
                case "Month":
                    rec.setMonth(reader.nextInt());
                    break;
                case "DayAheadTotalLoadForecastByMonthValue":
                    rec.setDayAheadTotalLoadForecastByMonthValue(reader.nextDouble());
                    break;
                case "ActualTotalLoadByMonthValue":
                    rec.setActualTotalLoadByMonthValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
    // CSV
    private static List<AVFRecordForSpecificYear> readActualvsForecastRecordsForSpecificYear(BufferedReader reader)
            throws IOException {
        List<AVFRecordForSpecificYear> result = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                                .withFirstRecordAsHeader()
                                                .withIgnoreHeaderCase()
                                                .withTrim());
        for (CSVRecord csvRecord : csvParser) {
            result.add(readActualvsForecastRecordForSpecificYear(csvRecord));
        }
        return result;
    }
    // CSV
    private static AVFRecordForSpecificYear readActualvsForecastRecordForSpecificYear(CSVRecord csvRecord)
            throws IOException {
        AVFRecordForSpecificYear rec = new AVFRecordForSpecificYear();
        rec.setAreaName(csvRecord.get("AreaName"));
        rec.setAreaTypeCode(csvRecord.get("AreaTypeCode"));
        rec.setMapCode(csvRecord.get("MapCode"));
        rec.setResolutionCode(csvRecord.get("ResolutionCode"));
        rec.setYear(Integer.valueOf(csvRecord.get("Year")));
        rec.setMonth(Integer.valueOf(csvRecord.get("Month")));
        rec.setDayAheadTotalLoadForecastByMonthValue(Double.valueOf(csvRecord.get("DayAheadTotalLoadForecastByMonthValue")));
        rec.setActualTotalLoadByMonthValue(Double.valueOf(csvRecord.get("ActualTotalLoadByMonthValue")));
        return rec;
    }
}
