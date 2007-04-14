package edu.stanford.hci.r3.pen.handwriting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import edu.stanford.hci.r3.PaperToolkit;
import edu.stanford.hci.r3.pen.ink.InkPanel;
import edu.stanford.hci.r3.util.DebugUtils;
import edu.stanford.hci.r3.util.WindowUtils;

/**
 * <p>
 * This assumes capture on a single patterned page (it does not utilize the PaperToolkit's Tiling).
 * </p>
 * <p>
 * <span class="BSDLicense"> This software is distributed under the <a
 * href="http://hci.stanford.edu/research/copyright.txt">BSD License</a>.</span>
 * </p>
 * 
 * @author <a href="http://graphics.stanford.edu/~ronyeh">Ron B Yeh</a> (ronyeh(AT)cs.stanford.edu)
 */
public class HandwritingCaptureDebugger extends JFrame {

	private static final String NO_TEXT = "No Text Yet";

	public static void main(String[] args) {
		new HandwritingCaptureDebugger();
	}

	private CaptureApplication app;

	private JPanel buttonPanel;

	private JButton calibrateButton;

	private JButton clearButton;

	/**
	 * JPanel that renders Ink.
	 */
	private InkPanel mainPanel;

	private JButton saveButton;

	private JPanel statusBarPanel;

	private JLabel statusMessageLabel;

	private JTextArea textOutput;

	public HandwritingCaptureDebugger() {
		PaperToolkit.initializeLookAndFeel();
		initGUI();
		startApp();
	}

	/**
	 * @return
	 */
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.add(getCalibrateButton());
			buttonPanel.add(getSaveButton());
			buttonPanel.add(getClearButton());
		}
		return buttonPanel;
	}

	/**
	 * @return
	 */
	private JButton getCalibrateButton() {
		if (calibrateButton == null) {
			calibrateButton = new JButton();
			calibrateButton.setText("        Calibrate        ");
			calibrateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					doClear();
					DebugUtils.println("Calibrate: Choose Top Left and Bottom Right Corners...");
					app.addCalibrationHandler();
				}
			});
		}
		return calibrateButton;
	}

	/**
	 * Clears the ink in the GUI, and also from the model.
	 * 
	 * @return
	 */
	private Component getClearButton() {
		if (clearButton == null) {
			clearButton = new JButton("Clear");
			clearButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DebugUtils.println("Clear!");
					doClear();
				}

			});
		}
		return clearButton;
	}

	private void doClear() {
		getInkPanel().clear();
		app.clearInk();
		getTextOutputArea().setText(NO_TEXT);
	}

	/**
	 * @return
	 */
	InkPanel getInkPanel() {
		if (mainPanel == null) {
			mainPanel = new InkPanel();
			mainPanel.setPreferredSize(new Dimension(800, 600));
			mainPanel.setBackground(Color.WHITE);
		}
		return mainPanel;
	}

	/**
	 * The introductory message at the top.
	 * 
	 * @return
	 */
	private JLabel getMessageLabel() {
		if (statusMessageLabel == null) {
			statusMessageLabel = new JLabel();
			statusMessageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			statusMessageLabel.setText("After clicking Calibrate, tap once on the upper left "
					+ "corner of your page, and then once on the lower right corner of your page.");
		}
		return statusMessageLabel;
	}

	/**
	 * @return
	 */
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton("Save");
			saveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DebugUtils.println("Save");
					app.saveInkToDisk();
				}
			});
		}
		return saveButton;
	}

	/**
	 * The info bar at the bottom...
	 * 
	 * @return
	 */
	private JTextArea getTextOutputArea() {
		if (textOutput == null) {
			textOutput = new JTextArea(1, 50);
			textOutput.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
			textOutput.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
			textOutput.setText(NO_TEXT);
			textOutput.setForeground(Color.WHITE);
			textOutput.setEditable(false);
		}
		return textOutput;
	}

	/**
	 * @return
	 */
	private JPanel getToolBarPanel() {
		if (statusBarPanel == null) {
			statusBarPanel = new JPanel();
			BorderLayout statusBarPanelLayout = new BorderLayout();
			statusBarPanel.setLayout(statusBarPanelLayout);
			statusBarPanel.add(getMessageLabel(), BorderLayout.CENTER);
			statusBarPanel.add(getButtonPanel(), BorderLayout.EAST);
		}
		return statusBarPanel;
	}

	/**
	 * 
	 */
	private void initGUI() {
		setTitle("Handwriting Recognition Debugger");
		getContentPane().add(getToolBarPanel(), BorderLayout.NORTH);
		getContentPane().add(getInkPanel(), BorderLayout.CENTER);
		getContentPane().add(getTextOutputArea(), BorderLayout.SOUTH);
		pack();
		setLocation(WindowUtils.getWindowOrigin(this, WindowUtils.DESKTOP_CENTER));
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @param text
	 */
	public void setInfoText(String text) {
		getTextOutputArea().setText(text);
	}

	/**
	 * 
	 */
	public void showBottomRightPointConfirmation() {
		getTextOutputArea().setText("Bottom-Right Point has been set.");
	}

	/**
	 * 
	 */
	public void showTopLeftPointConfirmation() {
		getTextOutputArea().setText("Top-Left Point has been set.");
	}

	/**
	 * Create the Paper App and ask the toolkit to start it up.
	 */
	private void startApp() {
		app = new CaptureApplication(this);
		PaperToolkit p = new PaperToolkit();
		p.startApplication(app);
	}

}