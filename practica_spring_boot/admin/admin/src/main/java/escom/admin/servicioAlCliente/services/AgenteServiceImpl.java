package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.entities.Ticket;
import escom.admin.servicioAlCliente.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenteServiceImpl implements AgenteService{

    @Autowired
    private final TicketRepository ticketRepository;

    public AgenteServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> verTickets(Long numeroAgente) {
        return ticketRepository.buscarTicketsPorNumeroAgente(numeroAgente);
    }
}
