package gr.ntua.ece.softeng19b.cli;

import gr.ntua.ece.softeng19b.client.RestAPI;

import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(
    name="Login"
)
public class Login extends BasicCliArgs implements Callable<Integer> {

    @Option(
        names = "--username",
        required = true,
        description = "the username of the user trying to login."
    )
    protected String userName;
    
    @Option(
        names = "--passw",
        required = true,
        description = "the password of the user trying to login.",
        interactive = true
    )
    protected String password;
       
    @Override
    public Integer call() throws Exception {
        CommandLine cli = spec.commandLine();

        if (usageHelpRequested) {
            cli.usage(cli.getOut());
            return 0;
        }


        try {
            RestAPI restAPI = new RestAPI();
            restAPI.login(userName, password);
            return 0;
        } 
        
        catch (RuntimeException e) {
            cli.getOut().println(e.getMessage());
            e.printStackTrace(cli.getOut());
            return -1;
        }
    }
}