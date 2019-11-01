//��������////���� Ȱ��ȭ���ѵ� �۾� ������, ���� ����� �Ѵټ��ð���///////6�� ���� �ȿ� ��/////////////////////////

package teamProject_Projects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import teamProject_Frame.TFrame2;
import teamProject_Member.TeamProject_Member_Mgr;

public class TeamGoal extends TFrame2{

	JPanel titleP, roomDetailP, detailP, endP, planTitleP;
	JLabel planNameL, titleNameL, imageL, openStatusL, /* hashTagL, */ /* alarmL[], */ endDayL, detailL, numPeopleL,
			numPeople2L, planTitleL, dayL[];
	JTextField photoT, /* hashTagT, */ endDayT, dayT[];
	JButton leaveB, joinB, detailPlanSaveB, teamGoalUpdateB, teamGoalDeleteB;
	JToggleButton dayB[];
	JCheckBox openCB, closeCB;
	JComboBox numPeopleC;
	String dayStr[] = { "��", "ȭ", "��", "��", "��", "��", "��", "����" };
//	JComboBox ourCB[], minuteCB[];
	boolean flag[] = new boolean[8];
	
	// DB ����� ���� ����
	Vector <TeamProject_Projects_Bean> vlist;
	TeamProject_Projects_Bean bean;
	TeamProject_Projects_Mgr mgr;
	int projectIndex;

	public TeamGoal(int projectIndex) {
		this.projectIndex = projectIndex;

		planTitleP = new JPanel();
		planTitleL = new JLabel("�� ��ǥ");
		// 1��
		titleP = new JPanel();
		imageL = new JLabel("image ���");
		titleNameL = new JLabel();
		openStatusL = new JLabel("��������");
		openCB = new JCheckBox("����");
		openCB.setEnabled(false);
		closeCB = new JCheckBox("�����");
		closeCB.setEnabled(false);
		
		// 2��
		roomDetailP = new JPanel();
		//hashTagL = new JLabel("�ؽ��±�");
		//hashTagT = new JTextField("# # # #");
		//hashTagT.setEnabled(false);
		numPeopleL = new JLabel("�����ο�");
		numPeople2L = new JLabel();				
		endDayL = new JLabel("������");
		endDayT = new JTextField();
		endDayT.setEnabled(false);

		// 3 detailP
		detailP = new JPanel();
		detailL = new JLabel("���θ�ǥ");
		dayB = new JToggleButton[8];
		dayL = new JLabel[8];
		dayT = new JTextField[8];
//		alarmL = new JLabel[8];
//		ourCB = new JComboBox[8];
//		minuteCB = new JComboBox[8];

		// 4��
		endP = new JPanel();
		joinB = new JButton("�����ϱ�");
		joinB.addActionListener(this);
		leaveB = new JButton("Ż���ϱ�");
		leaveB.addActionListener(this);
		detailPlanSaveB = new JButton("���� ��ǥ ����");
		teamGoalUpdateB = new JButton("����ǥ ����");
		teamGoalDeleteB = new JButton("����ǥ ����");
		teamGoalDeleteB.addActionListener(this);
	}

	@Override
	public void arrange(String id, int x, int y) {
		
		
//		System.out.println(" current ID  =   [ "   + id   +  " ]  ... !!");
		
		add(planTitleP);
		planTitleP.add(planTitleL);
		planTitleP.setBounds(0, 50, 1000, 50);
		planTitleP.setBackground(new Color(211, 221, 179));
		planTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		planTitleL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 30));
		planTitleL.setBackground(new Color(211, 221, 179));
		// 1��
		add(titleP);
		titleP.setLayout(null);
		titleP.setBounds(90, 120, 800, 185);
		titleP.setBackground(new Color(238, 242, 225));

		titleP.add(imageL);
		imageL.setBounds(40, 20, 200, 200);
		titleP.add(titleNameL);
		titleNameL.setBounds(300, 45, 600, 50);
		titleNameL.setHorizontalAlignment(SwingConstants.LEFT);
		titleNameL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 30));
		titleNameL.setOpaque(true);
//		titleNameL.setBackground(Color.red);
		titleNameL.setBackground(new Color(238, 242, 225));
		titleP.add(openStatusL);
		openStatusL.setBounds(300, 150, 80, 30);
		openStatusL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		titleP.add(openCB);
		openCB.setBounds(400, 150, 80, 30);
		openCB.setBackground(new Color(238, 242, 225));
		openCB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		titleP.add(closeCB);
		closeCB.setBounds(480, 150, 80, 30);
		closeCB.setBackground(new Color(238, 242, 225));
		closeCB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));

		// 2��
		add(roomDetailP);
		roomDetailP.setBounds(90, 305, 800, 110);
		roomDetailP.setLayout(null);
		roomDetailP.setBackground(new Color(238, 242, 225));
		//roomDetailP.add(hashTagL);
		//hashTagL.setBounds(40, 10, 70, 30);
		//hashTagL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		//roomDetailP.add(hashTagT);
		//hashTagT.setBounds(150, 5, 300, 40);

		roomDetailP.add(numPeopleL);
		numPeopleL.setBounds(40, 20, 90, 30);
		numPeopleL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		roomDetailP.add(numPeople2L);
		numPeople2L.setBounds(150, 20, 100, 30);
		numPeople2L.setOpaque(true);
		numPeople2L.setBackground(new Color(238, 242, 225));
		numPeople2L.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		roomDetailP.add(endDayL);
		endDayL.setBounds(40, 70, 90, 30);
		endDayL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		roomDetailP.add(endDayT);
		endDayT.setBounds(150, 70, 120, 30);
		endDayT.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
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
		
		/* 3�� �������г� */
		add(detailP);
		add(detailP);
		detailP.setBounds(90, 415, 800, 295);
		detailP.setLayout(null);
//		detailP.setBackground(Color.blue);
		detailP.setBackground(new Color(238, 242, 225));
		detailP.add(detailL);
		detailL.setBounds(40, 110, 90, 100);
		detailL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));

		// day ��ư///////////////////////////////////////////////////
		for (int i = 0; i < dayStr.length; i++) {
			dayB[i] = new JToggleButton(dayStr[i]);
			detailP.add(dayB[i]);
			dayB[i].setBounds(140 + (80 * i), 10, 70, 30);
			dayB[i].setBackground(new Color(211, 221, 179));
			dayB[i].setFont(new Font("������������� ExtraBold", Font.PLAIN, 13));
			dayB[i].addActionListener(this);
		}

		// day ��///////////////////////////////////////////////////
		for (int i = 0; i < dayStr.length; i++) {
			dayL[i] = new JLabel(dayStr[i]);
			detailP.add(dayL[i]);
			dayL[i].setBounds(140, 55 + (30 * i), 60, 20);
			dayL[i].setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
			dayL[i].setEnabled(false);
		}

		// day �ؽ�Ʈ/////////////////////////////////////////////////
		for (int i = 0; i < dayStr.length; i++) {
			dayT[i] = new JTextField();
			detailP.add(dayT[i]);
			dayT[i].getText().trim();
			dayT[i].setBounds(210, 55 + (30 * i), 400, 25);
			dayT[i].setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
			dayT[i].setEnabled(false);
		}

//		// �˸� ��///////////////////////////////////////////////////
//		for (int i = 0; i < dayStr.length; i++) {
//			alarmL[i] = new JLabel("�˸�");
//			detailP.add(alarmL[i]);
//			alarmL[i].setBounds(550, 55 + (30 * i), 60, 20);
//			alarmL[i].setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
//			// alarmL.setHorizontalAlignment(SwingConstants.CENTER);
//			alarmL[i].setEnabled(false);
//		}
//
//		// �˸� �ð�&�� �޺��ڽ�////////////////////////////////
//		for (int i = 0; i < ourCB.length; i++) {
//			ourCB[i] = new JComboBox();
//			detailP.add(ourCB[i]);
//			ourCB[i].setBounds(620, 55 + (30 * i), 60, 25);
//			ourCB[i].setEnabled(false);
//			for (int j = 0; j <= 23; j++) {
//				ourCB[i].addItem(j + "��");
//			}
//		}
//		for (int i = 0; i < minuteCB.length; i++) {
//			minuteCB[i] = new JComboBox();
//			detailP.add(minuteCB[i]);
//			minuteCB[i].setBounds(690, 55 + (30 * i), 60, 25);
//			minuteCB[i].setEnabled(false);
//			for (int j = 0; j < 60; j++) {
//				minuteCB[i].addItem(j + "��");
//			}
//		}
		// alarmL.setHorizontalAlignment(SwingConstants.CENTER);
		////////////////////////////////////////////////////////

		/* 4�� �����г� */
		add(endP);
		endP.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		endP.setBounds(90, 710, 800, 40);
		endP.setBackground(Color.white);
		// joinB.setEnabled(false);		
		// withdrawalB.setEnabled(false);
		detailPlanSaveB.addActionListener(this);
//	    detailPlanSaveB.setEnabled(false);
		teamGoalUpdateB.addActionListener(this);
//	    teamGoalUpdateB.setEnabled(false);
		// teamGoalDeleteB.setEnabled(false);
		joinB.setBackground(new Color(211, 221, 179));
		leaveB.setBackground(new Color(211, 221, 179));
		detailPlanSaveB.setBackground(new Color(211, 221, 179));

		teamGoalUpdateB.setBackground(new Color(211, 221, 179));
		teamGoalDeleteB.setBackground(new Color(211, 221, 179));
		joinB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		leaveB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		detailPlanSaveB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		teamGoalUpdateB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		teamGoalDeleteB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));

		////// DB �����Ͽ� �� ����ϱ�
		
		mgr = new TeamProject_Projects_Mgr ();
		
		vlist = mgr.showProject(projectIndex);
		
		titleNameL.setText(vlist.get(0).getPjtName());
		String s1 = vlist.get(0).getPhoto();
		ImageIcon ss1 = new ImageIcon(new ImageIcon(s1).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		imageL.setIcon(ss1);
		if(vlist.get(0).getPassword().length() == 0) {
			openCB.setSelected(true);
		}else {
			closeCB.setSelected(true);
		}
		String datex = vlist.get(0).getEndDate();
		String date = datex.substring(0, 4) + datex.substring(5, 7) + datex.substring(8, 10);
		numPeople2L.setText(vlist.get(0).getNumOfPpl() + " / " + vlist.get(0).getNumMax());
		endDayT.setText(date);

		vlist = mgr.showPjtDetail(projectIndex, id);

		for (int i = 0; i < dayL.length; i++) {
			dayL[i].getText();
			for (int j = 0; j < vlist.size(); j++) {
				if (vlist.get(j).getDays().equals(dayL[i].getText().trim())) {
					dayT[i].setText(vlist.get(j).getDetailPlan());
//					System.out.println(vlist.get(j).getDetailPlan());
				}
			}
		}
		
		///////////// id �� ���ѿ� ���� Button ��� ���� ���� ////////////
		//  1. ������ id �� ���� ���� üũ
		//  2. �������� ��� : ������ id �� ������ ���� üũ
		boolean isIDinPjt = mgr.isIDinPjt(projectIndex, id);  					// ���Կ��� üũ
		boolean isIdPjtCreator = mgr.isIdPjtCreator(projectIndex, id);		// �����ڿ��� üũ
		
		if(isIDinPjt == false) {		// ������
			//System.out.println("is " + id + " in  Pjt ?  :    [ " +  isIDinPjt + " ]");
			endP.add(joinB); 
		}else {
			if(isIdPjtCreator == true) {		// ������
				endP.add(detailPlanSaveB);
				endP.add(teamGoalUpdateB);
				endP.add(teamGoalDeleteB);
			}else {									// ������
				endP.add(leaveB);
				endP.add(detailPlanSaveB);
			}
		}
	
		
		
		/////////////////////////////////////////////////////////////////////
		
		setLocation(x, y);
		setVisible(true);
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object obj = e.getSource();
		
		
		if (obj == dayB[0]) {
			flag[0] = !flag[0];
			for (int i = 0; i < dayStr.length; i++) {
				dayL[i].setEnabled(flag[i]);
				dayT[i].setEnabled(flag[i]);
				// alarmL[i].setEnabled(flag[i]);
				// ourCB[i].setEnabled(flag[i]);
				// minuteCB[i].setEnabled(flag[i]);
			}
			return;
		} else if (obj == dayB[1]) {
			flag[1] = !flag[1];
			for (int i = 0; i < dayStr.length; i++) {
				dayL[i].setEnabled(flag[i]);
				dayT[i].setEnabled(flag[i]);
				// alarmL[i].setEnabled(flag[i]);
				// ourCB[i].setEnabled(flag[i]);
				// minuteCB[i].setEnabled(flag[i]);
			}
			return;
		} else if (obj == dayB[2]) {
			flag[2] = !flag[2];
			for (int i = 0; i < dayStr.length; i++) {
				dayL[i].setEnabled(flag[i]);
				dayT[i].setEnabled(flag[i]);
				// alarmL[i].setEnabled(flag[i]);
				// ourCB[i].setEnabled(flag[i]);
				// minuteCB[i].setEnabled(flag[i]);
			}
			return;
		} else if (obj == dayB[3]) {
			flag[3] = !flag[3];
			for (int i = 0; i < dayStr.length; i++) {
				dayL[i].setEnabled(flag[i]);
				dayT[i].setEnabled(flag[i]);
				// alarmL[i].setEnabled(flag[i]);
				// ourCB[i].setEnabled(flag[i]);
				// minuteCB[i].setEnabled(flag[i]);
			}
			return;
		} else if (obj == dayB[4]) {
			flag[4] = !flag[4];
			for (int i = 0; i < dayStr.length; i++) {
				dayL[i].setEnabled(flag[i]);
				dayT[i].setEnabled(flag[i]);
				// alarmL[i].setEnabled(flag[i]);
				// ourCB[i].setEnabled(flag[i]);
				// minuteCB[i].setEnabled(flag[i]);
			}
			return;
		} else if (obj == dayB[5]) {
			flag[5] = !flag[5];
			for (int i = 0; i < dayStr.length; i++) {
				dayL[i].setEnabled(flag[i]);
				dayT[i].setEnabled(flag[i]);
				// alarmL[i].setEnabled(flag[i]);
				// ourCB[i].setEnabled(flag[i]);
				// minuteCB[i].setEnabled(flag[i]);
			}
			return;
		} else if (obj == dayB[6]) {
			flag[6] = !flag[6];
			for (int i = 0; i < dayStr.length; i++) {
				dayL[i].setEnabled(flag[i]);
				dayT[i].setEnabled(flag[i]);
				// alarmL[i].setEnabled(flag[i]);
				// ourCB[i].setEnabled(flag[i]);
				// minuteCB[i].setEnabled(flag[i]);
			}
			return;
		}
//		else if (obj == dayB[7]) {
//			flag[7] = !flag[7];
//			for (int i = 0; i < dayStr.length; i++) {
//				dayL[i].setEnabled(flag[i]);
//				dayT[i].setEnabled(flag[i]);
//				//alarmL[i].setEnabled(flag[i]);
//				//ourCB[i].setEnabled(flag[i]);
//				//minuteCB[i].setEnabled(flag[i]);
//			}
//		}

		// ���� ��ư ������ �ٸ� ��ư ����
		else if (obj == dayB[7]) {
			if (dayB[7].isSelected()) {
				for (int i = 0; i < dayStr.length; i++) {
					flag[i] = false;
					dayB[i].setSelected(flag[i]);
					dayL[i].setEnabled(flag[i]);
					dayT[i].setEnabled(flag[i]);
					if (i == 7) {
						//System.out.println("77");
						dayL[i].setEnabled(!flag[i]);
						dayT[i].setEnabled(!flag[i]);
					}
				}
				return;
			} else {
				dayL[7].setEnabled(flag[7]);
				dayT[7].setEnabled(flag[7]);
			}
			return;
		}

		if(obj == joinB ||obj == detailPlanSaveB) {
			int x = 0; // ������ �ִ� ���������� �˻��ϰ� �� ���� ����
			int flag = 0; // �����Ǵ� ������ ������� 1�� ������.
			String day = "";
			
			for (int i = 0; i < 8; i++) {
				if (dayB[i].isSelected() == true) {
					x = i;
					//System.out.println(x);
					break;
				}
			}
			for (int i = x; i < 8; i++) {
				String s = dayT[i].getText().trim();

				if (dayL[i].isEnabled() == true && s.length() == 0) {
					flag = 1;
					day += dayB[i].getText() + "  ";
				}
			}
			if (flag == 1) {
				JOptionPane.showMessageDialog(null, "[  " + day + "]  �����׸���� ���� �־�� �մϴ�.", "�˸�",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		
		
		///////////////////// DB ����
		mgr = new TeamProject_Projects_Mgr();
		bean = new TeamProject_Projects_Bean();
		
		
		if (obj == joinB) {
			int result = JOptionPane.showConfirmDialog(null, "[ "+titleNameL.getText()+" ] ��ǥ�� �����Ͻðڽ��ϱ�?", "Tree Plan", JOptionPane.YES_NO_OPTION);
			if (result ==0) {
				Calendar now = Calendar.getInstance();
				int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH) + 1;
				int day = now.get(Calendar.DAY_OF_MONTH);
				String today = year + "-" + month + "-" + day;
				
				boolean flag = false;
				
				bean.setPjtIndex(projectIndex);
				bean.setMemId(id);
				bean.setJoinDate(today);
				
				flag = mgr.insertProjectMember(bean); 				// ������ ��ܿ� �߰�
				if (flag) { 														// ��ܿ� �߰� �����ϸ� detail plan �߰�
					for (int i = 0; i < dayT.length; i++) {
						if (dayT[i].isEnabled()) {
							bean.setDetailOnOff(1);
						} else if (!dayT[i].isEnabled()) {
							bean.setDetailOnOff(0);
						}
						bean.setDays(dayL[i].getText());
						bean.setDetailPlan(dayT[i].getText().trim());
						flag = mgr.insertDetailPlans(bean);
						if(flag==false) {
							JOptionPane.showMessageDialog(null, "������ �����߽��ϴ�. �ٽ� �õ����ּ���.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					JOptionPane.showMessageDialog(null, "[ "+titleNameL.getText()+" ] �� ���ԵǾ����ϴ�.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
					moveBack();
				}
			}
			return;
		}

		TeamProject_Member_Mgr mgr1 = new TeamProject_Member_Mgr();
		
		if (obj == leaveB) {
			int result = JOptionPane.showConfirmDialog(null, "������ �� ��ǥ�� �����ðڽ��ϱ�?", "Tree Plan", JOptionPane.YES_NO_OPTION);
			if(result == 0) {
				try {
					String userPwd = JOptionPane.showInputDialog(null, "��й�ȣ�� �Է����ּ���.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
					String dbPwd = mgr1.getPwd(id);
					if(userPwd.equals(dbPwd)) {
						boolean flag;
						flag = mgr.leavePjt(projectIndex, id);
						if(flag) {
							JOptionPane.showMessageDialog(null, "Ż�� �Ϸ�Ǿ����ϴ�.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
							moveBack();
						}else {
							JOptionPane.showMessageDialog(null, "Ż�� �� �� ���� ������ �����Ͽ����ϴ�.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� �ʽ��ϴ�.", "Tree Plan [ Locked ]", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (NullPointerException x) {
					return;
				}
			}
		}

		if (obj == teamGoalDeleteB) {
			int result = JOptionPane.showConfirmDialog(null, "��ǥ�� ������ ������ϴ�.\n������ �� ��ǥ�� �����Ͻðڽ��ϱ�?", "Tree Plan",
					JOptionPane.YES_NO_OPTION);
			if (result == 0) {
				JPanel panel = new JPanel();
				panel.setLayout(new BorderLayout(2, 1));
				JLabel label = new JLabel("ȸ������ ��й�ȣ�� �Է��ϼ���.");
				JPasswordField pass = new JPasswordField();
				panel.add(label, BorderLayout.CENTER);
				panel.add(pass, BorderLayout.SOUTH);
				String[] options = new String[] { "OK", "Cancel" };
				int option = JOptionPane.showOptionDialog(null, panel, "Plan Tree", JOptionPane.NO_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
				if (option == 0) // OK ������ ��
				{
					char[] password = pass.getPassword();
					String userPwd = "";
					for (int i = 0; i < password.length; i++) {
						userPwd += password[i];
					}
					try {
						String dbPwd = mgr1.getPwd(id);
						if (userPwd.equals(dbPwd)) {
							boolean flag;
							flag = mgr.deletePjt(projectIndex, id);
							if (flag) {
								JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.", "Tree Plan",
										JOptionPane.PLAIN_MESSAGE);
								dispose();
								new HomeProjects().arrange(id, getX(), getY());
								pageList.removeAllElements();
							} else {
								JOptionPane.showMessageDialog(null, "������ �� �� ���� ������ �����Ͽ����ϴ�.", "Tree Plan",
										JOptionPane.ERROR_MESSAGE);
							}
						} else {
							JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� �ʽ��ϴ�.", "Tree Plan",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
					} catch (NullPointerException x) {
						return;
					}
				}
			}
		}
		
		
		if (obj == detailPlanSaveB) {
			int result = JOptionPane.showConfirmDialog(null, "���������� �����Ͻðڽ��ϱ�?", "Tree Plan",
					JOptionPane.YES_NO_OPTION);
			if (result == 0) {
				boolean flag = false;
				for (int i = 0; i < dayT.length; i++) {
					if (dayT[i].isEnabled()) {
						bean.setDetailOnOff(1);
					} else if (!dayT[i].isEnabled()) {
						bean.setDetailOnOff(0);
					}
					bean.setDays(dayL[i].getText());
					bean.setDetailPlan(dayT[i].getText().trim());
					bean.setPjtIndex(projectIndex);
					bean.setMemId(id);
					flag = mgr.updateDetailPlans(bean);
					if (flag == false) {
						JOptionPane.showMessageDialog(null, "������ �����߽��ϴ�. �ٽ� �õ����ּ���.", "Tree Plan",
								JOptionPane.ERROR_MESSAGE);
						break;
					}
				}
				if (flag) {
					JOptionPane.showMessageDialog(null, "���� �Ϸ�Ǿ����ϴ�.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
					moveBack();
				}
			}
		}

		if (obj == teamGoalUpdateB) {
			new TeamPlanUpdate(projectIndex).arrange(id, getX(), getY());
			//saveThisPage();
		}
	}

	public static void main(String[] args) {
		new TeamGoal(17).arrange("admin", 0, 0);
	}
}
