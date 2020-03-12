import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AuthorGUI {

	public static Shell shell;
	public static upload upload;
	public static ArrayList<Journal> myJournals = new ArrayList<Journal>();
	public static String name = "AUTHOR";

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AuthorGUI window = new AuthorGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void populateJournals() {
		myJournals.add(new Journal("Journal 1"));
		myJournals.add(new Journal("Journal 2"));
		myJournals.add(new Journal("Journal 3"));
		populateReviewers();
	}
	
	public static void populateReviewers() {
		for(int i = 0; i < myJournals.size(); i++)
		{
			Reviewer[] newReviewers = new Reviewer[3];
			newReviewers[0] = new Reviewer("Paul");
			newReviewers[1] = new Reviewer("Josh");
			newReviewers[2] = new Reviewer("Guy");
			
			myJournals.get(i).setReviewers(newReviewers);
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		populateJournals();
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
	public void createContents() {
		shell = new Shell();
		shell.setSize(1200, 1000);
		shell.setText("SWT Application");
		
		Button btnUploadFile = new Button(shell, SWT.NONE);
		btnUploadFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				upload = new upload();
				upload.createContents();
				upload.open();
			}
		});
		btnUploadFile.setBounds(1096, 10, 94, 28);
		btnUploadFile.setText("Upload File");
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(33, 32, 531, 20);
		
		ToolItem tltmMyJournals = new ToolItem(toolBar, SWT.RADIO);
		tltmMyJournals.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				displayMyJournals();
			}
		});
		tltmMyJournals.setText("My Journals");
		
		ToolItem tltmReviewedJournals = new ToolItem(toolBar, SWT.RADIO);
		tltmReviewedJournals.setText("Reviewed Journals");
		
		ToolItem tltmPublishedJournals = new ToolItem(toolBar, SWT.RADIO);
		tltmPublishedJournals.setText("Published Journals");
		
		Label lblWelcome = new Label(shell, SWT.NONE);
		lblWelcome.setBounds(956, 17, 134, 14);
		lblWelcome.setText("Welcome " + name);
	}
	
	public static void displayMyJournals() {
		for(int i = 0; i < myJournals.size(); i++)
		{
			Label x = new Label(shell, SWT.NONE);
			x.setBounds(50+80*i, 60, 70, 100);
			x.setText(myJournals.get(i).getJournalTitle() + "\n\n" + "Reviewers\n" + myJournals.get(i).getReviewers()[0].getName() + "\n" + myJournals.get(i).getReviewers()[1].getName() + "\n" + myJournals.get(i).getReviewers()[2].getName());
		}
	}
}
