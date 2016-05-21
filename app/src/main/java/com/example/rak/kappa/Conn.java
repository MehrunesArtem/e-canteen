package com.example.rak.kappa;

import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by artem on 21.05.16.
 */
public class Conn {
    public static String getResponce(JSONObject input, String url) throws MalformedURLException {
        URL url1 = new URL(url);
        String response = "";

        try {
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            byte[] postDataBytes = input.toString().getBytes("UTF-8");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.getOutputStream().write(postDataBytes);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                response= response.concat(line);
            }
            br.close();




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
