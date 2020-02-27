package gr.ntua.ece.softeng19b.cli;

import com.google.gson.stream.JsonWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.client.Format;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;
import picocli.CommandLine;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.util.List;
import java.util.concurrent.Callable;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

import static picocli.CommandLine.Command;

@Command(
    name="ActualTotalLoad"
)
public class ActualTotalLoad extends EnergyCliArgs implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        try {
            if ((format == Format.JSON) || (format == null)){
                if (dateArgs.date != null ) {
                    List<ATLRecordForSpecificDay> records = new RestAPI().
                            getActualTotalLoad(areaName, timeres.name(), LocalDate.parse(dateArgs.date), format);
                    // Do something with the records :)
                    if (records.isEmpty() == true) {
                        System.out.println("Fetched 0 records");
                        return 0;
                    }
                    JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out,"UTF-8"));
                    w.setIndent("  ");
                    w.beginArray();
                    //System.out.print("[ ");
                    for(ATLRecordForSpecificDay rec : records){
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
                        /*if (records.indexOf(rec) != (records.size() - 1)) {
                            System.out.println(",");
                        }*/
                    }
                    w.endArray();
                    w.flush();
                    System.out.println();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else if (dateArgs.month != null){
                    List<ATLRecordForSpecificMonth> records = new RestAPI().
                            getActualTotalLoad(areaName, timeres.name(), YearMonth.parse(dateArgs.month), format);
                    if (records.isEmpty() == true) {
                        System.out.println("Fetched 0 records");
                        return 0;
                    }
                    JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out,"UTF-8"));
                    w.setIndent("  ");
                    w.beginArray();
                    for(ATLRecordForSpecificMonth rec : records){
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
                    w.endArray();
                    w.flush();
                    System.out.println();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else {
                    List<ATLRecordForSpecificYear> records = new RestAPI().
                            getActualTotalLoad(areaName, timeres.name(), Year.parse(dateArgs.year), format);
                    if (records.isEmpty() == true) {
                        System.out.println("Fetched 0 records");
                        return 0;
                    }
                    JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out,"UTF-8"));
                    w.setIndent("  ");
                    w.beginArray();
                    for(ATLRecordForSpecificYear rec : records){
                        w.beginObject(); // {
                        w.name("Source").value(rec.getSource());
                        w.name("DataSet").value(rec.getDataSet());
                        w.name("AreaName").value(rec.getAreaName());
                        w.name("AreaTypeCode").value(rec.getAreaTypeCode());
                        w.name("MapCode").value(rec.getMapCode());
                        w.name("ResolutionCode").value(rec.getResolutionCode());
                        w.name("Year").value(rec.getYear());
                        w.name("Month").value(rec.getMonth());
                        w.name("ActualTotalLoadByMonthValue").value(rec.getActualDataLoadByMonthValue());
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
                // CSV Formating
                if (dateArgs.date != null ) {
                    List<ATLRecordForSpecificDay> records = new RestAPI().
                            getActualTotalLoad(areaName, timeres.name(), LocalDate.parse(dateArgs.date), Format.JSON);
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
                                                            "ActualTotalLoadValue"));
                    for(ATLRecordForSpecificDay rec : records){
                        csvPrinter.printRecord(rec.getSource(),
                                                rec.getDataSet(), 
                                                rec.getAreaName(), 
                                                rec.getAreaTypeCode(), 
                                                rec.getMapCode(), 
                                                rec.getResolutionCode(), 
                                                String.valueOf(rec.getYear()), 
                                                String.valueOf(rec.getMonth()), 
                                                String.valueOf(rec.getDay()), 
                                                String.valueOf(rec.getActualTotalLoadValue()));
                    }
                    csvPrinter.flush();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else if (dateArgs.month != null){
                    List<ATLRecordForSpecificMonth> records = new RestAPI().
                            getActualTotalLoad(areaName, timeres.name(), YearMonth.parse(dateArgs.month), Format.JSON);
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
                                                            "ActualTotalLoadValue"));
                    for(ATLRecordForSpecificMonth rec : records){
                        csvPrinter.printRecord(rec.getSource(),
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
                    csvPrinter.flush();
                    System.out.println();
                    System.out.println("Fetched " + records.size() + " records");
                    return 0;
                }
                else {
                    List<ATLRecordForSpecificYear> records = new RestAPI().
                            getActualTotalLoad(areaName, timeres.name(), Year.parse(dateArgs.year), Format.JSON);
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
                                                            "ActualTotalLoadByMonthValue"));
                    for(ATLRecordForSpecificYear rec : records){
                        csvPrinter.printRecord(rec.getSource(),
                                                rec.getDataSet(), 
                                                rec.getAreaName(), 
                                                rec.getAreaTypeCode(), 
                                                rec.getMapCode(), 
                                                rec.getResolutionCode(), 
                                                String.valueOf(rec.getYear()), 
                                                String.valueOf(rec.getMonth()), 
                                                String.valueOf(rec.getActualDataLoadByMonthValue()));
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
