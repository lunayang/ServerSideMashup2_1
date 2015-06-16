package store;

import java.math.BigDecimal;
import java.util.*;

/**
 * A very simple shopping Cart
 */
public class Cart {

	public HashMap<Item, Integer> contents;

	/**
	 * Creates a new Cart instance
	 */
	public Cart() {
		contents = new HashMap<Item, Integer>();
	}

	/**
	 * Adds a named item to the cart
	 * 
	 * @param itemName
	 *            The name of the item to add to the cart
	 */
	public void addItem(String name) {

		Catalog catalog = new Catalog();

		if (catalog.containsItem(name)) {
			Item item = catalog.getItem(name);

			int newQuantity = 1;
			if (contents.containsKey(item)) {
				Integer currentQuantity = contents.get(item);
				newQuantity += currentQuantity.intValue();
			}

			contents.put(item, new Integer(newQuantity));
		}
                
	}
	
	/**
	 *
	 * @param name
	 * @return Item
	 */
	public List<Item> getAllItems() {
		
		List<Item> items = new ArrayList<Item>();
		Set<Item> set = contents.keySet();
		Iterator<Item> it = set.iterator();
		while(it.hasNext()) {
			items.add(it.next());
		}
		return items;
                
	}

	/**
	 * Removes the named item from the cart
	 * 
	 * @param itemName
	 *            Name of item to remove
	 */
	public void removeItems(String itemCode) {
            Catalog catalog = new Catalog();

            if (catalog.containsItem(itemCode)) {
                
                    contents.remove(new Catalog().getItem(itemCode));
                
            }

	}

	/**
	 * @return XML representation of cart contents
	 */
	public String toXml() {
		StringBuffer xml = new StringBuffer();
		xml.append("<?xml version=\"1.0\"?>\n");
		xml.append("<cart generated=\"" + System.currentTimeMillis()
				+ "\" total=\"" + getCartTotal() + "\">\n");

		for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
			Item item = I.next();
			int itemQuantity = contents.get(item).intValue();

			xml.append("<item code=\"" + item.getCode() + "\">\n");
			xml.append("<name>");
			xml.append(item.getName());
			xml.append("</name>\n");
			xml.append("<quantity>");
			xml.append(itemQuantity);
			xml.append("</quantity>\n");
			xml.append("</item>\n");
		}

		xml.append("</cart>\n");
		return xml.toString();
	}

	/**
	 * 
	 * @return JSON representation of cart contents
	 */
	public String toJson() {
		StringBuilder json = new StringBuilder();
		json.append("{\"cart\": {\"generated\": \"");
		json.append(System.currentTimeMillis());
		json.append("\", \"total\": \"");//json.append("\", \"total\": \"getCartTotal():");
		json.append(getCartTotal());
		json.append("\"");
		
		json.append(", \"item\": ["); // item is a array.
		
		int index = 0;
		for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
			Item item = I.next();
			//int itemQuantity = contents.get(item).intValue();
			//
			if(index > 0) {
				json.append(",");
			}
			//json.append("{\"item\": {\"code\":\"");
			json.append("{\"name\":\"");
			json.append(item.getName());
			json.append("\", \"source\": \"");
			json.append(item.getSource());
			json.append("\",\"url\":\"");
			json.append(item.getUrl());
			json.append("\"}");
			index ++;
		}
		json.append("]");
		
		json.append("}}");

		return json.toString();
	}

	private int getCartTotal() {
		int total = 0;

		for (Iterator<Item> I = contents.keySet().iterator(); I.hasNext();) {
			Item item = I.next();
			//int itemQuantity = contents.get(item).intValue();

			total +=  1;
		}

		return total;
	}
}
