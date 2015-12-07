package defectTracker.views.forms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import defectTracker.adapter.UserListModel;
import defectTracker.database.DatabaseAccess;
import defectTracker.model.User;
import defectTracker.utility.StringUtils;

public class UserForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// CONSTANTS
	private static final String DATABASE_RELATIVE_PATH = "./repository/";

	// COMPONENTS
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JList<User> lstUsers;
	private JButton btnAdd;
	private JButton btnClear;
	private JLabel lblUserId;

	// CACHE
	private List<User> userList;

	// DATABASE
	DatabaseAccess db;

	/**
	 * UserForm constructor
	 */
	public UserForm() {

		// initialize UI components
		initialize();

		// instantiate database object
		db = new DatabaseAccess(DATABASE_RELATIVE_PATH);

		// add action handlers
		addActionHandlers();

	}

	private void initialize() {

		setPreferredSize(new Dimension(500, 10));
		setBackground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Add/Edit User",
				TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		panel.setBackground(Color.WHITE);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		GroupLayout gl_pnlUsers = new GroupLayout(this);
		gl_pnlUsers.setHorizontalGroup(gl_pnlUsers.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_pnlUsers
						.createSequentialGroup()
						.addGap(25)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE,
								251, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 30,
								Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 293,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));
		gl_pnlUsers
				.setVerticalGroup(gl_pnlUsers
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_pnlUsers
										.createSequentialGroup()
										.addGap(27)
										.addComponent(scrollPane_1,
												GroupLayout.PREFERRED_SIZE,
												307, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(27, Short.MAX_VALUE))
						.addGroup(
								Alignment.TRAILING,
								gl_pnlUsers
										.createSequentialGroup()
										.addContainerGap(40, Short.MAX_VALUE)
										.addComponent(panel,
												GroupLayout.PREFERRED_SIZE,
												276, GroupLayout.PREFERRED_SIZE)
										.addGap(45)));

		lstUsers = new JList();
		lstUsers.setBorder(new TitledBorder(null, "Users",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lstUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(lstUsers);

		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtEmail = new JTextField();
		txtEmail.setColumns(10);

		btnAdd = new JButton("Update");
		btnAdd.setEnabled(false);

		btnClear = new JButton("Clear");

		lblUserId = new JLabel("");
		lblUserId.setVisible(false);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(lblFirstName)
												.addComponent(lblLastName)
												.addComponent(lblEmail))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(lblUserId)
												.addComponent(
														txtEmail,
														GroupLayout.DEFAULT_SIZE,
														192, Short.MAX_VALUE)
												.addComponent(
														txtLastName,
														GroupLayout.DEFAULT_SIZE,
														192, Short.MAX_VALUE)
												.addComponent(
														txtFirstName,
														GroupLayout.DEFAULT_SIZE,
														192, Short.MAX_VALUE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		btnClear,
																		GroupLayout.PREFERRED_SIZE,
																		74,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(
																		btnAdd)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap(23, Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblFirstName)
												.addComponent(
														txtFirstName,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(22)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(lblLastName)
												.addComponent(
														txtLastName,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(
														txtEmail,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEmail))
								.addGap(20)
								.addComponent(lblUserId)
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnClear)
												.addComponent(btnAdd))
								.addGap(57)));
		panel.setLayout(gl_panel);
		setLayout(gl_pnlUsers);

	}

	private void addActionHandlers() {

		// user list view
		lstUsers.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {

				if (!lstUsers.getSelectedValuesList().isEmpty()) {
					User user = (User) lstUsers.getSelectedValue();
					populateUserForm(user);
					btnAdd.setText("Update");
					btnAdd.setEnabled(true);
					btnClear.setEnabled(true);

				} else
					clearUserForm(); // validateUserForm();

			}
		});

		// new user form

		txtFirstName.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent arg0) {

			}

			public void keyReleased(KeyEvent arg0) {

			}

			public void keyTyped(KeyEvent e) {

				validateUserForm();
			}

		});

		txtLastName.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent arg0) {

			}

			public void keyReleased(KeyEvent arg0) {

			}

			public void keyTyped(KeyEvent e) {

				validateUserForm();
			}

		});

		txtEmail.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent arg0) {

			}

			public void keyReleased(KeyEvent arg0) {

			}

			public void keyTyped(KeyEvent e) {

				validateUserForm();
			}

		});

		// clear button
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				clearUserForm();
				btnClear.setEnabled(false);
			}
		});

		// add button
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int rowsAffected = 0;
				String userName = txtFirstName.getText() + " "
						+ txtLastName.getText();

				// get connection to database
				db.getConnection();

				// update user
				if (btnAdd.getText().equals("Update")) {

					rowsAffected = db.updateUser(txtFirstName.getText(),
							txtLastName.getText(), txtEmail.getText(),
							Integer.parseInt(lblUserId.getText()));
				}
				// insert user
				else
					rowsAffected = db.insertUser(txtFirstName.getText(),
							txtLastName.getText(), txtEmail.getText());

				if (rowsAffected > 0) {

					// refresh user listview
					lstUsers.setModel(new UserListModel(db.getUsers()));
					JOptionPane.showMessageDialog(null, "Save successful for: "
							+ userName);

				}

				// close database
				db.closeConnection();

				// clear the form fields
				clearUserForm();

			}
		});
	}

	/**
	 * helper methods
	 */

	private void populateUserForm(User user) {

		lblUserId.setText(String.valueOf(user.getUserId()));
		txtFirstName.setText(user.getFirstName());
		txtLastName.setText(user.getLastName());
		txtEmail.setText(user.getEmail());
	}

	private void clearUserForm() {

		lblUserId.setText("");
		txtFirstName.setText("");
		txtLastName.setText("");
		txtEmail.setText("");
		btnAdd.setText("Add");
		btnAdd.setEnabled(false);
		lstUsers.clearSelection();
		btnClear.setEnabled(false);
	}

	private void validateUserForm() {

		if (!txtFirstName.getText().equals("")
				&& !txtLastName.getText().equals("")
				&& !txtEmail.getText().equals("")
				&& StringUtils.isValidEmailAddress(txtEmail.getText())) {

			btnAdd.setEnabled(true);
			btnClear.setEnabled(true);

			if (lblUserId.getText().equals(""))
				btnAdd.setText("Add");
			else
				btnAdd.setText("Update");
		}

		else
			btnAdd.setEnabled(false);

		// check if form is blank
		if (txtFirstName.getText().equals("")
				&& txtLastName.getText().equals("")
				&& txtEmail.getText().equals("")) {

			btnAdd.setText("Add");
			btnAdd.setEnabled(false);
		}

		// update mode
		if (!lstUsers.getSelectedValuesList().isEmpty())
			btnClear.setEnabled(false);

	}

	public void refreshUserListView() {

		// get connection to database
		db.getConnection();

		userList = db.getUsers();
		lstUsers.setModel(new UserListModel(userList));

		// close database
		db.closeConnection();
	}

}
