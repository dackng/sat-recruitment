package sat.recruitment.api.domain;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import sat.recruitment.api.resource.User;

@Component
public class FileReader {

	public List<User> getUsers(){
		List<User> users = new ArrayList<>();
		try (InputStream fstream =  getClass().getResourceAsStream("/users.txt")){

			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			while ((strLine = br.readLine()) != null) {
				String[] line = strLine.split(",");
				User user = new User();
				user.setName(line[0]);
				user.setEmail(line[1]);
				user.setPhone(line[2]);
				user.setAddress(line[3]);
				user.setUserType(UserType.valueOf(line[4].toUpperCase()));
				user.setMoney(new BigDecimal(line[5]));
				users.add(user);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
