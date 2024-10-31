
package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ArticuloEntity;

@Repository
public class ArticuloRepository {

	private ArrayList<ArticuloEntity> listaDeArticulos;

	public ArticuloRepository() {
		this.listaDeArticulos = new ArrayList<ArticuloEntity>();
		cargarArticulos();
	}

	public List<ArticuloEntity> findAll() {
		System.out.println("Invocando a listarArticulos");
		return this.listaDeArticulos;
	}

	public ArticuloEntity findById(Integer id) {
		System.out.println("Invocando a consultar un articulo");
		ArticuloEntity objArticulo = null;

		for (ArticuloEntity articulo : listaDeArticulos) {
			if (articulo.getId() == id) {
				objArticulo = articulo;
				break;
			}
		}

		return objArticulo;
	}

	public ArticuloEntity save(ArticuloEntity articulo) {
		System.out.println("Invocando a almacenar articulo");
		ArticuloEntity objArticulo = null;
		if (this.listaDeArticulos.add(articulo)) {
			objArticulo = articulo;
		}

		return objArticulo;
	}

	public ArticuloEntity update(Integer id, ArticuloEntity articulo) {
		System.out.println("Invocando a actualizar un articulo");
		ArticuloEntity objArticulo = null;

		for (int i = 0; i < this.listaDeArticulos.size(); i++) {
			if (this.listaDeArticulos.get(i).getId() == id) {
				this.listaDeArticulos.set(i, articulo);
				objArticulo = articulo;
				break;
			}
		}

		return objArticulo;
	}

	public boolean delete(Integer id) {
		System.out.println("Invocando a eliminar un articulo");
		boolean bandera = false;

		for (int i = 0; i < this.listaDeArticulos.size(); i++) {
			if (this.listaDeArticulos.get(i).getId() == id) {
				this.listaDeArticulos.remove(i);
				bandera = true;
				break;
			}
		}

		return bandera;
	}

	private void cargarArticulos() {
		ArticuloEntity objArticulo1 = new ArticuloEntity(1, "Articulo Sistemas", "Daniel Muñoz", 1, "Aprobado");
		this.listaDeArticulos.add(objArticulo1);

		ArticuloEntity objArticulo2 = new ArticuloEntity(2, "Articulo Ingenieria", "Leanel Messi", 1, "Revision");
		this.listaDeArticulos.add(objArticulo2);

		ArticuloEntity objArticulo3 = new ArticuloEntity(3, "Articulo Biologia", "Cristiano Ronaldo", 1, "Revision");
		this.listaDeArticulos.add(objArticulo3);

		ArticuloEntity objArticulo4 = new ArticuloEntity(4, "Articulo Matematicas", "Erling Haland, Kylian Mbappe", 2, "Aprobado");
		this.listaDeArticulos.add(objArticulo4);

		ArticuloEntity objArticulo5 = new ArticuloEntity(5, "Articulo Electronica", "Vini Jr", 1, "Aprobado");
		this.listaDeArticulos.add(objArticulo5);
	}
}
