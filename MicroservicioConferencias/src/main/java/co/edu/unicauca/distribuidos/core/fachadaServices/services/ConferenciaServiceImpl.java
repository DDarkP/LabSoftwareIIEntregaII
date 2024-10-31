
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ConferenciaEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.ConferenciaRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ConferenciaDTO;

@Service
public class ConferenciaServiceImpl implements IConferenciaService {

	private ConferenciaRepository servicioAccesoBaseDatos;

	private ModelMapper modelMapper;

	public ConferenciaServiceImpl(ConferenciaRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
		this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ConferenciaDTO> findAll() {

		List<ConferenciaEntity> conferenciasEntity = this.servicioAccesoBaseDatos.findAll();
		List<ConferenciaDTO> conferenciasDTO = this.modelMapper.map(conferenciasEntity, new TypeToken<List<ConferenciaDTO>>() {
		}.getType());
		return conferenciasDTO;
	}

	@Override
	public ConferenciaDTO findById(Integer id) {
		ConferenciaEntity objConferenciaEntity = this.servicioAccesoBaseDatos.findById(id);
		ConferenciaDTO objConferenciaDTO = this.modelMapper.map(objConferenciaEntity, ConferenciaDTO.class);
		return objConferenciaDTO;
	}

	@Override
	public ConferenciaDTO save(ConferenciaDTO cliente) {
		ConferenciaEntity objConferenciaEntity = this.modelMapper.map(cliente, ConferenciaEntity.class);
		ConferenciaEntity objCLienteEntity = this.servicioAccesoBaseDatos.save(objConferenciaEntity);
		ConferenciaDTO objConferenciaDTO = this.modelMapper.map(objCLienteEntity, ConferenciaDTO.class);
		return objConferenciaDTO;
	}

	@Override
	public ConferenciaDTO update(Integer id, ConferenciaDTO conferencia) {
		ConferenciaEntity objConferenciaEntity = this.modelMapper.map(conferencia, ConferenciaEntity.class);
		ConferenciaEntity objConferenciaEntityActualizado = this.servicioAccesoBaseDatos.update(id, objConferenciaEntity);
		ConferenciaDTO objConferenciaDTO = this.modelMapper.map(objConferenciaEntityActualizado, ConferenciaDTO.class);
		return objConferenciaDTO;
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}

	@Override
    public List<ConferenciaDTO> ListarConferenciasDeArticulo(Integer idArticulo) {
        List<ConferenciaEntity> listaConferencias = this.servicioAccesoBaseDatos.ListarConferenciasDeArticulo(idArticulo);
		List<ConferenciaDTO> listaConferenciaDTO = this.modelMapper.map(listaConferencias, new TypeToken<List<ConferenciaDTO>>() {
		}.getType());
		return listaConferenciaDTO;
    }
}
