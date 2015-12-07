package defectTracker.views.tables;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import defectTracker.adapter.DefectTableModel;
import defectTracker.database.DatabaseAccess;
import defectTracker.model.Defect;
import defectTracker.utility.LinkRenderer;
import defectTracker.views.forms.DefectForm;

public class DefectTable extends JPanel {

	private static final long serialVersionUID = 1L;

	// CONSTANTS
	private static final String DEFECT_TABLE_COL_DEFECT_ID = "Defect #";
	private static final String DEFECT_TABLE_COL_TITLE = "Title";
	private static final String DEFECT_TABLE_COL_STATUS = "Status";
	private static final String DEFECT_TABLE_COL_PRIORITY = "Priority";
	private static final String DEFECT_TABLE_COL_ASSIGNEE = "Assignee";
	private static final String DATABASE_RELATIVE_PATH = "./repository/";

	// COMPONENTS
	private JTable tblDefects;
	private JTabbedPane mTabbedPane;
	private DefectForm mDefectForm;

	// DROPDOWN CACHE
	private List<String> columnList;
	private boolean isAscending = false;

	// DATABASE
	DatabaseAccess db;

	/**
	 * DefectTable constructor
	 */
	public DefectTable(JTabbedPane tabbedPane, DefectForm defectForm) {

		// injecting parent components
		mTabbedPane = tabbedPane;
		mDefectForm = defectForm;

		// initialize UI components
		initialize();

		// instantiate database object
		db = new DatabaseAccess(DATABASE_RELATIVE_PATH);

		// set column headers in Defect Table in View Defect Panel
		columnList = new ArrayList<>();
		columnList.add(DEFECT_TABLE_COL_DEFECT_ID);
		columnList.add(DEFECT_TABLE_COL_TITLE);
		columnList.add(DEFECT_TABLE_COL_STATUS);
		columnList.add(DEFECT_TABLE_COL_PRIORITY);
		columnList.add(DEFECT_TABLE_COL_ASSIGNEE);

		// add action handlers
		addActionHandlers();
	}

	private void initialize() {

		setPreferredSize(new Dimension(500, 10));
		setBackground(Color.WHITE);
		// tabPanel.addTab("View Defects", null, pnlViewDefects, null);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_pnlViewDefects = new GroupLayout(this);
		gl_pnlViewDefects.setHorizontalGroup(gl_pnlViewDefects
				.createParallelGroup(Alignment.LEADING).addGroup(
						gl_pnlViewDefects
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 574,
										Short.MAX_VALUE).addGap(25)));
		gl_pnlViewDefects.setVerticalGroup(gl_pnlViewDefects
				.createParallelGroup(Alignment.TRAILING).addGroup(
						gl_pnlViewDefects
								.createSequentialGroup()
								.addGap(32)
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 318,
										Short.MAX_VALUE).addContainerGap()));

		tblDefects = new JTable();
		tblDefects.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		scrollPane.setViewportView(tblDefects);

		// center column content
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tblDefects.setDefaultRenderer(String.class, centerRenderer);

		// center column headers
		JTableHeader header = tblDefects.getTableHeader();
		TableCellRenderer renderer = header.getDefaultRenderer();
		JLabel label = (JLabel) renderer;
		label.setHorizontalAlignment(JLabel.CENTER);

		setLayout(gl_pnlViewDefects);
	}

	private void addActionHandlers() {

		// DefectId column CLICK
		tblDefects.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {

				// check if this is DefectId (first) column
				JTable target = (JTable) e.getSource();
				int column = target.getSelectedColumn();
				int row = target.getSelectedRow();

				String cellString = (String) target.getValueAt(row, column);

				if (column == 0) {

					// switch to New/Edit Defect pane
					mTabbedPane.setSelectedIndex(0);

					// get connection to database
					db.getConnection();

					// parse for number, ie, the string will be html formatted
					// <html><u>25</u></html>, only want '25'
					Pattern p = Pattern.compile("[0-9]+");
					Matcher m = p.matcher(cellString);
					int n = 0;

					while (m.find()) {
						n = Integer.parseInt(m.group());
					}

					Defect defect = db.getDefect(n);

					if (defect != null) {

						// populate form
						mDefectForm.populateForm(defect);

					}

					// close database
					db.closeConnection();
				}

			}

		});

		// TABLE COLUMN HEADER CLICK
		tblDefects.getTableHeader().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int col = tblDefects.columnAtPoint(e.getPoint());

				// get connection to database
				db.getConnection();

				List<Defect> defectList = db.getDefects(col, isAscending);
				isAscending = !isAscending;

				DefectTableModel defectModel = new DefectTableModel(defectList,
						columnList);
				tblDefects.setModel(defectModel);

				// close database
				db.closeConnection();
			}

			public void mouseEntered(MouseEvent arg0) {

			}

			public void mouseExited(MouseEvent arg0) {

			}

			public void mousePressed(MouseEvent arg0) {

			}

			public void mouseReleased(MouseEvent arg0) {

			}

		});

	}

	/**
	 * helper methods
	 */

	public void refreshDefectTable() {

		// get connection to database
		db.getConnection();

		List<Defect> defectList = db.getDefects(0, isAscending);

		DefectTableModel defectModel = new DefectTableModel(defectList,
				columnList);
		tblDefects.setModel(defectModel);

		// set renderer for first column
		LinkRenderer linkRenderer = new LinkRenderer();
		TableColumnModel model = tblDefects.getColumnModel();
		TableColumn column = model.getColumn(0);
		column.setCellRenderer(linkRenderer);

		// close database
		db.closeConnection();

	}

}
