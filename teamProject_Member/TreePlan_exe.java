package teamProject_Member;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import teamProject_Frame.PageList_TFrame1_2;
import teamProject_Projects.HomeProjects;

import java.awt.Font;
 
public class TreePlan_exe extends JFrame implements ActionListener, PageList_TFrame1_2 {
 
    BufferedImage img = null;
    JTextField loginTextField;
    JPasswordField passwordField;
    JButton b1, b2, b3, b4;
	GraphicsEnvironment ge;			// 모니터 픽셀 수 구하기 위한 클래스의 변수 선언
	
    // 메인
    public static void main(String[] args) {
        new TreePlan_exe();
    }
 
    // 생성자
    public TreePlan_exe() {
    	setTitle("TREE PLAN");
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image iconImg = toolkit.getImage("teamProject_Frame/basic.png");
		setIconImage(iconImg);
        // 레이아웃 설정
        getContentPane().setLayout(null);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1000, 800);
        layeredPane.setLayout(null);
 
        // 패널1
        // 이미지 받아오기
        try {
            img = ImageIO.read(new File("teamProject_Member/nno.png"));
        } catch (IOException e) {
            System.out.println("이미지 불러오기 실패");
            System.exit(0);
        }
         
        MyPanel panel = new MyPanel();
        panel.setBounds(0, 0, 1000, 800);
         
 
        // 로그인 필드
        loginTextField = new JTextField(15);
        loginTextField.setFont(new Font("굴림", Font.BOLD, 20));
        loginTextField.setBounds(274, 394, 280, 30);
        layeredPane.add(loginTextField);
        loginTextField.setOpaque(false);
        loginTextField.setForeground(Color.BLACK);
        loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        // 패스워드
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("굴림", Font.BOLD, 20));
        passwordField.setBounds(274, 479, 280, 30);
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.BLACK);
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        layeredPane.add(passwordField);
 
        // 로그인버튼 추가
        b1 = new JButton("");
        b1.setBounds(647, 384, 180, 50);
        b2 = new JButton("");
        b2.setBounds(647, 487, 180, 50);
        b3 = new JButton("");
        b3.setBounds(234, 601, 118, 48);
        b4 = new JButton("");
        b4.setBounds(412, 599, 127, 50);
 
        // 버튼 투명처리
        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        b1.setContentAreaFilled(false);
        
        
        b2.setBorderPainted(false);
        b2.setFocusPainted(false);
        b2.setContentAreaFilled(false);
        
        
        b3.setBorderPainted(false);
        b3.setFocusPainted(false);
        b3.setContentAreaFilled(false);
        
        
        b4.setBorderPainted(false);
        b4.setFocusPainted(false);
        b4.setContentAreaFilled(false);
        
        
        // 버튼들에 ActionListener 추가 
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
 
        layeredPane.add(b1);
        layeredPane.add(b2);
        layeredPane.add(b3);
        layeredPane.add(b4);
 
        // 마지막 추가들
        layeredPane.add(panel);
        getContentPane().add(layeredPane);
        
        
		//어떤 모니터이든 중앙에 열리기
	
    	ge = GraphicsEnvironment.getLocalGraphicsEnvironment();					//모니터(픽셀)의 크기 구함
        int height = (int) ge.getMaximumWindowBounds().getHeight();
        int width = (int) ge.getMaximumWindowBounds().getWidth();
		setLocation((width/2-this.getWidth()/2), (height/2-this.getHeight()/2));  // 모니터의 중앙에 열리도록 설정
        
        setVisible(true);
    }
    
  
	
//////////////////////////////////////////////이벤트리스너와  DB 기능 연결  ////////////////////////		

    // DB 연결용 변수 선언 
	TeamProject_Member_Mgr mgr = new TeamProject_Member_Mgr();
	//			
    
    @Override
	public void actionPerformed(ActionEvent e) {
	Object obj = e.getSource();
	
	if(obj == b1) {
		String id = loginTextField.getText().trim();
		char[] pwd = passwordField.getPassword();
		String currentPwd = "";
		for (int i = 0; i < pwd.length; i++) {
			currentPwd += pwd[i];
		}
		
		///////// Log-in 버튼 ///////
		String dbPwd = mgr.logIn(id);		// db에서 가져온  password
		if (id.length() ==0) {
			JOptionPane.showMessageDialog(null, "ID를 입력해주세요.", "Log-in", JOptionPane.WARNING_MESSAGE);
		}else	if (pwd.length == 0) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.", "Log-in", JOptionPane.WARNING_MESSAGE);
		}else if (dbPwd.isEmpty()) {
			JOptionPane.showMessageDialog(null, "입력하신 ID가 존재하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
		}else	if (!currentPwd.equals(dbPwd)) {
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
		}else {
			idMemory.addElement(id);
			HomeProjects hp = new HomeProjects();
			//hp.addToRowDataTodo("", "", "");
			//hp.addToRowDataPPjt("", "", "", "", "");
			//hp.addToRowDataTPjt("", "", "", "", "");
			hp.arrange(id, getX(), getY());
			dispose();
		}
		
		
    }if(obj==b2) {
		new SignUp().arrange(getX(),getY());
	}if(obj==b3) {
		new FindID();
	}if(obj==b4) {
		new PwdFind("");
	}
}
        
   
    class MyPanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }


	@Override
	public void arrange(String id, int x, int y) {
	}

	@Override
	public void saveThisPage() {
	}

	@Override
	public void moveBack() {
	}
}
