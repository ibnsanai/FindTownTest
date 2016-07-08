package com.findtown.cities;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

class GetJsonThread extends Thread {

    private List<City> newList = new ArrayList<>();

    synchronized List<City> getNewList() {
        return newList;
    }

    @Override
    public void run() {
        try {
            int att = 1;
            System.out.println("get json from web");

            boolean okGetJson = false;

            while (!okGetJson && att < 11) {
                System.out.println(" ------- attempt " + att++ );
                try {
                    JSONArray jsonArray = readJsonFromUrl("http://119.9.109.76:8080/known_cities");

                    System.out.println("parse json to list");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONArray jsArr = jsonArray.getJSONObject(i).getJSONArray("providers");
                        String[] strArr = new String[jsArr.length()];
                        for (int j = 0; j < jsArr.length(); j++) {
                            strArr[j] = jsArr.getString(j);
                        }
                        newList.add(new City(jsonArray.getJSONObject(i).getString("province"),
                                strArr,
                                jsonArray.getJSONObject(i).getString("city_name"),
                                jsonArray.getJSONObject(i).getString("city_id")));
                    }
                    System.out.println("json is made");
                    if (newList.size()>0) okGetJson = true;

                } catch(Exception e){
                    sleep(30000);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONArray(jsonText);
        }
    }
}
