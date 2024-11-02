package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticuloDTO {
	private static final AtomicInteger idCounter = new AtomicInteger(0);
	private Integer id;
	private String nombre;
	private String autores;
	private Integer cantidadAutores;
	private String revista;

	public ArticuloDTO() {
		this.id = idCounter.incrementAndGet(); // Asigna un ID único y ordenado
	}
}