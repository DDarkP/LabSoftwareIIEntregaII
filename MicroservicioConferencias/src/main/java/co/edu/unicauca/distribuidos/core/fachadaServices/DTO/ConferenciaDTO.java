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
	private Integer id;
	private String nombre;
	private List<ArticuloEntity> articulos;
	private Integer cantidadMAxArt;

	public ConferenciaDTO() {

	}
}