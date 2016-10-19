package entity;

public class Book {
   
	int bookISN;
	int bookStore;
	int bookPrice;
	String bookName;
	String bookType;
	String bookImg;
	String bookDescript;
	String author;
	public Book(){
		
	}
	public Book(int bookISN,int bookStore,int bookPrice,String bookName,String bookType){
		this.bookISN=bookISN;
		this.bookStore=bookStore;
		this.bookPrice=bookPrice;
		this.bookName=bookName;
		this.bookType=bookType;
	}
	public Book(int bookISN,int bookStore,int bookPrice,String author,String bookName,String bookType,String bookImg,String bookDescript){
		this.bookISN=bookISN;
		this.bookStore=bookStore;
		this.bookPrice=bookPrice;
		this.bookName=bookName;
		this.bookType=bookType;
		this.bookImg=bookImg;
		this.bookDescript =bookDescript;
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getBookISN() {
		return bookISN;
	}

	public void setBookISN(int bookISN) {
		this.bookISN = bookISN;
	}

	public String getBookDescript() {
		return bookDescript;
	}
	public void setBookDescript(String bookDescript) {
		this.bookDescript = bookDescript;
	}
	public int getBookStore() {
		return bookStore;
	}

	public void setBookStore(int bookStore) {
		this.bookStore = bookStore;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}
	
}
