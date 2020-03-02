package gr.ntua.ece.softeng19b.api.resource;

import org.restlet.data.Status;
import org.restlet.representation.Representation;
import gr.ntua.ece.softeng19b.api.representation.JsonMapRepresentation;
import org.restlet.data.MediaType;
import org.restlet.data.Form;
import org.restlet.representation.WriterRepresentation;
import org.restlet.resource.ResourceException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.fileupload.*;
import java.util.Collections;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import org.restlet.representation.StringRepresentation;
import org.restlet.ext.fileupload.RestletFileUpload;
import java.util.Base64;
import java.io.Writer;
import java.util.function.Consumer;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import gr.ntua.ece.softeng19b.data.DataAccess;
import gr.ntua.ece.softeng19b.conf.Configuration;
import io.jsonwebtoken.*;

public class ImportAggregatedGenerationPerTypeDataSet extends EnergyResource {
    
    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();
    
    @Override
    protected Representation post(Representation entity) throws ResourceException {
        
        // Import dataset
        Representation result = null;
        String fileName = null;

        String h = getRequest().getHeaders().toString();
        String token = null;

        try{
            token = getToken(h);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, e.getMessage(), e);
        }
        String adminUserName = Jwts.parser()         
                            .setSigningKey(Base64.getDecoder().decode("J0KlwfLrnZ92TWJ0VgZXZjTAnQynDpnYY4TYdBTvtOc="))
                            .parseClaimsJws(token).getBody().get("username").toString();

        if (entity != null) {
            int numberOfLines = -1;
            if (MediaType.MULTIPART_FORM_DATA.equals(entity.getMediaType(), true)) {
                // 1/ Create a factory for disk-based file items
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(1000240);

                // 2/ Create a new file upload handler based on the Restlet
                // FileUpload extension that will parse Restlet requests and
                // generates FileItems.
                RestletFileUpload upload = new RestletFileUpload(factory);

                // 3/ Request is parsed by the handler which generates a
                // list of FileItems
                FileItemIterator fileIterator;
                FileItemStream fi;
                try{
                    fileIterator = upload.getItemIterator(entity);
                }
                catch(Exception e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
                }

                // Process only the uploaded item called "fileToUpload"
                // and return back
                boolean found = false;
                try{
                    while (fileIterator.hasNext() && !found) {
                        fi = fileIterator.next();
                        fileName = fi.getName();
                        if (fi.getFieldName().equals("fileToUpload")) {
                            found = true;
                            // consume the stream immediately, otherwise the stream
                            // will be closed.
                            StringBuilder sb = new StringBuilder("");
                            BufferedReader br = new BufferedReader(
                                new InputStreamReader(fi.openStream(), StandardCharsets.UTF_8));
                            String line = null;
                            while ((line = br.readLine()) != null) {
                                    sb.append(line);
                                    sb.append("\n");
                                    numberOfLines = numberOfLines + 1;
                            }
                            BufferedWriter bw =  new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/" + fi.getName()));
                            bw.write(sb.toString());
                            bw.flush();
                            bw.close();                            
                        }
                    }
                }
                catch(Exception e){
                    throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
                }
            } 
            else {
                // POST request with no entity.
                setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
                return new JsonMapRepresentation(Collections.emptyMap());
            }
            // Call Data Access
            try{
                int[] rows = dataAccess.importAggregatedGenerationPerTypeDataSet(adminUserName, fileName);
                final int rowsInFile = numberOfLines;
                return new CustomJsonRepresentation( (JsonWriter w) -> {
                    try {
                        w.beginObject(); // {
                        w.name("TotalRecordsInFile").value(rowsInFile);
                        w.name("TotalRecordsImported").value(rows[0]);
                        w.name("TotalRecordsInDatabase").value(rows[1]);
                        w.endObject(); // }
                        w.flush();
                    } 
                    
                    catch (IOException e) {
                        throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
                    }
                });
            }
            catch(Exception e)
            {
                if(e.getMessage().equals("Unauthorized (401) - User has no Admin Priveleges!")){
                    throw new ResourceException(Status.CLIENT_ERROR_UNAUTHORIZED, e.getMessage());
                }
                else throw new ResourceException(Status.SERVER_ERROR_INTERNAL, e.getMessage(), e);
            }
        }
        return new JsonMapRepresentation(Collections.emptyMap());
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
