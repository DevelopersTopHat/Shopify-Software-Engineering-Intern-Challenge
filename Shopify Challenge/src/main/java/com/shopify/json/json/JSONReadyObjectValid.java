package com.shopify.json.json;

import java.util.Arrays;
import java.util.List;

public class JSONReadyObjectValid {
    private int root_id;
    private Object[] children;

    public JSONReadyObjectValid(List<Integer> valid_menus) {
        this.root_id = valid_menus.get(0);
        valid_menus.remove(0);
        this.children = valid_menus.toArray();

        //System.out.println(Arrays.toString(children));
    }

    @Override
    public String toString() {
        return "root_id: " + root_id + ", " + "children: " + Arrays.toString(children);
    }

}
