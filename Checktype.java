public class Checktype{
	
boolean istrue = true;	
	
public void checktype(){
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
														// logic for checking multiplicity
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
  								}

						     }
                
				           }		
			           }
                           }
                   }
         }    
