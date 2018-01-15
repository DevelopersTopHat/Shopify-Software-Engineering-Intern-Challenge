package com.shopify.json.json;

import java.util.Arrays;

public class Menus {

    private int id;

    private int[] child_ids;

    private String data;

    private int parent_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getChild_ids() {
        return child_ids;
    }

    public String getChild_ids_As_String() {
        return Arrays.toString(child_ids);
    }

    public void setChild_ids(int[] child_ids) {
        this.child_ids = child_ids;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getParent_id ()
    {
        return parent_id;
    }

    public void setParent_id (int parent_id)
    {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "ShopifyAPI [id = " + id + ", child_ids = " + child_ids + ", data = " + data + "]";
    }


}
