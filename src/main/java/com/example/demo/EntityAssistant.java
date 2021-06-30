package com.example.demo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TimerTask;

public class EntityAssistant extends TimerTask {
    private static String readAll(BufferedReader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public JSONObject getAllCharacters() throws IOException {
        URL url = new URL("https://rickandmortyapi.com/api/character");
        InputStream inputStream = url.openStream();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String text = readAll(bufferedReader);
            JSONObject jsonObject = new JSONObject(text);
            System.out.println(jsonObject);
            return jsonObject;
        } finally {
            inputStream.close();
        }
    }

    @Override
    public void run() {
        try {
            getAllCharacters();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
