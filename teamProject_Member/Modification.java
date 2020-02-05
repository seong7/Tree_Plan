//////////ȸ������ ����//////////

package teamProject_Member;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
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

public class Modification extends TFrame2 {

	JLabel ModificationL, idL, pwdL, rePwdL, pwdQL, pwdAL, nameL, addressL, photoL, eMailL, atL, photo2L;
	JTextField idTf, pwdQTf, pwdATf, nameTf, eMail1Tf, eMail2Tf, addressTf3, /* addressTf4, */
			birthTf, photoTf, eMailTf, addressTf;
	JButton ModifyB, attachB;
	JComboBox pwdQCb, eMailCb;
	JPasswordField pwdPf, rePwdPf;
	JPanel allP;
	
	// DB ����� ���� ����
	TeamProject_Member_Mgr mgr;
	Vector<TeamProject_Member_Bean> vlist;

	public Modification() {
		allP = new JPanel();
		ModificationL = new JLabel("ȸ������ �����ϱ�");
		idL = new JLabel("���̵�");
		pwdL = new JLabel("��й�ȣ");
		rePwdL = new JLabel("��й�ȣ Ȯ��");
		pwdQL = new JLabel("��й�ȣ ã��");
		pwdAL = new JLabel("�亯");
		nameL = new JLabel("�̸�");
		photoL = new JLabel("������ ����");
		photo2L = new JLabel("");// ���� �̸�����
		eMailL = new JLabel("�̸���");
		addressL = new JLabel("�ּ�");
		atL = new JLabel("@");

		idTf = new JTextField();
		pwdPf = new JPasswordField();
		rePwdPf = new JPasswordField();
		pwdQTf = new JTextField();
		pwdATf = new JTextField();
		nameTf = new JTextField();
		photoTf = new JTextField();
		eMail1Tf = new JTextField();
		eMail2Tf = new JTextField();
		addressTf3 = new JTextField();
		// addressTf4 = new JTextField();
		pwdQCb = new JComboBox();
		eMailCb = new JComboBox();

		ModifyB = new JButton("�����ϱ�");
		ModifyB.addActionListener(this);
		attachB = new JButton("÷��");
		attachB.addActionListener(this);
	}

	@Override
	public void arrange(String id, int x, int y) {
		getContentPane().setBackground(Color.WHITE);
		ModificationL.setBounds(160, 80, 200, 30);
		ModificationL.setFont(new Font("���������� Bold", Font.PLAIN, 24));
		allP.add(ModificationL);
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
		idTf.setEnabled(false);
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
		photoTf.setEditable(false);
		allP.add(photoTf);
		// ���� �̸�����
		photo2L.setBounds(375, 370, 80, 80);
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

		// ÷�ι�ư
		attachB.setBounds(375, 470, 80, 30);
		attachB.setFont(new Font("���������� Bold", Font.PLAIN, 15));
		attachB.setBackground(new Color(211, 221, 179));
		allP.add(attachB);

//		// �����ϱ�
//		ModifyB.setBounds(440, 690, 150, 30);
//		ModifyB.setFont(new Font("���������� Bold", Font.PLAIN, 15));
//		ModifyB.setBackground(Color.WHITE);
//		add(ModifyB);

		ModifyB.setBounds(190, 640, 150, 30);
		ModifyB.setFont(new Font("���������� Bold", Font.PLAIN, 15));
		ModifyB.setBackground(new Color(211, 221, 179));
		allP.add(ModifyB);

		/////// panel/////////
		allP.setBounds(250, 0, 500, 700);
		allP.setLayout(null);
		allP.setBackground(new Color(238, 242, 225));
		add(allP);

		

		
		// DB ����� ���� ����
		mgr = new TeamProject_Member_Mgr();
		vlist = mgr.listMember("Modification", id);
		idTf.setText(id);
		pwdPf.setText(vlist.get(0).getPwd());
		pwdQCb.setSelectedItem(vlist.get(0).getPwdQ());
		pwdATf.setText(vlist.get(0).getPwdA());
		nameTf.setText(vlist.get(0).getName());
		photoTf.setText(vlist.get(0).getPhoto());
		int indexOfxx = vlist.get(0).getEmail().indexOf('@');
		eMail1Tf.setText(vlist.get(0).getEmail().substring(0, indexOfxx));
		eMailCb.setSelectedItem(vlist.get(0).getEmail().substring(indexOfxx+1));
		addressTf3.setText(vlist.get(0).getAddress());
		
		setDefaultCloseOperation(SignUp.DISPOSE_ON_CLOSE);
		setLocation(x, y);
		setVisible(true);
		validate();
		repaint();
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////// �̺�Ʈ�����ʿ� DB ��� ���� /////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	// DB ����� ���� ����
	//TeamProject_MemberMgr mgr;
	//Vector<TeamProject_MemberBean> vlist;
	//--------------���� ������ ������
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj == backB) {
			dispose();
			return;
		}
		
		super.actionPerformed(e);
		
		if (obj == ModifyB) {
			mgr = new TeamProject_Member_Mgr();
			/// ������ TextField �Է� ���� ���̰� 0�� ��� ��� ��� �޽��� �Է�
			
			char[] pwd = pwdPf.getPassword();
			String currentPwd = "";
			for (int i = 0; i < pwd.length; i++) {
				currentPwd += pwd[i];
			}
			char[] rePwd = rePwdPf.getPassword();
			String currentRePwd = "";
			for (int i = 0; i < rePwd.length; i++) {
				currentRePwd += rePwd[i];
			}
			
			
			
			if (currentPwd.length() == 0) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
			} else if (!currentPwd.equals(currentRePwd)) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
			} else if (pwdATf.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "�亯�� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
			} else if (nameTf.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
			} else if (eMail1Tf.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "email�� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
			} else if (addressTf3.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "�ּҸ� �Է����ּ���.", "���", JOptionPane.WARNING_MESSAGE);
			} else {
				int result = JOptionPane.showConfirmDialog(null, "������ ������ �����Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.YES_NO_OPTION);
				if (result == 0) {

					///// ȸ������ ���� DB�� ���� ///////////////////////////////////////////
					try {

						TeamProject_Member_Bean bean = new TeamProject_Member_Bean();
						bean.setId(idTf.getText().trim());
						bean.setPwd(currentRePwd);
						bean.setPwdQ(pwdQCb.getSelectedItem().toString());
						bean.setPwdA(pwdATf.getText().trim());
						bean.setName(nameTf.getText().trim());
						bean.setPhoto(photoTf.getText().trim());
						// photoTf �� �� ���̸� �Ʒ� �⺻�̹����� ����
						if (photoTf.getText().trim().length() == 0) {
							bean.setPhoto("teamProject_Projects\\user.png");
						}
						bean.setEmail(eMail1Tf.getText() + "@" + eMailCb.getSelectedItem().toString());
						bean.setAddress(addressTf3.getText().trim());
						bean.setAdmin(0); // admin �� default ���� '0'

						if (idTf.getText().equals("admin")) {
							bean.setAdmin(1); // ID�� 'admin' �� ��� admin ���� '1' �� ����
						}
						boolean flag = mgr.updateMember(bean);
						if(flag) {
							JOptionPane.showMessageDialog(null, "ȸ�������� �����Ǿ����ϴ�.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
							dispose();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}

				}
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////// �̺�Ʈ�����ʿ� DB ��� ����  �Ϸ�/////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		if (obj == attachB) {
			JFileChooser jc = new JFileChooser();
			if (jc.showOpenDialog(this) == jc.APPROVE_OPTION) { // ���� ���� ��ư ���������
				File f = jc.getSelectedFile(); // ���õ� ������ ������
				photoTf.setText(f.getPath()); // ��θ� ������
				fileSave(f, "c:\\java", f.getName());
				String filePath = jc.getSelectedFile().getPath();
				ImageIcon icon = new ImageIcon(filePath);
				Image img1 = icon.getImage();
				Image img2 = img1.getScaledInstance(photo2L.getWidth(), photo2L.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img2);
				photo2L.setIcon(icon); // ------------------ image ��� label �� �߰��ϴ� �ڵ�
			}
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

	public static void main(String[] args) {
		new Modification().arrange("admin", 0, 0);
	}
}
