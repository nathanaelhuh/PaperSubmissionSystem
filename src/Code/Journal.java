package Code;

import java.util.ArrayList;

public class Journal 
{	
	public String getJournalTitle() {
		return journalTitle;
	}

	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}

	public Reviewer[] getReviewers() {
		return reviewers;
	}

	public void setReviewers(Reviewer[] reviewers) {
		if(reviewers.length == 3)
		{
			for(int i = 0; i < 3; i++)	//TODO: Swap out for magic variable number
			{
				this.reviewers[i] = reviewers[i];
			}
		}
		else
			System.out.println("Not 3 reviewers");
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String journalTitle;
	public Reviewer reviewers[] = new Reviewer[3];
	public int status = -1;					//1 means major, 2 means minor, 3 means approved???
	public String summary;
	
	public String[] reqReviewers = new String[3];
	public String[] nomReviewers = new String[3];
	public String[] assReviewers = new String[3];
	/**
	public ArrayList<String> reqReviewers = new ArrayList<String>(); 
	public ArrayList<String> nomReviewers = new ArrayList<String>(); 
	public ArrayList<String> assReviewers = new ArrayList<String>();
	

	public ArrayList<String> getAssReviewers() {
		return assReviewers;
	}

	public void setAssReviewers(ArrayList<String> assReviewers) {
		this.assReviewers = assReviewers;
	}

	public ArrayList<String> getReqReviewers() {
		return reqReviewers;
	}

	public void setReqReviewers(ArrayList<String> reqReviewers) {
		this.reqReviewers = reqReviewers;
	}

	public ArrayList<String> getNomReviewers() {
		return nomReviewers;
	}

	public void setNomReviewers(ArrayList<String> nomReviewers) {
		this.nomReviewers = nomReviewers;
	}
	*/

	public String getSummary() {
		return summary;
	}

	public String[] getReqReviewers() {
		return reqReviewers;
	}

	public void setReqReviewers(String[] reqReviewers) {
		this.reqReviewers = reqReviewers;
	}

	public String[] getNomReviewers() {
		return nomReviewers;
	}

	public void setNomReviewers(String[] nomReviewers) {
		this.nomReviewers = nomReviewers;
	}

	public String[] getAssReviewers() {
		return assReviewers;
	}

	public void setAssReviewers(String[] assReviewers) {
		this.assReviewers = assReviewers;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Journal(String journalTitle) {
		this.journalTitle = journalTitle;
	}
}