package service;

import java.util.List;
import java.util.Map;

import dao.BookDao;
import entity.Book;
import entity.Customer;
import entity.Order;

public class BookService {


private BookDao bookDao = new BookDao();

// 增加或者编辑数据库的图书
public String saveBook(Book book ,String method) {
	try {
		if (this.bookDao.saveBook(book,method)) {
			return "yes";
		} else {
			return "no";
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}
	return "no";
}
//删除图书
public void deleteBook(int id) {
	// TODO Auto-generated method stub
	try {
		this.bookDao.deleteBook(id);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}
}
//管理员查看图书
public List<Map<String, Object>> findBooks(int page) {
	int pageNow = page;
	try {
		return this.bookDao.findBooks(pageNow);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}
	return null;
}
//管理员查看某本书
public List<Map<String, Object>> findThisBook(int id) {
	// TODO Auto-generated method stub
	int thisId = id;
	try {
		return this.bookDao.findThisBook(thisId);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}
	return null;
}
//得到有多少种图书
public int findBookLength() {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.findBookLength();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}
	return 0;
}
//用户注册
public int saveUser(Customer customer) {
	// TODO Auto-generated method stub
	try {
		
			return this.bookDao.saveUser(customer);
		
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}
	return 0;
}
//用户登录验证
public int login(String userName,String password) {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.login(userName,password);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}
	return 0;
}
//按条件查找书
public  List<Map<String, Object>> findByCondition(String condition,
		String inputValue, int page) {
	// TODO Auto-generated method stub
	String conditionNow = condition;
	String inputValueNow = inputValue;
	int pageNow = page;
	try {
		return this.bookDao.findByCondition(conditionNow,
				inputValueNow, pageNow);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}

	return null;
}
//用户查书
public List<Map<String, Object>> serchBook(String condition, String inputValue,
		int pageNow) {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.serchBook(condition,
				inputValue, pageNow);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}

	return null;
}
//用户直接买书
public boolean BuyBook(int id, String userName,int userId,int BuyNumber,int bookSize,double price,String bookName) {
	// TODO Auto-generated method stub
	
	try {
		return this.bookDao.BuyBook(id,userName,userId,BuyNumber,bookSize,price,bookName);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}
	return false;
}
//用户查看订单
public List<Map<String, Object>> showOrderList(String userName) {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.showOrderList(userName);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}

	return null;
}
//用户添加图书到购物车
public boolean addToShoppingCart(int bookid, String userName, int userId) {
	// TODO Auto-generated method stub
	
	try {
		return this.bookDao.addToShoppingCart(bookid,userName,userId);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}
	return false;
}
//用户查看购物车
public List<Map<String, Object>> showCartList(String userName,int userId) {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.showCartList(userName,userId);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}

	return null;
}
//管理员查看所有的订单
public List<Map<String, Object>> allOrderList() {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.allOrderList();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}

	return null;
}
//用户到购物车购买
public boolean BuyAllSelect(String userName, int userId, List<Order> list) {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.BuyAllSelect(userName,userId,list);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}
	return false;
}
//管理员查看入库表
public List<Map<String, Object>> inBookList() {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.inBookList();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错！");
	}


	return null;
}


}