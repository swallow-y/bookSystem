package entity;

public class Customer {
     String userName;
     String password;
     String tel;
     String email;
     public Customer(){
    	 
     }
      public Customer(String userName,String password,String tel){
    	 this.userName=userName;
    	 this.password = password;
    	 this.tel = tel;
     }
     
     public Customer(String userName,String password,String tel,String email){
    	 this.userName = userName;
    	 this.password = password;
    	 this.tel = tel;
    	 this.email = email;
     }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
     
}
