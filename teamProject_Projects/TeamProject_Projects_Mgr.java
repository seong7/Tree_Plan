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
		pool = TeamProject_DBConnectionMgr.getInstance(); // 연결 매니저 객체 생성
	
		switch (dayOfW) {
		case 1:
			dayOfWeek = "일";
			break;
		case 2:
			dayOfWeek = "월";
			break;
		case 3:
			dayOfWeek = "화";
			break;
		case 4:
			dayOfWeek = "수";
			break;	
		case 5:
			dayOfWeek = "목";
			break;
		case 6:
			dayOfWeek = "금";
			break;
		case 7:
			dayOfWeek = "토";
			break;		
		}
	
	}

///////////   프로젝트 생성 (Insert)   /////////////
	public boolean insertProject(TeamProject_Projects_Bean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false; // flag 는 '제어권' 이라는 뜻
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
			JOptionPane.showMessageDialog(null, "팀 목표 생성에 실패했습니다. 다시 시도하여 주세요.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

///////////   프로젝트 별 멤버 명단에 추가  (Insert)   _ 프로젝트 생성 시 and 팀 프로젝트 가입 시 /////////////
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
		boolean flag = false; // flag 는 '제어권' 이라는 뜻
		try {
			con1 = pool.getConnection();
			sql = "SELECT numMax - numofppl FROM projects WHERE idx = ?";			// 인원 제한 체크
			pstmt1 = con1.prepareStatement(sql);
			pstmt1.setInt(1, bean.getPjtIndex());
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				num = rs.getInt(1);
			}
			if (num <=0) {
				JOptionPane.showMessageDialog(null, "정원이 초과된 그룹입니다.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
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
					sql = "UPDATE projects SET numofppl = (SELECT numofppl+1 WHERE idx = ?) WHERE idx = ?";		// 인원 1 더함
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
			JOptionPane.showMessageDialog(null, "가입에 실패했습니다. 다시 시도하여 주세요.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
			
		} finally {
			pool.freeConnection(con3, pstmt3);
			pool.freeConnection(con2, pstmt2);
		}
		return flag;
	}

///////////  프로젝트 세부 실천 항목  (Insert)   /////////////
	public boolean insertDetailPlans(TeamProject_Projects_Bean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false; // flag 는 '제어권' 이라는 뜻
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

////////방금 insert 한 프로젝트의 index (기본키) 값 추출   (select)  /// 	
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

///////////  프로젝트 세부 실천 항목  (select)   /////////////
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
	

/////////// 접속자 ID 의   팀 목표 가입 여부 체크  (select) /////////
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

//////////// 접속자 ID 의 팀 목표 생성자 여부 체크 (SELECT) ///////////
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
	
	
///////////// 개인 Project 값 DB에서 가져와서 테이블에 추가  --- 홈화면   (Select)  /////
	public Vector<TeamProject_Projects_Bean> PersonalProjectList(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> ProjectList = new Vector<TeamProject_Projects_Bean>();
		try {
			con = pool.getConnection(); // pool 에서 Connection 빌려옴.
			sql = "select idx, photo, pjtName, joinDate, endDate from projects JOIN pjtmembers ON (projects.idx = pjtmembers.pjtIdx) WHERE id = ? and team = 0 ORDER BY idx ASC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 다음 순서가 없을 때까지
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
				bean.setPjtName("개인 목표를 추가하세요.");
				
				ProjectList.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
//		System.out.println("(vector) 개인 목표 List의 size :  " + ProjectList.size());
		return ProjectList;
	}

	
/////////////  팀 Project 값 DB에서 가져와서 테이블에 추가  --- 홈화면   (Select)   /////
	public Vector<TeamProject_Projects_Bean> teamProjectList(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> ProjectList = new Vector<TeamProject_Projects_Bean>();
		try {
			con = pool.getConnection(); // pool 에서 Connection 빌려옴.
			sql = "select idx, photo, pjtName, joinDate, endDate from projects JOIN pjtmembers ON (projects.idx = pjtmembers.pjtIdx) WHERE id = ? and team = 1 ORDER BY idx ASC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 다음 순서가 없을 때까지
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
				bean.setPjtName("팀 목표를 생성하거나 기존의 팀 목표에 가입하세요.");
				
				ProjectList.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
//		System.out.println("(vector) 팀 목표 List의 size :  " + ProjectList.size());
		return ProjectList;
	}
	
	
	
	
	
//////////// 팀 홈화면 달성률 정보 빼오기   (Select)   ///////
	
	public int getCompleteRate(int projectIdx) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
//		TeamProject_ProjectsBean_목표 bean = new TeamProject_ProjectsBean_목표();
		int completeRate = 0;
		try {
			con = pool.getConnection(); // pool에서 Connection 빌려온다.
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

////////////팀 홈화면 정보 빼오기   (Select)   ///////

	public Vector<TeamProject_Projects_Bean> showProject(int projectIdx) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> vlist = new Vector();
		try {
			con = pool.getConnection(); // pool에서 Connection 빌려온다.
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

////////////팀 홈화면 complete list 빼오기   (Select)   ///////

	public Vector<TeamProject_Projects_Bean> showCompleteList(int pjtIdx, String today) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> vlist = new Vector();
		int i = 0;
		try {
			con = pool.getConnection(); // pool에서 Connection 빌려온다.
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
				bean.setDetailPlan("오늘 완료한 팀원이 아직 없습니다.");
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	/////////////// 사진 검색///
	public String photoSearch(String id) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String photo = "";
		try {
			con = pool.getConnection(); // pool에서 Connection 빌려온다.
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
	
	
/////////////  팀 Project 검색화면   --- (Select)   /////
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
			con = pool.getConnection(); // pool 에서 Connection 빌려옴.
			sql = "SELECT * FROM projects WHERE team = 1 AND ("+ search_sql +") ORDER BY idx";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {// 다음 순서가 없을 때까지
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
				bean.setPjtName("검색 결과가 없습니다.");
				
				ProjectList.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
//		System.out.println("(vector) 팀 목표 List의 size :  " + ProjectList.size());
		return ProjectList;
	}
	
	
//////////// todo list 빼오기   (Select)   ///////

	public Vector<TeamProject_Projects_Bean> showTodoList(int pjtIdx, String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<TeamProject_Projects_Bean> vlist = new Vector();
		int i = 0;
		try {
			con = pool.getConnection(); // pool에서 Connection 빌려온다.
			sql = "SELECT pjtidx, detail FROM detailplan WHERE pjtidx = ? AND id = ? AND ONOFF = 1 AND days IN (? , '매일')";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pjtIdx);
			pstmt.setString(2, id);
			pstmt.setString(3, dayOfWeek);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
//				System.out.println(" rs 도나?  " + i++);
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

/////////////// 완료 여부 검색///
	public String completeSearch(int pjtIdx, String id, String daysOfWeek, String today) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String completion = "";
		try {
			con = pool.getConnection(); // pool에서 Connection 빌려온다.
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
					completion = "미완료";
					break;
				default:
					completion = "완료";
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

///////////  팀 프로젝트 완료리스트 추가    (Insert)   /////////////
	public boolean insertComplete(int pjtIdx, String id, String detail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false; // flag 는 '제어권' 이라는 뜻
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
			JOptionPane.showMessageDialog(null, "완료 체크에 실패했습니다. 다시 시도하여주세요.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

/////////  팀 프로젝트 완료리스트 취소 (DELETE) /////////
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
			JOptionPane.showMessageDialog(null, "완료 취소에 실패했습니다. 다시 시도하여주세요.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

/////////// 세부 실천사항 수정    (Update)   /////////////
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
			sql = "SELECT count(days) FROM detailplan WHERE pjtIdx = ? AND id = ? AND days = ?"; // 이미 만들어진 세부 실천사항 있는지
																									// 확인
			pstmt1 = con1.prepareStatement(sql);
			pstmt1.setInt(1, bean.getPjtIndex());
			pstmt1.setString(2, bean.getMemId());
			pstmt1.setString(3, bean.getDays());
			rs = pstmt1.executeQuery();
			while (rs.next()) {
				newOrOld = rs.getInt(1);
			}
			if (newOrOld == 0) {
				flag = insertDetailPlans(bean); // 위의 select 결과 0이면 insert 실행
				return flag;
			} else {
				con2 = pool.getConnection();
				sql = "update detailplan set days = ?, detail = ?, onOff = ? where pjtIdx = ? and id = ? and days =?"; // 이미
																														// 만든
																														// 것
																														// 있으면
																														// update
																														// 실행
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

///////////  팀 프로젝트에서 탈퇴    (Delete)   /////////////
	public boolean leavePjt(int projectIndex, String id) {
		Connection con = null;
		Connection con1 = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "delete from pjtmembers where pjtIdx = ? and  id = ? "; // 탈퇴
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, projectIndex);
			pstmt.setString(2, id);
			int cnt = pstmt.executeUpdate();
			if (cnt == 1) {
				con1 = pool.getConnection();
				sql = "UPDATE projects SET numofppl = (SELECT numofppl-1 WHERE idx = ?) WHERE idx = ?"; // 인원 1 빼기
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
	
///////////// 프로젝트 기본사항 수정 (update) /////////////
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
			JOptionPane.showMessageDialog(null, "수정이 실패했습니다. 다시 시도해주세요.", "fail",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			pool.freeConnection(con1, pstmt1);
			pool.freeConnection(con2, pstmt2);
		}
		return flag;
	}
	
/////////////////// 목표 삭제  (DELETE) //////////////
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
