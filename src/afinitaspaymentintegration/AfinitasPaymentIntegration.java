/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afinitaspaymentintegration;

import com.alodiga.afinitas.json.charge.object.ChargeResponse;
import com.alodiga.afinitas.json.token.object.TokenResponse;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ltoro
 */
public class AfinitasPaymentIntegration {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {

        AfinitasPaymentIntegration afinitasPaymentIntegration = new AfinitasPaymentIntegration();

        

    }

    public TokenResponse afinitasToken() throws IOException, Exception {
        try {
            String response = null;
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("grant_type", "password");
            params.put("username", "jcalderas@alodiga.com");
            params.put("password", "554fbd0a9daa0bdb61ba503e97e1dd7a388db0ffd335b50314f5f71c24b9fc29");

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (postData.length() != 0) {
                    postData.append('&');
                }
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            String wsEndPoint = "http://3.231.199.162:9000/oauth/token";
            URL url = new URL(wsEndPoint);
            URLConnection connection = url.openConnection();
            HttpURLConnection conn = (HttpURLConnection) connection;
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));

            /* BASIC AUTH START */
            String username = "blumon_pay_ecommerce_api";
            String password = "blumon_pay_ecommerce_api_password";
            String userpass = username + ":" + password;
            String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
            conn.setRequestProperty("Authorization", basicAuth);
            /* BASIC AUTH END */
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response = inputLine;
                // System.out.println("response:"+ response);
            }

            TokenResponse tokenResponse = new Gson().fromJson(response, TokenResponse.class);

            //TokenResponse tokenResponse = new Gson().fromJson(in.toString(), TokenResponse.class);
            //System.out.println(tokenResponse);

            in.close();
            return tokenResponse;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        }

    }
    // TODO code application logic here

    public ChargeResponse afinitasCharge(String amount, String currency,String cardNumber,String expirationYear,String expirationMonth,String cvv, String cardHolderName) throws IOException, Exception {
        HttpURLConnection connection = null;
        InputStream is = null;
        String response = null;
        AfinitasPaymentIntegration afinitasPaymentIntegration = new AfinitasPaymentIntegration();
        try {
            // set up URL connection
            java.net.URL url = new java.net.URL("http://3.231.199.162:9005/ecommerce/charge");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            TokenResponse accesToken = afinitasPaymentIntegration.afinitasToken();
            
            /* BASIC AUTH START */
            connection.setRequestProperty("Authorization", "Bearer " + accesToken.getAccess_token());
            // write out form parameters
            String postParamaters = "{\n"
                    + "    \"amount\": "+amount+",\n"
                    + "    \"currency\": \""+currency+"\",\n"
                    + "    \"noPresentCardData\": {\n"
                    + "        \"cardNumber\": \""+cardNumber+"\",\n"
                    + "        \"expirationYear\": \""+expirationYear+"\",\n"
                    + "        \"expirationMonth\": \""+expirationMonth+"\",\n"
                    + "        \"cvv\": \""+cvv+"\",\n"
                    + "        \"cardholderName\": \""+cardHolderName+"\"\n"
                    + "    }\n"
                    + "}";
            //System.out.println(postParamaters);
            connection.setFixedLengthStreamingMode(postParamaters.getBytes().length);
            PrintWriter out = new PrintWriter(connection.getOutputStream());
            out.print(postParamaters);
            out.close();
            //Get Response  
            try {
                is = connection.getInputStream();
            } catch (IOException ioe) {
                if (connection instanceof HttpURLConnection) {
                    HttpURLConnection httpConn = (HttpURLConnection) connection;
                    int statusCode = httpConn.getResponseCode();
                    System.out.println(httpConn.getResponseCode());
                    System.out.println(statusCode);
                    if (statusCode != 200) {
                        is = httpConn.getErrorStream();
                    }
                }
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response = inputLine;
                System.out.println("response:" + response);
            }
            ChargeResponse chargeResponse = new Gson().fromJson(response, ChargeResponse.class);
            System.out.println("chargeResponse" + chargeResponse);
            in.close();
            return chargeResponse;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IOException();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
