package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticuloEntity {
	private Integer id;
	private String nombre;
	private String autores;
	private String rutaArchivoPDF;
	private String revista;

	public ArticuloEntity() {

	}
}
