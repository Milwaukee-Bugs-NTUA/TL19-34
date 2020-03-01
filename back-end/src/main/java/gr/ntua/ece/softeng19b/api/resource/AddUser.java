package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.WriterRepresentation;
import org.restlet.data.MediaType;
import org.restlet.resource.ResourceException;
import org.restlet.data.Form;
import gr.ntua.ece.softeng19b.conf.Configuration;
import gr.ntua.ece.softeng19b.data.model.User;
import com.google.gson.stream.JsonWriter;
import java.io.Writer;
import java.util.function.Consumer;

import gr.ntua.ece.softeng19b.data.DataAccess;
import java.io.IOException;

public class AddUser extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        
        Form form = new Form(entity);
        String userName = form.getFirstValue("username");
        String password = form.getFirstValue("password");
        String email = form.getFirstValue("email");
        int requestedPerDayQuota = Integer.valueOf(form.getFirstValue("requestsPerDayQuota"));
        User u;

        //create new user

        try{
            u = dataAccess.addUser(userName, password, email, requestedPerDayQuota);
        }
        catch(Exception e)
        {
            throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
        }

        return new CustomJsonRepresentation( (JsonWriter w) -> {
            try {
                w.beginObject(); // {
                w.name("Username").value(u.getUserName());
                w.name("Email").value(u.getEmail());
                w.name("Admin").value(u.getAdmin());
                w.name("RequestsPerDayQuota").value(u.getRequestsPerDayQuota());
                w.endObject(); // }
                w.flush();
            } 
            
            catch (IOException e) {
                throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
            }
        });
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

