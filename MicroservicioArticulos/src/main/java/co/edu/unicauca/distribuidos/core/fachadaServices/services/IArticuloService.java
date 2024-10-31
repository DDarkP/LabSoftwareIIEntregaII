
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ArticuloDTO;

public interface IArticuloService {

	public List<ArticuloDTO> findAll();

	public ArticuloDTO findById(Integer id);

	public ArticuloDTO save(ArticuloDTO articulo);

	public ArticuloDTO update(Integer id, ArticuloDTO articulo);

	public boolean delete(Integer id);
}
