//////ǥ�۾�ü����!/////Ȩȭ��////////���� �⺻�̹��� �߰�
//Ȩ��ư���� �ڷΰ��� ������ ���� �����ϴ°�
//���θ�ǥ Ŭ���ϸ� ��ǥ ����â? ����â?
//�����?
//�׷���..?

package teamProject_Projects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import teamProject_Frame.TFrame1_null;
import teamProject_Manager_QnA.Manage_Qna_Bean;
import teamProject_Manager_QnA.Manage_Qna_Mgr;

public class HomeProjects extends TFrame1_null {
    private static final int COLUMN_SIZE = 3;
	private static final int INDEX_OF_TYPE_COLUMN = 2;

	JPanel allP, scP, pP, tP, todayP, todayComP, todoP, pPjtP, tPjtP;
	JLabel todayComL, todoL, pPjtL, tPjtL;
	JButton todoPPjtDeB, todoTPjtDeB;
	JCheckBox todoPPjtDeC, todoTPjtDeC;
	JTable todoTb, pPjtTb, tPjtTb;
	JScrollPane todoSc, pPjtSc, tPjtSc;

	JLabel todayCom2L;
	JTextField todayComTf;

	
	// DB ����� ���� ����
	TeamProject_Projects_Mgr mgr = new TeamProject_Projects_Mgr();
	Vector<TeamProject_Projects_Bean> vlist;
	Calendar now = Calendar.getInstance();
	int year = now.get(Calendar.YEAR);
	int month = now.get(Calendar.MONTH) + 1;
	int day = now.get(Calendar.DAY_OF_MONTH);
	String today = year + "-" + month + "-" + day;
	int rate = 0;
	double todoComplete;
	
	
	
////////////////////////////////////// ���̺� �Ӽ�_�迭, ���ڵ�_���� ���� /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/////////////////////////// To-do ���̺� //////////////////////////////
	Vector<String> columnNamesTodo = new Vector<String>(3);
	Vector rowDataTodo;
	DefaultTableModel todoTableModel; /* column�� row ���� ���� table Model */

	// ���ڵ� �� �ִ� �޼ҵ�//
	public void addToRowDataTodo(int i) {
		if (columnNamesTodo.size() == 0) { //// ���� ���� ��( ���Ͱ� ��� ���� ��) ���� columnNamesTodo �� �־���
			columnNamesTodo.addElement("��ǥ No");
			//columnNamesTodo.addElement("�з�");
			columnNamesTodo.addElement("�� ��");
			columnNamesTodo.addElement("�ϷῩ��");
			todoTableModel = new DefaultTableModel(columnNamesTodo, 0) { // table model ��ü ���� �� column �� �־��� (0 �� �� ��
																			// ���ڵ� ǥ�� x)
				public boolean isCellEditable(int row, int column) { // cell ���� ����
					return false;
				}
			};
		} // ------cell ���� ����

//		s1 = "1";
//		s2 = "��";
//		s3 = "  " + "�޸��� �ϱ�";
//		s4 = "�Ϸ�"; // ���ڵ� �� ���� (DB���� ����;���) // " " �� ���� ������ ��� ������ �� �� ���� �켱 ���� �־���
		rowDataTodo = new Vector();
		rowDataTodo.addElement(vlist.get(i).getPjtIndex());
		//rowDataTodo.addElement(i);
		rowDataTodo.addElement(vlist.get(i).getDetailPlan());
		rowDataTodo.addElement(vlist.get(i).getCompletion());
		todoTableModel.addRow(rowDataTodo);
		todoTb.setModel(todoTableModel);
		todoTb.getTableHeader().setReorderingAllowed(false); // header �̵� �Ұ�
		todoTb.setSelectionMode(1);
		todoTb.setFont(new Font("������������� ExtraBold", Font.PLAIN, 13));
		// todoTb.setCellSelectionEnabled(false);f
		// todoTb.setRowSelectionAllowed(false);
		// todoTb.setColumnSelectionAllowed(true);

		///// column ���� ����
		TableColumn column = null;
		for (int j = 0; j < columnNamesTodo.size(); j++) {
			column = todoTb.getColumnModel().getColumn(j);
			if (j == 1) {
				column.setPreferredWidth(500);
			}
		}
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = todoTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		//tcm.getColumn(3).setCellRenderer(dtcr);
	}

	/////////////////////////// ���� ������Ʈ ���̺� //////////////////////////////
	//////////////////////////////////////////////////////////////////////
	
	Vector<String> columnNamesPPjt = new Vector<String>(6);
	Vector rowDataPPjt;
	DefaultTableModel pPjtTableModel; /* column�� row ���� ���� table Model */

	// ���ڵ� �� �ִ� �޼ҵ�//
	public void addToRowDataPPjt(int i) {
		if (columnNamesPPjt.size() == 0) { //// ���� ���� ��( ���Ͱ� ��� ���� ��) ���� columnNamesPPjt �� �־���
			columnNamesPPjt.addElement("No");
			columnNamesPPjt.addElement("Photo");
			columnNamesPPjt.addElement("��ǥ��");
			columnNamesPPjt.addElement("�����ϱ���");
			columnNamesPPjt.addElement("������");
				pPjtTableModel = new PjtTableModel(columnNamesPPjt, 0) { // table model ��ü ���� �� column �� �־��� (0 �� �� �� ���ڵ�
																		// ǥ�� x)
				public boolean isCellEditable(int row, int column) { // cell ���� ����
					return false;
				}
			};
			; // table model ��ü ���� �� column �� �־��� (0 �� �� �� ���ڵ� ǥ�� x)
		}

		// ���ڵ� �� ���� (DB���� ����;���)
		rowDataPPjt = new Vector();
		
		rowDataPPjt.addElement(vlist.get(i).getPjtIndex());
		ImageIcon ss2 = new ImageIcon(new ImageIcon(vlist.get(i).getPhoto()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		rowDataPPjt.addElement(ss2);
		rowDataPPjt.addElement("   " + vlist.get(i).getPjtName());
		rowDataPPjt.addElement(doDiffOfDates(vlist.get(i).getJoinDate(), today)+"��° ������");
		rowDataPPjt.addElement(vlist.get(i).getEndDate());

		pPjtTableModel.addRow(rowDataPPjt);
		pPjtTb.setModel(pPjtTableModel);
		pPjtTb.getTableHeader().setReorderingAllowed(false); // header �̵� �Ұ�
		pPjtTb.setSelectionMode(1);
		pPjtTb.setFont(new Font("������������� ExtraBold", Font.PLAIN, 13));

		///// column ���� ����
		TableColumn column = null;
		for (int j = 0; j < columnNamesPPjt.size(); j++) {
			column = pPjtTb.getColumnModel().getColumn(j);
			if (j == 0) {
				column.setPreferredWidth(10);
			}
			if (j == 1) {
				column.setPreferredWidth(30);
			}
			if (j == 2) {
				column.setPreferredWidth(200);
			}
		}
		// Ư���� �� ��� ����
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = pPjtTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
	}

	
	
	/////////////////////////// �� ������Ʈ ���̺� //////////////////////////////
	/////////////////////////////////////////////////////////
	
	Vector<String> columnNamesTPjt = new Vector<String>(5);
	Vector rowDataTPjt;
	DefaultTableModel tPjtTableModel; /* column�� row ���� ���� table Model */

	// ���ڵ� �� �ִ� �޼ҵ�//
	public void addToRowDataTPjt(int i) {
		if (columnNamesTPjt.size() == 0) { //// ���� ���� ��( ���Ͱ� ��� ���� ��) ���� columnNamesTodo �� �־���
			columnNamesTPjt.add("No");
			columnNamesTPjt.add("Photo");
			columnNamesTPjt.add("��ǥ��");
			columnNamesTPjt.add("�����ϱ���");
			columnNamesTPjt.add("������");
			tPjtTableModel = new PjtTableModel(columnNamesTPjt, 0) { // table model ��ü ���� �� column �� �־��� (0 �� �� ��
																		// ���ڵ� ǥ�� x)
				public boolean isCellEditable(int row, int column) { // cell ���� ����
					return false;
				}
			};
			 // table model ��ü ���� �� column �� �־��� (0 �� �� �� ���ڵ� ǥ�� x)
		}

		rowDataTPjt = new Vector();
//		s1 = "1";
//		s2 = "teamProject_Projects/tGoalBasic.png";
//		s3 = "�޸��� �ϱ�";
//		s4 = "10��° ������";
//		s5 = "������";
		// ���ڵ� �� ���� (DB���� ����;���)
		///// �̹��� ũ������
		
		rowDataTPjt.addElement(vlist.get(i).getPjtIndex());
		ImageIcon ss2 = new ImageIcon(new ImageIcon(vlist.get(i).getPhoto()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		rowDataTPjt.addElement(ss2);
		rowDataTPjt.addElement("   " + vlist.get(i).getPjtName());
		rowDataTPjt.addElement(doDiffOfDates(vlist.get(i).getJoinDate(), today)+"��° ������");
		rowDataTPjt.addElement(vlist.get(i).getEndDate());
		
		tPjtTableModel.addRow(rowDataTPjt);
		tPjtTb.setModel(tPjtTableModel);
		tPjtTb.getTableHeader().setReorderingAllowed(false); // header �̵� �Ұ�
		tPjtTb.setSelectionMode(1);
		tPjtTb.setFont(new Font("������������� ExtraBold", Font.PLAIN, 13));
		///// column ���� ����
		TableColumn column = null;
		for (int j = 0; j < rowDataTPjt.size(); j++) {
			column = tPjtTb.getColumnModel().getColumn(j);
			if (j == 0) {
				column.setPreferredWidth(10);
			}
			if (j == 1) {
				column.setPreferredWidth(30);
			}
			if (j == 2) {
				column.setPreferredWidth(200);
			}
		}
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = tPjtTb.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
	}

////////////////////////////////////// ���̺� ���� �Ϸ�  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// ���̺� ���� �Ϸ�  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public HomeProjects() {

		allP = new JPanel(); // �߰�

		scP = new JPanel(); // �߰�
		todayP = new JPanel(); // �߰�
		pP = new JPanel();// �߰�
		tP = new JPanel();// �߰�
		todayComP = new JPanel();
		todoP = new JPanel();
		pPjtP = new JPanel();
		tPjtP = new JPanel();
		todayComL = new JLabel("������ �޼���");
		todoL = new JLabel("TODAY TO-DO LIST");
		// todoTb = new JTable();
		//super.homeB.setEnabled(false);
		
		
		/////// ���� �Ϸ�� �� ����
		todoTb = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component component = super.prepareRenderer(renderer, row, col);
				Object column = getValueAt(row, INDEX_OF_TYPE_COLUMN);
				if (column instanceof String) {
					String s3 = (String) column;
					if (s3.equals("�Ϸ�")) {
						/////////// ��///////////
						component.setBackground(new Color(238, 242, 225));
						component.setForeground(Color.black);
						/////////// ��///////////
					} else {
						component.setBackground(super.getBackground());
						component.setForeground(super.getForeground());
					}
				}
				return component;
			}
		};
		todoSc = new JScrollPane(todoTb);
		pPjtL = new JLabel("���� ��ǥ");
		/////////// ��///////////
		pPjtTb = new JTable() {///// ���̺� �����ص� �� �ȹٲ�
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
		pPjtSc = new JScrollPane(pPjtTb);
		tPjtL = new JLabel("�� ��ǥ");
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
		};
/////////// ��///////////
		tPjtSc = new JScrollPane(tPjtTb);
		todoPPjtDeB = new JButton("�޸��� �ϱ�");
		todoTPjtDeB = new JButton("�����");
		todoPPjtDeC = new JCheckBox();
		todoTPjtDeC = new JCheckBox();
		todayCom2L = new JLabel();// �׷����̹���
		todayComTf = new JTextField();// ������ �޼���(%)

		//////////////// Event Listener �߰� ///////
		todoTb.addMouseListener(this);
		pPjtTb.addMouseListener(this);
		tPjtTb.addMouseListener(this);

	}

	@Override
	public void arrange(String id, int x, int y) {
		todayComL.setBounds(20, 10, 250, 40);
		todayComL.setHorizontalAlignment(SwingConstants.CENTER);
		todayComL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		todayComL.setOpaque(true);
		todayComL.setBackground(new Color(211, 221, 179));
		todayP.add(todayComL);// ������ �޼���

		/////////// ��///////////����
//		todayComP.setBounds(45, 60, 200, 200);
//		todayComP.setLayout(null);
//		todayComP.setBackground(Color.gray);
//		todayP.add(todayComP);// �޼��� �г�--�̹��� �־����
		/////////// ��///////////

		todoL.setBounds(290, 10, 490, 40);
		todoL.setHorizontalAlignment(SwingConstants.CENTER);
		todoL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		todoL.setOpaque(true);
		todoL.setBackground(new Color(211, 221, 179));
		todayP.add(todoL);// ���θ���Ʈ �̸�

		/////////// ��///////////����
		todayComTf.setBorder(null);
		todayComTf.setBounds(110, 132, 70, 30);
		todayComTf.setFont(new Font("������������� ExtraBold", Font.PLAIN, 20));
		todayComTf.setForeground(new Color(59,89,72));
		todayComTf.setEditable(false);
		todayComTf.setBackground(Color.white);
		//todayComTf.setBorder(border);
		todayComTf.setHorizontalAlignment(SwingConstants.CENTER);
		todayP.add(todayComTf);
		//// �޼��� ��
		todayCom2L.setBounds(45, 45, 200, 200);
		todayCom2L.setLayout(null);
		todayP.add(todayCom2L);// �޼��� ��--�̹��� �־����

		/////////// ��///////////

		todoTb.setRowHeight(30);
		todoP.setBounds(290, 60, 490, 200);
		todoP.setLayout(new BorderLayout());
		todoP.setBackground(Color.white);
		todoP.add(todoSc);
		todayP.add(todoP);

		todayP.setBounds(100, 0, 800, 270);
		todayP.setLayout(null);
		todayP.setBackground(Color.white);
		add(todayP);

		pPjtL.setBounds(0, 0, 800, 40);
		pPjtL.setHorizontalAlignment(SwingConstants.CENTER);
		pPjtL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		pPjtL.setOpaque(true);
		pPjtL.setBackground(new Color(211, 221, 179));
		add(pPjtL);// ���� ��ǥ �̸�

		pPjtTb.setRowHeight(40);
		pPjtP.setBounds(20, 50, 760, 170);
		pPjtP.setLayout(new BorderLayout());
		pPjtP.setBackground(Color.WHITE);
		add(pPjtP);// ���� ��ǥ
		pPjtP.add(pPjtSc);

		pP.add(pPjtL);
		pP.add(pPjtP);
		pP.setBounds(100, 270, 800, 230);
		pP.setLayout(null);
		pP.setBackground(Color.white);
		add(pP);

		tPjtL.setBounds(0, 0, 800, 40);
		tPjtL.setHorizontalAlignment(SwingConstants.CENTER);
		tPjtL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		tPjtL.setOpaque(true);
		tPjtL.setBackground(new Color(211, 221, 179));
		add(tPjtL);// �� ��ǥ �̸�

		tPjtTb.setRowHeight(40);
		tPjtP.setBounds(20, 50, 760, 170);
		tPjtP.setLayout(new BorderLayout());
		tPjtP.setBackground(Color.white);
		add(tPjtP);// �� ��ǥ
		tPjtP.add(tPjtSc);

		tP.add(tPjtL);
		tP.add(tPjtP);
		tP.setBounds(100, 500, 800, 230);
		tP.setLayout(null);
		tP.setBackground(Color.white);
		add(tP);

		allP.add(todayP);
		allP.add(pP);
		allP.add(tP);
		allP.setBounds(0, 0, 1000, 800);
		allP.setLayout(null);
		allP.setBackground(Color.white);
		add(allP);
		MainP.add(allP);
		allP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);

		setLocation(x, y);
		setVisible(true);
		repaint();
		validate();
		
		
		////////////DB ���� ////////
		
		personalProjectListShow(id);
		teamProjectListShow(id);
		
		Vector <Integer> pjtIdxList = new Vector();
		for (int i = 0; i < pPjtTb.getRowCount(); i++) {
			pjtIdxList.addElement((int)pPjtTb.getValueAt(i, 0));
		}
		for (int i = 0; i < tPjtTb.getRowCount(); i++) {
			pjtIdxList.addElement((int)tPjtTb.getValueAt(i, 0));
		}
		for (int i = 0; i < pjtIdxList.size(); i++) {
			toDoListShow(pjtIdxList.get(i), id);
		}
		for (int i = 0; i < todoTb.getRowCount(); i++) {
			if(todoTb.getValueAt(i, 2).equals("�Ϸ�")) {
				todoComplete += 1;
			}
		}
		rate = (int)(todoComplete/todoTb.getRowCount()*100);
		
		todayComTf.setText(rate + "%");
		todayCom2L.setIcon(new ImageIcon(getGraphName(rate)));
		
		if (todoTb.getRowCount() == 0) {
			vlist = new Vector();
			TeamProject_Projects_Bean bean = new TeamProject_Projects_Bean();
			bean.setPjtIndex(1);
			bean.setDetailPlan("������ to-do list�� ����ֽ��ϴ�.");
			bean.setCompletion("");
			vlist.addElement(bean);
			for (int j = 0; j < vlist.size(); j++) {
				addToRowDataTodo(j);
			}
		}
		
		rate = 0;
		todoComplete = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) { // Ȩ��ư�� ���̺� �׸� �߰� �޼ҵ� ����
		Object obj = e.getSource();
		
		if(obj == homeB) {
			return;
		}
		
		super.actionPerformed(e);
		
//	backB, searchB, homeB, newPjtB, qnaB, personalB, mgrB, personalInfoB,
		
		if (/* obj == homeB || */obj == searchB ||obj ==newPjtB || obj == qnaB || obj == mgrB) {
			//System.out.println("cccccc");
			// JTable �ʱ�ȭ !!
			DefaultTableModel Tmodel = (DefaultTableModel)tPjtTb.getModel();
			Tmodel.setRowCount(0);
			DefaultTableModel Pmodel = (DefaultTableModel)pPjtTb.getModel();
			Pmodel.setRowCount(0);
			DefaultTableModel todomodel = (DefaultTableModel)todoTb.getModel();
			todomodel.setRowCount(0);
			
			//
		}
	}
	
	
	////////////////// �����ν� ����
	class PjtTableModel extends DefaultTableModel {
		PjtTableModel(Vector<String> columnNames, int rowNum) {
			super(columnNames, rowNum);
		}

		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
	}

	///////// todo list ///////
	public void toDoListShow(int pjtIdx, String id) {
		vlist = mgr.showTodoList(pjtIdx, id);
		//System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataTodo(i);
		}
	}
	
	//////// ���� ��ǥ �߰� ///////
	public void personalProjectListShow(String id) {
		vlist = mgr.PersonalProjectList(id);
		//System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataPPjt(i);
		}
	}
	////////�� ��ǥ �߰� ///////
	public void teamProjectListShow(String id) {
		vlist = mgr.teamProjectList(id);
		//System.out.println(vlist.size());
		for (int i = 0; i < vlist.size(); i++) {
			addToRowDataTPjt(i);
		}
	}
	
	
	/// ��¥ ���� ���ϴ� �޼ҵ�
	public int doDiffOfDates(String startDate, String today){
		//String start = "2015-05-05";
	    //today = start;
	    //String end = "2015-04-01";
	    //startDate = end;
		//System.out.println("startDate : " + startDate);
		//System.out.println("today : " + today);

		int diffDays = 0;
		if (startDate != null) {
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
		}
		return diffDays;
	}

	/// �޼��� ���� �׷��� ���� ���� ���ϴ� �޼ҵ�
	public String getGraphName(int rate){
		String fileName="";
		if(rate ==0) {
			fileName = "teamProject_Projects/0p.png";
		}else if(rate>0 && rate<=10 ) {
			fileName = "teamProject_Projects/10p.png";
		}else if(rate>10 && rate<=20 ) {
			fileName = "teamProject_Projects/20p.png";
		}else if(rate>20 && rate<=30 ) {
			fileName = "teamProject_Projects/30p.png";
		}else if(rate>30 && rate<=40 ) {
			fileName = "teamProject_Projects/40p.png";
		}else if(rate>40 && rate<=49 ) {
			fileName = "teamProject_Projects/45p.png";
		}else if(rate==50 ) {
			fileName = "teamProject_Projects/50p.png";
		}else if(rate>50 && rate<=60 ) {
			fileName = "teamProject_Projects/60p.png";
		}else if(rate>60 && rate<=70 ) {
			fileName = "teamProject_Projects/70p.png";
		}else if(rate>70 && rate<=80 ) {
			fileName = "teamProject_Projects/80p.png";
		}else if(rate>80 && rate<=90 ) {
			fileName = "teamProject_Projects/90p.png";
		}else if(rate>90 && rate<=99 ) {
			fileName = "teamProject_Projects/95p.png";
		}else if( rate==100 ) {
			fileName = "teamProject_Projects/100p.png";
		}
		return fileName;
	}

	
	
	
	
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		Object obj = e.getSource();

///////// �������̺� �׸�Ϸ��
		if (obj == todoTb) {
			if (e.getClickCount() == 2) {
				int todoRow = todoTb.getSelectedRow();
				int pjtidx = (int)todoTableModel.getValueAt(todoRow, 0);
				String selectedComoplete = todoTableModel.getValueAt(todoRow, 2).toString();
				String todoItem = todoTableModel.getValueAt(todoRow, 1).toString();
				
				if(todoItem.equals("������ to-do list�� ����ֽ��ϴ�.")) {
					return;
				}
				
				/// �̿Ϸ� -> �Ϸ�
				if(selectedComoplete.equals("�̿Ϸ�")) {
					int result = JOptionPane.showConfirmDialog(null, "[ " + todoItem + " ] " + "�׸��� �Ϸ��Ͻðڽ��ϱ�?",
							"To-do List", JOptionPane.YES_NO_OPTION);
					
					if(result == 0) {
						boolean flag = mgr.insertComplete(pjtidx, id, todoItem);
						if(flag) {
							new HomeProjects().arrange(id, getX(), getY());
							dispose();
						}
					}
					return;
				}
				
				/// �Ϸ� -> �̿Ϸ�
				if(selectedComoplete.equals("�Ϸ�")) {
					int result = JOptionPane.showConfirmDialog(null, "[ " + todoItem + " ] " + "�׸��� ����Ͻðڽ��ϱ�?",
							"To-do List", JOptionPane.YES_NO_OPTION);
					if(result == 0) {
						boolean flag = mgr.deleteComplete(pjtidx, id);
						if(flag) {
							new HomeProjects().arrange(id, getX(), getY());
							dispose();
						}
					}
					return;
				}
			}
		}

////////// ���� ��ǥ ����Ŭ���� �����׸� ���� �̵�			
		if (obj == pPjtTb) {
			if (e.getClickCount() == 2) {
				int clickedRow = pPjtTb.getSelectedRow();
				String pPjtNull = pPjtTableModel.getValueAt(clickedRow, 2).toString();
				if (pPjtNull.trim().equals("���� ��ǥ�� �߰��ϼ���.")) {
					SinglePlanCreate sc = new SinglePlanCreate();
					sc.arrange(id, getX(), getY());
					// JTable �ʱ�ȭ !!
					DefaultTableModel Tmodel = (DefaultTableModel) tPjtTb.getModel();
					Tmodel.setRowCount(0);
					DefaultTableModel Pmodel = (DefaultTableModel) pPjtTb.getModel();
					Pmodel.setRowCount(0);
					DefaultTableModel todomodel = (DefaultTableModel) todoTb.getModel();
					todomodel.setRowCount(0);
					//
					saveThisPage();
					return;
				} else {
					//System.out.println("pPjtNull  :" +pPjtNull);
					int pPjtItem = Integer.parseInt(pPjtTableModel.getValueAt(clickedRow, 0).toString());
					SinglePlanUpdate sp = new SinglePlanUpdate(pPjtItem);
					sp.arrange(id, getX(), getY());
					// JTable �ʱ�ȭ !!
					DefaultTableModel Tmodel = (DefaultTableModel) tPjtTb.getModel();
					Tmodel.setRowCount(0);
					DefaultTableModel Pmodel = (DefaultTableModel) pPjtTb.getModel();
					Pmodel.setRowCount(0);
					DefaultTableModel todomodel = (DefaultTableModel) todoTb.getModel();
					todomodel.setRowCount(0);
					//
					saveThisPage();
					return;
				}
			}
		}

////////// �� ��ǥ ����Ŭ���� �� Ȩ���� �̵�
		if (obj == tPjtTb) {
			if (e.getClickCount() == 2) {
				int clickedRow = tPjtTb.getSelectedRow();
				int tPjtItem = Integer.parseInt(tPjtTableModel.getValueAt(clickedRow, 0).toString());
				String tPjtNull = tPjtTableModel.getValueAt(clickedRow, 2).toString();
				if (tPjtNull.trim().equals("�� ��ǥ�� �����ϰų� ������ �� ��ǥ�� �����ϼ���.")) {
					TeamPlanCreate tc = new TeamPlanCreate();
					tc.arrange(id, getX(), getY());
					// JTable �ʱ�ȭ !!
					DefaultTableModel Tmodel = (DefaultTableModel) tPjtTb.getModel();
					Tmodel.setRowCount(0);
					DefaultTableModel Pmodel = (DefaultTableModel) pPjtTb.getModel();
					Pmodel.setRowCount(0);
					DefaultTableModel todomodel = (DefaultTableModel) todoTb.getModel();
					todomodel.setRowCount(0);
					//
					saveThisPage();
					return;
				} else {
					TeamProjects tp = new TeamProjects(tPjtItem);
					// TeamProjects tp = new TeamProjects(pPjtItem);
					tp.arrange(id, getX(), getY());
					// JTable �ʱ�ȭ !!
					DefaultTableModel Tmodel = (DefaultTableModel) tPjtTb.getModel();
					Tmodel.setRowCount(0);
					DefaultTableModel Pmodel = (DefaultTableModel) pPjtTb.getModel();
					Pmodel.setRowCount(0);
					DefaultTableModel todomodel = (DefaultTableModel) todoTb.getModel();
					todomodel.setRowCount(0);
					//
					saveThisPage();
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		HomeProjects hp = new HomeProjects();
		hp.arrange("admin", 0, 0);
	}
}
