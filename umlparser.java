
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;



public class UMLParsertesting {
	
	private HashMap<String,List<ClassOrInterfaceType>> classInterfaceHashMap = new HashMap<String,List<ClassOrInterfaceType>>();
	private static File foldertypefile = null;
	private static File[] listOfFiles = null;
	private StringBuilder bodyURL = new StringBuilder();
	private ArrayList<String> variableList = new ArrayList<String>();
	private ArrayList<String> methodParamList = new ArrayList<String>();
	private ArrayList<String> methodList = new ArrayList<String>();
	private ArrayList<String> constructList = new ArrayList<String>();
	private ArrayList<String> interfaceList = new ArrayList<String>();
	boolean flag = true;
	private String className = "";
	
	

	public static void main(String[] args) {
		String path = "/Users/ipshamohanty/Desktop/testcase1";
		String a = "/Users/ipshamohanty/Desktop/testcase1/testcase123out.png";
		
		foldertypefile = new File(path);
		//Creates a new File instance by converting the given pathname string into an abstract pathname.
		
		listOfFiles=foldertypefile.listFiles(new FilenameFilter() {
			//Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname that satisfy the specified filter.
			//Returns the array of strings naming these files i.e A.java,B.java,C.java etc

		    public boolean accept(File dir, String name) {
		    	//System.out.println("name "+name.toLowerCase().endsWith(".java"));
		    	/*name false
                  name true
				  name true
				  name true
				  name true
				  name false
				  name false
                 for testcase1
		    	 * 
		    	 *  
		    	 */
		        return name.toLowerCase().endsWith(".java");
		        
		    }
		});
		

		UMLParsertesting obj = new UMLParsertesting();
		obj.umlParser(a);
		
		

	}



	
		public String umlParser(String image)
		{
			
			
				for(File file : listOfFiles)
				{
					if (file.isFile())
					{
						if(bodyURL.length() > 0 && (bodyURL.charAt(bodyURL.length()-1) != ','))
						{
							bodyURL.append(",");
						}
						variableList = new ArrayList<String>();
						methodList = new ArrayList<String>();
						constructList = new ArrayList<String>();
						bodyURL.append("[");
						
						
						System.out.println("Printing file" + file.getName().split("\\.")[1]);
						System.out.println("hiii    myout " + foldertypefile.toString()+ "     /" + file.getName().split("\\.")[0]);
						//gives A
						FileInputStream inputStream = null;
						try{
							 inputStream = new FileInputStream(foldertypefile.toString()+ "/"+file.getName().split("\\.")[0]+ ".java");
							 System.out.println(inputStream);
						}
						catch (Exception e) {
							System.out.println("exception!");
						}
						
						CompilationUnit cu;
						try {
							cu = JavaParser.parse(inputStream);
							
							System.out.println("cu-start " + cu.toString() + " cu-end");
							List<Node> childrenNodes = cu.getChildrenNodes();
							System.out.println("childrenNodes-start"+ childrenNodes + "childrenNodes-end");
							
							int count = 0;
							for(Node child : childrenNodes)
							{
								count++;
								System.out.println("child "+count + child+ "child"+ count);
								if(child instanceof ClassOrInterfaceDeclaration)
								{
									ClassOrInterfaceDeclaration classInterfaceDec = (ClassOrInterfaceDeclaration)child;
									System.out.println("classInterfaceDec " + classInterfaceDec);
									
									// create a list with interfaces as entries
									
									if(classInterfaceDec.isInterface())
									{
										interfaceList.add(classInterfaceDec.getName());
										bodyURL.append("<<Interface>>;");
										bodyURL.append(classInterfaceDec.getName());
										bodyURL.append("");
										flag=false;
										continue;
									}
									flag=true;
									bodyURL.append(classInterfaceDec.getName());
									className = classInterfaceDec.getName();
									// create HashMaps to map classes with interfaces and parent classes for implements and extends relation
									
									List<ClassOrInterfaceType> implementsList = classInterfaceDec.getImplements();
									if(implementsList!=null)
									{
										classInterfaceHashMap.put(classInterfaceDec.getName(), implementsList);
									}
									
									List<ClassOrInterfaceType> extendsList = classInterfaceDec.getExtends();
									if(extendsList!=null)
									{
										classInterfaceHashMap.put(classInterfaceDec.getName(), extendsList);
									}
									
								}
							}// en
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//This class represents the entire compilation unit. Each java file denotes a compilation unit.
						
				}
			}
				return "jiiijij";
			
		}
		
	}
		

			
