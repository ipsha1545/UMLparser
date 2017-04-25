import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class Helper {
	public static void getImage(String bodyURL2, String image)
	{
		try
		{
		URL url = new URL(bodyURL2.toString());
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf)))
		{
		  out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();
		FileOutputStream fos = new FileOutputStream(image);
		fos.write(response);
		fos.close();
		//System.out.println("\""+args[1]+"\" downloaded on the default folder");
		}
		catch(Exception e)
		{
			System.out.println("Provide image name with extension.");
			e.printStackTrace();
		}
	}


public static boolean checkForMultiplicity(String referenceClass,String className)
{
	ConcurrentHashMap<String,String> multiplicityMap = Java2UMLParser.getMultiplicityMap();
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
