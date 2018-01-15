package com.shopify.json;

import com.shopify.json.json.*;
import okhttp3.OkHttpClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Collect the information from the JSON link and aggregate into a list of MenuNode objects.
        //To use a JSON link, copy the entire url - the page number.
        //MenuAssembler menuAssembler = new MenuAssembler("https://backend-challenge-summer-2018.herokuapp.com/challenges.json?id=1&page=");
        MenuAssembler menuAssembler = new MenuAssembler("https://backend-challenge-summer-2018.herokuapp.com/challenges.json?id=2&page=");

        /*
        Transform the MenuNodes in the list into a hierarchical structure. Does this through hashtable implementation. Stores the hashtables in a list.

            |---------------------------------------------------|
            | Level 1  | List of nodes with no parent_id        |
            |---------------------------------------------------|
            | Level 2  | List of children nodes to the above level
            |---------------------------------------------------|
            |  ...     | List of nodes without children         |
            |---------------------------------------------------|

         */
        MenuOrganizer menuOrganizer = new MenuOrganizer(menuAssembler.getNodeList());
/*
        System.out.println(menuOrganizer.getMenuTrees().get(0).toString());
        System.out.println(menuOrganizer.getMenuTrees().get(1).toString());
        System.out.println(menuOrganizer.getMenuTrees().get(2).toString());
*/
        // Parses through the hashtables checking for nodes with a repeating id on a lower level.
        // Stores those nodes in a list starting at the leaf and working its way up to the offender.
        // Stores all chains of invalid nodes in a list.
        InvalidMenuAssembler invalidMenuAssembler = new InvalidMenuAssembler(menuAssembler.getNodeList(), menuOrganizer.getMenuTrees());

        //to be converted to JSON file.
        //Uses last node id as the root_id and every other node id as children.
        List<List<MenuNode>> invalidMenus = invalidMenuAssembler.getListOfInvalidMenus();

        //Uses the list of invalid menus and their node ids and removes those nodes from the menu tree.
        //Gets all permutations of menu nodes from the resultant tree.
        ValidMenuAssembler validMenuAssembler = new ValidMenuAssembler(menuAssembler.getNodeList(), menuOrganizer.getMenuTrees(), invalidMenus);

        //to be converted to JSON file.
        //Uses first id as the root_id and every other id as children.
        List<List<Integer>> validMenus = validMenuAssembler.getListOfValidMenus();

        //formats the menus to match the style of output desired.
        MenuProcessor menuProcessor = new MenuProcessor(validMenus, invalidMenus);

        //writes the objects to json using gson and filewriter.
        JSONWriter jsonWriter = new JSONWriter(menuProcessor.getValidMenus(), menuProcessor.getInvalidMenus());

    }

}
