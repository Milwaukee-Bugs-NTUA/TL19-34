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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Consumer;

import gr.ntua.ece.softeng19b.data.DataAccess;
import java.io.IOException;
import io.jsonwebtoken.*;
import java.util.Base64;
import java.security.*;
import java.nio.charset.StandardCharsets;

public class AddUser extends EnergyResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    private static String generateHash(String data, String algorithm, byte[] salt) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        digest.update(salt);
        byte[] hash = digest.digest(data.getBytes());
        return bytesToStringHex(hash);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToStringHex(byte[] bytes){
        char[] hexChars = new char [bytes.length*2];
        for(int j=0; j<bytes.length; j++){
            int v = bytes[j] & 0xFF;
            hexChars[j*2] = hexArray[v>>>4];
            hexChars[j*2+1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte [] createSalt(){
        String salt = getAlphaNumericString(10);
        System.out.println(salt);
        return salt.getBytes(StandardCharsets.UTF_8);
    }

    public static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        // create new user
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
        Form form = new Form(entity);
        String userName = form.getFirstValue("username");
        String password = form.getFirstValue("password");
        String email = form.getFirstValue("email");
        String algorithm = "SHA-512";
        String salt = getAlphaNumericString(10);
        try{
            password = generateHash(password, algorithm, salt.getBytes());
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong :(");
        }
        int requestedPerDayQuota = Integer.valueOf(form.getFirstValue("requestsPerDayQuota"));
        User u;

        try{
            u = dataAccess.addUser(adminUserName, userName, password, email, requestedPerDayQuota, salt);
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

