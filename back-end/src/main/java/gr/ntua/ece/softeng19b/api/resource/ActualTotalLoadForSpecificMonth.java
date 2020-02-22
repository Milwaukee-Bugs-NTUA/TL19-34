package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.DataAccess;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;

import java.util.List;

/**
 * The Restlet resource that deals with the /ActualDataLoad/... payloads.
 */
public class ActualTotalLoadForSpecificMonth extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        //Read the mandatory URI attributes
        String areaName = getMandatoryAttribute("AreaName", "AreaName is missing");
        String resolution = getMandatoryAttribute("Resolution", "Resolution is missing");

        //Read the optional date attribute
        String monthParam = getAttributeDecoded("month");
        String values[] = monthParam.split("-");
        int year = Integer.parseInt(values[0]);
        int month = Integer.parseInt(values[1]);
       
        //Use the EnergyResource.parseXXX methods to parse the dates and implement the required business logic
        //For the sake of this example, we hard-code a date
        YearMonth yearMonth = YearMonth.of(year, month);
        System.out.println(year +"-"+ month);

        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<ATLRecordForSpecificMonth> result = dataAccess.fetchActualDataLoadForSpecificMonth(
                    areaName,
                    resolution,
                    yearMonth
            );
            return format.generateRepresentationATLFSM(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }


}
