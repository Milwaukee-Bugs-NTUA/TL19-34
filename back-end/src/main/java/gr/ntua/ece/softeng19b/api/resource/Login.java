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

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import java.util.Map;

public class Login extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        // This is a dummy login implementation for the sake of the front-end example app
        
        Form form = new Form(entity);
        String userName = form.getFirstValue("username");
        String password = form.getFirstValue("password");

        try {

            User user = dataAccess.Login(userName, password);
            if(user!=null){ 

                //The JWT signature algorithm we will be using to sign the token
                SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    
                //We will sign our JWT with our ApiKey secret
                byte[] secret = Base64.getDecoder().decode("J0KlwfLrnZ92TWJ0VgZXZjTAnQynDpnYY4TYdBTvtOc=");
    
                //Let's set the JWT Claims
                String jwt = Jwts.builder().claim("username", user.getUserName())
                                           .signWith(Keys.hmacShaKeyFor(secret))
                                           .compact();
                return new JsonMapRepresentation(Map.of("token", jwt));                                           
            }
            else return new JsonMapRepresentation(Map.of("token", null));
        } catch (Exception e) {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

    }
        
}
