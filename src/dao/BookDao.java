package dao;

import java.util.List;
import java.util.Map;
import entity.Book;
import entity.Customer;
import entity.Order;
import util.CommonDAO;

public class BookDao {

	private CommonDAO commonDAO = new CommonDAO();

	// 新增书籍或编辑图书
	public boolean saveBook(Book book ,String Method) {

		try {
			
			String sql;
			String method = Method;
			String bookname = book.getBookName();
			int bookisn = book.getBookISN();
			int bookprice = book.getBookPrice();
			int bookstore = book.getBookStore();
			String booktype = book.getBookType();
			String image = book.getBookImg();
            String bookDescript = book.getBookDescript();
            String author = book.getAuthor();
			if("saveBook".equals(method)){
				
			
				sql = "insert into book(id,bookName,price,type,bookSize,imagePath,descript,author) values("+ bookisn+ ",'"+ bookname+ "' ,"+bookprice+", '"+booktype+"', "+bookstore+", '"+image+"', '"+bookDescript+"','"+author+"')";
				this.commonDAO.executeUpdate(sql, new Object[] {});
				String sqlTwo ="insert into inBook(bookId,bookNumber,bookName) values("+ bookisn+ ","+ bookstore+ ",'"+ bookname+ "',NOW())";
				this.commonDAO.executeUpdate(sqlTwo, new Object[] {});
				return true;
				
			}
			else{
				if(image == null){
					sql = "update book " + "set id=" + "\"" + bookisn + "\""
							+ ",bookName=" + "\"" + bookname + "\",price=" + "\"" + bookprice
							+ "\",type=" + "\"" + booktype + "\"," + "bookSize="
							+ "\"" + bookstore + "\",descript=" + "\"" + bookDescript + "\",author=" + "\"" + author + "\" where id=" + "\"" + bookisn + "\"";
					this.commonDAO.executeUpdate(sql, new Object[] {});
					return true;

				}
				else{
					sql = "update book " + "set id=" + "\"" + bookisn + "\""
						+ ",bookName=" + "\"" + bookname + "\",price=" + "\"" + bookprice
						+ "\",type=" + "\"" + booktype + "\"," + "bookSize="
						+ "\"" + bookstore + "\",imagePath=" + "\"" + image
						+ "\",descript=" + "\"" + bookDescript + "\" where id=" + "\"" + bookisn + "\"";
					this.commonDAO.executeUpdate(sql, new Object[] {});
					return true;

				}
				
			}
			
		} catch (Exception e) {
			System.out.println("操作数据库出错");
		}

		return false;
	}
	//删除图书
	public boolean deleteBook(int id) {
		// TODO Auto-generated method stub
		try {
			String sql1 = "delete from inbook where bookId='" + id + "'";
			this.commonDAO.executeUpdate(sql1, new Object[] {});
			String sql = "delete from book where id='" + id + "'";
			this.commonDAO.executeUpdate(sql, new Object[] {});
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("操作数据库出错");
		}
		return false;
	}
//管理员查看图书
	public List<Map<String, Object>> findBooks(int page) {

		int numberNow = (page - 1) * 10;
		try {
			String sql = "select * from  book order by id desc limit " + numberNow + " , " + 8;
			List<Map<String, Object>> books = this.commonDAO.excuteQuery(
					sql, null);
			return books;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("操作数据库出错");
		}
		return null;
	}
	//管理员查看某本书
	public List<Map<String, Object>> findByCondition(String conditionNow,
			String inputValueNow, int page) {
		// TODO Auto-generated method stub
       
		int numberNow = (page - 1) * 8;

		String sql;
		 if(page == 0&&"null".equals(inputValueNow)){
			 try {
						sql = "select * from  book where type like '%" + conditionNow + "%'";

					List<Map<String, Object>> books = this.commonDAO.excuteQuery(
							sql, null);
					return books;
				} catch (Exception e) {
					System.out.println("操作数据库出错");
				}

	        }
		try {
			if ("".equals(inputValueNow)) {
				sql = "select * from  book limit " + numberNow + " , " + 8;

			} else {
				sql = "select * from  book where " + conditionNow
						+ " like '%" + inputValueNow + "%'  limit " + numberNow
						+ " , " + 8;

			}
			List<Map<String, Object>> books = this.commonDAO.excuteQuery(
					sql, null);
			return books;
		} catch (Exception e) {
			System.out.println("操作数据库出错");
		}

		return null;
	}
	//用户搜索图书
	public List<Map<String, Object>> serchBook(String conditionNow,
			String inputValueNow, int page) {
		// TODO Auto-generated method stub
		int numberNow = (page - 1) * 8;

		String sql;
		 if(page == 0&&"null".equals(inputValueNow)){
			 try {
				 if((conditionNow=="")||(conditionNow==null)){
					 sql = "select * from  book";

				 }
				 else{
					 sql = "select * from  book where bookName like '%" + conditionNow + "%' or author like '%" + conditionNow + "%'";
				 }

					List<Map<String, Object>> books = this.commonDAO.excuteQuery(
							sql, null);
					return books;
				} catch (Exception e) {
					System.out.println("操作数据库出错");
				}

	        }
		try {
			if ("".equals(inputValueNow)) {
				sql = "select * from  book limit " + numberNow + " , " + 8;

			} else {
				sql = "select * from  book where " + conditionNow
						+ " like '%" + inputValueNow + "%'  limit " + numberNow
						+ " , " + 8;

			}
			List<Map<String, Object>> books = this.commonDAO.excuteQuery(
					sql, null);
			return books;
		} catch (Exception e) {
			System.out.println("操作数据库出错");
		}

		return null;
	}

	//用户查看某本书
	public List<Map<String, Object>> findThisBook(int thisId) {
		// TODO Auto-generated method stub
		int id = thisId;
		try {
			String sql = "select * from  book where id="+id+"";
			List<Map<String, Object>> thisBook = this.commonDAO.excuteQuery(
					sql, null);
			return thisBook;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("操作数据库出错");
		}
		return null;
	}
//得到图书分类量
	public int findBookLength() {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from  book ";
			List<Map<String, Object>> books = this.commonDAO.excuteQuery(
					sql, null);
			return books.size();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("操作数据库出错");
		}
		return 0;
	}
//用户注册
	public int saveUser(Customer customer) {
		// TODO Auto-generated method stub
           try {
			
			String sql;
			
			String userName = customer.getUserName();
			String password = customer.getPassword();
			String userPhone = customer.getTel();

				sql = "insert into user(name,phone,password) values('"+ userName+ "','"+ userPhone+ "' ,'"+password+"')";
				this.commonDAO.executeUpdate(sql, new Object[] {});
			    sql = "select * from  user where name='"+userName+"'";
				List<Map<String, Object>> thisPerson = this.commonDAO.excuteQuery(
						sql, null);
				sql = "insert into shoppingCart(userId,time) values('"+(int) thisPerson.get(0).get("id")+ "',NOW())";
				this.commonDAO.executeUpdate(sql, new Object[] {});
				
							return (int) thisPerson.get(0).get("id");
			
		} catch (Exception e) {
			System.out.println("操作数据库出错");
		}

		return 0;
	}
//用户登录验证
	public int login(String userName,String password) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from user where (name='"+userName+"')"+"and password = '"+password+"'";
			List<Map<String, Object>> user = this.commonDAO.excuteQuery(
					sql, null);
			if(user.size() == 0){
				return 0;
			}
			
			return (int) user.get(0).get("id");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("操作数据库出错");
		}
		return 0;
	}
	//用户直接购买书
	public boolean BuyBook(int thisId, String username,int userid,int buyNumber,int booksize,double price,String bookName) {
		// TODO Auto-generated method stub

		try {
			
		   
		    String sql = "insert into userorder(userId,userName,price,orderTime) values("+ userid+ ",'"+ username+ "',0,NOW())";
			this.commonDAO.executeUpdate(sql, new Object[] {});
			int numberNow = 0;
			String sqlOne = "select * from userorder order by id desc limit " + numberNow + " , " + 1;
			List<Map<String, Object>> userOrder = this.commonDAO.excuteQuery(
					sqlOne, null);
			int orderId = (int) userOrder.get(0).get("id");
			
			String sqlTwo ="insert into orderItem(bookId,orderId,bookNumber,price,bookName,time) values("+ thisId+ ","+ orderId+ ",'"+ buyNumber+ "',"+price+","+bookName+",NOW())";
			this.commonDAO.executeUpdate(sqlTwo, new Object[] {});
			int bookSize = booksize - buyNumber;
			String sqlThree =  "update book " + "set bookSize=" + "\"" + bookSize + "\" where id="+"\"" + thisId + "\"";
			this.commonDAO.executeUpdate(sqlThree, new Object[] {});
			price = price*buyNumber;
			String sqlFour =  "update userorder " + "set allprice=" + "\"" + price + "\" where id="+"\"" + orderId + "\"";
			this.commonDAO.executeUpdate(sqlFour, new Object[] {});
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("操作数据库出错");
		}
		return false;
	}
	//展示用户的订单
	public List<Map<String, Object>> showOrderList(String userName) {
		// TODO Auto-generated method stub
	   try{
			
		String sql = "select * from  orderitem left join userorder on  orderitem.orderId = userorder.id"
				+ " where userName = '"+userName+"'";
		List<Map<String, Object>> userOrder = this.commonDAO.excuteQuery(
				sql, null);
		
		

		return userOrder;
	   } catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错");
	   }
		return null;
	   }
	//没用left join 的展示订单
	@SuppressWarnings("null")
	public List<Map<String, Object>> showOrderList1(String userName) {
		// TODO Auto-generated method stub
	   try{
			
		String sql = "select * from  userorder where userName = '"+userName+"'";
		List<Map<String, Object>> userOrder = this.commonDAO.excuteQuery(
				sql, null);
		
		int orderId = (int) userOrder.get(0).get("id");
		String sqlOne  = "select * from  orderitem where orderId = "+orderId+"";
		List<Map<String, Object>> userOrderItem  =  this.commonDAO.excuteQuery(sqlOne, null);
		
		for(int i = 1; i < userOrder.size();i++){
			orderId = (int) userOrder.get(i).get("id");
			

		sqlOne = "select * from  orderitem where orderId = "+orderId+"";
			List<Map<String, Object>> userOrderItemNow =  this.commonDAO.excuteQuery(sqlOne, null);
			userOrderItem .addAll(userOrderItemNow);
		}

		return userOrderItem;
	   } catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错");
	   }
		return null;
	   }
	//用户添加图书到购物车
	public boolean addToShoppingCart(int bookid, String userName, int userId) {
		// TODO Auto-generated method stub
		try{
			
		String selectsql = "select * from  shoppingCart where userId = "+userId+"";
		List<Map<String, Object>> userCart =  this.commonDAO.excuteQuery(selectsql, null);
	    int cartId = (int)userCart.get(0).get("id");
		String sqlinsert = "insert into cartAndOther(bookId,cartId) values("+ bookid+ ","+ cartId+ ")";
		this.commonDAO.executeUpdate(sqlinsert, new Object[] {});
		
		
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("操作数据库出错");
	}
	return false;
	}
	//用户查看购物车
	public List<Map<String, Object>> showCartList(String userName,int userId) {
		// TODO Auto-generated method stub
		try{
			String sqlSelectCartId ="select * from  shoppingCart where userId ="+userId+"";
			List<Map<String, Object>> userCartId = this.commonDAO.excuteQuery(
					sqlSelectCartId, null);
			int cartId = (int)userCartId.get(0).get("id");
			String sql = "select * from  book left join cartAndOther on  book.id = cartAndOther.bookId where cartId = "+cartId+"";
			List<Map<String, Object>> userCart = this.commonDAO.excuteQuery(
					sql, null);
			return userCart;
		   } catch (Exception e) {
			e.printStackTrace();
			System.out.println("操作数据库出错");
		   }
			return null;
	}
	//管理员查看订单
	public List<Map<String, Object>> allOrderList() {
		// TODO Auto-generated method stub
		 try{
				
				String sql = "select * from  orderitem left join userorder on  orderitem.orderId = userorder.id";
				List<Map<String, Object>> allOrder = this.commonDAO.excuteQuery(
						sql, null);

				return allOrder;
			   } catch (Exception e) {
				e.printStackTrace();
				System.out.println("操作数据库出错");
			   }
		return null;
	}
	//用户到购物车购买
	public boolean BuyAllSelect(String userName, int userId,List<Order> list) {
		// TODO Auto-generated method stub
		
		try {
			  
		    String sql = "insert into userorder(userId,userName,allprice,orderTime) values("+ userId+ ",'"+ userName+ "',0,NOW())";
			this.commonDAO.executeUpdate(sql, new Object[] {});
			int numberNow = 0;
			String sqlOne = "select * from userorder order by id desc limit " + numberNow + " , " + 1;
			List<Map<String, Object>> userOrder = this.commonDAO.excuteQuery(
					sqlOne, null);
			int orderId = (int) userOrder.get(0).get("id");
			float price =0;
			for(int i = 0;i<list.size();i++){
				String sqlTwo ="insert into orderItem(bookId,orderId,bookNumber,price,bookName,time) values("+ list.get(i).getBookId()+ ","+ orderId+ ",'"+ list.get(i).getBuyNumber()+ "',"+list.get(i).getPrice()+","+list.get(i).getBookName()+",NOW())";
				this.commonDAO.executeUpdate(sqlTwo, new Object[] {});
				int bookSize = list.get(i).getBookSize()- list.get(i).getBuyNumber();
				String sqlThree =  "update book " + "set bookSize=" + "\"" + bookSize + "\" where id="+"\"" + list.get(i).getBookId() + "\"";
				this.commonDAO.executeUpdate(sqlThree, new Object[] {});
			    price += (list.get(i).getPrice())*(list.get(i).getBuyNumber());
				System.out.println(list.get(i).getBuyNumber());
				
				
				System.out.println(list.get(i).getPrice());
				System.out.println(list.get(i).getBookSize());

				
			
			}

			String sqlFour =  "update userorder " + "set allprice=" +  price + " where id="+"\"" + orderId + "\"";
			this.commonDAO.executeUpdate(sqlFour, new Object[] {});
			return true;	
		}catch(Exception e){
			
		};
		return false;
	}
	//管理员入库表
	public List<Map<String, Object>> inBookList() {
		// TODO Auto-generated method stub
		 try{
				
				String sql = "select * from  inbook left join book on  inbook.bookId = book.id";
				List<Map<String, Object>> inbookList = this.commonDAO.excuteQuery(
						sql, null);
				return inbookList;
			   } catch (Exception e) {
				e.printStackTrace();
				System.out.println("操作数据库出错");
			   }

		return null;
	}
	
	
}
