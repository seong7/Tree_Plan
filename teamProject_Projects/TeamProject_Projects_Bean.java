package teamProject_Projects;

public class TeamProject_Projects_Bean {
	// private int idx;
	private String pjtName;
	private String creator_id; // ������ ID
	private int team;
	private String photo;
	private String startDate;
	private String endDate;
	private int numMax; // ���� �ο�
	private int numOfPpl; // ���� �ο�
	private String password; // ��й� ��й�ȣ
	//private String hashtag;
	private int completeRate; // �� ������Ʈ ���� �޼��� ȸ�� ����

	// project member ��ܿ� �߰��ϱ� ���� �ʵ�
	private int pjtIndex;
	private String memId;
	private String joinDate;

	// detail plan �� �߰��ϱ� ���� �ʵ�
		// private int pjtIndex; (���� �ߺ�)
		// private String memId; (���� �ߺ�)
	private String days;
	private String detailPlan;
	private int detailOnOff;

	
	// complete table �� �߰��ϱ� ���� �ʵ�
		//	private int pjtIndex;	 (���� �ߺ�)
		// private String memId; (���� �ߺ�)
		// private String days;
		// private String detailPlan;
	private String completeTime;
	private String completion; // "�Ϸ�" , "�̿Ϸ�"

	
/////// Getter , Setter	////////
	
	public String getPjtName() {
		return pjtName;
	}

	public int getDetailOnOff() {
		return detailOnOff;
	}

	public void setDetailOnOff(int detailOnOff) {
		this.detailOnOff = detailOnOff;
	}

	public void setPjtName(String pjtName) {
		this.pjtName = pjtName;
	}

	public String getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getNumMax() {
		return numMax;
	}

	public void setNumMax(int numMax) {
		this.numMax = numMax;
	}

	public int getNumOfPpl() {
		return numOfPpl;
	}

	public void setNumOfPpl(int numOfPpl) {
		this.numOfPpl = numOfPpl;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String getHashtag() {
//		return hashtag;
//	}

//	public void setHashtag(String hashtag) {
//		this.hashtag = hashtag;
//	}

	public int getPjtIndex() {
		return pjtIndex;
	}

	public void setPjtIndex(int pjtIndex) {
		this.pjtIndex = pjtIndex;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getDays() {
		return days;
	}
	

	public int getCompleteRate() {
		return completeRate;
	}

	public void setCompleteRate(int completeRate) {
		this.completeRate = completeRate;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getDetailPlan() {
		return detailPlan;
	}

	public void setDetailPlan(String detailPlan) {
		this.detailPlan = detailPlan;
	}

	public String getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public String getCompletion() {
		return completion;
	}

	public void setCompletion(String completion) {
		this.completion = completion;
	}
}
