///////////////////////// TFrame3 :  기본화면 (menu바 없음) (800, 800)  ////////

package teamProject_Frame;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TFrame3 extends JFrame  implements MouseListener {
	
	GraphicsEnvironment ge;
	
	public TFrame3() {
		setTitle("TREE PLAN");
		setResizable(false);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		setLayout(null);
		addMouseListener(this);
		validate();
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();					//모니터(픽셀)의 크기 구함
        int height = (int) ge.getMaximumWindowBounds().getHeight();
        int width = (int) ge.getMaximumWindowBounds().getWidth();
		setLocation((width/2-this.getWidth()/2), (height/2-this.getHeight()/2));  // 모니터의 중앙에 열리도록 설정

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage("teamProject_Frame/basic.png");
		setIconImage(img);
	}
	
	public void remove() {}
	
	public void arrange() {}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getComponent();
		if(obj == this) {
			System.out.print("(x , y) :  " +"(" + (e.getX()-8) + " , " + (e.getY()-32) + ") \n" );
		}else {
			System.out.print("(x , y) :  " +"(" + (e.getX()) + " , " + (e.getY()) + ") \n" );
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	
}