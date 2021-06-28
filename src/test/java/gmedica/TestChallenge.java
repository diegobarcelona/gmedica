package gmedica;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import gmedica.challenge.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class TestChallenge {
	@Autowired
    private MockMvc mvc;
	
	private String fileContent = "\"source\",\"codeListCode\",\"code\",\"displayValue\",\"longDescription\",\"fromDate\",\"toDate\",\"sortingPriority\"\n"
			+ "\"ZIB\",\"ZIB001\",\"271636001\",\"Polsslag regelmatig\",\"The long description is necessary\",\"01-01-2019\",\"\",\"1\"\n"
			+ "\"ZIB\",\"ZIB001\",\"61086009\",\"Polsslag onregelmatig\",\"\",\"01-01-2019\",\"\",\"2\"\n"
			+ "\"ZIB\",\"ZIB001\",\"Type 1\",\"Losse harde keutels, zoals noten\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB002\",\"Type 2\",\"Als een worst, maar klonterig\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB002\",\"Type 3\",\"Als een worst, maar met barstjes aan de buitenkant\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB002\",\"Type 4\",\"Als een worst of slang, glad en zacht\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB002\",\"Type 5\",\"Zachte keutels met duidelijke randen\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB002\",\"Type 6\",\"Zachte stukjes met gehavende randen\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB002\",\"Type 7\",\"Helemaal vloeibaar\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB003\",\"307047009\",\"Rectale temperatuur\",\"\",\"01-01-2019\",\"\",\"1\"\n"
			+ "\"ZIB\",\"ZIB003\",\"415945006\",\"Orale temperatuur (onder de tong)\",\"\",\"01-01-2019\",\"\",\"2\"\n"
			+ "\"ZIB\",\"ZIB003\",\"415882003\",\"Axillaire temperatuur (onder de oksel)\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB003\",\"415929009\",\"Inguinale temperatuur (via de lies)\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB003\",\"415974002\",\"Tympanische temperatuur\",\"\",\"01-01-2019\",\"\",\"3\"\n"
			+ "\"ZIB\",\"ZIB003\",\"415922000\",\"Temporale temperatuur\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB003\",\"364246006\",\"Vaginale temperatuur\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB003\",\"698832009\",\"Blaastemperatuur\",\"\",\"01-01-2019\",\"\",\"\"\n"
			+ "\"ZIB\",\"ZIB003\",\"276885007\",\"Kern temperatuur (invasief gemeten)\",\"\",\"01-01-2019\",\"\",\"\"";
	private String content = "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority\n"
			+ "ZIB,ZIB001,271636001,Polsslag regelmatig,The long description is necessary,2019-01-01,,1\n"
			+ "ZIB,ZIB001,61086009,Polsslag onregelmatig,,2019-01-01,,2\n"
			+ "ZIB,ZIB001,Type 1,\"Losse harde keutels, zoals noten\",,2019-01-01,,\n"
			+ "ZIB,ZIB002,Type 2,\"Als een worst, maar klonterig\",,2019-01-01,,\n"
			+ "ZIB,ZIB002,Type 3,\"Als een worst, maar met barstjes aan de buitenkant\",,2019-01-01,,\n"
			+ "ZIB,ZIB002,Type 4,\"Als een worst of slang, glad en zacht\",,2019-01-01,,\n"
			+ "ZIB,ZIB002,Type 5,Zachte keutels met duidelijke randen,,2019-01-01,,\n"
			+ "ZIB,ZIB002,Type 6,Zachte stukjes met gehavende randen,,2019-01-01,,\n"
			+ "ZIB,ZIB002,Type 7,Helemaal vloeibaar,,2019-01-01,,\n"
			+ "ZIB,ZIB003,307047009,Rectale temperatuur,,2019-01-01,,1\n"
			+ "ZIB,ZIB003,415945006,Orale temperatuur (onder de tong),,2019-01-01,,2\n"
			+ "ZIB,ZIB003,415882003,Axillaire temperatuur (onder de oksel),,2019-01-01,,\n"
			+ "ZIB,ZIB003,415929009,Inguinale temperatuur (via de lies),,2019-01-01,,\n"
			+ "ZIB,ZIB003,415974002,Tympanische temperatuur,,2019-01-01,,3\n"
			+ "ZIB,ZIB003,415922000,Temporale temperatuur,,2019-01-01,,\n"
			+ "ZIB,ZIB003,364246006,Vaginale temperatuur,,2019-01-01,,\n"
			+ "ZIB,ZIB003,698832009,Blaastemperatuur,,2019-01-01,,\n"
			+ "ZIB,ZIB003,276885007,Kern temperatuur (invasief gemeten),,2019-01-01,,";
	private String contentData = "longDescription"+"Thelongdescriptionis"+"necessary";
	
	@Test
	@Before
	public void postUploadCsvFile() throws Exception{
		MockMultipartFile file = new MockMultipartFile("csvfile","test.csv", "text/csv", fileContent.getBytes());
        mvc.perform(multipart("/data")
               .file(file)
               .characterEncoding("UTF-8"))
               .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void downloadFile() throws Exception{
		MvcResult result = mvc.perform(get("/data/download")
				.accept(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		//Not Equals 300 because the file content must be bigger than 300
		Assert.assertNotEquals(300, result.getResponse().getContentAsByteArray().length);
        Assert.assertEquals("text/csv", result.getResponse().getContentType());
        Assert.assertEquals(content.replaceAll("[^a-zA-Z0-9]", ""), result.getResponse().getContentAsString().replaceAll("[^a-zA-Z0-9]", ""));
        
        result = mvc.perform(get("/data/download/longDescription")
				.accept(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        Assert.assertEquals("text/csv", result.getResponse().getContentType());
        Assert.assertEquals(contentData.replaceAll("[^a-zA-Z0-9]", ""), result.getResponse().getContentAsString().replaceAll("[^a-zA-Z0-9]", ""));
        
        mvc.perform(delete("/data"))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andReturn();
        
        result = mvc.perform(get("/data/download/longDescription")
				.accept(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
        Assert.assertEquals("text/csv", result.getResponse().getContentType());
        Assert.assertNotEquals(contentData.replaceAll("[^a-zA-Z0-9]", ""), result.getResponse().getContentAsString().replaceAll("[^a-zA-Z0-9]", ""));
	}

}
