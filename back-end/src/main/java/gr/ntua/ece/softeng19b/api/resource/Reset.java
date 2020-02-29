package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.data.DataAccessException;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.Map;

public class Reset extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {

        try {
            System.out.println("hello from reset of back-end");
            dataAccess.resetDatabase();
            return new JsonMapRepresentation(Map.of("status", "OK"));
        }
        catch(DataAccessException e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, "Data access exception: " + e.getMessage(), e);
        }


    }
}