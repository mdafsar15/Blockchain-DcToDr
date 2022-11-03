package com.genx.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.genx.dto.Constants;
import com.genx.model.Birth;
import com.genx.repository.MicroRepository;
import com.genx.service.MicroService;
import com.genx.util.JsonUtill;

@Service
public class MicroServiceImpl implements MicroService {

	private final Logger logger = LoggerFactory.getLogger(MicroServiceImpl.class);

	@Autowired
	MicroRepository microRepo;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public boolean insertBirthRecord(Birth birth) throws Exception {
		String jsonPayload = JsonUtill.getJsonString(birth);

		if (birth != null) {

			Birth json = JsonUtill.getObjectFromJson(jsonPayload, Birth.class);
			logger.info("  === getObjectFromJson =====" + json);

			microRepo.save(json);
		}
		return true;
	}

	private ResponseEntity<Object> callBlcForReplication(Object object) {
		logger.info("INSIDE BLC FUNCTION REQUEST ==" + object);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> httpEntity = new HttpEntity<>(object, headers);
		ResponseEntity<Object> response = restTemplate().exchange(Constants.REGISTER_BLOCKCHAIN_MICROSERVICES_URL,
				HttpMethod.POST, httpEntity, Object.class);
		logger.info("Remote Transactions: " + response.getStatusCode() + " Body: " + response.getBody());

		return response;
	}

}
