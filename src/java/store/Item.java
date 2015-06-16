/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

/**
 *
 * @author Haoyue
 */


import java.math.BigDecimal;

public class Item {
  private String source;
  private String name;
  private String code;
  private String url;
 

  public Item(String code,String source,String url) {
    
    this.name=code;
    this.code=code;
    this.source=source;
    this.url=url;
  }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }
public String getCode() {
        return code;
    }
 
  public String getName() {
    return name;
  }

  
  

  public boolean equals(Object o) {
    if (this == o) return true;
    if (this == null) return false;
    if (!(o instanceof Item)) return false;
    return ((Item)o).getUrl().equals(this.url);
  }

   
}

