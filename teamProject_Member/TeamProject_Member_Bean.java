package teamProject_Member;

public class TeamProject_Member_Bean {
	private String id;
	private String pwd;
	private String name;
	private String photo;
	private String address;
	private String email;
	private int admin;
	private String pwdQ;
	private String pwdA;
	
	
	// id 메소드
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	// pwd 메소드
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	// name 메소드
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// photo 메소드
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	// address 메소드
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	// email 메소드
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// admin 메소드
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	// pwdQ 메소드
	public String getPwdQ() {
		return pwdQ;
	}
	public void setPwdQ(String pwdQ) {
		this.pwdQ = pwdQ;
	}
	
	// pwdA 메소드
	public String getPwdA() {
		return pwdA;
	}
	public void setPwdA(String pwdA) {
		this.pwdA = pwdA;
	}
}
