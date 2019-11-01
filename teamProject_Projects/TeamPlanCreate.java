//////ㅇㅋ/////////////////////////7번 팀 목표 수정하기////////////////////////////

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

public class TeamPlanCreate extends TFrame2 implements ItemListener {

	JPanel planTitleP, titleP, photoP, roomDetailP, endP;
	JLabel planTitleL, planNameL, photoL, imageL, openStatusL, /* hashTagL, */ endDayL, DdayL, numPeopleL;
	JTextField planNameT, photoT, /* hashTagT, */ endDayT;
	JPasswordField roomPwdT;
	JButton photoB, createB;
	JCheckBox openCB, closeCB, DdayCB;
	JComboBox numPeopleC;
	//String id;

	public TeamPlanCreate(/* String id */) {
		//this.id = id;

		// 1번
		planTitleP = new JPanel();
		planTitleP.addMouseListener(this);
		planTitleL = new JLabel("새로운 팀 목표 만들기");


		// 2번
		titleP = new JPanel();
		titleP.addMouseListener(this);
		planNameL = new JLabel("목표 이름");
		planNameT = new JTextField();

		// 3번
		photoP = new JPanel();
		photoL = new JLabel("사진");
		imageL = new JLabel();
		photoT = new JTextField(/* "Team1/aaa.png", */ 30);
		photoB = new JButton("첨    부");

		// 4번
		roomDetailP = new JPanel();
		openStatusL = new JLabel("공개여부");

		openCB = new JCheckBox("공개");
		closeCB = new JCheckBox("비공개");

		roomPwdT = new JPasswordField("", 20);
//		hashTagL = new JLabel("해쉬태그");
//		hashTagT = new JTextField("# # # #");
		numPeopleL = new JLabel("참여인원");
		numPeopleC = new JComboBox();

		// 5번
		endP = new JPanel();
		endDayL = new JLabel("종료일");
		endDayT = new JTextField();
//		DdayL = new JLabel("D-day 표시");
		DdayCB = new JCheckBox();
		createB = new JButton("만들기");

	}

	// @Override
	public void arrange(String id, int x, int y) {
		setLayout(null);

		// 1번
		add(planTitleP);
		setLayout(null);
		planTitleP.add(planTitleL);
		planTitleP.setBounds(0, 50, 1000, 50);
		planTitleP.setBackground(new Color(211, 221, 179));
		planTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		planTitleL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 30));
		planTitleL.setBackground(new Color(211, 221, 179));

		// 2번 타이틀Panel
		add(titleP);
		titleP.setBounds(90, 120, 800, 100);
		titleP.setLayout(null);
		titleP.add(planNameL);
		titleP.add(planNameT);
		planNameL.setBounds(40, 30, 90, 41);
		planNameL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		planNameT.setBounds(160, 27, 500, 50);
		planNameT.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		titleP.setBackground(new Color(238, 242, 225));

		// 3번 포토Panel
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
		String s1 = "teamProject_Projects/tGoalBasic.png";
		ImageIcon ss1 = new ImageIcon(new ImageIcon(s1).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH));
		imageL.setIcon(ss1);
		imageL.setBounds(170, 0,130, 130);
		imageL.setOpaque(true);
		imageL.setBackground(Color.white);
		photoT.setBounds(420, 45, 230, 30);
		photoT.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 12));
		photoB.setBounds(665, 44, 100, 30);
		photoB.setBackground(new Color(211, 221, 179));
		photoB.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 12));
		photoB.addActionListener(this);

		// 4번 룸설정Panel
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

		// 5번
		add(endP);
		endP.setLayout(null);
		endP.setBounds(90, 540, 800, 100);
		endP.setBackground(new Color(238, 242, 225));
		endP.add(endDayL);
		endDayL.setBounds(40, 35, 90, 20);
		endDayL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		endP.add(endDayT);
		endDayT.setBounds(150, 35, 120, 30);
		endDayT.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 12));
		endDayT.setToolTipText("8자리 숫자   \"예) 20191231\" ");

		/////// 외부 클래스(숫자만 입력받고 알림창 뜨는 걸 정의한 클래스)
		endDayT.setDocument(new JTextFieldLimit());

		/////////// 익명클래스로 입력 글자 수(8글자) 제한 시키는 부문
		endDayT.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField) ke.getSource();
				if (src.getText().length() >= 8)
					ke.consume();
			}
		});

		endP.add(createB);
		createB.setBounds(630, 25, 150, 50);
		createB.addActionListener(this);
		createB.setBackground(new Color(211, 221, 179));
		createB.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		validate();

		setLocation(x, y);
		setVisible(true);
	}


	//////////////// DB 연결하기 위한 벡터 및 변수 선언
	Vector<TeamProject_Projects_Bean> vlist = new Vector();
	TeamProject_Projects_Mgr mgr = new TeamProject_Projects_Mgr();
	TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH) + 1;
	int day = now.get(Calendar.DAY_OF_MONTH);

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object obj = e.getSource();
		int team = 0; // tea 컬럼의 값 (공개방, 비공개방 중 택1)

		// 생성하기 버튼 //
		if (obj == createB) {
			int flag = 0;
			if (planNameT.getText().length() == 0) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "팀 목표명을 입력하세요!", "Message", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (endDayT.getText().length() == 0) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "종료일을 입력하세요!", "Message", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (endDayT.getText().length() < 8) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "종료일은 8자리의 숫자입니다", "Message", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (year * 10000 + month * 100 + day > Integer.parseInt(endDayT.getText().trim())) {
				JOptionPane.showMessageDialog(null, "종료일은 오늘보다 빠를 수 없습니다.", "PLAN TREE", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(closeCB.isSelected() && roomPwdT.getPassword().length==0) {
				flag = 1;
				JOptionPane.showMessageDialog(null, "비공개 목표의 비밀번호를 입력하세요", "팀 목표", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			else if (flag == 0) {
				int result = JOptionPane.showConfirmDialog(null, "팀 목표를 생성합니다.", "Message",
						JOptionPane.OK_CANCEL_OPTION);
				
				
				///////  DB 연동
				if (result == 0) {
					String Syear = Integer.toString(year);
					String Smonth = Integer.toString(month);
					String Sday = Integer.toString(day);

//						planNameT, photoT, hashTagT, endDayT

					bean.setPjtName(planNameT.getText().trim());
					bean.setCreator_id(id);

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
					bean.setTeam(1);
					bean.setPhoto(photoT.getText().trim());
					if (photoT.getText().trim().length() == 0 /* photo null일때 */) {
						bean.setPhoto("teamProject_Projects/tGoalBasic.png");
					}
					bean.setStartDate(Syear + "-" + Smonth + "-" + Sday);
					
					String endYear = endDayT.getText().trim().substring(0,4);
					String endMonth = endDayT.getText().trim().substring(4,6);
					String endDay = endDayT.getText().trim().substring(6,8);
					bean.setEndDate(endYear + "-" + endMonth + "-"+ endDay);
					
					bean.setNumMax((int) numPeopleC.getSelectedItem());
					bean.setNumOfPpl(0);
//					bean.setHashtag(hashTagT.getText().trim());
					
					boolean insertPjt = mgr.insertProject(bean);

					///////// project member 명단 추가
					if (insertPjt) {
						bean.setPjtIndex(mgr.getProjectIndex());
						bean.setMemId(id);
						bean.setJoinDate(Syear + "-" + Smonth + "-" + Sday);
						boolean insertMember = mgr.insertProjectMember(bean);
					}
					//

					new TeamProjects(mgr.getProjectIndex()).arrange(id, getX(), getY());
					//saveThisPage();
					dispose();
				}
			}
			return;
		}
		
		
		/// 첨부 버튼 ///
		if (e.getSource() == photoB) {
			JFileChooser jc = new JFileChooser();
//		jc.showOpenDialog(this); // 열기
			if (jc.showOpenDialog(this) == jc.APPROVE_OPTION) { // 파일 열기 버튼 눌렀을경우
				File f = jc.getSelectedFile(); // 선택된 파일을 가져옴
				photoT.setText(f.getPath()); // 경로명 가져옴
				fileSave(f, "c:\\java", f.getName());
				String filePath = jc.getSelectedFile().getPath();
				ImageIcon icon = new ImageIcon(filePath);
				Image img1 = icon.getImage();
				Image img2 = img1.getScaledInstance(imageL.getWidth(), imageL.getHeight(),
						java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(img2);
				imageL.setIcon(icon);
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

			while ((i = fis.read(buffer, 0, 1024)) != -1) {// -1 = EOF(End of File)
				fos.write(buffer, 0, i); // 읽은 개수만큼 출력
			}

			fis.close();
			fos.close();

		} catch (Exception ex) {
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getItem();
		// boolean flag[] = new boolean[1];

		if (obj == closeCB) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				roomPwdT.setEnabled(true);
				openCB.setSelected(false);
				roomPwdT.requestFocus();
			}
		} else if (obj == openCB) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				closeCB.setSelected(false);
				roomPwdT.setEnabled(false);
			}
		}
	}

	public static void main(String[] args) {
		new TeamPlanCreate().arrange("",0, 0);
	}
}
