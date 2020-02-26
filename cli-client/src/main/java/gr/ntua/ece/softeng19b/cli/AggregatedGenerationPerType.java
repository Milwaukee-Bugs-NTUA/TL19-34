package gr.ntua.ece.softeng19b.cli;

import com.google.gson.stream.JsonWriter;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificYear;
import picocli.CommandLine;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.util.List;
import java.util.concurrent.Callable;
import java.io.OutputStreamWriter;

import static picocli.CommandLine.*;

@Command(
    name="AggregatedGenerationPerType"
)
public class AggregatedGenerationPerType extends EnergyCliArgs implements Callable<Integer> {

    @Option(
        names = "--prodtype",
        required = true,
        description = "the production type."
    )
    protected String productionType;

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }

        try {
            if (dateArgs.date != null ) {
                List<AGPTRecordForSpecificDay> records = new RestAPI().
                        getAggregatedGenerationPerType(areaName, productionType, timeres.name(), LocalDate.parse(dateArgs.date), format);
                // Do something with the records :)
                if (records.isEmpty() == true) {
                    System.out.println("Fetched 0 records");
                    return 0;
                }
                JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out));
                w.setIndent("  ");
                w.beginArray();
                for(AGPTRecordForSpecificDay rec : records){
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
                    w.name("ActualGenerationOutputValue").value(rec.getActualGenerationOutputValue());
                    w.endObject(); // }
                    w.flush();
                }
                w.endArray();
                w.flush();
                System.out.println();
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            else if (dateArgs.month != null) {
                List<AGPTRecordForSpecificMonth> records = new RestAPI().
                        getAggregatedGenerationPerType(areaName, productionType, timeres.name(), YearMonth.parse(dateArgs.month), format);
                // Do something with the records :)
                if (records.isEmpty() == true) {
                    System.out.println("Fetched 0 records");
                    return 0;
                }
                JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out));
                w.setIndent("  ");
                w.beginArray();
                for(AGPTRecordForSpecificMonth rec : records){
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
                    w.name("ActualGenerationOutputByDayValue").value(rec.getActualGenerationOutputValue());
                    w.endObject(); // }
                    w.flush();
                }
                w.endArray();
                w.flush();
                System.out.println();
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            else if (dateArgs.year != null) {
                List<AGPTRecordForSpecificYear> records = new RestAPI().
                        getAggregatedGenerationPerType(areaName, productionType, timeres.name(), Year.parse(dateArgs.year), format);
                // Do something with the records :)
                if (records.isEmpty() == true) {
                    System.out.println("Fetched 0 records");
                    return 0;
                }
                JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out));
                w.setIndent("  ");
                w.beginArray();
                for(AGPTRecordForSpecificYear rec : records){
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
                    w.name("ActualGenerationOutputByMonthValue").value(rec.getActualGenerationOutputValue());
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