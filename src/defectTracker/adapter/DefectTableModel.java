package defectTracker.adapter;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import defectTracker.model.Defect;

public class DefectTableModel extends AbstractTableModel {

	/**
	 * 
	 */

	// CONSTANTS
	private static final long serialVersionUID = 1L;
	private static final String BLUE_LINK_FORMAT_PRE = "<html><u><font color=blue>";
	private static final String BLUE_LINK_FORMAT_POST = "</font></u></html>";

	// CACHE
	private List<Defect> mDefectList;
	private List<String> mColumnList;

	/**
	 * DefectTableModel constructor
	 */
	public DefectTableModel(List<Defect> defectList, List<String> columnList) {
		mDefectList = defectList;
		mColumnList = columnList;
	}

	@Override
	public int getRowCount() {
		return mDefectList.size();
	}

	@Override
	public int getColumnCount() {
		return mColumnList.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		Class type = Object.class;

		switch (columnIndex) {
		case 0:
		case 1:
		case 2:
			type = String.class;
			break;
		}
		return type;
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {

		Defect defect = mDefectList.get(rowIndex);
		String value = "";

		switch (columnIndex) {

		case 0:
			value = BLUE_LINK_FORMAT_PRE + String.valueOf(defect.getId())
					+ BLUE_LINK_FORMAT_POST;
			break;
		case 1:
			value = defect.getSummary();
			break;
		case 2:
			value = defect.getStatus().getStatusName();
			break;
		case 3:
			value = defect.getPriority().getName();
			break;
		case 4:
			value = defect.getAssignee().getFullName();
			break;
		}

		return value.trim();
	}

	@Override
	public String getColumnName(int column) {

		String name = "";
		return BLUE_LINK_FORMAT_PRE + mColumnList.get(column)
				+ BLUE_LINK_FORMAT_POST;

	}
}
