//ㅇㅋㅇㅋ/////QnA///////

package teamProject_Manager_QnA;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import teamProject_Frame.TFrame1_null;

public class QnA extends TFrame1_null implements ActionListener {
	private static final int INDEX_OF_TYPE_COLUMN = 4;

	JPanel allP, qnaTbP;
	JLabel myQnaL, qnaTitleL, qnaPostL;
	JButton qnaSendB;
	JTextField qnaTitleTf;
	JTextArea qnaContentTa;
	JTable qnaTb;
	JScrollPane qnaListSc, qnaContentSc;
	

	// DB 연결용 변수 선언
	Manage_Qna_Mgr mgr = new Manage_Qna_Mgr();
	Vector<Manage_Qna_Bean> vlist;

	public QnA() {

		super.qnaB.setEnabled(false); 
		
		
		allP = new JPanel();
		qnaTbP = new JPanel();
		myQnaL = new JLabel("나의 Q&A");
		qnaTitleL = new JLabel("제목");
		qnaPostL = new JLabel("내용");
		qnaSendB = new JButton(" " + "질문 보내기");
		qnaSendB.addActionListener(this);
		qnaTitleTf = new JTextField("  ");
		qnaContentTa = new JTextArea("  ");
		qnaTb = new JTable(){
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component component = super.prepareRenderer(renderer, row, col);
				Object column = getValueAt(row, INDEX_OF_TYPE_COLUMN);
				if (column instanceof String) {
					String s5 = (String) column;
					if (s5.equals("완료")) {
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
		qnaListSc = new JScrollPane(qnaTb);
		qnaContentSc = new JScrollPane(qnaContentTa);
		setVisible(true);
	}

	@Override
	public void arrange(String id, int x, int y) {

		//// 질문하기 타이틀 라벨
		myQnaL.setBounds(100, 20, 800, 40);
		myQnaL.setHorizontalAlignment(SwingConstants.CENTER);
		myQnaL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		myQnaL.setOpaque(true);
		myQnaL.setBackground(new Color(211, 221, 179));
		allP.add(myQnaL);

		/// 질문 제목 타이틀
		qnaTitleL.setBounds(30, 110, 60, 30);
		qnaTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaTitleL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		allP.add(qnaTitleL);

		/// 질문 내용 타이틀
		qnaPostL.setBounds(30, 145, 60, 30);
		qnaPostL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaPostL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		allP.add(qnaPostL);

		/// 질문 제목
		qnaTitleTf.setBounds(100, 110, 800, 30);
		allP.add(qnaTitleTf);

		/// 질문 내용
		qnaContentSc.setBounds(100, 150, 800, 100);
		allP.add(qnaContentSc);

		//// 보내기 버튼
		qnaSendB.setBounds(740, 260, 160, 30);
		allP.add(qnaSendB);
		qnaSendB.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 12));
		qnaSendB.setBackground(new Color(211, 221, 179));

		//// 질문 내역 테이블
		qnaTb.setRowHeight(50);
		qnaTb.addMouseListener(this);
		qnaListSc.setBounds(100, 326, 800, 350);
		allP.add(qnaListSc);

		//// 전체 테이블
		allP.setBounds(0, 0, 1000, 800);
		allP.setLayout(null);
		allP.setBackground(Color.white);
		add(allP);

		MainP.add(allP);
		allP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);

		qnaTitleTf.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 13));
		qnaContentTa.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 13));
		
		setLocation(x, y);
		validate();
		repaint();
		setVisible(true);

		qnaTableModel = new DefaultTableModel(columnNamesQna, 0) { // table model 객체 생성 후 column 값 넣어줌 (0 은 빈 값
			// 레코드 표시 x)
			public boolean isCellEditable(int row, int column) { // cell 수정 금지
				return false;
			}
		};

		////////////DB 연동 ////////
		qnaListShow(id);
	}

	////////////////////////// Q&A 테이블 //////////////////////////////

	Vector<String> columnNamesQna = new Vector<String>(5);
	Vector rowDataQna;
	DefaultTableModel qnaTableModel; /* column과 row 값을 가질 table Model */

	public void addToRowDataQnaAdmin(int i) {
		if (columnNamesQna.size() == 0) {
			columnNamesQna.add("NO");
			columnNamesQna.add("제목");
			columnNamesQna.add("아이디");
			columnNamesQna.add("작성 시간");
			columnNamesQna.add("답변 여부");

		} // ------cell 수정 금지

		rowDataQna = new Vector();
		rowDataQna.addElement(vlist.get(i).getIdx());
		rowDataQna.addElement(vlist.get(i).getTitle());
		rowDataQna.addElement(vlist.get(i).getId());
		rowDataQna.addElement(vlist.get(i).getQDate());
		rowDataQna.addElement(vlist.get(i).getAnswer());

		qnaTableModel.addRow(rowDataQna);
		qnaTb.setModel(qnaTableModel);
		qnaTb.getTableHeader().setReorderingAllowed(false); // header 이동 불가
		qnaTb.setSelectionMode(1);
		qnaTb.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 13));
		// todoTb.setCellSelectionEnabled(false);f
		// todoTb.setRowSelectionAllowed(false);
		// todoTb.setColumnSelectionAllowed(true);

		TableColumn column = null;
		for (int j = 0; j < rowDataQna.size(); j++) {
			column = qnaTb.getColumnModel().getColumn(j);
			if (j == 1) {
				column.setPreferredWidth(200);

			}
			if (j == 0) {
				column.setPreferredWidth(5);
			}
		}
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = qnaTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
	}

	////////////// Q&A 테이블 끝/////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object obj = e.getSource();
		if (obj == qnaSendB) {
			if(qnaTitleTf.getText().trim().length() == 0) {
				JOptionPane.showInternalMessageDialog(null, "질문 제목을 입력해주세요.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			if(qnaContentTa.getText().trim().length() == 0) {
				JOptionPane.showInternalMessageDialog(null, "질문 내용을 입력해주세요.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
				return;
			}

			int result = JOptionPane.showConfirmDialog(null, "해당 내용으로 문의하시겠습니까?", "Tree Plan", JOptionPane.OK_CANCEL_OPTION);

			// System.out.printf("%d\n", result);
			if (result == 0) {
				
				try {
					Calendar now = Calendar.getInstance();
					String year = Integer.toString(now.get(Calendar.YEAR));
					String month = Integer.toString(now.get(Calendar.MONTH)+1);
					String day = Integer.toString(now.get(Calendar.DAY_OF_MONTH));

					Manage_Qna_Bean bean = new Manage_Qna_Bean();
					bean.setTitle(qnaTitleTf.getText().trim());
					bean.setQuestion(qnaContentTa.getText().trim());
					bean.setQDate(year + "년 " + month + "월 " + day + "일");
					bean.setId(id);
					boolean flag = mgr.createQnA(bean);
					if(flag) {
						JOptionPane.showMessageDialog(null, "문의 내용을 전달하였습니다.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
						new QnA().arrange(id, getX(), getY());
						dispose();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public void qnaListShow(String id) {
		vlist = mgr.QnaList("userQnA", id);
//		System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataQnaAdmin(i);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		
		Object obj = e.getSource();
		if (obj == qnaTb) {
			if (e.getClickCount() == 2) {
				int qnaRow = qnaTb.getSelectedRow();
				int selected_Index = (int)qnaTableModel.getValueAt(qnaRow, 0);
				String selected_Title = qnaTableModel.getValueAt(qnaRow, 1).toString();
				
				if(selected_Title.equals("   질문이 없습니다."))		
					return;													// 질문이 없으면 아래 코드 실행하지 않음
				
				Qna_Answer qa_a = new Qna_Answer(selected_Index);
				qa_a.arrange(id, getX(), getY());
				
				// JTable 초기화 !!
				DefaultTableModel model = (DefaultTableModel)qnaTb.getModel();
				model.setRowCount(0);
				//
				saveThisPage();
			}
		}
	}

	public static void main(String[] args) {
		QnA qn = new QnA();
		qn.arrange("aa", 0, 0);
	}
}