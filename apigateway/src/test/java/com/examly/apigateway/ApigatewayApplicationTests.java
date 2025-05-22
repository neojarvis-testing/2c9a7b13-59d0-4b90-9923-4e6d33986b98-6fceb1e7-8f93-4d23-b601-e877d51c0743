package com.examly.apigateway;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ApigatewayApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private String receptionisttoken;
	private String doctortoken;
	private String nursetoken;
	private String usertoken;

	private ObjectMapper objectMapper = new ObjectMapper(); // Initialize ObjectMapper

	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	@Test
	@Order(1)
	void backend_testRegisterReceptionist() throws Exception {
		String requestBody = "{ \"userId\": 1,\"email\": \"receptionist@example.com\", \"password\": \"receptionist1234\", \"username\": \"receptionist\", \"userRole\": \"Receptionist\", \"mobileNumber\": \"1234567890\"}";
		ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
				new HttpEntity<>(requestBody, createHeaders()), String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(3)
	void backend_testRegisterDoctor() {
		String requestBody = "{\"userId\": 2,\"email\": \"doctor@example.com\", \"password\": \"doctor1234\", \"username\": \"doctor\", \"userRole\": \"Doctor\", \"mobileNumber\": \"1234567890\"}";

		ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
				new HttpEntity<>(requestBody, createHeaders()), String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(5)
	void backend_testRegisterNurse() {
		String requestBody = "{\"userId\":3,\"email\": \"nurse@example.com\", \"password\": \"nurse1234\", \"username\": \"nurse\", \"userRole\": \"Nurse\", \"mobileNumber\": \"1234567890\"}";

		ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
				new HttpEntity<>(requestBody, createHeaders()), String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(7)
	void backend_testRegisterUser() {
		String requestBody = "{\"userId\":4,\"email\": \"user@example.com\", \"password\": \"user1234\", \"username\": \"user\", \"userRole\": \"User\", \"mobileNumber\": \"1234567890\"}";

		ResponseEntity<String> response = restTemplate.postForEntity("/api/users/register",
				new HttpEntity<>(requestBody, createHeaders()), String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(2)
	void backend_testLoginReceptionist() throws Exception, JsonProcessingException {
		String requestBody = "{\"email\": \"receptionist@example.com\", \"password\": \"receptionist1234\"}";

		ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
				new HttpEntity<>(requestBody, createHeaders()), String.class);

		JsonNode responseBody = objectMapper.readTree(response.getBody());
		String token = responseBody.get("token").asText();
		receptionisttoken = token;

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(token);
	}

	@Test
	@Order(4)
	void backend_testLoginDoctor() throws Exception, JsonProcessingException {
		String requestBody = "{\"email\": \"doctor@example.com\", \"password\": \"doctor1234\"}";

		ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
				new HttpEntity<>(requestBody, createHeaders()), String.class);

		JsonNode responseBody = objectMapper.readTree(response.getBody());
		String token = responseBody.get("token").asText();
		doctortoken = token;

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(token);
	}

	@Test
	@Order(6)
	void backend_testLoginNurse() throws Exception, JsonProcessingException {
		String requestBody = "{\"email\": \"nurse@example.com\", \"password\": \"nurse1234\"}";

		ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
				new HttpEntity<>(requestBody, createHeaders()), String.class);

		JsonNode responseBody = objectMapper.readTree(response.getBody());
		String token = responseBody.get("token").asText();
		nursetoken = token;

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(token);
	}

	@Test
	@Order(8)
	void backend_testLoginUser() throws Exception, JsonProcessingException {
		String requestBody = "{\"email\": \"user@example.com\", \"password\": \"user1234\"}";

		ResponseEntity<String> response = restTemplate.postForEntity("/api/users/login",
				new HttpEntity<>(requestBody, createHeaders()), String.class);

		JsonNode responseBody = objectMapper.readTree(response.getBody());
		String token = responseBody.get("token").asText();
		usertoken = token;

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(token);
	}

	@Test
	@Order(9)
	void backend_testAddPatientByReceptionist() throws Exception {
		Assertions.assertNotNull(receptionisttoken, "petownertoken should not be null");

		String requestBody = "{"
				+ "\"patientId\": 1,"
				+ "\"firstName\": \"John\","
				+ "\"lastName\": \"Doe\","
				+ "\"dateOfBirth\": \"1985-06-15\","
				+ "\"gender\": \"Male\","
				+ "\"address\": \"123 Main St, Anytown, USA\","
				+ "\"phoneNumber\": \"+1234567890\","
				+ "\"email\": \"john.doe@example.com\","
				+ "\"insuranceDetails\": \"ABC Health Insurance, Policy #123456\","
				+ "\"status\": \"Active\""
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + receptionisttoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/patients", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(10)
	void backend_testAddPatientByNurse() throws Exception {
		Assertions.assertNotNull(nursetoken, "petownertoken should not be null");

		String requestBody = "{"
				+ "\"patientId\": 1,"
				+ "\"firstName\": \"John\","
				+ "\"lastName\": \"Doe\","
				+ "\"dateOfBirth\": \"1985-06-15\","
				+ "\"gender\": \"Male\","
				+ "\"address\": \"123 Main St, Anytown, USA\","
				+ "\"phoneNumber\": \"+1234567890\","
				+ "\"email\": \"john.doe@example.com\","
				+ "\"insuranceDetails\": \"ABC Health Insurance, Policy #123456\","
				+ "\"status\": \"Active\""
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + nursetoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/patients", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(11)
	void backend_testAddPatientByDoctor() throws Exception {
		Assertions.assertNotNull(doctortoken, "petownertoken should not be null");

		String requestBody = "{"
				+ "\"patientId\": 1,"
				+ "\"firstName\": \"John\","
				+ "\"lastName\": \"Doe\","
				+ "\"dateOfBirth\": \"1985-06-15\","
				+ "\"gender\": \"Male\","
				+ "\"address\": \"123 Main St, Anytown, USA\","
				+ "\"phoneNumber\": \"+1234567890\","
				+ "\"email\": \"john.doe@example.com\","
				+ "\"insuranceDetails\": \"ABC Health Insurance, Policy #123456\","
				+ "\"status\": \"Active\""
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + doctortoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/patients", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(12)
	void backend_testAddPatientByUser() throws Exception {
		Assertions.assertNotNull(usertoken, "User should not be null");

		String requestBody = "{"
				+ "\"patientId\": 1,"
				+ "\"firstName\": \"John\","
				+ "\"lastName\": \"Doe\","
				+ "\"dateOfBirth\": \"1985-06-15\","
				+ "\"gender\": \"Male\","
				+ "\"address\": \"123 Main St, Anytown, USA\","
				+ "\"phoneNumber\": \"+1234567890\","
				+ "\"email\": \"john.doe@example.com\","
				+ "\"insuranceDetails\": \"ABC Health Insurance, Policy #123456\","
				+ "\"status\": \"Active\""
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + usertoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/patients", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(13)
	void backend_testGetAllPatientsByDoctor() throws Exception {
		Assertions.assertNotNull(doctortoken, "Doctor token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + doctortoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/patients", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(14)
	void backend_testGetAllPatientsByReceptionist() throws Exception {
		// Ensure the ClinicManager is logged in and we have a valid token
		Assertions.assertNotNull(receptionisttoken, "Receptionist token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + receptionisttoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/patients", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(15)
	void backend_testGetAllPatientsByNurse() throws Exception {
		// Ensure the ClinicManager is logged in and we have a valid token
		Assertions.assertNotNull(nursetoken, "Nurse token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + nursetoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/patients", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(16)
	void backend_testGetAllPatientsByUser() throws Exception {
		// Ensure the ClinicManager is logged in and we have a valid token
		Assertions.assertNotNull(usertoken, "User token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + usertoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/patients", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	
@Test
@Order(17)
void backend_testGetPatientByIdAsDoctor() throws Exception {
    Assertions.assertNotNull(doctortoken, "Doctor token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + doctortoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/patients/" + 1, 
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

	
@Test
@Order(18)
void backend_testGetPatientByIdAsNurse() throws Exception {
    Assertions.assertNotNull(nursetoken, "Nurse token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + nursetoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/patients/" + 1, 
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(19)
void backend_testGetPatientByIdAsReceptionist() throws Exception {
    Assertions.assertNotNull(receptionisttoken, "Receptionist token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + receptionisttoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/patients/" + 1, 
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

@Test
@Order(20)
void backend_testGetPatientByIdAsUser() throws Exception {
    Assertions.assertNotNull(usertoken, "Receptionist token should not be null");

    HttpHeaders headers = createHeaders();
    headers.set("Authorization", "Bearer " + usertoken);

    ResponseEntity<String> response = restTemplate.exchange(
            "/api/patients/" + 1, 
            HttpMethod.GET,
            new HttpEntity<>(headers), 
            String.class);

    Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
}

	@Test
	@Order(21) // Ensure the order is unique and appropriate
	void backend_testAddMedicalHistoryByDoctor() throws Exception {
		Assertions.assertNotNull(doctortoken, "Doctor Token should not be null");

		String requestBody = "{"
				+ "\"medicalHistoryId\": 1,"
				+ "\"diagnosis\": \"Hypertension\","
				+ "\"treatment\": \"Medication and lifestyle changes\","
				+ "\"date\": \"2023-09-15\","
				+ "\"patient\": {"
				+ "\"patientId\": 1"
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + doctortoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/medicalhistories", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	
	@Test
	@Order(22) // Ensure the order is unique and appropriate
	void backend_testAddMedicalHistoryByNurse() throws Exception {
		Assertions.assertNotNull(nursetoken, "Nurse Token should not be null");

		String requestBody = "{"
				+ "\"medicalHistoryId\": 1,"
				+ "\"diagnosis\": \"Hypertension\","
				+ "\"treatment\": \"Medication and lifestyle changes\","
				+ "\"date\": \"2023-09-15\","
				+ "\"patient\": {"
				+ "\"patientId\": 1"
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + nursetoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/medicalhistories", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN	, response.getStatusCode());
	}

	@Test
	@Order(23) // Ensure the order is unique and appropriate
	void backend_testAddMedicalHistoryByReceptionist() throws Exception {
		Assertions.assertNotNull(receptionisttoken, "Receptionist Token should not be null");

		String requestBody = "{"
				+ "\"medicalHistoryId\": 1,"
				+ "\"diagnosis\": \"Hypertension\","
				+ "\"treatment\": \"Medication and lifestyle changes\","
				+ "\"date\": \"2023-09-15\","
				+ "\"patient\": {"
				+ "\"patientId\": 1"
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + receptionisttoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/medicalhistories", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN	, response.getStatusCode());
	}

	@Test
	@Order(24) // Ensure the order is unique and appropriate
	void backend_testAddMedicalHistoryByUser() throws Exception {
		Assertions.assertNotNull(usertoken, "User Token should not be null");

		String requestBody = "{"
				+ "\"medicalHistoryId\": 1,"
				+ "\"diagnosis\": \"Hypertension\","
				+ "\"treatment\": \"Medication and lifestyle changes\","
				+ "\"date\": \"2023-09-15\","
				+ "\"patient\": {"
				+ "\"patientId\": 1"
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + usertoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/medicalhistories", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN	, response.getStatusCode());
	}

	@Test
	@Order(25)
	void backend_testGetAllMedicalHistoryByNurse() throws Exception {
		// Ensure the ClinicManager is logged in and we have a valid token
		Assertions.assertNotNull(nursetoken, "Nurse token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + nursetoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/medicalhistories", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(26)
	void backend_testGetAllMedicalHistoryByDoctor() throws Exception {
		// Ensure the ClinicManager is logged in and we have a valid token
		Assertions.assertNotNull(doctortoken, "Doctor token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + doctortoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/medicalhistories", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(27)
	void backend_testGetAllMedicalHistoryByReceptionist() throws Exception {
		// Ensure the ClinicManager is logged in and we have a valid token
		Assertions.assertNotNull(receptionisttoken, "Receptionist token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + receptionisttoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/medicalhistories", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	
	@Test
	@Order(28)
	void backend_testGetAllMedicalHistoryByUser() throws Exception {
		// Ensure the ClinicManager is logged in and we have a valid token
		Assertions.assertNotNull(usertoken, "User token should not be null");

		// Set up headers with Authorization token
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + usertoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/medicalhistories", HttpMethod.GET,
				new HttpEntity<>(headers), // Use HttpEntity with headers
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}




	@Test
	@Order(29) // Ensure the order is unique and appropriate
	void backend_testAddFeedbackByUser() throws Exception {
		Assertions.assertNotNull(usertoken, "User token should not be null");

		String requestBody = "{"
				+ "\"feedbackId\": 1,"
				+ "\"feedbackText\": \"Great application, really user-friendly!\","
				+ "\"date\": \"2024-09-15\","
				+ "\"user\": {"
				+ "\"userId\": " + 1
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + usertoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(30) // Ensure the order is unique and appropriate
	void backend_testAddFeedbackByDoctor() throws Exception {
		Assertions.assertNotNull(doctortoken, "ClinicManager should not be null");

		String requestBody = "{"
				+ "\"feedbackId\": 1,"
				+ "\"feedbackText\": \"Great application, really user-friendly!\","
				+ "\"date\": \"2024-09-15\","
				+ "\"user\": {"
				+ "\"userId\": " + 1
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + doctortoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(31) // Ensure the order is unique and appropriate
	void backend_testAddFeedbackByNurse() throws Exception {
		Assertions.assertNotNull(nursetoken, "Nurse token should not be null");

		String requestBody = "{"
				+ "\"feedbackId\": 1,"
				+ "\"feedbackText\": \"Great application, really user-friendly!\","
				+ "\"date\": \"2024-09-15\","
				+ "\"user\": {"
				+ "\"userId\": " + 1
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + nursetoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}

	@Test
	@Order(32) // Ensure the order is unique and appropriate
	void backend_testAddFeedbackByReceptionist() throws Exception {
		Assertions.assertNotNull(receptionisttoken, "Nurse token should not be null");

		String requestBody = "{"
				+ "\"feedbackId\": 1,"
				+ "\"feedbackText\": \"Great application, really user-friendly!\","
				+ "\"date\": \"2024-09-15\","
				+ "\"user\": {"
				+ "\"userId\": " + 1
				+ "}"
				+ "}";

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + receptionisttoken);
		HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.POST, requestEntity,
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
	}
 
	@Test
	@Order(33)
	void backend_testGetAllFeedbackByDoctor() throws Exception {
		Assertions.assertNotNull(doctortoken, "Doctor token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + doctortoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(34)
	void backend_testGetAllFeedbackByReceptionist() throws Exception {
		Assertions.assertNotNull(receptionisttoken, "Doctor token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + receptionisttoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(35)
	void backend_testGetAllFeedbackByNurse() throws Exception {
		Assertions.assertNotNull(nursetoken, "Doctor token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + nursetoken);
 
		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	
	@Test
	@Order(36)
	void backend_testGetAllFeedbackByUser() throws Exception {
		Assertions.assertNotNull(usertoken, "Doctor token should not be null");
 
		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + usertoken);

		// Perform GET request to fetch all pets
		ResponseEntity<String> response = restTemplate.exchange("/api/feedback", HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(37)
	void backend_testGetFeedbackByUserIdAsUser() throws Exception {
		Assertions.assertNotNull(usertoken, "User token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + usertoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/feedback/user/" + 1, // Adjust the userId as necessary for your test
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(38)
	void backend_testGetFeedbackByUserIdAsDoctor() throws Exception {
		Assertions.assertNotNull(doctortoken, "User token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + doctortoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/feedback/user/" + 1, // Adjust the userId as necessary for your test
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(39)
	void backend_testGetFeedbackByUserIdAsNurse() throws Exception {
		Assertions.assertNotNull(nursetoken, "Nurse token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + nursetoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/feedback/user/" + 1, // Adjust the userId as necessary for your test
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);

		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

	@Test
	@Order(40)
	void backend_testGetFeedbackByUserIdAsReceptionist() throws Exception {
		Assertions.assertNotNull(receptionisttoken, "Nurse token should not be null");

		HttpHeaders headers = createHeaders();
		headers.set("Authorization", "Bearer " + receptionisttoken);

		ResponseEntity<String> response = restTemplate.exchange(
				"/api/feedback/user/" + 1, // Adjust the userId as necessary for your test
				HttpMethod.GET,
				new HttpEntity<>(headers),
				String.class);
 
		Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
		Assertions.assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getHeaders().getContentType().toString());
	}

}
