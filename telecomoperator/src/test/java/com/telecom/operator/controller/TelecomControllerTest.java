package com.telecom.operator.controller;

import com.telecom.operator.model.PhoneNumber;
import com.telecom.operator.service.TelecomService;
import com.telecom.operator.service.TelecomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes ={TelecomController.class,TelecomServiceImpl.class})
@Import({TelecomController.class,TelecomServiceImpl.class})
@AutoConfigureMockMvc
public class TelecomControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	TelecomService telecomService;

	@InjectMocks
	TelecomController telecomController;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(telecomController).build();
	}

	@Test
	public void testGetAllPhoneNumbers() throws Exception {
		List<PhoneNumber> phoneNumbers = new ArrayList<>();
		phoneNumbers.add(new PhoneNumber(1L,"988888888", false));
		when(telecomService.getAllPhoneNumbers()).thenReturn(phoneNumbers);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/v1/phones")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNo").value("988888888"));
	}


	@Test
	public void testGetPhoneNumbersByCustomer() throws Exception {
		List<PhoneNumber> phoneNumbers = new ArrayList<>();
		phoneNumbers.add(new PhoneNumber(1L,"988888888", false));
		when(telecomService.getPhoneNumberByCustomerId("2")).thenReturn(phoneNumbers);

		mockMvc.perform(get("/api/v1/phones/2"))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNo").value("988888888"));
	}

	@Test
	public void testPhoneNotFoundForCustomer() throws Exception {
		List<PhoneNumber> phoneNumbers = new ArrayList<>();
		phoneNumbers.add(new PhoneNumber(1L,"988888889", false));
		when(telecomService.getPhoneNumberByCustomerId("4")).thenReturn(phoneNumbers);

		mockMvc.perform(get("/api/v1/phones/2"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testActivatePhoneNumber() throws Exception {
		when(telecomService.activatePhoneNumber("1", "988888888")).thenReturn(true);

		mockMvc.perform(put("/api/v1/phones/1/activate/988888888"))
				.andExpect(status().isOk())
				.andExpect(content().string("Phone number 988888888 activated successfully."));
	}

	@Test
	public void testPhoneNumberNotFound() throws Exception {
		when(telecomService.activatePhoneNumber("1", "988888889")).thenReturn(true);

		mockMvc.perform(put("/api/v1/phones/1/activate/988888888"))
				.andExpect(status().isNotFound());
	}

}
