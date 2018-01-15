package com.shopify.json.json;

public class Pagination {

    private int total;

    private String per_page;

    private int current_page;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    @Override
    public String toString() {
        return "ShopifyAPI [total = " + total + ", per_page = " + per_page + ", current_page = " + current_page + "]";
    }
}


