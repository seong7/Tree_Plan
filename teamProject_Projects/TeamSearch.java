/////////팀 검색 결과///////////ㅇㅋ
///너웨더블클릭작동안하니...

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
	
	
	
	// DB 연결용 변수 선언
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

		allP = new JPanel();// 전체패널
		tPjtP = new JPanel();// 타이틀명+결과
		tPjtScP = new JPanel();// 서치결과 테이블패널
		tSearchL = new JLabel("팀 검색결과");// 타이틀명
/////////// ★///////////
		tPjtTb = new JTable() {///// 테이블 선택해도 색 안바뀜
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
		}; // 팀 서치결과 테이블

/////////// ★///////////
		tPjtSc = new JScrollPane(tPjtTb); // 테이블 들어있는 스크롤패널
		tPjtTb.addMouseListener(this);

	}

	@Override
	public void arrange(String id, int x, int y) {
		
		searchTf.setText(searchTerm);

		// 타이틀 라벨
		tSearchL.setBounds(0, 20, 800, 40);
		tSearchL.setHorizontalAlignment(SwingConstants.CENTER);
		tSearchL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		tSearchL.setOpaque(true);
		tSearchL.setBackground(new Color(211, 221, 179));
		add(tSearchL);

		tPjtTb.setRowHeight(120);
		tPjtScP.setBounds(20, 90, 760, 620);
		tPjtScP.setLayout(new BorderLayout());
		tPjtScP.setBackground(Color.white);
		tPjtScP.add(tPjtSc);

		/// 첫번째 검색결과
		tPjtP.add(tPjtScP);
		tPjtP.add(tSearchL);
		tPjtP.setBounds(100, 0, 800, 720);
		tPjtP.setLayout(null);
		tPjtP.setBackground(Color.white);
		add(tPjtP);

/////전체패널
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

//////////팀 검색결과 테이블/////////

	Vector<String> columnNamesTSearchList = new Vector<String>(7);
	Vector rowDataTSearchList;
	DefaultTableModel tSearchTableModel; /* column과 row 값을 가질 table Model */

//레코드 값 넣는 메소드//
	public void addToRowDataTeam(int i) {
		if (columnNamesTSearchList.size() == 0) { //// 최초 실행 시( 벡터가 비어 있을 때) 에만 columnNamesTodo 값 넣어줌
			columnNamesTSearchList.add("No");
			columnNamesTSearchList.add("사진");
			columnNamesTSearchList.add("팀목표");
			columnNamesTSearchList.add("잠금여부");
			columnNamesTSearchList.add("시작일");
			columnNamesTSearchList.add("참여인원");
			columnNamesTSearchList.add("종료일");
			tSearchTableModel = new PjtTableModel(columnNamesTSearchList, 0) { // table model 객체 생성 후 column 값 넣어줌
																				// (0 은 빈 값
// 레코드 표시 x)
				public boolean isCellEditable(int row, int column) { // cell 수정 금지
					return false;
				}
			}; // table model 객체 생성 후 column 값 넣어줌 (0 은 빈 값 레코드 표시 x)
		}

		rowDataTSearchList = new Vector();
		rowDataTSearchList = new Vector();
		rowDataTSearchList.add(vlist.get(i).getPjtIndex());
		ImageIcon ss1 = new ImageIcon(new ImageIcon(vlist.get(i).getPhoto()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		rowDataTSearchList.add(ss1);
		rowDataTSearchList.add(vlist.get(i).getPjtName());
		if(vlist.get(i).getPassword() == null) {
			if(vlist.get(i).getPjtName().equals("검색 결과가 없습니다."))
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
		tPjtTb.getTableHeader().setReorderingAllowed(false); // header 이동 불가
		tPjtTb.setSelectionMode(1);

///// column 길이 조절 
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
		// 특정한 셀 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = tPjtTb.getColumnModel();
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
		tcm.getColumn(5).setCellRenderer(dtcr);
		tcm.getColumn(6).setCellRenderer(dtcr);
	}

/////////////////표 끝

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
				
				if(pjtTitle.equals("검색 결과가 없습니다.")) {
					return;
				}
				
				// 비밀번호 방인지 확인
				for (int i = 0; i < vlist.size(); i++) {
					if (vlist.get(i).getPjtIndex() == searchItem) {
						if (vlist.get(i).getPassword() != null) { // 비밀번호 설정되어 있으면 input 받음
							try {
								String userPwd = JOptionPane.showInputDialog(null, "비밀번호", "팀목표 [ Locked ]", JOptionPane.OK_CANCEL_OPTION);
								String dbPwd = vlist.get(i).getPassword();
								if (userPwd.equals(dbPwd)) {
									TeamProjects tp = new TeamProjects(searchItem);
									tp.arrange(id, getX(), getY());
									// JTable 초기화 !!
									DefaultTableModel Tmodel = (DefaultTableModel) tPjtTb.getModel();
									Tmodel.setRowCount(0);
									//
									saveThisPage();
								} else {
									JOptionPane.showMessageDialog(null, "비밀번호가 맞지 않습니다.", "팀목표 [ Locked ]",
											JOptionPane.ERROR_MESSAGE);
									break;
								}
							} catch (NullPointerException x) {
								return;
							}
						} else {
							TeamProjects tp = new TeamProjects(searchItem);
							tp.arrange(id, getX(), getY());
							// JTable 초기화 !!
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

	
	////////팀 목표 추가 ///////
	public void teamProjectListShow() {
		vlist = mgr.teamProjectSearchList(id, stkn);
		//System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataTeam(i);
		}
	}
	
	////////////////// 사진인식 관련
	class PjtTableModel extends DefaultTableModel {
		PjtTableModel(Vector<String> columnNames, int rowNum) {
			super(columnNames, rowNum);
		}

		/////////////////////////// 사진 인식 /////////////////////
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