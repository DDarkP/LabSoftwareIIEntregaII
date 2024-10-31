package ArticlesView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class ArticleService {

    private static final String BASE_URL = "http://localhost:5001/api/articulos";
    private static final String CONFERENCES_URL = "http://localhost:8085/api/conferences"; // Microservicio de conferencias
    private final HttpClient client = HttpClient.newHttpClient();
//    private final ObjectMapper objectMapper = new ObjectMapper();

    public void registrarArticulo(int idArticle, String name, String authors, String archivo, String revista) {
        HttpClient cliente = HttpClient.newHttpClient();
        ObjectMapper object_Mapper = new ObjectMapper();

        Map<String, Object> articleData = Map.of(
                "id", idArticle,
                "nombre", name,
                "autores", authors,
                "rutaArchivoPDF", archivo,
                "revista", revista
        );
        

        try {
            String json = object_Mapper.writeValueAsString(articleData);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL)) // Ajusta la URL
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Artículo creado: " + response.body());
            } else {
                System.out.println("Error al crear el artículo: " + response.statusCode());
            }
        } catch (IOException | InterruptedException ex) {
        }
    }
    
    // Método para obtener las conferencias
    public String[][] getConferences() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(CONFERENCES_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonElement jsonElement = JsonParser.parseString(response.body());
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            // Aumenta el tamaño a 6 columnas para incluir el ID
            String[][] data = new String[jsonArray.size()][2];

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement element = jsonArray.get(i);

                // Incluye el ID en la primera columna
                data[i][0] = element.getAsJsonObject().get("id").getAsString();
                data[i][1] = element.getAsJsonObject().get("name").getAsString();
            }

            return data;
        } else {
            throw new Exception("Error al obtener las conferencias: " + response.body());
        }
    }

    public String[][] getArticles() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonElement jsonElement = JsonParser.parseString(response.body());
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            // Ajusta el tamaño de la matriz de acuerdo a los campos que necesitas mostrar
            String[][] data = new String[jsonArray.size()][5];
            
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement element = jsonArray.get(i);
                data[i][0] = element.getAsJsonObject().get("id").getAsString();
                data[i][1] = element.getAsJsonObject().get("nombre").getAsString();
                data[i][2] = element.getAsJsonObject().get("autores").getAsString();
                data[i][3] = element.getAsJsonObject().get("rutaArchivoPDF").getAsString();
                data[i][4] = element.getAsJsonObject().get("revista").getAsString();
            }
            return data;
        } else {
            throw new Exception("Error al obtener los artículos: " + response.body());
        }
    }
    
    public String updateArticle(Long articleId, String title, String abstractText, String keywords, String pdfFilePath, String userId) throws Exception {
        String json = String.format("{\"name\": \"%s\", \"summary\": \"%s\", \"keywords\": \"%s\", \"filePath\": \"%s\"}",
                title, abstractText, keywords, pdfFilePath);

        // URL que incluye el userId como parámetro de consulta
        String urlWithParams = String.format("%s/%d?userId=%s", BASE_URL, articleId, userId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlWithParams))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "Artículo actualizado con éxito";
        } else {
            throw new Exception("Error al actualizar el artículo: " + response.body());
        }
    }

    public Boolean deleteArticle(int articleId) throws Exception {
        // URL que incluye el articleId como parte de la ruta
        String urlWithParams = String.format("%s/%d", BASE_URL, articleId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlWithParams))
                .DELETE() // Usar el método DELETE
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Manejar la respuesta según el resultado del controlador
        if (response.statusCode() == 200) {
            return true; // Si el artículo fue eliminado con éxito
        } else {
            throw new Exception("Error al eliminar el artículo: " + response.body());
        }
    }

}
