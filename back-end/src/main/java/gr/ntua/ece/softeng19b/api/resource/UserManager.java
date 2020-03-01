package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.representation.WriterRepresentation;
import org.restlet.data.MediaType;
import org.restlet.data.Form;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.User;
import com.google.gson.stream.JsonWriter;
import java.io.Writer;
import java.util.function.Consumer;
import java.net.http.HttpRequest;

import gr.ntua.ece.softeng19b.data.DataAccess;
import java.io.IOException;
import io.jsonwebtoken.*;
import java.util.Base64;

public class UserManager extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {
        //read existing user
        String h = getRequest().getHeaders().toString();
        String token = null;

        try{
            token = getToken(h);
        }
        catch(Exception e)
        {
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, e.getMessage(), e);
        }
        String adminUserName = Jwts.parser()         
                            .setSigningKey(Base64.getDecoder().decode("J0KlwfLrnZ92TWJ0VgZXZjTAnQynDpnYY4TYdBTvtOc="))
                            .parseClaimsJws(token).getBody().get("username").toString();
        String userName = getMandatoryAttribute("username", "username is missing");
        User u;

        try{
            u = dataAccess.getUser(adminUserName, userName);
        }
        catch(Exception e)
        {
            if(e.getMessage().equals("Unauthorized (401) - User has no Admin Priveleges!")){
                throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, e.getMessage());
            }
            else throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

        return new CustomJsonRepresentation( (JsonWriter w) -> {
            try {
                w.beginObject(); // {
                w.name("Username").value(u.getUserName());
                w.name("Email").value(u.getEmail());
                w.name("Admin").value(u.getAdmin());
                w.name("RequestsPerDayQuota").value(u.getRequestsPerDayQuota());
                w.name("UsedPerDayQuota").value(u.getUsedPerDayQuota());
                w.endObject(); // }
                w.flush();
            } 
            catch (IOException e) {
                throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
            }
        });
    }


    @Override
    protected Representation put(Representation entity) throws ResourceException {
        //update existing user
        String h = getRequest().getHeaders().toString();
        String token = null;
        
        try{
            token = getToken(h);
        }
        catch(Exception e)
        {
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, e.getMessage(), e);
        }
        String adminUserName = Jwts.parser()         
                            .setSigningKey(Base64.getDecoder().decode("J0KlwfLrnZ92TWJ0VgZXZjTAnQynDpnYY4TYdBTvtOc="))
                            .parseClaimsJws(token).getBody().get("username").toString();

        throw new ResourceException(Status.SERVER_ERROR_NOT_IMPLEMENTED);
    }


    private static final class CustomJsonRepresentation extends WriterRepresentation {

        private final Consumer<JsonWriter> consumer;

        CustomJsonRepresentation(Consumer<JsonWriter> consumer) {
            super(MediaType.APPLICATION_JSON);
            this.consumer = consumer;
        }

        @Override
        public void write(Writer writer) throws IOException {
            JsonWriter jsonWriter = new JsonWriter(writer);
            consumer.accept(jsonWriter);
        }
    }
}
