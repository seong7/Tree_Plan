package teamProject_Projects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

import teamProject_DBConnectionMgr.TeamProject_DBConnectionMgr;

public class TeamProject_Projects_Mgr {

	TeamProject_DBConnectionMgr pool;
	
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH) + 1;
	int day = now.get(Calendar.DAY_OF_MONTH);
	int hour = now.get(Calendar.HOUR_OF_DAY);
	int min = now.get(Calendar.MINUTE);
	String time = hour + ":" + min;
	String today = year + "-" + month + "-" + day;
	
	int dayOfW = now.get(Calendar.DAY_OF_WEEK);
	String dayOfWeek = "";

	public TeamProject_Projects_Mgr() {
		pool = TeamProject_DBConnectionMgr.getInstance(); // ���� �Ŵ��� ��ü ����
	
		switch (dayOfW) {
		case 1:
			dayOfWeek = "��";
			break;
		case 2:
			dayOfWeek = "��";
			break;
		case 3:
			dayOfWeek = "ȭ";
			break;
		case 4:
			dayOfWeek = "��";
			break;	
		case 5:
			dayOfWeek = "��";
			break;
		case 6:
			dayOfWeek = "��";
			break;
		case 7:
			dayOfWeek = "��";
			break;		
		}
	
	}

///////////   ������Ʈ ���� (Insert)   /////////////
	public boolean insertProject(TeamProject_Projects_Bean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false; // flag �� '�����' �̶�� ��
		try {
			con = pool.getConnection();
			sql = "insert into projects(pjtName, creator, team, photo, startDate, endDate, numMax, numofppl, password) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getPjtName());
			pstmt.setString(2, bean.getCreator_id());
			pstmt.setInt(3, bean.getTeam());
			pstmt.setString(4, bean.getPhoto());
			pstmt.setString(5, bean.getStartDate());
			pstmt.setString(6, bean.getEndDate());
			pstmt.setInt(7, bean.getNumMax());
			pstmt.setInt(8, bean.getNumOfPpl());
			pstmt.setString(9, bean.getPassword());
//			pstmt.setString(10, bean.getHashtag());
			int cnt = pstmt.executeUpdate();
			if (cnt == 1)
				flag = true;
//			System.out.println("project insert :   " + flag);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "�� ��ǥ ������ �����߽��ϴ�. �ٽ� �õ��Ͽ� �ּ���.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

///////////   ������Ʈ �� ��� ��ܿ� �߰�  (Insert)   _ ������Ʈ ���� �� and �� ������Ʈ ���� �� /////////////
	public boolean insertProjectMember(TeamProject_Projects_Bean bean) {
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;
		PreparedStatement pstmt1 = null; 
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		int num = 0;
		String sql = null;
		boolean flag = false; // flag �� '�����' �̶�� ��
		try {
			con1 = pool.getConnection();
			sql = "SELECT numMax - numofppl FROM projects WHERE idx = ?";			// �ο� ���� üũ
			pstmt1 = con1.prepareStatement(sql);
			pstmt1.setInt(1, bean.getPjtIndex());
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				num = rs.getInt(1);
			}
			if (num <=0) {
				JOptionPane.showMessageDialog(null, "������ �ʰ��� �׷��Դϴ�.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
				return flag;
			} else {
				con2 = pool.getConnection();
				sql = "insert into pjtmembers(pjtIdx, id, joinDate) values(?, ?, ?)";
				pstmt2 = con2.prepareStatement(sql);
				pstmt2.setInt(1, bean.getPjtIndex());
				pstmt2.setString(2, bean.getMemId());
				pstmt2.setString(3, bean.getJoinDate());
				int cnt1 = pstmt2.executeUpdate();
				if (cnt1 == 1) {
					con3 = pool.getConnection();
					sql = "UPDATE projects SET numofppl = (SELECT numofppl+1 WHERE idx = ?) WHERE idx = ?";		// �ο� 1 ����
					pstmt3 = con3.prepareStatement(sql);
					pstmt3.setInt(1, bean.getPjtIndex());
					pstmt3.setInt(2, bean.getPjtIndex());
					int cnt2 = pstmt3.executeUpdate();
					if(cnt2 ==1) {
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "���Կ� �����߽��ϴ�. �ٽ� �õ��Ͽ� �ּ���.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			pool.freeConnection(con3, pstmt3);
			pool.freeConnection(con2, pstmt2);
		}
		return flag;
	}

///////////  ������Ʈ ���� ��õ �׸�  (Insert)   /////////////
	public boolean insertDetailPlans(TeamProject_Projects_Bean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false; // flag �� '�����' �̶�� ��
		try {
			con = pool.getConnection();
			sql = "insert into detailplan(pjtIdx, id, days, detail, onOff)values(?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bean.getPjtIndex());
			pstmt.setString(2, bean.getMemId());
			pstmt.setString(3, bean.getDays());
			pstmt.setString(4, bean.getDetailPlan());
			pstmt.setInt(5, bean.getDetailOnOff());
			int cnt = pstmt.executeUpdate();
			if (cnt == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

////////��� insert �� ������Ʈ�� index (�⺻Ű) �� ����   (select)  /// 	
	public int getProjectIndex() {
		int projectIndex = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "select max(idx) from projects";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				projectIndex = rs.getInt("max(idx)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return projectIndex;
	}

///////////  ������Ʈ ���� ��õ �׸�  (select)   /////////////
public Vector<TeamProject_Projects_Bean> showPjtDetail(int projectIndex, String id) {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	Vector<TeamProject_Projects_Bean> PjtDetailList = new Vector<TeamProject_Projects_Bean>();
	try {
		con = pool.getConnection();
		sql = "SELECT days, detail, onOff FROM detailplan WHERE pjtIdx = ? AND id = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, projectIndex);
		pstmt.setString(2, id);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
			bean.setDays(rs.getString("days"));
			bean.setDetailPlan(rs.getString("detail"));
			bean.setDetailOnOff(rs.getInt("onOff"));
			PjtDetailList.addElement(bean);
		}
//		System.out.println("pjt Detail  :   " + PjtDetailList.size() );
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		pool.freeConnection(con, pstmt);
	}
	return PjtDetailList;
}
	

/////////// ������ ID ��   �� ��ǥ ���� ���� üũ  (select) /////////
public boolean isIDinPjt(int projectIndex, String id) {
	boolean flag = false;
	int count = 0;
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs  = null;
	String sql = null;
	try {
		con = pool.getConnection();
		sql = "SELECT count(id) FROM pjtmembers WHERE id = ? AND pjtIdx = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setInt(2, projectIndex);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			count = rs.getInt(1);
		}
//		System.out.println("is " + id + " in  Pjt ?  :    [ " +  count + " ]  of same ID found .... !! ");
	} catch (Exception e) {
		e.printStackTrace();
	}
	if(count == 1)
		flag = true;
		return flag;
	}

//////////// ������ ID �� �� ��ǥ ������ ���� üũ (SELECT) ///////////
	public boolean isIdPjtCreator(int projectIndex, String id) {
		boolean flag = false;
		String creator = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			con = pool.getConnection();
			sql = "SELECT creator FROM projects WHERE idx = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectIndex);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				creator = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (id.equals(creator))
			flag = true;
		return flag;
	}
	
	
///////////// ���� Project �� DB���� �����ͼ� ���̺� �߰�  --- Ȩȭ��   (Select)  /////
	public Vector<TeamProject_Projects_Bean> PersonalProjectList(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> ProjectList = new Vector<TeamProject_Projects_Bean>();
		try {
			con = pool.getConnection(); // pool ���� Connection ������.
			sql = "select idx, photo, pjtName, joinDate, endDate from projects JOIN pjtmembers ON (projects.idx = pjtmembers.pjtIdx) WHERE id = ? and team = 0 ORDER BY idx ASC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// ���� ������ ���� ������
				TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
				bean.setPjtIndex(rs.getInt("idx"));
				bean.setPhoto(rs.getString("photo"));
				bean.setPjtName(rs.getString("pjtName"));
				bean.setJoinDate(rs.getString("joinDate"));
				bean.setEndDate(rs.getString("endDate"));
				ProjectList.addElement(bean);
			}
			if(ProjectList.size()==0) {
				TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
				bean.setPjtIndex(1);
				bean.setPjtName("���� ��ǥ�� �߰��ϼ���.");
				
				ProjectList.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
//		System.out.println("(vector) ���� ��ǥ List�� size :  " + ProjectList.size());
		return ProjectList;
	}

	
/////////////  �� Project �� DB���� �����ͼ� ���̺� �߰�  --- Ȩȭ��   (Select)   /////
	public Vector<TeamProject_Projects_Bean> teamProjectList(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> ProjectList = new Vector<TeamProject_Projects_Bean>();
		try {
			con = pool.getConnection(); // pool ���� Connection ������.
			sql = "select idx, photo, pjtName, joinDate, endDate from projects JOIN pjtmembers ON (projects.idx = pjtmembers.pjtIdx) WHERE id = ? and team = 1 ORDER BY idx ASC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// ���� ������ ���� ������
				TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
				bean.setPjtIndex(rs.getInt("idx"));
				bean.setPhoto(rs.getString("photo"));
				bean.setPjtName(rs.getString("pjtName"));
				bean.setJoinDate(rs.getString("joinDate"));
				bean.setEndDate(rs.getString("endDate"));
				ProjectList.addElement(bean);
			}
			if(ProjectList.size()==0) {
				TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
				bean.setPjtIndex(1);
				bean.setPjtName("�� ��ǥ�� �����ϰų� ������ �� ��ǥ�� �����ϼ���.");
				
				ProjectList.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
//		System.out.println("(vector) �� ��ǥ List�� size :  " + ProjectList.size());
		return ProjectList;
	}
	
	
	
	
	
//////////// �� Ȩȭ�� �޼��� ���� ������   (Select)   ///////
	
	public int getCompleteRate(int projectIdx) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
//		TeamProject_ProjectsBean_��ǥ bean = new TeamProject_ProjectsBean_��ǥ();
		int completeRate = 0;
		try {
			con = pool.getConnection(); // pool���� Connection �����´�.
			sql = "SELECT (SELECT COUNT(id) FROM complete WHERE pjtidx = ? AND DATE = ?) / (SELECT COUNT(id) FROM detailplan WHERE pjtidx = ? AND DAYs = ? AND onOff = 1)";
			pstmt = con.prepareStatement(sql);	
			pstmt.setInt(1, projectIdx);
			pstmt.setString(2,year+"-"+month+"-"+day);
			pstmt.setInt(3, projectIdx);
			pstmt.setString(4, dayOfWeek);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				completeRate = (int)(rs.getDouble(1)*100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return completeRate;
	}

////////////�� Ȩȭ�� ���� ������   (Select)   ///////

	public Vector<TeamProject_Projects_Bean> showProject(int projectIdx) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> vlist = new Vector();
		try {
			con = pool.getConnection(); // pool���� Connection �����´�.
			sql = "select * from projects where idx = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectIdx);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
				bean.setPjtName(rs.getString("pjtName"));
				bean.setStartDate(rs.getString("startDate"));
				bean.setPhoto(rs.getString("photo"));
				bean.setNumOfPpl(rs.getInt("numofppl"));
				bean.setNumMax(rs.getInt("numMax"));
				bean.setEndDate(rs.getString("endDate"));
				if(rs.getString("password") == null) {
					bean.setPassword("");
				}else {
					bean.setPassword(rs.getString("password"));
				}
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

////////////�� Ȩȭ�� complete list ������   (Select)   ///////

	public Vector<TeamProject_Projects_Bean> showCompleteList(int pjtIdx, String today) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> vlist = new Vector();
		int i = 0;
		try {
			con = pool.getConnection(); // pool���� Connection �����´�.
			sql = "SELECT complete.id, detail, joinDate, time from complete JOIN pjtmembers ON(complete.pjtIdx = pjtmembers.pjtIdx AND complete.id = pjtmembers.id) WHERE complete.pjtIdx = ? and date = ? ORDER BY TIME asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pjtIdx);
			pstmt.setString(2, today);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
				bean.setPhoto(photoSearch(rs.getString("id")));
				bean.setMemId(rs.getString("id"));
				bean.setDetailPlan(rs.getString("detail"));
				bean.setJoinDate(rs.getString("joinDate"));
				bean.setCompleteTime(rs.getString("Time"));
				vlist.addElement(bean);
			}
			if(vlist.size()==0) {
				TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
				//bean.setPjtIndex(1);
				bean.setDetailPlan("���� �Ϸ��� ������ ���� �����ϴ�.");
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	/////////////// ���� �˻�///
	public String photoSearch(String id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String photo = "";
		try {
			con = pool.getConnection(); // pool���� Connection �����´�.
			sql = "SELECT photo from users where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
			while (rs.next()) {
				photo = rs.getString("photo");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return photo;
	}
	
	
/////////////  �� Project �˻�ȭ��   --- (Select)   /////
	public Vector<TeamProject_Projects_Bean> teamProjectSearchList(String id, StringTokenizer searchTerm) {
		String search_sql = "";
		
		while(searchTerm.hasMoreTokens()) {
			search_sql += " Or pjtName Like '%" + searchTerm.nextToken() + "%'"; 
		}
		if(search_sql.length() != 0) {
			search_sql = search_sql.substring(4);
		} else if(search_sql.length() == 0) {
			search_sql = "pjtName Like '%%'";
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> ProjectList = new Vector<TeamProject_Projects_Bean>();
		try {
			con = pool.getConnection(); // pool ���� Connection ������.
			sql = "SELECT * FROM projects WHERE team = 1 AND ("+ search_sql +") ORDER BY idx";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {// ���� ������ ���� ������
				TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
				bean.setPjtIndex(rs.getInt("idx"));
				bean.setPhoto(rs.getString("photo"));
				bean.setPjtName(rs.getString("pjtName"));
				bean.setPassword(rs.getString("password"));
				bean.setStartDate(rs.getString("startdate"));
				bean.setNumOfPpl(rs.getInt("numOfPpl"));
				bean.setNumMax(rs.getInt("numMax"));
				bean.setEndDate(rs.getString("endDate"));
				ProjectList.addElement(bean);
			}
			if(ProjectList.size()==0) {
				TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
				bean.setPjtIndex(1);
				bean.setPjtName("�˻� ����� �����ϴ�.");
				
				ProjectList.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
//		System.out.println("(vector) �� ��ǥ List�� size :  " + ProjectList.size());
		return ProjectList;
	}
	
	
//////////// todo list ������   (Select)   ///////

	public Vector<TeamProject_Projects_Bean> showTodoList(int pjtIdx, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> vlist = new Vector();
		int i = 0;
		try {
			con = pool.getConnection(); // pool���� Connection �����´�.
			sql = "SELECT pjtidx, detail FROM detailplan WHERE pjtidx = ? AND id = ? AND ONOFF = 1 AND days IN (? , '����')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pjtIdx);
			pstmt.setString(2, id);
			pstmt.setString(3, dayOfWeek);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
//				System.out.println(" rs ����?  " + i++);
				TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
				bean.setPjtIndex(rs.getInt("pjtidx"));
				bean.setDetailPlan(rs.getString("detail"));
				bean.setCompletion(completeSearch(pjtIdx, id, dayOfWeek, today));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

/////////////// �Ϸ� ���� �˻�///
	public String completeSearch(int pjtIdx, String id, String daysOfWeek, String today) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String completion = "";
		try {
			con = pool.getConnection(); // pool���� Connection �����´�.
			sql = "SELECT count(detail) from complete where pjtIdx = ? and id = ? and days = ? and date = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pjtIdx);
			pstmt.setString(2, id);
			pstmt.setString(3, daysOfWeek);
			pstmt.setString(4, today);
			rs = pstmt.executeQuery();
			TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
			while (rs.next()) {
				switch (rs.getInt("count(detail)")) {
				case 0:
					completion = "�̿Ϸ�";
					break;
				default:
					completion = "�Ϸ�";
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return completion;
	}

///////////  �� ������Ʈ �ϷḮ��Ʈ �߰�    (Insert)   /////////////
	public boolean insertComplete(int pjtIdx, String id, String detail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false; // flag �� '�����' �̶�� ��
		try {
			con = pool.getConnection();
			sql = "insert into complete(pjtIdx, id, days, date, detail, time)values(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pjtIdx);
			pstmt.setString(2, id);
			pstmt.setString(3, dayOfWeek);
			pstmt.setString(4, today);
			pstmt.setString(5, detail);
			pstmt.setString(6, time);
			int cnt = pstmt.executeUpdate();
			if (cnt == 1)
				flag = true;
//		System.out.println("complete insert :   " + flag);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�Ϸ� üũ�� �����߽��ϴ�. �ٽ� �õ��Ͽ��ּ���.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

/////////  �� ������Ʈ �ϷḮ��Ʈ ��� (DELETE) /////////
	public boolean deleteComplete(int pjtIdx, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "DELETE FROM complete WHERE pjtIdx = ? AND id = ? AND days = ? AND date =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pjtIdx);
			pstmt.setString(2, id);
			pstmt.setString(3, dayOfWeek);
			pstmt.setString(4, today);
			int cnt = pstmt.executeUpdate();
			if(cnt == 1)
				flag = true;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "�Ϸ� ��ҿ� �����߽��ϴ�. �ٽ� �õ��Ͽ��ּ���.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

/////////// ���� ��õ���� ����    (Update)   /////////////
	public boolean updateDetailPlans(TeamProject_Projects_Bean bean) {
		Connection con1 = null;
		Connection con2 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		int newOrOld = 0;
		String sql = null;
		boolean flag = false;
		try {
			con1 = pool.getConnection();
			sql = "SELECT count(days) FROM detailplan WHERE pjtIdx = ? AND id = ? AND days = ?"; // �̹� ������� ���� ��õ���� �ִ���
																									// Ȯ��
			pstmt1 = con1.prepareStatement(sql);
			pstmt1.setInt(1, bean.getPjtIndex());
			pstmt1.setString(2, bean.getMemId());
			pstmt1.setString(3, bean.getDays());
			rs = pstmt1.executeQuery();
			while (rs.next()) {
				newOrOld = rs.getInt(1);
			}
			if (newOrOld == 0) {
				flag = insertDetailPlans(bean); // ���� select ��� 0�̸� insert ����
				return flag;
			} else {
				con2 = pool.getConnection();
				sql = "update detailplan set days = ?, detail = ?, onOff = ? where pjtIdx = ? and id = ? and days =?"; // �̹�
																														// ����
																														// ��
																														// ������
																														// update
																														// ����
				pstmt2 = con2.prepareStatement(sql);
				pstmt2.setString(1, bean.getDays());
				pstmt2.setString(2, bean.getDetailPlan());
				pstmt2.setInt(3, bean.getDetailOnOff());
				pstmt2.setInt(4, bean.getPjtIndex());
				pstmt2.setString(5, bean.getMemId());
				pstmt2.setString(6, bean.getDays());
				int cnt = pstmt2.executeUpdate();
				if (cnt == 1) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con2, pstmt2);
		}
		return flag;
	}

///////////  �� ������Ʈ���� Ż��    (Delete)   /////////////
	public boolean leavePjt(int projectIndex, String id) {
		Connection con = null;
		Connection con1 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from pjtmembers where pjtIdx = ? and  id = ? "; // Ż��
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectIndex);
			pstmt.setString(2, id);
			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				con1 = pool.getConnection();
				sql = "UPDATE projects SET numofppl = (SELECT numofppl-1 WHERE idx = ?) WHERE idx = ?"; // �ο� 1 ����
				pstmt1 = con1.prepareStatement(sql);
				pstmt1.setInt(1, projectIndex);
				pstmt1.setInt(2, projectIndex);
				int cnt1 = pstmt1.executeUpdate();
				if(cnt1 ==1)
					flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con1,pstmt1);
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
///////////// ������Ʈ �⺻���� ���� (update) /////////////
	public boolean updatePjt(TeamProject_Projects_Bean bean) {
		Connection con1 = null;
		Connection con2 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = null;
		String creator = "";
		boolean flag = false;
		try {
			con1 = pool.getConnection();
			sql = "SELECT creator FROM projects WHERE idx = ?";
			pstmt1 = con1.prepareStatement(sql);
			pstmt1.setInt(1, bean.getPjtIndex());
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				creator = rs.getString(1);
			}
			if(creator.equals(bean.getMemId())) {
				con2 = pool.getConnection();
				sql = "UPDATE projects SET photo = ?, endDate = ?, numMax = ?, password = ? WHERE idx = ?";
				pstmt2 = con2.prepareStatement(sql);
				pstmt2.setString(1, bean.getPhoto());
				pstmt2.setString(2, bean.getEndDate());
				pstmt2.setInt(3, bean.getNumMax());
				pstmt2.setString(4, bean.getPassword());
				pstmt2.setInt(5, bean.getPjtIndex());
				int cnt = pstmt2.executeUpdate();
				if(cnt == 1)
					flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "������ �����߽��ϴ�. �ٽ� �õ����ּ���.", "fail",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con1, pstmt1);
			pool.freeConnection(con2, pstmt2);
		}
		return flag;
	}
	
/////////////////// ��ǥ ����  (DELETE) //////////////
	public boolean deletePjt(int projectIndex, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "DELETE FROM projects WHERE idx = ? AND creator = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectIndex);
			pstmt.setString(2, id);
			int count = pstmt.executeUpdate();
			if(count == 1) {
				flag = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
}
