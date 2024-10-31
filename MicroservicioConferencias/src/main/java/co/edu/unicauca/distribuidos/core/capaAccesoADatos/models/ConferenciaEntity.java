package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import java.util.List;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConferenciaEntity {
	private Integer id;
	private String nombre;
	private List<ArticuloEntity> articulos;
	private Integer cantidadMAxArt;

    public ConferenciaEntity(int id, String nombre, int cantidadMAxArt) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadMAxArt = cantidadMAxArt;
        this.articulos = new ArrayList<>(); // Inicialización de la lista de artículos
    }

	// Método para agregar un artículo a la conferencia
    public void agregarArticulo(ArticuloEntity articulo) {
        if (articulos.size() < cantidadMAxArt) {
            articulos.add(articulo);
        } else {
            System.out.println("No se pueden agregar más artículos, tope alcanzado.");
        }
    }
}
