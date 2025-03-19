package escom.admin.productos.services;

import escom.admin.productos.repositories.ProcesadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProcesadorServiceImpl implements ProcesadorService{
    ProcesadorRepository procesadorRepository;

    public ProcesadorServiceImpl(ProcesadorRepository procesadorRepository) {
        this.procesadorRepository = procesadorRepository;
    }

    @Override
    public List<Map<String, Object>> obtenerProcesadores() {
        return procesadorRepository.obtenerProcesadores();
    }
}
