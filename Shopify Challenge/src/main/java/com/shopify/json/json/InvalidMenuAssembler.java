package com.shopify.json.json;

import java.util.*;

public class InvalidMenuAssembler {

    private static List<MenuNode> nodeList;
    private static List<Hashtable<Integer, List<MenuNode>>> menuTrees;
    private static List<List<MenuNode>> listOfInvalidMenus = new ArrayList<List<MenuNode>>();

    public InvalidMenuAssembler(List<MenuNode> nodeList, List<Hashtable<Integer, List<MenuNode>>> menuTrees) {
        this.nodeList = nodeList;
        this.menuTrees = menuTrees;

        getTrees();
    }

    private void getTrees() {
        for (int i = 0; i < menuTrees.size(); i++) {
            parseTables(menuTrees.get(i));
        }
    }

    private void parseTables(Hashtable<Integer, List<MenuNode>> menuTree) {
        for (Integer level : menuTree.keySet()) {
            checkLowerLevels(level, menuTree);
        }
    }

    private void checkLowerLevels(Integer level, Hashtable<Integer, List<MenuNode>> menuTree) {
        int maxDepth;

        if (menuTree.size() - level > 4) {
            maxDepth = level + 4;
        }
        else {
            maxDepth = menuTree.size() - level;
        }

        //loop through nodes on the level you are verifying for duplicates.
        for (int k = 0; k < menuTree.get(level).size(); k++) {
            //go through the lower levels checking for duplicates.
            for (int i = level + 1; i <= maxDepth; i++) {
                checkForDuplicates(menuTree.get(i), menuTree.get(level).get(k));
            }
        }
    }

    private void checkForDuplicates(List<MenuNode> lowerLevelNodes, MenuNode rootNode) {
        //go through the nodes of the level 1 lower than the current level.
        for (int i = 0; i < lowerLevelNodes.size(); i++) {
            checkChildrenIDs(lowerLevelNodes.get(i), rootNode);
        }
    }

    private void checkChildrenIDs(MenuNode menuNode, MenuNode rootNode) {
        for (int i = 0; i < menuNode.getChildIDs().length; i++) {
            if (menuNode.getChildIDs()[i] == rootNode.getID()) {
                collectInvalidChain(menuNode.getID(), menuNode.getParentID(), rootNode.getID());
            }
        }
    }

    private void collectInvalidChain(int leafID, int parentOfLeafID, int rootID) {
        List<MenuNode> invalidChain = new ArrayList<MenuNode>();
        while (parentOfLeafID != rootID) {
            for (int i = 0; i < nodeList.size(); i++) {
                if (nodeList.get(i).getID() == leafID) {
                    invalidChain.add(nodeList.get(i));
                    leafID = parentOfLeafID;
                    parentOfLeafID = nodeList.get(i).getParentID();
                }
            }
        }

        for (int j = 0; j < nodeList.size(); j++) {
            if (nodeList.get(j).getID() == rootID) {
                invalidChain.add(nodeList.get(j));
            }
        }

        listOfInvalidMenus.add(invalidChain);
    }

    public List<List<MenuNode>> getListOfInvalidMenus() {
        return listOfInvalidMenus;
    }

}
