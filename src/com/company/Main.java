package com.company;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class Main {

    public static void main(String[] args) {

        try {

            String source = new String(readAllBytes(get("source.json")));
            System.out.println("Input: " + source);

            JSONObject json = new JSONObject(source);

            FileInputStream x = new FileInputStream("bla.properties");
            Properties props = new Properties();
            props.load(x);
            x.close();
            for (Map.Entry<Object, Object> entry : props.entrySet()) {

                System.out.println("Löschen: " + entry.getKey());

                String[] parts = ((String) entry.getKey()).split("\\.");

                JSONObject current = json;
                JSONObject parent = json;

                for (String i : parts) {

                    if (current.has(i)) {
                        parent = current;
                        //System.out.println(i);
                        try {
                            current = current.getJSONObject(i);
                        } catch (Exception e) {
                            parent.remove(i);
                        }
                    }

                }

            }

            System.out.println("Output: " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
