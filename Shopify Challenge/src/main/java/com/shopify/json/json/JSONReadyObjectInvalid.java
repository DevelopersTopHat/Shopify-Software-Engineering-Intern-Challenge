package com.shopify.json.json;

import java.util.Arrays;
import java.util.List;

public class JSONReadyObjectInvalid {
    private int root_id;
    private Object[] children;

    public JSONReadyObjectInvalid(List<Integer> invalid_menus) {
        this.root_id = invalid_menus.get(0);
        this.children = invalid_menus.toArray();

        //System.out.println(Arrays.toString(children));
    }

    @Override
    public String toString() {
        return "root_id: " + root_id + ", " + " children: " + Arrays.toString(children);
    }
}
