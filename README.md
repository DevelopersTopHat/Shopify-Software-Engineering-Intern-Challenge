# Shopify-Software-Engineering-Intern-Challenge-Summer-2018
I am uploading my solution to the summer of 2018 SWE intern challenge, now that the due date has passed.
This is 100% the work of Abdeali Daginawala.

The challenge was: given the links to a JSON API containing menu nodes, create a JSON file that contains the list of all valid menus, and a list of all invalid menus. Menus should not contain cyclical references and can have a maximum depth of 4.

I believe that my solution is unique as I preserve the hierarchical structure of the nodes, based on the optional value of a parent-id. I used a hashtable to contain all the levels of menu tree, starting at 1; where subsequent levels contain the children of the above level.
 
/*
        Transforms the MenuNodes in the list into a hierarchical structure. Does this through hashtable implementation. Stores the hashtables in a list.

            |---------------------------------------------------|
            | Level 1  | List of nodes with no parent_id        |
            |---------------------------------------------------|
            | Level 2  | List of children nodes to the above level
            |---------------------------------------------------|
            |  ...     | List of nodes without children         |
            |---------------------------------------------------|

*/
