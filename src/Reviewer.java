
public class Reviewer extends User {
	//Currently too much of a pain to use user as a super class so doing it within reviewer instead
	public String name;
	
	public Reviewer(String name)
	{
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
