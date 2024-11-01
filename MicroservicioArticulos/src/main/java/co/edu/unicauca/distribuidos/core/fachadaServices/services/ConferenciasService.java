package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ArticuloDTO;
import reactor.core.publisher.Mono;
import java.util.List;

@Service
public class ConferenciasService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<ArticuloDTO> obtenerConferenciasDeArticulo(Integer idArticulo) {
        String url = "http://localhost:5002/api/conferencias/articulos/" + idArticulo;

        Mono<ArticuloDTO[]> response = webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(ArticuloDTO[].class);

        ArticuloDTO[] ArticulosArray = response.block();
        return ArticulosArray != null ? List.of(ArticulosArray) : List.of();
    }
}

