package gmedica.challenge.datamanager;

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
import com.opencsv.bean.CsvToBeanBuilder;

import gmedica.challenge.model.Data;
import gmedica.challenge.model.DataCsv;

public class UploadDownloadData {
	
	public static List<Data> parseCsvFile(InputStream is) {
		String[] CSV_HEADER = { "source","codeListCode","code","displayValue","longDescription","fromDate","toDate","sortingPriority" };
		Reader fileReader = null;
	
		List<Data> allData = new ArrayList<Data>();
		
		try {
			fileReader = new InputStreamReader(is);
			ColumnPositionMappingStrategy<DataCsv> mappingStrategy = new ColumnPositionMappingStrategy<DataCsv>();
	
			mappingStrategy.setType(DataCsv.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
	
			List<DataCsv> beans = new CsvToBeanBuilder(fileReader).withType(DataCsv.class).build().parse();
			
			for(DataCsv d : beans) {
				Data data = new Data();
				data.setCode(d.getCode());
				data.setCodeListCode(d.getCodeListCode());
				data.setDisplayValue(d.getDisplayValue());
				data.setFromDate(d.getFromDate());
				data.setToDate(d.getToDate());
				data.setLongDescription(d.getLongDescription());
				data.setSource(d.getSource());
				if(!d.getSortingPriority().isEmpty())
					data.setSortingPriority(Integer.valueOf(d.getSortingPriority()));
				allData.add(data);
			}

			return allData;
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
		
		return allData;
	}
	
	public static void allDataToCsv(Writer writer, List<Data> allData) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("source","codeListCode","code","displayValue","longDescription","fromDate","toDate","sortingPriority"));) {
			for (Data d : allData) {
				String fromDate = "";
				String toDate = "";
				String sortingPriority = "";
				if(d.getFromDate() != null)
					fromDate = d.getFromDate().toString();
				if(d.getToDate() != null)
					toDate = d.getToDate().toString();
				if(d.getSortingPriority() != null)
					sortingPriority = d.getSortingPriority().toString();
				List<String> data = Arrays.asList(d.getSource(),
						d.getCodeListCode(), d.getCode(), d.getDisplayValue(),
						d.getLongDescription(), fromDate,
						toDate, sortingPriority);

				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}
	
	public static void allValuesToCsv(Writer writer, List<String> allData, String code) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader(code));) {
			for (String d : allData) {
				csvPrinter.printRecord(d);
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
