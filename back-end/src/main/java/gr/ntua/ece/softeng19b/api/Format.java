package gr.ntua.ece.softeng19b.api;

import com.google.gson.stream.JsonWriter;
import gr.ntua.ece.softeng19b.api.representation.RepresentationGenerator;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.WriterRepresentation;
import org.restlet.resource.ResourceException;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.function.Consumer;

public enum Format implements RepresentationGenerator {
    JSON {
        public Representation generateRepresentation(List<ATLRecordForSpecificDay> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(ATLRecordForSpecificDay rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentationDATLFRFSD(List<DATLFRecordForSpecificDay> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(DATLFRecordForSpecificDay rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentationDATLFRFSM(List<DATLFRecordForSpecificMonth> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(DATLFRecordForSpecificMonth rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("Day").value(rec.getDay());
                        w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

    },
    CSV {
        public Representation generateRepresentation(List<ATLRecordForSpecificDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentationDATLFRFSD(List<DATLFRecordForSpecificDay> result) {
            throw new UnsupportedOperationException("Implement this");
        }
        public Representation generateRepresentationDATLFRFSM(List<DATLFRecordForSpecificMonth> result) {
            throw new UnsupportedOperationException("Implement this");
        }
    };

    private static final class CustomJsonRepresentation extends WriterRepresentation {

        private final Consumer<JsonWriter> consumer;

        CustomJsonRepresentation(Consumer<JsonWriter> consumer) {
            super(MediaType.APPLICATION_JSON);
            this.consumer = consumer;
        }

        @Override
        public void write(Writer writer) throws IOException {
            JsonWriter jsonWriter = new JsonWriter(writer);
            consumer.accept(jsonWriter);
        }
    }
}
