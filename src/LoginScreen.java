import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.awt.Button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoginScreen {

	protected Shell shell;
	public Button selected;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginScreen window = new LoginScreen();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(68, 48, 170, 14);
		lblNewLabel.setText("Please Select Your Role");
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(68, 105, 170, 20);
		
		ToolItem tltmEditor = new ToolItem(toolBar, SWT.RADIO);
		tltmEditor.setText("Editor");
		
		ToolItem tltmAuthor = new ToolItem(toolBar, SWT.RADIO);
		tltmAuthor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AuthorGUI author = new AuthorGUI();
				shell.close();
				author.open();
			}
		});
		tltmAuthor.setText("Author");
		
		ToolItem tltmReviewer = new ToolItem(toolBar, SWT.RADIO);
		tltmReviewer.setText("Reviewer");

	}
}
