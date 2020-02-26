package gr.ntua.ece.softeng19b.cli;

import com.google.gson.stream.JsonWriter;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificYear;
import picocli.CommandLine;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.time.Month;
import java.util.List;
import java.util.concurrent.Callable;
import java.io.OutputStreamWriter;

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
                    w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
                    w.name("ActualTotalLoadValue").value(rec.getActualTotalLoadValue());
                    w.endObject(); // }
                    w.flush();
                }
                w.endArray();
                w.flush();
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
                    w.name("DayAheadTotalLoadForecastByDayValue").value(rec.getDayAheadTotalLoadForecastValue());
                    w.name("ActualTotalLoadByDayValue").value(rec.getActualTotalLoadValue());
                    w.endObject(); // }
                    w.flush();
                }
                w.endArray();
                w.flush();
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
                    w.name("DayAheadTotalLoadForecastByMonthValue").value(rec.getDayAheadTotalLoadForecastValue());
                    w.name("ActualTotalLoadByMonthValue").value(rec.getActualTotalLoadValue());
                    w.endObject(); // }
                    w.flush();
                }
                w.endArray();
                w.flush();
                System.out.println();
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            
        } catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
    }
}
