package com.shopify.json.json;

import com.google.gson.Gson;
import com.shopify.json.json.ShopifyAPI;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
The purpose of this class is to aggregate all of the JSON data into a list of JSON objects.
It should accomplish this by first reading the data from the url and then storing the root_id and children.
*/
public class MenuAssembler {

    //Used for GET request to shopify url.
    private static OkHttpClient client = new OkHttpClient();

    //The list holding the menu nodes to be converted to valid and invalid menus.
    private static List<MenuNode> nodeList = new ArrayList<MenuNode>();

    public MenuAssembler(String url) {
        //To hold url of JSON.
        String json = null;

        try {
            json = getJSON(url + 1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        ShopifyAPI shopifyAPI = gson.fromJson(json, ShopifyAPI.class);

        //System.out.println("total_pages: " + shopifyAPI.getPagination().getTotal());

        //Goes through each page.
        for (int index = 1; index < shopifyAPI.getPagination().getTotal(); index++) {
            getMenuData(json, index, gson, shopifyAPI, url);
        }
    }

    //Retrieves JSON from url.
    public static String getJSON(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    //Stores the menu data in menu nodes and adds them to the list.
    public static void getMenuData(String json, int page, Gson gson, ShopifyAPI shopifyAPI, String url) {

        try {
            json = getJSON(url + page);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        shopifyAPI = gson.fromJson(json, ShopifyAPI.class);

        /* Tests to ensure that the correct values are being put into the menu nodes.

        System.out.println("root_id: " + shopifyAPI.getMenus()[index].getId());
        System.out.println("data: " + shopifyAPI.getMenus()[index].getData());
        System.out.println("children: " + shopifyAPI.getMenus()[index].getChild_ids_As_String());
        System.out.println("parent_id: " + shopifyAPI.getMenus()[index].getParent_id());
        System.out.println("size: " + shopifyAPI.getMenuSize());

        */

        //Creates a menu node with the attributes of JSON objects and adds them to the list.
        for (int index = 0; index < shopifyAPI.getMenuSize(); index++) {
            nodeList.add(new MenuNode(shopifyAPI.getMenus()[index].getId(), shopifyAPI.getMenus()[index].getData(), shopifyAPI.getMenus()[index].getChild_ids(), shopifyAPI.getMenus()[index].getParent_id()));
        }
    }

    public List<MenuNode> getNodeList() {
        return nodeList;
    }

}
