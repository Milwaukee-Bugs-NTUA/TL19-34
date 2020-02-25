package gr.ntua.ece.softeng19b.cli;

//import org.json.simple.JSONObject;
import com.google.gson.stream.JsonWriter;

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
import java.io.OutputStreamWriter;

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
                JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out));
                w.beginArray();
                //System.out.print("[");
                for(ATLRecordForSpecificDay rec : records){
                    /*JSONObject jsonObj  = new JSONObject();
                    jsonObj.put("Source",rec.getSource());
                    jsonObj.put("Dataset",rec.getDataSet());
                    jsonObj.put("AreaName",rec.getAreaName());
                    jsonObj.put("AreaTypeCode",rec.getAreaTypeCode());
                    jsonObj.put("MapCode",rec.getMapCode());
                    jsonObj.put("ResolutionCode",rec.getResolutionCode());
                    jsonObj.put("Year",String.valueOf(rec.getYear()));
                    jsonObj.put("Month",String.valueOf(rec.getMonth()));
                    jsonObj.put("Day",String.valueOf(rec.getDay()));
                    jsonObj.put("ActualTotalLoadValue",String.valueOf(rec.getActualTotalLoadValue()));
                    System.out.print(jsonObj.toJSONString());*/
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
                    //if (records.indexOf(rec) != (records.size() - 1)) {
                    //    System.out.println(",");
                    //}
                }
                w.endArray();
                //System.out.println("]");
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
