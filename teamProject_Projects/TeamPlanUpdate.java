/////////////////////////ㅇㅋ?//////7번 팀 목표 수정하기////////////////////////////

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
	
	// DB 연결용 변수 선언
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
		//1번
		planTitleP = new JPanel();
		planTitleP.addMouseListener(this);
		planTitleL = new JLabel("팀 목표 수정하기");
		
		//2번
		titleP = new JPanel();
		titleP.addMouseListener(this);
		planNameL = new JLabel("목표 이름");
		planNameT = new JTextField();
		
		//3번
		photoP = new JPanel();
		photoL = new JLabel("사진");
		imageL = new JLabel("image");
		photoT = new JTextField("", 30);
		photoT.setEditable(false);
		photoB = new JButton("첨부");
		
		//4번
		roomDetailP = new JPanel();
		openStatusL = new JLabel("공개여부");
		openCB = new JCheckBox("공개");
		closeCB = new JCheckBox("비공개");
		roomPwdT = new JPasswordField("",20);
		//hashTagL = new JLabel("해쉬태그");
		//hashTagT = new JTextField("# # # #");
		numPeopleL = new JLabel("참여인원");
		numPeopleC = new JComboBox();
		
		
		//5번
		endP = new JPanel();
		endDayL = new JLabel("종료일");
		endDayT = new JTextField();
//		DdayL = new JLabel("D-day 표시");
		DdayCB = new JCheckBox();
		saveB = new JButton("수정하기");
		
/////////// 익명클래스로 입력 글자 수(8글자) 제한 시키는 부문
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

		//1번
		add(planTitleP);
		setLayout(null);
		planTitleP.add(planTitleL);
		planTitleP.setBounds(0, 50, 1000, 50);
		planTitleP.setBackground(new Color(211, 221, 179));
		planTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		planTitleL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 30));
		planTitleL.setBackground(new Color(211, 221, 179));
		
		//2번 타이틀Panel
		add(titleP);
		titleP.setBounds(90, 120, 800, 100);
		titleP.setLayout(null);
		titleP.add(planNameL);
		titleP.add(planNameT);
		planNameL.setBounds(40, 30, 90, 41);
		planNameL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		planNameT.setBounds(160, 27, 500, 50);
		planNameT.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		planNameT.setEnabled(false);
		titleP.setBackground(new Color(238, 242, 225));
		
		//3번 포토Panel
		add(photoP);
		photoP.setLayout(null);
		photoP.setBounds(90, 220, 800, 130);
		photoP.setBackground(new Color(238, 242, 225));
		photoP.add(photoL);
		photoP.add(imageL);
		photoP.add(photoT);
		photoP.add(photoB);
		photoL.setBounds(40, 10, 90, 100);
		photoL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		imageL.setBounds(170, 0,130, 130);
		imageL.setOpaque(true);
		imageL.setBackground(Color.white);
		photoT.setBounds(420, 45, 230, 30);
		photoT.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 12));
		photoB.setBounds(665, 44, 100, 30);
		photoB.setBackground(new Color(211, 221, 179));
		photoB.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 12));
		photoB.addActionListener(this);
		
		//4번 룸설정Panel
		add(roomDetailP);
		roomDetailP.setBounds(90, 350, 800, 190);
		roomDetailP.setLayout(null);
		roomDetailP.setBackground(new Color(238, 242, 225));
		roomDetailP.add(openStatusL);
		openStatusL.setBounds(40, 20, 90, 30);
		openStatusL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
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
//		hashTagL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
//		roomDetailP.add(hashTagT);
//		hashTagT.setBounds(150, 100, 300, 50);
//		hashTagT.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 12));

		roomDetailP.add(numPeopleL);
		numPeopleL.setBounds(40, 120, 90, 30);
		numPeopleL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		for (int i = 1; i <= 20; i++) {
			numPeopleC.addItem(i);
		}
		roomDetailP.add(numPeopleC);
		numPeopleC.setBounds(150, 125, 60, 20);
		
		
		//5번
	    add(endP);
		endP.setLayout(null);
		endP.setBounds(90, 540, 800, 180);
		endP.setBackground(new Color(238, 242, 225));
		endP.add(endDayL);
		endDayL.setBounds(40, 35, 90, 20);
		endDayL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		endDayT.setBounds(150, 35, 120, 30);
		endDayT.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 12));
	    endP.add(endDayT);
	    endP.add(saveB);
	    saveB.setBounds(320, 100, 150, 50);
		saveB.setBackground(new Color(211, 221, 179));
		saveB.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		validate();
	    saveB.addActionListener(this);
	    
	    ///////외부 클래스(숫자만 입력받고 알림창 뜨는 걸 정의한 클래스)
	    endDayT.setDocument(new JTextFieldLimit());
	    
	    ///////////익명클래스로 입력 글자 수(8글자) 제한 시키는 부문
	    endDayT.addKeyListener(new KeyAdapter() {
	    	   public void keyTyped(KeyEvent ke) {
	    		    JTextField src = (JTextField) ke.getSource();
	    		    if(src.getText().length()>=8) ke.consume();
	    		   }
	    		  });

	    
		////// DB 연결하여 값 출력하기
	    
	   // 출력할 곳  :   JTextField planNameT, photoT, endDayT;
	    
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
			if (jc.showOpenDialog(this) == jc.APPROVE_OPTION) { // 파일 열기 버튼 눌렀을경우
				File f = jc.getSelectedFile(); // 선택된 파일을 가져옴
				photoT.setText(f.getPath()); // 경로명 가져옴
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
				JOptionPane.showMessageDialog(null, "종료일을 입력하세요!", "Plan Tree", JOptionPane.WARNING_MESSAGE);
			} else if (endDayT.getText().length() < 8) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "종료일은 8자리의 숫자입니다", "Plan Tree", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (year * 10000 + month * 100 + day > Integer.parseInt(endDayT.getText().trim())) {
				JOptionPane.showMessageDialog(null, "종료일은 오늘보다 빠를 수 없습니다.", "알림", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (closeCB.isSelected() && roomPwdT.getPassword().length == 0) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요", "Plan Tree", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (flag == 0) {
				int result = JOptionPane.showConfirmDialog(null, "현재 상태로 수정하시겠습니까?", "Plan Tree", JOptionPane.OK_CANCEL_OPTION);
				if (result ==0) {
					bean = new TeamProject_Projects_Bean();
					boolean flag2;
					
					bean.setPjtIndex(projectIndex);
					bean.setMemId(id);
					bean.setPhoto(photoT.getText());
					String edate = endDayT.getText().substring(0, 4)+"-"+endDayT.getText().substring(4,6)+"-"+endDayT.getText().substring(6,8);
					bean.setNumMax((int)numPeopleC.getSelectedItem());
					bean.setEndDate(edate);
					if (openCB.isSelected()) { // 공개방일 때
						bean.setPassword(null); // 방비밀번호 : null
					} else if (closeCB.isSelected()) { // 비공개방일 때
						char[] pwd = roomPwdT.getPassword(); // 방비밀번호 가져오기
						String pwdS = "";
						for (int i = 0; i < pwd.length; i++) {
							pwdS += pwd[i];
						}
						bean.setPassword(pwdS);
					}
					flag2 = mgr.updatePjt(bean);
					if (flag2) {
						JOptionPane.showMessageDialog(null, "수정 완료되었습니다.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
						//moveBack();
						dispose();
					}
				}
			}
			return;
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
			while ((i = fis.read(buffer, 0, 1024)) != -1){// -1 = EOF(End of File)
				fos.write(buffer, 0, i); // 읽은 개수만큼 출력
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
        ///////////////////////////////공개 비공개 체크박스 활성화 비활성화 부문
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

