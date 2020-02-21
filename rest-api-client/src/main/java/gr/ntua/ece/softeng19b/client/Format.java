package gr.ntua.ece.softeng19b.client;

import com.google.gson.stream.JsonReader;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;

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
    },
    CSV {
        @Override
        public List<ATLRecordForSpecificDay> consumeActualTotalLoadRecordsForSpecificDay(Reader reader) {
            throw new UnsupportedOperationException("Implement this");
        }

        //for month

        @Override
        public List<ATLRecordForSpecificMonth> consumeActualTotalLoadRecordsForSpecificMonth(Reader reader) {
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
}
