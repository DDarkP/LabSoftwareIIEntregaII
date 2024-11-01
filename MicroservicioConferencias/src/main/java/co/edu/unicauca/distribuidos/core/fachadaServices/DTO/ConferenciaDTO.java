package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.List;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ArticuloEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConferenciaDTO {
	private static int idCounter = 0; // Variable estática para contar IDs
    private int id;
	private String nombreConferencia;
	//private List<ArticuloEntity> articulos;
	private Integer cantidadMaxArt;

	public ConferenciaDTO() {
		this.id = ++idCounter; // Asigna el ID autoincremental
	}
}