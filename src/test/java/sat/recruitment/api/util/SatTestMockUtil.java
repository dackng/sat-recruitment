package sat.recruitment.api.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import sat.recruitment.api.domain.UserType;
import sat.recruitment.api.resource.User;

public class SatTestMockUtil {

	public static User getNewUserRequestMock() {
		return new User(
			"Diego", 
			"dackng@gmail.com", 
			"Av.Fibonacci #2009", 
			"123456789", 
			UserType.NORMAL, 
			BigDecimal.valueOf(100)
		);
	}
	
	public static User getExistingUserRequestIntoFileMock() {
		return new User(
			"Juan", 
			"Juan@marmol.com", 
			"+5491154762312", 
			"Peru 2464", 
			UserType.NORMAL, 
			BigDecimal.valueOf(1234)
		);
	}
	
	public static List<User> getExistingUsersIntoFile(){
		List<User> users = new ArrayList<>();
	
		users.add(new User("Juan","Juan@marmol.com", "+5491154762312","Peru 2464"
				, UserType.NORMAL, new BigDecimal(1234)));
		users.add(new User("Franco","Franco.Perez@gmail.com", "+534645213542","Alvear y Colombres"
				, UserType.PREMIUM, new BigDecimal(112234)));
		users.add(new User("Agustina","Agustina@gmail.com", "+534645213542","Garay y Otra Calle"
				, UserType.SUPERUSER, new BigDecimal(112234)));
		return users;
	}
}
