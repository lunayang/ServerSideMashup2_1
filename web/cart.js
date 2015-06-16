/**
 * adds items to shopping cart and remove from Shopping cart by using localStorage
 * name - itemCode as the key in the localStorage
 * url - the url String of each item, as the value in the localStorage
 *
 * @author Luna Yang
 * @version 1.1
 * @since 02/06/2015
 */

// Timestamp of cart that page was last updated with
var lastCartUpdate = Date.now();
/*
 * Adds the specified item to the shopping cart, via Ajax call
 * itemCode - product code of the item to add
 */
function addToCart(name, url) {
    // not every brower supports HTML5
    if (!window["localStorage"]) {
        alert("no local storage support");
        return false;
    }
    //get the name and url of item as the key and value and stores in localStorage
    if (name && url) {
        localStorage.setItem(name, url);
        //update shopping cart each time adding a new item
        updateCart();
    }
}

function deleteFromCart(name) {
    //delete the item in the localStorage
    if (name) {
        localStorage.removeItem(name);
        //update shopping cart each time deleting a new item
        updateCart();
    }

}

function display(name) {
    //get urlString from local storage
    var url = localStorage.getItem(name);
    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState == 4) {
            //add the reponse with former contents
            document.getElementById("newsDiv").innerHTML = document.getElementById("newsDiv").innerHTML + req.response;
        }
    };
    //set false to be synchronized and send the urlString to the servlet
    req.open("get", "xmlcart.do?url=" + url, false);
    req.setRequestHeader("Content-Type", "application/x-www-form-urlencode");
    req.send(null);
}

function getDocsFromCart() {
    document.getElementById("newsDiv").innerHTML = '';

    //for each item in the localStorage, call the display function
    for (var name in localStorage) {

        if (localStorage.hasOwnProperty(name)) {
            //alert(name);
            display(name);
        }

    }
}
/*
 * Update shopping-cart area of page to reflect condtents of cart
 * using the localStorage to update the shopping-cart
 * described in XML document.
 */
function updateCart() {
    var generated = Date.now();
    if (generated > lastCartUpdate) {
        lastCartUpdate = generated;
        var contents = document.getElementById("contents");
        contents.innerHTML = "";

        //get all items in the localStorage
        var items = Object.keys(localStorage);
        //for each item in the localStorage, show it in the shopping-cart area of page
        items.forEach(function (name) {
            var item = localStorage[name];
            var listItem = document.createElement("li");
            listItem.appendChild(document.createTextNode(name));
            contents.appendChild(listItem);
        });

        document.getElementById("total").innerHTML = items.length;
    }

}


