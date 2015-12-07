package defectTracker.adapter;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class PriorityListModel<Priority> extends AbstractListModel implements
		ComboBoxModel {

	List<Priority> mList;
	Priority selection = null;

	public PriorityListModel(List<Priority> list) {

		mList = list;
	}

	public Priority getElementAt(int index) {
		return mList.get(index);
	}

	public int getSize() {
		return mList.size();
	}

	public void setSelectedItem(Object anItem) {
		selection = (Priority) anItem;
		// to select and register an
	} // item from the pull-down list

	// Methods implemented from the interface ComboBoxModel
	public Priority getSelectedItem() {
		return selection; // to add the selection to the combo box
	}

}
