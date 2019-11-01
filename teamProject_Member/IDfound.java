//ㅇㅇ////////찾은 아이디//////////******

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
		idL = new JLabel("회원님의 아이디는 아래와 같습니다.");
		idTf = new JTextField(id);
		pwdB = new JButton("비밀번호 찾기로 이동");
		confirmB = new JButton("확인");
		pwdB.addActionListener(this);
		confirmB.addActionListener(this);

		idL.setBounds(115, 143, 271, 30);
		idL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		add(idL);
		idTf.setBounds(180, 254, 123, 30);
		idTf.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		idTf.setEditable(false);
		idTf.setBackground(Color.white);
		add(idTf);
		pwdB.setBounds(66, 380, 200, 30);
		pwdB.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		pwdB.setBackground(new Color(211, 221, 179));
		add(pwdB);
		confirmB.setBounds(310, 380, 70, 30);
		confirmB.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		confirmB.setBackground(new Color(211, 221, 179));
		add(confirmB);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		validate();
		setVisible(true);
	}
	/////////비밀번호찾기로 이동/확인버튼/////////
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
