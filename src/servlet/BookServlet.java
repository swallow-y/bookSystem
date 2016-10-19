package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import entity.Book;
import entity.Customer;
import entity.Order;
import service.BookService;
import sun.misc.BASE64Decoder;


@WebServlet("/AddNewBook")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
//		String userName = null;
		String method = request.getParameter("method");
		HttpSession session =request.getSession();
		
		BookService bookService = new BookService();
		//保存图片
		
		if ("saveBook".equals(method)||"updateBook".equals(method)) {
			String bookName = request.getParameter("bookName");
			int bookISN = Integer.parseInt(request.getParameter("bookISN"));
			int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
			int bookStore = Integer.parseInt(request.getParameter("bookStore"));
			String bookType = request.getParameter("bookTypeBox");
			String bookImg = request.getParameter("bookImg");
			String bookDescript = request.getParameter("bookDescript");
			String author = request.getParameter("author");
			if("updateBook".equals(method)&&(bookImg == null)){
					Book book = new Book(bookISN,bookStore,bookPrice,author,bookName,bookType,bookImg,bookDescript);
					
					String getKey = bookService.saveBook(book,method);
					out.write(getKey);
				
			}
			else{
				
			
			int x = bookImg .indexOf(",")+1;
			String reImgSrc = bookImg.substring(x);
			
			BASE64Decoder decoder = new BASE64Decoder();  
			 
				//Base64解码  
				byte[] b = decoder.decodeBuffer(reImgSrc);  
				for(int i=0;i<b.length;++i)  
				{  
					if(b[i]<0)  
					{//调整异常数据  
						b[i]+=256;  
					}  
				}  
				//生成jpeg图片  
//				Calendar now = Calendar.getInstance();  
				
				String imgFilePath = "D://angular//workspace//bookSystem//WebContent/image//"+System.currentTimeMillis()+".jpg";//新生成的图片
				Book book = new Book(bookISN,bookStore,bookPrice,author,bookName,bookType,imgFilePath,bookDescript);
				
				String getKey = bookService.saveBook(book,method);
				OutputStream output = new FileOutputStream(imgFilePath);      
				output.write(b);  
				output.flush();  
				output.close();  
			
			    out.write(getKey);
			}
		
	   }
        else if ("deleteBook".equals(method)) {
			int id = Integer.parseInt(request.getParameter("id"));

			bookService.deleteBook(id);
		}
		else if ("findBooks".equals(method)) {
		
			int pageNow = Integer.parseInt(request.getParameter("page"));
			List<Map<String, Object>> books = bookService
					.findBooks(pageNow);
			out.write(JSON.toJSONString(books));
		}
		
		
		else if ("findThisBook".equals(method)) {
			
			int id =Integer.parseInt( request.getParameter("id"));
			List<Map<String, Object>> thisBook = bookService
					.findThisBook(id);
			out.write(JSON.toJSONString(thisBook));
		}
       else if ("findBooksLength".equals(method)) {
			
			int bookLength = bookService.findBookLength();
			out.write(JSON.toJSONString(bookLength));
		}
		
       else if ("findByCondition".equals(method)) {
   		
    	   String condition = request.getParameter("condition");
    	   if("wy".equals(condition)){
    		   condition = "文艺";
    	   }
    	   else if("jg".equals(condition)){
    		   condition = "经管";
    	   }
    	   else if("sh".equals(condition)){
    		    condition ="生活";
    	   }
    	   else if("jy".equals(condition)){
    		   condition = "教育";
    	   }
    	   else if("kj".equals(condition)){
    		   condition = "科技";
    	   }
    	   else if("ts".equals(condition)){
    		   condition = "童书";
    	   }
			String inputValue = request.getParameter("inputValue");
			String page = request.getParameter("page");
			int pageNow;
			if("null".equals(page)){
				pageNow = 0;
			}
			else{
				pageNow = Integer.parseInt(page);
			}
			


			List<Map<String, Object>> books = bookService
					.findByCondition(condition, inputValue, pageNow);
			out.write(JSON.toJSONString(books));
		}
       else if ("serchBook".equals(method)) {
      		
    	   String condition = request.getParameter("condition");
    	  
			String inputValue = request.getParameter("inputValue");
			String page = request.getParameter("page");
			int pageNow;
			if("null".equals(page)){
				pageNow = 0;
			}
			else{
				pageNow = Integer.parseInt(page);
			}
			


			List<Map<String, Object>> books = bookService
					.serchBook(condition, inputValue, pageNow);
			out.write(JSON.toJSONString(books));
		}
       else if ("inBookList".equals(method)) {
     		
			List<Map<String, Object>> inBookList = bookService
					.inBookList();
			out.write(JSON.toJSONString(inBookList));
		}
       
		
       else if ("newUser".equals(method)) {
			String userTel = request.getParameter("userTel");
		    String userName = request.getParameter("userName");
			String password = request.getParameter("password");
            Customer customer = new Customer(userName,password,userTel);
            int getKey = bookService.saveUser(customer);
            if(getKey != 0){
            	session.setAttribute("LoginUser", customer.getUserName());
				session.setAttribute("LoginId",getKey);
	
            }
			out.write(JSON.toJSONString(getKey));
            
		}
       else if ("login".equals(method)) {
			
		    String userName = request.getParameter("userName");
			String password = request.getParameter("password");
            int login = bookService
					.login(userName,password);
			if(login != 0){
				Customer customer = new Customer();
				customer.setUserName(userName);
				session.setAttribute("LoginUser", customer.getUserName());
				session.setAttribute("LoginId",login);
			}
			out.write(JSON.toJSONString(login));
		}
       else if("showUser".equals(method)){
    	   String name =(String)request.getSession().getAttribute("LoginUser");
           System.out.println(name);
           out.write(name);
           out.flush();
           out.close();
    	   
       }
		
       else if("BuyBook".equals(method)){
    	   
			int id =Integer.parseInt( request.getParameter("bookId"));
			int bookSize =Integer.parseInt(request.getParameter("bookSize"));
			int buyNumber =Integer.parseInt( request.getParameter("buyNumber"));
			String userName =(String)request.getSession().getAttribute("LoginUser");
            double price = Double.parseDouble(request.getParameter("price")); 
            String bookName = request.getParameter("bookName");
			if(userName == null){
				out.write(JSON.toJSONString("false"));
			}
			int userId =(int)request.getSession().getAttribute("LoginId");

			boolean buyMessage = bookService
					.BuyBook(id,userName,userId,buyNumber,bookSize,price,bookName);
			out.write(JSON.toJSONString(buyMessage));
       }
		
		
		
       else if ("showOrderList".equals(method)) {
			String userName =(String)request.getSession().getAttribute("LoginUser");

			List<Map<String, Object>> myOrder = bookService
					.showOrderList(userName);
			out.write(JSON.toJSONString(myOrder));
	   }
       else if ("addToShoppingCart".equals(method)) {

			int bookid =Integer.parseInt( request.getParameter("bookId"));

			String userName =(String)request.getSession().getAttribute("LoginUser");

			if(userName == null){
				out.write(JSON.toJSONString("false"));
			}
			int userId =(int)request.getSession().getAttribute("LoginId");

			boolean addToShoppingCart = bookService
					.addToShoppingCart(bookid,userName,userId);
			out.write(JSON.toJSONString(addToShoppingCart));
	   }
	
	  else if ("showCartList".equals(method)) {
			String userName =(String)request.getSession().getAttribute("LoginUser");
			int userId =(int)request.getSession().getAttribute("LoginId");

			List<Map<String, Object>> myCart = bookService
					.showCartList(userName,userId);
			out.write(JSON.toJSONString(myCart));
	   }
	  else if ("allOrderList".equals(method)) {
		

			List<Map<String, Object>> allOrder = bookService
					.allOrderList();
			out.write(JSON.toJSONString(allOrder));
	   }
	  else if("BuyAllSelect".equals(method)){
   	   
			String userName =(String)request.getSession().getAttribute("LoginUser");
			System.out.print(userName);
			String list = request.getParameter("list");
			List<Order> orderList = Order.getEntity(list);
			System.out.print(orderList.get(0).getBookId());
			if(userName == null){
				out.write(JSON.toJSONString("false"));
			}
			int userId =(int)request.getSession().getAttribute("LoginId");

			boolean buyMessage = bookService.BuyAllSelect(userName,userId,orderList);
			out.write(JSON.toJSONString(buyMessage));
     }
}
}