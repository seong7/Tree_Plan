///////QnA �亯������-ȸ����///////����

package teamProject_Manager_QnA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import teamProject_Frame.TFrame1_null;

public class Qna_Answer extends TFrame1_null {

	JPanel allP;
	JLabel qnaMemL, qnaTitleL, qnaContentL, qnaAnswerL;
	JTextField qnaTitleTf;
	JTextArea qnaContentTa, qnaAnswerTa;
	JScrollPane qnaContentSc, pnaAnswerSc;
	Manage_Qna_Mgr mgr;
	Manage_Qna_Bean bean;
	int qna_Idx;

	public Qna_Answer(int qna_Idx) {
		this.qna_Idx = qna_Idx;
		allP = new JPanel();
		qnaTitleL = new JLabel("����");
		qnaContentL = new JLabel("����");
		qnaAnswerL = new JLabel("�亯");
		qnaMemL = new JLabel();
		qnaTitleTf = new JTextField("�� ��ǥ ����� ����");
		qnaContentTa = new JTextArea("�� ��ǥ ����Ⱑ �� �ȵ˴ϴ�");
		qnaAnswerTa = new JTextArea("�亯 ����");
		qnaContentSc = new JScrollPane(qnaContentTa);
		pnaAnswerSc = new JScrollPane(qnaAnswerTa);
	}

	@Override
	public void arrange(String id, int x, int y) {
		
		/// ������
		qnaMemL.setBounds(100, 80, 800, 40);
		qnaMemL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaMemL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		qnaMemL.setOpaque(true);
		qnaMemL.setBackground(new Color(211, 221, 179));
		allP.add(qnaMemL);

		//// ���� ���� Ÿ��Ʋ
		qnaTitleL.setBounds(10, 160, 90, 60);
		qnaTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaTitleL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		allP.add(qnaTitleL);

		//// ���� Ÿ��Ʋ
		qnaContentL.setBounds(10, 210, 90, 60);
		qnaContentL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaContentL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		allP.add(qnaContentL);

		//// �亯Ÿ��Ʋ
		qnaAnswerL.setBounds(10, 440, 90, 60);
		qnaAnswerL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaAnswerL.setFont(new Font("������������� ExtraBold", Font.PLAIN, 16));
		allP.add(qnaAnswerL);

		/// ���� ���� ����
		qnaTitleTf.setBounds(100, 160, 800, 60);
		allP.add(qnaTitleTf);

		/// ���� ����
		qnaContentSc.setBounds(100, 230, 800, 200);
		allP.add(qnaContentSc);

		// �亯 ����
		pnaAnswerSc.setBackground(Color.white);
		pnaAnswerSc.setBounds(100, 460, 800, 200);
		allP.add(pnaAnswerSc);

		allP.setLayout(null);
		allP.setBackground(Color.white);
		allP.setBounds(0, 0, 1000, 800);
		add(allP);

		qnaTitleTf.setEditable(false);
		qnaContentTa.setEditable(false);
		qnaAnswerTa.setEditable(false);

		MainP.add(allP);
		allP.setBounds(0, 0, this.getWidth(), this.getHeight() - 36);

		
		
		//////////// DB ���� ////////
		qnaMemL.setText("\"" + id + "\" ���� �����Դϴ�.");
		mgr = new Manage_Qna_Mgr();
		
		bean = mgr.getQnaAnswer(qna_Idx);
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
	}

	public static void main(String[] args) {
		new Qna_Answer(1).arrange("", 0, 0);
	}
}