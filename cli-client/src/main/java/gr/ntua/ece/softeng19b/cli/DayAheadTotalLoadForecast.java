package gr.ntua.ece.softeng19b.cli;

import com.google.gson.stream.JsonWriter;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificYear;
import picocli.CommandLine;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.util.List;
import java.util.concurrent.Callable;
import java.io.OutputStreamWriter;

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
            if (dateArgs.date != null ) {
                List<DATLFRecordForSpecificDay> records = new RestAPI().
                        getDayAheadTotalLoadForecast(areaName, timeres.name(), LocalDate.parse(dateArgs.date), format);
                // Do something with the records :)
                if (records.isEmpty() == true) {
                    System.out.println("Fetched 0 records");
                    return 0;
                }
                JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out));
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
                    w.name("DayAheadTotalLoadForecastValue").value(rec.getDayAheadTotalLoadForecastValue());
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
                List<DATLFRecordForSpecificMonth> records = new RestAPI().
                        getDayAheadTotalLoadForecast(areaName, timeres.name(), YearMonth.parse(dateArgs.month), format);
                // Do something with the records :)
                if (records.isEmpty() == true) {
                    System.out.println("Fetched 0 records");
                    return 0;
                }
                JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out));
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
                    w.name("DayAheadTotalLoadForecastByDayValue").value(rec.getDayAheadTotalLoadForecastValue());
                    w.endObject(); // }
                    w.flush();
                }
                w.endArray();
                w.flush();
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
                JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out));
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
                    w.name("DayAheadTotalLoadForecastByMonthValue").value(rec.getDayAheadTotalLoadForecastValue());
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
                // Implement the other cases
                System.err.println("Not implemented yet");
                return -1;
            }
        } catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }

    }
}
