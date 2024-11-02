package ConferenceView;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Unicauca
 */
import Utilidades.Utilidades;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConferenceService {

    private static final String BASE_URL = "http://localhost:5002/api/conferencias";
    private final HttpClient client = HttpClient.newHttpClient();
    private final List<ArticuloEntity> articulos = new ArrayList<>();
    
    public void createConference(String nombreConferencia, int cantMaxArticulos) throws Exception {

        HttpClient cliente = HttpClient.newHttpClient();
        ObjectMapper object_Mapper = new ObjectMapper();

        Map<String, Object> articleData = Map.of(
                "nombreConferencia", nombreConferencia,
                "articulos",articulos,
                "cantidadMaxArt", cantMaxArticulos
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
                System.out.println("Conferencia creada: " + response.body());
                Utilidades.mensajeExito("El registro de la conferencia fue exitoso", "Registro exitoso");
            } else {
                System.out.println("Error al crear la conferencia: " + response.statusCode());
                Utilidades.mensajeError("El registro de la conferencia no se realizo", "Error en el registro");
            }
        } catch (IOException | InterruptedException ex) {
        }        
    }

    public String[][] getConferences() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonElement jsonElement = JsonParser.parseString(response.body());
            JsonArray jsonArray = jsonElement.getAsJsonArray();

            // numero de columnas en el json
            String[][] data = new String[jsonArray.size()][4];

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement element = jsonArray.get(i);

                System.out.println(element);
                // Incluye el ID en la primera columna
                data[i][0] = element.getAsJsonObject().get("id").getAsString();
                data[i][1] = element.getAsJsonObject().get("nombreConferencia").getAsString();

                // Suponiendo que `element` es un JsonObject que contiene la conferencia
                JsonArray articulosArray = element.getAsJsonObject().getAsJsonArray("articulos");

                // Construir una cadena con los nombres de los artículos, por ejemplo
                StringBuilder nombresArticulos = new StringBuilder();

                for (JsonElement articuloElement : articulosArray) {
                    JsonObject articuloObj = articuloElement.getAsJsonObject();
                    int id = articuloObj.get("id").getAsInt();
                    String nombre = articuloObj.get("nombre").getAsString();
                    // Agregar el nombre del artículo a la cadena
//                    nombresArticulos.append(nombre).append(", ");
                    nombresArticulos.append("[id: ").append(id).append("] ").append(nombre).append(", ");
                }
                // Remover la última coma y espacio si hay artículos
                if (nombresArticulos.length() > 0) {
                    nombresArticulos.setLength(nombresArticulos.length() - 2);
                }
                // Asignar la cadena resultante (con los nombres de los artículos) a tu estructura de datos
                data[i][2] = nombresArticulos.toString();
                data[i][3] = element.getAsJsonObject().get("cantidadMaxArt").getAsString();
            }
            return data;
        } else {
            throw new Exception("Error al obtener las conferencias: " + response.body());
        }
    }

    public String updateConference(Long conferenceId, String name, String location, String startDate, String endDate, String topics, String userId) throws Exception {
        String json = String.format("{\"name\": \"%s\", \"location\": \"%s\", \"startDate\": \"%s\", \"endDate\": \"%s\", \"topics\": \"%s\"}",
                name, location, startDate, endDate, topics);

        // URL que incluye el userId como parámetro de consulta
        String urlWithParams = String.format("%s/%d?userId=%s", BASE_URL, conferenceId, userId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlWithParams))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "Conferencia actualizada con éxito";
        } else {
            throw new Exception("Error al actualizar la conferencia: " + response.body());
        }
    }

    public String deleteConference(Long conferenceId, String userId) throws Exception {
        // URL que incluye el userId como parámetro de consulta
        String urlWithParams = String.format("%s/%d?userId=%s", BASE_URL, conferenceId, userId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(urlWithParams))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return "Conferencia eliminada con éxito";
        } else {
            throw new Exception("Error al eliminar la conferencia: " + response.body());
        }
    }

    private static class ArticuloEntity {

        public ArticuloEntity() {
        }
    }

}

// Métodos para Update y Delete pueden seguir la misma estructura

