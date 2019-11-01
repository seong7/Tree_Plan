package teamProject_Manager_QnA;

public class Manage_Qna_Bean {
	private int idx;
	private String id;
	private String title;
	private String question;
	private String qDate;
	private String answer;
	private String aDate;
	
	
	// id 메소드
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	// pwd 메소드
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	// name 메소드
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	// photo 메소드
	public String getQDate() {
		return qDate;
	}
	public void setQDate(String qDate) {
		this.qDate = qDate;
	}
	
	// address 메소드
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	// email 메소드
	public String getaDate() {
		return aDate;
	}
	public void setaDate(String aDate) {
		this.aDate = aDate;
	}

}
