package Code;

import java.io.File;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(System.getProperty("user.dir"));
		String absolutePath = System.getProperty("user.dir") + File.separator + "reviewedJournals.txt";
		System.out.println(absolutePath);
	}

}
