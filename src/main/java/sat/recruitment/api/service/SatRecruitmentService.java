package sat.recruitment.api.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import sat.recruitment.api.domain.FileReader;
import sat.recruitment.api.domain.Gif;
import sat.recruitment.api.domain.UserType;
import sat.recruitment.api.exception.UserDuplicatedException;
import sat.recruitment.api.resource.User;

@Service
public class SatRecruitmentService {
	FileReader fileReader;
	
	public SatRecruitmentService(FileReader fileReader) {
		this.fileReader = fileReader;
	}

	public void createUser(User newUser) {
		if(newUser.getMoney() != null) {
			newUser.getMoney().add(getGif(newUser.getMoney(), newUser.getUserType()));
		}
		boolean isDuplicated = fileReader.getUsers().stream()
			.anyMatch(u-> u.equals(newUser));
		
		if (isDuplicated) {
			throw new UserDuplicatedException("User is duplicated");
		}
	}
		
	private BigDecimal getGif(BigDecimal money, UserType type) {
		final BigDecimal one_hundred = BigDecimal.valueOf(100);
		Gif function = (BigDecimal m, Double percentage) -> m.multiply(BigDecimal.valueOf(percentage));
		BigDecimal gif = BigDecimal.ZERO;
		switch(type) {
			case NORMAL : 
				if (one_hundred.compareTo(money) < 0)
					gif = function.calculate(money, 0.12D);
				else if (one_hundred.compareTo(money) > 0 && BigDecimal.TEN.compareTo(money) < 0)
					gif = function.calculate(money, 0.8D);
				break;
			case SUPERUSER: 
				if (one_hundred.compareTo(money) < 0) gif = function.calculate(money, 0.2D);
				break;
			case PREMIUM:
				if (one_hundred.compareTo(money) < 0) gif = function.calculate(money, 2D);
				break;
			default: break;
		}
		return gif;
	}

}
