package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import au.com.bytecode.opencsv.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Converter {
    /*
        Consider a CSV file like the following:
        
        ID,Total,Assignment 1,Assignment 2,Exam 1
        111278,611,146,128,337
        111352,867,227,228,412
        111373,461,96,90,275
        111305,835,220,217,398
        111399,898,226,229,443
        111160,454,77,125,252
        111276,579,130,111,338
        111241,973,236,237,500
        
        The corresponding JSON file would be as follows (note the curly braces):
        
        {
            "colHeaders":["Total","Assignment 1","Assignment 2","Exam 1"],
            "rowHeaders":["111278","111352","111373","111305","111399","111160","111276","111241"],
            "data":[[611,146,128,337],
                    [867,227,228,412],
                    [461,96,90,275],
                    [835,220,217,398],
                    [898,226,229,443],
                    [454,77,125,252],
                    [579,130,111,338],
                    [973,236,237,500]
            ]
        }  
    */
    
    @SuppressWarnings("unchecked")
    public static String csvToJson(String csvString) {
		String newJsonString = "";
		String colHeaders = "";
		String rowHeaders = "";
		String data = "";
		int count = 0;
		String[] parsedString;
		CSVParser parser = new CSVParser();
		
		try {
			
			Scanner unparsedString = new Scanner(csvString).useDelimiter("\n");
			
			while(unparsedString.hasNext()) {
				
				parsedString = parser.parseLine(unparsedString.next());
				
				if (count == 0) {
					
					colHeaders = '"' + "colHeaders" + '"' + ":" + "[";
					
					for(int i = 2; i < parsedString.length; i++) {
						colHeaders = colHeaders + "," + '"' + parsedString[i] + '"';
					}
					colHeaders = colHeaders + "]" + ",";
					count = 1;
				}
				
				else {
					
					if (rowHeaders == "") {
						rowHeaders = '"' + "rowHeaders" + '"' + ":" + "[" + '"' + parsedString[0] + '"';
					}
					
					else {
						rowHeaders = rowHeaders + "," + '"' + parsedString[0] + '"';
					}
					
					
					if (data == "") {
						data = '"' + "data" + '"' + ":" + "[" + "["+ parsedString[1];
						
						for(int i = 1; i < parsedString.length; i++) {
							data = data + "," + parsedString[i];
						}
						data = data + "]" + "," + "\n" + "\t" + "\t" + "\t";
					}
					
					else {
						
						data = data + "[" + parsedString[1];
						
						for(int i = 1; i < parsedString.length; i++) {
							data = data + "," + parsedString[i];
						}
						data = data + "]" + "," + "\n" + "\t" + "\t" + "\t";
					}
					count++;
				}
			}
			
			newJsonString = "{" + "\n" + "\t" + colHeaders + "\n" + "\t" + rowHeaders + "\n" + "\t" + data + "\n" + "\t" + "]" + "\n" + "}";
			
		}
		catch(IOException ex){ex.printStackTrace();}
		
        return newJsonString;
    }
    
    public static String jsonToCsv(String jsonString) {
        return "";
    }
}



