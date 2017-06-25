/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Batuhan
 */
public class JsonWorker {

    /**
     * Returns JSON as text from given urlString
     *
     * @param urlString (String)
     */
    private static String getJson(String urlString) throws Exception, IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            try {
                while ((read = reader.read(chars)) != -1) {
                    buffer.append(chars, 0, read);
                }
            } catch (IOException ex) {
                Logger.getLogger(JsonWorker.class.getName()).log(Level.SEVERE, null, ex);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    // Modify part will be added.
    private void instantiateClasswithJson(String json, Class c) throws InstantiationException, IllegalAccessException {
        /*String json = "{\"city\":\"Jos\",\"country\":\"Nigeria\",\"houseNumber\":\"13\",\"lga\":\"Jos South\",\n"
                + "\"state\":\"Plateau\",\"streetName\":\"Jonah Jann\",\"village\":\"Bukuru\",\"ward\":\"1\"}";*/
        Object object = null;
        try {
            object = c.newInstance();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JsonWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        Gson gson = new GsonBuilder().create();
        object = gson.fromJson(json, object.getClass());
    }

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            getJson("http://www.javascriptkit.com/dhtmltutors/javascriptkit.json");
        } catch (Exception ex) {
            Logger.getLogger(JsonWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
