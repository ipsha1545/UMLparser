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
	
	
	public String parseit(String image)
	{
		
		for(File file : allfiles){
			
			if(URL.length() > 0 && (URL.charAt(URL.length()-1) != ','))
			{
				URL.append(",");
			}
			attributeList = new ArrayList<String>();
			functionList = new ArrayList<String>();
			constructorList = new ArrayList<String>();
			URL.append("[");
			FileInputStream inputStream = new FileInputStream(folder.toString()+"/"+file.getName().split("\\.")[0]+".java");
					CompilationUnit unitc = JavaParser.parse(inputStream);
					List<Node> childrenNodes = unitc.getChildrenNodes();
					for(Node eachchild : childrenNodes)
					{
						if(eachchild instanceof ClassOrInterfaceDeclaration)
						{
							ClassOrInterfaceDeclaration cIDec = (ClassOrInterfaceDeclaration)eachchild;
							
							// create a list with interfaces as entries
							
							if(cIDec.isInterface())
							{
								interfaceList.add(cIDec.getName());
								URL.append("<<Interface>>;");
								URL.append(cIDec.getName());
								URL.append("");
								flag=false;
								continue;
							}
							flag=true;
							URL.append(cIDec.getName());
							className = cIDec.getName();
							// create HashMaps to map classes with interfaces and parent classes for implements and extends relation
							
							List<ClassOrInterfaceType> implementsList = cIDec.getImplements();
							if(implementsList!=null)
							{
								classInterfaceMap.put(classInterfaceDec.getName(), implementsList);
							}
							
							List<ClassOrInterfaceType> extendsList = cIDec.getExtends();
							if(extendsList!=null)
							{
								classSuperClassMap.put(cIDec.getName(), extendsList);
							}
							
						}
					}// end of for loop for chil
		}
		return null;
	}
	
}
