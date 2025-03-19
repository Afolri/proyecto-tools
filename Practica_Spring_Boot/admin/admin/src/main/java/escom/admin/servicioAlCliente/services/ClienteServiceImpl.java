package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void agregarCliente(String nombreCliente) {

    }
}
