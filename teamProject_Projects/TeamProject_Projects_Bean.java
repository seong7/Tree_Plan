package teamProject_Projects;

public class TeamProject_Projects_Bean {
	// private int idx;
	private String pjtName;
	private String creator_id; // 생성자 ID
	private int team;
	private String photo;
	private String startDate;
	private String endDate;
	private int numMax; // 제한 인원
	private int numOfPpl; // 참여 인원
	private String password; // 비밀방 비밀번호
	//private String hashtag;
	private int completeRate; // 팀 프로젝트 오늘 달성한 회원 비율

	// project member 명단에 추가하기 위한 필드
	private int pjtIndex;
	private String memId;
	private String joinDate;

	// detail plan 에 추가하기 위한 필드
		// private int pjtIndex; (위와 중복)
		// private String memId; (위와 중복)
	private String days;
	private String detailPlan;
	private int detailOnOff;

	
	// complete table 에 추가하기 위한 필드
		//	private int pjtIndex;	 (위와 중복)
		// private String memId; (위와 중복)
		// private String days;
		// private String detailPlan;
	private String completeTime;
	private String completion; // "완료" , "미완료"

	
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
