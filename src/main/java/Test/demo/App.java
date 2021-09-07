package Test.demo;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
import java.util.regex.*;
import org.apache.commons.lang3.*;
public class App 
{
	//Fonction to calculate addition of numbers
	public static int add(String numbers) {
		int sum = 0;
		//delimiters Array of string that will contain all delimiters
		String [] delimiters;
		//Regex string of delimiters (it contain default delimiters) 
		String delimiter=",|\n";
		// checking if we have costumed delimiters
		if(numbers.length()>2 && numbers.substring(0,2).equals("//")) {
			//getting delimiters between [ ] ( adding org.apache.commons.lang3 to library is needed to use this method)
			delimiters =StringUtils.substringsBetween(numbers,"[","]");
			
			
			
			if(delimiters!=null) {
				
				//Loop to ignore operator used by the user as delimiter by adding \ before operator
				for(int i=0;i<delimiters.length;i++) {
					//checking if the delimiter is an accuracy (for example *****)
							List notValidDelim= Arrays.asList("^","$",".","*","+","?","&","|","\\");
							String modifiedDelimiter="";
							for (int j=0 ;j<delimiters[i].length();j++) {
								if (notValidDelim.contains(""+delimiters[i].charAt(j))) {
									modifiedDelimiter+= "\\"+delimiters[i].charAt(j);
									
								}
								else
									modifiedDelimiter+= delimiters[i].charAt(j);
								
							}
							delimiters[i]=modifiedDelimiter;
					}
				// creating regex
				delimiter = String.join("|",delimiters);
				System.out.println("delim"+ String.join("|",delimiters));
				}
			else
				//creating regex in case o default delimiter
				delimiter=""+numbers.charAt(2);
			//getting all numbers
			numbers=numbers.substring(numbers.indexOf("\n"));
			}
		//cleaning white spaces from numbers
		numbers = numbers.trim();
		//if it contains nothing we return 0
		if (numbers.length()==0)
			return sum;
		
		// Storing all numbers in array by splitting using regex
		String[] numbersList = numbers.split(delimiter);
	
		try {
			
			String exceptionMsg ="Negative not allowed.\n Negatives passed:";
			// bool to check if we have negative number
			boolean negative=false;
			//calculating the sum of all numbers
			
			for(int i=0 ;i<numbersList.length;i++) {
			
				int number=Integer.parseInt(numbersList[i]);
				
				//checking if number is negative
				if(number<0) {
					negative=true;
					// adding the number to the exception msg
					exceptionMsg+="\t"+number;
					}
				//ignoring numbers upper than 1000
				else if(number<=1000)
					sum+=number;
				}
			//if negative is true an exception is thrown
			if(negative) 
				throw new ArithmeticException(exceptionMsg);
				

			
		}catch(Exception e) {
			
			e.printStackTrace();
			return 0;
		}
		
		return sum;
		
	}
    public static void main( String[] args )
    {
    	//step1
    	System.out.println( add("") );
    	System.out.println( add("1") );
    	System.out.println( add("2,3") );
    	//step2
    	System.out.println( add("2,3,5") );
    	//step 3
        System.out.println( add("1\n2,3") );
      	//step 4
        System.out.println( add("//;\n1;3") );
        //step 5
        System.out.println( add("//;\n-1;3") );
        //step 6
        System.out.println( add("//;\n1001;1") );
        //step 7
        System.out.println( add("//[+++]\n1+++2") );
        //step 8 & 9
        System.out.println( add("//[***][+++]\n11***1+++1") );
    }
}
