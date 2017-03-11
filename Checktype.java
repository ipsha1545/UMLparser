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
									
									int f = fieldDec.getModifiers();
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

								}
                
						 }		
                        
                           }
            
         }    
