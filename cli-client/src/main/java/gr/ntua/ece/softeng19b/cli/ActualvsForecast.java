package gr.ntua.ece.softeng19b.cli;

import com.google.gson.stream.JsonWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.client.Format;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificYear;
import picocli.CommandLine;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.time.Month;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.Callable;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

import static picocli.CommandLine.Command;

@Command(
    name="ActualvsForecast"
)
public class ActualvsForecast extends EnergyCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        try {
            if ((format == Format.JSON) || (format == null)) {
                if (dateArgs.date != null ) {
                    List<AVFRecordForSpecificDay> records = new RestAPI().
                            getActualvsForecast(areaName, timeres.name(), LocalDate.parse(dateArgs.date), format);
                    // Do something with the records :)
                    if (records.isEmpty() == true) {
                        System.out.println("Fetched 0 records");
                        return 0;
                    }
                    JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out));
                    w.setIndent("  ");
                    w.beginArray();
                    for(AVFRecordForSpecificDay rec : records){
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
                        w.name("DateTimeUTC").value(rec.getDateTimeUTC());
                        w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
                        w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                        w.name("UpdateTimeUTC").value(rec.getUpdateTimeUTC());
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
                    List<AVFRecordForSpecificMonth> records = new RestAPI().
                            getActualvsForecast(areaName, timeres.name(), YearMonth.parse(dateArgs.month), format);
                    if (records.isEmpty() == true) {
                        System.out.println("Fetched 0 records");
                        return 0;
                    }
                    JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out));
                    w.setIndent("  ");
                    w.beginArray();
                    for(AVFRecordForSpecificMonth rec : records){
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
                    w.endArray();
                    w.flush();
                    System.out.println();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else {
                    List<AVFRecordForSpecificYear> records = new RestAPI().
                            getActualvsForecast(areaName, timeres.name(), Year.parse(dateArgs.year), format);
                    if (records.isEmpty() == true) {
                        System.out.println("Fetched 0 records");
                        return 0;
                    }
                    JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out));
                    w.setIndent("  ");
                    w.beginArray();
                    for(AVFRecordForSpecificYear rec : records){
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
                    w.endArray();
                    w.flush();
                    System.out.println();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
            }
            else {
                // CSV Format
                if (dateArgs.date != null ) {
                    List<AVFRecordForSpecificDay> records = new RestAPI().
                            getActualvsForecast(areaName, timeres.name(), LocalDate.parse(dateArgs.date), Format.JSON);
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
                                                            "ActualTotalLoadValue",
                                                            "UpdateTimeUTC"));
                    for(AVFRecordForSpecificDay rec : records){
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
                                                String.valueOf(rec.getActualTotalLoadValue()),
                                                String.valueOf(rec.getUpdateTimeUTC()));
                    }
                    csvPrinter.flush();                    
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else if (dateArgs.month != null){
                    List<AVFRecordForSpecificMonth> records = new RestAPI().
                            getActualvsForecast(areaName, timeres.name(), YearMonth.parse(dateArgs.month), Format.JSON);
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
                                                            "MapCode", 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month", 
                                                            "Day",
                                                            "DayAheadTotalLoadForecastByDayValue",
                                                            "ActualTotalLoadByDayValue"));
                    for(AVFRecordForSpecificMonth rec : records){
                        csvPrinter.printRecord(rec.getSource(),
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
                    csvPrinter.flush();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else {
                    List<AVFRecordForSpecificYear> records = new RestAPI().
                            getActualvsForecast(areaName, timeres.name(), Year.parse(dateArgs.year), Format.JSON);
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
                                                            "MapCode", 
                                                            "ResolutionCode", 
                                                            "Year", 
                                                            "Month", 
                                                            "DayAheadTotalLoadForecastByMonthValue",
                                                            "ActualTotalLoadByMonthValue"));
                    for(AVFRecordForSpecificYear rec : records){
                        csvPrinter.printRecord(rec.getSource(),
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
