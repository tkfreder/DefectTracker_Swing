package defectTracker.views.forms;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

import defectTracker.adapter.PriorityListModel;
import defectTracker.adapter.StatusListModel;
import defectTracker.adapter.UserListModel;
import defectTracker.database.DatabaseAccess;
import defectTracker.model.Defect;
import defectTracker.model.Priority;
import defectTracker.model.Status;
import defectTracker.model.User;

public class DefectForm extends JPanel {

	// CONSTANTS
	private static final long serialVersionUID = 1L;
	private static final String DATABASE_RELATIVE_PATH = "./repository/";

	// COMPONENTS
	private JTextField txtTitle;
	private JComboBox<String> cbStatus;
	private JComboBox<Priority> cbPriority;
	private JComboBox<User> cbAssignee;
	private JButton btnSave;
	private JTextArea txtDescription;
	private JLabel lblDefectId;
	private JLabel lblDefectNumber;

	// DROPDOWN CACHE
	private List<Status> statusList;
	private List<Priority> priorityList;
	private List<User> userList;

	// DATABASE
	DatabaseAccess db;

	/**
	 * DefectForm constructor
	 */
	public DefectForm() {

		// initialize UI components
		initialize();

		// instantiate database object
		db = new DatabaseAccess(DATABASE_RELATIVE_PATH);

		// populate dropdowns
		setDropDowns();

		// add action handlers
		addActionHandlers();

	}

	/**
	 * Initialize UI Components
	 */
	private void initialize() {

		setAlignmentY(Component.BOTTOM_ALIGNMENT);
		setAlignmentX(Component.RIGHT_ALIGNMENT);
		setSize(new Dimension(200, 300));
		setPreferredSize(new Dimension(200, 10));
		setBorder(null);
		setBackground(Color.WHITE);

		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtTitle = new JTextField();
		txtTitle.setFont(new Font("Arial", Font.PLAIN, 12));
		txtTitle.setColumns(10);

		JLabel lblNewLabel = new JLabel("Status:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

		cbStatus = new JComboBox<>();
		cbStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		cbStatus.setForeground(Color.BLACK);
		cbStatus.setBackground(Color.WHITE);

		JLabel lblPriority = new JLabel("Priority:");
		lblPriority.setFont(new Font("Tahoma", Font.PLAIN, 13));

		cbPriority = new JComboBox<>();
		cbPriority.setBackground(Color.WHITE);
		cbPriority.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblAssignee = new JLabel("Assignee:");
		lblAssignee.setFont(new Font("Tahoma", Font.PLAIN, 13));

		cbAssignee = new JComboBox<>();
		cbAssignee.setBackground(Color.WHITE);
		cbAssignee.setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));

		txtDescription = new JTextArea();
		txtDescription.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
				null));

		btnSave = new JButton("SAVE");
		btnSave.setEnabled(false);

		lblDefectNumber = new JLabel("Defect ID:");
		lblDefectNumber.setVisible(false);
		lblDefectNumber.setFont(new Font("Tahoma", Font.BOLD, 13));

		lblDefectId = new JLabel("");

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));

		GroupLayout gl_pnlNewDefect = new GroupLayout(this);
		gl_pnlNewDefect
				.setHorizontalGroup(gl_pnlNewDefect
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_pnlNewDefect
										.createSequentialGroup()
										.addGroup(
												gl_pnlNewDefect
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_pnlNewDefect
																		.createSequentialGroup()
																		.addGap(28)
																		.addGroup(
																				gl_pnlNewDefect
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblAssignee)
																						.addComponent(
																								lblPriority)
																						.addComponent(
																								lblNewLabel)
																						.addComponent(
																								lblTitle)
																						.addComponent(
																								lblDescription)
																						.addComponent(
																								lblDefectNumber))
																		.addGap(10)
																		.addGroup(
																				gl_pnlNewDefect
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblNewLabel_1)
																						.addComponent(
																								lblDefectId)
																						.addComponent(
																								txtDescription,
																								GroupLayout.PREFERRED_SIZE,
																								432,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_pnlNewDefect
																										.createParallelGroup(
																												Alignment.TRAILING,
																												false)
																										.addComponent(
																												cbStatus,
																												Alignment.LEADING,
																												0,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												cbPriority,
																												0,
																												163,
																												Short.MAX_VALUE)
																										.addComponent(
																												cbAssignee,
																												0,
																												GroupLayout.DEFAULT_SIZE,
																												Short.MAX_VALUE)
																										.addComponent(
																												txtTitle,
																												Alignment.LEADING))))
														.addGroup(
																gl_pnlNewDefect
																		.createSequentialGroup()
																		.addGap(264)
																		.addComponent(
																				btnSave)))
										.addContainerGap(49, Short.MAX_VALUE)));
		gl_pnlNewDefect
				.setVerticalGroup(gl_pnlNewDefect
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_pnlNewDefect
										.createSequentialGroup()
										.addGap(13)
										.addGroup(
												gl_pnlNewDefect
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblDefectNumber)
														.addComponent(
																lblDefectId)
														.addComponent(
																lblNewLabel_1))
										.addGap(18)
										.addGroup(
												gl_pnlNewDefect
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblTitle)
														.addComponent(
																txtTitle,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_pnlNewDefect
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel)
														.addComponent(
																cbStatus,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_pnlNewDefect
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblPriority)
														.addComponent(
																cbPriority,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_pnlNewDefect
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																cbAssignee,
																GroupLayout.PREFERRED_SIZE,
																18,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblAssignee))
										.addGap(18)
										.addGroup(
												gl_pnlNewDefect
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_pnlNewDefect
																		.createSequentialGroup()
																		.addComponent(
																				lblDescription)
																		.addGap(100))
														.addGroup(
																gl_pnlNewDefect
																		.createSequentialGroup()
																		.addComponent(
																				txtDescription,
																				GroupLayout.PREFERRED_SIZE,
																				92,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)))
										.addComponent(btnSave).addGap(31)));
		setLayout(gl_pnlNewDefect);
	}

	/**
	 * Initialize UI Components
	 */
	private void setDropDowns() {

		ArrayList<String> strList;

		// get connection to database
		db.getConnection();

		// ********* STATUS DROPDOWN *************
		statusList = db.getStatuses();
		strList = new ArrayList<>();

		for (Status status : statusList) {

			strList.add(status.getStatusName().trim());
		}

		cbStatus.setModel(new StatusListModel<Status>(statusList));
		cbStatus.setSelectedIndex(-1);

		// ********* PRIORITY DROPDOWN *************
		priorityList = db.getPriorities();
		strList = new ArrayList<>();

		for (Priority priority : priorityList) {

			strList.add(priority.getName().trim());
		}

		cbPriority.setModel(new PriorityListModel<Priority>(priorityList));

		// ********* ASSIGNEE DROPDOWN *************
		userList = db.getUsers();
		strList = new ArrayList<>();

		for (User user : userList) {

			strList.add(user.getFirstName().trim() + " "
					+ user.getLastName().trim());
		}

		cbAssignee.setModel(new UserListModel<User>(userList));
		cbAssignee.setSelectedIndex(-1);

		// close database
		db.closeConnection();
	}

	private void addActionHandlers() {

		/************** NEW/EDIT DEFECT PANEL ************************/

		// Title
		txtTitle.addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {

			}

			public void keyTyped(KeyEvent e) {

				validateForm();
			}

		});

		// Status
		cbStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				validateForm();
			}
		});

		// Priority
		cbPriority.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				validateForm();
			}
		});

		// Assignee
		cbAssignee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				validateForm();
			}
		});

		// SAVE BUTTON
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// get connection to database
				db.getConnection();

				int rowsAffected = 0;
				String defectTitle = txtTitle.getText();

				// if there is a defectId, update record
				if (!lblDefectId.getText().equals("")) {

					rowsAffected = db.updateDefect(txtTitle.getText(),
							statusList.get(cbStatus.getSelectedIndex())
									.getStatusId(),
							priorityList.get(cbPriority.getSelectedIndex())
									.getPriorityId(),
							userList.get(cbAssignee.getSelectedIndex())
									.getUserId(), txtDescription.getText(),
							Integer.parseInt(lblDefectId.getText()));
				}

				// else this is NEW DEFECT, insert record
				else {

					rowsAffected = db.insertDefect(txtTitle.getText(),
							statusList.get(cbStatus.getSelectedIndex())
									.getStatusId(),
							priorityList.get(cbPriority.getSelectedIndex())
									.getPriorityId(),
							userList.get(cbAssignee.getSelectedIndex())
									.getUserId(), txtDescription.getText());

				}

				// close database
				db.closeConnection();

				if (rowsAffected != 0) {

					JOptionPane.showMessageDialog(null,
							"Save successful for \"" + defectTitle + "\"");
					resetForm();

				} else {

					JOptionPane.showMessageDialog(null,
							"An error occurred while saving. Try again.");
				}

			}
		});
	}

	public void populateForm(Defect defect) {

		lblDefectNumber.setVisible(true);
		lblDefectId.setText(String.valueOf(defect.getId()));
		txtTitle.setText(defect.getSummary());
		cbStatus.setSelectedIndex(defect.getStatus().getStatusIndex(statusList));
		cbPriority.setSelectedIndex(defect.getPriority().getPriorityIndex(
				priorityList));
		cbAssignee
				.setSelectedIndex(defect.getAssignee().getUserIndex(userList));
		txtDescription.setText(defect.getDescription());
	}

	public void resetForm() {

		lblDefectNumber.setVisible(false);
		lblDefectId.setText("");
		txtTitle.setText("");
		cbStatus.setSelectedIndex(-1);
		cbPriority.setSelectedIndex(-1);
		cbAssignee.setSelectedIndex(-1);
		txtDescription.setText("");
		btnSave.setEnabled(false);

		setDropDowns();
	}

	public void validateForm() {

		if (!txtTitle.getText().equals("") && cbStatus.getSelectedIndex() != -1
				&& cbPriority.getSelectedIndex() != -1
				&& cbAssignee.getSelectedIndex() != -1) {

			btnSave.setEnabled(true);

		}

	}

}
