package de.sbs.fswi1.services;

import de.sbs.fswi1.models.RestDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class DataAccessObject {

	public List<RestDTO> findAll() {

		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpRequest request =
					HttpRequest.newBuilder()
							.uri(new URI("https://jsonplaceholder.typicode.com/posts"))
							.GET()
							.build();
			
			// Send request and get response
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			StringBuilder rawJSON = new StringBuilder(response.body()
					.replace("[\n", "")
					.replace("\n]", "")
					.trim().replace("\n" +
					"    ","")
					.replace(" ", "")
					.replace("\"\n", "\"")
					.replace(",\n{", ",{"));
			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String hilfsmethode(String body) {
		return null;
	}
}

