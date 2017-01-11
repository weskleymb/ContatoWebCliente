package br.senac.rn.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class AcessoWS {

    public boolean httpPost(String endereco, String json) {
        try {
            URL url = new URL(endereco);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("content-type", "application/json");
            httpConn.connect();

            OutputStreamWriter output = new OutputStreamWriter(httpConn.getOutputStream());
            output.write(json);
            output.flush();
            output.close();

            httpConn.getInputStream();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String httpGet(String endereco) {
        try {
            URL url = new URL(endereco);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            InputStreamReader streamReader = new InputStreamReader(url.openStream());
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            bufferedReader.close();
            String json = stringBuilder.toString();
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean httpPut(String endereco, String json) {
        try {
            URL url = new URL(endereco);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("PUT");
            httpConn.setRequestProperty("content-type", "application/json");
            httpConn.connect();

            OutputStreamWriter output = new OutputStreamWriter(httpConn.getOutputStream());
            output.write(json);
            output.flush();
            output.close();

            httpConn.getInputStream();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean httpDelete(String endereco) {
        try {
            URL url = new URL(endereco);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("DELETE");
            httpConn.connect();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}