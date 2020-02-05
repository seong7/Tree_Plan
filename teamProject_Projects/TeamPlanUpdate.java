/////////////////////////����?//////7�� �� ��ǥ �����ϱ�////////////////////////////

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
import teamProject_Projects.TeamProject_Projects_Bean;
import teamProject_Projects.TeamProject_Projects_Mgr;

public class TeamPlanUpdate extends TFrame2 implements ItemListener{

	JPanel planTitleP, titleP, photoP, roomDetailP, endP;
	JLabel planTitleL, planNameL, photoL, imageL, openStatusL, /*hashTagL,*/ endDayL, DdayL, numPeopleL;
	JTextField planNameT, photoT, /*hashTagT,*/ endDayT;
	JPasswordField roomPwdT;
	JButton photoB, saveB;
	JCheckBox openCB, closeCB, DdayCB;
	JComboBox numPeopleC;
	
	// DB ����� ���� ����
	Vector <TeamProject_Projects_Bean> vlist;
	TeamProject_Projects_Bean bean;
	TeamProject_Projects_Mgr mgr;
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH) + 1;
	int day = now.get(Calendar.DAY_OF_MONTH);
	
	int projectIndex;
	
	public TeamPlanUpdate(int projectIndex) {
		this.projectIndex = projectIndex;
		//1��
		planTitleP = new JPanel();
		planTitleP.addMouseListener(this);
		planTitleL = new JLabel("�� ��ǥ �����ϱ�");
		
		//2��
		titleP = new JPanel();
		titleP.addMouseListener(this);
		planNameL = new JLabel("��ǥ �̸�");
		planNameT = new JTextField();
		
		//3��
		photoP = new JPanel();
		photoL = new JLabel("����");
		imageL = new JLabel("image");
		photoT = new JTextField("", 30);
		photoT.setEditable(false);
		photoB = new JButton("÷��");
		
		//4��
		roomDetailP = new JPanel();
		openStatusL = new JLabel("��������");
		openCB = new JCheckBox("����");
		closeCB = new JCheckBox("�����");
		roomPwdT = new JPasswordField("",20);
		//hashTagL = new JLabel("�ؽ��±�");
		//hashTagT = new JTextField("# # # #");
		numPeopleL = new JLabel("�����ο�");
		numPeopleC = new JComboBox();
		
		
		//5��
		endP = new JPanel();
		endDayL = new JLabel("������");
		endDayT = new JTextField();
//		DdayL = new JLabel("D-day ǥ��");
		DdayCB = new JCheckBox();
		saveB = new JButton("�����ϱ�");
		
/////////// �͸�Ŭ������ �Է� ���� ��(8����) ���� ��Ű�� �ι�
		endDayT.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if (src.getText().length() >= 8)
					ke.consume();
			}
		});
		
	}
	
	@Override
	public void arrange(String id, int x ,int y) {
        setLayout(null);
		
 //       System.out.println( "current ID  :   [ " +id + " ] ... !!");

		//1��
		add(planTitleP);
		setLayout(null);
		planTitleP.add(planTitleL);
		planTitleP.setBounds(0, 50, 1000, 50);
		planTitleP.setBackground(new Color(211, 221, 179));
		planTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		planTitleL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 30));
		planTitleL.setBackground(new Color(211, 221, 179));
		
		//2�� Ÿ��ƲPanel
		add(titleP);
		titleP.setBounds(90, 120, 800, 100);
		titleP.setLayout(null);
		titleP.add(planNameL);
		titleP.add(planNameT);
		planNameL.setBounds(40, 30, 90, 41);
		planNameL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		planNameT.setBounds(160, 27, 500, 50);
		planNameT.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		planNameT.setEnabled(false);
		titleP.setBackground(new Color(238, 242, 225));
		
		//3�� ����Panel
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
		imageL.setBounds(170, 0,130, 130);
		imageL.setOpaque(true);
		imageL.setBackground(Color.white);
		photoT.setBounds(420, 45, 230, 30);
		photoT.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		photoB.setBounds(665, 44, 100, 30);
		photoB.setBackground(new Color(211, 221, 179));
		photoB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
		photoB.addActionListener(this);
		
		//4�� �뼳��Panel
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
		
		
		//5��
	    add(endP);
		endP.setLayout(null);
		endP.setBounds(90, 540, 800, 180);
		endP.setBackground(new Color(238, 242, 225));
		endP.add(endDayL);
		endDayL.setBounds(40, 35, 90, 20);
		endDayL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		endDayT.setBounds(150, 35, 120, 30);
		endDayT.setFont(new Font("������������� ExtraBold", Font.PLAIN, 12));
	    endP.add(endDayT);
	    endP.add(saveB);
	    saveB.setBounds(320, 100, 150, 50);
		saveB.setBackground(new Color(211, 221, 179));
		saveB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		validate();
	    saveB.addActionListener(this);
	    
	    ///////�ܺ� Ŭ����(���ڸ� �Է¹ް� �˸�â �ߴ� �� ������ Ŭ����)
	    endDayT.setDocument(new JTextFieldLimit());
	    
	    ///////////�͸�Ŭ������ �Է� ���� ��(8����) ���� ��Ű�� �ι�
	    endDayT.addKeyListener(new KeyAdapter() {
	    	   public void keyTyped(KeyEvent ke) {
	    		    JTextField src = (JTextField) ke.getSource();
	    		    if(src.getText().length()>=8) ke.consume();
	    		   }
	    		  });

	    
		////// DB �����Ͽ� �� ����ϱ�
	    
	   // ����� ��  :   JTextField planNameT, photoT, endDayT;
	    
		mgr = new TeamProject_Projects_Mgr ();
		vlist = mgr.showProject(projectIndex);

		
		planNameT.setText(vlist.get(0).getPjtName());
		String s1 = vlist.get(0).getPhoto();
		photoT.setText(s1);
		ImageIcon ss1 = new ImageIcon(new ImageIcon(s1).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		imageL.setIcon(ss1);
		if(vlist.get(0).getPassword().length() == 0) {
			openCB.setSelected(true);
		}else {
			closeCB.setSelected(true);
			roomPwdT.setText(vlist.get(0).getPassword());
		}
		numPeopleC.setSelectedItem(vlist.get(0).getNumMax());
		String datex = vlist.get(0).getEndDate();
		String date = datex.substring(0, 4) + datex.substring(5, 7) + datex.substring(8, 10);
		endDayT.setText(date);
	    ////////////
		
		
		setLocation(x, y);
		setVisible(true);
		validate();
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (obj == backB) {
			dispose();
			return;
		}
		
		super.actionPerformed(e);

		
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
			}
			return;
		}

		
		if (e.getSource() == saveB) {
			int flag = 0;
			if (endDayT.getText().length() == 0) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "�������� �Է��ϼ���!", "Plan Tree", JOptionPane.WARNING_MESSAGE);
			} else if (endDayT.getText().length() < 8) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "�������� 8�ڸ��� �����Դϴ�", "Plan Tree", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (year * 10000 + month * 100 + day > Integer.parseInt(endDayT.getText().trim())) {
				JOptionPane.showMessageDialog(null, "�������� ���ú��� ���� �� �����ϴ�.", "�˸�", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (closeCB.isSelected() && roomPwdT.getPassword().length == 0) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���", "Plan Tree", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (flag == 0) {
				int result = JOptionPane.showConfirmDialog(null, "���� ���·� �����Ͻðڽ��ϱ�?", "Plan Tree", JOptionPane.OK_CANCEL_OPTION);
				if (result ==0) {
					bean = new TeamProject_Projects_Bean();
					boolean flag2;
					
					bean.setPjtIndex(projectIndex);
					bean.setMemId(id);
					bean.setPhoto(photoT.getText());
					String edate = endDayT.getText().substring(0, 4)+"-"+endDayT.getText().substring(4,6)+"-"+endDayT.getText().substring(6,8);
					bean.setNumMax((int)numPeopleC.getSelectedItem());
					bean.setEndDate(edate);
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
					flag2 = mgr.updatePjt(bean);
					if (flag2) {
						JOptionPane.showMessageDialog(null, "���� �Ϸ�Ǿ����ϴ�.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
						//moveBack();
						dispose();
					}
				}
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
			while ((i = fis.read(buffer, 0, 1024)) != -1){// -1 = EOF(End of File)
				fos.write(buffer, 0, i); // ���� ������ŭ ���
			}

			fis.close();
			fos.close();

		} catch (Exception ex) {
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getItemSelectable();
		// boolean flag[] = new boolean[1];
        ///////////////////////////////���� ����� üũ�ڽ� Ȱ��ȭ ��Ȱ��ȭ �ι�
		if (obj == closeCB) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				roomPwdT.setEnabled(true);
				openCB.setSelected(false);
				roomPwdT.requestFocus();
			}
		} else if (obj == openCB) {
			if(e.getStateChange()== ItemEvent.SELECTED) {
				closeCB.setSelected(false);
				roomPwdT.setEnabled(false);
			}
		}
	}

	
	public static void main(String[] args) {
		new TeamPlanUpdate(32).arrange("ff", 0,0);
	}
}

