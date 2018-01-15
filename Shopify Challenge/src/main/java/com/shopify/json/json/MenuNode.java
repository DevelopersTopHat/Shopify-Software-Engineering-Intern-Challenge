package com.shopify.json.json;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuNode {

    //Uniquely identifies each node of the Menu.
    private int id;
    //Specifies the data that the node holds.
    private String data;
    //Specifies the children of the node.
    private int[] child_ids;
    //parent_id is optional, is the last attribute to be added when constructing.
    private int parent_id;
    //If this person has a parent_id of 0, then this node has a root_id.
    private boolean root_id = false;
    //Links to children nodes.
    private List<MenuNode> childrenLinks = new ArrayList<MenuNode>();

    //Constructor if the node has no parent id.
    public MenuNode(int id, String data, int[] child_ids) {
        this.id = id;
        this.data = data;
        this.child_ids = child_ids;
    }

    //Constructor if the node has a parent id.
    public MenuNode(int id, String data, int[] child_ids, int parent_id) {
        this.id = id;
        this.data = data;
        this.child_ids = child_ids;
        this.parent_id = parent_id;
    }

    //Getters and setters for all of the values in the MenuNode.
    public int getID() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String newData) {
        this.data = newData;
    }

    public int[] getChildIDs() {
        return child_ids;
    }

    public int getParentID() {
        return parent_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChild_ids(int[] child_ids) {
        this.child_ids = child_ids;
    }

    public String getChild_ids_As_String() {
        return Arrays.toString(child_ids);
    }

    public boolean isRoot_id() {
        return root_id;
    }

    public void setRoot_id(boolean root_id) {
        this.root_id = root_id;
    }

    public void storeChildNodes(List<MenuNode> nodeList) {
        for (int j = 0; j < getChildIDs().length; j++) {
            for (int i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i).getID() == getChildIDs()[j]) {
                    childrenLinks.add(nodeList.get(i));
                }
            }
        }
    }

    public List<MenuNode> getChildNodes() {
        return childrenLinks;
    }

    @Override
    public String toString() {
        return "[id = " + id + ", child_ids = " + getChild_ids_As_String() + ", parent_id = " + parent_id + "]";
    }
}
