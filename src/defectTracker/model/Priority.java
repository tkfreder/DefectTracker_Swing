package defectTracker.model;

import java.util.List;

public class Priority {
	private int priorityId;
	private String name;

	public Priority(int priorityId, String name) {
		this.setPriorityId(priorityId);
		this.setName(name);
	}

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name.trim();
	}

	public int getPriorityIndex(List<Priority> priorityList) {

		for (int i = 0; i < priorityList.size(); i++) {

			if (priorityList.get(i).getPriorityId() == getPriorityId())
				return i;
		}

		return -1;
	}

}
