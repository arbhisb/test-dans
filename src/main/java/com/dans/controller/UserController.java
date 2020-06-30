package com.dans.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dans.model.User;
import com.dans.helper.helper;
import com.dans.model.payload.GetjoblistDetailPayload;
import com.dans.model.payload.LoginPayload;
import com.dans.model.response.GeneralResponse;
import com.dans.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@ResponseBody
	@RequestMapping(value = "api/login", method = RequestMethod.POST)
	public GeneralResponse login(String token, @Valid @RequestBody LoginPayload param, HttpServletRequest http)
			throws Exception {
		try {

			Optional<User> optUser = userRepository.findUser(param.getUsername(), param.getPassword());

			if (optUser.isPresent()) {
				GeneralResponse out = new GeneralResponse("00", "Berhasil");

				return out;
			}
			GeneralResponse out = new GeneralResponse("99", "Gagal");

			return out;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@RequestMapping(value = "api/getjoblist", method = RequestMethod.GET)
	public HashMap<String, Object> getJobList(HttpServletResponse response) throws IOException {
		HashMap<String, Object> mapout = new HashMap<String, Object>();
		String URL = "https://jobs.github.com/positions.json";
		ObjectMapper mapper = new ObjectMapper();
		try {
			String responout = helper.getTransaction(URL);
			List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
			list = mapper.readValue(responout, List.class);
			mapout.put("data", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mapout;
	}

	@ResponseBody
	@RequestMapping(value = "api/getjoblistdetail", method = RequestMethod.POST)
	public HashMap<String, Object> getJobListDetail(@Valid @RequestBody GetjoblistDetailPayload param,
			HttpServletRequest http) throws Exception {
		HashMap<String, Object> mapout = new HashMap<String, Object>();
		String URL = "https://jobs.github.com/positions/"+param.getId()+".json";
		ObjectMapper mapper = new ObjectMapper();
		try {
			String responout = helper.getTransaction(URL);
			mapout = mapper.readValue(responout, HashMap.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mapout;
	}
}
