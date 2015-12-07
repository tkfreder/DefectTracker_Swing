package defectTracker.model;

public class Defect implements Comparable<Defect> {

	private int mId;
	private Status mStatus;
	private Priority mPriority;
	private User mAssignee;
	private String mSummary;
	private String mDescription;

	public Defect(int id, Status status, Priority priority, User assigned,
			String summary, String description) {

		mId = id;
		mStatus = status;
		mPriority = priority;
		mAssignee = assigned;
		mSummary = summary;
		mDescription = description;
	}

	public int compareTo(Defect defect) {

		return mId - defect.getId();
	}

	/**** mId ***********/
	public int getId() {
		return mId;
	}

	public void setId(int id) {

		mId = id;
	}

	/**** mStatus ***********/
	public Status getStatus() {
		return mStatus;
	}

	public void setStatus(Status status) {
		mStatus = status;
	}

	/**** mPriority ***********/
	public Priority getPriority() {
		return mPriority;
	}

	public void setPriority(Priority priority) {
		mPriority = priority;
	}

	/**** mAssigned ***********/
	public User getAssignee() {
		return mAssignee;
	}

	public void setAssigneeId(User assignee) {
		mAssignee = assignee;
	}

	/**** mSummary ***********/
	public String getSummary() {
		return mSummary;
	}

	public void setSummary(String summary) {
		mSummary = summary;
	}

	/**** mDescription ***********/
	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		mDescription = description;
	}

}
