package ma.smartcity.smartcity.khouribgaDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CityInfosReader {
	public static List<CityInfos> readCsv(String filename) {
		List<CityInfos> cityInfosList = new ArrayList<>();

		try (FileReader reader = new FileReader(filename);
			 CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
			for (CSVRecord record : parser) {
				CityInfos cityInfos = new CityInfos();
				cityInfos.setId(Long.parseLong(record.get("id")));
				cityInfos.setCategory(record.get("category"));
				cityInfos.setDescription(record.get("description"));
				cityInfos.setImage(record.get("image"));
				cityInfos.setLocation(record.get("location"));
				cityInfos.setMail(record.get("mail"));
				cityInfos.setName(record.get("name"));
				cityInfos.setPhone(record.get("phone"));
				cityInfos.setUrl(record.get("url"));
				cityInfosList.add(cityInfos);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cityInfosList;
	}
}
