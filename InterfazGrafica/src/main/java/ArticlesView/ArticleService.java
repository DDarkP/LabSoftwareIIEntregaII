package ArticlesView;

import Utilidades.Utilidades;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class ArticleService {

    private static final String BASE_URL = "http://localhost:5001/api/articulos";
    private static final String CONFERENCES_URL = "http://localhost:5002/api/conferencias"; // Microservicio de conferencias
    private final HttpClient client = HttpClient.newHttpClient();

    public int registrarArticulo(String name, String authors, int cantAutores, String revista) {
        HttpClient cliente = HttpClient.newHttpClient();
        ObjectMapper object_Mapper = new ObjectMapper();

        Map<String, Object> articleData = Map.of(
                "nombre", name,
                "autores", authors,
                "cantidadAutores", cantAutores,
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

            ObjectMapper objectMapper = new ObjectMapper();

            //obtener id Del articulo registrado
            try {
                // Parsear el JSON
                JsonNode jsonNode = objectMapper.readTree(response.body());

                // Obtener el valor de "id"
                int id = jsonNode.get("id").asInt();
                System.out.println("ID: " + id);
                return id;
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("articulo registrado: " + response.body());
            if (response.statusCode() == 200) {
                System.out.println("Artículo creado: " + response.body());
                Utilidades.mensajeExito("El registro de la conferencia fue exitoso", "Registro exitoso");
            } else {
                System.out.println("Error al crear el artículo: " + response.statusCode());
                Utilidades.mensajeError("El registro de la conferencia no se realizo", "Error en el registro");
            }
        } catch (IOException | InterruptedException ex) {
        }
        return -1;
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

    public String[][] listarArticulos() throws Exception {
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
            System.out.println(Arrays.toString(data));
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement element = jsonArray.get(i);
                JsonObject jsonObject = element.getAsJsonObject();

                // Verifica si cada campo existe y no es null antes de asignar
                data[i][0] = jsonObject.has("id") && !jsonObject.get("id").isJsonNull() ? jsonObject.get("id").getAsString() : "N/A"; // Cambia a un valor predeterminado
                data[i][1] = jsonObject.has("nombre") && !jsonObject.get("nombre").isJsonNull() ? jsonObject.get("nombre").getAsString() : "N/A";
                data[i][2] = jsonObject.has("autores") && !jsonObject.get("autores").isJsonNull() ? jsonObject.get("autores").getAsString() : "N/A";
                data[i][3] = jsonObject.has("cantidadAutores") && !jsonObject.get("cantidadAutores").isJsonNull() ? jsonObject.get("cantidadAutores").getAsString() : "N/A";
                data[i][4] = jsonObject.has("revista") && !jsonObject.get("revista").isJsonNull() ? jsonObject.get("revista").getAsString() : "N/A";
            }
            return data;
        } else {
            throw new Exception("Error al obtener los artículos: " + response.body());
        }
    }

    public String[][] listarArticulosPorID(int idArticulo) throws Exception {
        String URL_CONSULTAR_ARTICULO = "http://localhost:5001/api/articulos/" + idArticulo;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL_CONSULTAR_ARTICULO))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonElement jsonElement = JsonParser.parseString(response.body());

            // Inicializa la matriz para un único artículo
            String[][] data = new String[1][5];
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            // Asigna los valores a la matriz
            data[0][0] = jsonObject.has("id") && !jsonObject.get("id").isJsonNull() ? jsonObject.get("id").getAsString() : "N/A";
            data[0][1] = jsonObject.has("nombre") && !jsonObject.get("nombre").isJsonNull() ? jsonObject.get("nombre").getAsString() : "N/A";
            data[0][2] = jsonObject.has("autores") && !jsonObject.get("autores").isJsonNull() ? jsonObject.get("autores").getAsString() : "N/A";
            data[0][3] = jsonObject.has("cantidadAutores") && !jsonObject.get("cantidadAutores").isJsonNull() ? jsonObject.get("cantidadAutores").getAsString() : "N/A";
            data[0][4] = jsonObject.has("revista") && !jsonObject.get("revista").isJsonNull() ? jsonObject.get("revista").getAsString() : "N/A";

            return data;
        } else {
            throw new Exception("Error al obtener los artículos: " + response.body());
        }
    }

    public String updateArticle(Long articleId, String title, String abstractText, String keywords, String pdfFilePath, String userId) throws Exception {
        String json = String.format("{\"name\": \"%s\", \"summary\": \"%s\", \"keywords\": \"%s\", \"filePath\": \"%s\"}",
                title, abstractText, keywords, pdfFilePath);

        String URL_ACTUALIZAR = "http://localhost:5001/api/articulos/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL_ACTUALIZAR))
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
        String URL_DELETE = BASE_URL + "/" + articleId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL_DELETE))
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

    public void asociarArticuloAConferencia(int articuloID, int conferenceID, String nombreArticulo) {
        HttpClient cliente = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        // Usar LinkedHashMap para mantener el orden
        Map<String, Object> articleData = new LinkedHashMap<>();
        articleData.put("id", String.valueOf(articuloID)); // Primero el ID
        articleData.put("nombre", nombreArticulo); // Luego el nombre

        try {
            // Convertir los datos a JSON
            String json = objectMapper.writeValueAsString(articleData);
            System.out.println("json enviado: " + json);

            // Crear la solicitud POST
            String url = CONFERENCES_URL + "/" + conferenceID + "/articulos";
            System.out.println(url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            // Enviar la solicitud
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
            // Verificar la respuesta
            if (response.statusCode() == 200 || response.statusCode() == 201) {
                System.out.println("Artículo asociado a conferencia: " + response.body());
                Utilidades.mensajeExito("El artículo se ha asociado exitosamente a la conferencia.", "Asociación exitosa");
            } else {
                System.out.println("Error al asociar el artículo: " + response.statusCode());
                Utilidades.mensajeError("No se pudo asociar el artículo a la conferencia.", "Error en la asociación");
            }
        } catch (IOException | InterruptedException ex) {
            Utilidades.mensajeError("Hubo un error al procesar la solicitud.", "Error de red");
        }
    }

//    public void asociarArticuloAConferencia(int articuloID, int conferenceID, String nombreArticulo) {
//        HttpClient cliente = HttpClient.newHttpClient();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Crear el JSON con los datos del artículo, ajustando "id" y "nombre" según tus necesidades
//        Map<String, Object> articleData = Map.of(
//                "id", String.valueOf(articuloID), // Usamos articuloID para el campo "id"
//                "nombre", nombreArticulo // Y nombreArticulo para "nombre"
//        );
//
//        try {
//            // Convertir los datos a JSON
//            String json = objectMapper.writeValueAsString(articleData);
//
//            System.out.println("json enviado: " + json);
//            // Crear la solicitud POST
//            String url = CONFERENCES_URL + "/" + conferenceID + "/articulos";
//            System.out.println(url);
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create(url))
//                    .header("Content-Type", "application/json")
//                    .PUT(HttpRequest.BodyPublishers.ofString(json))
//                    .build();
//
//            // Enviar la solicitud
//            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // Verificar la respuesta
//            if (response.statusCode() == 200 || response.statusCode() == 201) {
//                System.out.println("Artículo asociado a conferencia: " + response.body());
//                Utilidades.mensajeExito("El artículo se ha asociado exitosamente a la conferencia.", "Asociación exitosa");
//            } else {
//                System.out.println("Error al asociar el artículo: " + response.statusCode());
//                Utilidades.mensajeError("No se pudo asociar el artículo a la conferencia.", "Error en la asociación");
//            }
//        } catch (IOException | InterruptedException ex) {
//            Utilidades.mensajeError("Hubo un error al procesar la solicitud.", "Error de red");
//        }
//    }
}
