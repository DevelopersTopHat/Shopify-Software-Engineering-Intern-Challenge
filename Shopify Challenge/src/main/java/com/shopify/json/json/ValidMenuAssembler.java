package com.shopify.json.json;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ValidMenuAssembler {

    private static List<MenuNode> nodeList;
    private static List<Hashtable<Integer, List<MenuNode>>> menuTrees;
    private static List<List<MenuNode>> listOfInvalidMenus;
    //to be returned.
    private static List<List<Integer>> listOfValidMenus = new ArrayList<List<Integer>>();

    public ValidMenuAssembler(List<MenuNode> nodeList, List<Hashtable<Integer, List<MenuNode>>> menuTrees, List<List<MenuNode>> listOfInvalidMenus) {
        this.nodeList = nodeList;
        this.menuTrees = menuTrees;
        this.listOfInvalidMenus = listOfInvalidMenus;

        removeInvalidNodes();

        //This is a precautionary measure to save time. If the entire hash table is empty, remove it.
        removeEmptyTrees();

        for (int i = 0; i < menuTrees.size(); i++) {
            makeValidMenus(menuTrees.get(i));
        }
    }

    private void makeValidMenus(Hashtable<Integer, List<MenuNode>> menuTree) {
        //for every level of the tree
        for (Integer level : menuTree.keySet()) {
            //for every node on a level
            for (int i = 0; i < menuTree.get(level).size(); i++) {
                collectIDs(level, menuTree, menuTree.get(level).get(i));
            }
        }
    }

    private void collectIDs(int rootLevel, Hashtable<Integer, List<MenuNode>> menuTree, MenuNode rootNode) {
        List<Integer> validIDChain = new ArrayList<Integer>();
        validIDChain.add(rootNode.getID());

        while (menuTree.get(rootLevel + 1) != null) {
            rootLevel++;
            for (int i = 0; i < menuTree.get(rootLevel).size(); i++) {
                if (chainHasID(menuTree.get(rootLevel).get(i).getParentID(), validIDChain)) {
                    validIDChain.add(menuTree.get(rootLevel).get(i).getID());
                }
            }
        }
        listOfValidMenus.add(validIDChain);
    }

    private boolean chainHasID(int parentID, List<Integer> validIDChain) {
        if (validIDChain.contains(parentID)) {
            return true;
        }
        return false;
    }


    /*
        private void makeValidMenus(Hashtable<Integer, List<MenuNode>> menuTree) {

            collectLargestMenus(menuTree);

            levelTraversal(menuTree);

        }

        //traverses the levels of the hashtable.
        private void levelTraversal(Hashtable<Integer, List<MenuNode>> menuTree) {
            for (Integer level : menuTree.keySet()) {
                nodeTraversal(menuTree, level);
            }
        }

        //traverses the nodes at each level of the hashtable.
        private void nodeTraversal(Hashtable<Integer, List<MenuNode>> menuTree, int level) {
            for (int i = 0; i < menuTree.get(level).size(); i++) {
                getValidChain(menuTree.get(level).get(i), menuTree, level);
            }
        }

        private void getValidChain(MenuNode rootNode, Hashtable<Integer, List<MenuNode>> menuTree, int level) {
            List<MenuNode> validChain = new ArrayList<MenuNode>();
            validChain.add(rootNode);

            while (rootNode.getChildIDs().length > 0) {
                level++;
                for (MenuNode node : menuTree.get(level)) {
                    for (int i = 0; i < rootNode.getChildIDs().length; i++) {
                        if (rootNode.getChildIDs()[i] == node.getID()) {
                            rootNode = node;
                            validChain.add(node);
                        }
                    }
                }
            }
            listOfValidMenus.add(validChain);
        }

        private void collectLargestMenus(Hashtable<Integer, List<MenuNode>> menuTree) {
            List<MenuNode> validChain = new ArrayList<MenuNode>();
            for (Integer level : menuTree.keySet()) {
                for (int i = 0; i < menuTree.get(level).size(); i++) {
                    validChain.add(menuTree.get(level).get(i));
                }
            }
            listOfValidMenus.add(validChain);
        }

    */
    private void removeEmptyTrees() {
        for (int i = 0; i < menuTrees.size(); i++) {
            int nodeCount = 0;
            for (Integer level : menuTrees.get(i).keySet()) {
                if (menuTrees.get(i).get(level).size() > 0) {
                    nodeCount += menuTrees.get(i).get(level).size();
                }
            }
            if (nodeCount == 0) {
                menuTrees.remove(i);
            }
        }
    }

    private void removeInvalidNodes() {
        for (int i = 0; i < listOfInvalidMenus.size(); i++) {
            for (int j = 0; j < menuTrees.size(); j++) {
                compareNodes(listOfInvalidMenus.get(i), menuTrees.get(j));
            }
        }
    }

    private void compareNodes(List<MenuNode> invalidNodeList, Hashtable<Integer, List<MenuNode>> menuTree) {
        for (int i = 0; i < invalidNodeList.size(); i++) {
            for (Integer level : menuTree.keySet()) {
                checkIDOverlap(invalidNodeList.get(i).getID(), menuTree.get(level));
            }
        }
    }

    private void checkIDOverlap(int invalidID, List<MenuNode> nodesOnLevel) {
        for (int i = 0; i < nodesOnLevel.size(); i++) {
            if (nodesOnLevel.get(i).getID() == invalidID) {
                nodesOnLevel.remove(i);
            }
        }
    }

    public List<List<Integer>> getListOfValidMenus() {
        return listOfValidMenus;
    }
}
