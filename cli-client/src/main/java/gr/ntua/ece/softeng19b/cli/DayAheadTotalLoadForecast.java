package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import picocli.CommandLine;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.concurrent.Callable;

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
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            else if (dateArgs.month != null){
                List<DATLFRecordForSpecificMonth> records = new RestAPI().
                        getDayAheadTotalLoadForecast(areaName, timeres.name(), YearMonth.parse(dateArgs.month), format);
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

    }
}
