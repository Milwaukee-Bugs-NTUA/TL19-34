package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;

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
        DayAheadTotalLoadForecast,
        ActualVSForecastedTotalLoad
    }

    @ArgGroup(exclusive = true, multiplicity = "1")
    List<BasicAdminArguments> basicAdminArguments;
    
    static class BasicAdminArguments {
        @ArgGroup(exclusive = false, multiplicity = "1")
        List<UserArgumentGroup> userArgumentGroup;

        @ArgGroup(heading = "%nFetch specified user's information from DataBase%n")
        UserStatusArgumentGroup userStatusArgumentGroup;

        @ArgGroup(exclusive = false, multiplicity = "1", heading = "%nOptions for importing data into database%n")
        NewDataArgumentGroup newDataArgumentGroup;
    }

    static class UserArgumentGroup {
        @ArgGroup(exclusive = true, multiplicity = "1", heading = "%nArguments for modifying existing user or adding new user:%n")
        List<ActionOnUserArgumentGroup> actionOnUserArgumentGroup;

        @ArgGroup(exclusive = false, multiplicity = "1", heading = "    User's Information Arguments:%n")
        UserInfo userInfo;
    }

    static class ActionOnUserArgumentGroup {
        @Option(
            names = "--newuser",
            required = true,
            description = "username of the new user"
        ) 
        protected String newUser;

        @Option(
            names = "--moduser", 
            required = true,
            description = "username of the user to be modified"
        ) 
        protected String oldUser;
    }

    static class UserStatusArgumentGroup {
        @Option(
            names = "--userstatus", 
            required = true,
            description = "username of the user, whose data will be fetched"
        ) 
        protected String user;
    }

    static class NewDataArgumentGroup {
        @Option(
            names = "--newdata", 
            required = true,
            description = "dataset in which data will be import (ActualTotalLoad, DayAheadTotalLoadForecast, AggregatedGenerationPerType, ActualvsForecast)"    
        )
        protected DataSet dataSet;

        @Option(
            names = "--source", 
            required = true,
            description = "file to be imported to database"    
        )
        protected String dataFile;
    }

    static class UserInfo {
        @Option(
            names = "--passw", 
            required = true,
            interactive = true,
            description = "password of specified user"
        ) 
        protected String password;

        @Option(
            names = "--email", 
            required = true,
            description = "email address of specified user"    
        )
        protected String email;

        @Option(
            names = "--quotas", 
            required = true,
            description = "currently available or new user quotas"    
        )
        protected int quotas;
    }

    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }


        try {
            
            return 0;
        } 
        
        catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
    }
}