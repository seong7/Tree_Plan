//��������/////QnA ����������///////
//������ 2�޳�?

package teamProject_Manager_QnA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import teamProject_Frame.TFrame1_null;
import teamProject_Member.TeamProject_Member_Bean;
import teamProject_Member.TeamProject_Member_Mgr;

public class MemAdmin extends TFrame1_null {

	JPanel adminP, qnaAdminTbP;
	JTable memAdminTb;
	JScrollPane memSc;
	JButton memAdminB, qnaAdminB;

	// DB ����� ���� ����
	TeamProject_Member_Mgr mgr = new TeamProject_Member_Mgr();
	Vector<TeamProject_Member_Bean> vlist;
	
	public MemAdmin() {

		super.mgrB.setEnabled(false);
		
		adminP = new JPanel();
		qnaAdminTbP = new JPanel();
		memAdminTb = new JTable();
		memAdminTb.addMouseListener(this);
		memSc = new JScrollPane(memAdminTb);
		memAdminB = new JButton("ȸ������");
		qnaAdminB = new JButton("ȸ������");
		memAdminB = new JButton("ȸ������");
		qnaAdminB = new JButton("ȸ������");
	}

	public void arrange(String id, int x, int y) {

		//// Q&A ������ǥ
		memAdminTb.setRowHeight(30);
		memSc.setBounds(100, 100, 800, 550);

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
		adminP.setBackground(Color.WHITE);
		adminP.setLayout(null);
		adminP.setBounds(0, 0, 1000, 800);
		add(adminP);

		MainP.add(adminP);
		adminP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);
		adminP.add(memSc);// ǥ ��ġ

		setLocation(x, y);
		repaint();
		validate();
		setVisible(true);
		
		//////////////////////// DB ���� code /////////////
		memberListShow();
	}

	/////////////////////////// Member ���� ���̺� //////////////////////////////

	Vector<String> columnNamesMemAdmin = new Vector<String>(8);
	Vector rowDataMemAdmin;
	DefaultTableModel memAdminTableModel; /* column�� row ���� ���� table Model */

	public void addToRowDataMemAdmin(int i) {
		if (columnNamesMemAdmin.size() == 0) {
			columnNamesMemAdmin.add("NO");
			columnNamesMemAdmin.add("���̵�");
			columnNamesMemAdmin.add("�̸�");
			columnNamesMemAdmin.add("�����ּ�");
			columnNamesMemAdmin.add("�ּ�");
			columnNamesMemAdmin.add("��й�ȣ ã�� ����");
			columnNamesMemAdmin.add("��й�ȣ ã�� �亯");

			memAdminTableModel = new DefaultTableModel(columnNamesMemAdmin, 0) { // table model ��ü ���� �� column �� �־��� (0
																					// �� ��
																					// �� ���ڵ� ǥ�� x)
				public boolean isCellEditable(int row, int column) { // cell ���� ����
					return false;
				}
			};
		} // ------cell ���� ����


		
		rowDataMemAdmin = new Vector();
		rowDataMemAdmin.addElement(i+1);
		rowDataMemAdmin.addElement(vlist.get(i).getId());
		rowDataMemAdmin.addElement(vlist.get(i).getName());
		rowDataMemAdmin.addElement(vlist.get(i).getEmail());
		rowDataMemAdmin.addElement(vlist.get(i).getAddress());
		rowDataMemAdmin.addElement(vlist.get(i).getPwdQ());
		rowDataMemAdmin.addElement(vlist.get(i).getPwdA());

		memAdminTableModel.addRow(rowDataMemAdmin);
		memAdminTb.setModel(memAdminTableModel);
		memAdminTb.getTableHeader().setReorderingAllowed(false); // header �̵� �Ұ�
		memAdminTb.setSelectionMode(1);
		memAdminTb.setFont(new Font("������������� ExtraBold", Font.PLAIN, 13));

		
//		if(x.equals("reset")){
//			for (int j = 0; j < memAdminTableModel.getRowCount(); j++) {
//				memAdminTableModel.removeRow(j);
//			}
//		}
		
		TableColumn column = null;
		for (int j = 0; j < rowDataMemAdmin.size(); j++) {
			column = memAdminTb.getColumnModel().getColumn(j);
			if (j == 0) {
				column.setPreferredWidth(5);
			}

		}
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = memAdminTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
		tcm.getColumn(5).setCellRenderer(dtcr);
		tcm.getColumn(6).setCellRenderer(dtcr);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Object obj = e.getSource();
		if (obj == memAdminTb) {
			if (e.getClickCount() == 2) {
				int memRow = memAdminTb.getSelectedRow();
				String memItem = memAdminTableModel.getValueAt(memRow, 1).toString();
				if(memItem.equals("admin")) {
					JOptionPane.showMessageDialog(null, "������ ������ Ż�� �Ұ����մϴ�.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int result = JOptionPane.showConfirmDialog(null, "[ "+memItem+" ]"+" ȸ���� Ż���Ű�ڽ��ϱ�?", "ȸ�� Ż��",
						JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					JPanel panel = new JPanel();
					panel.setLayout(new BorderLayout(2, 1));
					JLabel label = new JLabel("������ ������ ��й�ȣ�� �Է��ϼ���");
					JPasswordField pass = new JPasswordField();
					panel.add(label, BorderLayout.CENTER);
					panel.add(pass, BorderLayout.SOUTH);
					String[] options = new String[] { "OK", "Cancel" };
					int option = JOptionPane.showOptionDialog(null, panel, "ȸ�� Ż��", JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
					if (option == 0) // OK ������ ��
					{
						char[] password = pass.getPassword();
						String pwd = "";
						for (int i = 0; i < password.length; i++) {
							pwd += password[i];
						}
						String DBpwd = mgr.getPwd("admin");
						
						if(pwd.equals(DBpwd)) {
							boolean flag = mgr.deleteMember(memItem);
							if(flag) {
								JOptionPane.showMessageDialog(null, "Ż�� �Ϸ�Ǿ����ϴ�", "ȸ�� Ż��", JOptionPane.PLAIN_MESSAGE);
								MemAdmin ma = new MemAdmin();
								ma.arrange(id, getX(), getY());
								dispose();
							}
						} else {
							JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� �ʽ��ϴ�.", "ȸ�� Ż��", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object obj = e.getSource();
//		if (e.getActionCommand().equals("ȸ������")) {
//			MemAdmin ma = new MemAdmin();
//			ma.addToRowDataMemAdmin("", "", "", "", "", "", "", "");
//			ma.arrange();
//			dispose();
//		}
		if (e.getActionCommand().equals("ȸ������")) {
			Qna_Admin qa = new Qna_Admin();
			qa.arrange(id, getX(), getY());
			dispose();
		}
	}
	
	public void memberListShow() {
		// JTable �ʱ�ȭ
		DefaultTableModel model = (DefaultTableModel)memAdminTb.getModel();
		model.setRowCount(0);
		//
		vlist = mgr.listMember("MemAdmin", id);
//		System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataMemAdmin(i);
		}
	}
	
//	public void reSet_memberList() {
//		addToRowDataMemAdmin(0, "reset");
//	}

	public static void main(String[] args) {
		MemAdmin ma = new MemAdmin();
		ma.arrange("admin", 0, 0);
	}
}