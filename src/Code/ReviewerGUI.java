package Code;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.TabFolder;

import java.util.ArrayList;

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

	private ArrayList<Journal> reviewedJournals = new ArrayList<Journal>();
	
	
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
	public static int statusToInt(String status) {
		int sInt = -1;
		
		if(status.equals("Major Changes")) {
			sInt = 1;
		} else if(status.equals("Minor Changes")) {
			sInt = 2;
		} else if(status.equals("Accepted")) {
			sInt = 3;
		} else if(status.equals("Rejected")) {
			sInt = 4;
		}
		
		return sInt;
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
		shell.setSize(700, 450);
		shell.setText("SWT Application");
		
		Button btnLogOut = new Button(shell, SWT.NONE);
		btnLogOut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginScreen logout = new LoginScreen();
				shell.close();
				logout.open();
			}
		});
		btnLogOut.setBounds(599, 10, 75, 25);
		btnLogOut.setText("Log Out");
		Composite tabs = new Composite(shell, SWT.NONE);
		tabs.setBounds(0, 0, 674, 364);
		
		StackLayout layout = new StackLayout();
		tabs.setLayout(layout);
		
		TabFolder tabFolder = new TabFolder(tabs, SWT.NONE);
		tabFolder.setBounds(0, 26, 674, 364);
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
		//TODO: POPULATE THE LIST WITH ALL JOURNAL OBJECTS
		allJournals.setItems(new String[] {"Journal 1", "Journal 2", "Journal 3"});
		
		Label lblPreview = new Label(browseJournals, SWT.CENTER);
		lblPreview.setBounds(248, 12, 70, 14);
		lblPreview.setText("Preview");
		
		Button requestToReviewBtn = new Button(browseJournals, SWT.NONE);
		requestToReviewBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String j = allJournals.getSelection()[0];
				System.out.println(j);
				//TODO: Somehow communicate with the editor class that a
				//review request for this journal has been submitted
			}
		});
		requestToReviewBtn.setBounds(10, 203, 152, 27);
		requestToReviewBtn.setText("Request to Review");
		
		Label removeThisLabel = new Label(browseJournals, SWT.NONE);
		removeThisLabel.setBounds(205, 38, 229, 14);
		removeThisLabel.setText("TODO: Either remove this section, or figure out a way to preview the journal");
		
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
		lblJournalToReview.setBounds(54, 26, 115, 22);
		lblJournalToReview.setText("Journal to Review");
		
		Combo journalList = new Combo(reviewJournals, SWT.READ_ONLY);
		journalList.setBounds(26, 49, 170, 22);
		//TODO: POPULATE THE LIST WITH ALL JOURNAL OBJECTS (see line 114 above)
		journalList.setItems(new String[] {"Journal 1", "Journal 2", "Journal 3"});
		
		Button btnOpenSelectedJournal = new Button(reviewJournals, SWT.NONE);
		btnOpenSelectedJournal.setBounds(29, 80, 167, 28);
		btnOpenSelectedJournal.setText("Open Selected Journal");
		
		Label lblChangesNeeded = new Label(reviewJournals, SWT.NONE);
		lblChangesNeeded.setAlignment(SWT.CENTER);
		lblChangesNeeded.setBounds(248, 26, 132, 22);
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
				for(int i = 0; i < reviewedJournals.size(); i++) {
					if(reviewedJournals.get(i).journalTitle.equals(selectedJournal)) {
						reviewedJournals.get(i).setStatus(statusToInt(selectedChanges));
						reviewedJournals.get(i).setComments(comments);
					}
				}
				System.out.println(selectedJournal);
				System.out.println(selectedChanges);
				System.out.println(comments);
			}
		});
		btnSubmit.setBounds(258, 80, 102, 28);
		btnSubmit.setText("Submit");

	}
}