///////QnA 답변페이지-회원용///////ㅇㅋ

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
		qnaTitleL = new JLabel("제목");
		qnaContentL = new JLabel("내용");
		qnaAnswerL = new JLabel("답변");
		qnaMemL = new JLabel();
		qnaTitleTf = new JTextField("팀 목표 만들기 질문");
		qnaContentTa = new JTextArea("팀 목표 만들기가 잘 안됩니다");
		qnaAnswerTa = new JTextArea("답변 없음");
		qnaContentSc = new JScrollPane(qnaContentTa);
		pnaAnswerSc = new JScrollPane(qnaAnswerTa);
	}

	@Override
	public void arrange(String id, int x, int y) {
		
		/// 문의자
		qnaMemL.setBounds(100, 80, 800, 40);
		qnaMemL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaMemL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		qnaMemL.setOpaque(true);
		qnaMemL.setBackground(new Color(211, 221, 179));
		allP.add(qnaMemL);

		//// 질문 제목 타이틀
		qnaTitleL.setBounds(10, 160, 90, 60);
		qnaTitleL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaTitleL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		allP.add(qnaTitleL);

		//// 질문 타이틀
		qnaContentL.setBounds(10, 210, 90, 60);
		qnaContentL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaContentL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		allP.add(qnaContentL);

		//// 답변타이틀
		qnaAnswerL.setBounds(10, 440, 90, 60);
		qnaAnswerL.setHorizontalAlignment(SwingConstants.CENTER);
		qnaAnswerL.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		allP.add(qnaAnswerL);

		/// 질문 제목 내용
		qnaTitleTf.setBounds(100, 160, 800, 60);
		allP.add(qnaTitleTf);

		/// 질문 내용
		qnaContentSc.setBounds(100, 230, 800, 200);
		allP.add(qnaContentSc);

		// 답변 내용
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

		
		
		//////////// DB 연동 ////////
		qnaMemL.setText("\"" + id + "\" 님의 질문입니다.");
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