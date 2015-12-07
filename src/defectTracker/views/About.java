package defectTracker.views;

import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class About extends JDialog {

	// CONSTANTS
	private static final long serialVersionUID = 1L;
	private static final String APP_ICON_IMAGE_PATH = "/defectTracker/resources/ladybug_16.png";

	private JPanel mcontentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			About dialog = new About();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public About() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				About.class.getResource(APP_ICON_IMAGE_PATH)));

		setBounds(100, 100, 688, 287);
		mcontentPane = new JPanel();
		mcontentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mcontentPane);

		JTextArea txtrAuthorTinaFredericksrndate = new JTextArea();
		txtrAuthorTinaFredericksrndate
				.setText("Author: Tina Fredericks\r\nDate: 12-01-2015\r\nVersion: 1.0.1\r\n\r\nThis application is based on a class team project, a java web application. \r\nWith the use of WindowBuilder Eclipse plugin, the UI was converted to Java Swing.\r\nThe original Java web application can be found here:\r\nhttps://github.com/tkfreder/DefectTracker");
		GroupLayout gl_contentPane = new GroupLayout(mcontentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(txtrAuthorTinaFredericksrndate,
								GroupLayout.PREFERRED_SIZE, 440,
								Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(txtrAuthorTinaFredericksrndate,
								GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
						.addContainerGap()));
		mcontentPane.setLayout(gl_contentPane);
	}
}
