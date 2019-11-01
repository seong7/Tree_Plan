///////////////////////// TFrame1 :  기본화면 + menu바  (1000, 1000)  ////////

package teamProject_Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TFrame1 extends JFrame  implements MouseListener, ActionListener{
	
	JPanel menuP2, personalP;
	JButton backB, searchB, homeB, newPjtB, qnaB, personalB, mgrB, calendarB, personalInfoB, logOutB;
	JTextField searchTf;
	GraphicsEnvironment ge;			// 모니터 픽셀 수 구하기 위한 클래스의 변수 선언
	int personalP_onOff;
	int personalMouse = 0;
	JPanel InnerFrameP;				// 전체 Frame Panel  (layout : GridBag)
	JPanel menuP1;						// 메뉴바 P1  (layout : GridBag)
	JPanel mainP;						//mainP _ 메인 화면 부분 Panel  (layout : GridBag)
	JScrollPane scrollPane;			//mainP 담을 JScrollPane
	
	public TFrame1() {
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(/* new GridBagLayout() *//* null */new BorderLayout());
		getContentPane().setBackground(new Color(255, 255, 255));
		
		// 개인 정보 버튼의 부속 정보 메뉴 Panel (수정필요)////////////////////////////////////
		personalP = new JPanel();
		personalP.addMouseListener(this); 	// 개인 정보 부속 메뉴 패널  MouseListener
		calendarB = new JButton("달력");
		calendarB.setSize(90, 25);
		calendarB.addMouseListener(this);		// 달력 버튼 MouseListener
		personalInfoB = new JButton("회원정보");
		personalInfoB.setSize(90, 25);
		personalInfoB.addMouseListener(this);	// 회원 정보 버튼 MouseListener
		logOutB = new JButton("Log-out");
		logOutB.setSize(90, 25);
		logOutB.addMouseListener(this);	// 로그아웃 버튼 MouseListener
		addMouseListener(this);			// 전체 화면에 MouseListener
		
		// 전체 Frame Panel  (layout : GridBag)
		InnerFrameP = new JPanel();
		InnerFrameP.addMouseListener(this);
		InnerFrameP.setBackground(new Color(255, 255, 255));
		getContentPane().add(InnerFrameP, BorderLayout.CENTER);
		GridBagLayout gbl_InnerFrameP = new GridBagLayout();
		gbl_InnerFrameP.columnWidths = new int[]{984, 0};
		gbl_InnerFrameP.rowHeights = new int[]{42, 725, 0, 0};
		gbl_InnerFrameP.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_InnerFrameP.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		InnerFrameP.setLayout(gbl_InnerFrameP);
		
		
		/// 메뉴바 P1  (layout : GridBag)
		menuP1 = new JPanel();
		GridBagConstraints gbc_menuP1 = new GridBagConstraints();
		gbc_menuP1.insets = new Insets(0, 0, 5, 0);
		gbc_menuP1.fill = GridBagConstraints.BOTH;
		gbc_menuP1.gridx = 0;
		gbc_menuP1.gridy = 0;
		InnerFrameP.add(menuP1, gbc_menuP1);
		menuP1.setLayout(new GridLayout(1, 0, 0, 0));
		
		/// 메뉴바 P2  (layout : absolute)
		menuP2 = new JPanel();
		menuP1.add(menuP2);
		menuP2.setBackground(new Color(255, 228, 225));
		searchTf = new JTextField(20);
		searchTf.setBackground(new Color(185, 226, 250));
		searchTf.setBorder(null);
		backB = new JButton("뒤로가기");
		searchB = new JButton("검색버튼");
		homeB = new JButton("홈");
		homeB.addActionListener(this);  		// 홈 버튼 ActionListener
		newPjtB = new JButton("새프로젝트");
		qnaB = new JButton("Q&A");
		personalB = new JButton("개인정보");
		personalB.addActionListener(this);		// 개인정보 버튼 ActionListener
		mgrB = new JButton("관리자모드");
		
		menuP2.setLayout(null);
		backB.setBounds(10, 5, 90, 25);
		menuP2.add(backB);
		searchTf.setBounds(105, 5, 200, 25);
		menuP2.add(searchTf);
		searchB.setBounds(310, 5, 90, 25);
		menuP2.add(searchB);
		homeB.setBounds(505, 5, 90, 25);
		menuP2.add(homeB);
		newPjtB.setBounds(600, 5, 90, 25);
		menuP2.add(newPjtB);
		qnaB.setBounds(695, 5, 90, 25);
		menuP2.add(qnaB);
		personalB.setBounds(790, 5, 90, 25);
		menuP2.add(personalB);
		mgrB.setBounds(885, 5, 90, 25);
		menuP2.add(mgrB);
		menuP2.addMouseListener(this);		// 메뉴바  패널 MouseListener
		

		//mainP _ 메인 화면 부분 Panel  (layout : GridBag)
		mainP = new JPanel();
		mainP.addMouseListener(this);
		mainP.setBackground(new Color(255, 255, 255));

		
		//mainP 담을 JScrollPane
		scrollPane = new JScrollPane(mainP);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBorder(null);
		mainP.setLayout(new GridLayout(1, 0, 0, 0));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		InnerFrameP.add(scrollPane, gbc_scrollPane);
		
		
		validate();
		
		////// 어떤 모니터이든 중앙에 열리기
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();					//모니터(픽셀)의 크기 구함
        int height = (int) ge.getMaximumWindowBounds().getHeight();
        int width = (int) ge.getMaximumWindowBounds().getWidth();
		setLocation((width/2-this.getWidth()/2), (height/2-this.getHeight()/2));  // 모니터의 중앙에 열리도록 설정
	}
	
	
	//////개인정보 부속 메뉴 ON 메소드
	public void personalM_on() {
		
		int pWidth = calendarB.getWidth();																				// Panel의 너비
		int pHeigt = calendarB.getHeight() + personalInfoB.getHeight() + logOutB.getHeight();		// Panel의 높이
		
		//personalP.setLayout(gl);
		personalP.add(calendarB);
		personalP.add(personalInfoB);
		personalP.add(logOutB);
		
		personalP.setBounds(733, 36, pWidth, pHeigt);
		getContentPane().add(personalP);
		repaint();
		validate();
		personalP_onOff =1;  //on 표시
	};
	
	//////개인정보 부속 메뉴 OFF 메소드
	public void personalM_off(){
		System.out.println("fefsd");
		remove(personalP);															
		repaint();
		validate();
		personalP_onOff = 0; //off 표시
	};
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if (personalP_onOff == 1) {
			personalM_off();  	// 개인정보 부속 메뉴가 켜있으면 어디를 클릭해도 제거됨
		}
		
		
		// 클릭 한 점의 좌표 console 출력
		Object obj = e.getComponent();
		if(obj == this) {
			System.out.print("(x , y) :  " +"(" + (e.getX()-8) + " , " + (e.getY()-32) + ") \n" );
		}else {
			System.out.print("(x , y) :  " +"(" + (e.getX()) + " , " + (e.getY()) + ") \n" );
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(e.getSource());
		Object obj = e.getSource();
		if(obj == personalB && personalP_onOff ==1) {					// 개인정보 부속메뉴 on 일 때 누르면 off 됨
			personalM_off();
		}else if(obj == personalB){												// 개인정보 부속메뉴 on
			personalM_on();
		}
		
		///// 홈버튼 /////
		if(obj == homeB) {
			
		}
	}
	
	
	
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	
	
	public void remove() {};			// 상속을 위한 메소드
	
	public void arrange() {};			// 상속을 위한 메소드
	
	public static void main(String[] args) {
		new TFrame1().setVisible(true);
	}
}