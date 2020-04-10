package Code;

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
	public int status = -1;					//1 means major, 2 means minor, 3 means approved, 4 means rejected
	public String summary;
	public String comments;
	
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
}