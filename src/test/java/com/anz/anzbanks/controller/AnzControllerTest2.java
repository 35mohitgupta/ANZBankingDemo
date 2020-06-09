package com.anz.anzbanks.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyLong;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.anz.anzbanks.exception.AccountException;
import com.anz.anzbanks.service.ANZService;

@RunWith(SpringRunner.class)
@WebMvcTest(ANZController.class)
public class AnzControllerTest2 {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ANZService anzService;
	
	@Test
	public void notFoundTransaction() throws Exception {
		
		when(anzService.viewTransaction(anyLong())).thenThrow(new AccountException("NO_SUCH_ACCOUNT"));
		
		mockMvc.perform(get("/view-transactions/1000000000")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isNotFound())
			      .andExpect(jsonPath("$", is("NO ACCOUNT EXISITS WITH THIS ACCOUNT NO")));
		
	}
	
	@Test
	public void InvalidAccountNo() throws Exception {
		
		when(anzService.viewTransaction(anyLong())).thenThrow(new AccountException("INVALID_ACCOUNT_NO"));
		
		mockMvc.perform(get("/view-transactions/1000000000")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isBadRequest())
			      .andExpect(jsonPath("$", is("INVALID ACCOUNT NUMBER")));
		
	}
	
}
