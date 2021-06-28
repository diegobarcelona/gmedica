package gmedica.challenge.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Data {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String source;
	private String codeListCode;
	private String code;
	private String displayValue;
	private String longDescription;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fromDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate toDate;
	private Integer sortingPriority;
	
	public Data() {}
	
	public Data(Long id, String source, String codeListCode, String code, String displayValue, String longDescription,
			LocalDate fromDate, LocalDate toDate, Integer sortingPriority) {
		this.id = id;
		this.source = source;
		this.codeListCode = codeListCode;
		this.code = code;
		this.displayValue = displayValue;
		this.longDescription = longDescription;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.sortingPriority = sortingPriority;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		LocalDate dateTime = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			dateTime = LocalDate.parse(fromDate, formatter);
		}
		catch(DateTimeParseException exception) {}
		this.fromDate = dateTime;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		LocalDate dateTime = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			dateTime = LocalDate.parse(toDate, formatter);
		}
		catch(DateTimeParseException exception) {}
		this.toDate = dateTime;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public Integer getSortingPriority() {
		return sortingPriority;
	}
	public void setSortingPriority(Integer sortingPriority) {
		this.sortingPriority = sortingPriority;
	}
	
}
