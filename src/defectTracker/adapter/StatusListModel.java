package defectTracker.adapter;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class StatusListModel<Status> extends AbstractListModel implements
		ComboBoxModel {

	List<Status> mList;
	Object selection = null;

	public StatusListModel(List<Status> list) {

		mList = list;
	}

	public Status getElementAt(int index) {
		return mList.get(index);
	}

	public int getSize() {
		return mList.size();
	}

	public void setSelectedItem(Object anItem) {
		selection = (Status) anItem; // to select and register an
	} // item from the pull-down list

	// Methods implemented from the interface ComboBoxModel
	public Object getSelectedItem() {
		return selection; // to add the selection to the combo box
	}

}
