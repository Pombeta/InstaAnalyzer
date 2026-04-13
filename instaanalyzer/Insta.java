package instaanalyzer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Insta {

    public static void main(String[] args) {

        try {

            String usuario = "instagram"; // coloque o usuario aqui
            String urlString = "https://www.instagram.com/" + usuario + "/?__a=1";

            URL url = new URL(urlString);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            StringBuilder json = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            reader.close();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json.toString());

            int seguidores = root
                    .get("graphql")
                    .get("user")
                    .get("edge_followed_by")
                    .get("count")
                    .asInt();

            System.out.println("Seguidores: " + seguidores);

        } catch (Exception e) {
            System.out.println("Erro ao buscar dados do Instagram.");
            e.printStackTrace();
        }
    }
}