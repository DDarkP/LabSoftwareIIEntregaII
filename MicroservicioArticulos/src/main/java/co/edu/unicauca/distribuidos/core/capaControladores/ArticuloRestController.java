
package co.edu.unicauca.distribuidos.core.capaControladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ArticuloDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.ConferenciasService;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.IArticuloService;

@RestController
@RequestMapping("/api")
public class ArticuloRestController {

	@Autowired
	private IArticuloService articuloService;

	private final ConferenciasService conferenciaService;

    ArticuloRestController(ConferenciasService conferenciaService) {
        this.conferenciaService = conferenciaService;
    }

	@GetMapping("/articulos")
	public List<ArticuloDTO> listarArticulos() {
		return articuloService.findAll();
	}

	@GetMapping("/articulos/conferencia/{idArticulo}")
	public List<ArticuloDTO> consultarArticuloConConferencias(@PathVariable Integer idArticulo) {
		return this.conferenciaService.obtenerConferenciasDeArticulo(idArticulo);
	}

	@GetMapping("/articulos/{id}")
	public ArticuloDTO consultarArticulo(@PathVariable Integer id) {
		ArticuloDTO objArticulo = null;
		objArticulo = articuloService.findById(id);
		return objArticulo;
	}

	@PostMapping("/articulos")
	public ArticuloDTO crearArticulo(@RequestBody ArticuloDTO articulo) {
		ArticuloDTO objArticulo = null;
		objArticulo = articuloService.save(articulo);
		return objArticulo;
	}

	@PutMapping("/articulos/{id}")
	public ArticuloDTO actualizarArticulo(@RequestBody ArticuloDTO articulo, @PathVariable Integer id) {
		ArticuloDTO objArticulo = null;
		ArticuloDTO articuloActual = articuloService.findById(id);
		if (articuloActual != null) {
			objArticulo = articuloService.update(id, articulo);
		}
		return objArticulo;
	}

	@DeleteMapping("/articulos/{id}")
	public Boolean eliminarArticulo(@PathVariable Integer id) {
		Boolean bandera = false;
		ArticuloDTO articuloActual = articuloService.findById(id);
		if (articuloActual != null) {
			bandera = articuloService.delete(id);
		}
		return bandera;
	}

}
