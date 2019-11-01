package teamProject_Frame;

import java.util.Vector;
import javax.swing.JFrame;

public interface PageList_TFrame1_2{

	Vector <PageList_TFrame1_2> pageList = new Vector();
	
	Vector <String>	idMemory = new Vector(1);
	
	void arrange(String id, int x, int y);			// 상속을 위한 메소드
	
	void saveThisPage();		// 상속을 위한 메소드
	
	void moveBack();		// 상속을 위한 메소드
}
