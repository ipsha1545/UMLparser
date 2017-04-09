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
	private ConcurrentHashMap<String,String> Mapu = new ConcurrentHashMap<String,String>();
	private ConcurrentHashMap<String,String> checkmulti = new ConcurrentHashMap<String,String>();
	private HashMap<String,List<ClassOrInterfaceType>> CS = new HashMap<String,List<ClassOrInterfaceType>>();
	private String doesuse="";
	private HashMap<String,List<ClassOrInterfaceType>> CI = new HashMap<String,List<CI>>();
	private String doesuseInter="";
	
		
	
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
		
    for(File file : allfiles)
     {

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
   }
List<TypeDeclaration> Types = unitc.getTypes();
for(TypeDeclaration anyonetype : Types)
{
	List<BodyDeclaration> mydec = bodyType.getMembers();

	if(!mydec.isEmpty() && mydec.size()>0)
	{
		String a = "";
		for(BodyDeclaration body : medic)
		{
			if(body instanceof FieldDeclaration)
			{
				String p="";
				FieldDeclaration fidec = (FieldDeclaration)body;

				int f = fidec.getModifiers();
				boolean doesexist = false;


				switch(f)
				{
				case ModifierSet.PRIVATE:
					a = "-";
					doesexist= true;
					break;
				case ModifierSet.PUBLIC:
					a= "+";
					doesexist= true;
					break;

				}
										
				if(doesexist)
				{
					boolean newdoesexist = true;




					List<Node> f = fieldDec.getChildrenNodes();

					for(Node Nodes :f)
					{
						if(Nodes instanceof ReferenceType)
						{
							String refType = ((ReferenceType) Nodes).getType().toString();
							if(refType.equals("String"))
							{
								primitiveType += refType;
							}
							else
							{
								boolean foundPrimitive = false;
								boolean foundCollection = false;


								for(String primitiveRef : primitives)
								{
									if(refType.contains(primitiveRef))
									{
										primitiveType += refType+"(*)";
										foundPrimitive = true;
										break;
									}
								}
								if(!foundPrimitive)
								{
									
									if(checkForMultiplicity(refType,className))
									{
										newdoesexist = false;
										foundCollection = true;
										break;
									}

								}
							}
						}
						else if(Nodes instanceof PrimitiveType)
						{
							PrimitiveType pType = (PrimitiveType)Nodes;
							primitiveType = pType.toString();
						}
						if(Nodes instanceof VariableDeclarator && newdoesexist)
						{
							VariableDeclarator variableDec = (VariableDeclarator)Nodes;
							variableList.add(accessModifier+variableDec.toString()+":"+primitiveType);
						}
					}
			         }

			}

			else if(body instanceof MethodDeclaration)
			{
				if(istrue==true){
				String temporary = "";
				MethodDeclaration method = (MethodDeclaration)body;
				String ma = "";
				String mr = "";
				String mN = "";
				int m = method.getModifiers();
				boolean doesnotexist = false;


				switch(m)
				{
				case ModifierSet.PUBLIC:
					ma = "+";
					doesnotexist = true;
					break;
				case ModifierSet.PUBLIC+ModifierSet.STATIC:
					ma = "+";
					doesnotexist = true;
					break;
			    }
				if(doesexist)
				 {
					List<Node> mynodechild =   method.getChildrenNodes();


					for(Node eachchildnode : mynodechild)
					{
						if(eachchildnode instanceof ReferenceType)
						{
							ReferenceType rM = (ReferenceType)eachchildnode;


							methodRefType = rM.getType().toString();
						}
						else if(eachchildnode instanceof VoidType)
						{
							methodRefType = "void";
						}
					}
					Name  = method.getName();

					List<Parameter Param = method.getParameters();


					ListofParam = new ArrayList<String>();


					if(Param.size() > 0)
					{
						tempMethodParam += "(";
						for(Parameter eachparam : Param)
						{
				List<Node> Childparam = param.getChildrenNodes();
				String methodParamReferenceType="", variable="";
				
				for(Node pch : Childparam)
				{
					if(pch instanceof VariableDeclaratorId)
					{
						VariableDeclaratorId v = (VariableDeclaratorId)paramChild;
						variable = v.getName().toString();
					}
					else if(pch instanceof ReferenceType)
					{
						ReferenceType rtype = (ReferenceType)paramChild;
						methodParamReferenceType = rtype.getType().toString();
						CheckDependency cdep = new CheckDependency(className,method);
						usesMap=cdep.checkDependency(interfaceList);
				        }
					

			              }	
							
				   ListofParam.add(variable+":"+methodParamReferenceType); 

                                   }
				for(int i=0; i<ListofParam.size() ; i++)
				{
					if(i != ListofParam.size()-1)
					{
						tempConstruct += ListofParam.get(i)+",";
					}
					else
					{
						tempConstruct += ListofParam.get(i);
					}
				    }
				    tempConstruct += ")";		
				}
				else
				{
					tempConstruct = "()";
				}
				constructList.add(constructAccessModifier+constrctName+tempConstruct);		
				
				}
					
	  	               }

                             }
	                
			  }
			if(attributeList.size() > 0)
			{
			 URL.append("|");
			 for(int i=0 ; i<attributeList.size() ; i++)
			  {
				if(i != attributeList.size()-1)
					URL.append(attributeList.get(i)+";");
				else
					URL.append(attributeList.get(i));
			    }

			  }
		       if(functionList.size() > 0)
			{
			URL.append("|");
			for(int i=0 ; i<functionList.size() ; i++)
			    {
				if(i != functionList.size()-1)
					bodyURL.append(functionList.get(i)+";");
				else
					bodyURL.append(functionList.get(i)+";");
			    }
			  }
	if(constructorList.size() > 0)
	    {
	      if(methodList.isEmpty() && methodList.size()==0)
		{
		URL.append("|");
		for(int i=0 ; i<constructorList.size() ; i++)
		   {
			if(i != constructorList.size()-1)
				URL.append(constructorList.get(i)+";");
			else
				URL.append(constructorList.get(i));
		    }
		  }
		else
		  {
		     for(int i=0 ; i<constructorList.size() ; i++)
			      {
				if(i != constructorList.size()-1)
					URL.append(constconstructorListructList.get(i)+";");
				else
					URL.append(constructorList.get(i));
				}
			     }
		           }
		           URL.append("]");
	                   URL.append(",");

	                }
	              }
	             if(!Mapu.isEmpty() && Mapu.size()>0)
			{
				System.out.println(Mapu);
				for(String keys : Mapu.keySet())
				{
					String t = keys;
					URL.append("[");
					URL.append(Mapu.get(t));
					URL.append("]uses -.->[<<interface>>;");
					URL.append(t);
					URL.append("],");
				}
			     if(!Mapu.isEmpty() && Mapu.size()>0)
				{
					System.out.println(Mapu);
					for(String tags : Mapu.keySet())
					{
						String tag = tags;
						URL.append("[");
						URL.append(tag);
						URL.append("]uses -.->[<<interface>>;");
						URL.append(Mapu.get(tag));
						URL.append("],");
					}
				}
		     if(!checkmulti.isEmpty() && checkmulti.size()>0)
		        {
			for(String tags : checkmulti.keySet())
			    {
				String tag = tags;
				if(interfaceList.contains(tag.split("\\~")[1]))
				{
					doesuse += "["+tag.split("\\~")[0]+"]"+checkmulti.get(tag)+"[<<interface>>;"+tag.split("\\~")[1]+"],";
				}
				else
				{
					doesuse += "["+tag.split("\\~")[0]+"]"+checkmulti.get(tag)+"["+tag.split("\\~")[1]+"],";
				}

				System.out.println(" tag "+tag);

					
				}
			    }
		   
			for(String tag : classInterfaceMap.keySet() )
			{
				String tmp = tag;
				List<ClassOrInterfaceType> tmpList = classInterfaceMap.get(tempKey);
				if( tmpList != null)
				{
				for(int i=0;i<tmpList.size();i++)
				{
					if(!tmpList.isEmpty())
					{
						if(tmpList.get(i)!=null)
						{
							URL = URL.append("[<<interface>>;"+tmpList.get(i)+"]^-.-["+tmp+"],");
							
						}
						
					}
				   }
			      }
			 }
			     
	                URL.append(doesuse);
			URL.append(doesuseInter);
			URL.deleteCharAt(URL.length()-1);
		     
		  }
	            
	       
	      }
	    return null;
        }
}
