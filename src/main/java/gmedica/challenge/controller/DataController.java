package gmedica.challenge.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import gmedica.challenge.datamanager.UploadDownloadData;
import gmedica.challenge.service.DataService;

@RestController
@RequestMapping("/data")
public class DataController {
	@Autowired
	private DataService dataService;
	
	@PostMapping
	public ResponseEntity<String> uploadSingleCSVFile(@RequestParam("csvfile") MultipartFile csvfile) {
		String response;
		// Checking the upload-file's name before processing
		if (csvfile.getOriginalFilename().isEmpty()) {
			response = "No selected file to upload";
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
		
		// checking the upload file's type is CSV or NOT
		if(!UploadDownloadData.isCSVFile(csvfile)) {
			response = "Error: this is not a CSV file";
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
		
		 
		try {
			// save file data to database
			dataService.uploadDataFromCsvFile(csvfile.getInputStream());
			response = "Upload File successfully!";
		} catch (Exception e) {
			response = "ERROR: " + csvfile.getOriginalFilename() + " " + e.getMessage();
			return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
		}
	
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/download")
	public void downloadFile(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=alldata.csv");
		dataService.getCsvFile(response.getWriter());
	}
	
	@GetMapping("/download/{data}")
	public void downloadFileData(@PathVariable String data, HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename="+data+".csv");
		dataService.getValuesByCode(data, response.getWriter());
	}
	
	@DeleteMapping
	public void deleteAll() {
		dataService.deleteAll();
	}

}
