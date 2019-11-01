///////////////////////// TFrame1 :  �⺻ȭ�� + menu��  (1000, 1000)  ////////

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

//import team_p1_�����ڸ��.MemAdmin;

public class TFrame1_null extends JFrame implements MouseListener, ActionListener, PageList_TFrame1_2 {

	JPanel menuP, personalP;
	public JButton backB, searchB, homeB, newPjtB, qnaB, personalB, mgrB, personalInfoB, logOutB;
	public JTextField searchTf;
	GraphicsEnvironment ge; // ����� �ȼ� �� ���ϱ� ���� Ŭ������ ���� ����
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

		// ���� ���� ��ư�� �μ� ���� �޴� Panel (�����ʿ�)////////////////////////////////////
		personalP = new JPanel();
		personalP.addMouseListener(this); // ���� ���� �μ� �޴� �г� MouseListener
		personalInfoB = new JButton("ȸ������");
		personalInfoB.setSize(90, 15);
		personalInfoB.setBackground(Color.WHITE);
		logOutB = new JButton("Log-out");
		logOutB.setSize(90, 15);
		logOutB.setBackground(Color.WHITE);
		
		
		logOutB.addMouseListener(this); // �α׾ƿ� ��ư MouseListener
		logOutB.addActionListener(this); // �α׾ƿ� ��ư ActionListener
		personalInfoB.addMouseListener(this); // ȸ�� ���� ��ư MouseListener
		personalInfoB.addActionListener(this); // ȸ�� ���� ��ư ActionListener
		
		
		/////////////////////////////////// Menu Bar �г�
		/////////////////////////////////// /////////////////////////////////////

		/* �г� */
		menuP = new JPanel();
		menuP.setBounds(0, 0, 1000, 38);
		add(menuP);
		menuP.setBackground(new Color(255, 255, 255));

		/* �˻�â */
		searchTf = new JTextField(20);
		searchTf.setBackground(new Color(185, 226, 250));
		searchTf.setBorder(null);

		/* �ڷΰ��� ��ư */
		backB = new JButton("");
		backB.setIcon(new ImageIcon("teamProject_Frame/left-arrow.png"));
		backB.setBorderPainted(false);
		backB.setFocusPainted(false);
		backB.setContentAreaFilled(false);

		/* �˻� ��ư */
		searchB = new JButton("");
		searchB.setIcon(new ImageIcon("teamProject_Frame/magnifying-glass.png"));
		searchB.setBorderPainted(false);
		searchB.setFocusPainted(false);
		searchB.setContentAreaFilled(false);

		/* Ȩ ��ư */
		homeB = new JButton("");
		homeB.setIcon(new ImageIcon("teamProject_Frame/homepage.png"));
		homeB.setBorderPainted(false);
		homeB.setFocusPainted(false);
		homeB.setContentAreaFilled(false);

		/* �� ������Ʈ �߰� ��ư */
		newPjtB = new JButton("");
		newPjtB.setIcon(new ImageIcon("teamProject_Frame/add-file.png"));
		newPjtB.setBorderPainted(false);
		newPjtB.setFocusPainted(false);
		newPjtB.setContentAreaFilled(false);

		/* QnA ��ư */
		qnaB = new JButton("");
		qnaB.setIcon(new ImageIcon("teamProject_Frame/help.png"));
		qnaB.setBorderPainted(false);
		qnaB.setFocusPainted(false);
		qnaB.setContentAreaFilled(false);

		/* �������� ��ư */
		personalB = new JButton("");
		personalB.setIcon(new ImageIcon("teamProject_Frame/profile.png"));
		personalB.setBorderPainted(false);
		personalB.setFocusPainted(false);
		personalB.setContentAreaFilled(false);

		/* ������ ��� ��ư */
		mgrB = new JButton("");
		mgrB.setIcon(new ImageIcon("teamProject_Frame/admin-with-cogwheels.png"));
		mgrB.setBorderPainted(false);
		mgrB.setFocusPainted(false);
		mgrB.setContentAreaFilled(false);

		/* menu button �� EventListener �߰� */
		backB.addActionListener(this);
		searchB.addActionListener(this);
		homeB.addActionListener(this);
		newPjtB.addActionListener(this);
		qnaB.addActionListener(this);
		personalB.addActionListener(this);
		mgrB.addActionListener(this);
		menuP.addMouseListener(this);
		/* Event Listener �Ϸ� */

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

		/////////////////////////////////// Main Panel : ���� ȭ�� �κ� �г�
		/////////////////////////////////// ////////////////////////////////////////////////////////////

		MainP.setBounds(0, menuP.getY() + menuP.getHeight(), getWidth(), getHeight() - 35);
		add(MainP);
		MainP.setLayout(null);
		menuP.addMouseListener(this);
		MainP.addMouseListener(this);
		validate();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////

		// � ������̵� �߾ӿ� ������
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); // �����(�ȼ�)�� ũ�� ����
		int height = (int) ge.getMaximumWindowBounds().getHeight();
		int width = (int) ge.getMaximumWindowBounds().getWidth();
		setLocation((width / 2 - this.getWidth() / 2), (height / 2 - this.getHeight() / 2)); // ������� �߾ӿ� �������� ����
		
		pm = new personalMenu();
		personalInfoB.addMouseListener(this);
		logOutB.addMouseListener(this);
	}

	///////////////// �������� �μӸ޴� ���� Ŭ���� ///////////////////
	class personalMenu extends JFrame{
		int pWidth = personalInfoB.getWidth(); // Panel�� �ʺ�
		int pHeigt = personalInfoB.getHeight() + personalInfoB.getHeight() + logOutB.getHeight(); // Panel�� ����
		int pX = personalB.getX();

		public void personalM_on(int x, int y) { // �������� �μ� �޴� ON �޼ҵ�
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

			personalP_onOff = 1; // on ǥ��
			//System.out.println(personalP_onOff);
			super.setType(javax.swing.JFrame.Type.UTILITY);
			
			
			setVisible(true);
		}

		public void personalM_off() { // �������� �μ� �޴� OFF �޼ҵ�
			super.dispose();
			personalP_onOff = 0; // off ǥ��
			//System.out.println(personalP_onOff);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (personalP_onOff == 1) {
			//System.out.println("����");
			pm.personalM_off(); // �������� �μ� �޴��� �������� ��� Ŭ���ص� ���ŵ�]
			//return;
		}

////////////////////////////////////////////////// MENU BUTTON Ȱ��ȭ ////////////////////////////////////////////////////////////////////		

		///// Ȩ��ư ��� /////
		if (obj == homeB) {
			// System.out.println("Ȩ");
			new HomeProjects().arrange(id, getX(), getY());
			saveThisPage();
			return;
		}

		/// �˻� ��ư ��� /////
		else if (obj == searchB) {
			// System.out.println("�˻�");
			new teamProject_Projects.TeamSearch(searchTf.getText().trim()).arrange(id, getX(), getY());
			saveThisPage();
			return;
		}

		//// ��������Ʈ �߰� ��ư ��� /////
		if (obj == newPjtB) {
			new NewGoalCreate().arrange(id, getX(), getY());
			saveThisPage();
			return;
		}

		//// QnA ��ư ��� ////
		if (obj == qnaB) {
			QnA qna = new QnA();
			qna.arrange(id, getX(), getY());
			saveThisPage();
			return;
		}

		//// ���� ���� �μ� �޴� ��ư ��� /////
		if (obj == personalB && personalP_onOff == 1) { // �������� �μӸ޴� on �� �� ������ off ��
			pm.personalM_off();
			return;
		} else if (obj == personalB) { // �������� �μӸ޴� on
			pm.personalM_on(this.getX() + 800, this.getY() + 70);
			return;
		}

		//// ������ ��� //////
		if (obj == mgrB) {
			MemAdmin ma = new MemAdmin();
			ma.arrange(id, getX(), getY());
			saveThisPage();
			return;
		}

		//////// �α׾ƿ� ��ư////
		if (obj == logOutB) {
			int result = JOptionPane.showConfirmDialog(null, "�α׾ƿ� �Ͻðڽ��ϱ�?", "�α׾ƿ� Ȯ��â", JOptionPane.OK_CANCEL_OPTION);
			if (result == 0) {
				new TreePlan_exe();
				idMemory.removeAllElements();
				dispose();
				return;
			}
			return;
		}

		//////// ȸ������ ���� ��ư ////
		if (obj == personalInfoB) {
			new Modification().arrange(id, getX(), getY());
			return;
		}

		//// �ڷΰ��� ��ư ��� /////
		if (obj == backB) {
			if (pageList.size() == 0) {
				//System.out.println("�ڷ� �� ȭ�� ����");
				return;
			} else {
				//System.out.println("�ڷ� ����");
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
			//System.out.println("����");
			pm.personalM_off(); // �������� �μ� �޴��� �������� ��� Ŭ���ص� ���ŵ�
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Ŭ�� �� ���� ��ǥ console ���
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
