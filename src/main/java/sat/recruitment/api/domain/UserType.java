package sat.recruitment.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserType {
    @JsonProperty("Normal")
	NORMAL("Normal"),
	
    @JsonProperty("SuperUser")
	SUPERUSER("SuperUser"),
	
    @JsonProperty("Premium")
	PREMIUM("Premium");
	
	private String value;

	private UserType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
