package teamProject_Manager_QnA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JOptionPane;

import teamProject_DBConnectionMgr.TeamProject_DBConnectionMgr;
import teamProject_Projects.TeamProject_Projects_Bean;

public class Manage_Qna_Mgr {

	TeamProject_DBConnectionMgr pool;

	public Manage_Qna_Mgr() {
		pool = TeamProject_DBConnectionMgr.getInstance(); // ���� �Ŵ��� ��ü ����
	}

///////////   user QnA �Խñ� ���� (Insert)   /////////////
	public boolean createQnA(Manage_Qna_Bean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false; // flag �� '�����' �̶�� ��
		try {
			con = pool.getConnection();
			sql = "insert into qna(id, title, question, qDate) values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getQuestion());
			pstmt.setString(4, bean.getQDate());

			int cnt = pstmt.executeUpdate();
			if (cnt == 1)
				flag = true;
			//System.out.println("insert :   " + flag);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "���� ���ۿ� �����߽��ϴ�. �ٽ� �õ����ּ���.", "fail",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

/////////////  QnA�� DB���� �����ͼ� ���̺� �߰�    _ user & �����ڸ��  (Select)  /////
	public Vector<Manage_Qna_Bean> QnaList(String mode, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Manage_Qna_Bean> QnaList = new Vector<Manage_Qna_Bean>();
		try {
			con = pool.getConnection(); // pool ���� Connection ������.
			switch (mode) {
			case ("userQnA"):
				sql = "select idx, title, id, qDate, answer from qna WHERE id = ?  Order by answer ASC";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				break;
			case ("QnaAdmin"):
				sql = "select idx, title, id, qDate, answer from qna  Order by answer ASC";
				pstmt = con.prepareStatement(sql);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {// ���� ������ ���� ������
				Manage_Qna_Bean bean = new Manage_Qna_Bean();
				bean.setIdx(rs.getInt("idx"));
				bean.setId(rs.getString("id")); // DB�� id ���� �����´�.
				bean.setTitle(rs.getString("title"));
				bean.setQDate(rs.getString("qDate"));
				if (rs.getString("answer") == null) {
					bean.setAnswer("�̿Ϸ�");
				} else {
					bean.setAnswer("�Ϸ�");
				}
				QnaList.addElement(bean);
			}
			if(QnaList.size()==0) {
				Manage_Qna_Bean bean = new Manage_Qna_Bean();
				bean.setTitle("   ������ �����ϴ�.");
				QnaList.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
//		System.out.println("(vector) qna List�� size :  " + QnaList.size());
		return QnaList;
	}

/////////////  QnA answer �� ��������   (Select)  /////
	public Manage_Qna_Bean getQnaAnswer(int qna_Idx) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Manage_Qna_Bean bean = new Manage_Qna_Bean();
		try {
			con = pool.getConnection(); // pool ���� Connection ������.
			sql = "select id, title, question, answer from qna WHERE idx = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_Idx);
			rs = pstmt.executeQuery();
			while (rs.next()) { // ���� ������ ���� ������
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("title"));
				bean.setQuestion(rs.getString("question"));
				if (rs.getString("answer") == null) {
					bean.setAnswer("�亯 ����");
				} else {
					bean.setAnswer(rs.getString("answer"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
//		System.out.println("QnA Answer");
		return bean;
	}


///////////   QnA answer ���� (Update)  /////////////
	public boolean updateQna(Manage_Qna_Bean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false; // flag �� '�����' �̶�� ��
		try {
			con = pool.getConnection();
			sql = "update qna set answer=?, aDate=? where idx = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getAnswer());
			pstmt.setString(2, bean.getaDate());
			pstmt.setInt(3, bean.getIdx());
			int cnt = pstmt.executeUpdate();
			if (cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "�亯 ���޿� �����߽��ϴ�. �ٽ� �õ��Ͽ��ּ���.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}


	public static void main(String[] args) {
		new Manage_Qna_Mgr();
	}
}
