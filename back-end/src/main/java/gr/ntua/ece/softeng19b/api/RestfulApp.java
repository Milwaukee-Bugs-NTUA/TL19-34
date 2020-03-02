package gr.ntua.ece.softeng19b.api;

import gr.ntua.ece.softeng19b.api.resource.*;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.Method;
import org.restlet.engine.application.CorsFilter;
import org.restlet.routing.Router;

import java.util.Set;

public class RestfulApp extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {

        Router router = new Router(getContext());

        //Perform a heath check
        router.attach("/HealthCheck", HealthCheck.class);
        //Init a new (empty) database with the default Admin user (username: admin, password: 321nimda)
        router.attach("/Reset", Reset.class);

        //Authenticate the user
        router.attach("/Login", Login.class);

        //You should make these REST endpoints available to Admin users only (Users with the Admin role)
        router.attach("/Admin/users", AddUser.class);
        router.attach("/Admin/users/{username}", UserManager.class);
        router.attach("/Admin/ActualTotalLoad", ImportActualTotalLoadDataSet.class);
        router.attach("/Admin/DayAheadTotalLoadForecast", ImportDayAheadTotalLoadForecastDataSet.class);
        router.attach("/Admin/AggregatedGenerationPerType", ImportAggregatedGenerationPerTypeDataSet.class);

        //You should make these REST endpoints available to all authenticated users
        router.attach("/Logout", Logout.class);
        /*
         All routes accept a `format` query param (optional, either json or csv, default is json)
         All year, month and date attributes in routes are in the ISO-8601 format
           (YYYY, YYYY-MM, YYYY-MM-DD respectively)
         */
        router.attach(
            "/ActualTotalLoad/{AreaName}/{Resolution}/date/{date}",
            ActualTotalLoadForSpecificDate.class
        );
        //Add more datasets
        router.attach(
            "/ActualTotalLoad/{AreaName}/{Resolution}/month/{month}",
            ActualTotalLoadForSpecificMonth.class
        );
        router.attach(
            "/DayAheadTotalLoadForecast/{AreaName}/{Resolution}/date/{date}",
            DayAheadTotalLoadForecastForSpecificDate.class
        );
        router.attach(
            "/DayAheadTotalLoadForecast/{AreaName}/{Resolution}/month/{month}",
            DayAheadTotalLoadForecastForSpecificMonth.class
        );
        router.attach(
            "/DayAheadTotalLoadForecast/{AreaName}/{Resolution}/year/{year}",
            DayAheadTotalLoadForecastForSpecificYear.class
        );
        router.attach(
            "/ActualvsForecast/{AreaName}/{Resolution}/date/{date}",
            ActualvsForecastForSpecificDate.class
        );
        router.attach(
            "/ActualvsForecast/{AreaName}/{Resolution}/month/{month}",
            ActualvsForecastForSpecificMonth.class
        );
        router.attach(
            "/ActualvsForecast/{AreaName}/{Resolution}/year/{year}",
            ActualvsForecastForSpecificYear.class
        );
        router.attach(
            "/AggregatedGenerationPerType/{AreaName}/{ProductionType}/{Resolution}/date/{date}",
            AggregatedGenerationPerTypeForSpecificDate.class
        );
        router.attach(
            "/AggregatedGenerationPerType/{AreaName}/{ProductionType}/{Resolution}/month/{month}",
            AggregatedGenerationPerTypeForSpecificMonth.class
        );
        router.attach(
            "/AggregatedGenerationPerType/{AreaName}/{ProductionType}/{Resolution}/year/{year}",
            AggregatedGenerationPerTypeForSpecificYear.class
        );

        router.attach(
            "/ActualTotalLoad/{AreaName}/{Resolution}/year/{year}",
            ActualTotalLoadForSpecificYear.class
        );

        //Enable CORS for all origins (don't use this in a production service)
        CorsFilter corsFilter = new CorsFilter(getContext(), router);
        corsFilter.setAllowedOrigins(Set.of("*"));
        corsFilter.setAllowedCredentials(true);
        corsFilter.setAllowedHeaders(Set.of("X-OBSERVATORY-AUTH"));
        corsFilter.setDefaultAllowedMethods(Set.of(Method.GET, Method.PUT, Method.POST, Method.DELETE));
        corsFilter.setAllowingAllRequestedHeaders(true);
        corsFilter.setSkippingResourceForCorsOptions(true);
        corsFilter.setMaxAge(10);
        return corsFilter;
    }


}
