package gr.ntua.ece.softeng19b.client;

import com.google.gson.stream.JsonReader;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;


import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public enum Format implements ResponseBodyProcessor {

    JSON {
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



        @Override
        public List<DATLFRecordForSpecificDay> consumeDayAheadTotalLoadForecastRecordsForSpecificDay(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readDayAheadTotalLoadForecastRecordsForSpecificDay(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }
        
        @Override
        public List<DATLFRecordForSpecificMonth> consumeDayAheadTotalLoadForecastRecordsForSpecificMonth(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readDayAheadTotalLoadForecastRecordsForSpecificMonth(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        }

        @Override
        public List<DATLFRecordForSpecificYear> consumeDayAheadTotalLoadForecastRecordsForSpecificYear(Reader reader) {
            try (JsonReader jsonReader = new JsonReader(reader)) {
                return readDayAheadTotalLoadForecastRecordsForSpecificYear(jsonReader);
            }
            catch(IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }

        } 
    },
    CSV {
        @Override
        public List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }
        
        @Override
        public List<DATLFRecordForSpecificDay> consumeDayAheadTotalLoadForecastRecordsForSpecificDay(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }

        @Override
        public List<DATLFRecordForSpecificMonth> consumeDayAheadTotalLoadForecastRecordsForSpecificMonth(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }

        @Override
        public List<DATLFRecordForSpecificYear> consumeDayAheadTotalLoadForecastRecordsForSpecificYear(Reader reader) {
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
    };

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

    // for month

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

}
