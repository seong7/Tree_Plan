//����////////ã�� ��й�ȣ//////////*****

package teamProject_Member;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import teamProject_Frame.TFrame4;

public class FindPwd extends TFrame4 implements ActionListener{
	JLabel pwdL, pwd2L;
	JTextField pwdTf;
	JButton confirmB;
	
	public FindPwd(String pwd) {
		getContentPane().setBackground(new Color(238, 242, 225));
		pwdL = new JLabel("ȸ������ ��й�ȣ�� �Ʒ��� �����ϴ�.");
		pwdTf = new JTextField(pwd);
		pwdTf.setEditable(false);
		confirmB = new JButton("Ȯ��");
		confirmB.addActionListener(this);

		pwdL.setBounds(107, 141, 290, 30);
		pwdL.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(pwdL);
		pwdTf.setBounds(180, 251, 123, 30);
		pwdTf.setBackground(Color.WHITE);
		pwdTf.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(pwdTf);
		confirmB.setBounds(190, 379, 97, 30);
		confirmB.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		confirmB.setBackground(new Color(211, 221, 179));

		add(confirmB);

		setLayout(null);
		validate();
		setVisible(true);
	}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==confirmB);
			dispose();
		}
	
	public static void main(String[] args) {
		new FindPwd("test pwd");
	}
}
