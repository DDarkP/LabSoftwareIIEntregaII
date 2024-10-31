package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConferenciaDTO {
	private Integer id;
	private String nombre;
	private Integer cantidadMAxArt;

	public ConferenciaDTO() {

	}
}