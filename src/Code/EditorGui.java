package Code;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Scale;

public class EditorGUI {

	protected Shell shell;
	protected Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EditorGUI window = new EditorGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(817, 512);
		shell.setText("SWT Application");
		
		Color white = new Color(display, 255, 255, 255);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBackground(white);
		composite.setForeground(white);
		composite.setBounds(0, 0, 801, 473);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 31, 212, 423);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		List list = new List(scrolledComposite, SWT.BORDER);
		list.setItems(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "89", "9", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"});
		scrolledComposite.setContent(list);
		scrolledComposite.setMinSize(list.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblPapers = new Label(composite, SWT.NONE);
		lblPapers.setBackground(white);
		lblPapers.setBounds(10, 10, 55, 15);
		lblPapers.setText("Papers:");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setBounds(228, 50, 130, 25);
		btnNewButton.setText("Check Paper");
		
		Button btnAcceptPaper = new Button(composite, SWT.NONE);
		btnAcceptPaper.setText("Accept Paper");
		btnAcceptPaper.setBounds(228, 317, 130, 25);
		
		Button btnDenyPaper = new Button(composite, SWT.NONE);
		btnDenyPaper.setText("Deny Paper");
		btnDenyPaper.setBounds(228, 361, 130, 25);
		
		Button btnDownloadPaper = new Button(composite, SWT.NONE);
		btnDownloadPaper.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnDownloadPaper.setText("Download Paper");
		btnDownloadPaper.setBounds(228, 92, 130, 25);
		
		StyledText styledText = new StyledText(composite, SWT.BORDER);
		styledText.setText("Paper summary with Title and Author");
		styledText.setBounds(364, 37, 427, 161);
		
		StyledText styledText_1 = new StyledText(composite, SWT.BORDER);
		styledText_1.setText("Reviewer1: Minor changes \r\nReviewer2: Major Changes\r\nReviewer3: No changes\r\n");
		styledText_1.setBounds(364, 317, 427, 137);
		
		Button btnLogOut = new Button(composite, SWT.NONE);
		btnLogOut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginScreen logout = new LoginScreen();
				shell.close();
				logout.open();
			}
		});
		btnLogOut.setBounds(716, 6, 75, 25);
		btnLogOut.setText("Log Out");

	}
}