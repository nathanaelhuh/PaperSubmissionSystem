import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;

public class upload extends Shell {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static Display display;
	public static ToolBar toolBar;
	public static Button btnNewJournal;
	
	public static Reviewer[] reviewerList = {new Reviewer("Reviewer 1"), new Reviewer("Reviewer 2"), new Reviewer("Reviewer 3"), new Reviewer("Reviewer 4")};
	public static String[] stringReviewerList = new String[reviewerList.length];
	
	public static void populateStringList()
	{
		for(int i = 0; i < reviewerList.length; i++)
			stringReviewerList[i] = reviewerList[i].getName();
	}
	
	//This is for testing upload separately
	public static void main(String args[]) {
		try {
			display = Display.getDefault();
			upload shell = new upload();
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
				newJournalCheck(btnNewJournal, toolBar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void open() {
//		createContents();
//		this.open();
//		this.layout();
//		while (!this.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//			newJournalCheck(btnNewJournal, toolBar);
//		}
//	}

	/**
	 * Create the shell.
	 * @param display
	 */

	public upload() {
		
		toolBar = new ToolBar(this, SWT.FLAT | SWT.RIGHT | SWT.VERTICAL);
		
		Label lblInsertFileName = new Label(this, SWT.NONE);
		lblInsertFileName.setBounds(73, 34, 118, 14);
		lblInsertFileName.setText("Insert File Name");
		
		Button btnBrowse = new Button(this, SWT.NONE);
		btnBrowse.setBounds(203, 27, 94, 28);
		btnBrowse.setText("Browse");
		
		btnNewJournal = new Button(this, SWT.CHECK);
		btnNewJournal.setSelection(false);
		if(btnNewJournal.getSelection() == true)
		{
			toolBar.setVisible(false);
		}
		
		btnNewJournal.setBounds(84, 81, 135, 18);
		btnNewJournal.setText("New Journal");
		
		
		toolBar.setBounds(56, 138, 86, 67);
		
		ToolItem tltmRadioItem = new ToolItem(toolBar, SWT.RADIO);
		tltmRadioItem.setText("Journal 1");
		
		ToolItem tltmRadioItem_1 = new ToolItem(toolBar, SWT.RADIO);
		tltmRadioItem_1.setText("Journal 2");
		
		ToolItem tltmRadioItem_2 = new ToolItem(toolBar, SWT.RADIO);
		tltmRadioItem_2.setText("Journal 3");
		
		populateStringList();
		
		Combo reviewerCombo = new Combo(this, SWT.DROP_DOWN);
		reviewerCombo.setItems(stringReviewerList);
		reviewerCombo.setBounds(200,  200,  80,  30);
		
		
		// TODO Auto-generated constructor stub
	}
	public static void newJournalCheck(Button b, ToolBar bar) {
		if(b.getSelection())
			bar.setVisible(false);
		else
			bar.setVisible(true);
		System.out.println(b.getSelection());
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Upload File");
		setSize(450, 300);
		
		
		display = Display.getDefault();
		this.open();
		this.layout();
		while (!this.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
			newJournalCheck(btnNewJournal, toolBar);
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
