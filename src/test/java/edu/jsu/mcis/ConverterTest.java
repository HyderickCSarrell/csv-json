package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConverterTest {
	
	private Converter csvJsonConverter;
    private String csvString;
    private String jsonString;

    @Before
    public void setUp() {
		csvString = "";
		
		Converter csvJsonConverter = new Converter();
		
		try(Scanner csvFile = new Scanner(new File("C:/Users/Hyderick Sarrell/Documents/GitHub/csv-json/src/test/resources/grades.csv"))) {
			while(csvFile.hasNext()) {
				csvString = csvString + csvFile.nextLine() + "\n";
			}
			csvFile.close();
		}
		catch(IOException ex){ex.printStackTrace();}
		
		jsonString = "";
		try(Scanner jsonFile = new Scanner(new File("C:/Users/Hyderick Sarrell/Documents/GitHub/csv-json/src/test/resources/grades.json"))) {
			while(jsonFile.hasNext()) {
				jsonString = jsonString + jsonFile.nextLine() + "\n";
			}
			jsonFile.close();
		}
		catch(IOException ex){ex.printStackTrace();}
		
    }
	
	
    
    @Test
    public void testConvertCSVtoJSON() {
        // You should test using the files in src/test/resources.
		String newJsonString;
		
		newJsonString = csvJsonConverter.csvToJson(csvString);
		/*try {
		PrintWriter writer = new PrintWriter("outFile.txt");
		writer.println(newJsonString);
		writer.close();
		}
		catch(IOException ex){ex.printStackTrace();}
		*/
		

		try {
			
			JSONParser parser = new JSONParser();
			Object originalStringObject = parser.parse(jsonString);
			Object newStringObject = parser.parse(newJsonString);
			
			String[] originalString = (String[])originalStringObject;
			String[] newString = (String[])newStringObject;
			
			for(int i = 0; i < originalString.length; i++) {
				if (originalString[i] != newString[i]) {
					assertTrue(false);
				}
				else {
					assertTrue(true);
				}
			}
		}
		catch(ParseException ex){ex.printStackTrace();}
		
    }

    @Test
    public void testConvertJSONtoCSV() {
        // You should test using the files in src/test/resources.
		
		String newCsvString;
		newCsvString = csvJsonConverter.jsonToCsv(jsonString);
		assertTrue(false);
    }
}







