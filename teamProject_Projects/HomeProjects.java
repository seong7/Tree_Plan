//////표글씨체변경!/////홈화면////////ㅇㅋ 기본이미지 추가
//홈버튼에서 뒤로가기 누르면 어디로 가야하는가
//개인목표 클릭하면 목표 생성창? 수정창?
//진행률?
//그래프..?

package teamProject_Projects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import teamProject_Frame.TFrame1_null;
import teamProject_Manager_QnA.Manage_Qna_Bean;
import teamProject_Manager_QnA.Manage_Qna_Mgr;

public class HomeProjects extends TFrame1_null {
    private static final int COLUMN_SIZE = 3;
	private static final int INDEX_OF_TYPE_COLUMN = 2;

	JPanel allP, scP, pP, tP, todayP, todayComP, todoP, pPjtP, tPjtP;
	JLabel todayComL, todoL, pPjtL, tPjtL;
	JButton todoPPjtDeB, todoTPjtDeB;
	JCheckBox todoPPjtDeC, todoTPjtDeC;
	JTable todoTb, pPjtTb, tPjtTb;
	JScrollPane todoSc, pPjtSc, tPjtSc;

	JLabel todayCom2L;
	JTextField todayComTf;

	
	// DB 연결용 변수 선언
	TeamProject_Projects_Mgr mgr = new TeamProject_Projects_Mgr();
	Vector<TeamProject_Projects_Bean> vlist;
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH) + 1;
	int day = now.get(Calendar.DAY_OF_MONTH);
	String today = year + "-" + month + "-" + day;
	int rate = 0;
	double todoComplete;
	
	
	
////////////////////////////////////// 테이블 속성_배열, 레코드_벡터 선언 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/////////////////////////// To-do 테이블 //////////////////////////////
	Vector<String> columnNamesTodo = new Vector<String>(3);
	Vector rowDataTodo;
	DefaultTableModel todoTableModel; /* column과 row 값을 가질 table Model */

	// 레코드 값 넣는 메소드//
	public void addToRowDataTodo(int i) {
		if (columnNamesTodo.size() == 0) { //// 최초 실행 시( 벡터가 비어 있을 때) 에만 columnNamesTodo 값 넣어줌
			columnNamesTodo.addElement("목표 No");
			//columnNamesTodo.addElement("분류");
			columnNamesTodo.addElement("할 일");
			columnNamesTodo.addElement("완료여부");
			todoTableModel = new DefaultTableModel(columnNamesTodo, 0) { // table model 객체 생성 후 column 값 넣어줌 (0 은 빈 값
																			// 레코드 표시 x)
				public boolean isCellEditable(int row, int column) { // cell 수정 금지
					return false;
				}
			};
		} // ------cell 수정 금지

//		s1 = "1";
//		s2 = "◆";
//		s3 = "  " + "달리기 하기";
//		s4 = "완료"; // 레코드 값 예시 (DB에서 끌고와야함) // " " 를 더한 이유는 가운데 정렬을 할 줄 몰라 우선 공백 주었음
		rowDataTodo = new Vector();
		rowDataTodo.addElement(vlist.get(i).getPjtIndex());
		//rowDataTodo.addElement(i);
		rowDataTodo.addElement(vlist.get(i).getDetailPlan());
		rowDataTodo.addElement(vlist.get(i).getCompletion());
		todoTableModel.addRow(rowDataTodo);
		todoTb.setModel(todoTableModel);
		todoTb.getTableHeader().setReorderingAllowed(false); // header 이동 불가
		todoTb.setSelectionMode(1);
		todoTb.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 13));
		// todoTb.setCellSelectionEnabled(false);f
		// todoTb.setRowSelectionAllowed(false);
		// todoTb.setColumnSelectionAllowed(true);

		///// column 길이 조절
		TableColumn column = null;
		for (int j = 0; j < columnNamesTodo.size(); j++) {
			column = todoTb.getColumnModel().getColumn(j);
			if (j == 1) {
				column.setPreferredWidth(500);
			}
		}
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = todoTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		//tcm.getColumn(3).setCellRenderer(dtcr);
	}

	/////////////////////////// 개인 프로젝트 테이블 //////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	Vector<String> columnNamesPPjt = new Vector<String>(6);
	Vector rowDataPPjt;
	DefaultTableModel pPjtTableModel; /* column과 row 값을 가질 table Model */

	// 레코드 값 넣는 메소드//
	public void addToRowDataPPjt(int i) {
		if (columnNamesPPjt.size() == 0) { //// 최초 실행 시( 벡터가 비어 있을 때) 에만 columnNamesPPjt 값 넣어줌
			columnNamesPPjt.addElement("No");
			columnNamesPPjt.addElement("Photo");
			columnNamesPPjt.addElement("목표명");
			columnNamesPPjt.addElement("시작일기준");
			columnNamesPPjt.addElement("종료일");
				pPjtTableModel = new PjtTableModel(columnNamesPPjt, 0) { // table model 객체 생성 후 column 값 넣어줌 (0 은 빈 값 레코드
																		// 표시 x)
				public boolean isCellEditable(int row, int column) { // cell 수정 금지
					return false;
				}
			};
			; // table model 객체 생성 후 column 값 넣어줌 (0 은 빈 값 레코드 표시 x)
		}

		// 레코드 값 예시 (DB에서 끌고와야함)
		rowDataPPjt = new Vector();
		
		rowDataPPjt.addElement(vlist.get(i).getPjtIndex());
		ImageIcon ss2 = new ImageIcon(new ImageIcon(vlist.get(i).getPhoto()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		rowDataPPjt.addElement(ss2);
		rowDataPPjt.addElement("   " + vlist.get(i).getPjtName());
		rowDataPPjt.addElement(doDiffOfDates(vlist.get(i).getJoinDate(), today)+"일째 진행중");
		rowDataPPjt.addElement(vlist.get(i).getEndDate());

		pPjtTableModel.addRow(rowDataPPjt);
		pPjtTb.setModel(pPjtTableModel);
		pPjtTb.getTableHeader().setReorderingAllowed(false); // header 이동 불가
		pPjtTb.setSelectionMode(1);
		pPjtTb.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 13));

		///// column 길이 조절
		TableColumn column = null;
		for (int j = 0; j < columnNamesPPjt.size(); j++) {
			column = pPjtTb.getColumnModel().getColumn(j);
			if (j == 0) {
				column.setPreferredWidth(10);
			}
			if (j == 1) {
				column.setPreferredWidth(30);
			}
			if (j == 2) {
				column.setPreferredWidth(200);
			}
		}
		// 특정한 셀 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = pPjtTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
	}

	
	
	/////////////////////////// 팀 프로젝트 테이블 //////////////////////////////
	/////////////////////////////////////////////////////////
	
	Vector<String> columnNamesTPjt = new Vector<String>(5);
	Vector rowDataTPjt;
	DefaultTableModel tPjtTableModel; /* column과 row 값을 가질 table Model */

	// 레코드 값 넣는 메소드//
	public void addToRowDataTPjt(int i) {
		if (columnNamesTPjt.size() == 0) { //// 최초 실행 시( 벡터가 비어 있을 때) 에만 columnNamesTodo 값 넣어줌
			columnNamesTPjt.add("No");
			columnNamesTPjt.add("Photo");
			columnNamesTPjt.add("목표명");
			columnNamesTPjt.add("시작일기준");
			columnNamesTPjt.add("종료일");
			tPjtTableModel = new PjtTableModel(columnNamesTPjt, 0) { // table model 객체 생성 후 column 값 넣어줌 (0 은 빈 값
																		// 레코드 표시 x)
				public boolean isCellEditable(int row, int column) { // cell 수정 금지
					return false;
				}
			};
			 // table model 객체 생성 후 column 값 넣어줌 (0 은 빈 값 레코드 표시 x)
		}

		rowDataTPjt = new Vector();
//		s1 = "1";
//		s2 = "teamProject_Projects/tGoalBasic.png";
//		s3 = "달리기 하기";
//		s4 = "10일째 진행중";
//		s5 = "종료일";
		// 레코드 값 예시 (DB에서 끌고와야함)
		///// 이미지 크기조절
		
		rowDataTPjt.addElement(vlist.get(i).getPjtIndex());
		ImageIcon ss2 = new ImageIcon(new ImageIcon(vlist.get(i).getPhoto()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		rowDataTPjt.addElement(ss2);
		rowDataTPjt.addElement("   " + vlist.get(i).getPjtName());
		rowDataTPjt.addElement(doDiffOfDates(vlist.get(i).getJoinDate(), today)+"일째 참여중");
		rowDataTPjt.addElement(vlist.get(i).getEndDate());
		
		tPjtTableModel.addRow(rowDataTPjt);
		tPjtTb.setModel(tPjtTableModel);
		tPjtTb.getTableHeader().setReorderingAllowed(false); // header 이동 불가
		tPjtTb.setSelectionMode(1);
		tPjtTb.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 13));
		///// column 길이 조절
		TableColumn column = null;
		for (int j = 0; j < rowDataTPjt.size(); j++) {
			column = tPjtTb.getColumnModel().getColumn(j);
			if (j == 0) {
				column.setPreferredWidth(10);
			}
			if (j == 1) {
				column.setPreferredWidth(30);
			}
			if (j == 2) {
				column.setPreferredWidth(200);
			}
		}
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = tPjtTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
	}

////////////////////////////////////// 테이블 선언 완료  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// 테이블 선언 완료  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public HomeProjects() {

		allP = new JPanel(); // 추가

		scP = new JPanel(); // 추가
		todayP = new JPanel(); // 추가
		pP = new JPanel();// 추가
		tP = new JPanel();// 추가
		todayComP = new JPanel();
		todoP = new JPanel();
		pPjtP = new JPanel();
		tPjtP = new JPanel();
		todayComL = new JLabel("오늘의 달성률");
		todoL = new JLabel("TODAY TO-DO LIST");
		// todoTb = new JTable();
		//super.homeB.setEnabled(false);
		
		
		/////// 할일 완료시 색 변경
		todoTb = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component component = super.prepareRenderer(renderer, row, col);
				Object column = getValueAt(row, INDEX_OF_TYPE_COLUMN);
				if (column instanceof String) {
					String s3 = (String) column;
					if (s3.equals("완료")) {
						/////////// ★///////////
						component.setBackground(new Color(238, 242, 225));
						component.setForeground(Color.black);
						/////////// ☆///////////
					} else {
						component.setBackground(super.getBackground());
						component.setForeground(super.getForeground());
					}
				}
				return component;
			}
		};
		todoSc = new JScrollPane(todoTb);
		pPjtL = new JLabel("개인 목표");
		/////////// ★///////////
		pPjtTb = new JTable() {///// 테이블 선택해도 색 안바뀜
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
		pPjtSc = new JScrollPane(pPjtTb);
		tPjtL = new JLabel("팀 목표");
/////////// ★///////////
		tPjtTb = new JTable() {///// 테이블 선택해도 색 안바뀜
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
		tPjtSc = new JScrollPane(tPjtTb);
		todoPPjtDeB = new JButton("달리기 하기");
		todoTPjtDeB = new JButton("고려사");
		todoPPjtDeC = new JCheckBox();
		todoTPjtDeC = new JCheckBox();
		todayCom2L = new JLabel();// 그래프이미지
		todayComTf = new JTextField();// 오늘의 달성률(%)

		//////////////// Event Listener 추가 ///////
		todoTb.addMouseListener(this);
		pPjtTb.addMouseListener(this);
		tPjtTb.addMouseListener(this);

	}

	@Override
	public void arrange(String id, int x, int y) {
		todayComL.setBounds(20, 10, 250, 40);
		todayComL.setHorizontalAlignment(SwingConstants.CENTER);
		todayComL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		todayComL.setOpaque(true);
		todayComL.setBackground(new Color(211, 221, 179));
		todayP.add(todayComL);// 오늘의 달성률

		/////////// ★///////////ㅇㅋ
//		todayComP.setBounds(45, 60, 200, 200);
//		todayComP.setLayout(null);
//		todayComP.setBackground(Color.gray);
//		todayP.add(todayComP);// 달성률 패널--이미지 넣어야함
		/////////// ☆///////////

		todoL.setBounds(290, 10, 490, 40);
		todoL.setHorizontalAlignment(SwingConstants.CENTER);
		todoL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		todoL.setOpaque(true);
		todoL.setBackground(new Color(211, 221, 179));
		todayP.add(todoL);// 투두리스트 이름

		/////////// ★///////////ㅇㅋ
		todayComTf.setBorder(null);
		todayComTf.setBounds(110, 132, 70, 30);
		todayComTf.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		todayComTf.setForeground(new Color(59,89,72));
		todayComTf.setEditable(false);
		todayComTf.setBackground(Color.white);
		//todayComTf.setBorder(border);
		todayComTf.setHorizontalAlignment(SwingConstants.CENTER);
		todayP.add(todayComTf);
		//// 달성률 라벨
		todayCom2L.setBounds(45, 45, 200, 200);
		todayCom2L.setLayout(null);
		todayP.add(todayCom2L);// 달성률 라벨--이미지 넣어야함

		/////////// ☆///////////

		todoTb.setRowHeight(30);
		todoP.setBounds(290, 60, 490, 200);
		todoP.setLayout(new BorderLayout());
		todoP.setBackground(Color.white);
		todoP.add(todoSc);
		todayP.add(todoP);

		todayP.setBounds(100, 0, 800, 270);
		todayP.setLayout(null);
		todayP.setBackground(Color.white);
		add(todayP);

		pPjtL.setBounds(0, 0, 800, 40);
		pPjtL.setHorizontalAlignment(SwingConstants.CENTER);
		pPjtL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		pPjtL.setOpaque(true);
		pPjtL.setBackground(new Color(211, 221, 179));
		add(pPjtL);// 개인 목표 이름

		pPjtTb.setRowHeight(40);
		pPjtP.setBounds(20, 50, 760, 170);
		pPjtP.setLayout(new BorderLayout());
		pPjtP.setBackground(Color.WHITE);
		add(pPjtP);// 개인 목표
		pPjtP.add(pPjtSc);

		pP.add(pPjtL);
		pP.add(pPjtP);
		pP.setBounds(100, 270, 800, 230);
		pP.setLayout(null);
		pP.setBackground(Color.white);
		add(pP);

		tPjtL.setBounds(0, 0, 800, 40);
		tPjtL.setHorizontalAlignment(SwingConstants.CENTER);
		tPjtL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		tPjtL.setOpaque(true);
		tPjtL.setBackground(new Color(211, 221, 179));
		add(tPjtL);// 팀 목표 이름

		tPjtTb.setRowHeight(40);
		tPjtP.setBounds(20, 50, 760, 170);
		tPjtP.setLayout(new BorderLayout());
		tPjtP.setBackground(Color.white);
		add(tPjtP);// 팀 목표
		tPjtP.add(tPjtSc);

		tP.add(tPjtL);
		tP.add(tPjtP);
		tP.setBounds(100, 500, 800, 230);
		tP.setLayout(null);
		tP.setBackground(Color.white);
		add(tP);

		allP.add(todayP);
		allP.add(pP);
		allP.add(tP);
		allP.setBounds(0, 0, 1000, 800);
		allP.setLayout(null);
		allP.setBackground(Color.white);
		add(allP);
		MainP.add(allP);
		allP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);

		setLocation(x, y);
		setVisible(true);
		repaint();
		validate();
		
		
		////////////DB 연동 ////////
		
		personalProjectListShow(id);
		teamProjectListShow(id);
		
		Vector <Integer> pjtIdxList = new Vector();
		for (int i = 0; i < pPjtTb.getRowCount(); i++) {
			pjtIdxList.addElement((int)pPjtTb.getValueAt(i, 0));
		}
		for (int i = 0; i < tPjtTb.getRowCount(); i++) {
			pjtIdxList.addElement((int)tPjtTb.getValueAt(i, 0));
		}
		for (int i = 0; i < pjtIdxList.size(); i++) {
			toDoListShow(pjtIdxList.get(i), id);
		}
		for (int i = 0; i < todoTb.getRowCount(); i++) {
			if(todoTb.getValueAt(i, 2).equals("완료")) {
				todoComplete += 1;
			}
		}
		rate = (int)(todoComplete/todoTb.getRowCount()*100);
		
		todayComTf.setText(rate + "%");
		todayCom2L.setIcon(new ImageIcon(getGraphName(rate)));
		
		if (todoTb.getRowCount() == 0) {
			vlist = new Vector();
			TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
			bean.setPjtIndex(1);
			bean.setDetailPlan("오늘의 to-do list가 비어있습니다.");
			bean.setCompletion("");
			vlist.addElement(bean);
			for (int j = 0; j < vlist.size(); j++) {
				addToRowDataTodo(j);
			}
		}
		
		rate = 0;
		todoComplete = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) { // 홈버튼에 테이블 항목 추가 메소드 실행
		Object obj = e.getSource();
		
		if(obj == homeB) {
			return;
		}
		
		super.actionPerformed(e);
		
//	backB, searchB, homeB, newPjtB, qnaB, personalB, mgrB, personalInfoB,
		
		if (/* obj == homeB || */obj == searchB ||obj ==newPjtB || obj == qnaB || obj == mgrB) {
			//System.out.println("cccccc");
			// JTable 초기화 !!
			DefaultTableModel Tmodel = (DefaultTableModel)tPjtTb.getModel();
			Tmodel.setRowCount(0);
			DefaultTableModel Pmodel = (DefaultTableModel)pPjtTb.getModel();
			Pmodel.setRowCount(0);
			DefaultTableModel todomodel = (DefaultTableModel)todoTb.getModel();
			todomodel.setRowCount(0);
			
			//
		}
	}
	
	
	////////////////// 사진인식 관련
	class PjtTableModel extends DefaultTableModel {
		PjtTableModel(Vector<String> columnNames, int rowNum) {
			super(columnNames, rowNum);
		}

		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
	}

	///////// todo list ///////
	public void toDoListShow(int pjtIdx, String id) {
		vlist = mgr.showTodoList(pjtIdx, id);
		//System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataTodo(i);
		}
	}
	
	//////// 개인 목표 추가 ///////
	public void personalProjectListShow(String id) {
		vlist = mgr.PersonalProjectList(id);
		//System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataPPjt(i);
		}
	}
	////////팀 목표 추가 ///////
	public void teamProjectListShow(String id) {
		vlist = mgr.teamProjectList(id);
		//System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataTPjt(i);
		}
	}
	
	
	/// 날짜 차이 구하는 메소드
	public int doDiffOfDates(String startDate, String today){
		//String start = "2015-05-05";
	    //today = start;
	    //String end = "2015-04-01";
	    //startDate = end;
		//System.out.println("startDate : " + startDate);
		//System.out.println("today : " + today);

		int diffDays = 0;
		if (startDate != null) {
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
		}
		return diffDays;
	}

	/// 달성률 별로 그래프 사진 파일 구하는 메소드
	public String getGraphName(int rate){
		String fileName="";
		if(rate ==0) {
			fileName = "teamProject_Projects/0p.png";
		}else if(rate>0 && rate<=10 ) {
			fileName = "teamProject_Projects/10p.png";
		}else if(rate>10 && rate<=20 ) {
			fileName = "teamProject_Projects/20p.png";
		}else if(rate>20 && rate<=30 ) {
			fileName = "teamProject_Projects/30p.png";
		}else if(rate>30 && rate<=40 ) {
			fileName = "teamProject_Projects/40p.png";
		}else if(rate>40 && rate<=49 ) {
			fileName = "teamProject_Projects/45p.png";
		}else if(rate==50 ) {
			fileName = "teamProject_Projects/50p.png";
		}else if(rate>50 && rate<=60 ) {
			fileName = "teamProject_Projects/60p.png";
		}else if(rate>60 && rate<=70 ) {
			fileName = "teamProject_Projects/70p.png";
		}else if(rate>70 && rate<=80 ) {
			fileName = "teamProject_Projects/80p.png";
		}else if(rate>80 && rate<=90 ) {
			fileName = "teamProject_Projects/90p.png";
		}else if(rate>90 && rate<=99 ) {
			fileName = "teamProject_Projects/95p.png";
		}else if( rate==100 ) {
			fileName = "teamProject_Projects/100p.png";
		}
		return fileName;
	}

	
	
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Object obj = e.getSource();

///////// 투두테이블 항목완료시
		if (obj == todoTb) {
			if (e.getClickCount() == 2) {
				int todoRow = todoTb.getSelectedRow();
				int pjtidx = (int)todoTableModel.getValueAt(todoRow, 0);
				String selectedComoplete = todoTableModel.getValueAt(todoRow, 2).toString();
				String todoItem = todoTableModel.getValueAt(todoRow, 1).toString();
				
				if(todoItem.equals("오늘의 to-do list가 비어있습니다.")) {
					return;
				}
				
				/// 미완료 -> 완료
				if(selectedComoplete.equals("미완료")) {
					int result = JOptionPane.showConfirmDialog(null, "[ " + todoItem + " ] " + "항목을 완료하시겠습니까?",
							"To-do List", JOptionPane.YES_NO_OPTION);
					
					if(result == 0) {
						boolean flag = mgr.insertComplete(pjtidx, id, todoItem);
						if(flag) {
							new HomeProjects().arrange(id, getX(), getY());
							dispose();
						}
					}
					return;
				}
				
				/// 완료 -> 미완료
				if(selectedComoplete.equals("완료")) {
					int result = JOptionPane.showConfirmDialog(null, "[ " + todoItem + " ] " + "항목을 취소하시겠습니까?",
							"To-do List", JOptionPane.YES_NO_OPTION);
					if(result == 0) {
						boolean flag = mgr.deleteComplete(pjtidx, id);
						if(flag) {
							new HomeProjects().arrange(id, getX(), getY());
							dispose();
						}
					}
					return;
				}
			}
		}

////////// 개인 목표 더블클릭시 세부항목 설정 이동			
		if (obj == pPjtTb) {
			if (e.getClickCount() == 2) {
				int clickedRow = pPjtTb.getSelectedRow();
				String pPjtNull = pPjtTableModel.getValueAt(clickedRow, 2).toString();
				if (pPjtNull.trim().equals("개인 목표를 추가하세요.")) {
					SinglePlanCreate sc = new SinglePlanCreate();
					sc.arrange(id, getX(), getY());
					// JTable 초기화 !!
					DefaultTableModel Tmodel = (DefaultTableModel) tPjtTb.getModel();
					Tmodel.setRowCount(0);
					DefaultTableModel Pmodel = (DefaultTableModel) pPjtTb.getModel();
					Pmodel.setRowCount(0);
					DefaultTableModel todomodel = (DefaultTableModel) todoTb.getModel();
					todomodel.setRowCount(0);
					//
					saveThisPage();
					return;
				} else {
					//System.out.println("pPjtNull  :" +pPjtNull);
					int pPjtItem = Integer.parseInt(pPjtTableModel.getValueAt(clickedRow, 0).toString());
					SinglePlanUpdate sp = new SinglePlanUpdate(pPjtItem);
					sp.arrange(id, getX(), getY());
					// JTable 초기화 !!
					DefaultTableModel Tmodel = (DefaultTableModel) tPjtTb.getModel();
					Tmodel.setRowCount(0);
					DefaultTableModel Pmodel = (DefaultTableModel) pPjtTb.getModel();
					Pmodel.setRowCount(0);
					DefaultTableModel todomodel = (DefaultTableModel) todoTb.getModel();
					todomodel.setRowCount(0);
					//
					saveThisPage();
					return;
				}
			}
		}

////////// 팀 목표 더블클릭시 팀 홈으로 이동
		if (obj == tPjtTb) {
			if (e.getClickCount() == 2) {
				int clickedRow = tPjtTb.getSelectedRow();
				int tPjtItem = Integer.parseInt(tPjtTableModel.getValueAt(clickedRow, 0).toString());
				String tPjtNull = tPjtTableModel.getValueAt(clickedRow, 2).toString();
				if (tPjtNull.trim().equals("팀 목표를 생성하거나 기존의 팀 목표에 가입하세요.")) {
					TeamPlanCreate tc = new TeamPlanCreate();
					tc.arrange(id, getX(), getY());
					// JTable 초기화 !!
					DefaultTableModel Tmodel = (DefaultTableModel) tPjtTb.getModel();
					Tmodel.setRowCount(0);
					DefaultTableModel Pmodel = (DefaultTableModel) pPjtTb.getModel();
					Pmodel.setRowCount(0);
					DefaultTableModel todomodel = (DefaultTableModel) todoTb.getModel();
					todomodel.setRowCount(0);
					//
					saveThisPage();
					return;
				} else {
					TeamProjects tp = new TeamProjects(tPjtItem);
					// TeamProjects tp = new TeamProjects(pPjtItem);
					tp.arrange(id, getX(), getY());
					// JTable 초기화 !!
					DefaultTableModel Tmodel = (DefaultTableModel) tPjtTb.getModel();
					Tmodel.setRowCount(0);
					DefaultTableModel Pmodel = (DefaultTableModel) pPjtTb.getModel();
					Pmodel.setRowCount(0);
					DefaultTableModel todomodel = (DefaultTableModel) todoTb.getModel();
					todomodel.setRowCount(0);
					//
					saveThisPage();
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		HomeProjects hp = new HomeProjects();
		hp.arrange("admin", 0, 0);
	}
}
