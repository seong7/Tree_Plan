package teamProject_Member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeamProject_MySQLConnection {
    private String _driver = "org.gjt.mm.mysql.Driver",
    _url = "jdbc:mysql://127.0.0.1:3306/teamproject?useUnicode=true&characterEncoding=EUC_KR",
    _user = "root",
    _password = "1234";
    Connection con = null;  // db�� java program�� ������ �����ϴ� ��ü 
							// �� ������ ���ῡ�� input �� output (IO) �� ��θ� ������ ����
    
    public TeamProject_MySQLConnection() {
    	try {
			Class.forName(_driver);
			con = DriverManager.getConnection(_url,_user,_password);
			System.out.println("���Ἲ��");
		} catch (Exception e) {
			System.out.println("�������");
			e.printStackTrace();
		}
    }
  
    
    //SELECT ��
    private void select() {
    	try {
			String sql = "select * from users";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();//�������� sql�� ����
			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String photo = rs.getString("photo");
				String address = rs.getString("address");
				String email = rs.getString("email");
				int admin = rs.getInt("admin");
				String pwdQ = rs.getString("pwdQ");
				String pwdA = rs.getString("pwdA");
				System.out.println(id+"\t"+pwd+"\t"+name+"\t"+photo+"\t"+address +"\t" +email + "\t" +admin+"\t"+ pwdQ + "\t" + pwdA+"\t");
			}
			System.out.println("select ����");
		} catch (Exception e) {
			System.out.println("select ����");
			e.printStackTrace();
		}
    }
    
    
    public void insert(String id, String pwd,String NAME, String photo,String address, String email, int admin,String pwdQ,String pwdA ) {
 	   try {
 		String sql = "insert users(id, pwd, NAME, photo, address, email, admin, pwdQ, pwdA)"+"values(?,?,?,?,?,?,?,?,?)";
 		PreparedStatement pstmt = null;
 		pstmt = con.prepareStatement(sql);
 		pstmt.setString(1, id);
 		pstmt.setString(2, pwd);
 		pstmt.setString(3, NAME);
 		pstmt.setString(4, photo);
 		pstmt.setString(5, address);
 		pstmt.setString(6, email);
 		pstmt.setInt(7, admin);
 		pstmt.setString(8, pwdQ);
 		pstmt.setString(9, pwdA);

 		int cnt = pstmt.executeUpdate();
 		System.out.println("cnt: "+cnt);
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
    }
     public static void main (String[] args) {              
     	TeamProject_MySQLConnection mc = new TeamProject_MySQLConnection();
     	//mc.insert("wwmt", "1284", "������ȣ", "xx", "Japan", "smmddjc@naver.com", 2, "ee", "ee_e");
     	//mc.select();
    	 SignUp cc = new SignUp();
    	 cc.arrange(0,0);
     	}
     }