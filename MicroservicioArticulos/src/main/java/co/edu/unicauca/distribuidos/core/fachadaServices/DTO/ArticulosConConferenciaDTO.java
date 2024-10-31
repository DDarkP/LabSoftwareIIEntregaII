package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticulosConConferenciaDTO {
	private Integer id;
	private String nombre;
	List<ConferenciaDTO> conferenciasInscrito;

	public ArticulosConConferenciaDTO() {

	}
}