package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.dto.TipoIdentificadorResponseDTO;
import escom.admin.servicioAlCliente.entities.TipoIdentificador;
import escom.admin.servicioAlCliente.repositories.TipoIdentificadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoIdentificadorServiceImpl implements TipoIdentificadorService {

    @Autowired
    TipoIdentificadorRepository tipoIdentificadorRepository;

    @Override
    public List<TipoIdentificadorResponseDTO> obtenerIdentificadores() {
        return tipoIdentificadorRepository.buscarIdentificadores();
    }

    @Override
    public void agregarIdentificador(String nombreIdentificador) {
        TipoIdentificador tipoIdentificador = new TipoIdentificador();
        tipoIdentificador.setNombreIdentificador(nombreIdentificador);
        tipoIdentificadorRepository.save(tipoIdentificador);
    }
}
