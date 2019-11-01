//��������/////QnA ����������///////

package teamProject_Manager_QnA;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import teamProject_Frame.TFrame1_null;

public class Qna_Admin extends TFrame1_null {
/////////// ��///////////
	private static final int INDEX_OF_TYPE_COLUMN = 4;
/////////// ��///////////

	JPanel adminP, qnaAdminTbP;
	JTable qnaAdminTb;
	JScrollPane qnaSc;
	JButton memAdminB, qnaAdminB;

	// DB ����� ���� ����
	Manage_Qna_Mgr mgr = new Manage_Qna_Mgr();
	Vector<Manage_Qna_Bean> vlist;

	public Qna_Admin() {
		super.mgrB.setEnabled(false); 

		adminP = new JPanel();
		qnaAdminTbP = new JPanel();
/////////// ��///////////	
		/////// �亯 �Ϸ�� �� ����
		qnaAdminTb = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component component = super.prepareRenderer(renderer, row, col);
				Object column = getValueAt(row, INDEX_OF_TYPE_COLUMN);
				if (column instanceof String) {
					String s5 = (String) column;
					if (s5.equals("�Ϸ�")) {
						component.setBackground(new Color(238, 242, 225));
						component.setForeground(Color.black);
					} else {
						component.setBackground(super.getBackground());
						component.setForeground(super.getForeground());
					}
				}
				return component;
			}
		};
/////////// ��///////////
		qnaAdminTb.addMouseListener(this);
		qnaSc = new JScrollPane(qnaAdminTb);
		memAdminB = new JButton("ȸ������");
		qnaAdminB = new JButton("ȸ������");

	}

	public void arrange(String id, int x, int y) {

		//// Q&A ������ǥ
		qnaAdminTb.setRowHeight(30);
		qnaSc.setBounds(100, 100, 800, 550);

		//// ������ ��ü�г�
		adminP.add(memAdminB);
		memAdminB.setBounds(600, 50, 150, 50);
		memAdminB.addActionListener(this);
		memAdminB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		memAdminB.setBackground(new Color(211, 221, 179));
		adminP.add(qnaAdminB);
		qnaAdminB.setBounds(750, 50, 150, 50);
		qnaAdminB.addActionListener(this);
		qnaAdminB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		qnaAdminB.setBackground(new Color(211, 221, 179));
		adminP.setBackground(Color.white);
		adminP.setLayout(null);
		adminP.setBounds(0, 0, 1000, 800);
		add(adminP);

		MainP.add(adminP);
		adminP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);
		adminP.add(qnaSc);// ǥ ��ġ

		setLocation(x, y);
		repaint();
		validate();
		setVisible(true);

		//////////// DB ���� ////////
		qnaListShow(id);
	}

	/////////////////////////// Q&A���� ���̺� //////////////////////////////

	Vector<String> columnNamesQnaAdmin = new Vector<String>(5);
	Vector rowDataQnaAdmin;
	DefaultTableModel qnaTableModel; /* column�� row ���� ���� table Model */

	public void addToRowDataQnaAdmin(int i) {
		if (columnNamesQnaAdmin.size() == 0) {
			columnNamesQnaAdmin.add("NO");
			columnNamesQnaAdmin.add("����");
			columnNamesQnaAdmin.add("���̵�");
			columnNamesQnaAdmin.add("�ۼ���");
			columnNamesQnaAdmin.add("�亯 ����");

			qnaTableModel = new DefaultTableModel(columnNamesQnaAdmin, 0) { // table model ��ü ���� �� column �� �־��� (0 �� �� ��
																			// ���ڵ� ǥ�� x)
				public boolean isCellEditable(int row, int column) { // cell ���� ����
					return false;
				}
			};
		} // ------cell ���� ����

		rowDataQnaAdmin = new Vector();
		rowDataQnaAdmin.addElement(vlist.get(i).getIdx());
		rowDataQnaAdmin.addElement(vlist.get(i).getTitle());
		rowDataQnaAdmin.addElement(vlist.get(i).getId());
		rowDataQnaAdmin.addElement(vlist.get(i).getQDate());
		rowDataQnaAdmin.addElement(vlist.get(i).getAnswer());

		qnaTableModel.addRow(rowDataQnaAdmin);
		qnaAdminTb.setModel(qnaTableModel);
		qnaAdminTb.getTableHeader().setReorderingAllowed(false); // header �̵� �Ұ�
		qnaAdminTb.setSelectionMode(1);
		qnaAdminTb.setFont(new Font("������������� ExtraBold", Font.PLAIN, 13));

		TableColumn column = null;
		for (int j = 0; j < rowDataQnaAdmin.size(); j++) {
			column = qnaAdminTb.getColumnModel().getColumn(j);
			if (j == 1) {
				column.setPreferredWidth(200);

			}
			if (j == 0) {
				column.setPreferredWidth(5);
			}
		}
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = qnaAdminTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);

	}

//////////////Q&A ���̺� ��/////////////

	public void qnaListShow(String id) {
		// JTable �ʱ�ȭ
		DefaultTableModel model = (DefaultTableModel) qnaAdminTb.getModel();
		model.setRowCount(0);
		//
		vlist = mgr.QnaList("QnaAdmin", id);
//		System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataQnaAdmin(i);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Object obj = e.getSource();
		if (obj == qnaAdminTb) {
			if (e.getClickCount() == 2) {
				int qnaRow = qnaAdminTb.getSelectedRow();
				int qnaItem = (int) qnaTableModel.getValueAt(qnaRow, 0);
				String selected_Title = qnaTableModel.getValueAt(qnaRow, 1).toString();
				
				if(selected_Title.equals("   ������ �����ϴ�."))		
					return;		
				
//				System.out.println("@@@@ ��� qna ? : "+qnaItem);
				new Qna_Answer_Admin(qnaItem).arrange(id, getX(), getY());
//				// JTable �ʱ�ȭ
//				DefaultTableModel model = (DefaultTableModel)qnaAdminTb.getModel();
//				model.setRowCount(0);
//				//
				saveThisPage();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object obj = e.getSource();
		if (e.getActionCommand().equals("ȸ������")) {
			MemAdmin ma = new MemAdmin();
			ma.arrange(id, getX(), getY());
			dispose();
		}
	}

	public static void main(String[] args) {
		Qna_Admin qa = new Qna_Admin();
		qa.arrange("admin", 0, 0);
	}

}