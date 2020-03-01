package gr.ntua.ece.softeng19b.cli;

import com.google.gson.stream.JsonWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.client.Format;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificYear;
import picocli.CommandLine;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.Callable;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

import static picocli.CommandLine.Command;

@Command(
    name="DayAheadTotalLoadForecast"
)
public class DayAheadTotalLoadForecast extends EnergyCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }


        try {
            if (format == Format.JSON) {
                if (dateArgs.date != null ) {
                    List<DATLFRecordForSpecificDay> records = new RestAPI().
                            getDayAheadTotalLoadForecast(areaName, timeres.name(), LocalDate.parse(dateArgs.date), format);
                    // Do something with the records :)
                    if (records.isEmpty() == true) {
                        System.out.println("Fetched 0 records");
                        return 0;
                    }
                    JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out,"UTF-8"));
                    w.setIndent("  ");
                    w.beginArray();
                    for(DATLFRecordForSpecificDay rec : records){
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
                    w.endArray();
                    w.flush();
                    System.out.println();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else if (dateArgs.month != null){
                    List<DATLFRecordForSpecificMonth> records = new RestAPI().
                            getDayAheadTotalLoadForecast(areaName, timeres.name(), YearMonth.parse(dateArgs.month), format);
                    // Do something with the records :)
                    if (records.isEmpty() == true) {
                        System.out.println("Fetched 0 records");
                        return 0;
                    }
                    JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out,"UTF-8"));
                    w.setIndent("  ");
                    w.beginArray();
                    for(DATLFRecordForSpecificMonth rec : records){
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
                    w.endArray();
                    w.flush();
                    System.out.println();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else if (dateArgs.year != null){
                    List<DATLFRecordForSpecificYear> records = new RestAPI().
                            getDayAheadTotalLoadForecast(areaName, timeres.name(), Year.parse(dateArgs.year), format);
                    // Do something with the records :)
                    if (records.isEmpty() == true) {
                        System.out.println("Fetched 0 records");
                        return 0;
                    }
                    JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out,"UTF-8"));
                    w.setIndent("  ");
                    w.beginArray();
                    for(DATLFRecordForSpecificYear rec : records){
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
                    w.endArray();
                    w.flush();
                    System.out.println();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else {
                    // Implement the other cases
                    System.err.println("Not implemented yet");
                    return -1;
                }
            }
            else {
                // CSV Formating
                if (dateArgs.date != null ) {
                    List<DATLFRecordForSpecificDay> records = new RestAPI().
                            getDayAheadTotalLoadForecast(areaName, timeres.name(), LocalDate.parse(dateArgs.date), format);
                    // Do something with the records :)
                    if (records.isEmpty() == true) {
                        System.out.println("Fetched 0 records");
                        return 0;
                    }
                    CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(System.out, "UTF-8"), 
                                                            CSVFormat.DEFAULT.withHeader(
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
                    for(DATLFRecordForSpecificDay rec : records){
                        csvPrinter.printRecord(rec.getSource(),
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
                    csvPrinter.flush();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else if (dateArgs.month != null){
                    List<DATLFRecordForSpecificMonth> records = new RestAPI().
                            getDayAheadTotalLoadForecast(areaName, timeres.name(), YearMonth.parse(dateArgs.month), format);
                    CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(System.out, "UTF-8"), 
                                                            CSVFormat.DEFAULT.withHeader(
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
                    for(DATLFRecordForSpecificMonth rec : records){
                        csvPrinter.printRecord(rec.getSource(),
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
                    csvPrinter.flush();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else {
                    List<DATLFRecordForSpecificYear> records = new RestAPI().
                            getDayAheadTotalLoadForecast(areaName, timeres.name(), Year.parse(dateArgs.year), format);
                    CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(System.out, "UTF-8"), 
                                                            CSVFormat.DEFAULT.withHeader(
                                                            "Source", 
                                                            "DataSet", 
                                                            "AreaName" , 
                                                            "AreaTypeCode", 
                                                            "MapCode" , 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month",
                                                            "DayAheadTotalLoadForecastByMonthValue"));
                    for(DATLFRecordForSpecificYear rec : records){
                        csvPrinter.printRecord(rec.getSource(),
                                                rec.getDataSet(), 
                                                rec.getAreaName(), 
                                                rec.getAreaTypeCode(), 
                                                rec.getMapCode(), 
                                                rec.getResolutionCode(), 
                                                String.valueOf(rec.getYear()), 
                                                String.valueOf(rec.getMonth()), 
                                                String.valueOf(rec.getDayAheadTotalLoadForecastByMonthValue()));
                    }
                    csvPrinter.flush();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
            }
        } catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
        catch (UnsupportedEncodingException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
        catch (IOException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
    }
}
