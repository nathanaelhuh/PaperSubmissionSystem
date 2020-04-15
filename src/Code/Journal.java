package Code;

import java.util.ArrayList;

public class Journal 
{	
	public String journalTitle = "";
	public int[] status = {-1, -1, -1};					//1 means major, 2 means minor, 3 means approved???
	public String summary = "";
 
	public String comments;
	public String[] reqReviewers = new String[3]; 
	public String[] nomReviewers = new String[3];
	public String[] assReviewers = new String[3];
	
	public String getJournalTitle() {
		return journalTitle;
	}

	public void setJournalTitle(String journalTitle) {
		this.journalTitle = journalTitle;
	}

	public int[] getStatus() {
		return status;
	}

	public void setStatus(int[] s) {
		for(int i = 0; i < s.length; i++)
			this.status[i] = s[i];
	}

	public String[] getReqReviewers() {
		return reqReviewers;
	}

	public void setReqReviewers(String[] reqReviewers) {
		for(int i = 0; i < reqReviewers.length; i++)
			this.reqReviewers[i] = reqReviewers[i];
	}

	public String[] getNomReviewers() {
		return nomReviewers;
	}

	public void setNomReviewers(String[] nomReviewers) {
		for(int i = 0; i < nomReviewers.length; i++)
			this.nomReviewers[i] = nomReviewers[i];
	}

	public String[] getAssReviewers() {
		return assReviewers;
	}

	public void setAssReviewers(String[] assReviewers) {
		for(int i = 0; i < reqReviewers.length; i++)
			this.nomReviewers[i] = nomReviewers[i];
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
  	public String getComments() {
		return this.comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}

	public Journal(String journalTitle) {
		this.journalTitle = journalTitle;
	}
	//Constructor for author upload
	public Journal(String journalTitle, String summary, String[] nomReviewers) {
		this.journalTitle = journalTitle;
		this.summary = summary;
		this.setNomReviewers(nomReviewers);
	}
	public String toString()
	{
		String s = journalTitle + ",";
		for(int i = 0; i < 3; i++)
		{
			s = s.concat(Integer.toString(status[i]) + ",");
			s = s.concat(assReviewers[i]+",");
			s = s.concat(nomReviewers[i]+",");
			s = s.concat(reqReviewers[i]+",");
		}
			//MIGHT MAKE INTO For loop for Status array
		s.concat(summary);
		//TODO Add nominated reviewers and requested reviewers
		System.out.println("STRING " + s);
		return s;
		
		
	}
}