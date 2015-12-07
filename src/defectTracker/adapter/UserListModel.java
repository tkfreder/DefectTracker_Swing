package defectTracker.adapter;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class UserListModel<User> extends AbstractListModel implements
		ComboBoxModel {

	private List<User> mUserList;
	User selection = null;

	public UserListModel(List<User> userList) {

		mUserList = userList;
	}

	public Object getElementAt(int arg0) {

		return mUserList.get(arg0);
	}

	public int getSize() {

		return mUserList.size();
	}

	public void setSelectedItem(Object anItem) {
		selection = (User) anItem;
		// to select and register an
	} // item from the pull-down list

	// Methods implemented from the interface ComboBoxModel
	public User getSelectedItem() {
		return selection; // to add the selection to the combo box
	}

}
