package defectTracker.model;

import java.util.List;

public class Status {

	private String statusId;
	private String statusName;

	public Status(String id, String name) {

		this.setStatusId(id);
		this.setStatusName(name);
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String toString() {

		return statusName.trim();
	}

	public int getStatusIndex(List<Status> statusList) {

		for (int i = 0; i < statusList.size(); i++) {

			if (statusList.get(i).getStatusName().equals(this.getStatusName()))
				return i;

		}

		return -1;
	}

}
