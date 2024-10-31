
package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ArticuloEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ConferenciaEntity;

@Repository
public class ConferenciaRepository {

	private ArrayList<ConferenciaEntity> listaDeConferencias;

	public ConferenciaRepository() {
		this.listaDeConferencias = new ArrayList<ConferenciaEntity>();
		cargarConferencias();
	}

	public List<ConferenciaEntity> findAll() {
		System.out.println("Invocando a listarConferencias");
		return this.listaDeConferencias;
	}

	public ConferenciaEntity findById(Integer id) {
		System.out.println("Invocando a consultar una conferencia");
		ConferenciaEntity objConferencia = null;

		for (ConferenciaEntity conferencia : listaDeConferencias) {
			if (conferencia.getId() == id) {
				objConferencia = conferencia;
				break;
			}
		}
		return objConferencia;
	}

	public ConferenciaEntity save(ConferenciaEntity conferencia) {
		System.out.println("Invocando a almacenar conferencia");
		ConferenciaEntity objConferencia = null;
		if (this.listaDeConferencias.add(conferencia)) {
			objConferencia = conferencia;
		}
		return objConferencia;
	}

	public ConferenciaEntity update(Integer id, ConferenciaEntity conferencia) {
		System.out.println("Invocando a actualizar una conferencia");
		ConferenciaEntity objConferencia = null;

		for (int i = 0; i < this.listaDeConferencias.size(); i++) {
			if (this.listaDeConferencias.get(i).getId() == id) {
				this.listaDeConferencias.set(i, conferencia);
				objConferencia = conferencia;
				break;
			}
		}
		return objConferencia;
	}

	public boolean delete(Integer id) {
		System.out.println("Invocando a eliminar una conferencia");
		boolean bandera = false;

		for (int i = 0; i < this.listaDeConferencias.size(); i++) {
			if (this.listaDeConferencias.get(i).getId() == id) {
				this.listaDeConferencias.remove(i);
				bandera = true;
				break;
			}
		}
		return bandera;
	}

	public List<ConferenciaEntity> ListarConferenciasDeArticulo(Integer idArticulo) {
		System.out.println("Obteniendo los Conferencias desde el repositorio");
		ArrayList<ConferenciaEntity> listaConferencias = new ArrayList();
		for (int i = 0; i < this.listaDeConferencias.size(); i++) {
			List<ArticuloEntity> listaArticulos = this.listaDeConferencias.get(i).getArticulos();
			for (int j = 0; j < listaArticulos.size(); j++) {
				if (listaArticulos.get(j).getId() == idArticulo) {
					listaConferencias.add(this.listaDeConferencias.get(i));
					break;
				}
			}
		}
		System.out.println("lista retornada " + listaConferencias.size());
		return listaConferencias;
	}

	private void cargarConferencias() {
		ArticuloEntity objArticuloEntity1 = new ArticuloEntity(1, "Articulo IA");
		ArticuloEntity objArticuloEntity2 = new ArticuloEntity(2, "Articulo Software");
		ArticuloEntity objArticuloEntity3 = new ArticuloEntity(3, "Articulo ingenieria");
		ArticuloEntity objArticuloEntity4 = new ArticuloEntity(4, "Articulo Investigacion");

		ConferenciaEntity objConferencia1 = new ConferenciaEntity(1, "Conferencia Sistemas", 2);
		objConferencia1.agregarArticulo(objArticuloEntity1);
		objConferencia1.agregarArticulo(objArticuloEntity4);
		this.listaDeConferencias.add(objConferencia1);

		ConferenciaEntity objConferencia2 = new ConferenciaEntity(2, "Conferencia IA", 5);
		objConferencia2.agregarArticulo(objArticuloEntity2);
		objConferencia2.agregarArticulo(objArticuloEntity3);
		this.listaDeConferencias.add(objConferencia2);

		ConferenciaEntity objConferencia3 = new ConferenciaEntity(3, "Conferencia Ingenieria", 3);
		objConferencia3.agregarArticulo(objArticuloEntity2);
		this.listaDeConferencias.add(objConferencia3);
	}
}
