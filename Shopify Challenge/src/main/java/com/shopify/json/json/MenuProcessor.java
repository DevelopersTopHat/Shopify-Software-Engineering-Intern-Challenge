package com.shopify.json.json;

import java.util.*;

public class MenuProcessor {

    private static List<List<Integer>> validMenus;
    private static List<List<Integer>> invalidMenus = new ArrayList<List<Integer>>();
    private static List<JSONReadyObjectValid> valid_menus = new ArrayList<JSONReadyObjectValid>();
    private static List<JSONReadyObjectInvalid> invalid_menus = new ArrayList<JSONReadyObjectInvalid>();

    public MenuProcessor(List<List<Integer>> validMenus, List<List<MenuNode>> invalidMenuNodes) {
        this.validMenus = validMenus;
        getInvalidIDs(invalidMenuNodes);

        for (int i = 0; i < validMenus.size(); i++) {
            JSONReadyObjectValid jsonReadyObjectValid = new JSONReadyObjectValid(validMenus.get(i));
            valid_menus.add(jsonReadyObjectValid);
        }

        for (int j = 0; j < invalidMenus.size(); j++) {
            JSONReadyObjectInvalid jsonReadyObjectInvalid = new JSONReadyObjectInvalid(invalidMenus.get(j));
            invalid_menus.add(jsonReadyObjectInvalid);
        }
    }

    private void getInvalidIDs(List<List<MenuNode>> invalidMenus) {
        List<List<Integer>> invalidIDChains = new ArrayList<List<Integer>>();

        for (int i = 0; i < invalidMenus.size(); i++) {
            List<Integer> menuIDs = new ArrayList<Integer>();
            for (int j = 0; j < invalidMenus.get(i).size(); j++) {
                menuIDs.add(invalidMenus.get(i).get(j).getID());
            }
            invalidIDChains.add(menuIDs);
        }

        invalidPostProcessing(invalidIDChains);
    }

    private void invalidPostProcessing(List<List<Integer>> invalidIDChains) {
        Set<Integer> nonDuplicateIDs = new LinkedHashSet<Integer>();
        List<Integer> idChain;

        for (int i = 0; i < invalidIDChains.size(); i++) {
            idChain = invalidIDChains.get(i);
            nonDuplicateIDs.addAll(idChain);
            idChain.clear();
            idChain.addAll(nonDuplicateIDs);
            Collections.reverse(idChain);
            invalidMenus.add(idChain);
        }
    }

    public List<JSONReadyObjectValid> getValidMenus() {
        return valid_menus;
    }

    public List<JSONReadyObjectInvalid> getInvalidMenus() {
        return invalid_menus;
    }
}
