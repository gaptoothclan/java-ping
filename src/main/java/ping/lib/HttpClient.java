package ping.lib;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpClient {

    private final String USER_AGENT = "cb-ping/1.0";

    private String url;
    private Map<String, String> params;

    public HttpClient setUrl(String url){
        this.url = url;
        return this;
    }

    public HttpClient setParams(Map<String, String> params){
        this.params = params;
        return this;
    }

    public String get() throws Exception {

        url = url + "?" + createQueryString(params, true);
        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream())
        );

        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }

        in.close();

        return response.toString();

    }

    public String post() throws Exception {

        URL urlObj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParams = createQueryString(params, false);

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParams);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }

        return response.toString();


    }

    public String createQueryString(Map<String, String> params, boolean urlEncode) throws UnsupportedEncodingException {

        List<String> paramPairs = new ArrayList<String>();

        //params.forEach((k,v) -> );

        for (Map.Entry<String, String> entry : params.entrySet())
        {

            String key = entry.getKey();
            String value = entry.getValue();

            if (urlEncode) {
                key = URLEncoder.encode(key, "UTF-8");
                value = URLEncoder.encode(value, "UTF-8");
            }

            paramPairs.add(key + "=" + value);
        }

        return String.join("&", paramPairs);
    }
}
