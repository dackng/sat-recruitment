package sat.recruitment.api.domain;

import java.math.BigDecimal;

public interface Gif {
	BigDecimal calculate(BigDecimal money, Double percentage);
}
