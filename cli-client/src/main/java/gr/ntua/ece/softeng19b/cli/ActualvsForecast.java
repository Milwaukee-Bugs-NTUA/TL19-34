package gr.ntua.ece.softeng19b.cli;

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
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            else if (dateArgs.month != null){
                List<AVFRecordForSpecificMonth> records = new RestAPI().
                        getActualvsForecast(areaName, timeres.name(), YearMonth.parse(dateArgs.month), format);
         
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            else {
                List<AVFRecordForSpecificYear> records = new RestAPI().
                        getActualvsForecast(areaName, timeres.name(), Year.parse(dateArgs.year), format);
         
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
