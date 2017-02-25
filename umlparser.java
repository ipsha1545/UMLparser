 import java.io.File;
import java.util.ArrayList;


public class Umlparser {
	private String inputfilepath = null;
	private static File container = null;
	private static File[] allfiles = null;
	private StringBuilder URL = new StringBuilder();
	private ArrayList<String> attributeList = new ArrayList<String>();
	private ArrayList<String> functionList = new ArrayList<String>();
	private ArrayList<String> constructorList = new ArrayList<String>();
	
		
	
	public static void main(String[] args) {
		String inputfilename = "C:\\Users\\ipsha\\workspace\\Testingclasses\\src";
		String outputfilename = inputfilename + "\\" + "neww.png";
		container = new File(inputfilename);
		allfiles = container.listFiles();
		Umlparser myobject = new Umlparser();
		myobject.parseit(outputfilename);
	}
	
	
	public String parseit(String image){
		
		for(File file : allfiles){
			
			if(URL.length() > 0 && (URL.charAt(URL.length()-1) != ','))
			{
				URL.append(",");
			}
			attributeList = new ArrayList<String>();
			functionList = new ArrayList<String>();
			constructorList = new ArrayList<String>();
			URL.append("[");
		}
		return null;
	}
	
}
