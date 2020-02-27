package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.data.Status;
import java.text.Normalizer.Form;
import java.util.Collections;

public class Logout extends EnergyResource {

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        
        String h = getRequest().getHeaders().toString();
        String token = null;

        try{
            token = getToken(h);
        }
        catch(Exception e)
        {
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, e.getMessage(), e);
        }
        return new JsonMapRepresentation(Collections.emptyMap());
    }
}
