
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ArticuloEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.ArticuloRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ArticuloDTO;

@Service
public class ArticuloServiceImpl implements IArticuloService {

	private ArticuloRepository servicioAccesoBaseDatos;

	private ModelMapper modelMapper;

	public ArticuloServiceImpl(ArticuloRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
		this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ArticuloDTO> findAll() {

		List<ArticuloEntity> articulosEntity = this.servicioAccesoBaseDatos.findAll();
		List<ArticuloDTO> articulosDTO = this.modelMapper.map(articulosEntity, new TypeToken<List<ArticuloDTO>>() {
		}.getType());
		return articulosDTO;
	}

	@Override
	public ArticuloDTO findById(Integer id) {
		ArticuloEntity objArticuloEntity = this.servicioAccesoBaseDatos.findById(id);
		ArticuloDTO objArticuloDTO = this.modelMapper.map(objArticuloEntity, ArticuloDTO.class);
		return objArticuloDTO;
	}

	@Override
	public ArticuloDTO save(ArticuloDTO cliente) {
		ArticuloEntity objArticuloEntity = this.modelMapper.map(cliente, ArticuloEntity.class);
		ArticuloEntity objCLienteEntity = this.servicioAccesoBaseDatos.save(objArticuloEntity);
		ArticuloDTO articuloDTO = this.modelMapper.map(objCLienteEntity, ArticuloDTO.class);
		return articuloDTO;
	}

	@Override
	public ArticuloDTO update(Integer id, ArticuloDTO cliente) {
		ArticuloEntity objArticuloEntity = this.modelMapper.map(cliente, ArticuloEntity.class);
		ArticuloEntity objArticuloEntityActualizado = this.servicioAccesoBaseDatos.update(id, objArticuloEntity);
		ArticuloDTO objArticuloDTO = this.modelMapper.map(objArticuloEntityActualizado, ArticuloDTO.class);
		return objArticuloDTO;
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}
}
