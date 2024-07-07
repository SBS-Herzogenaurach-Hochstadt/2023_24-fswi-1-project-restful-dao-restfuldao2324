package de.sbs.fswi1.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sbs.fswi1.models.Student;
import de.sbs.fswi1.models.StudentDTO;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DataAccessObject {

	private ObjectMapper objectMapper = new ObjectMapper();

	public void save(Student student) {

		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpRequest request =
					HttpRequest.newBuilder()
							.uri(new URI("http://localhost:8080/studenten"))
							.header("Content-Type", "application/json")
							.POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(student)))
							.build();

			client.send(request, HttpResponse.BodyHandlers.ofString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public List<StudentDTO> findAll() {

		List<StudentDTO> dtos = new ArrayList<>();

		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpRequest request =
					HttpRequest.newBuilder()
							.uri(new URI("http://localhost:8080/studenten"))
							.header("Accept", "application/json")
							.GET()
							.build();

			// Send request and get response
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == HttpURLConnection.HTTP_OK) {
				JsonNode nodes = objectMapper.readTree(response.body());
				if (nodes != null) {
					dtos = objectMapper.readValue(response.body(), new TypeReference<>() {
					});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dtos;
	}
}
