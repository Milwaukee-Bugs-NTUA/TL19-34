package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.data.Form;

import java.util.Map;

public class Login extends EnergyResource {

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        // This is a dummy login implementation for the sake of the front-end example app
        
        Form form = new Form(entity);
        String userName = form.getFirstValue("username");
        String password = form.getFirstValue("password");
        System.out.println(userName+" "+password+" geia sas");
        return new JsonMapRepresentation(Map.of("token", "dummy-user-token"));
    }
}
