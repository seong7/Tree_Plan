//////����/////////////////////////7�� �� ��ǥ �����ϱ�////////////////////////////

package teamProject_Projects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import teamProject_Frame.TFrame2;

public class TeamPlanCreate extends TFrame2 implements ItemListener {

	JPanel planTitleP, titleP, photoP, roomDetailP, endP;
	JLabel planTitleL, planNameL, photoL, imageL, openStatusL, /* hashTagL, */ endDayL, DdayL, numPeopleL;
	JTextField planNameT, photoT, /* hashTagT, */ endDayT;
	JPasswordField roomPwdT;
	JButton photoB, createB;
	JCheckBox openCB, closeCB, DdayCB;
	JComboBox numPeopleC;
	//String id;

	public TeamPlanCreate(/* String id */) {
		//this.id = id;

		// 1��
		planTitleP = new JPanel();
		planTitleP.addMouseListener(this);
		planTitleL = new JLabel("���ο� �� ��ǥ �����");


		// 2��
		titleP = new JPanel();
		titleP.addMouseListener(this);
		planNameL = new JLabel("��ǥ �̸�");
		planNameT = new JTextField();

		// 3��
		photoP = new JPanel();
		photoL = new JLabel("����");
		imageL = new JLabel();
		photoT = new JTextField(/* "Team1/aaa.png", */ 30);
		photoB = new JButton("÷    ��");

		// 4��
		roomDetailP = new JPanel();
		openStatusL = new JLabel("��������");

		openCB = new JCheckBox("����");
		closeCB = new JCheckBox("�����");

		roomPwdT = new JPasswordField("", 20);
//		hashTagL = new JLabel("�ؽ��±�");
//		hashTagT = new JTextField("# # # #");
		numPeopleL = new JLabel("�����ο�");
		numPeopleC = new JComboBox();

		// 5��
		endP = new JPanel();
		endDayL = new JLabel("������");
		endDayT = new JTextField();
//		DdayL = new JLabel("D-day ǥ��");
		DdayCB = new JCheckBox();
		createB = new JButton("�����");

	}

	// @Override
	public void arrange(String id, int x, int y) {
		setLayout(null);

		// 1��
		add(planTitleP);
		setLayout(null);
		planTitleP.add(planTitleL);
		planTitleP.setBounds(0, 50, 1000, 50);
		planTitleP.setBackground(new Color(211, 221, 179));
		planTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		planTitleL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 30));
		planTitleL.setBackground(new Color(211, 221, 179));

		// 2�� Ÿ��ƲPanel
		add(titleP);
		titleP.setBounds(90, 120, 800, 100);
		titleP.setLayout(null);
		titleP.add(planNameL);
		titleP.add(planNameT);
		planNameL.setBounds(40, 30, 90, 41);
		planNameL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		planNameT.setBounds(160, 27, 500, 50);
		planNameT.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		titleP.setBackground(new Color(238, 242, 225));

		// 3�� ����Panel
		add(photoP);
		photoP.setLayout(null);
		photoP.setBounds(90, 220, 800, 130);
		photoP.setBackground(new Color(238, 242, 225));
		photoP.add(photoL);
		photoP.add(imageL);
		photoP.add(photoT);
		photoP.add(photoB);
		photoL.setBounds(40, 10, 90, 100);
		photoL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		String s1 = "teamProject_Projects/tGoalBasic.png";
		ImageIcon ss1 = new ImageIcon(new ImageIcon(s1).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH));
		imageL.setIcon(ss1);
		imageL.setBounds(170, 0,130, 130);
		imageL.setOpaque(true);
		imageL.setBackground(Color.white);
		photoT.setBounds(420, 45, 230, 30);
		photoT.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		photoB.setBounds(665, 44, 100, 30);
		photoB.setBackground(new Color(211, 221, 179));
		photoB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		photoB.addActionListener(this);

		// 4�� �뼳��Panel
		add(roomDetailP);
		roomDetailP.setBounds(90, 350, 800, 190);
		roomDetailP.setLayout(null);
		roomDetailP.setBackground(new Color(238, 242, 225));
		roomDetailP.add(openStatusL);
		openStatusL.setBounds(40, 20, 90, 30);
		openStatusL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		roomDetailP.add(openCB);
		openCB.setBounds(150, 20, 70, 30);
		openCB.addItemListener(this);
		openCB.setSelected(true);
		openCB.setBackground(new Color(238, 242, 225));
		roomDetailP.add(closeCB);
		closeCB.setBounds(270, 20, 70, 30);
		closeCB.addItemListener(this);
		closeCB.setBackground(new Color(238, 242, 225));
		roomDetailP.add(roomPwdT);
		roomPwdT.setBounds(390, 20, 150, 30);
		// roomPwdT.setEchoChar((char)0);
		roomPwdT.setEnabled(false);

//		roomDetailP.add(hashTagL);
//		hashTagL.setBounds(40, 110, 90, 30);
//		hashTagL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
//		roomDetailP.add(hashTagT);
//		hashTagT.setBounds(150, 100, 300, 50);
//		hashTagT.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));

		roomDetailP.add(numPeopleL);
		numPeopleL.setBounds(40, 120, 90, 30);
		numPeopleL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		for (int i = 1; i <= 20; i++) {
			numPeopleC.addItem(i);
		}
		roomDetailP.add(numPeopleC);
		numPeopleC.setBounds(150, 125, 60, 20);

		// 5��
		add(endP);
		endP.setLayout(null);
		endP.setBounds(90, 540, 800, 100);
		endP.setBackground(new Color(238, 242, 225));
		endP.add(endDayL);
		endDayL.setBounds(40, 35, 90, 20);
		endDayL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		endP.add(endDayT);
		endDayT.setBounds(150, 35, 120, 30);
		endDayT.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		endDayT.setToolTipText("8�ڸ� ����   \"��) 20191231\" ");

		/////// �ܺ� Ŭ����(���ڸ� �Է¹ް� �˸�â �ߴ� �� ������ Ŭ����)
		endDayT.setDocument(new JTextFieldLimit());

		/////////// �͸�Ŭ������ �Է� ���� ��(8����) ���� ��Ű�� �ι�
		endDayT.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if (src.getText().length() >= 8)
					ke.consume();
			}
		});

		endP.add(createB);
		createB.setBounds(630, 25, 150, 50);
		createB.addActionListener(this);
		createB.setBackground(new Color(211, 221, 179));
		createB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		validate();

		setLocation(x, y);
		setVisible(true);
	}


	//////////////// DB �����ϱ� ���� ���� �� ���� ����
	Vector<TeamProject_Projects_Bean> vlist = new Vector();
	TeamProject_Projects_Mgr mgr = new TeamProject_Projects_Mgr();
	TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH) + 1;
	int day = now.get(Calendar.DAY_OF_MONTH);

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object obj = e.getSource();
		int team = 0; // tea �÷��� �� (������, ������� �� ��1)

		// �����ϱ� ��ư //
		if (obj == createB) {
			int flag = 0;
			if (planNameT.getText().length() == 0) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "�� ��ǥ���� �Է��ϼ���!", "Message", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (endDayT.getText().length() == 0) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "�������� �Է��ϼ���!", "Message", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (endDayT.getText().length() < 8) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "�������� 8�ڸ��� �����Դϴ�", "Message", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (year * 10000 + month * 100 + day > Integer.parseInt(endDayT.getText().trim())) {
				JOptionPane.showMessageDialog(null, "�������� ���ú��� ���� �� �����ϴ�.", "PLAN TREE", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(closeCB.isSelected() && roomPwdT.getPassword().length==0) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "����� ��ǥ�� ��й�ȣ�� �Է��ϼ���", "�� ��ǥ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			else if (flag == 0) {
				int result = JOptionPane.showConfirmDialog(null, "�� ��ǥ�� �����մϴ�.", "Message",
						JOptionPane.OK_CANCEL_OPTION);
				
				
				///////  DB ����
				if (result == 0) {
					String Syear = Integer.toString(year);
					String Smonth = Integer.toString(month);
					String Sday = Integer.toString(day);

//						planNameT, photoT, hashTagT, endDayT

					bean.setPjtName(planNameT.getText().trim());
					bean.setCreator_id(id);

					if (openCB.isSelected()) { // �������� ��
						bean.setPassword(null); // ���й�ȣ : null
					} else if (closeCB.isSelected()) { // ��������� ��
						char[] pwd = roomPwdT.getPassword(); // ���й�ȣ ��������
						String pwdS = "";
						for (int i = 0; i < pwd.length; i++) {
							pwdS += pwd[i];
						}
						bean.setPassword(pwdS);
					}
					bean.setTeam(1);
					bean.setPhoto(photoT.getText().trim());
					if (photoT.getText().trim().length() == 0 /* photo null�϶� */) {
						bean.setPhoto("teamProject_Projects/tGoalBasic.png");
					}
					bean.setStartDate(Syear + "-" + Smonth + "-" + Sday);
					
					String endYear = endDayT.getText().trim().substring(0,4);
					String endMonth = endDayT.getText().trim().substring(4,6);
					String endDay = endDayT.getText().trim().substring(6,8);
					bean.setEndDate(endYear + "-" + endMonth + "-"+ endDay);
					
					bean.setNumMax((int) numPeopleC.getSelectedItem());
					bean.setNumOfPpl(0);
//					bean.setHashtag(hashTagT.getText().trim());
					
					boolean insertPjt = mgr.insertProject(bean);

					///////// project member ��� �߰�
					if (insertPjt) {
						bean.setPjtIndex(mgr.getProjectIndex());
						bean.setMemId(id);
						bean.setJoinDate(Syear + "-" + Smonth + "-" + Sday);
						boolean insertMember = mgr.insertProjectMember(bean);
					}
					//

					new TeamProjects(mgr.getProjectIndex()).arrange(id, getX(), getY());
					//saveThisPage();
					dispose();
				}
			}
			return;
		}
		
		
		/// ÷�� ��ư ///
		if (e.getSource() == photoB) {
			JFileChooser jc = new JFileChooser();
//		jc.showOpenDialog(this); // ����
			if (jc.showOpenDialog(this) == jc.APPROVE_OPTION) { // ���� ���� ��ư ���������
				File f = jc.getSelectedFile(); // ���õ� ������ ������
				photoT.setText(f.getPath()); // ��θ� ������
				fileSave(f, "c:\\java", f.getName());
				String filePath = jc.getSelectedFile().getPath();
				ImageIcon icon = new ImageIcon(filePath);
				Image img1 = icon.getImage();
				Image img2 = img1.getScaledInstance(imageL.getWidth(), imageL.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img2);
				imageL.setIcon(icon);
			}
			return;
		}
	}

	// ���� ���� ���α׷�
	public void fileSave(File file, String path, String name) {
		try {
			File f = new File(path); // ���丮�� ����
			if (!f.exists()) {// ������ ���� ���� �ʴ´ٸ�
				f.mkdir(); // upload���� ����
			}

			// ���� ����
			String filePath = path + "\\" + name;

			// ���� �б�
			FileInputStream fis = new FileInputStream(file);
			// ���� ����
			FileOutputStream fos = new FileOutputStream(filePath);

			int i = 0; // ���� ���� ����Ʈ ���� üũ�ϱ� ���� ����
			byte[] buffer = new byte[1024];

			while ((i = fis.read(buffer, 0, 1024)) != -1) {// -1 = EOF(End of File)
				fos.write(buffer, 0, i); // ���� ������ŭ ���
			}

			fis.close();
			fos.close();

		} catch (Exception ex) {
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getItem();
		// boolean flag[] = new boolean[1];

		if (obj == closeCB) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				roomPwdT.setEnabled(true);
				openCB.setSelected(false);
				roomPwdT.requestFocus();
			}
		} else if (obj == openCB) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				closeCB.setSelected(false);
				roomPwdT.setEnabled(false);
			}
		}
	}

	public static void main(String[] args) {
		new TeamPlanCreate().arrange("",0, 0);
	}
}
