
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.VoidType;

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
	private ArrayList<String> constructParamList = new ArrayList<String>();
	private ConcurrentHashMap<String,String> usesMap = new ConcurrentHashMap<String,String>();
	boolean flag = true;
	private String className = "";
	private String[] primitives = {"byte","short","int","long","float","double","boolean","char",
			"Byte","Short","Integer","Long","Float","Double","Boolean","Char"};
	private ConcurrentHashMap<String,String> multiplicityMap = new ConcurrentHashMap<String,String>();
	
	

	public static void main(String[] args) {
		String path = "/Users/ipshamohanty/Desktop/testcase1";
		String a = "/Users/ipshamohanty/Desktop/testcase1/testcase123out.png";
		
		foldertypefile = new File(path);
		//Creates a new File instance by converting the given pathname string into an abstract pathname.
		
		listOfFiles=foldertypefile.listFiles(new FilenameFilter() {
			//Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname that satisfy the specified filter.
			//Returns the array of strings naming these files i.e A.java,B.java,C.java etc

		    public boolean accept(File dir, String name) {
		    	
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
							}
							List<TypeDeclaration> bodyTypes = cu.getTypes();
							System.out.println("bodyTypes-"+ bodyTypes);
							for(TypeDeclaration bodyType : bodyTypes)
							{   
								System.out.println("bodyType-"+ bodyType);
								
								List<BodyDeclaration> bodyDec = bodyType.getMembers();
								System.out.println(bodyType.getMembers());
								
								System.out.println(bodyDec.size());
								if(!bodyDec.isEmpty() && bodyDec.size()>0)
								{
									String accessModifier = "";
									for(BodyDeclaration body : bodyDec)
									{
										if(body instanceof FieldDeclaration)
										{
											String primitiveType="";
											FieldDeclaration fieldDec = (FieldDeclaration)body;
											// checking access modifiers 
											int fieldDecModifiers = fieldDec.getModifiers();
											boolean success = false;

											switch(fieldDecModifiers)
											{
											case ModifierSet.PRIVATE:
												accessModifier = "-";
												success = true;
												break;
											case ModifierSet.PUBLIC:
												accessModifier = "+";
												success = true;
												break;
											
											}
											
											// end checking access modifiers
											if(success)
											{
												boolean enterVariable = true;

												//System.out.println("Inside success ");
												
												List<Node> fieldChildNodes = fieldDec.getChildrenNodes();
												
												for(Node fieldNode :fieldChildNodes)
												{
													if(fieldNode instanceof ReferenceType)
													{
														String refType = ((ReferenceType) fieldNode).getType().toString();
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
																// logic for checking multiplicity
																if(checkForMultiplicity(refType,className))
																{
																	enterVariable = false;
																	foundCollection = true;
																	break;
																}
																
															}
														}
													}
													else if(fieldNode instanceof PrimitiveType)
													{
														PrimitiveType pType = (PrimitiveType)fieldNode;
														primitiveType = pType.toString();
													}
													if(fieldNode instanceof VariableDeclarator && enterVariable)
													{
														VariableDeclarator variableDec = (VariableDeclarator)fieldNode;
														//System.out.println(className + variableDeclarator);
														variableList.add(accessModifier+variableDec.toString()+":"+primitiveType);
													}
												}
											}
											
										}
										
										else if(body instanceof MethodDeclaration)
										{
											if(flag==true){
											String tempMethodParam = "";
											MethodDeclaration method = (MethodDeclaration)body;
											String methodAccessModifier = "";
											String methodReferenceType = "";
											String methodName = "";
											int methodModifier = method.getModifiers();
											boolean success = false;

											switch(methodModifier)
											{
											case ModifierSet.PUBLIC:
												methodAccessModifier = "+";
												success = true;
												break;
											case ModifierSet.PUBLIC+ModifierSet.STATIC:
												methodAccessModifier = "+";
												success = true;
												break;
											}
											
											if(success)
											{
												List<Node> methodChildNodes = method.getChildrenNodes();

												for(Node methodChildNode : methodChildNodes)
												{
													if(methodChildNode instanceof ReferenceType)
													{
														ReferenceType referenceMethod = (ReferenceType)methodChildNode;

														methodReferenceType = referenceMethod.getType().toString();
													}
													else if(methodChildNode instanceof VoidType)
													{
														methodReferenceType = "void";
													}
												}

												methodName = method.getName();
												
												List<Parameter> methodParams = method.getParameters();

												methodParamList = new ArrayList<String>();
												
												if(methodParams.size() > 0)
												{
													tempMethodParam += "(";
													for(Parameter param : methodParams)
													{
														List<Node> paramChildNodes = param.getChildrenNodes();

														String methodParamReferenceType="", variable="";
														for(Node paramChild : paramChildNodes)
														{
															if(paramChild instanceof ReferenceType)
															{
																ReferenceType r = (ReferenceType)paramChild;
																methodParamReferenceType = r.getType().toString();
																//System.out.println(className+" methodParamReferenceType "+methodParamReferenceType);
																// do uses operation
																ChechDependency cd = new ChechDependency(className,method);
																usesMap=cd.checkDependency(interfaceList);
																
															}
															else if(paramChild instanceof VariableDeclaratorId)
															{
																VariableDeclaratorId v = (VariableDeclaratorId)paramChild;
																variable = v.getName().toString();
															}
															
														}

														methodParamList.add(variable+":"+methodParamReferenceType);
													}
													
													for(int i=0; i<methodParamList.size() ; i++)
													{
														if(i != methodParamList.size()-1)
														{
															tempMethodParam += methodParamList.get(i)+",";
														}
														else
														{
															tempMethodParam += methodParamList.get(i);
														}
													}
													tempMethodParam += ")";
												}
												else
												{
													tempMethodParam = "()";
												}
												if(methodName.startsWith("get")||methodName.startsWith("set"))
												{
													
												}
												else
												{
													methodList.add(methodAccessModifier+methodName+tempMethodParam+":"+methodReferenceType);
												}
											}
										}
										}
										else if(body instanceof ConstructorDeclaration)
										{
											String tempConstructParam="";
											ConstructorDeclaration construct = (ConstructorDeclaration)body;
											List<Node> constructNodes = construct.getChildrenNodes();
											
											String constructAccessModifier = "";
											String constrctName = "";
											
											int constructModifier = construct.getModifiers();
											boolean success = false;

											switch(constructModifier)
											{
											case ModifierSet.PUBLIC:
												constructAccessModifier = "+";
												success = true;
												break;
											}
											constrctName = construct.getName();
											List<Parameter> constructParams = construct.getParameters();
											constructParamList = new ArrayList<String>();
											if(constructParams.size()>0)
											{
												tempConstructParam+="(";
												for(Parameter param : constructParams)
												{
													List<Node> paramChildNodes = param.getChildrenNodes();
													String conParamReferenceType="", variable="";
													for(Node paramChild : paramChildNodes)
													{
														if(paramChild instanceof ReferenceType)
														{
															ReferenceType r = (ReferenceType)paramChild;
															conParamReferenceType = r.getType().toString();
															//System.out.println(className+" methodParamReferenceType "+methodParamReferenceType);
															// do uses operation
															ChechDependency cd = new ChechDependency(className,construct);
															usesMap=cd.checkCDependency(interfaceList);
															
														}
														else if(paramChild instanceof VariableDeclaratorId)
														{
															VariableDeclaratorId v = (VariableDeclaratorId)paramChild;
															variable = v.getName().toString();
														}
														
													}

													constructParamList.add(variable+":"+conParamReferenceType);
												}
												
												for(int i=0; i<constructParamList.size() ; i++)
												{
													if(i != constructParamList.size()-1)
													{
														tempConstructParam += constructParamList.get(i)+",";
													}
													else
													{
														tempConstructParam += constructParamList.get(i);
													}
												}
												tempConstructParam += ")";
											}
											else
											{
												tempConstructParam = "()";
											}
											constructList.add(constructAccessModifier+constrctName+tempConstructParam);
										}
										
									}
								}
							}
							
				           if(variableList.size() > 0)
					{
						bodyURL.append("|");
						for(int i=0 ; i<variableList.size() ; i++)
						{
							if(i != variableList.size()-1)
								bodyURL.append(variableList.get(i)+";");
							else
								bodyURL.append(variableList.get(i));
						     }

					     }
							
							if(methodList.size() > 0)
					{
						bodyURL.append("|");
						for(int i=0 ; i<methodList.size() ; i++)
						{
							if(i != methodList.size()-1)
								bodyURL.append(methodList.get(i)+";");
							else
								bodyURL.append(methodList.get(i)+";");
						}
					}
				        if(constructList.size() > 0)
					{
						if(methodList.isEmpty() && methodList.size()==0)
						{
							bodyURL.append("|");
							for(int i=0 ; i<constructList.size() ; i++)
							{
								if(i != constructList.size()-1)
									bodyURL.append(constructList.get(i)+";");
								else
									bodyURL.append(constructList.get(i));
							}
						    }
							
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
									
				  }
			}
				return "";
			
		}

	private boolean checkForMultiplicity(String referenceClass, String className2) {
		String relationValue="";
		String relationKey="";
		String reverseRelationKey="";
		
		if(referenceClass.contains("Collection"))
		{
			referenceClass = referenceClass.toString().replace("Collection<","");
			referenceClass = referenceClass.replace(">", "");
			
			//System.out.println("strClass "+referenceClass);
			relationValue="1-*";
			relationKey=className+"~"+referenceClass;
			reverseRelationKey=referenceClass+"~"+className;
			/*System.out.println("relationKey "+relationKey+" - - "+"reverseRelationKey "+reverseRelationKey );
			System.out.println(multiplicityMap.size());*/
			if(multiplicityMap.isEmpty())
			{
				multiplicityMap.put(relationKey, relationValue);
			}
			// check key already exists
			else if(!multiplicityMap.isEmpty() && multiplicityMap.size()>0)
			{
				if(!multiplicityMap.containsKey(relationKey) && !multiplicityMap.containsKey(reverseRelationKey))
				{
					for(String keys : multiplicityMap.keySet())
					{
						String tempKey = keys;
					//	System.out.println(" tempKey "+tempKey+" reverseRelationKey "+reverseRelationKey +" "+tempKey.equals(reverseRelationKey));
						if(tempKey.equals(reverseRelationKey)==false)
						{
							multiplicityMap.put(relationKey, relationValue);
							
						}
					}
				}
			}
			
			System.out.println(multiplicityMap);
			return true;
			
		}
		else
		{
			relationValue="1-1";
			relationKey=className+"~"+referenceClass;
			reverseRelationKey=referenceClass+"~"+className;
			if(multiplicityMap.isEmpty())
			{
				multiplicityMap.put(relationKey, relationValue);
				
			}
			// check key already exists
			else if(!multiplicityMap.isEmpty() && multiplicityMap.size()>0)
			{
				if(!multiplicityMap.containsKey(relationKey) && !multiplicityMap.containsKey(reverseRelationKey))
				{
					for(String keys : multiplicityMap.keySet())
					{
						String tempKey = keys;
						//System.out.println(" tempKey "+tempKey+" reverseRelationKey "+reverseRelationKey +" "+tempKey.equals(reverseRelationKey));
						if(tempKey.equals(reverseRelationKey)==false)
						{
							multiplicityMap.put(relationKey, relationValue);
							
						}
					}
				}
			}
			
			System.out.println(multiplicityMap);
			return true;
			
		}
	   }
		
	}
		

			
