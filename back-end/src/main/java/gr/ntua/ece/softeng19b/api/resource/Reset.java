package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;

public class Reset extends EnergyResource {

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        //reset the database
        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }
}
