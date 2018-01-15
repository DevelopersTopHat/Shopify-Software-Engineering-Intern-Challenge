package com.shopify.json.json;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONWriter {

    private static List<JSONReadyObjectValid> valid_menus = new ArrayList<JSONReadyObjectValid>();
    private static List<JSONReadyObjectInvalid> invalid_menus = new ArrayList<JSONReadyObjectInvalid>();

    public JSONWriter(List<JSONReadyObjectValid> validMenus, List<JSONReadyObjectInvalid> invalidMenus) {
        for (int i = 0; i < validMenus.size(); i++) {
            valid_menus.add(validMenus.get(i));
        }
        for (int j = 0; j < invalidMenus.size(); j++) {
            invalid_menus.add(invalidMenus.get(j));
        }

        fileWrite();
    }

    private void fileWrite() {
        Gson gson = new Gson();
//        String jsonValid = new Gson().toJson(valid_menus);
//        String jsonInvalid = new Gson().toJson(invalid_menus);

//        System.out.println("valid_menus: " + jsonValid + "\n" + "invalid_menus: " + jsonInvalid);

        JSONObject jsonObject = new JSONObject(valid_menus, invalid_menus);

        try (FileWriter writer = new FileWriter("D:\\Shopify Challenge\\shopifyAPIExtraChallenge.json")) {
            gson.toJson(jsonObject, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class JSONObject {
        private List<JSONReadyObjectValid> valid_menus;
        private List<JSONReadyObjectInvalid> invalid_menus;

        public JSONObject(List<JSONReadyObjectValid> valid_menus, List<JSONReadyObjectInvalid> invalid_menus) {
            this.valid_menus = valid_menus;
            this.invalid_menus = invalid_menus;
        }

        @Override
        public String toString() {
            return "valid_menus: " + valid_menus + ", " + "invalid_menus: " + invalid_menus;
        }
    }

}
