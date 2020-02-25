package gr.ntua.ece.softeng19b.api.resource;

import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import gr.ntua.ece.softeng19b.data.model.User;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.data.Form;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.conf.Configuration;
import java.util.List;
import org.restlet.data.Status;

import io.jsonwebtoken.Jwts;

import java.util.Map;

public class Login extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        // This is a dummy login implementation for the sake of the front-end example app
        
        Form form = new Form(entity);
        String userName = form.getFirstValue("username");
        String password = form.getFirstValue("password");
        System.out.println(userName+" "+password+" geia sas");

        try {

            User user = dataAccess.Login(userName, password);
            if(user!=null) System.out.println(user.getUserName()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getAdmin()+" "+user.getRequestsPerDayQuota());
            else System.out.println("no such user");
            return new JsonMapRepresentation(Map.of("token", "dummy-user-token"));
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }
        
}
