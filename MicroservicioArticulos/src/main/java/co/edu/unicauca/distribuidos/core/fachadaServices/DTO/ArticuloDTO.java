package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticuloDTO {
	private static int idCounter = 0; // Variable estática para contar IDs
	private Integer id;
	private String nombre;
	private String autores;
	private Integer cantidadAutores;
	private String revista;

	public ArticuloDTO() {
		this.id = ++idCounter; // Asigna el ID autoincremental
	}
}