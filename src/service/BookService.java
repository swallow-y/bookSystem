package service;

import java.util.List;
import java.util.Map;

import dao.BookDao;
import entity.Book;
import entity.Customer;
import entity.Order;

public class BookService {


private BookDao bookDao = new BookDao();

// ���ӻ��߱༭���ݿ��ͼ��
public String saveBook(Book book ,String method) {
	try {
		if (this.bookDao.saveBook(book,method)) {
			return "yes";
		} else {
			return "no";
		}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}
	return "no";
}
//ɾ��ͼ��
public void deleteBook(int id) {
	// TODO Auto-generated method stub
	try {
		this.bookDao.deleteBook(id);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}
}
//����Ա�鿴ͼ��
public List<Map<String, Object>> findBooks(int page) {
	int pageNow = page;
	try {
		return this.bookDao.findBooks(pageNow);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}
	return null;
}
//����Ա�鿴ĳ����
public List<Map<String, Object>> findThisBook(int id) {
	// TODO Auto-generated method stub
	int thisId = id;
	try {
		return this.bookDao.findThisBook(thisId);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}
	return null;
}
//�õ��ж�����ͼ��
public int findBookLength() {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.findBookLength();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}
	return 0;
}
//�û�ע��
public int saveUser(Customer customer) {
	// TODO Auto-generated method stub
	try {
		
			return this.bookDao.saveUser(customer);
		
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}
	return 0;
}
//�û���¼��֤
public int login(String userName,String password) {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.login(userName,password);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}
	return 0;
}
//������������
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
		System.out.println("�������ݿ����");
	}

	return null;
}
//�û�����
public List<Map<String, Object>> serchBook(String condition, String inputValue,
		int pageNow) {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.serchBook(condition,
				inputValue, pageNow);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}

	return null;
}
//�û�ֱ������
public boolean BuyBook(int id, String userName,int userId,int BuyNumber,int bookSize,double price,String bookName) {
	// TODO Auto-generated method stub
	
	try {
		return this.bookDao.BuyBook(id,userName,userId,BuyNumber,bookSize,price,bookName);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}
	return false;
}
//�û��鿴����
public List<Map<String, Object>> showOrderList(String userName) {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.showOrderList(userName);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}

	return null;
}
//�û����ͼ�鵽���ﳵ
public boolean addToShoppingCart(int bookid, String userName, int userId) {
	// TODO Auto-generated method stub
	
	try {
		return this.bookDao.addToShoppingCart(bookid,userName,userId);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}
	return false;
}
//�û��鿴���ﳵ
public List<Map<String, Object>> showCartList(String userName,int userId) {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.showCartList(userName,userId);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}

	return null;
}
//����Ա�鿴���еĶ���
public List<Map<String, Object>> allOrderList() {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.allOrderList();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}

	return null;
}
//�û������ﳵ����
public boolean BuyAllSelect(String userName, int userId, List<Order> list) {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.BuyAllSelect(userName,userId,list);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}
	return false;
}
//����Ա�鿴����
public List<Map<String, Object>> inBookList() {
	// TODO Auto-generated method stub
	try {
		return this.bookDao.inBookList();
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("�������ݿ����");
	}


	return null;
}


}