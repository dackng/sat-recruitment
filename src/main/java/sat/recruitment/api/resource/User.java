package sat.recruitment.api.resource;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import sat.recruitment.api.domain.UserType;

public class User {
	@NotNull(message = "The name is required")
	public String name;
	
	@NotNull(message = "The email is required")
	public String email;
	
	@NotNull(message = "The address is required")
	public String address;
	
	@NotNull(message = "The phone is required")
	public String phone;
	
	public UserType userType;
	
	public BigDecimal money;
	
	public User() {
		
	}

	public User(String name, String email, String address, String phone, UserType userType, BigDecimal money) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.userType = userType;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	@Override
    public boolean equals(Object object) {
 
		if (object == null) {
            return false;
        }

        if (object.getClass() != this.getClass()) {
            return false;
        }
         
        final User other = (User) object;
        if(other.getEmail().equals(this.getEmail()) || other.getPhone().equals(this.getPhone())) {
        	return true;
        }
        if(other.getName().equals(this.getName()) && other.getAddress().equals(this.getAddress())) {
        	return true;
        }
        
        return false;
    }
}
