/**
 *  Program to parse the java source code to generate yUML url that produces class diagram 
 */


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.ModifierSet;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.body.VariableDeclaratorId;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.VoidType;

public class JavacodetoUMLDiagram {
	
	//set file path according to the args
	private static String filePath = null;// "D:/projects/UMLJavaParser/src/javaparser";
	private static File folder = null;//new File(filePath+"/"+"testcase5");
	private static File[] listOfFiles = null;//folder.listFiles();
	private StringBuilder bodyURL = new StringBuilder();
	private ArrayList<String> interfaceList = new ArrayList<String>();
	private ArrayList<String> variableList = new ArrayList<String>();
	private ArrayList<String> methodParamList = new ArrayList<String>();
	private ArrayList<String> methodList = new ArrayList<String>();
	private ArrayList<String> constructList = new ArrayList<String>(); 
	private ArrayList<String> constructParamList = new ArrayList<String>();
	private HashMap<String,List<ClassOrInterfaceType>> classInterfaceMap = new HashMap<String,List<ClassOrInterfaceType>>();
	private HashMap<String,List<ClassOrInterfaceType>> classSuperClassMap = new HashMap<String,List<ClassOrInterfaceType>>();
	private String className = "";
	private String[] primitives = {"byte","short","int","long","float","double","boolean","char",
			"Byte","Short","Integer","Long","Float","Double","Boolean","Char"};
	private static ConcurrentHashMap<String,String> multiplicityMap = new ConcurrentHashMap<String,String>();
	private ConcurrentHashMap<String,String> usesMap = new ConcurrentHashMap<String,String>();
	private ConcurrentHashMap<String,String> usessMap = new ConcurrentHashMap<String,String>();
	private String usesRelation="";
	//private boolean flag = true;
	private String usesInterface="";
	boolean flag=true;

	public static ConcurrentHashMap<String,String> getMultiplicityMap(){
		return multiplicityMap;
	}
	public static void main(String[] args) throws URISyntaxException {
		
		String inPath  = "/Users/ipshamohanty/Desktop/testcase1";
		String outPath = "/Users/ipshamohanty/Desktop/o.png";

		HelperLarge obj = new HelperLarge();
		obj.umlParser(inPath,outPath);
	}
		
		
	
}
