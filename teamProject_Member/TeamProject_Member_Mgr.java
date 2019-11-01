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
		pool = TeamProject_DBConnectionMgr.getInstance(); // ���� �Ŵ��� ��ü ����
	}

	
//////////////////    �α���    (Select)  //////////
	public String logIn(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String pwd = new String();
		try {
			con = pool.getConnection(); // pool ���� Connection ������.
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
	
	
	
	
/////////////  ���̵� �ߺ�Ȯ��__ȸ������    (Select)  /////
	public Vector<String> idCheck() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<String> idList = new Vector<String>();
		try {
			con = pool.getConnection(); // pool ���� Connection ������.
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
//		System.out.println("(vector) idList�� size :  " + idList.size());
		return idList;
	}

///////////// ���̵� ã��   (Select)  /////
	public String idSearch(String name, String email) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String id = new String();
		try {
			con = pool.getConnection(); // pool ���� Connection ������.
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

///////////// ��й�ȣ ã��    (Select)  /////
	public String pwdSearch(String id, String email, String pwdQ, String pwdA) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String pwd = new String();
		try {
			con = pool.getConnection(); // pool ���� Connection ������.
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

////////////  ȸ�� ���� ��ȸ_������ ���    (Select)   ///////
	public Vector<TeamProject_Member_Bean> listMember(String mode, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Member_Bean> vlist = new Vector<TeamProject_Member_Bean>();
		try {
			con = pool.getConnection(); // pool���� Connection �����´�.
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
				bean.setId(rs.getString(1)); // rs�� column �� bean�� field�� �����ϴµ� 'column' ���� �ڸ� ���� �־ �ǰ� String �� �� �־
												// ��
				bean.setPwd(rs.getString(2));
				bean.setName(rs.getString(3));
				bean.setPhoto(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setEmail(rs.getString(6));
				bean.setAdmin(rs.getInt(7));
				bean.setPwdQ(rs.getString(8));
				bean.setPwdA(rs.getString(9));
				vlist.addElement(bean); // vector�� bean ��ü�� �ּҰ��� �ϳ��� �־��ش�. (�������� ���Ƶ� ���� ����)
//				System.out.println("vlist size  :  " + vlist.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ȸ������ ��ȸ�� �����߽��ϴ�. �ٽ� �õ����ּ���.", "fail",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist; // global variable Vector �� ���Ͻ�Ŵ
	}

///////////   ȸ�� ����     (Insert)   /////////////
	public boolean insertMember(TeamProject_Member_Bean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false; // flag �� '�����' �̶�� ��
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
			JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����߽��ϴ�. �ٽ� �õ��Ͽ��ּ���.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

/////////// ȸ�� ���� ����    (Update)   /////////////
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
			JOptionPane.showMessageDialog(null, "������ �����߽��ϴ�. �ٽ� �õ��Ͽ��ּ���.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

	
///////////// ������ ��й�ȣ �� _ ȸ�� Ż�� ��ų ��  (Select)  /////
	public String getPwd(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String pwd = new String();
		try {
			con = pool.getConnection(); // pool ���� Connection ������.
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
	
	
///////////  ȸ�� Ż��    (Delete)   /////////////
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
			JOptionPane.showMessageDialog(null, "ȸ�� Ż�� �����߽��ϴ�. �ٽ� �õ����ּ���.", "fail",
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
