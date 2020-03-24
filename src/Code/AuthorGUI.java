package Code;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
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
import org.eclipse.swt.layout.*;

public class AuthorGUI {

	public static Shell shell;
	public static Upload upload;
	public static Display display;
	public static ArrayList<Journal> myJournals = new ArrayList<Journal>();
	public static ArrayList<Journal> reviewedJournals = new ArrayList<Journal>();
	public static String name = "AUTHOR";
	public static List systemReviewers;
	public static Composite mainView;
	public static Composite myJournalGrid;
	public static Composite reviewedJournalGrid;
	
	//TEST LIST THAT CAN BE DELETED FOR A DATABASE LATER
//	public static Reviewer[] reviewerList = {new Reviewer("Reviewer 1"), new Reviewer("Reviewer 2"), new Reviewer("Reviewer 3"), new Reviewer("Reviewer 4")};
//	public static String[] stringReviewerList = new String[reviewerList.length];
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
		populateMyJournals();
		populateReviewedJournals();
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
		shell.setSize(1200, 1000);
		shell.setText("SWT Application");
		
//		populateStringList();
		
		Button btnUploadFile = new Button(shell, SWT.NONE);
		btnUploadFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				upload = new Upload();
				upload.createContents();
				upload.open();
			}
		});
		btnUploadFile.setBounds(1096, 10, 94, 28);
		btnUploadFile.setText("Upload File");
		
		mainView = new Composite(shell, SWT.BORDER);
		mainView.setBounds(33, 60, 1100, 900);
	    StackLayout layout = new StackLayout();
	    mainView.setLayout(layout);
	    
	    myJournalGrid = new Composite(mainView, SWT.NONE);
	    
	    myJournalGrid.setBounds(0, 0, 1100, 900);
		myJournalGrid.setLayout(new RowLayout());
		for(int i = 0; i < myJournals.size(); i++)
		{
			Composite tempJournal = new Composite(myJournalGrid, SWT.BORDER);
			
//			Combo reviewerCombo = new Combo(tempJournal, SWT.DROP_DOWN);
//			reviewerCombo.setItems(stringReviewerList);
//			reviewerCombo.setBounds(20,  100,  80,  30);

//			List journalReviewersListPick = new List(tempJournal, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
//			populateReviewerList(journalReviewersListPick);
//			Font reviewerPicklistFont = new Font(journalReviewersListPick.getDisplay(), new FontData( "Arial", 8, SWT.BOLD));
//			journalReviewersListPick.setFont(reviewerPicklistFont);
//			journalReviewersListPick.setBounds(50, 100, 50, 30);
			Button submitForReview = new Button(tempJournal, SWT.NONE);
			submitForReview.setText("Submit");
			submitForReview.setBounds(50, 100, 50, 30);
			
			Label x = new Label(tempJournal, SWT.BORDER);
			x.setBounds(0, 0, 100, 130);
			Color c = new Color(display, 110, 176, 216);
			x.setBackground(c);
			//This blocks future labels and stuff???
			x.setText(myJournals.get(i).getJournalTitle() + "\n\n" + "Nominated \nReviewers\n\n" 
					+ myJournals.get(i).getReviewers()[0].getName() + "\n" 
					+ myJournals.get(i).getReviewers()[1].getName() + "\n" 
					+ myJournals.get(i).getReviewers()[2].getName());
		}
	    
	    
	    
	    reviewedJournalGrid = new Composite(mainView, SWT.NONE);
	    
	    reviewedJournalGrid.setBounds(0, 0, 1100, 900);
		reviewedJournalGrid.setLayout(new RowLayout());
		for(int i = 0; i < reviewedJournals.size(); i++)
		{
			Composite tempJournal = new Composite(reviewedJournalGrid, SWT.BORDER);
			
			Color c = new Color(display, 237, 232, 99);
			
			Label status = new Label(tempJournal, SWT.NONE);
			status.setBounds(5, 30, 95, 30);
			status.setBackground(c);
			if(reviewedJournals.get(i).getStatus() == -1)
				status.setText("Status: \nNot Reviewed Yet");
			else if(reviewedJournals.get(i).getStatus() == 1)
				status.setText("Status: \nMajor Changes Required");
			else if(reviewedJournals.get(i).getStatus() == 1)
				status.setText("Status: \nMinor Changes Required");
			else if(reviewedJournals.get(i).getStatus() == 1)
				status.setText("Status: \nApproved Changes Required");
			else
				status.setText("Status: Error");
			
			Label x = new Label(tempJournal, SWT.BORDER);
			x.setBounds(0, 0, 100, 130);
			x.setBackground(c);
			//This blocks future labels and stuff???
			x.setText(reviewedJournals.get(i).getJournalTitle());
			
			
		}
		
		ToolBar toolBar_1 = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar_1.setBounds(33, 31, 366, 23);
		
		ToolItem tltmMyJournals = new ToolItem(toolBar_1, SWT.RADIO);
		tltmMyJournals.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				layout.topControl = myJournalGrid;
				mainView.layout();
			}
		});
		tltmMyJournals.setText("My Journals");
		
		ToolItem tltmReviewedJournals = new ToolItem(toolBar_1, SWT.RADIO);
		tltmReviewedJournals.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				layout.topControl = reviewedJournalGrid;
//				displayReviewedJournals();
				mainView.layout();
			}
		});
		tltmReviewedJournals.setText("Reviewed Journals");
		
		ToolItem tltmPublishedJournals = new ToolItem(toolBar_1, SWT.RADIO);
		tltmPublishedJournals.setText("Published Journals");
		
		Label lblWelcome = new Label(shell, SWT.NONE);
		lblWelcome.setBounds(956, 17, 134, 14);
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
	    btnLogOut.setBounds(979, 32, 94, 28);
	    btnLogOut.setText("Log Out");
		
	}
	
	public static void displayMyJournals() {
		myJournalGrid = new Composite(mainView, SWT.BORDER);
		myJournalGrid.setBounds(0, 0, 1100, 900);
		myJournalGrid.setLayout(new RowLayout());
		for(int i = 0; i < myJournals.size(); i++)
		{
			Composite tempJournal = new Composite(myJournalGrid, SWT.BORDER);

			List journalReviewersListPick = new List(tempJournal, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
			populateReviewerList(journalReviewersListPick);
			Font reviewerPicklistFont = new Font(journalReviewersListPick.getDisplay(), new FontData( "Arial", 8, SWT.BOLD));
			journalReviewersListPick.setFont(reviewerPicklistFont);
			journalReviewersListPick.setBounds(50, 100, 50, 30);
			
			Label x = new Label(tempJournal, SWT.BORDER);
			x.setBounds(0, 0, 100, 130);
			Color c = new Color(display, 110, 176, 216);
			x.setBackground(c);
			//This blocks future labels and stuff???
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
		for(int i = 0; i < reviewedJournals.size(); i++)
		{
			Composite tempJournal = new Composite(reviewedJournalGrid, SWT.BORDER);
			
			Label x = new Label(tempJournal, SWT.BORDER);
			x.setBounds(0, 0, 100, 130);
			Color c = new Color(display, 237, 232, 99);
			x.setBackground(c);
			//This blocks future labels and stuff???
			x.setText(reviewedJournals.get(i).getJournalTitle());
		}
	}
	
	public static void populateReviewerList(List l)
	{
		for(int i = 0; i < 10; i++)
		{
			l.add("Reviewer " + i);
		}
	}
	
//	public static void populateStringList()
//	{
//		for(int i = 0; i < reviewerList.length; i++)
//			stringReviewerList[i] = reviewerList[i].getName();
//	}
}