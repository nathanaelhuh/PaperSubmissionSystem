package Code;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class EditorGUI {

	protected Shell shell;
	protected Display display;
	public static ArrayList<Journal> myJournals = new ArrayList<Journal>();
	public static ArrayList<Journal> reviewedJournals = new ArrayList<Journal>();
	public int currentJournal;
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
	
	//Initializing fake database
	public static void populateMyJournals() {
		myJournals.add(new Journal("Journal 1"));
		myJournals.add(new Journal("Journal 2"));
		myJournals.add(new Journal("Journal 3"));
//		populateReviewers();
		populateSummaries();
		populateReqReviewers();
		populateNomReviewers();
		populateAssReviewers();
	}
	
//	public static void populateReviewers() {
//		for(int i = 0; i < myJournals.size(); i++)
//		{
//			Reviewer[] newReviewers = new Reviewer[3];
//			newReviewers[0] = new Reviewer("Paul");
//			newReviewers[1] = new Reviewer("Josh");
//			newReviewers[2] = new Reviewer("Guy");
//			
//			myJournals.get(i).setNomReviewers(newReviewers.toString());
//		}
//	}
	public static void populateSummaries() {
		myJournals.get(0).setSummary("Summary of Journal 1");
		myJournals.get(1).setSummary("Summary of Journal 2");
		myJournals.get(2).setSummary("Summary of Journal 3");
	}

	public static void populateReqReviewers() {
		myJournals.get(0).reqReviewers[0] = "j1r1";
		myJournals.get(0).reqReviewers[1] = "j1r2";
		myJournals.get(0).reqReviewers[2] = "j1r3";
		
		myJournals.get(1).reqReviewers[0] = "j2r1";
		myJournals.get(1).reqReviewers[1] = "j2r2";
		myJournals.get(1).reqReviewers[2] = "j2r3";
		
		myJournals.get(2).reqReviewers[0] = "j3r1";
		myJournals.get(2).reqReviewers[1] = "j3r2";
		myJournals.get(2).reqReviewers[2] = "j3r3";
	}
	
	public static void populateNomReviewers() {
		myJournals.get(0).nomReviewers[0] = "j1r4";
		myJournals.get(0).nomReviewers[1] = "j1r5";
		myJournals.get(0).nomReviewers[2] = "j1r6";
		
		myJournals.get(1).nomReviewers[0] = "j2r4";
		myJournals.get(1).nomReviewers[1] = "j2r5";
		myJournals.get(1).nomReviewers[2] = "j2r6";
		
		myJournals.get(2).nomReviewers[0] = "j3r4";
		myJournals.get(2).nomReviewers[1] = "j3r5";
		myJournals.get(2).nomReviewers[2] = "j3r6";
	}
	public static void populateAssReviewers() {
		myJournals.get(0).assReviewers[0] = "";
		myJournals.get(0).assReviewers[1] = "";
		myJournals.get(0).assReviewers[2] = "";
		
		myJournals.get(1).assReviewers[0] = "";
		myJournals.get(1).assReviewers[1] = "";
		myJournals.get(1).assReviewers[2] = "";
		
		myJournals.get(2).assReviewers[0] = "";
		myJournals.get(2).assReviewers[1] = "";
		myJournals.get(2).assReviewers[2] = "";
	}
	/*
	public static void initAssReviewers() {
		myJournals.get(0).assReviewers.ensureCapacity(3);
		myJournals.get(1).assReviewers.ensureCapacity(3);
		myJournals.get(2).assReviewers.ensureCapacity(3);
	}
	*/
	/**
	 * Open the window.
	 */
	public void open() {
		populateMyJournals();
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
		shell.setSize(715, 490);
		shell.setText("Paper Submission");
		
		/**
		* Logout button to send back to home screen
		*/
		Button LogOut = new Button(shell, SWT.NONE);
		LogOut.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				LoginScreen logout = new LoginScreen();
				shell.close();
				logout.open();
			}
		});
		LogOut.setBounds(614, 10, 75, 25);
		LogOut.setText("Log Out");
		
		TabFolder tabFolder_1 = new TabFolder(shell, SWT.NONE);
		tabFolder_1.setBounds(0, 24, 699, 427);
		
		TabItem editor_tab1 = new TabItem(tabFolder_1, SWT.NONE);
		editor_tab1.setText("New Submissions");
		
		Composite NewSubComp = new Composite(tabFolder_1, SWT.NONE);
		editor_tab1.setControl(NewSubComp);
		NewSubComp.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		NewSubComp.setBackground(SWTResourceManager.getColor(255, 255, 255));
		
		Label lblPapers = new Label(NewSubComp, SWT.NONE);
		lblPapers.setText("Papers:");
		lblPapers.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lblPapers.setBounds(10, 10, 55, 15);
		/*
		Button btnDownloadPaper = new Button(NewSubComp, SWT.NONE);
		btnDownloadPaper.setText("Download Paper");
		btnDownloadPaper.setBounds(164, 87, 120, 25);
		*/
		/**
		 * Fills the displayed list with journals
		 */		
		List paperList = new List(NewSubComp, SWT.BORDER);
		paperList.setItems(new String[] {});
		for(int i = 0; i < myJournals.size(); i++){
			paperList.add(myJournals.get(i).getJournalTitle());
		}
		paperList.setBounds(10, 31, 150, 209);
		
		List reqReviewerList = new List(NewSubComp, SWT.BORDER | SWT.H_SCROLL);
		reqReviewerList.setItems(new String[] {});
		reqReviewerList.setBounds(290, 192, 120, 200);
		
		Label lblRequestedReviewers = new Label(NewSubComp, SWT.NONE);
		lblRequestedReviewers.setText("Requested Reviewers:");
		lblRequestedReviewers.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblRequestedReviewers.setBounds(290, 171, 120, 15);
		
		List assReviewerList = new List(NewSubComp, SWT.BORDER);
		assReviewerList.setItems(new String[] {});
		assReviewerList.setBounds(563, 191, 120, 200);
		
		Label lblAssignedReviewers = new Label(NewSubComp, SWT.NONE);
		lblAssignedReviewers.setText("Assigned Reviewers:");
		lblAssignedReviewers.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblAssignedReviewers.setBounds(563, 171, 120, 15);
		
		List nomReviewerList = new List(NewSubComp, SWT.BORDER | SWT.V_SCROLL);
		nomReviewerList.setItems(new String[] {});
		nomReviewerList.setBounds(426, 191, 120, 200);
		
		Label lblNominatedReviewers = new Label(NewSubComp, SWT.NONE);
		lblNominatedReviewers.setText("Nominated Reviewers:");
		lblNominatedReviewers.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNominatedReviewers.setBounds(426, 171, 120, 15);
		
		Text summaryText = new Text(NewSubComp, SWT.BORDER);
		summaryText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		summaryText.setText("");
		summaryText.setEditable(false);
		summaryText.setBounds(290, 31, 391, 120);
		
		/**
		 * button to remove selected reviewer from assigned reviewers
		 */
		Button btnRemoveReviewer = new Button(NewSubComp, SWT.NONE);
		btnRemoveReviewer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedAssReviewer = assReviewerList.getSelectionIndex();
				if (selectedAssReviewer != -1) {
					myJournals.get(currentJournal).assReviewers[selectedAssReviewer] = "";
					//myJournals.get(currentJournal).assReviewers.remove(myJournals.get(currentJournal).getAssReviewers().get(selectedAssReviewer));
					assReviewerList.remove(selectedAssReviewer);;
				}
				paperList.deselectAll();
				nomReviewerList.deselectAll();
				reqReviewerList.deselectAll();
				assReviewerList.deselectAll();
			}
		});
		btnRemoveReviewer.setText("Remove Reviewer");
		btnRemoveReviewer.setBounds(50, 309, 182, 25);
		
		/**
		 * button to add selected reviewer from either nominated reviewer list or from requested reviewer list
		 */
		Button btnAddReviewer = new Button(NewSubComp, SWT.NONE);
		btnAddReviewer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedNomReviewer = reqReviewerList.getSelectionIndex();
				int selectedReqReviewer = nomReviewerList.getSelectionIndex();
				if (selectedNomReviewer != -1 || selectedReqReviewer != -1) {
					if (selectedReqReviewer == -1) {
						assReviewerList.add(myJournals.get(currentJournal).getReqReviewers()[selectedNomReviewer]);
						
						myJournals.get(currentJournal).assReviewers[assReviewerList.getItemCount()-1] = myJournals.get(currentJournal).getReqReviewers()[selectedNomReviewer];
						/*
						for (int i=0; i < myJournals.get(currentJournal).assReviewers.length; i++) {
							if (myJournals.get(currentJournal).assReviewers[i] == "") {
								myJournals.get(currentJournal).assReviewers[i] = myJournals.get(currentJournal).getReqReviewers()[selectedNomReviewer];
							}
						}
						*/
							//myJournals.get(currentJournal).assReviewers.add(myJournals.get(currentJournal).getReqReviewers()[selectedNomReviewer]);
					}
					if (selectedNomReviewer == -1) {
						assReviewerList.add(myJournals.get(currentJournal).getNomReviewers()[selectedReqReviewer]);
						for (int i=0; i < myJournals.get(currentJournal).assReviewers.length; i++) {
							if (myJournals.get(currentJournal).assReviewers[i] == "") {
								myJournals.get(currentJournal).assReviewers[i] = myJournals.get(currentJournal).getNomReviewers()[selectedReqReviewer];
							}
						}
						
						//myJournals.get(currentJournal).assReviewers.add(myJournals.get(currentJournal).getNomReviewers().get(selectedReqReviewer));
					}
				}
				//clears lists from selections
					paperList.deselectAll();
					nomReviewerList.deselectAll();
					reqReviewerList.deselectAll();
					assReviewerList.deselectAll();
			}
		});
		btnAddReviewer.setText("Add Reviewer");
		btnAddReviewer.setBounds(50, 261, 182, 25);
		
		/**
		 * 
		 */
		Button btnCheckPaper = new Button(NewSubComp, SWT.NONE);
		btnCheckPaper.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedJournal = paperList.getSelectionIndex();
				if (selectedJournal != -1) {
					reqReviewerList.removeAll();
					nomReviewerList.removeAll();
					assReviewerList.removeAll();
					summaryText.setText(myJournals.get(selectedJournal).getSummary());
					if (selectedJournal == 0) {
						//for(int i = 0; i < 3; i++){
							currentJournal = 0;
						//if (myJournals.get(currentJournal).assReviewers.length != 0) {
							for(int j = 0; j < 3; j++){
								assReviewerList.add(myJournals.get(currentJournal).getAssReviewers()[j], j);
							}
						//}
						//if (!myJournals.get(currentJournal).getReqReviewers().isEmpty()) {
							for(int j = 0; j < 3; j++){
								reqReviewerList.add(myJournals.get(currentJournal).getReqReviewers()[j], j);
							}
						//}
						//if (!myJournals.get(currentJournal).getNomReviewers().isEmpty()) {
							for(int j = 0; j < 3; j++){
								nomReviewerList.add(myJournals.get(currentJournal).getNomReviewers()[j], j);
							}
						//}
						///}
					}
					if (selectedJournal == 1) {
						for(int i = 0; i < 3; i++){
							currentJournal = 1;
						}
						//if (!myJournals.get(currentJournal).getAssReviewers().isEmpty()) {
							for(int i = 0; i < 3; i++){
								assReviewerList.add(myJournals.get(currentJournal).getAssReviewers()[i]);
							}
						//}
						//if (!myJournals.get(currentJournal).getReqReviewers().isEmpty()) {
							for(int i = 0; i < 3; i++){
								reqReviewerList.add(myJournals.get(currentJournal).getReqReviewers()[i]);
							}
						//}
						//if (!myJournals.get(currentJournal).getNomReviewers().isEmpty()) {
							for(int i = 0; i < 3; i++){
								nomReviewerList.add(myJournals.get(currentJournal).getNomReviewers()[i]);
							}
						//}
					}
					if (selectedJournal == 2) {
						for(int i = 0; i < 3; i++){
							currentJournal = 2;
						}
						//if (!myJournals.get(currentJournal).getAssReviewers().isEmpty()) {
							for(int i = 0; i < 3; i++){
								assReviewerList.add(myJournals.get(currentJournal).getAssReviewers()[i]);
							}
						//}
						//if (!myJournals.get(currentJournal).getReqReviewers().isEmpty()) {
							for(int i = 0; i < 3; i++){
								reqReviewerList.add(myJournals.get(currentJournal).getReqReviewers()[i]);
							}
						//}
						//if (!myJournals.get(currentJournal).getNomReviewers().isEmpty()) {
							for(int i = 0; i < 3; i++){
								nomReviewerList.add(myJournals.get(currentJournal).getNomReviewers()[i]);
							}
						//}
						
					}
					if (selectedJournal == 3) {
						for(int i = 0; i < 3; i++){
							currentJournal = 3;
						}
						//if (!myJournals.get(currentJournal).getAssReviewers().isEmpty()) {
							for(int i = 0; i < 3; i++){
								assReviewerList.add(myJournals.get(currentJournal).getAssReviewers()[i]);
							}
						//}
						//if (!myJournals.get(currentJournal).getReqReviewers().isEmpty()) {
							for(int i = 0; i < 3; i++){
								reqReviewerList.add(myJournals.get(currentJournal).getReqReviewers()[i]);
							}
						//}
						//if (!myJournals.get(currentJournal).getNomReviewers().isEmpty()) {
							for(int i = 0; i < 3; i++){
								nomReviewerList.add(myJournals.get(currentJournal).getNomReviewers()[i]);
							}
						//}
					}
					if (myJournals.get(currentJournal).getAssReviewers()[0] == "") {
						if (myJournals.get(currentJournal).getAssReviewers()[1] == "") {
							if (myJournals.get(currentJournal).getAssReviewers()[2] == "") {
														assReviewerList.removeAll();
							}
						}

					}
				}
				paperList.deselectAll();
				nomReviewerList.deselectAll();
				reqReviewerList.deselectAll();
				assReviewerList.deselectAll();
			}
		});
		btnCheckPaper.setText("Check Paper");
		btnCheckPaper.setBounds(164, 43, 120, 25);
		
//##########################################################################################################################	
		
		
		TabItem editor_tab2 = new TabItem(tabFolder_1, SWT.NONE);
		editor_tab2.setText("Reviewed Papers");
		
		Composite ReviewedPapersComp = new Composite(tabFolder_1, SWT.NONE);
		ReviewedPapersComp.setForeground(SWTResourceManager.getColor(255, 255, 255));
		ReviewedPapersComp.setBackground(SWTResourceManager.getColor(255, 255, 255));
		editor_tab2.setControl(ReviewedPapersComp);
		
		Label lblPapers_1 = new Label(ReviewedPapersComp, SWT.NONE);
		lblPapers_1.setText("Papers:");
		lblPapers_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lblPapers_1.setBounds(10, 10, 55, 15);
		
		Button btnAcceptPaper_2 = new Button(ReviewedPapersComp, SWT.NONE);
		btnAcceptPaper_2.setText("Accept Paper");
		btnAcceptPaper_2.setBounds(50, 261, 182, 25);
		
		Button btnDenyPaper_2 = new Button(ReviewedPapersComp, SWT.NONE);
		btnDenyPaper_2.setText("Deny Paper");
		btnDenyPaper_2.setBounds(50, 309, 182, 25);
		/*
		Button btnDownloadPaper_2 = new Button(ReviewedPapersComp, SWT.NONE);
		btnDownloadPaper_2.setText("Download Paper");
		btnDownloadPaper_2.setBounds(164, 87, 120, 25);
		*/
		List listPaper2 = new List(ReviewedPapersComp, SWT.BORDER);
		listPaper2.setItems(new String[] {});
		for(int i = 0; i < myJournals.size(); i++){
			listPaper2.add(myJournals.get(i).getJournalTitle());
		}
		listPaper2.setBounds(10, 31, 150, 209);
		
		List listReviews = new List(ReviewedPapersComp, SWT.BORDER);
		listReviews.setItems(new String[] {"Reviewer1: Minor changes ", "Reviewer2: Major Changes", "Reviewer3: No changes"});
		listReviews.setBounds(290, 241, 393, 120);
		
		Label lblPapers_1_1 = new Label(ReviewedPapersComp, SWT.NONE);
		lblPapers_1_1.setText("Reviews:");
		lblPapers_1_1.setBackground(SWTResourceManager.getColor(255, 255, 255));
		lblPapers_1_1.setBounds(290, 220, 55, 15);
		
		Text summaryText2 = new Text(ReviewedPapersComp, SWT.BORDER);
		summaryText2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		summaryText2.setText("");
		summaryText2.setEditable(false);
		summaryText2.setBounds(290, 31, 391, 120);
		
		Button btnCheckPaper_2 = new Button(ReviewedPapersComp, SWT.NONE);
		btnCheckPaper_2.setText("Check Paper");
		btnCheckPaper_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedJournal2 = listPaper2.getSelectionIndex();
				if (selectedJournal2 != -1) {
					summaryText2.setText(myJournals.get(selectedJournal2).getSummary());
				}
			}
		});
		btnCheckPaper_2.setBounds(164, 43, 120, 25);
		
	}
}