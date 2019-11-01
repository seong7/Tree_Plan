//////////���̵� ã��//////////***

package teamProject_Member;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import teamProject_Frame.TFrame4;


public class FindID extends TFrame4 implements ActionListener {
	JLabel nameL, emailL;
	JTextField nameTf, emailTf;
	JButton searchB;
	
	public FindID() {
		getContentPane().setBackground(new Color(238, 242, 225));
		nameL = new JLabel("�̸��� �Է����ּ���");
		emailL = new JLabel("�̸����� �Է����ּ���");
		nameTf = new JTextField();
		emailTf = new JTextField();
		searchB = new JButton("���̵�ã��");

		nameL.setBounds(148, 128, 210, 30);
		nameL.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(nameL);
		emailL.setBounds(146, 248, 210, 30);
		emailL.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(emailL);
		nameTf.setBounds(146, 178, 171, 30);
		nameTf.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(nameTf);
		emailTf.setBounds(110, 298, 250, 30);
		emailTf.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(emailTf);
		searchB.setBounds(157, 380, 150, 30);
		searchB.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		searchB.setBackground(new Color(211, 221, 179));
		add(searchB);
		searchB.addActionListener(this);

		validate();
		setVisible(true);
	}
	
	//////////���̵� ã�� ��ư///////////
	@Override
	public void actionPerformed(ActionEvent e) {
		TeamProject_Member_Mgr mgr = new TeamProject_Member_Mgr();
		String name = nameTf.getText().trim();
		String email = emailTf.getText().trim();
		
		Object obj = e.getSource();
		String id;
		if(obj==searchB) {
			if(name.length()==0) {
				JOptionPane.showMessageDialog(null,"�̸��� �Է����ּ���.","���",JOptionPane.WARNING_MESSAGE);
				nameTf.requestFocus();
			}else if(email.length()==0) {
				JOptionPane.showMessageDialog(null,"�̸����� �Է����ּ���.","���",JOptionPane.WARNING_MESSAGE);
				emailTf.requestFocus();
			}else {
				id = mgr.idSearch(name, email);
				if(id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�Է��Ͻ� ������ ���� �ʽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
					nameTf.requestFocus();
				}else {
					new IDfound(id);
					dispose();
				}
			}
		}
	}
	///////////////////////////////////
	public static void main(String[] args) {
		new FindID();
	}
}
