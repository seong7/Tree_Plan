//dd////////ȸ������//////////

package teamProject_Member;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import teamProject_Frame.TFrame2;


public class SignUp extends TFrame2{
	 JLabel signUpL, idL, pwdL, rePwdL, pwdQL, pwdAL, nameL,addressL, 
	 birthL, photoL, photo2L, eMailL, atL, at2L;
	 JTextField idTf, pwdTf, rePwdTf, pwdQTf, pwdATf, nameTf, 
	 birthTf, photoTf, eMail1Tf , eMail2Tf, addressTf1, addressTf2, addressTf3, addressTf4;
	 JButton signUpB, idDuplicateCheckB, attachB;
	 JComboBox pwdQCb, eMailCb;
	 String idTextField = "";					// ���� �Էµ� ID �� �����ϱ� ���� String ���� ���� (���̵� �ߺ�Ȯ�� ��ɿ� �����)
	 String duplicateCheckCompletedID = "";
		JPasswordField pwdPf, rePwdPf;
		JPanel allP;
	
	public SignUp() {
		allP = new JPanel();
		signUpL = new JLabel("ȸ������");
		idL = new JLabel("���̵�");
		pwdL = new JLabel("��й�ȣ");
		rePwdL = new JLabel("��й�ȣ Ȯ��");
		pwdQL = new JLabel("��й�ȣ ã��");
		pwdAL = new JLabel("�亯");
		nameL = new JLabel("�̸�");
		//birthL = new JLabel("�������");
		photoL = new JLabel("������ ����");
		photo2L = new JLabel("");//���� �̸�����
		eMailL = new JLabel("�̸���");
		addressL= new JLabel("�ּ�");
		atL = new JLabel("@");
		//at2L = new JLabel("-");
		
 		idTf = new JTextField();
		pwdPf = new JPasswordField();
		rePwdPf = new JPasswordField();
		pwdQTf = new JTextField();
		pwdATf = new JTextField();
		nameTf = new JTextField();
		//birthTf = new JTextField();
		photoTf = new JTextField();
		eMail1Tf = new JTextField();
		eMail2Tf = new JTextField();
		addressTf1 = new JTextField();
		addressTf2 = new JTextField();
		addressTf3 = new JTextField();
		addressTf4 = new JTextField();
		pwdQCb = new JComboBox();
		eMailCb = new JComboBox();
		
		signUpB = new JButton("ȸ�������ϱ�");
		signUpB.addActionListener(this);		// addActionListener
		idDuplicateCheckB = new JButton("�ߺ�Ȯ��");
		idDuplicateCheckB.addActionListener(this);		// addActionListener
		attachB = new JButton("÷��");
		attachB.addActionListener(this);		// addActionListener
	}
	
		public void arrange(int x, int y) {

			//////////// label/////////////
			// ȸ�������ϱ�
			getContentPane().setBackground(Color.WHITE);
			signUpL.setBounds(220, 80, 200, 30);
			signUpL.setFont(new Font("���������� Bold", Font.PLAIN, 24));
			allP.add(signUpL);
			// ���̵�
			idL.setBounds(50, 170, 57, 30);
			idL.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(idL);
			// ���
			pwdL.setBounds(50, 220, 70, 30);
			pwdL.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(pwdL);
			// ���Ȯ��
			rePwdL.setBounds(50, 270, 100, 30);
			rePwdL.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(rePwdL);
			// �������
			pwdQL.setBounds(50, 320, 100, 30);
			pwdQL.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(pwdQL);
			// ��
			pwdAL.setBounds(50, 370, 60, 30);
			pwdAL.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(pwdAL);
			// �̸�
			nameL.setBounds(50, 420, 57, 30);
			nameL.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(nameL);
			// ����
			photoL.setBounds(50, 470, 80, 30);
			photoL.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(photoL);
			// ����
			eMailL.setBounds(50, 525, 57, 30);
			eMailL.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(eMailL);
			atL.setBounds(250, 525, 20, 30);
			atL.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(atL);
			// �ּ�
			addressL.setBounds(50, 570, 57, 30);
			addressL.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(addressL);

			//////////// TextField///////////
			// ���̵�
			idTf.setBounds(160, 165, 150, 30);
			idTf.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(idTf);
			// ���
			pwdPf.setBounds(160, 220, 180, 30);
			allP.add(pwdPf);
			// ���Ȯ��
			rePwdPf.setBounds(160, 270, 180, 30);
			allP.add(rePwdPf);
			// �����
			pwdATf.setBounds(160, 370, 100, 30);
			pwdATf.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(pwdATf);
			// �̸�
			nameTf.setBounds(160, 420, 100, 30);
			nameTf.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(nameTf);
			// ����
			photoTf.setBounds(160, 470, 200, 30);
			photoTf.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(photoTf);
			// ���� �̸�����
			photo2L.setBounds(375, 370, 80, 80);
			photo2L.setBackground(Color.red);
			allP.add(photo2L);
			// �̸���
			eMail1Tf.setBounds(160, 525, 90, 30);
			eMail1Tf.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(eMail1Tf);

			// �ּ�
			addressTf3.setBounds(160, 570, 230, 30);
			addressTf3.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			allP.add(addressTf3);
			// addressTf4.setBounds(410, 570, 230, 30);
			// add(addressTf4);

			// ��й�ȣ ã��
			pwdQCb.setBounds(160, 320, 200, 30);
			pwdQCb.setBackground(Color.white);
			pwdQCb.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			pwdQCb.addItem("��Ӵ� ������?");
			pwdQCb.addItem("�ƹ��� ������?");
			pwdQCb.addItem("������ ������?");
			allP.add(pwdQCb);

			// email����
			eMailCb.setBounds(270, 525, 120, 30);
			eMailCb.setBackground(Color.white);
			eMailCb.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			eMailCb.addItem("naver.com");
			eMailCb.addItem("hanmail.net");
			eMailCb.addItem("gmail.net");
			allP.add(eMailCb);

			// ȸ�������ϱ��ư
			signUpB.setBounds(190, 640, 150, 30);
			signUpB.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			signUpB.setBackground(new Color(211, 221, 179));
			allP.add(signUpB);
			// �ߺ�Ȯ�ι�ư
			idDuplicateCheckB.setBounds(330, 165, 100, 30);
			idDuplicateCheckB.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			idDuplicateCheckB.setBackground(new Color(211, 221, 179));
			allP.add(idDuplicateCheckB);

			// ÷�ι�ư
			attachB.setBounds(375, 470, 80, 30);
			attachB.setFont(new Font("���������� Bold", Font.PLAIN, 15));
			attachB.setBackground(new Color(211, 221, 179));
			allP.add(attachB);

			/////// panel/////////
			allP.setBounds(250, 0, 500, 700);
			allP.setLayout(null);
			allP.setBackground(new Color(238, 242, 225));
			add(allP);
		
		//id textField �� focuse �ֱ�
		super.addWindowListener( new WindowAdapter() { public void windowOpened( WindowEvent e ){ idTf.requestFocus(); } });	
		
		setLocation(x, y);
		setDefaultCloseOperation(SignUp.DISPOSE_ON_CLOSE);
		setVisible(true);
		validate();
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
					
		
//////////////////////////////////////////////�̺�Ʈ�����ʿ�  DB ��� ����  ////////////////////////		
							
		// DB ����� ���� ���� 
		TeamProject_Member_Mgr mgr = new TeamProject_Member_Mgr();
		//			
					
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			boolean idDuplicateCheck;		// ���̵� �ߺ�üũ ���� Ȯ�� ��	 : 	�������� 'false' /�Ϸ��ϸ� 'true' 
			Object obj = e.getSource();
			int flag = 1;
			
			if(obj == backB) {
				dispose();
			}
			
			////////////////////// ���̵� �ߺ� Ȯ�� ��ư //////////////
			if(obj == idDuplicateCheckB) {
				String id = idTf.getText().trim();
				Vector <String> idCheck = new Vector<String>();
				idCheck = mgr.idCheck();
				
				if(idTf.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "ID�� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
				}else {
					for (int i = 0; i < idCheck.size(); i++) {
						String o = idCheck.get(i).trim();
						if (id.equals(o)) {
							flag = 0;
							break;
						}
					}
					if(flag == 0) {
						JOptionPane.showMessageDialog(null, "�̹� ������� ID �Դϴ�.", "���", JOptionPane.WARNING_MESSAGE);
					}else if(flag ==1)	{
						JOptionPane.showMessageDialog(null, "��� ������ ID �Դϴ�.", "Ȯ��", JOptionPane.INFORMATION_MESSAGE);
						idDuplicateCheck = true;	// Ȯ�� �Ϸ� �Ǹ� Boolean �� true �� ����
						duplicateCheckCompletedID = "" + id;
					}
				}
			}
			
			////////////////////// ÷�� ��ư //////////////
			if (obj == attachB) {
				JFileChooser jc = new JFileChooser();
				if (jc.showOpenDialog(this) == jc.APPROVE_OPTION){ // ���� ���� ��ư ���������
					File f = jc.getSelectedFile(); // ���õ� ������ ������
					photoTf.setText(f.getPath()); // ��θ� ������
					fileSave(f, "c:\\java", f.getName());
					String filePath = jc.getSelectedFile().getPath();
					ImageIcon icon = new ImageIcon(filePath);
					Image img1 = icon.getImage();
					Image img2 = img1.getScaledInstance(photo2L.getWidth(), 
							photo2L.getHeight(), java.awt.Image.SCALE_SMOOTH);
					icon = new ImageIcon(img2);
					photo2L.setIcon(icon); // ------------------ image ��� label �� �߰��ϴ� �ڵ�
				}
			}
			
			
			/////////////////////// ȸ�� �����ϱ� ��ư ///////////////

			if (obj == signUpB) {
				
				/// ������ TextField �Է� ���� ���̰� 0�� ��� ��� ��� �޽��� �Է�
				
				if(idTf.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "ID�� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
				}else if(pwdPf.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
				}else if(!pwdPf.getText().trim().equals(rePwdPf.getText().trim())) {
					JOptionPane.showMessageDialog(null,"��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
				}else if(pwdATf.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "�亯�� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
				}else if(nameTf.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
				}else if(eMail1Tf.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "email�� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
				}else if(addressTf3.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null, "�ּҸ� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
				}else if(idDuplicateCheck = false || !idTf.getText().trim().equals(duplicateCheckCompletedID)) {
					JOptionPane.showMessageDialog(null, "ID �ߺ����θ� Ȯ�����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
				}else {
					int result = JOptionPane.showConfirmDialog(null, "ȸ�������� �Ϸ��Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION);
					if(result == 0) {
						
						///// ȸ������ ���� DB�� ���� ///////////////////////////////////////////
						try {
							
							TeamProject_Member_Bean bean = new TeamProject_Member_Bean();
							bean.setId(idTf.getText().trim());
							bean.setPwd(rePwdPf.getText().trim());
							bean.setPwdQ(pwdQCb.getSelectedItem().toString());
							bean.setPwdA(pwdATf.getText().trim());
							bean.setName(nameTf.getText().trim());
							if(photoTf.getText().trim().length() ==0) {
				/*�⺻�̹���*/		bean.setPhoto("teamProject_Member/user.png");
							}else {
								bean.setPhoto(photoTf.getText().trim());
							}
							bean.setEmail(eMail1Tf.getText() + "@" + eMailCb.getSelectedItem().toString());
							bean.setAddress(addressTf3.getText().trim() + " " + addressTf4.getText().trim());
							/// addressTf1 - addressTf2 (������ �κ�) �� ���� �߰����� �ʾ���!! ///
							bean.setAdmin(0);			// admin �� default ���� '0'			
		
							if (idTf.getText().equals("admin")) {
								bean.setAdmin(1);  		// ID�� 'admin' �� ��� admin ���� '1' �� ����
							}
							boolean flag2 = mgr.insertMember(bean);
							if(flag2) {
								JOptionPane.showMessageDialog(null, "\tȸ�������� �Ϸ�Ǿ����ϴ�.\n\'�Ϸ�\'��ư�� ������ �α��� ȭ������ ���ư��ϴ�.",
										"Welcome to join us !", JOptionPane.PLAIN_MESSAGE);
								dispose();
							}
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						
					}
				}
			}
		}
	public static void main(String[] args) {
		new SignUp().arrange(0,0);
	}
}
