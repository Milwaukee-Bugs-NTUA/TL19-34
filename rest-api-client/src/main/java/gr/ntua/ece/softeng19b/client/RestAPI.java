package gr.ntua.ece.softeng19b.client;

import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.ATLRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.DATLFRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AGPTRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificDay;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificMonth;
import gr.ntua.ece.softeng19b.data.model.AVFRecordForSpecificYear;
import gr.ntua.ece.softeng19b.data.model.User;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.math.BigInteger;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File; 
import java.io.IOException;

public class RestAPI {

    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String URL_ENCODED = "application/x-www-form-urlencoded";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    public static final String BASE_URL = "/energy/api";
    public static final String CUSTOM_HEADER = "X-OBSERVATORY-AUTH";

    static {
        System.setProperty("jdk.internal.httpclient.disableHostnameVerification", "true");
    }

    private final String urlPrefix;
    private final HttpClient client;

    private String token = null; //user is not logged in
    private BufferedReader reader;

    public RestAPI() throws RuntimeException {
        this("localhost", 8765);
    }

    public RestAPI(String host, int port) throws RuntimeException {
        try {
            this.client = newHttpClient();
        }
        catch(NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e.getMessage());
        }
        this.urlPrefix = "https://" + host + ":" + port + BASE_URL;
    }

    /* Actual Total Load related url methods */
    //for day
    String urlForActualDataLoad(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualTotalLoad/" + encAreaName + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    //for month
    String urlForActualDataLoad(String areaName, String resolutionCode, YearMonth month, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualTotalLoad/" + encAreaName + "/" + encResCode + "/month/" + month.toString() +
                "?format=" + format.name().toLowerCase();
    }

    //for year
    String urlForActualDataLoad(String areaName, String resolutionCode, Year year, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualTotalLoad/" + encAreaName + "/" + encResCode + "/year/" + year.toString() +
                "?format=" + format.name().toLowerCase();
    }

    /* Day Ahead Total Load Forecast related url methods */
    //for day
    String urlForDayAheadTotalLoadForecast(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/DayAheadTotalLoadForecast/" + encAreaName + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    //for month
    String urlForDayAheadTotalLoadForecast(String areaName, String resolutionCode, YearMonth yearMonth, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/DayAheadTotalLoadForecast/" + encAreaName + "/" + encResCode + "/month/" + yearMonth.toString() +
                "?format=" + format.name().toLowerCase();
    }

    //for year
    String urlForDayAheadTotalLoadForecast(String areaName, String resolutionCode, Year year, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/DayAheadTotalLoadForecast/" + encAreaName + "/" + encResCode + "/year/" + year.toString() +
                "?format=" + format.name().toLowerCase();
    }

    /* Aggregated Generation Per Type related url methods */
    //for day
    String urlForAggregatedGenerationPerType(String areaName, String productionType, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encProdType  = URLEncoder.encode(productionType, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProdType + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    //for month
    String urlForAggregatedGenerationPerType(String areaName, String productionType, String resolutionCode, YearMonth yearMonth, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encProdType  = URLEncoder.encode(productionType, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProdType + "/" + encResCode + "/month/" + yearMonth.toString() +
                "?format=" + format.name().toLowerCase();
    }

    //for year
    String urlForAggregatedGenerationPerType(String areaName, String productionType, String resolutionCode, Year year, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encProdType  = URLEncoder.encode(productionType, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/AggregatedGenerationPerType/" + encAreaName + "/" + encProdType + "/" + encResCode + "/year/" + year.toString() +
                "?format=" + format.name().toLowerCase();
    }

    /* Actual Total Load VS Day Ahead Forecast related url methods */
    //for day
    String urlForActualvsForecast(String areaName, String resolutionCode, LocalDate date, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualvsForecast/" + encAreaName + "/" + encResCode + "/date/" + date.toString() +
                "?format=" + format.name().toLowerCase();
    }

    //for month
    String urlForActualvsForecast(String areaName, String resolutionCode, YearMonth month, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualvsForecast/" + encAreaName + "/" + encResCode + "/month/" + month.toString() +
                "?format=" + format.name().toLowerCase();
    }

    //for year
    String urlForActualvsForecast(String areaName, String resolutionCode, Year year, Format format) {
        String encAreaName = URLEncoder.encode(areaName, StandardCharsets.UTF_8);
        String encResCode  = URLEncoder.encode(resolutionCode, StandardCharsets.UTF_8);
        return urlPrefix + "/ActualvsForecast/" + encAreaName + "/" + encResCode + "/year/" + year.toString() +
                "?format=" + format.name().toLowerCase();
    }

    String urlForHealthCheck() {
        return urlPrefix + "/HealthCheck";
    }

    String urlForReset() { return urlPrefix + "/Reset"; }

    String urlForLogin() { return urlPrefix + "/Login"; }

    String urlForLogout() { return urlPrefix + "/Logout"; }

    String urlForAddUser() { return urlPrefix + "/Admin/users"; }

    String urlForUpdateUser(String username) {
        return urlPrefix + "/Admin/users/" + URLEncoder.encode(username, StandardCharsets.UTF_8);
    }

    String urlForGetUser(String username) {
        return urlForUpdateUser(username);
    }

    String urlForImport(String dataSet) {
        return urlPrefix + "/Admin/" + URLEncoder.encode(dataSet, StandardCharsets.UTF_8);
    }

    private HttpRequest newGetRequest(String url) {
        return newRequest("GET", url, URL_ENCODED, HttpRequest.BodyPublishers.noBody());
    }

    private HttpRequest newPostRequest(String url, String contentType, HttpRequest.BodyPublisher bodyPublisher) {
        return newRequest("POST", url, contentType, bodyPublisher);
    }

    private HttpRequest newPutRequest(String url, String contentType, HttpRequest.BodyPublisher bodyPublisher) {
        return newRequest("PUT", url, contentType, bodyPublisher);
    }

    private HttpRequest newRequest(String method,
                                   String url,
                                   String contentType,
                                   HttpRequest.BodyPublisher bodyPublisher) {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
                                                                  
        try {
            reader = new BufferedReader(new FileReader(System.getProperty("user.home")+"/softeng19bAPI.token"));
            token = reader.readLine();
            reader.close();
        } 
        catch (IOException e) {
              //e.printStackTrace();
        }   
        
        if (token != null) {
            builder.header(CUSTOM_HEADER, token);
        }
        return builder.
                method(method, bodyPublisher).
                header(CONTENT_TYPE_HEADER, contentType).
                uri(URI.create(url)).
                build();
    }

    private <T> T sendRequestAndParseResponseBodyAsUTF8Text(Supplier<HttpRequest> requestSupplier,
                                                            Function<Reader, T> bodyProcessor) {
        HttpRequest request = requestSupplier.get();
        try {
            System.out.println("Sending "+request.method()+" to "+request.uri());
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                try {
                    if (bodyProcessor != null) {
                        return bodyProcessor.apply(new InputStreamReader(response.body(), StandardCharsets.UTF_8));
                    }
                    else {
                        return null;
                    }
                }
                catch(Exception e) {
                    throw new ResponseProcessingException(e.getMessage(), e);
                }
            }
            else {
                throw new ServerResponseException(statusCode, ClientHelper.readContents(response.body()));
            }
        }
        catch(IOException | InterruptedException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }

    public boolean isLoggedIn() {
        return token != null;
    }

    public String healthCheck() {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForHealthCheck()),
            ClientHelper::parseJsonStatus
        );
    }

    public String resetDatabase() {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForReset(), URL_ENCODED, HttpRequest.BodyPublishers.noBody()),
            ClientHelper::parseJsonStatus
        );
    }

    public void login(String username, String password) {
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("username", username);
        formData.put("password", password);
        token = sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForLogin(), URL_ENCODED, ofUrlEncodedFormData(formData)),
            ClientHelper::parseJsonToken
        );
        try {
            File myObj = new File(System.getProperty("user.home")+"/softeng19bAPI.token");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
              try {
                FileWriter myWriter = new FileWriter(System.getProperty("user.home")+"/softeng19bAPI.token");
                myWriter.write(token);
                myWriter.close();
                System.out.println("User succesfully logged in!");
              } 
              catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
            } 
            else {
              System.out.println("User already logged in!");
            }
        } 
          catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
    }

    public void logout() {
        sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForLogout(), URL_ENCODED, HttpRequest.BodyPublishers.noBody()),
            null
        );
        token = null;
        File myObj = new File(System.getProperty("user.home")+"/softeng19bAPI.token");
        if (myObj.delete()) {
            System.out.println("User succesfully logged out!");
        }
        else{
            System.out.println("An error occured during logout proccess! Please try again!");
        }

    }

    public User addUser(String username, String email, String password, int quota) {
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("username", username);
        formData.put("email", email);
        formData.put("password", password);
        formData.put("requestsPerDayQuota", String.valueOf(quota));
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForAddUser(), URL_ENCODED, ofUrlEncodedFormData(formData)),
            ClientHelper::parseJsonUser
        );
    }

    public User updateUser(User updatedUser) {
        //only email and/or quota can be updated
        Map<String, String> formData = new LinkedHashMap<>();
        formData.put("email", updatedUser.getEmail());
        formData.put("requestsPerDayQuota", String.valueOf(updatedUser.getRequestsPerDayQuota()));
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPutRequest(urlForUpdateUser(updatedUser.getUserName()), URL_ENCODED, ofUrlEncodedFormData(formData)),
            ClientHelper::parseJsonUser
        );
    }

    public User getUser(String username) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForGetUser(username)),
            ClientHelper::parseJsonUser
        );
    }

    public ImportResult importFile(String dataSet, Path dataFilePath) throws IOException {
        String boundary = new BigInteger(256, new Random()).toString();
        Map<String, Object> formData = Map.of("file", dataFilePath);
        HttpRequest.BodyPublisher bodyPublisher = ofMultipartFormData(formData, boundary);
        String contentType = MULTIPART_FORM_DATA + ";boundary=" + boundary;
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newPostRequest(urlForImport(dataSet), contentType, bodyPublisher),
            ClientHelper::parseJsonImportResult
        );
    }


    /* Actual Total Load related get methods */
    //for date
    public List<ATLRecordForSpecificDay> getActualTotalLoad(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualDataLoad(areaName, resolutionCode, date, format)),
            format::consumeActualTotalLoadRecordsForSpecificDay
        );
    }

    //for month
    public List<ATLRecordForSpecificMonth> getActualTotalLoad(String areaName,
                                                            String resolutionCode,
                                                            YearMonth yearMonth,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualDataLoad(areaName, resolutionCode, yearMonth, format)),
            format::consumeActualTotalLoadRecordsForSpecificMonth
        );
    }

    //for year
    public List<ATLRecordForSpecificYear> getActualTotalLoad(String areaName,
                                                            String resolutionCode,
                                                            Year year,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualDataLoad(areaName, resolutionCode, year, format)), 
            format::consumeActualTotalLoadRecordsForSpecificYear
        );
    }

    /* Day Ahead Total Load Forecast related get methods */
    //for day
    public List<DATLFRecordForSpecificDay> getDayAheadTotalLoadForecast(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForDayAheadTotalLoadForecast(areaName, resolutionCode, date, format)),
            format::consumeDayAheadTotalLoadForecastRecordsForSpecificDay
        );
    }

    //for month
    public List<DATLFRecordForSpecificMonth> getDayAheadTotalLoadForecast(String areaName,
                                                            String resolutionCode,
                                                            YearMonth yearMonth,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForDayAheadTotalLoadForecast(areaName, resolutionCode, yearMonth, format)),
            format::consumeDayAheadTotalLoadForecastRecordsForSpecificMonth
        );
    }

    //for year
    public List<DATLFRecordForSpecificYear> getDayAheadTotalLoadForecast(String areaName,
                                                            String resolutionCode,
                                                            Year year,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForDayAheadTotalLoadForecast(areaName, resolutionCode, year, format)),
            format::consumeDayAheadTotalLoadForecastRecordsForSpecificYear
        );
    }

    /* Aggregated Generation Per Type related get methods */
    //for day
    public List<AGPTRecordForSpecificDay> getAggregatedGenerationPerType(String areaName,
                                                            String productionType,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForAggregatedGenerationPerType(areaName, productionType, resolutionCode, date, format)),
            format::consumeAggregatedGenerationPerTypeRecordsForSpecificDay
        );
    }

    //for month
    public List<AGPTRecordForSpecificMonth> getAggregatedGenerationPerType(String areaName,
                                                            String productionType,
                                                            String resolutionCode,
                                                            YearMonth yearMonth,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForAggregatedGenerationPerType(areaName, productionType, resolutionCode, yearMonth, format)),
            format::consumeAggregatedGenerationPerTypeRecordsForSpecificMonth
        );
    }

    //for year
    public List<AGPTRecordForSpecificYear> getAggregatedGenerationPerType(String areaName,
                                                            String productionType,
                                                            String resolutionCode,
                                                            Year year,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForAggregatedGenerationPerType(areaName, productionType, resolutionCode, year, format)),
            format::consumeAggregatedGenerationPerTypeRecordsForSpecificYear
        );
    }

    /* Actual Total Load VS Day Ahead Forecast related get methods */
    //for date
    public List<AVFRecordForSpecificDay> getActualvsForecast(String areaName,
                                                            String resolutionCode,
                                                            LocalDate date,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualvsForecast(areaName, resolutionCode, date, format)),
            format::consumeActualvsForecastRecordsForSpecificDay
        );
    }

    //for month
    public List<AVFRecordForSpecificMonth> getActualvsForecast(String areaName,
                                                            String resolutionCode,
                                                            YearMonth yearMonth,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualvsForecast(areaName, resolutionCode, yearMonth, format)),
            format::consumeActualvsForecastRecordsForSpecificMonth
        );
    }

    //for year
    public List<AVFRecordForSpecificYear> getActualvsForecast(String areaName,
                                                            String resolutionCode,
                                                            Year year,
                                                            Format format) {
        return sendRequestAndParseResponseBodyAsUTF8Text(
            () -> newGetRequest(urlForActualvsForecast(areaName, resolutionCode, year, format)), 
            format::consumeActualvsForecastRecordsForSpecificYear
        );
    }

    //Helper method to create a new http client that can tolerate self-signed or improper ssl certificates
    private static HttpClient newHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        return HttpClient.newBuilder().sslContext(sslContext).build();
    }

    private static TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) { }
        }
    };

    private static HttpRequest.BodyPublisher ofUrlEncodedFormData(Map<String, String> data) {
        return HttpRequest.BodyPublishers.ofString(ClientHelper.encode(data));
    }

    private static HttpRequest.BodyPublisher ofMultipartFormData(Map<String, Object> data, String boundary)
            throws IOException {
        var byteArrays = new ArrayList<byte[]>();
        String separator = "--" + boundary + "\r\nContent-Disposition: form-data; name=";
        byte[] separatorBytes = separator.getBytes(StandardCharsets.UTF_8);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            byteArrays.add(separatorBytes);

            if (entry.getValue() instanceof Path) {
                var path = (Path) entry.getValue();
                String mimeType = Files.probeContentType(path);
                byteArrays.add(("\"" + entry.getKey() + "\"; filename=\"" + path.getFileName()
                        + "\"\r\nContent-Type: " + mimeType + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                byteArrays.add(Base64.getMimeEncoder().encode(Files.readAllBytes(path)));
                byteArrays.add("\r\n".getBytes(StandardCharsets.UTF_8));
            } else {
                byteArrays.add(("\"" + entry.getKey() + "\"\r\n\r\n" + entry.getValue() + "\r\n")
                        .getBytes(StandardCharsets.UTF_8));
            }
        }
        byteArrays.add(("--" + boundary + "--").getBytes(StandardCharsets.UTF_8));
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

}
