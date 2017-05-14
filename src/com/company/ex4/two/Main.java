package com.company.ex4.two;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by patricklanzinger on 14.05.17.
 */
public class Main {
    public static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) {

        new Thread(Main::sendGet).start();

    }


    public static void sendGet(){
        try {

            String url = "http://localhost:8080/test?number=123";

            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
