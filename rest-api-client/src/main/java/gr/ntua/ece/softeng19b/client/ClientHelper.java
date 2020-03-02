package gr.ntua.ece.softeng19b.client;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import gr.ntua.ece.softeng19b.data.model.User;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class ClientHelper {

    private static String parseJsonAndGetValueOfField(Reader r, String field) {
        Gson gson = new Gson();
        Map map = gson.fromJson(r, Map.class);
        return (String) map.get(field);
    }

    static String parseJsonStatus(Reader r) {
        return parseJsonAndGetValueOfField(r, "status");
    }

    static String parseJsonToken(Reader r) {
        return parseJsonAndGetValueOfField(r, "token");
    }

    static User parseJsonUser(Reader r) {
        try {
            JsonReader jsonReader = new JsonReader(r);
            User user = new User();
            jsonReader.beginObject();
            while(jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                switch (name) {
                    case "Username":
                        user.setUserName(jsonReader.nextString());
                        break;
                    case "Email":
                        user.setEmail(jsonReader.nextString());
                        break;
                    case "Admin":
                        user.setAdmin(jsonReader.nextInt());
                        break;
                    case "RequestsPerDayQuota":
                        user.setRequestsPerDayQuota(jsonReader.nextInt());
                        break;
                    case "UsedPerDayQuota":
                        user.setUsedPerDayQuota(jsonReader.nextInt());
                        break;
                    default:
                        jsonReader.skipValue();
                        break;
                }
            }
            jsonReader.endObject();
            return user;    
        }catch(IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static ImportResult parseJsonImportResult(Reader r) {
        try {
            JsonReader jsonReader = new JsonReader(r);
            ImportResult importResult = new ImportResult();
            jsonReader.beginObject();
            while(jsonReader.hasNext()) {
                String name = jsonReader.nextName();
                switch (name) {
                    case "TotalRecordsInFile":
                        importResult.setTotalRecordsInFile(jsonReader.nextLong());
                        break;
                    case "TotalRecordsImported":
                        importResult.setTotalRecordsImported(jsonReader.nextLong());
                        break;
                    case "TotalRecordsInDatabase":
                        importResult.setTotalRecordsInDatabase(jsonReader.nextLong());
                        break;
                    default:
                        jsonReader.skipValue();
                        break;
                }
            }
            jsonReader.endObject();
            return importResult;    
        }catch(IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static String readContents(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).
                lines().collect(Collectors.joining("\n"));
    }

    public static String encode(Map<String, String> data) {
        var builder = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }
        return builder.toString();
    }
}
