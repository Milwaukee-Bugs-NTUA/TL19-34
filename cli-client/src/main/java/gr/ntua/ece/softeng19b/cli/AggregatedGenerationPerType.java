package gr.ntua.ece.softeng19b.cli;

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
            CommandLine cli = spec.commandLine();
            cli.usage(cli.getOut());
            return 0;
        }

        try {
            if (dateArgs.date != null ) {
                List<AGPTRecordForSpecificDay> records = new RestAPI().
                        getAggregatedGenerationPerType(areaName, productionType, timeres.name(), LocalDate.parse(dateArgs.date), format);
                // Do something with the records :)
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            else if (dateArgs.month != null) {
                List<AGPTRecordForSpecificMonth> records = new RestAPI().
                        getAggregatedGenerationPerType(areaName, productionType, timeres.name(), YearMonth.parse(dateArgs.month), format);
                // Do something with the records :)
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            else if (dateArgs.year != null) {
                List<AGPTRecordForSpecificYear> records = new RestAPI().
                        getAggregatedGenerationPerType(areaName, productionType, timeres.name(), Year.parse(dateArgs.year), format);
                // Do something with the records :)
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
      
        return 0;
    }
}