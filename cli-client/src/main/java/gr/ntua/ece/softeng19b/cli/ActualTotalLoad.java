package gr.ntua.ece.softeng19b.cli;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;


import picocli.CommandLine;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.time.Month;
import java.util.List;
import java.util.concurrent.Callable;

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
            if (dateArgs.date != null ) {
                List<ATLRecordForSpecificDay> records = new RestAPI().
                        getActualTotalLoad(areaName, timeres.name(), LocalDate.parse(dateArgs.date), format);
                // Do something with the records :)
                System.out.print("[");
                for(ATLRecordForSpecificDay rec : records){
                    Gson gson = new GsonBuilder().setPrettyPrinting().create(); //Pretty Builder for gson
                    String jsonString = gson.toJson(rec);
                    System.out.print(jsonString);
                    if (records.indexOf(rec) != (records.size() - 1)) {
                        System.out.println(",");
                    }
                }
                System.out.println("]");
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            else if (dateArgs.month != null){
                List<ATLRecordForSpecificMonth> records = new RestAPI().
                        getActualTotalLoad(areaName, timeres.name(), YearMonth.parse(dateArgs.month), format);
         
                System.out.println("Fetched " + records.size() + " records");
                return 0;
            }
            else {
                List<ATLRecordForSpecificYear> records = new RestAPI().
                        getActualTotalLoad(areaName, timeres.name(), Year.parse(dateArgs.year), format);
         
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
