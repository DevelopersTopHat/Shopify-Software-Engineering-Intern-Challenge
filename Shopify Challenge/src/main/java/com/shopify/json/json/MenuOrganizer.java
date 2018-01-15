package com.shopify.json.json;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MenuOrganizer {

    //a list of all the possible menus contained in hashtables.
    private static List<Hashtable<Integer, List<MenuNode>>> menuTrees = new ArrayList<Hashtable<Integer, List<MenuNode>>>();

    //The hashtables start at level 1 and work there way down until the nodes no longer have children.
    public MenuOrganizer(List<MenuNode> nodeList) {

        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).getParentID() == 0) {
                Hashtable<Integer, List<MenuNode>> menuAdjacency = new Hashtable<Integer, List<MenuNode>>();
                List<MenuNode> level = new ArrayList<MenuNode>();
                level.add(nodeList.get(i));
                menuAdjacency.put(1, level);
                menuTrees.add(menuAdjacency);
            }
        }

        fillMenuTrees(nodeList);

    }

    private void fillMenuTrees(List<MenuNode> nodeList) {
        for (int i = 0; i < menuTrees.size(); i++) {
            sortMenus(nodeList, menuTrees.get(i));
        }
    }

    private void sortMenus(List<MenuNode> nodeList, Hashtable<Integer, List<MenuNode>> menuTree) {
        for (Integer level : menuTree.keySet()) {
            locateChildNodes(level, nodeList, menuTree);
        }
    }

    private void locateChildNodes(int level, List<MenuNode> nodeList, Hashtable<Integer, List<MenuNode>> menuTree) {

        //creates the list to be added to the next level
        List<MenuNode> nodesOfLevel = new ArrayList<MenuNode>();

        //goes through every node in the list of that level
        for (int j = 0; j < menuTree.get(level).size(); j++) {
            //finds the child nodes in the nodelist
            for (int i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i).getParentID() == menuTree.get(level).get(j).getID()) {
                    nodesOfLevel.add((nodeList.get(i)));
                }
            }
        }

        menuTree.put(level + 1, nodesOfLevel);

        checkForChildren(menuTree, nodeList);

    }

    //checks the lowest level of the hashtable and sees if the nodes in the list have children.
    private void checkForChildren(Hashtable<Integer, List<MenuNode>> menuTable, List<MenuNode> nodeList) {
        for (int  i = 0; i < menuTable.get(menuTable.size()).size(); i++) {
            if (menuTable.get(menuTable.size()).get(i).getChildIDs().length > 0) {
                locateChildNodes(menuTable.size(), nodeList, menuTable);
            }
        }
    }

    public List<Hashtable<Integer, List<MenuNode>>> getMenuTrees() {
        return menuTrees;
    }

}
