///////////////////////// TFrame2 :  기본화면 (menu바 없음) (1000, 1000)  ////////

package teamProject_Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TFrame2 extends JFrame implements ActionListener, MouseListener , PageList_TFrame1_2 {
	
	public JPanel menuP;
	public JButton backB;
	GraphicsEnvironment ge;
	public String id;
//	public String id = "admin";
	
	public TFrame2() {
		
		if (idMemory.size() != 0) {
			id = idMemory.get(0);
		}
		setTitle("TREE PLAN");
		setResizable(false);
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		setLayout(null);
		addMouseListener(this);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("teamProject_Frame/basic.png");
		setIconImage(img);
		
		menuP = new JPanel();
		menuP.setBackground(Color.WHITE);
		backB = new JButton("");
		backB.setIcon(new ImageIcon("teamProject_Frame/left-arrow.png"));
		backB.setBorderPainted(false);
		backB.setFocusPainted(false);
		backB.setContentAreaFilled(false);
		
		menuP.setBounds(0,0,1000,37);
		menuP.setLayout(null);
		backB.setBounds(10, 5, 100, 30);
		menuP.add(backB);
		add(menuP);
		validate();
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();					//모니터(픽셀)의 크기 구함
        int height = (int) ge.getMaximumWindowBounds().getHeight();
        int width = (int) ge.getMaximumWindowBounds().getWidth();
		//setLocation((width/2-this.getWidth()/2), (height/2-this.getHeight()/2));  // 모니터의 중앙에 열리도록 설정
        /*Tframe2 상속 받는 화면들은 중앙이 아니라 이전 화면의 위치 값을 받아 동일하게 출력해야함 */
        
        backB.addActionListener(this);
		setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//클릭한 지점의 좌표 console 출력
//		Object obj = e.getComponent();
//		if(obj == this) {
//			System.out.print("(x , y) :  " +"(" + (e.getX()-8) + " , " + (e.getY()-32) + ") \n" );
//		}else {
//			System.out.print("(x , y) :  " +"(" + (e.getX()) + " , " + (e.getY()) + ") \n" );
//		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println("누름");
		Object obj = e.getSource();
		
		
		//// 뒤로가기 버튼 기능 /////
		if(obj == backB) {
			if(pageList.size() == 0) {
				//System.out.println("뒤로 갈 화면 없음");
			}else {
				//System.out.println("뒤로 가기");
				moveBack();
			}
		}
	}
	
	@Override
	public void saveThisPage() {
		pageList.addElement(this);
		System.out.println("pageList vector size : " + pageList.size());
		dispose();
	};
	
	@Override
	public void moveBack() {
		int x = getX();
		int y = getY();
		pageList.get(pageList.size()-1).arrange(id, x, y);
		pageList.remove(pageList.size()-1);
		System.out.println("pageList vector size : " + pageList.size());
		dispose();
	};

	@Override
	public void arrange(String id, int x, int y) {
	}
	
	public static void main(String[] args) {
		TFrame2 tf = new TFrame2();	
		tf.setVisible(true);

	}
}