package gmedica.challenge.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gmedica.challenge.model.Data;
import gmedica.challenge.repository.DataRepository;
import gmedica.challenge.datamanager.UploadDownloadData;

@Service
public class DataService {
	@Autowired
	private DataRepository dataRepo;
	
	public void uploadDataFromCsvFile(InputStream file) {
		try {
			
			List<Data> dataList = UploadDownloadData.parseCsvFile(file);
			
			dataRepo.saveAll(dataList);
		} catch(Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
	public void getCsvFile(Writer writer) throws IOException {
    	try {
        	List<Data> allData = (List<Data>) dataRepo.findAll();
        	
        	UploadDownloadData.allDataToCsv(writer, allData);    		
    	} catch(Exception e) {
    		throw new RuntimeException("Fail! -> Message = " + e.getMessage());
    	}
    }
	
	public void getValuesByCode(String code, Writer writer) throws IOException {
		List<String> result = new ArrayList<String>();
		List<Data> allData = dataRepo.findAll();
		for(Data d : allData) {
			try {
				if(code.toLowerCase().contentEquals("source"))
					result.add(d.getSource());
				else if(code.toLowerCase().contentEquals("codelistcode"))
					result.add(d.getCodeListCode());
				else if(code.toLowerCase().contentEquals("code"))
					result.add(d.getCode());
				else if(code.toLowerCase().contentEquals("displayvalue"))
					result.add(d.getDisplayValue());
				else if(code.toLowerCase().contentEquals("longdescription"))
					result.add(d.getLongDescription());
				else if(code.toLowerCase().contentEquals("fromdate"))
					result.add(d.getFromDate().toString());
				else if(code.toLowerCase().contentEquals("todate"))
					result.add(d.getToDate().toString());
				else if(code.toLowerCase().contentEquals("sortingpriority"))
					result.add(d.getSortingPriority().toString());
			}catch(NullPointerException ex) {
				result.add("");
			}
		}
		UploadDownloadData.allValuesToCsv(writer, result, code);
	}
	
	public void deleteAll() {
		dataRepo.deleteAll();
	}
}
