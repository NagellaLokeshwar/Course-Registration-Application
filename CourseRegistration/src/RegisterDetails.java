import java.util.Scanner;

public class RegisterDetails {

    Scanner sc = new Scanner(System.in);
    public  String EmailID;
    public String Password;
    public String  User_Name;

    public String getUser_Name() {
		return User_Name;
	}
	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}
	public String getEmailID() {
        return EmailID;
    }
    public void setEmailID(String emailID) {
        EmailID = emailID;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public Scanner getSc() {
        return sc;
    }
    public void setSc(Scanner sc) {
        this.sc = sc;
    }



}

