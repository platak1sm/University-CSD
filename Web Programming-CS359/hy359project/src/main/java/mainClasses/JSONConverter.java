/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainClasses;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author mountant
 */
public class JSONConverter {

    public String getJSONFromAjax(BufferedReader reader) throws IOException {
        StringBuilder buffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        return data;
    }

    public Person jsonToPerson(BufferedReader json) {
        Gson gson = new Gson();
        Person msg = gson.fromJson(json, Person.class);
        return msg;
    }

    public String JavaObjectToJSONRemoveElements(Person p, String removeProp) {
        // Creating a Gson Object
        Gson gson = new Gson();
        String json = gson.toJson(p, Person.class);
        JsonObject object = (JsonObject) gson.toJsonTree(p);
        object.remove(removeProp);
        return object.toString();
    }

    public String personToJSON(Person per) {
        Gson gson = new Gson();

        String json = gson.toJson(per, Person.class);
        return json;
    }

}
