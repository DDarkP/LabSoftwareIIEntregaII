package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticuloEntity {
	private static final AtomicInteger idCounter = new AtomicInteger(0);
	private Integer id;
	private String nombre;
	private String autores;
	private Integer cantidadAutores;
	private String revista;

	public ArticuloEntity() {
		this.id = idCounter.incrementAndGet(); // Asigna un ID único y ordenado
	}
}
