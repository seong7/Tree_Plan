//��������/////QnA �亯������-�����ڿ�///////

package teamProject_Manager_QnA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import teamProject_Frame.TFrame1_null;

public class Qna_Answer_Admin extends TFrame1_null implements ActionListener {

	JPanel adminP;
	JButton /* memAdminB, qnaAdminB, */ saveB;
	JLabel qnaMemL, qnaTitleL, qnaContentL, qnaAnswerL;
	JTextField qnaTitleTf;
	JTextArea qnaContentTa, qnaAnswerTa;
	JScrollPane qnaContentSc, qnaAnswerSc;
	Manage_Qna_Mgr mgr;
	Manage_Qna_Bean bean;
	int qna_Idx;

	public Qna_Answer_Admin(int qna_Idx) {
		this.qna_Idx = qna_Idx;

		adminP = new JPanel();
		//memAdminB = new JButton("ȸ������");
		//memAdminB.addActionListener(this);
		//qnaAdminB = new JButton("ȸ������");
		//qnaAdminB.addActionListener(this);
		saveB = new JButton("�亯 ����");
		saveB.addActionListener(this);
		qnaTitleL = new JLabel("����");
		qnaContentL = new JLabel("����");
		qnaAnswerL = new JLabel("�亯");
		qnaMemL = new JLabel();
		qnaTitleTf = new JTextField("  �� ��ǥ ����� ����");
		qnaContentTa = new JTextArea("  �� ��ǥ ����Ⱑ �� �ȵ˴ϴ�");
		qnaAnswerTa = new JTextArea("  �亯 ����");
		qnaContentSc = new JScrollPane(qnaContentTa);
		qnaAnswerSc = new JScrollPane(qnaAnswerTa);
	}

	@Override
	public void arrange(String id, int x, int y) {

		/// ������
		qnaMemL.setBounds(100, 80, 800, 40);
		qnaMemL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaMemL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		qnaMemL.setOpaque(true);
		qnaMemL.setBackground(new Color(211, 221, 179));
		adminP.add(qnaMemL);

		//// ���� ���� Ÿ��Ʋ
		qnaTitleL.setBounds(10, 160, 90, 60);
		qnaTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaTitleL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		qnaTitleL.setForeground(Color.white);
		adminP.add(qnaTitleL);

		//// ���� Ÿ��Ʋ
		qnaContentL.setBounds(10, 210, 90, 60);
		qnaContentL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaContentL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		qnaContentL.setForeground(Color.white);
		adminP.add(qnaContentL);

		//// �亯Ÿ��Ʋ
		qnaAnswerL.setBounds(10, 440, 90, 60);
		qnaAnswerL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaAnswerL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		qnaAnswerL.setForeground(Color.white);
		adminP.add(qnaAnswerL);

		/// ���� ���� ����
		qnaTitleTf.setBounds(100, 160, 800, 60);
	
		adminP.add(qnaTitleTf);

		/// ���� ����
		qnaContentSc.setBounds(100, 230, 800, 200);
		adminP.add(qnaContentSc);

		// �亯 ����
		qnaAnswerSc.setBounds(100, 460, 800, 200);
		
		adminP.add(qnaAnswerSc);

		// ���� ��ư
		saveB.setBounds(710, 670, 160, 40);
		saveB.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		saveB.setBackground(new Color(211, 221, 179));
		adminP.add(saveB);

		//// ������ ��ü�г�
		//adminP.add(memAdminB);
		//memAdminB.setBounds(650, 30, 150, 50);
		//adminP.add(qnaAdminB);
		//qnaAdminB.setBounds(800, 30, 150, 50);
		adminP.setBackground(Color.white);
		adminP.setLayout(null);
		adminP.setBounds(0, 0, 1000, 800);
		add(adminP);

		qnaTitleTf.setEditable(false);
		qnaContentTa.setEditable(false);

		MainP.add(adminP);
		adminP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);

		qnaTitleTf.setFont(new Font("������������� ExtraBold", Font.PLAIN, 14));
		qnaContentTa.setFont(new Font("������������� ExtraBold", Font.PLAIN, 14));
		qnaAnswerTa.setFont(new Font("������������� ExtraBold", Font.PLAIN, 14));

		////////////DB ���� ////////
		mgr = new Manage_Qna_Mgr();
		
		bean = mgr.getQnaAnswer(qna_Idx);
		qnaMemL.setText("\"" + bean.getId() + "\" ���� �����Դϴ�.");
		qnaTitleTf.setText(bean.getTitle());
		qnaContentTa.setText(bean.getQuestion());
		qnaAnswerTa.setText(bean.getAnswer());
		
		setLocation(x, y);
		setVisible(true);
		validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object obj = e.getSource();
//		if (e.getActionCommand().equals("ȸ������")) {
//			new MemAdmin();
//			saveThisPage();
//		}

//		if (e.getActionCommand().equals("ȸ������")) {
//			QnaAdmin qa = new QnaAdmin();
//			qa.addToRowDataQnaAdmin("", "", "", "", "");
//			qa.arrange(id, getX(), getY());
//			saveThisPage();
//		}
		if (e.getActionCommand().equals("�亯 ����")) {
			if(qnaAnswerTa.getText().trim().length() ==0 || qnaAnswerTa.getText().trim().equals("�亯 ����")) {
				JOptionPane.showConfirmDialog(null, "�亯�� �Է��ϼ���.", "Q&A_�����ڸ��",
						JOptionPane.PLAIN_MESSAGE);
				return;
			}
				
			
			int result = JOptionPane.showConfirmDialog(null, "�ش� �������� �亯�� �����Ͻðڽ��ϱ�?", "Q&A_�����ڸ��",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == 0) {
				try {
					Calendar now = Calendar.getInstance();
					String year = Integer.toString(now.get(Calendar.YEAR));
					String month = Integer.toString(now.get(Calendar.MONTH)+1);
					String day = Integer.toString(now.get(Calendar.DAY_OF_MONTH));

					Manage_Qna_Bean bean = new Manage_Qna_Bean();
					bean.setIdx(qna_Idx);
					bean.setAnswer(qnaAnswerTa.getText().trim());
					bean.setaDate(year + "�� " + month + "�� " + day + "��");
					boolean flag = mgr.updateQna(bean);
					if(flag)
						JOptionPane.showInternalMessageDialog(null, "�亯�� �����Ͽ����ϴ�.", "Tree Plan", JOptionPane.PLAIN_MESSAGE);
					new Qna_Admin().arrange(id, getX(), getY());
					dispose();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
				

			}
			if (result == 1) {

			}
		}
	}

	public static void main(String[] args) {
		new Qna_Answer_Admin(1).arrange("", 0, 0);
	}
}