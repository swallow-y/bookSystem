package entity;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
public class Order {
      int bookId;
      float price;
      String bookName;
      int bookSize;
      int buyNumber;
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBookSize() {
		return bookSize;
	}
	public void setBookSize(int bookSize) {
		this.bookSize = bookSize;
	}
	public int getBuyNumber() {
		return buyNumber;
	}
	public void setBuyNumber(int buyNumber) {
		this.buyNumber = buyNumber;
	}
    public static List<Order> getEntity(String objectString){
    	List<Order> list = JSON.parseArray(objectString,Order.class);
    	System.out.println(list.get(0).bookId);
		return list; 	
    	
    }
}
