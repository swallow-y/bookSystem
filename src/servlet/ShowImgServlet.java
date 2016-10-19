package servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowImgServlet
 */
@WebServlet("/ShowImgServlet")
public class ShowImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowImgServlet() {
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
		response.setContentType("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		/*	if( !=null){
			
		}
*/
//		String iString  = request.getParameter("imgSrc").getB??yt?es("ISO8859-1");
		String imgUrl = new String(request.getParameter("imgSrc").getBytes("ISO8859-1"),"utf-8");
		
		File checkFile = new File(imgUrl);
		if(checkFile.isFile() == false)
		{
			File imgFile = new File(imgUrl);
			try {
				showImgToFront(response,imgFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				showImgToFront(response,checkFile);
				return ; 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void showImgToFront(HttpServletResponse response,File imgFile) throws Exception
	{
		System.out.println("    ͼƬ    ·    "+imgFile.getAbsolutePath());
		FileInputStream fin = new FileInputStream(imgFile);
		BufferedInputStream bufferedIn = new BufferedInputStream(fin);
		int len = 1024;
		byte [] bytes = new byte[len];
		int size=0;
		
		OutputStream outputStream = response.getOutputStream();
		while((size=bufferedIn.read(bytes))>=0)
		{
			outputStream.write(bytes, 0, size);
		}
		
		System.out.println(outputStream.toString());
		
		bufferedIn.close();
		outputStream.flush();
		outputStream.close();
	}
}

