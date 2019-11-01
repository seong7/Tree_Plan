/////////�� �˻� ���///////////����
///�ʿ�����Ŭ���۵����ϴ�...

package teamProject_Projects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class TeamSearch extends TFrame1_null {
	private static final int INDEX_OF_TYPE_COLUMN = 2;
	
	JPanel allP, tPjtP, tPjtScP;
	JLabel tSearchL;
	JTable tPjtTb;
	JScrollPane tPjtSc;
	Icon tPhoIc;
	String searchTerm;
	StringTokenizer stkn;
	
	
	
	// DB ����� ���� ����
	TeamProject_Projects_Mgr mgr = new TeamProject_Projects_Mgr();
	Vector<TeamProject_Projects_Bean> vlist;
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH) + 1;
	int day = now.get(Calendar.DAY_OF_MONTH);
	String today = year + "-" + month + "-" + day;

	public TeamSearch(String searchTerm) {
		this.searchTerm = searchTerm;
		stkn = new StringTokenizer(searchTerm, " ");

		allP = new JPanel();// ��ü�г�
		tPjtP = new JPanel();// Ÿ��Ʋ��+���
		tPjtScP = new JPanel();// ��ġ��� ���̺��г�
		tSearchL = new JLabel("�� �˻����");// Ÿ��Ʋ��
/////////// ��///////////
		tPjtTb = new JTable() {///// ���̺� �����ص� �� �ȹٲ�
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component component = super.prepareRenderer(renderer, row, col);
				Object column = getValueAt(row, INDEX_OF_TYPE_COLUMN);
				if (column instanceof String) {
					String s3 = (String) column;
					if (s3.equals("")) {

						component.setBackground(super.getBackground());
						component.setForeground(super.getBackground());
					} else {
						component.setBackground(super.getBackground());
						component.setForeground(super.getForeground());
					}
				}
				return component;
			}
		}; // �� ��ġ��� ���̺�

/////////// ��///////////
		tPjtSc = new JScrollPane(tPjtTb); // ���̺� ����ִ� ��ũ���г�
		tPjtTb.addMouseListener(this);

	}

	@Override
	public void arrange(String id, int x, int y) {
		
		searchTf.setText(searchTerm);

		// Ÿ��Ʋ ��
		tSearchL.setBounds(0, 20, 800, 40);
		tSearchL.setHorizontalAlignment(SwingConstants.CENTER);
		tSearchL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		tSearchL.setOpaque(true);
		tSearchL.setBackground(new Color(211, 221, 179));
		add(tSearchL);

		tPjtTb.setRowHeight(120);
		tPjtScP.setBounds(20, 90, 760, 620);
		tPjtScP.setLayout(new BorderLayout());
		tPjtScP.setBackground(Color.white);
		tPjtScP.add(tPjtSc);

		/// ù��° �˻����
		tPjtP.add(tPjtScP);
		tPjtP.add(tSearchL);
		tPjtP.setBounds(100, 0, 800, 720);
		tPjtP.setLayout(null);
		tPjtP.setBackground(Color.white);
		add(tPjtP);

/////��ü�г�
		allP.add(tPjtP);
		allP.setBounds(0, 0, 1000, 800);
		allP.setLayout(null);
		allP.setBackground(Color.white);
		add(allP);

		MainP.add(allP);
		allP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);

		setLocation(x, y);
		setVisible(true);
		validate();
		
		teamProjectListShow();
	}

//////////�� �˻���� ���̺�/////////

	Vector<String> columnNamesTSearchList = new Vector<String>(7);
	Vector rowDataTSearchList;
	DefaultTableModel tSearchTableModel; /* column�� row ���� ���� table Model */

//���ڵ� �� �ִ� �޼ҵ�//
	public void addToRowDataTeam(int i) {
		if (columnNamesTSearchList.size() == 0) { //// ���� ���� ��( ���Ͱ� ��� ���� ��) ���� columnNamesTodo �� �־���
			columnNamesTSearchList.add("No");
			columnNamesTSearchList.add("����");
			columnNamesTSearchList.add("����ǥ");
			columnNamesTSearchList.add("��ݿ���");
			columnNamesTSearchList.add("������");
			columnNamesTSearchList.add("�����ο�");
			columnNamesTSearchList.add("������");
			tSearchTableModel = new PjtTableModel(columnNamesTSearchList, 0) { // table model ��ü ���� �� column �� �־���
																				// (0 �� �� ��
// ���ڵ� ǥ�� x)
				public boolean isCellEditable(int row, int column) { // cell ���� ����
					return false;
				}
			}; // table model ��ü ���� �� column �� �־��� (0 �� �� �� ���ڵ� ǥ�� x)
		}

		rowDataTSearchList = new Vector();
		rowDataTSearchList = new Vector();
		rowDataTSearchList.add(vlist.get(i).getPjtIndex());
		ImageIcon ss1 = new ImageIcon(new ImageIcon(vlist.get(i).getPhoto()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		rowDataTSearchList.add(ss1);
		rowDataTSearchList.add(vlist.get(i).getPjtName());
		if(vlist.get(i).getPassword() == null) {
			if(vlist.get(i).getPjtName().equals("�˻� ����� �����ϴ�."))
				rowDataTSearchList.add("");
			else
				rowDataTSearchList.add("[ OPEN ]");
		}else {
			rowDataTSearchList.add("[ LOKED ]");
		}
		rowDataTSearchList.add(vlist.get(i).getStartDate());
		rowDataTSearchList.add(vlist.get(i).getNumOfPpl() +" / " + vlist.get(i).getNumMax());
		rowDataTSearchList.add(vlist.get(i).getEndDate());
		tSearchTableModel.addRow(rowDataTSearchList);
		tPjtTb.setModel(tSearchTableModel);
		tPjtTb.getTableHeader().setReorderingAllowed(false); // header �̵� �Ұ�
		tPjtTb.setSelectionMode(1);

///// column ���� ���� 
		TableColumn column = null;
		for (int j = 0; j < rowDataTSearchList.size(); j++) {
			column = tPjtTb.getColumnModel().getColumn(j);
			if (j == 0) {
				column.setPreferredWidth(30);
			}
			if (j == 2) {
				column.setPreferredWidth(200);
			}
		}
		// Ư���� �� ��� ����
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = tPjtTb.getColumnModel();
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
		tcm.getColumn(5).setCellRenderer(dtcr);
		tcm.getColumn(6).setCellRenderer(dtcr);
	}

/////////////////ǥ ��

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Object obj = e.getSource();
		if (obj == tPjtTb) {
			if (e.getClickCount() == 2) {
				int searchRow = tPjtTb.getSelectedRow();
				int searchItem = (int)tSearchTableModel.getValueAt(searchRow, 0);
				String pjtTitle = tSearchTableModel.getValueAt(searchRow, 2).toString();

//				System.out.println("searchItem " + searchItem);
				
				if(pjtTitle.equals("�˻� ����� �����ϴ�.")) {
					return;
				}
				
				// ��й�ȣ ������ Ȯ��
				for (int i = 0; i < vlist.size(); i++) {
					if (vlist.get(i).getPjtIndex() == searchItem) {
						if (vlist.get(i).getPassword() != null) { // ��й�ȣ �����Ǿ� ������ input ����
							try {
								String userPwd = JOptionPane.showInputDialog(null, "��й�ȣ", "����ǥ [ Locked ]", JOptionPane.OK_CANCEL_OPTION);
								String dbPwd = vlist.get(i).getPassword();
								if (userPwd.equals(dbPwd)) {
									TeamProjects tp = new TeamProjects(searchItem);
									tp.arrange(id, getX(), getY());
									// JTable �ʱ�ȭ !!
									DefaultTableModel Tmodel = (DefaultTableModel) tPjtTb.getModel();
									Tmodel.setRowCount(0);
									//
									saveThisPage();
								} else {
									JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� �ʽ��ϴ�.", "����ǥ [ Locked ]",
											JOptionPane.ERROR_MESSAGE);
									break;
								}
							} catch (NullPointerException x) {
								return;
							}
						} else {
							TeamProjects tp = new TeamProjects(searchItem);
							tp.arrange(id, getX(), getY());
							// JTable �ʱ�ȭ !!
							DefaultTableModel Tmodel = (DefaultTableModel)tPjtTb.getModel();
							Tmodel.setRowCount(0);
							//
							saveThisPage();
							break;
						}
					}
				}
			}
		}
	}

	
	////////�� ��ǥ �߰� ///////
	public void teamProjectListShow() {
		vlist = mgr.teamProjectSearchList(id, stkn);
		//System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataTeam(i);
		}
	}
	
	////////////////// �����ν� ����
	class PjtTableModel extends DefaultTableModel {
		PjtTableModel(Vector<String> columnNames, int rowNum) {
			super(columnNames, rowNum);
		}

		/////////////////////////// ���� �ν� /////////////////////
		public Class getColumnClass(int col) {
			// System.out.println(getValueAt(0, col).getClass());
			return getValueAt(0, col).getClass();
		}
	}

	public static void main(String[] args) {
		TeamSearch ts = new TeamSearch("");
		ts.arrange("", 0, 0);
	}
}