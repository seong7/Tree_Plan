////////팀 프로젝트 홈 화면-그래프 있음//////

package teamProject_Projects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import teamProject_Frame.TFrame1_null;
import teamProject_Member.TeamProject_Member_Mgr;

public class TeamProjects extends TFrame1_null {
	private static final int INDEX_OF_TYPE_COLUMN = 2;

	JPanel allP, tPjtP, tComLiP, tComMemScP;
	JLabel tPjtNameL, tPjtStartL, tPjtPpL, tPjtEndL, tLackL, tPjtAveL, tComLiL, tPhoL;
	JButton tSetB;
	JTable tComMemTb;
	JScrollPane tComMemSc;
	JLabel tPjtAve2L;
	JTextField tPjtAveTf;
	int projectIndex;
	int rate = 0;
	
	TeamProject_Projects_Mgr mgr;
	Vector<TeamProject_Projects_Bean> vlist;
	TeamProject_Projects_Bean bean;
	
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH) + 1;
	int day = now.get(Calendar.DAY_OF_MONTH);
	String today = year + "-" + month + "-" + day;
	

	public TeamProjects(int projectIndex) {
		this.projectIndex = projectIndex;
		
		allP = new JPanel(); // 전체 패널
		tPjtP = new JPanel(); // 팀프로젝트 상세내역
		tComLiP = new JPanel(); // 완료리스트 패널
		tComMemScP = new JPanel();// 완료리스트 멤버 스크롤
		tPjtNameL = new JLabel(); // 목표이름
		tPjtStartL = new JLabel();
		tPjtPpL = new JLabel();
		tPjtEndL = new JLabel();
		tLackL = new JLabel();// 잠금여부
		tPjtAveL = new JLabel("오늘의 팀원 달성률"); // 평균달성률 이름라벨
		tComLiL = new JLabel("오늘의 팀원 완료 목록"); // 완료리스트 이름라벨
		tPhoL = new JLabel(); // 팀사진라벨
		tSetB = new JButton(new ImageIcon("teamProject_Projects/setting.png")); // 팀 설정버튼
		tSetB.addActionListener(this);
		tPjtAve2L = new JLabel();// 그래프이미지
		tPjtAveTf = new JTextField();// 평균 달성률(%)
/////////// ★///////////
		tComMemTb = new JTable() {///// 테이블 선택해도 색 안바뀜
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component component = super.prepareRenderer(renderer, row, col);
				Object column = getValueAt(row, INDEX_OF_TYPE_COLUMN);
				if (column instanceof String) {
					String s3 = (String) column;
					if (s3.equals("")) {

						component.setBackground(super.getBackground());
						component.setForeground(super.getBackground());
					} else {
						component.setBackground(super.getBackground());
						component.setForeground(super.getForeground());
					}
				}
				return component;
			}
		};
/////////// ☆///////////
		tComMemSc = new JScrollPane(tComMemTb);
		setVisible(true);
	}

	@Override
	public void arrange(String id, int x, int y) {

		// 팀프로젝트 상세 내용 화면
		tPjtP.add(tPjtNameL); // 프로젝트명
		tPjtNameL.setBounds(250, 55, 220, 40);
		tPjtNameL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		tPjtP.add(tPjtStartL); // 시작일
		tPjtStartL.setBounds(250, 95, 220, 40);
		tPjtStartL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 15));
		tPjtP.add(tPjtPpL); // 참여인원
		tPjtPpL.setBounds(250, 135, 220, 40);
		tPjtPpL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 15));
		tPjtP.add(tPjtEndL); // 종료일
		tPjtEndL.setBounds(250, 175, 220, 40);
		tPjtEndL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 15));
		tPjtP.add(tLackL);// 공개여부
		tLackL.setBounds(250, 215, 220, 40);
		tLackL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 15));
		tPjtP.add(tPjtAveL);// 평균그래프 타이틀 라벨
		tPjtAveL.setHorizontalAlignment(SwingConstants.CENTER);
		tPjtAveL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		tPjtAveL.setOpaque(true);
		tPjtAveL.setBackground(new Color(211, 221, 179));
		tPjtAveL.setBounds(500, 10, 250, 40);
		tPjtP.add(tSetB);// 설정버튼
		tSetB.setBounds(760, 260, 25, 25);
		tSetB.setBorderPainted(false);
		tSetB.setFocusPainted(false);
		tSetB.setContentAreaFilled(false);
		tPjtP.add(tPhoL);// 프로필 사진(자리)
		tPhoL.setOpaque(true);
		tPhoL.setBackground(Color.white);
		tPhoL.setBounds(10, 50, 200, 200);
		tPjtP.add(tPjtAve2L);
		tPjtAve2L.setLayout(null);

//		tPjtAve2L.setIcon(new ImageIcon("teamProject_Projects/90p.png"));
		tPjtAve2L.setBounds(525, 60, 200, 200);// 그래프
		tPjtAveTf.setBounds(590, 147, 70, 30);
		tPjtAveTf.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		tPjtAveTf.setEditable(false);
		tPjtAveTf.setBorder(null);
		tPjtAveTf.setBackground(Color.white);
		tPjtAveTf.setHorizontalAlignment(SwingConstants.CENTER);
		tPjtAveTf.setForeground(new Color(59,89,72));
		tPjtP.add(tPjtAveTf);

		tPjtP.setBounds(100, 0, 800, 300);
		tPjtP.setLayout(null);
		tPjtP.setBackground(Color.white);
		add(tPjtP);

		///// 완료리스트 타이틀 이름
		tComLiL.setBounds(0, 0, 800, 40);
		tComLiL.setHorizontalAlignment(SwingConstants.CENTER);
		tComLiL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		tComLiL.setOpaque(true);
		tComLiL.setBackground(new Color(211, 221, 179));
		add(tComLiL);

///완료리스트 테이블
		tComMemTb.setRowHeight(70);
		tComMemScP.setBounds(20, 50, 760, 350);
		tComMemScP.setLayout(new BorderLayout());
		tComMemScP.setBackground(Color.white);
		add(tComMemScP);
		tComMemScP.add(tComMemSc);

		/// 완료리스트
		tComLiP.add(tComLiL);
		tComLiP.add(tComMemScP);
		tComLiP.setLayout(null);
		tComLiP.setBounds(100, 300, 800, 420);
		tComLiP.setBackground(Color.white);
		add(tComLiP);

		/// 전체패널
		allP.add(tPjtP);
		allP.add(tComLiP);
		allP.setBounds(0, 0, 1000, 800);
		allP.setLayout(null);
		allP.setBackground(Color.white);
		add(allP);

		MainP.add(allP);
		allP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);

		
		// DB 연결용 변수 선언
		mgr = new TeamProject_Projects_Mgr();
		vlist = mgr.showProject(projectIndex);
		int completeRate = mgr.getCompleteRate(projectIndex);
		//System.out.println("completeRate :  " +completeRate);
		
		String s1 = vlist.get(0).getPhoto();
		ImageIcon ss1 = new ImageIcon(new ImageIcon(s1).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		tPhoL.setIcon(ss1);
		tPjtNameL.setText( vlist.get(0).getPjtName());
		tPjtStartL.setText(doDiffOfDates(vlist.get(0).getStartDate(), today)+ "일째 진행중");
		tPjtPpL.setText("참여인원 " + vlist.get(0).getNumOfPpl() + "/" + vlist.get(0).getNumMax());
		tPjtEndL.setText("종료일 : " + vlist.get(0).getEndDate());
		if(vlist.get(0).getPassword().length()==0) {
			tLackL.setText("[OPEN]");
		}else {
			tLackL.setText("[LOCK]");
		}
		tPjtAveTf.setText(completeRate+"%");
		tPjtAve2L.setIcon(new ImageIcon(getGraphName(completeRate)));
		
		setLocation(x, y);
		setVisible(true);
		validate();
		
		completeListShow(projectIndex);
	}

	/// 날짜 차이 구하는 메소드
	public int doDiffOfDates(String startDate, String today) {
		//System.out.println("startDate : " + startDate);
		//System.out.println("today : " + today);

		int diffDays = 0;

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date beginDate = formatter.parse(startDate);
			Date endDate = formatter.parse(today);

			// 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
			long diff = endDate.getTime() - beginDate.getTime();
			long diffDaysLong = diff / (24 * 60 * 60 * 1000);

			diffDays = (int) diffDaysLong;
			//System.out.println("날짜차이=" + diffDays);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffDays;
	}
	
	/// 달성률 별로 그래프 사진 파일 구하는 메소드
	public String getGraphName(int completeRate){
		String fileName="";
		if(completeRate ==0) {
			fileName = "teamProject_Projects/0p.png";
		}else if(completeRate>0 && completeRate<=10 ) {
			fileName = "teamProject_Projects/10p.png";
		}else if(completeRate>10 && completeRate<=20 ) {
			fileName = "teamProject_Projects/20p.png";
		}else if(completeRate>20 && completeRate<=30 ) {
			fileName = "teamProject_Projects/30p.png";
		}else if(completeRate>30 && completeRate<=40 ) {
			fileName = "teamProject_Projects/40p.png";
		}else if(completeRate>40 && completeRate<=49 ) {
			fileName = "teamProject_Projects/45p.png";
		}else if(completeRate==50 ) {
			fileName = "teamProject_Projects/50p.png";
		}else if(completeRate>50 && completeRate<=60 ) {
			fileName = "teamProject_Projects/60p.png";
		}else if(completeRate>60 && completeRate<=70 ) {
			fileName = "teamProject_Projects/70p.png";
		}else if(completeRate>70 && completeRate<=80 ) {
			fileName = "teamProject_Projects/80p.png";
		}else if(completeRate>80 && completeRate<=90 ) {
			fileName = "teamProject_Projects/90p.png";
		}else if(completeRate>90 && completeRate<=99 ) {
			fileName = "teamProject_Projects/95p.png";
		}else if( completeRate==100 ) {
			fileName = "teamProject_Projects/100p.png";
		}
		return fileName;
	}
	
////////// 목표 완료자 리스트 표/////////

	Vector<String> columnNamesTComMem = new Vector<String>(7);
	Vector rowDataTComMem;
	DefaultTableModel tComMemTableModel; /* column과 row 값을 가질 table Model */

// 레코드 값 넣는 메소드//
	public void addToRowDataTComMem(int i) {
		if (columnNamesTComMem.size() == 0) { //// 최초 실행 시( 벡터가 비어 있을 때) 에만 columnNamesTodo 값 넣어줌
			columnNamesTComMem.add("No");
			columnNamesTComMem.add("사진");
			columnNamesTComMem.add("아이디");
			columnNamesTComMem.add("세부목표명");
			columnNamesTComMem.add("시작일기준");
			columnNamesTComMem.add("오늘의 완료시간");
			tComMemTableModel = new PjtTableModel(columnNamesTComMem, 0) { // table model 객체 생성 후 column 값 넣어줌 (0 은
																			// 빈 값
				// 레코드 표시 x)
				public boolean isCellEditable(int row, int column) { // cell 수정 금지
					return false;
				}
			}; // table model 객체 생성 후 column 값 넣어줌 (0 은 빈 값 레코드 표시 x)
		}

		rowDataTComMem = new Vector();
		
		// 레코드 값 예시 (DB에서 끌고와야함)
		rowDataTComMem = new Vector();
		rowDataTComMem.addElement(i+1);
		ImageIcon ss2 = new ImageIcon(new ImageIcon(vlist.get(i).getPhoto()).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		rowDataTComMem.addElement(ss2);
		rowDataTComMem.addElement(vlist.get(i).getMemId());
		rowDataTComMem.addElement("   " +vlist.get(i).getDetailPlan());
		if(vlist.get(i).getJoinDate() == null) {
			rowDataTComMem.addElement(null);
		}else {
			rowDataTComMem.addElement(doDiffOfDates(vlist.get(i).getJoinDate(), today)+"일째 진행중");
		}
		rowDataTComMem.addElement(vlist.get(i).getCompleteTime());

		tComMemTableModel.addRow(rowDataTComMem);
		tComMemTb.setModel(tComMemTableModel);
		tComMemTb.getTableHeader().setReorderingAllowed(false); // header 이동 불가
		tComMemTb.setSelectionMode(1);
		tComMemTb.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 13));

///// column 길이 조절 
		TableColumn column = null;
		for (int j = 0; j < rowDataTComMem.size(); j++) {
			column = tComMemTb.getColumnModel().getColumn(j);
			if (j == 0) {
				column.setPreferredWidth(30);
			}
			if (j == 3) {
				column.setPreferredWidth(200);
			}
		}
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = tComMemTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
		tcm.getColumn(5).setCellRenderer(dtcr);
	}

///////////

	
	//////// 완료 리스트  추가 ///////
	public void completeListShow(int pjtIndex) {
		vlist = mgr.showCompleteList(pjtIndex, today);
//		System.out.println("pjtIndex    :  " +pjtIndex);
//		System.out.println(" today    :   " + today );
//		System.out.println("!!!!!!! 완료 리스트 !!!!!   " + vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataTComMem(i);
		}
	}
	
/////팀설정버튼
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (obj == searchB ||obj ==newPjtB || obj == qnaB || obj == mgrB || obj == homeB ) {
			// JTable 초기화 !!
			DefaultTableModel Tmodel = (DefaultTableModel)tComMemTb.getModel();
			Tmodel.setRowCount(0);
			//
		}
		
		super.actionPerformed(e);
		
		if (obj == tSetB) {
			//System.out.println("팀설정");
			new TeamGoal(projectIndex).arrange(id, getX(), getY());
			// JTable 초기화 !!
			DefaultTableModel Tmodel = (DefaultTableModel)tComMemTb.getModel();
			Tmodel.setRowCount(0);
			//
			saveThisPage();
		}
	}
	
	////////////////// 사진인식 관련
	class PjtTableModel extends DefaultTableModel {
		PjtTableModel(Vector<String> columnNames, int rowNum) {
			super(columnNames, rowNum);
		}

		/////////////////////////// 사진 인식 /////////////////////
		public Class getColumnClass(int col) {
			// System.out.println(getValueAt(0, col).getClass());
			return getValueAt(0, col).getClass();
		}
	}
	
	
	public static void main(String[] args) {
		TeamProjects tp = new TeamProjects(0);
		//tp.addToRowDataTComMem("", "", "", "", "", "");
		tp.arrange("", 0, 0);
	}
}
