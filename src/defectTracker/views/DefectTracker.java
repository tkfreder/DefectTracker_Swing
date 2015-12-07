package defectTracker.views;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import defectTracker.database.DatabaseAccess;
import defectTracker.views.forms.DefectForm;
import defectTracker.views.forms.UserForm;
import defectTracker.views.tables.DefectTable;

public class DefectTracker extends JFrame {

	// CONSTANTS
	private static final long serialVersionUID = 1L;
	private static final String APP_NAME = "Defect Tracker";
	private static final String APP_ICON_IMAGE_PATH = "/defectTracker/resources/ladybug_16.png";
	private static final String DATABASE_RELATIVE_PATH = "./repository/";

	// UI COMPONENTS
	private JPanel mcontentPane;
	private DefectForm mDefectForm;
	private DefectTable mDefectTable;
	private UserForm mUserForm;
	private JTabbedPane tabPanel;

	// DATABASE
	DatabaseAccess db;
	private JMenuBar menuBar;
	private JMenu mnHelp;
	private JMenuItem mnAbout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (Throwable e) {

			e.printStackTrace();

		}
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					DefectTracker frame = new DefectTracker();
					frame.setVisible(true);

				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * BugTracker constructor
	 */
	public DefectTracker() {

		setTitle(APP_NAME);

		// instantiate database object
		db = new DatabaseAccess(DATABASE_RELATIVE_PATH);

		// initialize UI components
		initialize();

		// add action handlers
		addActionHandlers();
	}

	/**
	 * Initialize UI Components
	 */
	private void initialize() {

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				DefectTracker.class.getResource(APP_ICON_IMAGE_PATH)));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 438);

		menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setJMenuBar(menuBar);

		mnHelp = new JMenu("Help");
		mnHelp.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(mnHelp);

		mnAbout = new JMenuItem("About");

		mnHelp.add(mnAbout);
		mcontentPane = new JPanel();
		mcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mcontentPane);
		mcontentPane.setLayout(new BorderLayout(0, 0));

		tabPanel = new JTabbedPane(JTabbedPane.TOP);
		// tabPanel.addTab("New/Edit Defect", null, pnlNewDefect, null);

		mDefectForm = new DefectForm();
		tabPanel.addTab("New/Edit Defect", null, mDefectForm, null);

		mDefectTable = new DefectTable(tabPanel, mDefectForm);
		tabPanel.addTab("View Defects", null, mDefectTable, null);

		mUserForm = new UserForm();
		tabPanel.addTab("Users", null, mUserForm, null);

		mcontentPane.add(tabPanel);

	}

	/**
	 * Add Action Handlers
	 */
	private void addActionHandlers() {

		// TAB PANEL CHANGED
		tabPanel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				// if NEW DEFECT panel, clear form
				if (tabPanel.getSelectedIndex() == 0) {

					mDefectForm.resetForm();

				}
				// if VIEW DEFECT panel, refresh the defects table
				else if (tabPanel.getSelectedIndex() == 1) {

					mDefectTable.refreshDefectTable();
				}

				// if USER panel, populate UserList
				else if (tabPanel.getSelectedIndex() == 2) {

					mUserForm.refreshUserListView();
				}
			}
		});

		mnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				About about = new About();
				about.setModal(true);
				about.setVisible(true);

			}
		});

	}

}
