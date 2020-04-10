package Code;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.DirectoryDialog;
import java.nio.file.*;

public class AuthorGUI {

	public static Shell shell;
	public static Shell uploadShell;
	public static Display display;
	public static ArrayList<Journal> myJournals = new ArrayList<Journal>();
	public static ArrayList<Journal> reviewedJournals = new ArrayList<Journal>();
	public static ArrayList<Journal> publishedJournals = new ArrayList<Journal>();
	public static String name = "AUTHOR";
	public static List systemReviewers;
	public static Composite mainView;
	public static Composite myJournalGrid;
	public static Composite reviewedJournalGrid;
	public static Composite publishedJournalGrid;

	// Arrays that link to database
	public static Reviewer[] reviewerList = { new Reviewer("Reviewer 1"), new Reviewer("Reviewer 2"), new Reviewer("Reviewer 3"), new Reviewer("Reviewer 4") };
	public static String[] stringReviewerList = new String[reviewerList.length];

	public static String[] stringJournals;

	/**
	 * Launch the application.
	 * 
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

	// PSEUDODATABASE
	public static void populateMyJournals() {
		myJournals.add(new Journal("Journal 1"));
		myJournals.add(new Journal("Journal 2"));
		myJournals.add(new Journal("Journal 3"));
		populateReviewers();
	}

	public static void populateReviewedJournals() {
		reviewedJournals.add(new Journal("Journal 1"));
		reviewedJournals.add(new Journal("Journal 2"));
		reviewedJournals.add(new Journal("Journal 3"));
	}

	public static void populatePublishedJournals() {
		publishedJournals.add(new Journal("Journal 1"));
		publishedJournals.add(new Journal("Journal 2"));
		publishedJournals.add(new Journal("Journal 3"));
	}

	public static void populateReviewers() {
		for (int i = 0; i < myJournals.size(); i++) {
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
		populateMyJournals();
		populateReviewedJournals();
		populatePublishedJournals();
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
	public void createContents() {
		shell = new Shell();
		shell.setSize(1000, 800);
		shell.setText("SWT Application");

		Button btnUploadFile = new Button(shell, SWT.NONE);
		btnUploadFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				uploadWindow();
			}
		});
		btnUploadFile.setBounds(770, 10, 94, 28);
		btnUploadFile.setText("Upload File");

		mainView = new Composite(shell, SWT.BORDER);
		mainView.setBounds(33, 60, 917, 650);
		StackLayout layout = new StackLayout();
		mainView.setLayout(layout);
		
		GridLayout gridlayout = new GridLayout();
		gridlayout.numColumns = 8;
		
//		myJournalGrid = new Composite(mainView, SWT.V_SCROLL);
//
//		myJournalGrid.setBounds(0, 0, 1100, 900);
//		myJournalGrid.setLayout(gridlayout);
//		for (int i = 0; i < myJournals.size(); i++) {
//			Composite tempJournal = new Composite(myJournalGrid, SWT.BORDER);
//
//			Button submitForReview = new Button(tempJournal, SWT.NONE);
//			submitForReview.setText("Submit");
//			submitForReview.setBounds(50, 100, 50, 30);
//
//			Label x = new Label(tempJournal, SWT.BORDER);
//			x.setBounds(0, 0, 100, 130);
//			Color c = new Color(display, 110, 176, 216);
//			x.setBackground(c);
//			// This blocks future labels and stuff???
//			x.setText(myJournals.get(i).getJournalTitle() + "\n\n" + "Nominated \nReviewers\n\n"
//					+ myJournals.get(i).getReviewers()[0].getName() + "\n"
//					+ myJournals.get(i).getReviewers()[1].getName() + "\n"
//					+ myJournals.get(i).getReviewers()[2].getName());
//		}
		
		
//		reviewedJournalGrid = new Composite(mainView, SWT.V_SCROLL);
//
//		reviewedJournalGrid.setBounds(0, 0, 900, 800);
//		reviewedJournalGrid.setLayout(gridlayout);
//		for (int i = 0; i < reviewedJournals.size(); i++) {
//			Composite tempJournal = new Composite(reviewedJournalGrid, SWT.BORDER);
//
//			Color c = new Color(display, 237, 232, 99);
//
//			Label status = new Label(tempJournal, SWT.NONE);
//			status.setBounds(5, 30, 95, 30);
//			status.setBackground(c);
//			if (reviewedJournals.get(i).getStatus() == -1)
//				status.setText("Status: \nNot Reviewed Yet");
//			else if (reviewedJournals.get(i).getStatus() == 1)
//				status.setText("Status: \nMajor Changes Required");
//			else if (reviewedJournals.get(i).getStatus() == 1)
//				status.setText("Status: \nMinor Changes Required");
//			else if (reviewedJournals.get(i).getStatus() == 1)
//				status.setText("Status: \nApproved Changes Required");
//			else
//				status.setText("Status: Error");
//
//			Label x = new Label(tempJournal, SWT.BORDER);
//			x.setBounds(0, 0, 100, 130);
//			x.setBackground(c);
//			// This blocks future labels and stuff???
//			x.setText(reviewedJournals.get(i).getJournalTitle());
//
//		}
//		publishedJournalGrid = new Composite(mainView, SWT.V_SCROLL);
//
//		publishedJournalGrid.setBounds(0, 0, 1100, 900);
//		publishedJournalGrid.setLayout(gridlayout);
//		for (int i = 0; i < myJournals.size(); i++) {
//			Composite tempJournal = new Composite(publishedJournalGrid, SWT.BORDER);
//
//			Label x = new Label(tempJournal, SWT.BORDER);
//			x.setBounds(0, 0, 100, 130);
//			Color c = new Color(display, 94, 186, 125);
//			x.setBackground(c);
//			// This blocks future labels and stuff???
//			x.setText(myJournals.get(i).getJournalTitle());
//		}

		ToolBar toolBar_1 = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setBounds(33, 31, 366, 23);

		ToolItem tltmMyJournals = new ToolItem(toolBar_1, SWT.RADIO);
		tltmMyJournals.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				displayMyJournals();
				layout.topControl = myJournalGrid;
				mainView.layout();
			}
		});
		tltmMyJournals.setText("My Journals");

		ToolItem tltmReviewedJournals = new ToolItem(toolBar_1, SWT.RADIO);
		tltmReviewedJournals.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				displayReviewedJournals();
				layout.topControl = reviewedJournalGrid;
				mainView.layout();
			}
		});
		tltmReviewedJournals.setText("Reviewed Journals");

		ToolItem tltmPublishedJournals = new ToolItem(toolBar_1, SWT.RADIO);
		tltmPublishedJournals.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				displayPublishedJournals();
				layout.topControl = publishedJournalGrid;
				mainView.layout();
			}
		});
		tltmPublishedJournals.setText("Published Journals");

		Label lblWelcome = new Label(shell, SWT.NONE);
		lblWelcome.setBounds(650, 15, 120, 14);
		lblWelcome.setText("Welcome " + name);

		systemReviewers = new List(shell, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		populateReviewerList(systemReviewers);

		Button btnLogOut = new Button(shell, SWT.NONE);
		btnLogOut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginScreen logout = new LoginScreen();
				shell.close();
				logout.open();
			}
		});
		btnLogOut.setBounds(879, 10, 94, 28);
		btnLogOut.setText("Log Out");

	}

	public static void uploadWindow() {
		uploadShell = new Shell(shell);
		uploadShell.open();
		uploadShell.getLayout();
		uploadShell.setText("Upload File");
		uploadShell.setSize(450, 300);

		String reviewers[] = new String[3];
		reviewers[0] = "";
		reviewers[1] = "";
		reviewers[2] = "";

		Combo journalCombo = new Combo(uploadShell, SWT.DROP_DOWN);

		Label lblInsertFileName = new Label(uploadShell, SWT.NONE); // CHANGES
		lblInsertFileName.setBounds(73, 34, 118, 14);
		lblInsertFileName.setText("Insert File Name");

		Button btnBrowse = new Button(uploadShell, SWT.NONE);
		btnBrowse.setBounds(203, 27, 94, 28);
		btnBrowse.setText("Browse");
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

//				DirectoryDialog directoryDialog = new DirectoryDialog(shell);
				String selectedDir = "";
//				directoryDialog.setFilterPath(selectedDir);
//				directoryDialog.setMessage("Please select a directory and click OK");		//CHANGE TO FILE SELECTION
//				String dir = directoryDialog.open();
//				if (dir != null) {
//					selectedDir = dir;
//					System.out.println(selectedDir);
//				}
				
				//TODO On upload, move file to common folder
				
				FileDialog file = new FileDialog(shell, SWT.SINGLE);
				file.setFilterNames(new String[] { "Batch Files", "All Files (*.*)" });
				file.setFilterExtensions(new String[] { "*.txt", "*.*" });

				file.setFilterPath(selectedDir);

				String firstFile = file.open(); // Using first file update database in new method???? Passing file path
												// as parameter
				System.out.println(firstFile);
//				AuthorFunctions af = new AuthorFunctions();
//				try {
//					af.authorCPFiles(firstFile, new java.io.File(".").getCanonicalPath());
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
		});

		Button btnNewJournal = new Button(uploadShell, SWT.CHECK);
		btnNewJournal.setSelection(false);
		if (btnNewJournal.getSelection() == true) {
			journalCombo.setVisible(false);
		}

		btnNewJournal.setBounds(84, 81, 135, 18);
		btnNewJournal.setText("New Journal");

		convertArrayListToString();

		journalCombo.setItems(stringJournals);
		journalCombo.setBounds(84, 150, 80, 30);
		journalCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String journalName = journalCombo.getText();
				//CHECKS ARRAYLIST
				if(journalExists(journalName))
				{
					System.out.println("JOURNAL EXISTS");
				}
				else {
					MessageBox m = new MessageBox(uploadShell, SWT.NONE);
					m.setMessage("ERROR Journal Does Not Exist");
					m.open();
				}
			}
		});

		Label reviewerLabel = new Label(uploadShell, SWT.NONE);
		reviewerLabel.setBounds(200, 150, 200, 30);

		Label reviewerTitle = new Label(uploadShell, SWT.NONE);
		reviewerTitle.setBounds(200, 120, 80, 30);
		reviewerTitle.setText("Reviewers");

		Combo reviewerCombo = new Combo(uploadShell, SWT.DROP_DOWN);
		reviewerCombo.setItems(stringReviewerList);
		reviewerCombo.setBounds(290, 120, 80, 30);
		reviewerCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(reviewerCombo.getText());
				reviewers[0] = reviewers[1];
				reviewers[1] = reviewers[2];
				reviewers[2] = reviewerCombo.getText();

				reviewerLabel.setText(reviewers[0] + ", " + reviewers[1] + ", " + reviewers[2]);
			}
		});
		
		Button btnAddNewReviewer = new Button(uploadShell, SWT.NONE);
		btnAddNewReviewer.setText("Add new reviewer");
		btnAddNewReviewer.setBounds(290, 190, 110, 30);
		btnAddNewReviewer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(reviewerCombo.getText());
				reviewerCombo.add(reviewerCombo.getText());
			}
		});

		Button btnUpload = new Button(uploadShell, SWT.NONE);
		btnUpload.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnNewJournal.getSelection()) // new Journal
				{
					// Pass through reviewers, journal name and add journal file
				} else if (!btnNewJournal.getSelection()) // Replace journal
				{
					// Find journal in arraylist, overwrite name with new journal name and add new
					// journal while deleting old journal.
					Reviewer[] arrayReviewers = new Reviewer[reviewers.length];
					for(int i = 0; i < arrayReviewers.length; i++)
					{
						arrayReviewers[i] = new Reviewer(reviewers[i]);
					}
					if(getJournalIndex(journalCombo.getText()) == -1)
					{
						MessageBox m = new MessageBox(uploadShell, SWT.NONE);
						m.setMessage("ERROR Journal Does Not Exist");
						m.open();
					}
					myJournals.get(getJournalIndex(journalCombo.getText())).setReviewers(arrayReviewers);
					System.out.println("SET");
					System.out.println(myJournals.get(getJournalIndex(journalCombo.getText())).getReviewers()[0].getName());
					System.out.println(myJournals.get(getJournalIndex(journalCombo.getText())).getReviewers()[1].getName());
					System.out.println(myJournals.get(getJournalIndex(journalCombo.getText())).getReviewers()[2].getName());

				}

				// Need to pass parameters to database
//				display.getActiveShell().close();
//				uploadShell.close();
			}
		});
		btnUpload.setBounds(349, 226, 75, 25);
		btnUpload.setText("Upload");

		try {
			while (!uploadShell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
				newJournalCheck(btnNewJournal, journalCombo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void newJournalCheck(Button b, Combo bar) {
		if (b.getSelection())
			bar.setVisible(false);
		else
			bar.setVisible(true);
	}

	public static void displayMyJournals() {
		myJournalGrid = new Composite(mainView, SWT.BORDER);
		myJournalGrid.setBounds(0, 0, 1100, 900);
		myJournalGrid.setLayout(new RowLayout());
		for (int i = 0; i < myJournals.size(); i++) {
			Composite tempJournal = new Composite(myJournalGrid, SWT.BORDER);
			Button submitForReview = new Button(tempJournal, SWT.NONE);
			submitForReview.setText("Submit");
			submitForReview.setBounds(50, 100, 50, 30);
			Label x = new Label(tempJournal, SWT.BORDER);
			x.setBounds(0, 0, 100, 130);
			Color c = new Color(display, 110, 176, 216);
			x.setBackground(c);
			// This blocks future labels and stuff???
			x.setText(myJournals.get(i).getJournalTitle() + "\n\n" + "Reviewers\n"
					+ myJournals.get(i).getReviewers()[0].getName() + "\n"
					+ myJournals.get(i).getReviewers()[1].getName() + "\n"
					+ myJournals.get(i).getReviewers()[2].getName());
		}
	}

	public static void displayReviewedJournals() {
		reviewedJournalGrid = new Composite(mainView, SWT.BORDER);
		reviewedJournalGrid.setBounds(0, 0, 1100, 900);
		reviewedJournalGrid.setLayout(new RowLayout());
		for (int i = 0; i < reviewedJournals.size(); i++) {
			Composite tempJournal = new Composite(reviewedJournalGrid, SWT.BORDER);

			Color c = new Color(display, 237, 232, 99);

			Label status = new Label(tempJournal, SWT.NONE);
			status.setBounds(5, 30, 95, 30);
			status.setBackground(c);
			if (reviewedJournals.get(i).getStatus() == -1)
				status.setText("Status: \nNot Reviewed Yet");
			else if (reviewedJournals.get(i).getStatus() == 1)
				status.setText("Status: \nMajor Changes Required");
			else if (reviewedJournals.get(i).getStatus() == 1)
				status.setText("Status: \nMinor Changes Required");
			else if (reviewedJournals.get(i).getStatus() == 1)
				status.setText("Status: \nApproved Changes Required");
			else
				status.setText("Status: Error");

			Label x = new Label(tempJournal, SWT.BORDER);
			x.setBounds(0, 0, 100, 130);
			x.setBackground(c);
			// This blocks future labels and stuff???
			x.setText(reviewedJournals.get(i).getJournalTitle());
		}
	}

	// TODO ADD JOURNAL BINDING
	public static void displayPublishedJournals() {
		publishedJournalGrid = new Composite(mainView, SWT.BORDER);
		publishedJournalGrid.setBounds(0, 0, 1100, 900);
		publishedJournalGrid.setLayout(new RowLayout());
		for (int i = 0; i < publishedJournals.size(); i++) {
			Composite tempJournal = new Composite(publishedJournalGrid, SWT.BORDER);

			Label x = new Label(tempJournal, SWT.BORDER);
			x.setBounds(0, 0, 100, 130);
			Color c = new Color(display, 94, 186, 125);
			x.setBackground(c);
			// This blocks future labels and stuff???
			x.setText(publishedJournals.get(i).getJournalTitle());
		}
	}

	public static void convertArrayListToString() {
		stringJournals = new String[myJournals.size()];
		for (int i = 0; i < reviewerList.length; i++)
			stringReviewerList[i] = reviewerList[i].getName();
		for (int i = 0; i < myJournals.size(); i++)
			stringJournals[i] = myJournals.get(i).getJournalTitle();
	}

	public static void populateReviewerList(List l) {
		for (int i = 0; i < 10; i++) {
			l.add("Reviewer " + i);
		}
	}
	public static boolean journalExists(String s)
	{
		for(int i = 0; i < myJournals.size(); i++)
		{
			if(s.equals(myJournals.get(i).getJournalTitle()))
				return true;
		}
		return false;
	}
	public static int getJournalIndex(String s)
	{
		for(int i = 0; i < myJournals.size(); i++)
		{
			if(s.equals(myJournals.get(i).getJournalTitle()))
				return i;
		}
		return -1;
	}
	
}