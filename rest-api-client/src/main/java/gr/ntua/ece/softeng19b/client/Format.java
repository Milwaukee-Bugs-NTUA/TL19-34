package gr.ntua.ece.softeng19b.client;

import com.google.gson.stream.JsonReader;
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
import java.util.ArrayList;
import java.util.List;

public enum Format implements ResponseBodyProcessor {

    JSON {
        /* Actual Total Load related consume methods */
        //for date
        @Override
        public List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualDataLoadRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        //for month
        @Override
        public List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualDataLoadRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        //for year
        @Override
        public List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readActualDataLoadRecordsForSpecificYear(jsonReader);
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
            throw new UnsupportedOperationException("Implement this");
        }
        //for month
        @Override
        public List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        //for year
        @Override
        public List<ATLRecordForSpecificYear> consumeActualTotalLoadRecordsForSpecificYear(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }

        /* Day Ahead Total Load Forecast related consume methods */
        //for date
        @Override
        public List<DATLFRecordForSpecificDay> consumeDayAheadTotalLoadForecastRecordsForSpecificDay(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        //for month
        @Override
        public List<DATLFRecordForSpecificMonth> consumeDayAheadTotalLoadForecastRecordsForSpecificMonth(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        //for year
        @Override
        public List<DATLFRecordForSpecificYear> consumeDayAheadTotalLoadForecastRecordsForSpecificYear(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        
        /* Aggregated Generation Per Type related consume methods */
        //for date
        @Override
        public List<AGPTRecordForSpecificDay> consumeAggregatedGenerationPerTypeRecordsForSpecificDay(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        //for month
        @Override
        public List<AGPTRecordForSpecificMonth> consumeAggregatedGenerationPerTypeRecordsForSpecificMonth(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        //for year
        @Override
        public List<AGPTRecordForSpecificYear> consumeAggregatedGenerationPerTypeRecordsForSpecificYear(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }

        /* Actual Total Load VS Day Ahead Forecast related get methods */
        //for date
        @Override
        public List<AVFRecordForSpecificDay> consumeActualvsForecastRecordsForSpecificDay(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        //for month
        @Override
        public List<AVFRecordForSpecificMonth> consumeActualvsForecastRecordsForSpecificMonth(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        //for year
        @Override
        public List<AVFRecordForSpecificYear> consumeActualvsForecastRecordsForSpecificYear(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
    };

    /*****  READ *****/
    /* Actual Total Load related read methods */
    //for day
    private static List<ATLRecordForSpecificDay> readActualDataLoadRecordsForSpecificDay(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificDay> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualDataLoadRecordForSpecificDay(reader));
        }
        reader.endArray();
        return result;
    }

    private static ATLRecordForSpecificDay readActualDataLoadRecordForSpecificDay(JsonReader reader)
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
                case "ActualTotalLoadValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }    

    //for month
    private static List<ATLRecordForSpecificMonth> readActualDataLoadRecordsForSpecificMonth(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificMonth> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualDataLoadRecordForSpecificMonth(reader));
        }
        reader.endArray();
        return result;
    }

    private static ATLRecordForSpecificMonth readActualDataLoadRecordForSpecificMonth(JsonReader reader)
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

    //for year
    private static List<ATLRecordForSpecificYear> readActualDataLoadRecordsForSpecificYear(JsonReader reader)
            throws IOException {
        List<ATLRecordForSpecificYear> result = new ArrayList<>();
        reader.beginArray();
        while(reader.hasNext()) {
            result.add(readActualDataLoadRecordForSpecificYear(reader));
        }
        reader.endArray();
        return result;
    }

    private static ATLRecordForSpecificYear readActualDataLoadRecordForSpecificYear(JsonReader reader)
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
                case "actualDataLoadByMonthValue":
                    rec.setActualDataLoadByMonthValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    /* Day Ahead Total Load Forecast related read methods */
    //for day
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
                case "DayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    //for month
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
                case "DayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    //for year
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
                case "DayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    /* Aggregated Generation Per Type related read methods */
    //for day
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
                case "ActualGenerationOutputValue":
                    rec.setActualGenerationOutputValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    //for month
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
                case "ActualGenerationOutputValue":
                    rec.setActualGenerationOutputValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    //for year
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
                case "ActualGenerationOutputValue":
                    rec.setActualGenerationOutputValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    /* Actual Total Load VS Day Ahead Forecast related read methods */
    //for day
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
                case "DayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                case "ActualTotalLoadValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }    

    //for month
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
                case "DayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                case "ActualTotalLoadValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }

    //for year
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
                case "dayAheadTotalLoadForecastValue":
                    rec.setDayAheadTotalLoadForecastValue(reader.nextDouble());
                    break;
                case "actualDataLoadByMonthValue":
                    rec.setActualTotalLoadValue(reader.nextDouble());
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        return rec;
    }
}
