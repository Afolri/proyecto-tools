package escom.admin.servicioAlCliente.services;


import escom.admin.servicioAlCliente.entities.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AgenteService {
    List<Ticket> verTickets(Long numeroAgente);
}
