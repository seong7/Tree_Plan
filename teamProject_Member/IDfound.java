//����////////ã�� ���̵�//////////******

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


public class IDfound extends TFrame4 implements ActionListener  {
	
	JLabel idL;
	JTextField idTf;
	JButton pwdB, confirmB;
	
	public IDfound(String id) {
		getContentPane().setBackground(new Color(238, 242, 225));
		idL = new JLabel("ȸ������ ���̵�� �Ʒ��� �����ϴ�.");
		idTf = new JTextField(id);
		pwdB = new JButton("��й�ȣ ã��� �̵�");
		confirmB = new JButton("Ȯ��");
		pwdB.addActionListener(this);
		confirmB.addActionListener(this);

		idL.setBounds(115, 143, 271, 30);
		idL.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		add(idL);
		idTf.setBounds(180, 254, 123, 30);
		idTf.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		idTf.setEditable(false);
		idTf.setBackground(Color.white);
		add(idTf);
		pwdB.setBounds(66, 380, 200, 30);
		pwdB.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		pwdB.setBackground(new Color(211, 221, 179));
		add(pwdB);
		confirmB.setBounds(310, 380, 70, 30);
		confirmB.setFont(new Font("���������� Bold", Font.PLAIN, 18));
		confirmB.setBackground(new Color(211, 221, 179));
		add(confirmB);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		validate();
		setVisible(true);
	}
	/////////��й�ȣã��� �̵�/Ȯ�ι�ư/////////
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj==pwdB) {
			new PwdFind(idTf.getText());		 
		}if(obj==confirmB);
		dispose();
		}
	
	public static void main(String[] args) {
		new IDfound("test");
	}
}
