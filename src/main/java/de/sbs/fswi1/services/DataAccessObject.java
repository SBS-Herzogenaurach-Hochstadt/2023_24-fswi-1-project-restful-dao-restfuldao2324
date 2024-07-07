package de.sbs.fswi1.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.sbs.fswi1.models.RestDTO;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DataAccessObject {

	public List<RestDTO> findAll() {

		List<RestDTO> dtos = new ArrayList<>();

		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpRequest request =
					HttpRequest.newBuilder()
							.uri(new URI("https://jsonplaceholder.typicode.com/posts"))
							.GET()
							.build();
			
			// Send request and get response
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == HttpURLConnection.HTTP_OK) {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode nodes = objectMapper.readTree(response.body());
				if (nodes != null) {
					 dtos = objectMapper.readValue(response.body(), new TypeReference<>() {});
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dtos;
	}
}
