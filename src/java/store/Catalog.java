/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

/**
 * contains all items
 * return the item base on the itemCode
 * 
 * @author Haoyue
 * @version 1.1
 * @since 02/06/15
 */
import java.util.*;

public class Catalog {
    
 
    //store all the items
    private static Map<String, Item> items;

    static {
        items = new HashMap<String, Item>();
        items.put("NYT America News", new Item("NYT America News", "NYT America News", "http://rss.nytimes.com/services/xml/rss/nyt/Americas.xml"));
        items.put("USA News", new Item("USA News", "USA news", "http://rss.nytimes.com/services/xml/rss/nyt/US.xml"));
        items.put("New South Wales News", new Item("New South Wales News", "Sydney Morning Herald New South Wales News", "http://www.smh.com.au/rssheadlines/nsw/article/rss.xml"));
        items.put("IT Pro News", new Item("IT Pro News", "Sydney Morning Herald IT Pro News", "http://feeds.smh.com.au/rssheadlines/itpro.xml"));
        items.put("Latin America", new Item("Latin America", "BBC Latin American News", "http://feeds.bbci.co.uk/news/world/latin_america/rss.xml"));
        items.put("Adelaide News", new Item("Adelaide News", "Adelaide News from Adelaide Now", "http://feeds.news.com.au/public/rss/2.0/anow_state_191.xml"));
        items.put("ABC News", new Item("ABC News", "ABC News Now", "http://www.abc.net.au/news/feed/46182/rss.xml"));
        items.put("South Australia", new Item("South Australia", "South Australia News", "http://www.abc.net.au/news/feed/54702/rss.xml"));
        items.put("Business News", new Item("Business News", "Business News Now", "http://www.abc.net.au/news/feed/51892/rss.xml"));
        items.put("Sports News", new Item("Sports News", "Sports News Now", "http://www.abc.net.au/news/feed/45924/rss.xml"));

    }

    public Collection<Item> getAllItems() {
        return items.values();
    }

    public boolean containsItem(String itemCode) {
        return items.containsKey(itemCode);
    }

    public Item getItem(String itemCode) {
        return items.get(itemCode);
    }

}
