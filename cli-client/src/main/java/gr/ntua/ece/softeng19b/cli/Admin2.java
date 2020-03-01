package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;
import gr.ntua.ece.softeng19b.data.model.User;
import com.google.gson.stream.JsonWriter;
import java.io.OutputStreamWriter;

import picocli.CommandLine;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
    name="Admin"
)
public class Admin extends BasicCliArgs implements Callable<Integer> {

    enum DataSet {
        ActualTotalLoad,
        AggregatedGenerationPerType,
        DayAheadTotalLoadForecast
    }

    @ArgGroup(exclusive = true, multiplicity = "1")
    BasicAdminArguments basicAdminArguments;
    
    static class BasicAdminArguments {
        @ArgGroup(exclusive = false, multiplicity = "1")
        UserArgumentGroup userArgumentGroup;

        @ArgGroup(heading = "%nFetch specified user's information from DataBase%n")
        UserStatusArgumentGroup userStatusArgumentGroup;

        @ArgGroup(exclusive = false, multiplicity = "1", heading = "%nOptions for importing data into database%n")
        NewDataArgumentGroup newDataArgumentGroup;
    }

    static class UserArgumentGroup {
        @ArgGroup(exclusive = true, multiplicity = "1", heading = "%nArguments for modifying existing user or adding new user:%n")
        ActionOnUserArgumentGroup actionOnUserArgumentGroup;

        @ArgGroup(exclusive = false, multiplicity = "1", heading = "    User's Information Arguments:%n")
        UserInfo userInfo;
    }

    static class ActionOnUserArgumentGroup {
        @Option(
            names = "--newuser",
            required = true,
            description = "username of the new user"
        ) 
        String newUsername;

        @Option(
            names = "--moduser", 
            required = true,
            description = "username of the user to be modified"
        ) 
        String oldUsername;
    }

    static class UserStatusArgumentGroup {
        @Option(
            names = "--userstatus", 
            required = true,
            description = "username of the user, whose data will be fetched"
        ) 
        String username;
    }

    static class NewDataArgumentGroup {
        @Option(
            names = "--newdata", 
            required = true,
            description = "dataset in which data will be import (ActualTotalLoad, DayAheadTotalLoadForecast, AggregatedGenerationPerType, ActualvsForecast)"    
        )
        DataSet dataSet;

        @Option(
            names = "--source", 
            required = true,
            description = "file to be imported to database"    
        )
        String dataFile;
    }

    static class UserInfo {
        @Option(
            names = "--passw", 
            required = true,
            interactive = true,
            description = "password of specified user"
        ) 
        String password;

        @Option(
            names = "--email", 
            required = true,
            description = "email address of specified user"    
        )
        String email;

        @Option(
            names = "--quotas", 
            required = true,
            description = "currently available or new user quotas"    
        )
        int quotas;
    }

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }
        
        try {
            RestAPI restAPI = new RestAPI();
            //String newUsername = basicAdminArguments.userArgumentGroup.actionOnUserArgumentGroup.newUsername;
            String oldUsername = basicAdminArguments.userArgumentGroup.actionOnUserArgumentGroup.oldUsername;
            String password = basicAdminArguments.userArgumentGroup.userInfo.password;
            String email = basicAdminArguments.userArgumentGroup.userInfo.email;
            int quotas = basicAdminArguments.userArgumentGroup.userInfo.quotas;

            //String username = basicAdminArguments.userStatusArgumentGroup.username;

            //DataSet dataSet = basicAdminArguments.newDataArgumentGroup.dataSet;
            //String dataFile = basicAdminArguments.newDataArgumentGroup.dataFile;

            //System.out.println(newUsername);
            System.out.println(oldUsername);
            System.out.println(password);
            System.out.println(email);
            System.out.println(String.valueOf(quotas));
            //System.out.println(username);
            //System.out.println(String.valueOf(dataSet));
            //System.out.println(dataFile);
            return 0;
        }
            catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }}
/*
            if (newUsername != null) {
                // Add new user
                User newUserObject = restAPI.addUser(newUsername, email, password, quotas);
                JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out,"UTF-8"));
                w.setIndent("  ");
                w.beginObject(); // {
                w.name("Username").value(newUserObject.getUserName());
                w.name("Email").value(newUserObject.getEmail());
                w.name("Admin").value(newUserObject.getAdmin());
                w.name("RequestsPerDayQuota").value(newUserObject.getRequestsPerDayQuota());
                w.endObject(); // }
                w.flush();
                System.out.println();
                System.out.println();
                System.out.println("User added in DataBase successfully!");
                return 0;
            }
            else if (oldUsername != null) {
                // Modify existing user
                //User userObject = new User(oldUsername, email, 0, quotas);
                //userObject.setPassword(password);
                //restAPI.updateUser(userObject);
                System.out.println("Not implemented yet");
                return 0;
            }
            else if (username != null) {
                // Fetch user status
                User newUserObject = restAPI.getUser(username);
                System.out.println("Information of requested User:");
                System.out.println();
                JsonWriter w = new JsonWriter(new OutputStreamWriter(System.out,"UTF-8"));
                w.setIndent("  ");
                w.beginObject(); // {
                w.name("Username").value(newUserObject.getUserName());
                w.name("Email").value(newUserObject.getEmail());
                w.name("Admin").value(newUserObject.getAdmin());
                w.name("RequestsPerDayQuota").value(newUserObject.getRequestsPerDayQuota());
                w.endObject(); // }
                w.flush();
                System.out.println();
                return 0;
            }
            else if (dataSet != null) {
                // Import Data into DataBase
                System.out.println("Not implemented yet");
            }
            return 0;
        } 
        
        catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
    }*/
}