package gr.ntua.ece.softeng19b.api;

import com.google.gson.stream.JsonWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import gr.ntua.ece.softeng19b.api.representation.RepresentationGenerator;
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
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.WriterRepresentation;
import org.restlet.resource.ResourceException;

import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.util.List;
import java.util.function.Consumer;

public enum Format implements RepresentationGenerator {
    JSON {
        public Representation generateRepresentationATLFSD(List<ATLRecordForSpecificDay> result) {
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
                        w.name("DateTimeUTC").value(rec.getDateTimeUTC().toString());
                        w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                        w.name("UpdateTimeUTC").value(rec.getUpdateTimeUTC().toString());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationATLFSM(List<ATLRecordForSpecificMonth> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(ATLRecordForSpecificMonth rec: result) {
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
                        w.name("ActualTotalLoadByDayValue").value(rec.getActualTotalLoadByDayValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationATLFSY(List<ATLRecordForSpecificYear> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(ATLRecordForSpecificYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("ActualTotalLoadByMonthValue").value(rec.getActualTotalLoadByMonthValue());
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
                        w.name("DateTimeUTC").value(rec.getDateTimeUTC().toString());
                        w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
                        w.name("UpdateTimeUTC").value(rec.getUpdateTimeUTC().toString());
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
                        w.name("DayAheadTotalLoadForecastByDayValue").value(rec.getDayAheadTotalLoadForecastByDayValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationDATLFRFSY(List<DATLFRecordForSpecificYear> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(DATLFRecordForSpecificYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("DayAheadTotalLoadForecastByMonthValue").value(rec.getDayAheadTotalLoadForecastByMonthValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationAGPTFSD(List<AGPTRecordForSpecificDay> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AGPTRecordForSpecificDay rec: result) {
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
                        w.name("ProductionType").value(rec.getProductionType());
                        w.name("DateTimeUTC").value(rec.getDateTimeUTC().toString());
                        w.name("ActualGenerationOutputValue").value(rec.getActualGenerationOutputValue());
                        w.name("UpdateTimeUTC").value(rec.getUpdateTimeUTC().toString());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentationAGPTFSM(List<AGPTRecordForSpecificMonth> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AGPTRecordForSpecificMonth rec: result) {
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
                        w.name("ProductionType").value(rec.getProductionType());
                        w.name("ActualGenerationOutputByDayValue").value(rec.getActualGenerationOutputByDayValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentationAGPTFSY(List<AGPTRecordForSpecificYear> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AGPTRecordForSpecificYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("ProductionType").value(rec.getProductionType());
                        w.name("ActualGenerationOutputByMonthValue").value(rec.getActualGenerationOutputByMonthValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationAVFFSD(List<AVFRecordForSpecificDay> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AVFRecordForSpecificDay rec: result) {
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
                        w.name("DateTimeUTC").value(rec.getDateTimeUTC().toString());
                        w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
                        w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                        w.name("UpdateTimeUTC").value(rec.getUpdateTimeUTC().toString());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationAVFFSM(List<AVFRecordForSpecificMonth> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AVFRecordForSpecificMonth rec: result) {
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
                        w.name("DayAheadTotalLoadForecastByDayValue").value(rec.getDayAheadTotalLoadForecastByDayValue());
                        w.name("ActualTotalLoadByDayValue").value(rec.getActualTotalLoadByDayValue());
                        w.endObject(); // }
                        w.flush();
                    }
                    w.endArray(); // ]
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationAVFFSY(List<AVFRecordForSpecificYear> result) {
            return new CustomJsonRepresentation( (JsonWriter w) -> {
                try {
                    w.beginArray(); // [
                    for(AVFRecordForSpecificYear rec: result) {
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("DayAheadTotalLoadForecastByMonthValue").value(rec.getDayAheadTotalLoadForecastByMonthValue());
                        w.name("ActualTotalLoadByMonthValue").value(rec.getActualTotalLoadByMonthValue());
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
        // Actual Total Load generateRepresentation
        public Representation generateRepresentationATLFSD(List<ATLRecordForSpecificDay> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "Day",
                                                            "DateTimeUTC",
                                                            "ActualTotalLoadValue",
                                                            "UpdateTimeUTC"));
                    for(ATLRecordForSpecificDay rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getDay()),
                                        String.valueOf(rec.getDateTimeUTC()),
                                        String.valueOf(rec.getActualTotalLoadValue()),
                                        String.valueOf(rec.getUpdateTimeUTC()));
                    }
                    p.flush();//w.name("DateTimeUTC").value(rec.getDateTimeUTC().toString());
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationATLFSM(List<ATLRecordForSpecificMonth> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "Day",
                                                            "ActualTotalLoadByDayValue"));
                    for(ATLRecordForSpecificMonth rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getDay()),
                                        String.valueOf(rec.getActualTotalLoadByDayValue()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationATLFSY(List<ATLRecordForSpecificYear> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "ActualTotalLoadByMonthValue"));
                    for(ATLRecordForSpecificYear rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getActualTotalLoadByMonthValue()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        // Day Ahead Total Load Forecast generateRepresentation
        public Representation generateRepresentationDATLFRFSD(List<DATLFRecordForSpecificDay> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "Day",
                                                            "DateTimeUTC",
                                                            "DayAheadTotalLoadForecastValue",
                                                            "UpdateTimeUTC"));
                    for(DATLFRecordForSpecificDay rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getDay()),
                                        String.valueOf(rec.getDateTimeUTC()),
                                        String.valueOf(rec.getDayAheadTotalLoadForecastValue()),
                                        String.valueOf(rec.getUpdateTimeUTC()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentationDATLFRFSM(List<DATLFRecordForSpecificMonth> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "Day",
                                                            "DayAheadTotalLoadForecastByDayValue"));
                    for(DATLFRecordForSpecificMonth rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getDay()),
                                        String.valueOf(rec.getDayAheadTotalLoadForecastByDayValue()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        public Representation generateRepresentationDATLFRFSY(List<DATLFRecordForSpecificYear> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "DayAheadTotalLoadForecastByMonthValue"));
                    for(DATLFRecordForSpecificYear rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getDayAheadTotalLoadForecastByMonthValue()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        // Aggregated Generation Per Type generateRepresentation
        public Representation generateRepresentationAGPTFSD(List<AGPTRecordForSpecificDay> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "Day",
                                                            "ProductionType",
                                                            "DateTimeUTC",
                                                            "ActualGenerationOutputValue",
                                                            "UpdateTimeUTC"));
                    for(AGPTRecordForSpecificDay rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getDay()),
                                        rec.getProductionType(),
                                        String.valueOf(rec.getDateTimeUTC()),
                                        String.valueOf(rec.getActualGenerationOutputValue()),
                                        String.valueOf(rec.getUpdateTimeUTC()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationAGPTFSM(List<AGPTRecordForSpecificMonth> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "Day",
                                                            "ProductionType",
                                                            "ActualGenerationOutputByDayValue"));
                    for(AGPTRecordForSpecificMonth rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getDay()),
                                        rec.getProductionType(),
                                        String.valueOf(rec.getActualGenerationOutputByDayValue()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationAGPTFSY(List<AGPTRecordForSpecificYear> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "ProductionType",
                                                            "ActualGenerationOutputByMonthValue"));
                    for(AGPTRecordForSpecificYear rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        rec.getProductionType(),
                                        String.valueOf(rec.getActualGenerationOutputByMonthValue()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }
        // Actual VS Forecast generateRepresentation
        public Representation generateRepresentationAVFFSD(List<AVFRecordForSpecificDay> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "Day",
                                                            "DateTimeUTC",
                                                            "DayAheadTotalLoadForecastValue",
                                                            "ActualTotalLoadValue",
                                                            "UpdateTimeUTC"));
                    for(AVFRecordForSpecificDay rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getDay()),
                                        String.valueOf(rec.getDateTimeUTC()),
                                        String.valueOf(rec.getDayAheadTotalLoadForecastValue()),
                                        String.valueOf(rec.getActualTotalLoadValue()),
                                        String.valueOf(rec.getUpdateTimeUTC()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationAVFFSM(List<AVFRecordForSpecificMonth> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "Day",
                                                            "DayAheadTotalLoadForecastByDayValue",
                                                            "ActualTotalLoadByDayValue"));
                    for(AVFRecordForSpecificMonth rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getDay()),
                                        String.valueOf(rec.getDayAheadTotalLoadForecastByDayValue()),
                                        String.valueOf(rec.getActualTotalLoadByDayValue()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
        }

        public Representation generateRepresentationAVFFSY(List<AVFRecordForSpecificYear> result) {
            return new CustomCSVRepresentation( (BufferedWriter w) -> {
                try {
                    CSVPrinter p = new CSVPrinter(w,CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "DayAheadTotalLoadForecastByMonthValue",
                                                            "ActualTotalLoadByMonthValue"));
                    for(AVFRecordForSpecificYear rec : result){
                        p.printRecord(rec.getSource(),
                                        rec.getDataSet(), 
                                        rec.getAreaName(), 
                                        rec.getAreaTypeCode(), 
                                        rec.getMapCode(), 
                                        rec.getResolutionCode(), 
                                        String.valueOf(rec.getYear()), 
                                        String.valueOf(rec.getMonth()),
                                        String.valueOf(rec.getDayAheadTotalLoadForecastByMonthValue()),
                                        String.valueOf(rec.getActualTotalLoadByMonthValue()));
                    }
                    p.flush();
                } catch (IOException e) {
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL);
                }
            });
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
    private static final class CustomCSVRepresentation extends WriterRepresentation {

        private final Consumer<BufferedWriter> consumer;

        CustomCSVRepresentation(Consumer<BufferedWriter> consumer) {
            super(MediaType.TEXT_CSV);
            this.consumer = consumer;
        }

        @Override
        public void write(Writer writer) throws IOException {
            //CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            consumer.accept(new BufferedWriter(writer));
        }
    }
}
