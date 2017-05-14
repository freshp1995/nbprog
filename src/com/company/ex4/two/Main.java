package com.company.ex4.two;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by patricklanzinger on 14.05.17.
 */
public class Main {
    public static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) {

        int loops = 10;
        for (int i = 0; i < loops; i++)
            new Thread(Main::sendGet).start();

    }


    public static void sendGet() {
        try {

            Random rnd = new Random();
            String number = Integer.toString(rnd.nextInt());
            String url = "http://localhost:8080/test?number=" + number;

            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("User-Agent", USER_AGENT);


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString() + " : " + number);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
