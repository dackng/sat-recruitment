package sat.recruitment.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sat.recruitment.api.resource.User;
import sat.recruitment.api.service.SatRecruitmentService;

@RestController
@RequestMapping(value = "/api/v1")
public class SatRecruitmentController {

	private SatRecruitmentService service;
	
	public SatRecruitmentController(SatRecruitmentService service) {
		this.service = service;
	}

	@PostMapping(value = "/create-user", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.CREATED)
	public void createUser(@Valid @RequestBody User messageBody) {
		service.createUser(messageBody);
	}
}
