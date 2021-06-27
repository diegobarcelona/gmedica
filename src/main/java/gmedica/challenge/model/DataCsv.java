package gmedica.challenge.model;

import com.opencsv.bean.CsvBindByName;

public class DataCsv {
	@CsvBindByName
	private String source;
	@CsvBindByName
	private String codeListCode;
	@CsvBindByName
	private String code;
	@CsvBindByName
	private String displayValue;
	@CsvBindByName
	private String longDescription;
	@CsvBindByName
	private String fromDate;
	@CsvBindByName
	private String toDate;
	@CsvBindByName
	private String sortingPriority;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCodeListCode() {
		return codeListCode;
	}
	public void setCodeListCode(String codeListCode) {
		this.codeListCode = codeListCode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDisplayValue() {
		return displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getSortingPriority() {
		return sortingPriority;
	}
	public void setSortingPriority(String sortingPriority) {
		this.sortingPriority = sortingPriority;
	}
	
}
