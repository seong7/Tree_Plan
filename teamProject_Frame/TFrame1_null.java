///////////////////////// TFrame1 :  기본화면 + menu바  (1000, 1000)  ////////

package teamProject_Frame;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import teamProject_Manager_QnA.MemAdmin;
import teamProject_Manager_QnA.QnA;
import teamProject_Member.Modification;
import teamProject_Member.TreePlan_exe;
import teamProject_Projects.HomeProjects;
import teamProject_Projects.NewGoalCreate;

//import team_p1_관리자모드.MemAdmin;

public class TFrame1_null extends JFrame implements MouseListener, ActionListener, PageList_TFrame1_2 {

	JPanel menuP, personalP;
	public JButton backB, searchB, homeB, newPjtB, qnaB, personalB, mgrB, personalInfoB, logOutB;
	public JTextField searchTf;
	GraphicsEnvironment ge; // 모니터 픽셀 수 구하기 위한 클래스의 변수 선언
	public int personalP_onOff;
	int personalMouse = 0;
	public JPanel MainP = new JPanel();
	personalMenu pm;
	static int pageNum = 0;
	public String id = idMemory.get(0);
//	public String id = "admin";

	public TFrame1_null() {
		setResizable(false);
		setTitle("TREE PLAN");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null/* new GridBagLayout() */ /* new BorderLayout() */);
		getContentPane().setBackground(new Color(255, 255, 255));
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("teamProject_Frame/basic.png");
		setIconImage(img);

		// 개인 정보 버튼의 부속 정보 메뉴 Panel (수정필요)////////////////////////////////////
		personalP = new JPanel();
		personalP.addMouseListener(this); // 개인 정보 부속 메뉴 패널 MouseListener
		personalInfoB = new JButton("회원정보");
		personalInfoB.setSize(90, 15);
		personalInfoB.setBackground(Color.WHITE);
		logOutB = new JButton("Log-out");
		logOutB.setSize(90, 15);
		logOutB.setBackground(Color.WHITE);
		
		
		logOutB.addMouseListener(this); // 로그아웃 버튼 MouseListener
		logOutB.addActionListener(this); // 로그아웃 버튼 ActionListener
		personalInfoB.addMouseListener(this); // 회원 정보 버튼 MouseListener
		personalInfoB.addActionListener(this); // 회원 정보 버튼 ActionListener
		
		
		/////////////////////////////////// Menu Bar 패널
		/////////////////////////////////// /////////////////////////////////////

		/* 패널 */
		menuP = new JPanel();
		menuP.setBounds(0, 0, 1000, 38);
		add(menuP);
		menuP.setBackground(new Color(255, 255, 255));

		/* 검색창 */
		searchTf = new JTextField(20);
		searchTf.setBackground(new Color(185, 226, 250));
		searchTf.setBorder(null);

		/* 뒤로가기 버튼 */
		backB = new JButton("");
		backB.setIcon(new ImageIcon("teamProject_Frame/left-arrow.png"));
		backB.setBorderPainted(false);
		backB.setFocusPainted(false);
		backB.setContentAreaFilled(false);

		/* 검색 버튼 */
		searchB = new JButton("");
		searchB.setIcon(new ImageIcon("teamProject_Frame/magnifying-glass.png"));
		searchB.setBorderPainted(false);
		searchB.setFocusPainted(false);
		searchB.setContentAreaFilled(false);

		/* 홈 버튼 */
		homeB = new JButton("");
		homeB.setIcon(new ImageIcon("teamProject_Frame/homepage.png"));
		homeB.setBorderPainted(false);
		homeB.setFocusPainted(false);
		homeB.setContentAreaFilled(false);

		/* 새 프로젝트 추가 버튼 */
		newPjtB = new JButton("");
		newPjtB.setIcon(new ImageIcon("teamProject_Frame/add-file.png"));
		newPjtB.setBorderPainted(false);
		newPjtB.setFocusPainted(false);
		newPjtB.setContentAreaFilled(false);

		/* QnA 버튼 */
		qnaB = new JButton("");
		qnaB.setIcon(new ImageIcon("teamProject_Frame/help.png"));
		qnaB.setBorderPainted(false);
		qnaB.setFocusPainted(false);
		qnaB.setContentAreaFilled(false);

		/* 개인정보 버튼 */
		personalB = new JButton("");
		personalB.setIcon(new ImageIcon("teamProject_Frame/profile.png"));
		personalB.setBorderPainted(false);
		personalB.setFocusPainted(false);
		personalB.setContentAreaFilled(false);

		/* 관리자 모드 버튼 */
		mgrB = new JButton("");
		mgrB.setIcon(new ImageIcon("teamProject_Frame/admin-with-cogwheels.png"));
		mgrB.setBorderPainted(false);
		mgrB.setFocusPainted(false);
		mgrB.setContentAreaFilled(false);

		/* menu button 들 EventListener 추가 */
		backB.addActionListener(this);
		searchB.addActionListener(this);
		homeB.addActionListener(this);
		newPjtB.addActionListener(this);
		qnaB.addActionListener(this);
		personalB.addActionListener(this);
		mgrB.addActionListener(this);
		menuP.addMouseListener(this);
		/* Event Listener 완료 */

		menuP.setLayout(null);
		backB.setBounds(10, 5, 90, 30);
		menuP.add(backB);
		searchTf.setBounds(105, 5, 220, 30);
		menuP.add(searchTf);
		searchB.setBounds(310, 5, 90, 30);
		menuP.add(searchB);
		homeB.setBounds(505, 5, 90, 30);
		menuP.add(homeB);
		newPjtB.setBounds(600, 5, 90, 30);
		menuP.add(newPjtB);
		qnaB.setBounds(695, 5, 90, 30);
		menuP.add(qnaB);
		personalB.setBounds(790, 5, 90, 30);
		menuP.add(personalB);
		mgrB.setBounds(885, 5, 90, 30);
		if (id.equals("admin")) {
			menuP.add(mgrB);
			//System.out.println(id);
		}

		/////////////////////////////////// Main Panel : 메인 화면 부분 패널
		/////////////////////////////////// ////////////////////////////////////////////////////////////

		MainP.setBounds(0, menuP.getY() + menuP.getHeight(), getWidth(), getHeight() - 35);
		add(MainP);
		MainP.setLayout(null);
		menuP.addMouseListener(this);
		MainP.addMouseListener(this);
		validate();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////

		// 어떤 모니터이든 중앙에 열리기
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); // 모니터(픽셀)의 크기 구함
		int height = (int) ge.getMaximumWindowBounds().getHeight();
		int width = (int) ge.getMaximumWindowBounds().getWidth();
		setLocation((width / 2 - this.getWidth() / 2), (height / 2 - this.getHeight() / 2)); // 모니터의 중앙에 열리도록 설정
		
		pm = new personalMenu();
		personalInfoB.addMouseListener(this);
		logOutB.addMouseListener(this);
	}

	///////////////// 개인정보 부속메뉴 내부 클래스 ///////////////////
	class personalMenu extends JFrame{
		int pWidth = personalInfoB.getWidth(); // Panel의 너비
		int pHeigt = personalInfoB.getHeight() + personalInfoB.getHeight() + logOutB.getHeight(); // Panel의 높이
		int pX = personalB.getX();

		public void personalM_on(int x, int y) { // 개인정보 부속 메뉴 ON 메소드
			super.setUndecorated(true);
			setSize(pWidth, pHeigt);
			setLocation(x, y);
			getContentPane().setLayout(null);

			personalP.setLayout(new GridLayout(2, 0));
			// personalP.add(calendarB);
			personalP.add(personalInfoB);
			personalP.add(logOutB);

			personalP.setBounds(0, 0, pWidth, pHeigt);
			add(personalP);
			repaint();
			validate();

			personalP_onOff = 1; // on 표시
			//System.out.println(personalP_onOff);
			super.setType(javax.swing.JFrame.Type.UTILITY);
			
			
			setVisible(true);
		}

		public void personalM_off() { // 개인정보 부속 메뉴 OFF 메소드
			super.dispose();
			personalP_onOff = 0; // off 표시
			//System.out.println(personalP_onOff);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (personalP_onOff == 1) {
			//System.out.println("끄기");
			pm.personalM_off(); // 개인정보 부속 메뉴가 켜있으면 어디를 클릭해도 제거됨]
			//return;
		}

////////////////////////////////////////////////// MENU BUTTON 활성화 ////////////////////////////////////////////////////////////////////		

		///// 홈버튼 기능 /////
		if (obj == homeB) {
			// System.out.println("홈");
			new HomeProjects().arrange(id, getX(), getY());
			saveThisPage();
			return;
		}

		/// 검색 버튼 기능 /////
		else if (obj == searchB) {
			// System.out.println("검색");
			new teamProject_Projects.TeamSearch(searchTf.getText().trim()).arrange(id, getX(), getY());
			saveThisPage();
			return;
		}

		//// 새프로젝트 추가 버튼 기능 /////
		if (obj == newPjtB) {
			new NewGoalCreate().arrange(id, getX(), getY());
			saveThisPage();
			return;
		}

		//// QnA 버튼 기능 ////
		if (obj == qnaB) {
			QnA qna = new QnA();
			qna.arrange(id, getX(), getY());
			saveThisPage();
			return;
		}

		//// 개인 정보 부속 메뉴 버튼 기능 /////
		if (obj == personalB && personalP_onOff == 1) { // 개인정보 부속메뉴 on 일 때 누르면 off 됨
			pm.personalM_off();
			return;
		} else if (obj == personalB) { // 개인정보 부속메뉴 on
			pm.personalM_on(this.getX() + 800, this.getY() + 70);
			return;
		}

		//// 관리자 모드 //////
		if (obj == mgrB) {
			MemAdmin ma = new MemAdmin();
			ma.arrange(id, getX(), getY());
			saveThisPage();
			return;
		}

		//////// 로그아웃 버튼////
		if (obj == logOutB) {
			int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃 확인창", JOptionPane.OK_CANCEL_OPTION);
			if (result == 0) {
				new TreePlan_exe();
				idMemory.removeAllElements();
				dispose();
				return;
			}
			return;
		}

		//////// 회원정보 수정 버튼 ////
		if (obj == personalInfoB) {
			new Modification().arrange(id, getX(), getY());
			return;
		}

		//// 뒤로가기 버튼 기능 /////
		if (obj == backB) {
			if (pageList.size() == 0) {
				//System.out.println("뒤로 갈 화면 없음");
				return;
			} else {
				//System.out.println("뒤로 가기");
				moveBack();
				return;
			}
		}
	}

	@Override
	public void saveThisPage() {
		//System.out.println("this page saved");
		pageList.addElement(this);
		System.out.println("pageList vector size : " + pageList.size());
		dispose();
	};

	@Override
	public void moveBack() {
		int x = getX();
		int y = getY();
		dispose();
		pageList.get(pageList.size() - 1).arrange(id, x, y);
		pageList.remove(pageList.size() - 1);
		System.out.println("pageList vector size : " + pageList.size());
	};

	@Override
	public void arrange(String id, int x, int y) {
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (personalP_onOff == 1) {
			//System.out.println("끄기");
			pm.personalM_off(); // 개인정보 부속 메뉴가 켜있으면 어디를 클릭해도 제거됨
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// 클릭 한 점의 좌표 console 출력
//		Object obj = e.getComponent();
//		if (obj == this) {
//			System.out.print("(x , y) :  " + "(" + (e.getX() - 8) + " , " + (e.getY() - 32) + ") \n");
//		} else {
//			System.out.print("(x , y) :  " + "(" + (e.getX()) + " , " + (e.getY()) + ") \n");
//		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	public static void main(String[] args) {
		TFrame1_null tf = new TFrame1_null();
		tf.setVisible(true);
	}
}
