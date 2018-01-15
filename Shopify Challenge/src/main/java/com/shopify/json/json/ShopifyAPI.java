package com.shopify.json.json;

public class ShopifyAPI {

    private Menus[] menus;

    private Pagination pagination;

    public Menus[] getMenus() {
        return menus;
    }

    public void setMenus(Menus[] menus) {
        this.menus = menus;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public int getMenuID(int index) {
        return menus[index].getId();
    }

    public int[] getMenuChildID(int index) {
        return menus[index].getChild_ids();
    }

    public int getMenuSize() {
        return menus.length;
    }

    @Override
    public String toString() {
        return "ShopifyAPI [menus = " + menus + ", pagination = " + pagination + "]";
    }

}
