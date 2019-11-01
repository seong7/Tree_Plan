///////////////////////// TFrame4 : (500, 600)  �Ƶ�, ��� ã��  /  ȸ������_�޷�, �ּ� ã��   ////////

package teamProject_Frame;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TFrame4 extends JFrame  implements MouseListener {
	
	GraphicsEnvironment ge;
	
	public TFrame4() {
		setTitle("TREE PLAN");
		setResizable(false);
		setSize(500, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(255, 255, 255));
		setLayout(null);
		addMouseListener(this);
		validate();
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();					//�����(�ȼ�)�� ũ�� ����
        int height = (int) ge.getMaximumWindowBounds().getHeight();
        int width = (int) ge.getMaximumWindowBounds().getWidth();
		setLocation((width/2-this.getWidth()/2), (height/2-this.getHeight()/2));  // ������� �߾ӿ� �������� ����

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