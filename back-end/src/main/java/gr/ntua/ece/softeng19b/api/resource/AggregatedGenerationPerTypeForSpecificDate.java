package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.DataAccess;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;
import java.util.List;

/**
 * The Restlet resource that deals with the /ActualDataLoad/... payloads.
 */
public class AggregatedGenerationPerTypeForSpecificDate extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        //Read the mandatory URI attributes
        String areaName = getMandatoryAttribute("AreaName", "AreaName is missing");
        String resolution = getMandatoryAttribute("Resolution", "Resolution is missing");
        String productionType = getMandatoryAttribute("ProductionType", "ProductionType is missing");
        //Read the optional date attribute
        String dateParam = getAttributeDecoded("date");
        String values[] = dateParam.split("-");
        int year = Integer.parseInt(values[0]);
        int month = Integer.parseInt(values[1]);
        int day = Integer.parseInt(values[2]);
        
        //Use the EnergyResource.parseXXX methods to parse the dates and implement the required business logic
        //For the sake of this example, we hard-code a date
        LocalDate date = LocalDate.of(year, month, day);

        //Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<AGPTRecordForSpecificDay> result = dataAccess.fetchAggregatedGenerationPerTypeForSpecificDate(
                    areaName,
                    resolution,
                    productionType,
                    date
            );
            return format.generateRepresentationAGPTFSD(result);
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }


}
