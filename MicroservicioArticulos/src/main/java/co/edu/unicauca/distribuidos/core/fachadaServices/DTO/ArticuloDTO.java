package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticuloDTO {
	private Integer id;
	private String nombre;
	private String autores;
	private String cantidadAutores;
	private String revista;

	public ArticuloDTO() {

	}
}