//////////회원정보 수정//////////

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
	
	// DB 연결용 변수 선언
	TeamProject_Member_Mgr mgr;
	Vector<TeamProject_Member_Bean> vlist;

	public Modification() {
		allP = new JPanel();
		ModificationL = new JLabel("회원정보 수정하기");
		idL = new JLabel("아이디");
		pwdL = new JLabel("비밀번호");
		rePwdL = new JLabel("비밀번호 확인");
		pwdQL = new JLabel("비밀번호 찾기");
		pwdAL = new JLabel("답변");
		nameL = new JLabel("이름");
		photoL = new JLabel("프로필 사진");
		photo2L = new JLabel("");// 프사 미리보기
		eMailL = new JLabel("이메일");
		addressL = new JLabel("주소");
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

		ModifyB = new JButton("수정하기");
		ModifyB.addActionListener(this);
		attachB = new JButton("첨부");
		attachB.addActionListener(this);
	}

	@Override
	public void arrange(String id, int x, int y) {
		getContentPane().setBackground(Color.WHITE);
		ModificationL.setBounds(160, 80, 200, 30);
		ModificationL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 24));
		allP.add(ModificationL);
		// 아이디
		idL.setBounds(50, 170, 57, 30);
		idL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(idL);
		// 비번
		pwdL.setBounds(50, 220, 70, 30);
		pwdL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(pwdL);
		// 비번확인
		rePwdL.setBounds(50, 270, 100, 30);
		rePwdL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(rePwdL);
		// 비번질문
		pwdQL.setBounds(50, 320, 100, 30);
		pwdQL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(pwdQL);
		// 답
		pwdAL.setBounds(50, 370, 60, 30);
		pwdAL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(pwdAL);
		// 이름
		nameL.setBounds(50, 420, 57, 30);
		nameL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(nameL);
		// 프사
		photoL.setBounds(50, 470, 80, 30);
		photoL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(photoL);
		// 메일
		eMailL.setBounds(50, 525, 57, 30);
		eMailL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(eMailL);
		atL.setBounds(250, 525, 20, 30);
		atL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(atL);
		// 주소
		addressL.setBounds(50, 570, 57, 30);
		addressL.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(addressL);

		//////////// TextField///////////
		// 아이디
		idTf.setBounds(160, 165, 150, 30);
		idTf.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		idTf.setEnabled(false);
		allP.add(idTf);
		// 비번
		pwdPf.setBounds(160, 220, 180, 30);
		allP.add(pwdPf);
		// 비번확인
		rePwdPf.setBounds(160, 270, 180, 30);
		allP.add(rePwdPf);
		// 비번답
		pwdATf.setBounds(160, 370, 100, 30);
		pwdATf.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(pwdATf);
		// 이름
		nameTf.setBounds(160, 420, 100, 30);
		nameTf.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(nameTf);
		// 프사
		photoTf.setBounds(160, 470, 200, 30);
		photoTf.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		photoTf.setEditable(false);
		allP.add(photoTf);
		// 프사 미리보기
		photo2L.setBounds(375, 370, 80, 80);
		allP.add(photo2L);
		// 이메일
		eMail1Tf.setBounds(160, 525, 90, 30);
		eMail1Tf.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(eMail1Tf);

		// 주소
		addressTf3.setBounds(160, 570, 230, 30);
		addressTf3.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		allP.add(addressTf3);
		// addressTf4.setBounds(410, 570, 230, 30);
		// add(addressTf4);

		// 비밀번호 찾기
		pwdQCb.setBounds(160, 320, 200, 30);
		pwdQCb.setBackground(Color.white);
		pwdQCb.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		pwdQCb.addItem("어머니 성함은?");
		pwdQCb.addItem("아버지 성함은?");
		pwdQCb.addItem("본인의 고향은?");
		allP.add(pwdQCb);

		// email선택
		eMailCb.setBounds(270, 525, 120, 30);
		eMailCb.setBackground(Color.white);
		eMailCb.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		eMailCb.addItem("naver.com");
		eMailCb.addItem("hanmail.net");
		eMailCb.addItem("gmail.net");
		allP.add(eMailCb);

		// 첨부버튼
		attachB.setBounds(375, 470, 80, 30);
		attachB.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		attachB.setBackground(new Color(211, 221, 179));
		allP.add(attachB);

//		// 수정하기
//		ModifyB.setBounds(440, 690, 150, 30);
//		ModifyB.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
//		ModifyB.setBackground(Color.WHITE);
//		add(ModifyB);

		ModifyB.setBounds(190, 640, 150, 30);
		ModifyB.setFont(new Font("나눔스퀘어 Bold", Font.PLAIN, 15));
		ModifyB.setBackground(new Color(211, 221, 179));
		allP.add(ModifyB);

		/////// panel/////////
		allP.setBounds(250, 0, 500, 700);
		allP.setLayout(null);
		allP.setBackground(new Color(238, 242, 225));
		add(allP);

		

		
		// DB 연결용 변수 선언
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
	////////////////////////////////////////////// 이벤트리스너와 DB 기능 연결 /////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	// DB 연결용 변수 선언
	//TeamProject_MemberMgr mgr;
	//Vector<TeamProject_MemberBean> vlist;
	//--------------제일 위에서 선언함
	
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
			/// 각각의 TextField 입력 값의 길이가 0인 경우 띄울 경고 메시지 입력
			
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
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
			} else if (!currentPwd.equals(currentRePwd)) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			} else if (pwdATf.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "답변을 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
			} else if (nameTf.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "이름을 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
			} else if (eMail1Tf.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "email을 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
			} else if (addressTf3.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(null, "주소를 입력해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
			} else {
				int result = JOptionPane.showConfirmDialog(null, "수정된 정보를 저장하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if (result == 0) {

					///// 회원가입 정보 DB에 삽입 ///////////////////////////////////////////
					try {

						TeamProject_Member_Bean bean = new TeamProject_Member_Bean();
						bean.setId(idTf.getText().trim());
						bean.setPwd(currentRePwd);
						bean.setPwdQ(pwdQCb.getSelectedItem().toString());
						bean.setPwdA(pwdATf.getText().trim());
						bean.setName(nameTf.getText().trim());
						bean.setPhoto(photoTf.getText().trim());
						// photoTf 가 빈 값이면 아래 기본이미지로 저장
						if (photoTf.getText().trim().length() == 0) {
							bean.setPhoto("teamProject_Projects\\user.png");
						}
						bean.setEmail(eMail1Tf.getText() + "@" + eMailCb.getSelectedItem().toString());
						bean.setAddress(addressTf3.getText().trim());
						bean.setAdmin(0); // admin 의 default 값은 '0'

						if (idTf.getText().equals("admin")) {
							bean.setAdmin(1); // ID가 'admin' 인 경우 admin 값은 '1' 로 수정
						}
						boolean flag = mgr.updateMember(bean);
						if(flag) {
							JOptionPane.showMessageDialog(null, "회원정보가 수정되었습니다.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
							dispose();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}

				}
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////// 이벤트리스너와 DB 기능 연결  완료/////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		
		if (obj == attachB) {
			JFileChooser jc = new JFileChooser();
			if (jc.showOpenDialog(this) == jc.APPROVE_OPTION) { // 파일 열기 버튼 눌렀을경우
				File f = jc.getSelectedFile(); // 선택된 파일을 가져옴
				photoTf.setText(f.getPath()); // 경로명 가져옴
				fileSave(f, "c:\\java", f.getName());
				String filePath = jc.getSelectedFile().getPath();
				ImageIcon icon = new ImageIcon(filePath);
				Image img1 = icon.getImage();
				Image img2 = img1.getScaledInstance(photo2L.getWidth(), photo2L.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img2);
				photo2L.setIcon(icon); // ------------------ image 출력 label 에 추가하는 코드
			}
		}

	}

	// 파일 복사 프로그램
	public void fileSave(File file, String path, String name) {
		try {
			File f = new File(path); // 디렉토리의 정보
			if (!f.exists()) {// 폴더가 존재 하지 않는다면
				f.mkdir(); // upload폴더 생성
			}
			// 파일 복사
			String filePath = path + "\\" + name;
			// 파일 읽기
			FileInputStream fis = new FileInputStream(file);
			// 파일 쓰기
			FileOutputStream fos = new FileOutputStream(filePath);
			int i = 0; // 파일 읽은 바이트 수를 체크하기 위한 변수
			byte[] buffer = new byte[1024];
			while ((i = fis.read(buffer, 0, 1024)) != -1) {// -1 = EOF(End of File)
				fos.write(buffer, 0, i); // 읽은 개수만큼 출력
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
