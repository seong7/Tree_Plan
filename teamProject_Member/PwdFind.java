/// test ID������ �׳ɵֵ� �Ǵ���////////��й�ȣ ã��//////////*****

package teamProject_Member;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import teamProject_Frame.TFrame4;



public class PwdFind extends TFrame4 implements ActionListener{
	JLabel idL, emailL, pwdQL, pwdAL;
	JTextField idTf, emailTf, pwdATf ;
	JComboBox pwdQCb;
	JButton pwdB;
	
	public PwdFind(String id) {
		getContentPane().setBackground(new Color(238, 242, 225));
		idL = new JLabel("���̵� �Է����ּ���");
		emailL = new JLabel("�̸����� �Է����ּ���");
		pwdQL = new JLabel("��й�ȣ ã�� ����");
		pwdAL = new JLabel("�亯");
		idTf = new JTextField(id);
		emailTf = new JTextField();

		pwdATf = new JTextField();
		pwdQCb = new JComboBox();
		pwdQCb.addItem("��Ӵ� ������?");
		pwdQCb.addItem("�ƹ��� ������?");
		pwdQCb.addItem("������ ������?");
		pwdB = new JButton("��й�ȣã��");
		pwdB.addActionListener(this);

		idL.setBounds(154, 50, 171, 30);
		idL.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(idL);
		emailL.setBounds(154, 150, 171, 30);
		emailL.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(emailL);
		pwdQL.setBounds(164, 260, 150, 30);
		pwdQL.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(pwdQL);
		pwdAL.setBounds(224, 350, 35, 30);
		pwdAL.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(pwdAL);
		idTf.setBounds(154, 90, 171, 30);
		idTf.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(idTf);
		emailTf.setBounds(154, 190, 171, 30);
		emailTf.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(emailTf);
		pwdATf.setBounds(154, 390, 171, 30);
		pwdATf.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(pwdATf);
		pwdQCb.setBounds(154, 300, 171, 30);
		pwdQCb.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		pwdQCb.setBackground(Color.WHITE);
		add(pwdQCb);
		pwdB.setBounds(180, 480, 134, 30);
		pwdB.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		pwdB.setBackground(Color.WHITE);
		add(pwdB);

		
		
		// mail �Ǵ� id �� focus �ֱ�
		if(idTf.getText().isEmpty() /* id �� ������� ���� id�� focus */) {
			super.addWindowListener( new WindowAdapter() { public void windowOpened( WindowEvent e ){ idTf.requestFocus(); } });
		}else /* id�� �� ���� �ƴ� ���� email�� focus */{
			idTf.setEditable(false);
			super.addWindowListener( new WindowAdapter() { public void windowOpened( WindowEvent e ){ emailTf.requestFocus(); } });	
		}

		
		setLayout(null);
		validate();
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==pwdB) {
			TeamProject_Member_Mgr mgr = new TeamProject_Member_Mgr();
			String id = idTf.getText().trim();
			String email = emailTf.getText().trim();
			String pwdQ = pwdQCb.getSelectedItem().toString().trim();
			String pwdA = pwdATf.getText().trim();
			
			if(idTf.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"���̵� �Է����ּ���.","���",JOptionPane.WARNING_MESSAGE);
			}else if(emailTf.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"�̸����� �Է����ּ���.","���",JOptionPane.WARNING_MESSAGE);
			}else if(pwdATf.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"�亯�� �Է����ּ���.","���",JOptionPane.WARNING_MESSAGE);
			}else {
				String pwd = mgr.pwdSearch(id, email, pwdQ, pwdA);
				if(pwd.trim().length()==0) {
					JOptionPane.showMessageDialog(null,"�Է��Ͻ� ������ ��ġ���� �ʽ��ϴ�.","���",JOptionPane.WARNING_MESSAGE);
				}else {
					new FindPwd(pwd);
					dispose();
				}
			}
		}
	}
	public static void main(String[] args) {
		new PwdFind("test ID");
	}
}
