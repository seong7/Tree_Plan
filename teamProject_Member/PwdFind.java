/// test ID적힌거 그냥둬도 되는지////////비밀번호 찾기//////////*****

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
		idL = new JLabel("아이디를 입력해주세요");
		emailL = new JLabel("이메일을 입력해주세요");
		pwdQL = new JLabel("비밀번호 찾기 질문");
		pwdAL = new JLabel("답변");
		idTf = new JTextField(id);
		emailTf = new JTextField();

		pwdATf = new JTextField();
		pwdQCb = new JComboBox();
		pwdQCb.addItem("어머니 성함은?");
		pwdQCb.addItem("아버지 성함은?");
		pwdQCb.addItem("본인의 고향은?");
		pwdB = new JButton("비밀번호찾기");
		pwdB.addActionListener(this);

		idL.setBounds(154, 50, 171, 30);
		idL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		add(idL);
		emailL.setBounds(154, 150, 171, 30);
		emailL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		add(emailL);
		pwdQL.setBounds(164, 260, 150, 30);
		pwdQL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		add(pwdQL);
		pwdAL.setBounds(224, 350, 35, 30);
		pwdAL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		add(pwdAL);
		idTf.setBounds(154, 90, 171, 30);
		idTf.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		add(idTf);
		emailTf.setBounds(154, 190, 171, 30);
		emailTf.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		add(emailTf);
		pwdATf.setBounds(154, 390, 171, 30);
		pwdATf.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		add(pwdATf);
		pwdQCb.setBounds(154, 300, 171, 30);
		pwdQCb.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		pwdQCb.setBackground(Color.WHITE);
		add(pwdQCb);
		pwdB.setBounds(180, 480, 134, 30);
		pwdB.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 18));
		pwdB.setBackground(Color.WHITE);
		add(pwdB);

		
		
		// mail 또는 id 에 focus 주기
		if(idTf.getText().isEmpty() /* id 가 비어있을 때는 id에 focus */) {
			super.addWindowListener( new WindowAdapter() { public void windowOpened( WindowEvent e ){ idTf.requestFocus(); } });
		}else /* id가 빈 값이 아닐 때는 email에 focus */{
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
				JOptionPane.showMessageDialog(null,"아이디를 입력해주세요.","경고",JOptionPane.WARNING_MESSAGE);
			}else if(emailTf.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"이메일을 입력해주세요.","경고",JOptionPane.WARNING_MESSAGE);
			}else if(pwdATf.getText().length()==0) {
				JOptionPane.showMessageDialog(null,"답변을 입력해주세요.","경고",JOptionPane.WARNING_MESSAGE);
			}else {
				String pwd = mgr.pwdSearch(id, email, pwdQ, pwdA);
				if(pwd.trim().length()==0) {
					JOptionPane.showMessageDialog(null,"입력하신 정보가 일치하지 않습니다.","경고",JOptionPane.WARNING_MESSAGE);
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
