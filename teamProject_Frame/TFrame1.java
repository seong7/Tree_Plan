///////////////////////// TFrame1 :  �⺻ȭ�� + menu��  (1000, 1000)  ////////

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
	GraphicsEnvironment ge;			// ����� �ȼ� �� ���ϱ� ���� Ŭ������ ���� ����
	int personalP_onOff;
	int personalMouse = 0;
	JPanel InnerFrameP;				// ��ü Frame Panel  (layout : GridBag)
	JPanel menuP1;						// �޴��� P1  (layout : GridBag)
	JPanel mainP;						//mainP _ ���� ȭ�� �κ� Panel  (layout : GridBag)
	JScrollPane scrollPane;			//mainP ���� JScrollPane
	
	public TFrame1() {
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(/* new GridBagLayout() *//* null */new BorderLayout());
		getContentPane().setBackground(new Color(255, 255, 255));
		
		// ���� ���� ��ư�� �μ� ���� �޴� Panel (�����ʿ�)////////////////////////////////////
		personalP = new JPanel();
		personalP.addMouseListener(this); 	// ���� ���� �μ� �޴� �г�  MouseListener
		calendarB = new JButton("�޷�");
		calendarB.setSize(90, 25);
		calendarB.addMouseListener(this);		// �޷� ��ư MouseListener
		personalInfoB = new JButton("ȸ������");
		personalInfoB.setSize(90, 25);
		personalInfoB.addMouseListener(this);	// ȸ�� ���� ��ư MouseListener
		logOutB = new JButton("Log-out");
		logOutB.setSize(90, 25);
		logOutB.addMouseListener(this);	// �α׾ƿ� ��ư MouseListener
		addMouseListener(this);			// ��ü ȭ�鿡 MouseListener
		
		// ��ü Frame Panel  (layout : GridBag)
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
		
		
		/// �޴��� P1  (layout : GridBag)
		menuP1 = new JPanel();
		GridBagConstraints gbc_menuP1 = new GridBagConstraints();
		gbc_menuP1.insets = new Insets(0, 0, 5, 0);
		gbc_menuP1.fill = GridBagConstraints.BOTH;
		gbc_menuP1.gridx = 0;
		gbc_menuP1.gridy = 0;
		InnerFrameP.add(menuP1, gbc_menuP1);
		menuP1.setLayout(new GridLayout(1, 0, 0, 0));
		
		/// �޴��� P2  (layout : absolute)
		menuP2 = new JPanel();
		menuP1.add(menuP2);
		menuP2.setBackground(new Color(255, 228, 225));
		searchTf = new JTextField(20);
		searchTf.setBackground(new Color(185, 226, 250));
		searchTf.setBorder(null);
		backB = new JButton("�ڷΰ���");
		searchB = new JButton("�˻���ư");
		homeB = new JButton("Ȩ");
		homeB.addActionListener(this);  		// Ȩ ��ư ActionListener
		newPjtB = new JButton("��������Ʈ");
		qnaB = new JButton("Q&A");
		personalB = new JButton("��������");
		personalB.addActionListener(this);		// �������� ��ư ActionListener
		mgrB = new JButton("�����ڸ��");
		
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
		menuP2.addMouseListener(this);		// �޴���  �г� MouseListener
		

		//mainP _ ���� ȭ�� �κ� Panel  (layout : GridBag)
		mainP = new JPanel();
		mainP.addMouseListener(this);
		mainP.setBackground(new Color(255, 255, 255));

		
		//mainP ���� JScrollPane
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
		
		////// � ������̵� �߾ӿ� ������
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();					//�����(�ȼ�)�� ũ�� ����
        int height = (int) ge.getMaximumWindowBounds().getHeight();
        int width = (int) ge.getMaximumWindowBounds().getWidth();
		setLocation((width/2-this.getWidth()/2), (height/2-this.getHeight()/2));  // ������� �߾ӿ� �������� ����
	}
	
	
	//////�������� �μ� �޴� ON �޼ҵ�
	public void personalM_on() {
		
		int pWidth = calendarB.getWidth();																				// Panel�� �ʺ�
		int pHeigt = calendarB.getHeight() + personalInfoB.getHeight() + logOutB.getHeight();		// Panel�� ����
		
		//personalP.setLayout(gl);
		personalP.add(calendarB);
		personalP.add(personalInfoB);
		personalP.add(logOutB);
		
		personalP.setBounds(733, 36, pWidth, pHeigt);
		getContentPane().add(personalP);
		repaint();
		validate();
		personalP_onOff =1;  //on ǥ��
	};
	
	//////�������� �μ� �޴� OFF �޼ҵ�
	public void personalM_off(){
		System.out.println("fefsd");
		remove(personalP);															
		repaint();
		validate();
		personalP_onOff = 0; //off ǥ��
	};
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if (personalP_onOff == 1) {
			personalM_off();  	// �������� �μ� �޴��� �������� ��� Ŭ���ص� ���ŵ�
		}
		
		
		// Ŭ�� �� ���� ��ǥ console ���
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
		if(obj == personalB && personalP_onOff ==1) {					// �������� �μӸ޴� on �� �� ������ off ��
			personalM_off();
		}else if(obj == personalB){												// �������� �μӸ޴� on
			personalM_on();
		}
		
		///// Ȩ��ư /////
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
	
	
	public void remove() {};			// ����� ���� �޼ҵ�
	
	public void arrange() {};			// ����� ���� �޼ҵ�
	
	public static void main(String[] args) {
		new TFrame1().setVisible(true);
	}
}