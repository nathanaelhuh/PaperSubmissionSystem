package Code;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.TabFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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

	private static ArrayList<Journal> reviewedJournals = new ArrayList<Journal>();
	
	
	protected Shell shlReviewerView;
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
		shlReviewerView.open();
		shlReviewerView.layout();
		while (!shlReviewerView.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlReviewerView = new Shell();
		shlReviewerView.setSize(700, 450);
		shlReviewerView.setText("Reviewer View");
		
		databaseDownload();
		
		Button btnLogOut = new Button(shlReviewerView, SWT.NONE);
		btnLogOut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginScreen logout = new LoginScreen();
				shlReviewerView.close();
				logout.open();
			}
		});
		btnLogOut.setBounds(599, 10, 75, 25);
		btnLogOut.setText("Log Out");
		Composite tabs = new Composite(shlReviewerView, SWT.NONE);
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
		lblJournals.setBounds(53, 10, 59, 14);
		lblJournals.setText("Journals");
		
		List allJournals = new List(browseJournals, SWT.BORDER);
		allJournals.setLocation(10, 32);
		allJournals.setSize(152, 168);
		allJournals.setItems(databaseDownload());
		
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
		journalList.setItems(databaseDownload());
		String[] temp = databaseDownload();
		for(int i = 0; i < temp.length; i++) {
			System.out.println(temp[0]);
		}
		System.out.println("this was reached");
		//journalList.setItems(new String[] {"Journal 1", "Journal 2", "Journal 3"});
		
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
						int[] firstStatus = {statusToInt(selectedChanges), -1, -1};
						reviewedJournals.get(i).setStatus(firstStatus);
						reviewedJournals.get(i).setComments(comments);
					}
				}
				System.out.println(selectedJournal);
				System.out.println(selectedChanges);
				System.out.println(comments);
				DataText dt = new DataText();
				try {
					dt.arrayToText(myJournalsString(), "reviewedJournals.txt", new java.io.File(".").getCanonicalPath() + File.separator + Paths.get("Database"));
				} catch (IOException e1) {
					System.out.println("Failed Array to Text");
					e1.printStackTrace();
				}
			}
		});
		btnSubmit.setBounds(258, 80, 102, 28);
		btnSubmit.setText("Submit");

	}

	public static String[] databaseDownload() {
		System.out.println(":(");
		ArrayList<Journal> allJournals = new ArrayList<Journal>();
		DataText dt = new DataText();
		ArrayList<String> temp2 = new ArrayList<String>();
		String databasePath = "";
		try {
			databasePath = new java.io.File(".").getCanonicalPath() + File.separator + Paths.get("Database");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		temp2 = dt.textToArray("reviewedJournals.txt", databasePath);
		System.out.println("we need non zero here:" + temp2.size());
		for(int i = 0; i < temp2.size(); i++) {
			System.out.println(temp2.get(i));
			String temps[] = temp2.get(i).split(",");
			System.out.println(temps[0]);
			Journal myJournal = new Journal(temps[0]);
			int status1 = Integer.parseInt(temps[1]);
			String aRev1 = temps[2];
			String nRev1 = temps[3];
			String rRev1 = temps[4];
			int status2 = Integer.parseInt(temps[5]);
			String aRev2 = temps[6];
			String nRev2 = temps[7];
			String rRev2 = temps[8];
			int status3 = Integer.parseInt(temps[9]);
			String aRev3 = temps[10];
			String nRev3 = temps[11];
			String rRev3 = temps[12];
			String summary = temps[13];
			
			String[] aReviewers = {aRev1, aRev2, aRev3};
			String[] nReviewers = {nRev1, nRev2, nRev3};
			String[] rReviewers = {rRev1, rRev2, rRev3};
			int[] status = {status1, status2, status3};
			
			myJournal.setAssReviewers(aReviewers);
			myJournal.setNomReviewers(nReviewers);
			myJournal.setReqReviewers(rReviewers);
			myJournal.setStatus(status);
			allJournals.add(myJournal);
			
			System.out.println("TWOAH " + allJournals.get(0).getJournalTitle());
		}
		String[] titleResult = new String[allJournals.size()];
		for(int i = 0; i < titleResult.length; i++) {
			titleResult[i] = allJournals.get(i).getJournalTitle();
			System.out.println("this is running");
		}
		return titleResult;
	}
	
	public static ArrayList<String> myJournalsString()
	{
		ArrayList<String> s = new ArrayList<String>();
		for(int i = 0; i < reviewedJournals.size(); i++)
			s.add(reviewedJournals.get(i).toString());
		return s;
	}

}
