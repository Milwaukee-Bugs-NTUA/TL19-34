package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.Format;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.DataAccess;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;

import java.util.List;
import io.jsonwebtoken.*;
import java.util.Base64;

/**
 * The Restlet resource that deals with the /ActualDataLoad/... payloads.
 */
public class ActualvsForecastForSpecificYear extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();
    private static Status outOfQuotasStatus = new Status(402, "Out of Quotas");

    @Override
    protected Representation get() throws ResourceException {

        // Read the mandatory URI attributes

        String h = getRequest().getHeaders().toString();
        String token = null;

        try {
            token = getToken(h);
        } catch (Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, e.getMessage(), e);
        }

        String userName = Jwts.parser()
                .setSigningKey(Base64.getDecoder().decode("J0KlwfLrnZ92TWJ0VgZXZjTAnQynDpnYY4TYdBTvtOc="))
                .parseClaimsJws(token).getBody().get("username").toString();

        String areaName = getMandatoryAttribute("AreaName", "AreaName is missing");
        String resolution = getMandatoryAttribute("Resolution", "Resolution is missing");

        // Read the optional date attribute
        String yearParam = getAttributeDecoded("year");
        int year = Integer.parseInt(yearParam);

        // Use the EnergyResource.parseXXX methods to parse the dates and implement the
        // required business logic
        // For the sake of this example, we hard-code a date
        Year year1 = Year.of(year);

        // Read the format query parameter
        Format format = parseFormat(getQueryValue("format"));

        try {

            List<AVFRecordForSpecificYear> result = dataAccess.fetchActualvsForecastForSpecificYear(areaName,
                    resolution, year1, userName);
            return format.generateRepresentationAVFFSY(result);
        } catch (Exception e) {
            if (e.getMessage().equals("Out of Quotas (402) - Out of Quotas!"))
                throw new ResourceException(outOfQuotasStatus, e.getMessage(), e);
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }

}