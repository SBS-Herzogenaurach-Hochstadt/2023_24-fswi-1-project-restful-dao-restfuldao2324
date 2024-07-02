package de.sbs.fswi1.services;

import de.sbs.fswi1.models.RestDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DataAccessObject {

	public String findAll() {

		try (HttpClient client = HttpClient.newHttpClient()) {
			HttpRequest request =
					HttpRequest.newBuilder()
							.uri(new URI("https://jsonplaceholder.typicode.com/posts"))
							.GET()
							.build();
			
			// Send request and get response
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			String rawJSON = response.body()
					.replace("[\n", "")
					.replace("\n]", "")
					.trim().replace("\n" +
					"    ","")
					.replace(" ", "")
					.replace("\"\n", "\"")
					.replace(",\n{", ",{");

			String[] jsonArray = rawJSON.split("\\},\\{");

			for (int i = 0; i < jsonArray.length; i++) {
				jsonArray[i] = jsonArray[i]
						.replace("{", "")
						.replace("\"", "")
						.replace("}", "")
						.replace("userId:", "")
						.replace("id:", "")
						.replace("body:", "")
						.replace("title:", "");
			}

			List<RestDTO> rests = new ArrayList<>();
			for (int i = 0; i < jsonArray.length; i++) {
				String[] bufArray = jsonArray[i].split(",");
				rests.add (new RestDTO(Long.parseLong(bufArray[0]), Long.parseLong(bufArray[1]), bufArray[2], bufArray[3].replace("\\n", " ")));
			}

			return rawJSON;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String hilfsmethode(String body) {
		return null;
	}
}

