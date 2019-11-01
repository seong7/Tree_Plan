//ㅇㅋㅇㅋ/////QnA 관리페이지///////
//프레임 2받나?

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

	// DB 연결용 변수 선언
	TeamProject_Member_Mgr mgr = new TeamProject_Member_Mgr();
	Vector<TeamProject_Member_Bean> vlist;
	
	public MemAdmin() {

		super.mgrB.setEnabled(false);
		
		adminP = new JPanel();
		qnaAdminTbP = new JPanel();
		memAdminTb = new JTable();
		memAdminTb.addMouseListener(this);
		memSc = new JScrollPane(memAdminTb);
		memAdminB = new JButton("회원관리");
		qnaAdminB = new JButton("회원문의");
		memAdminB = new JButton("회원관리");
		qnaAdminB = new JButton("회원문의");
	}

	public void arrange(String id, int x, int y) {

		//// Q&A 관리자표
		memAdminTb.setRowHeight(30);
		memSc.setBounds(100, 100, 800, 550);

		//// 관리자 전체패널
		adminP.add(memAdminB);
		memAdminB.setBounds(600, 50, 150, 50);
		memAdminB.addActionListener(this);
		memAdminB.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		memAdminB.setBackground(new Color(211, 221, 179));
		adminP.add(qnaAdminB);
		qnaAdminB.setBounds(750, 50, 150, 50);
		qnaAdminB.addActionListener(this);
		qnaAdminB.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		qnaAdminB.setBackground(new Color(211, 221, 179));
		adminP.setBackground(Color.WHITE);
		adminP.setLayout(null);
		adminP.setBounds(0, 0, 1000, 800);
		add(adminP);

		MainP.add(adminP);
		adminP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);
		adminP.add(memSc);// 표 위치

		setLocation(x, y);
		repaint();
		validate();
		setVisible(true);
		
		//////////////////////// DB 연동 code /////////////
		memberListShow();
	}

	/////////////////////////// Member 관리 테이블 //////////////////////////////

	Vector<String> columnNamesMemAdmin = new Vector<String>(8);
	Vector rowDataMemAdmin;
	DefaultTableModel memAdminTableModel; /* column과 row 값을 가질 table Model */

	public void addToRowDataMemAdmin(int i) {
		if (columnNamesMemAdmin.size() == 0) {
			columnNamesMemAdmin.add("NO");
			columnNamesMemAdmin.add("아이디");
			columnNamesMemAdmin.add("이름");
			columnNamesMemAdmin.add("메일주소");
			columnNamesMemAdmin.add("주소");
			columnNamesMemAdmin.add("비밀번호 찾기 질문");
			columnNamesMemAdmin.add("비밀번호 찾기 답변");

			memAdminTableModel = new DefaultTableModel(columnNamesMemAdmin, 0) { // table model 객체 생성 후 column 값 넣어줌 (0
																					// 은 빈
																					// 값 레코드 표시 x)
				public boolean isCellEditable(int row, int column) { // cell 수정 금지
					return false;
				}
			};
		} // ------cell 수정 금지


		
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
		memAdminTb.getTableHeader().setReorderingAllowed(false); // header 이동 불가
		memAdminTb.setSelectionMode(1);
		memAdminTb.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 13));

		
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
					JOptionPane.showMessageDialog(null, "관리자 계정은 탈퇴가 불가능합니다.", "Tree Plan", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int result = JOptionPane.showConfirmDialog(null, "[ "+memItem+" ]"+" 회원을 탈퇴시키겠습니까?", "회원 탈퇴",
						JOptionPane.YES_NO_OPTION);
				if (result == 0) {
					JPanel panel = new JPanel();
					panel.setLayout(new BorderLayout(2, 1));
					JLabel label = new JLabel("관리자 계정의 비밀번호를 입력하세요");
					JPasswordField pass = new JPasswordField();
					panel.add(label, BorderLayout.CENTER);
					panel.add(pass, BorderLayout.SOUTH);
					String[] options = new String[] { "OK", "Cancel" };
					int option = JOptionPane.showOptionDialog(null, panel, "회원 탈퇴", JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
					if (option == 0) // OK 눌렀을 때
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
								JOptionPane.showMessageDialog(null, "탈퇴가 완료되었습니다", "회원 탈퇴", JOptionPane.PLAIN_MESSAGE);
								MemAdmin ma = new MemAdmin();
								ma.arrange(id, getX(), getY());
								dispose();
							}
						} else {
							JOptionPane.showMessageDialog(null, "비밀번호가 맞지 않습니다.", "회원 탈퇴", JOptionPane.ERROR_MESSAGE);
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
//		if (e.getActionCommand().equals("회원관리")) {
//			MemAdmin ma = new MemAdmin();
//			ma.addToRowDataMemAdmin("", "", "", "", "", "", "", "");
//			ma.arrange();
//			dispose();
//		}
		if (e.getActionCommand().equals("회원문의")) {
			Qna_Admin qa = new Qna_Admin();
			qa.arrange(id, getX(), getY());
			dispose();
		}
	}
	
	public void memberListShow() {
		// JTable 초기화
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