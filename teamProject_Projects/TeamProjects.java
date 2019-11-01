////////�� ������Ʈ Ȩ ȭ��-�׷��� ����//////

package teamProject_Projects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import teamProject_Frame.TFrame1_null;
import teamProject_Member.TeamProject_Member_Mgr;

public class TeamProjects extends TFrame1_null {
	private static final int INDEX_OF_TYPE_COLUMN = 2;

	JPanel allP, tPjtP, tComLiP, tComMemScP;
	JLabel tPjtNameL, tPjtStartL, tPjtPpL, tPjtEndL, tLackL, tPjtAveL, tComLiL, tPhoL;
	JButton tSetB;
	JTable tComMemTb;
	JScrollPane tComMemSc;
	JLabel tPjtAve2L;
	JTextField tPjtAveTf;
	int projectIndex;
	int rate = 0;
	
	TeamProject_Projects_Mgr mgr;
	Vector<TeamProject_Projects_Bean> vlist;
	TeamProject_Projects_Bean bean;
	
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH) + 1;
	int day = now.get(Calendar.DAY_OF_MONTH);
	String today = year + "-" + month + "-" + day;
	

	public TeamProjects(int projectIndex) {
		this.projectIndex = projectIndex;
		
		allP = new JPanel(); // ��ü �г�
		tPjtP = new JPanel(); // ��������Ʈ �󼼳���
		tComLiP = new JPanel(); // �ϷḮ��Ʈ �г�
		tComMemScP = new JPanel();// �ϷḮ��Ʈ ��� ��ũ��
		tPjtNameL = new JLabel(); // ��ǥ�̸�
		tPjtStartL = new JLabel();
		tPjtPpL = new JLabel();
		tPjtEndL = new JLabel();
		tLackL = new JLabel();// ��ݿ���
		tPjtAveL = new JLabel("������ ���� �޼���"); // ��մ޼��� �̸���
		tComLiL = new JLabel("������ ���� �Ϸ� ���"); // �ϷḮ��Ʈ �̸���
		tPhoL = new JLabel(); // ��������
		tSetB = new JButton(new ImageIcon("teamProject_Projects/setting.png")); // �� ������ư
		tSetB.addActionListener(this);
		tPjtAve2L = new JLabel();// �׷����̹���
		tPjtAveTf = new JTextField();// ��� �޼���(%)
/////////// ��///////////
		tComMemTb = new JTable() {///// ���̺� �����ص� �� �ȹٲ�
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
		};
/////////// ��///////////
		tComMemSc = new JScrollPane(tComMemTb);
		setVisible(true);
	}

	@Override
	public void arrange(String id, int x, int y) {

		// ��������Ʈ �� ���� ȭ��
		tPjtP.add(tPjtNameL); // ������Ʈ��
		tPjtNameL.setBounds(250, 55, 220, 40);
		tPjtNameL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 20));
		tPjtP.add(tPjtStartL); // ������
		tPjtStartL.setBounds(250, 95, 220, 40);
		tPjtStartL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 15));
		tPjtP.add(tPjtPpL); // �����ο�
		tPjtPpL.setBounds(250, 135, 220, 40);
		tPjtPpL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 15));
		tPjtP.add(tPjtEndL); // ������
		tPjtEndL.setBounds(250, 175, 220, 40);
		tPjtEndL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 15));
		tPjtP.add(tLackL);// ��������
		tLackL.setBounds(250, 215, 220, 40);
		tLackL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 15));
		tPjtP.add(tPjtAveL);// ��ձ׷��� Ÿ��Ʋ ��
		tPjtAveL.setHorizontalAlignment(SwingConstants.CENTER);
		tPjtAveL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		tPjtAveL.setOpaque(true);
		tPjtAveL.setBackground(new Color(211, 221, 179));
		tPjtAveL.setBounds(500, 10, 250, 40);
		tPjtP.add(tSetB);// ������ư
		tSetB.setBounds(760, 260, 25, 25);
		tSetB.setBorderPainted(false);
		tSetB.setFocusPainted(false);
		tSetB.setContentAreaFilled(false);
		tPjtP.add(tPhoL);// ������ ����(�ڸ�)
		tPhoL.setOpaque(true);
		tPhoL.setBackground(Color.white);
		tPhoL.setBounds(10, 50, 200, 200);
		tPjtP.add(tPjtAve2L);
		tPjtAve2L.setLayout(null);

//		tPjtAve2L.setIcon(new ImageIcon("teamProject_Projects/90p.png"));
		tPjtAve2L.setBounds(525, 60, 200, 200);// �׷���
		tPjtAveTf.setBounds(590, 147, 70, 30);
		tPjtAveTf.setFont(new Font("������������� ExtraBold", Font.PLAIN, 20));
		tPjtAveTf.setEditable(false);
		tPjtAveTf.setBorder(null);
		tPjtAveTf.setBackground(Color.white);
		tPjtAveTf.setHorizontalAlignment(SwingConstants.CENTER);
		tPjtAveTf.setForeground(new Color(59,89,72));
		tPjtP.add(tPjtAveTf);

		tPjtP.setBounds(100, 0, 800, 300);
		tPjtP.setLayout(null);
		tPjtP.setBackground(Color.white);
		add(tPjtP);

		///// �ϷḮ��Ʈ Ÿ��Ʋ �̸�
		tComLiL.setBounds(0, 0, 800, 40);
		tComLiL.setHorizontalAlignment(SwingConstants.CENTER);
		tComLiL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		tComLiL.setOpaque(true);
		tComLiL.setBackground(new Color(211, 221, 179));
		add(tComLiL);

///�ϷḮ��Ʈ ���̺�
		tComMemTb.setRowHeight(70);
		tComMemScP.setBounds(20, 50, 760, 350);
		tComMemScP.setLayout(new BorderLayout());
		tComMemScP.setBackground(Color.white);
		add(tComMemScP);
		tComMemScP.add(tComMemSc);

		/// �ϷḮ��Ʈ
		tComLiP.add(tComLiL);
		tComLiP.add(tComMemScP);
		tComLiP.setLayout(null);
		tComLiP.setBounds(100, 300, 800, 420);
		tComLiP.setBackground(Color.white);
		add(tComLiP);

		/// ��ü�г�
		allP.add(tPjtP);
		allP.add(tComLiP);
		allP.setBounds(0, 0, 1000, 800);
		allP.setLayout(null);
		allP.setBackground(Color.white);
		add(allP);

		MainP.add(allP);
		allP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);

		
		// DB ����� ���� ����
		mgr = new TeamProject_Projects_Mgr();
		vlist = mgr.showProject(projectIndex);
		int completeRate = mgr.getCompleteRate(projectIndex);
		//System.out.println("completeRate :  " +completeRate);
		
		String s1 = vlist.get(0).getPhoto();
		ImageIcon ss1 = new ImageIcon(new ImageIcon(s1).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
		tPhoL.setIcon(ss1);
		tPjtNameL.setText( vlist.get(0).getPjtName());
		tPjtStartL.setText(doDiffOfDates(vlist.get(0).getStartDate(), today)+ "��° ������");
		tPjtPpL.setText("�����ο� " + vlist.get(0).getNumOfPpl() + "/" + vlist.get(0).getNumMax());
		tPjtEndL.setText("������ : " + vlist.get(0).getEndDate());
		if(vlist.get(0).getPassword().length()==0) {
			tLackL.setText("[OPEN]");
		}else {
			tLackL.setText("[LOCK]");
		}
		tPjtAveTf.setText(completeRate+"%");
		tPjtAve2L.setIcon(new ImageIcon(getGraphName(completeRate)));
		
		setLocation(x, y);
		setVisible(true);
		validate();
		
		completeListShow(projectIndex);
	}

	/// ��¥ ���� ���ϴ� �޼ҵ�
	public int doDiffOfDates(String startDate, String today) {
		//System.out.println("startDate : " + startDate);
		//System.out.println("today : " + today);

		int diffDays = 0;

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date beginDate = formatter.parse(startDate);
			Date endDate = formatter.parse(today);

			// �ð����̸� �ð�,��,�ʸ� ���� ������ ������ �Ϸ� ������ ����
			long diff = endDate.getTime() - beginDate.getTime();
			long diffDaysLong = diff / (24 * 60 * 60 * 1000);

			diffDays = (int) diffDaysLong;
			//System.out.println("��¥����=" + diffDays);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffDays;
	}
	
	/// �޼��� ���� �׷��� ���� ���� ���ϴ� �޼ҵ�
	public String getGraphName(int completeRate){
		String fileName="";
		if(completeRate ==0) {
			fileName = "teamProject_Projects/0p.png";
		}else if(completeRate>0 && completeRate<=10 ) {
			fileName = "teamProject_Projects/10p.png";
		}else if(completeRate>10 && completeRate<=20 ) {
			fileName = "teamProject_Projects/20p.png";
		}else if(completeRate>20 && completeRate<=30 ) {
			fileName = "teamProject_Projects/30p.png";
		}else if(completeRate>30 && completeRate<=40 ) {
			fileName = "teamProject_Projects/40p.png";
		}else if(completeRate>40 && completeRate<=49 ) {
			fileName = "teamProject_Projects/45p.png";
		}else if(completeRate==50 ) {
			fileName = "teamProject_Projects/50p.png";
		}else if(completeRate>50 && completeRate<=60 ) {
			fileName = "teamProject_Projects/60p.png";
		}else if(completeRate>60 && completeRate<=70 ) {
			fileName = "teamProject_Projects/70p.png";
		}else if(completeRate>70 && completeRate<=80 ) {
			fileName = "teamProject_Projects/80p.png";
		}else if(completeRate>80 && completeRate<=90 ) {
			fileName = "teamProject_Projects/90p.png";
		}else if(completeRate>90 && completeRate<=99 ) {
			fileName = "teamProject_Projects/95p.png";
		}else if( completeRate==100 ) {
			fileName = "teamProject_Projects/100p.png";
		}
		return fileName;
	}
	
////////// ��ǥ �Ϸ��� ����Ʈ ǥ/////////

	Vector<String> columnNamesTComMem = new Vector<String>(7);
	Vector rowDataTComMem;
	DefaultTableModel tComMemTableModel; /* column�� row ���� ���� table Model */

// ���ڵ� �� �ִ� �޼ҵ�//
	public void addToRowDataTComMem(int i) {
		if (columnNamesTComMem.size() == 0) { //// ���� ���� ��( ���Ͱ� ��� ���� ��) ���� columnNamesTodo �� �־���
			columnNamesTComMem.add("No");
			columnNamesTComMem.add("����");
			columnNamesTComMem.add("���̵�");
			columnNamesTComMem.add("���θ�ǥ��");
			columnNamesTComMem.add("�����ϱ���");
			columnNamesTComMem.add("������ �Ϸ�ð�");
			tComMemTableModel = new PjtTableModel(columnNamesTComMem, 0) { // table model ��ü ���� �� column �� �־��� (0 ��
																			// �� ��
				// ���ڵ� ǥ�� x)
				public boolean isCellEditable(int row, int column) { // cell ���� ����
					return false;
				}
			}; // table model ��ü ���� �� column �� �־��� (0 �� �� �� ���ڵ� ǥ�� x)
		}

		rowDataTComMem = new Vector();
		
		// ���ڵ� �� ���� (DB���� ����;���)
		rowDataTComMem = new Vector();
		rowDataTComMem.addElement(i+1);
		ImageIcon ss2 = new ImageIcon(new ImageIcon(vlist.get(i).getPhoto()).getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		rowDataTComMem.addElement(ss2);
		rowDataTComMem.addElement(vlist.get(i).getMemId());
		rowDataTComMem.addElement("   " +vlist.get(i).getDetailPlan());
		if(vlist.get(i).getJoinDate() == null) {
			rowDataTComMem.addElement(null);
		}else {
			rowDataTComMem.addElement(doDiffOfDates(vlist.get(i).getJoinDate(), today)+"��° ������");
		}
		rowDataTComMem.addElement(vlist.get(i).getCompleteTime());

		tComMemTableModel.addRow(rowDataTComMem);
		tComMemTb.setModel(tComMemTableModel);
		tComMemTb.getTableHeader().setReorderingAllowed(false); // header �̵� �Ұ�
		tComMemTb.setSelectionMode(1);
		tComMemTb.setFont(new Font("������������� ExtraBold", Font.PLAIN, 13));

///// column ���� ���� 
		TableColumn column = null;
		for (int j = 0; j < rowDataTComMem.size(); j++) {
			column = tComMemTb.getColumnModel().getColumn(j);
			if (j == 0) {
				column.setPreferredWidth(30);
			}
			if (j == 3) {
				column.setPreferredWidth(200);
			}
		}
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = tComMemTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
		tcm.getColumn(5).setCellRenderer(dtcr);
	}

///////////

	
	//////// �Ϸ� ����Ʈ  �߰� ///////
	public void completeListShow(int pjtIndex) {
		vlist = mgr.showCompleteList(pjtIndex, today);
//		System.out.println("pjtIndex    :  " +pjtIndex);
//		System.out.println(" today    :   " + today );
//		System.out.println("!!!!!!! �Ϸ� ����Ʈ !!!!!   " + vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataTComMem(i);
		}
	}
	
/////��������ư
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (obj == searchB ||obj ==newPjtB || obj == qnaB || obj == mgrB || obj == homeB ) {
			// JTable �ʱ�ȭ !!
			DefaultTableModel Tmodel = (DefaultTableModel)tComMemTb.getModel();
			Tmodel.setRowCount(0);
			//
		}
		
		super.actionPerformed(e);
		
		if (obj == tSetB) {
			//System.out.println("������");
			new TeamGoal(projectIndex).arrange(id, getX(), getY());
			// JTable �ʱ�ȭ !!
			DefaultTableModel Tmodel = (DefaultTableModel)tComMemTb.getModel();
			Tmodel.setRowCount(0);
			//
			saveThisPage();
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
		TeamProjects tp = new TeamProjects(0);
		//tp.addToRowDataTComMem("", "", "", "", "", "");
		tp.arrange("", 0, 0);
	}
}
