package gmedica.challenge.datamanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import gmedica.challenge.model.Data;

public class UploadDownloadData {
	
	public static List<Data> parseCsvFile(InputStream is) {
		String[] CSV_HEADER = { "source","codeListCode","code","displayValue","longDescription","fromDate","toDate","sortingPriority" };
		Reader fileReader = null;
		CsvToBean<Data> csvToBean = null;
	
		List<Data> customers = new ArrayList<Data>();
		
		try {
			fileReader = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(fileReader);
			while(reader.ready()) {
			     String line = reader.readLine();
			     System.out.println(line);
			}
			ColumnPositionMappingStrategy<Data> mappingStrategy = new ColumnPositionMappingStrategy<Data>();
	
			mappingStrategy.setType(Data.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
	
			csvToBean = new CsvToBeanBuilder<Data>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build();
	
			customers = csvToBean.parse();
			
			return customers;
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}
		
		return customers;
	}
	
	public static void allDataToCsv(Writer writer, List<Data> allData) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("source","codeListCode","code","displayValue","longDescription","fromDate","toDate","sortingPriority"));) {
			for (Data d : allData) {
				List<String> data = Arrays.asList(d.getSource(),
						d.getCodeListCode(), d.getCode(), d.getDisplayValue(),
						d.getLongDescription(), d.getFromDate().toString(),
						d.getToDate().toString(), String.valueOf(d.getSortingPriority()));

				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}
	
	public static void allValuesToCsv(Writer writer, List<String> allData) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("source","codeListCode","code","displayValue","longDescription","fromDate","toDate","sortingPriority"));) {
			for (String d : allData) {
				List<String> data = Arrays.asList(d);

				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}

	
	public static boolean isCSVFile(MultipartFile file) {
		String extension = file.getOriginalFilename().split("\\.")[1];
		
		if(!extension.equals("csv")) {
			return false;
		}
		
		return true;
	}
	
}
