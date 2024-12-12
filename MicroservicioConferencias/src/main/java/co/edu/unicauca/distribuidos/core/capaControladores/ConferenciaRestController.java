
package co.edu.unicauca.distribuidos.core.capaControladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ArticuloEntity;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ConferenciaDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.IConferenciaService;

@RestController
@RequestMapping("/api")
public class ConferenciaRestController {

	@Autowired
	private IConferenciaService conferenciaService;

	@GetMapping("/conferencias")
	public List<ConferenciaDTO> listarConferencias() {
		return conferenciaService.findAll();
	}

	@GetMapping("/conferencias/{id}")
	public ConferenciaDTO consultarConferencia(@PathVariable Integer id) {
		ConferenciaDTO objConferencia = null;
		objConferencia = conferenciaService.findById(id);
		return objConferencia;
	}

	@PostMapping("/conferencias")
	public ConferenciaDTO crearConferencia(@RequestBody ConferenciaDTO conferencia) {
		ConferenciaDTO objConferencia = null;
		objConferencia = conferenciaService.save(conferencia);
		return objConferencia;
	}

	@PutMapping("/conferencias/{id}")
	public ConferenciaDTO actualizarConferencia(@RequestBody ConferenciaDTO articulo, @PathVariable Integer id) {
		ConferenciaDTO objConferencia = null;
		ConferenciaDTO conferencaiActual = conferenciaService.findById(id);
		if (conferencaiActual != null) {
			objConferencia = conferenciaService.update(id, objConferencia);
		}
		return objConferencia;
	}

	@DeleteMapping("/conferencias/{id}")
	public Boolean eliminarConferencia(@PathVariable Integer id) {
		Boolean bandera = false;
		ConferenciaDTO conferenciaActual = conferenciaService.findById(id);
		if (conferenciaActual != null) {
			bandera = conferenciaService.delete(id);
		}
		return bandera;
	}

	@GetMapping("/conferencias/articulos/{idArticulos}")
	public List<ConferenciaDTO> listandoConferenciasDeUnArticulo(@PathVariable Integer idArticulos) {
		System.out.println("Consumiendo servicios para obtener conferencias del un artiulo con id " + idArticulos);
		return conferenciaService.ListarConferenciasDeArticulo(idArticulos);
	}

	@PutMapping("/conferencias/{id}/articulos")
	public ConferenciaDTO addArticuloToConferencia(@PathVariable Integer id, @RequestBody ArticuloEntity articulo) {
		ConferenciaDTO conferencia = conferenciaService.findById(id);
		if (conferencia == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conferencia no encontrada");
		}

		conferencia.getArticulos().add(articulo); // Agrega el artículo a la lista
		return conferenciaService.update(id,conferencia); // Guarda la conferencia actualizada
	}
}
