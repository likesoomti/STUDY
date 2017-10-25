package com.onthecoffee.hanplace.UTIL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by BAEJJI on 2017-01-17.
 */

public class ProgramPresenter {

    public String getJSON(String url, String query) {

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.connect();

            if (query != null) {
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(query);
                wr.flush();
                conn.connect();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            reader.close();
            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /*private static final String WWW_FORM = "application/x-www-form-urlencoded";
    private int httpStatusCode;
    private String body;

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getBody() {
        return body;
    }

    private Builder builder;

    private void setBuilder(Builder builder) {
        this.builder = builder;
    }

    public void request() throws ProtocolException {
        HttpURLConnection conn = getConnection();
        setHeader(conn);
        setBody(conn);
        httpStatusCode = getStatusCode(conn);
        body = readStream(conn);
        conn.disconnect();
    }

    private HttpURLConnection getConnection() {
        try {
            URL url = new URL(builder.getUrl());
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setHeader(HttpURLConnection connection) throws ProtocolException {
        setContentType(connection);
        setRequestMethod(connection);
        connection.setConnectTimeout(5000);
        connection.setDoOutput(true);
        connection.setDoInput(true);
    }

    private void setContentType(HttpURLConnection connection) {
        connection.setRequestProperty("Content-Type", WWW_FORM);
    }

    private void setRequestMethod(HttpURLConnection connection) throws ProtocolException {
        try {
            connection.setRequestMethod(builder.getMethod());
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
    }

    private void setBody(HttpURLConnection connection) {
        String parameter = builder.getParameters();
        if (parameter != null && parameter.length() > 0) {
            OutputStream outputStream = null;
            try {
                outputStream = connection.getOutputStream();
                outputStream.write(parameter.getBytes("UTF-8"));
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (outputStream != null) outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int getStatusCode(HttpURLConnection connection) {
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -10;
    }

    private String readStream(HttpURLConnection connection) {
        String result = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
            }
        }
        return result;
    }

    public static class Builder {
        private Map parameters;
        private String method;
        private String url;

        public String getMethod() {
            return method;
        }

        public String getUrl() {
            return url;
        }

        public Builder(String method, String url) {
            if (method == null) {
                method = "GET";
            }
            this.method = method;
            this.url = url;
            this.parameters = new HashMap();
        }

        public void addOrReplace(String key, String value) {
            this.parameters.put(key, value);
        }

        public String getParameters() {
            return generateParameters();
        }

        public String getParameter(String key) {
            return (String) this.parameters.get(key);
        }

        private String generateParameters() {
            StringBuffer parameters = new StringBuffer();
            Iterator keys = getKeys();
            String key = "";
            while (keys.hasNext()) {
                key = (String) keys.next();
                parameters.append(String.format("%s=%s", key, this.parameters.get(key)));
                parameters.append("&");
            }
            String params = parameters.toString();
            if (params.length() > 0) {
                params = params.substring(0, params.length() - 1);
            }
            return params;
        }

        private Iterator getKeys() {
            return this.parameters.keySet().iterator();
        }

        public ProgramPresenter create() {
            ProgramPresenter client = new ProgramPresenter();
            client.setBuilder(this);
            return client;
        }
    }*/

}
