package keywords;

import edu.jsu.mcis.*;
import org.json.simple.parser.*;

public class ConverterKeywords {
    
    public String convertToJson(String csv) {
		String jsonString;
		Converter csvToJsonConverter = new Converter();
		jsonString = csvToJsonConverter.csvToJson(csv);
		
        return jsonString;
    }
    
    public String convertToCsv(String json) {
		String csvString;
		Converter 	JSONToCsvConverter = new Converter();
		csvString = JSONToCsvConverter.jsonToCsv(json);
		
        return csvString;
    }
    
    public boolean jsonStringsAreEqual(String s, String t) {
        return false;
    }
}