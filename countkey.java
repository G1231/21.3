
/*21.3 (COUNT THE KEYWORDS IN JAVA SOURCE CODE) Revise the program in Listing 21.7. 
If a keyword is in a comment or in a string, don’t count it. Pass the Java file name 
from the command line. Assume the Java source code is correct and line comments and 
paragraph comments do not overlap.*/
import java.util.*;
import java.io.*;

public class countkey {
  public static void main(String[] args) throws Exception {  
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a Java source file: ");
    String filename = input.nextLine();

    File file = new File(filename);
    if (file.exists()) {
      System.out.println("The number of keywords in " + filename 
        + " is " + countKeywords(file));
    }
    else {
      System.out.println("File " + filename + " does not exist");
    }    
  }

  public static int countKeywords(File file) throws Exception {  
    // Array of all Java keywords + true, false and null
    String[] keywordString = {"abstract", "assert", "boolean", 
      "break", "byte", "case", "catch", "char", "class", "const",
      "continue", "default", "do", "double", "else", "enum",
      "extends", "for", "final", "finally", "float", "goto",
      "if", "implements", "import", "instanceof", "int", 
      "interface", "long", "native", "new", "package", "private",
      "protected", "public", "return", "short", "static", 
      "strictfp", "super", "switch", "synchronized", "this",
      "throw", "throws", "transient", "try", "void", "volatile",
      "while", "true", "false", "null"};

    Set<String> keywordSet = 
      new HashSet<>(Arrays.asList(keywordString));
    int count = 0;    
    Scanner input = new Scanner(file);
    boolean comment = false; //preparing a boolean to check if line is in comment

    while (input.hasNext()) {//while loop for when there is a next line
      String word = input.next();
      
      if (word.startsWith("/*")) { //if in a multi-line, turns comment true then skips, then to next statement
          comment = true;
          continue; 
          } 
      if (word.endsWith("*/")) {//if not in a multi-line, turns comment false then skips, then to next statement
          comment = false;
          continue; 
          }
        
	  if (comment) {//If inside a comment, skip the loop
	      continue; 
	      }
	  if (word.startsWith("\"")) {//if starts with string, skip the loop
		  continue;
	      }
      if (word.startsWith("//")) {//I don't know why continue doesn't work for this case, but i used another method. It skips the line completely
    	  input.nextLine();
      	  }
        
      if (keywordSet.contains(word)) { // If there's a keyword, count increases
          count++;
         }
    } 

    return count;
  }
}
