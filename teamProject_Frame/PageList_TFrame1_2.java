package teamProject_Frame;

import java.util.Vector;
import javax.swing.JFrame;

public interface PageList_TFrame1_2{

	Vector <PageList_TFrame1_2> pageList = new Vector();
	
	Vector <String>	idMemory = new Vector(1);
	
	void arrange(String id, int x, int y);			// ����� ���� �޼ҵ�
	
	void saveThisPage();		// ����� ���� �޼ҵ�
	
	void moveBack();		// ����� ���� �޼ҵ�
}
