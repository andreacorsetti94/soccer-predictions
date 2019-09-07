package com.acorsetti.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UnirestCalls {

    public static void tryConnection(String endpoint){
        HttpResponse<JsonNode> response = UnirestCalls.get(endpoint, null);

        try{
            JSONObject myObj = response.getBody().getObject();
            System.out.println("JSON: " + myObj.toString());
        }
        catch (NullPointerException e){
            System.out.println(e.toString());
        }


    }

    public static HttpResponse<JsonNode> get(String unirestGet, Map<String,String> headers){

        if(headers == null){
            headers = new HashMap<>();
        }

        headers.put("X-RapidAPI-Key", "a138c90d09msh409fe62763f10e1p181e71jsn6b5940cf451f");

        HttpResponse<JsonNode> response;

        System.out.println("API CALL: " + unirestGet);

        try{
            response = Unirest.get(unirestGet)
                    .headers(headers)
                    .asJson();
            return response;
        }
        catch(UnirestException ex){
            System.out.println("Unirest get FAILED. Unirest get String: " + ex.toString());
            return null;
        }
    }
}
