import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.List;

public class ReviewerGUI {

	protected Shell shell;
	private Text commentBox;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ReviewerGUI window = new ReviewerGUI();
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
		StackLayout layout = new StackLayout();
		shell.setLayout(layout);
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		layout.topControl = tabFolder;
		
		TabItem tbtmBrowseJournals = new TabItem(tabFolder, SWT.NONE);
		tbtmBrowseJournals.setText("Browse Journals");
		
		Composite browseJournals = new Composite(tabFolder, SWT.NONE);
		tbtmBrowseJournals.setControl(browseJournals);
		
		Label lblJournals = new Label(browseJournals, SWT.NONE);
		lblJournals.setBounds(45, 12, 59, 14);
		lblJournals.setText("Journals");
		
		List allJournals = new List(browseJournals, SWT.BORDER);
		allJournals.setLocation(10, 32);
		allJournals.setSize(152, 168);
		allJournals.setItems(new String[] {"Journal 1", "Journal 2", "Journal 3"});
		
		Label lblPreview = new Label(browseJournals, SWT.CENTER);
		lblPreview.setBounds(248, 12, 70, 14);
		lblPreview.setText("Preview");
		
		Button btnNewButton_1 = new Button(browseJournals, SWT.NONE);
		btnNewButton_1.setBounds(10, 203, 152, 27);
		btnNewButton_1.setText("Request to Review");
		
		TabItem tbtmReviewJournals = new TabItem(tabFolder, SWT.NONE);
		tbtmReviewJournals.setText("Review Journal");
		
		Composite reviewJournals = new Composite(tabFolder, SWT.NONE);
		tbtmReviewJournals.setControl(reviewJournals);
		
		Label lblComments = new Label(reviewJournals, SWT.CENTER);
		lblComments.setBounds(178, 124, 73, 22);
		lblComments.setText("Comments");
		
		commentBox = new Text(reviewJournals, SWT.BORDER);
		commentBox.setBounds(44, 139, 336, 89);
		
		Label lblJournalToReview = new Label(reviewJournals, SWT.NONE);
		lblJournalToReview.setAlignment(SWT.CENTER);
		lblJournalToReview.setBounds(54, 32, 115, 22);
		lblJournalToReview.setText("Journal to Review");
		
		Combo journalList = new Combo(reviewJournals, SWT.READ_ONLY);
		journalList.setBounds(26, 49, 170, 22);
		journalList.setItems(new String[] {"Journal 1", "Journal 2", "Journal 3"});
		
		Button btnNewButton = new Button(reviewJournals, SWT.NONE);
		btnNewButton.setBounds(29, 80, 167, 28);
		btnNewButton.setText("Open Selected Journal");
		
		Label lblChangesNeeded = new Label(reviewJournals, SWT.NONE);
		lblChangesNeeded.setAlignment(SWT.CENTER);
		lblChangesNeeded.setBounds(248, 32, 132, 22);
		lblChangesNeeded.setText("Changes Needed");
		
		Combo changesList = new Combo(reviewJournals, SWT.READ_ONLY);
		changesList.setBounds(248, 49, 132, 22);
		changesList.setItems(new String[] {"Major Changes", "Minor Changes", "Accepted", "Rejected"});
		changesList.select(3);
		
		Button btnSubmit = new Button(reviewJournals, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String selectedJournal = journalList.getText();
				String selectedChanges = changesList.getText();
				String comments = commentBox.getText();
				System.out.println(selectedJournal);
				System.out.println(selectedChanges);
				System.out.println(comments);
			}
		});
		btnSubmit.setBounds(258, 80, 102, 28);
		btnSubmit.setText("Submit");

	}
}
