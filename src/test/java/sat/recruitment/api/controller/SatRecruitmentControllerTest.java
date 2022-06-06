package sat.recruitment.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import sat.recruitment.api.resource.User;
import sat.recruitment.api.service.SatRecruitmentService;
import sat.recruitment.api.util.SatTestMockUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SatRecruitmentController.class)
public class SatRecruitmentControllerTest {
	
	private static final String ENDPOINT = "/api/v1/create-user";
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SatRecruitmentService satRecruitmentService;
	
    private ObjectMapper objectMapper;
    
    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

	@Test
	public void givenMessageBody_whenCreateUser_thenReturnResourceCreated() throws Exception {
		User request = SatTestMockUtil.getNewUserRequestMock();

		mockMvc.perform(post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void givenNameOrEmailMissing_whenCreateUser_thenReturnBadRequest() throws Exception {
		User request = SatTestMockUtil.getNewUserRequestMock();
		request.setName(null);
		request.setEmail(null);
		mockMvc.perform(post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest());
	}
}
