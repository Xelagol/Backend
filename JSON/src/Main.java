import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main
{
	public static void main(String[] args) throws IOException {
		String path = "src/companies.json";

		JSONObject json = new JSONObject();
		try {
			json.put("name", "Google");
			json.put("employees", 140000);
			json.put("offices", List.of("Mountain View", "Los Angeles", "New York"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
//				System.out.println(gson.toJson(JsonParser.parseString(json.toString())));
				out.write(gson.toJson(JsonParser.parseString(json.toString())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		final String inputJson = "{\"one\":\"AAA\",\"two\":[\"BBB\",\"CCC\"],\"three\":{\"four\":\"DDD\",\"five\":[\"EEE\",\"FFF\"]}}";

//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		System.out.println(gson.toJson(JsonParser.parseString(inputJson)));


	}
}