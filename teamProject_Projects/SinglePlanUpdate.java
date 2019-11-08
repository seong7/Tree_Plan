/////////////////////////////4�� ���θ�ǥ �����ϱ�/////////////////////////////////////

package teamProject_Projects;

import java.awt.BorderLayout;
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
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import teamProject_Frame.TFrame2;
import teamProject_Member.TeamProject_Member_Mgr;
import teamProject_Projects.TeamProject_Projects_Bean;
import teamProject_Projects.TeamProject_Projects_Mgr;

public class SinglePlanUpdate extends TFrame2 implements ActionListener {

	JPanel planTitleP, titleP, photoP, detailP, endP;
	JLabel planTitleL, planNameL, photoL, imageL, detailL, /* alarmL[], */ endDateL, DdayL, dayL[];
	JButton photoB, saveB, deleteB;
	JToggleButton dayB[];
	JTextField planNameT, photoT, endDateT, dayT[];
	JCheckBox DdayCH;
	String dayStr[] = { "��", "ȭ", "��", "��", "��", "��", "��", "����" };
//	JComboBox ourCB[], minuteCB[];
	boolean flag[] = new boolean[8];

	// DB ����� ���� ����
	TeamProject_Projects_Mgr mgr;
	Vector<TeamProject_Projects_Bean> vlist;
	TeamProject_Projects_Bean bean;
	int projectIndex;
	
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH) + 1;
	int day = now.get(Calendar.DAY_OF_MONTH);
	String today = year + "-" + month + "-" + day;

	public SinglePlanUpdate(int projectIndex) {
		this.projectIndex = projectIndex;
		
		planTitleP = new JPanel();
		planTitleP.addMouseListener(this);
		planTitleL = new JLabel("���θ�ǥ �����ϱ�");

		titleP = new JPanel();
		titleP.addMouseListener(this);
		planNameL = new JLabel("��ǥ �̸�");
		planNameT = new JTextField();

		// ����/////////////////////////////////
		photoP = new JPanel();
		photoL = new JLabel("����");
		imageL = new JLabel("image");
		photoT = new JTextField("", 30);
		photoB = new JButton("÷��");
		////////////////////////////////////////

		detailP = new JPanel();
		detailL = new JLabel("���θ�ǥ");
		dayB = new JToggleButton[8];
		dayL = new JLabel[8];
		dayT = new JTextField[8];
//		alarmL = new JLabel[8];
//		ourCB = new JComboBox[8];
//		minuteCB = new JComboBox[8];

		endP = new JPanel();
		endDateL = new JLabel("������");
		endDateT = new JTextField();
//	    DdayL = new JLabel("D-day ǥ��");
//	    DdayCH = new JCheckBox();
		saveB = new JButton("�����ϱ�");
		deleteB = new JButton("�����ϱ�");

	}

	@Override
	public void arrange(String id, int x, int y) {
		setLayout(null);

		add(planTitleP);
		planTitleP.add(planTitleL);
		planTitleP.setBounds(0, 50, 1000, 50);
		planTitleP.setBackground(new Color(211, 221, 179));
		planTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		planTitleL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 30));
		planTitleL.setBackground(new Color(211, 221, 179));
		
		// 1�� Ÿ��ƲPanel
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

		// 2�� ����Panel
		add(photoP);
		photoP.setLayout(null);
		photoP.setBounds(90, 220, 800, 140);
		photoP.setBackground(new Color(238, 242, 225));
		photoP.add(photoL);
		photoP.add(imageL);
		photoP.add(photoT);
		photoP.add(photoB);
		photoL.setBounds(40, 10, 90, 100);
		photoL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		imageL.setBounds(190, 0, 130, 130);
		imageL.setOpaque(true);
		imageL.setBackground(Color.white);
		photoT.setBounds(380, 45, 230, 30);
		photoT.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		photoT.setEditable(false);
		photoB.setBounds(635, 44, 100, 30);
		photoB.setBackground(new Color(211, 221, 179));
		photoB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		photoB.addActionListener(this);

		// 3�� ���θ�ǥPanel
		add(detailP);
		detailP.setBounds(90, 355, 800, 295);
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

		// �˸� ��///////////////////////////////////////////////////
//		for (int i = 0; i < dayStr.length; i++) {
//			alarmL[i] = new JLabel("�˸�");
//			detailP.add(alarmL[i]);
//			alarmL[i].setBounds(550, 55 + (30 * i), 60, 20);
//			alarmL[i].setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
//			// alarmL.setHorizontalAlignment(SwingConstants.CENTER);
//			alarmL[i].setEnabled(false);
//		}

		// �˸� �ð�&�� �޺��ڽ�////////////////////////////////
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
//		 alarmL.setHorizontalAlignment(SwingConstants.CENTER);
		////////////////////////////////////////////////////////

		// 4�� ��Ʈ�г�
		add(endP);
		endP.setLayout(null);
		endP.setBounds(90, 650, 800, 70);
		endP.add(endDateL);
		endP.setBackground(new Color(238, 242, 225));
		endDateL.setBounds(40, 20, 90, 20);
		endDateL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		endP.add(endDateT);
		endDateT.setBounds(120, 20, 120, 30);
		endDateT.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		/////// �ܺ� Ŭ����(���ڸ� �Է¹ް� �˸�â �ߴ� �� ������ Ŭ����)
		endDateT.setDocument(new JTextFieldLimit());


		/////////// �͸�Ŭ������ �Է� ���� ��(8����) ���� ��Ű�� �ι�
		endDateT.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if (src.getText().length() >= 8)
					ke.consume();
			}
		});

		endP.add(saveB);
		saveB.setBounds(450, 10, 150, 50);
		saveB.setBackground(new Color(211, 221, 179));
		saveB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		saveB.addActionListener(this);
		
		endP.add(deleteB);
		deleteB.setBounds(630, 10, 150, 50);
		deleteB.setBackground(new Color(211, 221, 179));
		deleteB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		deleteB.addActionListener(this);

		/////////////////// DB ����� ���� ����//////////////////////////

		// DB ����� ���� ����
		mgr = new TeamProject_Projects_Mgr();
		vlist = mgr.showProject(projectIndex);
		
		/// project ���� �Է�
		String edate = vlist.get(0).getEndDate().substring(0, 4)+vlist.get(0).getEndDate().substring(5,7)+vlist.get(0).getEndDate().substring(8,10);
		planNameT.setText(vlist.get(0).getPjtName());
		photoT.setText(vlist.get(0).getPhoto());
		endDateT.setText(edate);
		
		String s1 = vlist.get(0).getPhoto();
		ImageIcon ss1 = new ImageIcon(new ImageIcon(s1).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH));
		imageL.setIcon(ss1);
		
		vlist = mgr.showPjtDetail(projectIndex, id);
		
		for (int i = 0; i < dayL.length; i++) {
			dayL[i].getText();
			for (int j = 0; j < vlist.size(); j++) {
				if(vlist.get(j).getDays().equals(dayL[i].getText().trim())){
					dayT[i].setText(vlist.get(j).getDetailPlan());
				}
			}
		}
		
		
		planNameT.setEditable(false);
		photoT.setEditable(false);
		
		setLocation(x, y);
		setVisible(true);
		validate();
		
		//completeListShow(projectIndex);
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

		/////////////////// Ȯ��â�� �������� ���� �׸� null���� ������ ��� // ������ ���� ������ ���� Ȯ�� â

		else if (obj == saveB) {
			int x = 0; // ������ �ִ� ���������� �˻��ϰ� �� ���� ����
			int flag = 0; // �����Ǵ� ������ ������� 1�� ������.
			String day = "";

			if (planNameT.getText().length() == 0) {
				flag = 2;
				JOptionPane.showMessageDialog(null, "������ �Է��ϼ���!", "�˸�", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (endDateT.getText().length() == 0) {
				flag = 2;
				JOptionPane.showMessageDialog(null, "�������� �Է��ϼ���!", "�˸�", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (endDateT.getText().length() < 8) {
				flag = 2;
				JOptionPane.showMessageDialog(null, "�������� 8�ڸ� �����Դϴ�!", "�˸�", JOptionPane.WARNING_MESSAGE);
				return;
			}

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
			
			if (flag == 0) {
				// flag�� 0�̶�� ������ ������ ���ٴ� ��
				int result = JOptionPane.showConfirmDialog(null, "���� �� ������ �����մϴ�.", "Ȯ��", JOptionPane.OK_CANCEL_OPTION);

				///////////////////// DB ����
				mgr = new TeamProject_Projects_Mgr();
				bean = new TeamProject_Projects_Bean();
				boolean flag2 = false;

				if (result == 0) {
					for (int i = 0; i < dayT.length; i++) {
						if (dayT[i].getText().trim().length() != 0) {
							if (dayT[i].isEnabled()) {
								bean.setDetailOnOff(1);
							} else if (!dayT[i].isEnabled()) {
								bean.setDetailOnOff(0);
							}
							bean.setDays(dayL[i].getText());
							bean.setMemId(id);
							bean.setPjtIndex(projectIndex);
							bean.setDetailPlan(dayT[i].getText().trim());

//							if(mgr.searchDetailPlan(bean.getPjtIndex(), bean.getMemId(), bean.getDays()) == 0) {
//								mgr.insertDetailPlans(bean);
//							}else {
//								mgr.updateDetailPlans(bean);
//							}
							flag2 = mgr.updateDetailPlans(bean);
							if (flag2 == false) {
								JOptionPane.showMessageDialog(null, "���� ��õ �׸� ������ �����߽��ϴ�. �ٽ� �õ��Ͽ��ּ���.", "Plan Tree",
										JOptionPane.ERROR_MESSAGE);
								break;
							}
						}
					}
					if (flag2) {

						bean.setPjtIndex(projectIndex);
						bean.setMemId(id);
						bean.setPhoto(photoT.getText());
						String edate = endDateT.getText().substring(0, 4)+"-"+endDateT.getText().substring(4,6)+"-"+endDateT.getText().substring(6,8);
						bean.setNumMax(1);
						bean.setEndDate(edate);
						bean.setPassword(null);
						
						flag2 = mgr.updatePjt(bean);
					}
					
					if (flag2 == false) {
						JOptionPane.showMessageDialog(null, "���忡 �����߽��ϴ�. �ٽ� �õ����ּ���.", "fail",
								JOptionPane.ERROR_MESSAGE);
						return;
					} else if (flag2) {
						JOptionPane.showMessageDialog(null, "���� �Ϸ�Ǿ����ϴ�.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
						new HomeProjects().arrange(id, getX(), getY());
						dispose();
					}
				}
				return;
			}
		}
		
		
		if (obj == deleteB) {
			int result = JOptionPane.showConfirmDialog(null, "��ǥ�� ������ ������ϴ�.\n������ �ش� ��ǥ�� �����Ͻðڽ��ϱ�?", "Tree Plan",
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
						TeamProject_Member_Mgr mgr1 = new TeamProject_Member_Mgr();
						String dbPwd = mgr1.getPwd(id);
						if (userPwd.equals(dbPwd)) {
							boolean flag;
							flag = mgr.deletePjt(projectIndex, id);
							if (flag) {
								JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.", "Tree Plan",
										JOptionPane.PLAIN_MESSAGE);
								dispose();
								new HomeProjects().arrange(id, getX(), getY());
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
		
		
		
		if (obj == photoB) {
			JFileChooser jc = new JFileChooser();
			if (jc.showOpenDialog(this) == jc.APPROVE_OPTION) { // ���� ���� ��ư ���������
				File f = jc.getSelectedFile(); // ���õ� ������ ������
				photoT.setText(f.getPath()); // ��θ� ������
				fileSave(f, "c:\\java", f.getName());
				String filePath = jc.getSelectedFile().getPath();
				ImageIcon icon = new ImageIcon(filePath);
				Image img1 = icon.getImage();
				Image img2 = img1.getScaledInstance(imageL.getWidth(), imageL.getHeight(), java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img2);
				imageL.setIcon(icon);
				// imageL.setIcon(new ImageIcon(filePath));
			}
		}

	}

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
	

	public static void main(String[] args) {
		new SinglePlanUpdate(19).arrange("admin", 0, 0);
	}
}
