package teamProject_Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JOptionPane;

import teamProject_DBConnectionMgr.TeamProject_DBConnectionMgr;

public class TeamProject_Member_Mgr {

	TeamProject_DBConnectionMgr pool;

	public TeamProject_Member_Mgr() {
		pool = TeamProject_DBConnectionMgr.getInstance(); // 연결 매니저 객체 생성
	}

	
//////////////////    로그인    (Select)  //////////
	public String logIn(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String pwd = new String();
		try {
			con = pool.getConnection(); // pool 에서 Connection 빌려옴.
			sql = "select pwd from users where id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pwd = (rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
//		System.out.println(pwd);
		return pwd;
	}
	
	
	
	
/////////////  아이디 중복확인__회원가입    (Select)  /////
	public Vector<String> idCheck() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<String> idList = new Vector<String>();
		try {
			con = pool.getConnection(); // pool 에서 Connection 빌려옴.
			sql = "select id from users";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				idList.addElement(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
//		System.out.println("(vector) idList의 size :  " + idList.size());
		return idList;
	}

///////////// 아이디 찾기   (Select)  /////
	public String idSearch(String name, String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String id = new String();
		try {
			con = pool.getConnection(); // pool 에서 Connection 빌려옴.
			sql = "select id from users where name = ? and email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = (rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return id;
	}

///////////// 비밀번호 찾기    (Select)  /////
	public String pwdSearch(String id, String email, String pwdQ, String pwdA) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String pwd = new String();
		try {
			con = pool.getConnection(); // pool 에서 Connection 빌려옴.
			sql = "select pwd from users where id = ? and email = ? and pwdQ = ? and pwdA = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.setString(3, pwdQ);
			pstmt.setString(4, pwdA);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pwd = rs.getString("pwd");
			}
//			System.out.println(pwd.trim().length());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return pwd;
	}

////////////  회원 정보 조회_관리자 모드    (Select)   ///////
	public Vector<TeamProject_Member_Bean> listMember(String mode, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Member_Bean> vlist = new Vector<TeamProject_Member_Bean>();
		try {
			con = pool.getConnection(); // pool에서 Connection 빌려온다.
			if(mode == "Modification") {
				sql = "select * from users where id = ?";
			}else if ( mode == "MemAdmin") {
				sql = "select * from users";
			}
			pstmt = con.prepareStatement(sql);
			if(mode == "Modification") {
				pstmt.setString(1, id);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TeamProject_Member_Bean bean = new TeamProject_Member_Bean();
				bean.setId(rs.getString(1)); // rs의 column 과 bean의 field를 연결하는데 'column' 열의 자리 수를 넣어도 되고 String 명 을 넣어도
												// 됨
				bean.setPwd(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setPhoto(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setEmail(rs.getString(6));
				bean.setAdmin(rs.getInt(7));
				bean.setPwdQ(rs.getString(8));
				bean.setPwdA(rs.getString(9));
				vlist.addElement(bean); // vector에 bean 객체의 주소값을 하나씩 넣어준다. (변수명이 같아도 구분 가능)
//				System.out.println("vlist size  :  " + vlist.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "회원정보 조회에 실패했습니다. 다시 시도해주세요.", "fail",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist; // global variable Vector 를 리턴시킴
	}

///////////   회원 가입     (Insert)   /////////////
	public boolean insertMember(TeamProject_Member_Bean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false; // flag 는 '제어권' 이라는 뜻
		try {
			con = pool.getConnection();
			sql = "insert into users(id, pwd, name, photo, address, email, admin, pwdQ, pwdA) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPwd());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getPhoto());
			pstmt.setString(5, bean.getAddress());
			pstmt.setString(6, bean.getEmail());
			pstmt.setInt(7, bean.getAdmin());
			pstmt.setString(8, bean.getPwdQ());
			pstmt.setString(9, bean.getPwdA());
			int cnt = pstmt.executeUpdate();
			if (cnt == 1)
				flag = true;
//			System.out.println("insert :   " + flag);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "회원가입에 실패했습니다. 다시 시도하여주세요.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

/////////// 회원 정보 수정    (Update)   /////////////
	public boolean updateMember(TeamProject_Member_Bean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update users set id=?, pwd = ?, name=?, photo=?, address = ?, email=?, admin=?, pwdQ = ?, pwdA=? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPwd());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getPhoto());
			pstmt.setString(5, bean.getAddress());
			pstmt.setString(6, bean.getEmail());
			pstmt.setInt(7, bean.getAdmin());
			pstmt.setString(8, bean.getPwdQ());
			pstmt.setString(9, bean.getPwdA());
			pstmt.setString(10, bean.getId());
			int cnt = pstmt.executeUpdate();
			if (cnt == 1)
				flag = true;
//			System.out.println("update :   " + flag);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "수정에 실패했습니다. 다시 시도하여주세요.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

	
///////////// 관리자 비밀번호 비교 _ 회원 탈퇴 시킬 때  (Select)  /////
	public String getPwd(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String pwd = new String();
		try {
			con = pool.getConnection(); // pool 에서 Connection 빌려옴.
			sql = "select pwd from users where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pwd = rs.getString("pwd");
			}
//			System.out.println("pwd : " + pwd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return pwd;
	}
	
	
///////////  회원 탈퇴    (Delete)   /////////////
	public boolean deleteMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from users where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int cnt = pstmt.executeUpdate();
			if (cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "회원 탈퇴에 실패했습니다. 다시 시도해주세요.", "fail",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

	public static void main(String[] args) {
		new TeamProject_Member_Mgr();
	}
}
