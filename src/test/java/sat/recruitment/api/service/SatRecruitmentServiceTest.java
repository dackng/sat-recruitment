package sat.recruitment.api.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sat.recruitment.api.domain.FileReader;
import sat.recruitment.api.exception.UserDuplicatedException;
import sat.recruitment.api.resource.User;
import sat.recruitment.api.util.SatTestMockUtil;

@RunWith(SpringJUnit4ClassRunner.class)
public class SatRecruitmentServiceTest {
	
	private SatRecruitmentService satRecruitmentService;
	@MockBean
	private FileReader fileReader;
	
		
	@Before
    public void setup() {
		satRecruitmentService = new SatRecruitmentService(fileReader);
    }
	
	@Test
	public void givenUserRequest_whenCreateUser_thenReturnVoid() {
		User newUser = SatTestMockUtil.getNewUserRequestMock();
		
		given(fileReader.getUsers())
		.willReturn(SatTestMockUtil.getExistingUsersIntoFile());

		satRecruitmentService.createUser(newUser);
		Assert.assertTrue(true);
	}
	
	@Test
	public void givenExistingUserRequest_whenCreateUser_thenReturnUserDuplicated() {
		User existingUser = SatTestMockUtil.getExistingUserRequestIntoFileMock();
		
		given(fileReader.getUsers())
		.willReturn(SatTestMockUtil.getExistingUsersIntoFile());

		
		assertThatThrownBy(() -> satRecruitmentService.createUser(existingUser))
    	.isInstanceOf(UserDuplicatedException.class).hasMessage("User is duplicated");
	}
	
}
