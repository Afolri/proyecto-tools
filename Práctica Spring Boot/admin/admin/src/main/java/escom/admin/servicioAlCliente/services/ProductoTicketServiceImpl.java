package escom.admin.servicioAlCliente.services;

import escom.admin.servicioAlCliente.entities.ProductoTicket;
import escom.admin.servicioAlCliente.entities.ProductoTipo;
import escom.admin.servicioAlCliente.entities.TipoIdentificador;
import escom.admin.servicioAlCliente.repositories.ProductoTicketRepository;
import escom.admin.servicioAlCliente.repositories.ProductoTipoRepository;
import escom.admin.servicioAlCliente.repositories.TipoIdentificadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoTicketServiceImpl implements ProductoTicketService{
    @Autowired
    ProductoTicketRepository productoTicketRepository;
    @Autowired
    TipoIdentificadorRepository tipoIdentificadorRepository;
    @Autowired
    ProductoTipoRepository productoTipoRepository;


    @Override
    public ProductoTicket crearProducto(String numeroCompraCot, String codigo, String nombreIdentificador) {
        //Creamos los Optional
        Optional<TipoIdentificador> tipoOptional;
        Optional<ProductoTicket> productoOptional;
        Optional<ProductoTipo> productoTipoOptional;

        //Objetos donde guardaremos los optional
        TipoIdentificador tipoIdentificador;
        ProductoTipo productoTipo;
        ProductoTicket productoTicket = new ProductoTicket();

        //Buscamos si el nombre de identificador ya existe
        tipoOptional = tipoIdentificadorRepository.findByNombreIdentificador(nombreIdentificador);
        //Si ya existe asigna el existente sino crea uno nuevo
            tipoIdentificador = tipoOptional.orElseGet(() ->{
                TipoIdentificador tipoIdentificador1 = new TipoIdentificador();
                tipoIdentificador1.setNombreIdentificador(nombreIdentificador);
                return tipoIdentificadorRepository.save(tipoIdentificador1);
                    });
        //Buscamos si el numero de compra ya existe
        productoOptional =productoTicketRepository.findByNumeroCompraCot(numeroCompraCot);
        //Si ya existe asigna el existente sino crea uno nuevo
        productoTicket = productoOptional.orElseGet(() ->{
            ProductoTicket productoTicket1 = new ProductoTicket();
            productoTicket1.setNumeroCompraCot(numeroCompraCot);
            return productoTicketRepository.save(productoTicket1);
        });

        //se busca algun producto con un identificador y el valor del identificador si existe no lo guarda pero
        // si no existe lo guarda
        productoTipoOptional = productoTipoRepository.buscarProductoIdentificador(productoTicket.getNumeroProducto(),
                tipoIdentificador.getNumeroIdentificador(), codigo);
        if(productoTipoOptional.isPresent()){
            productoTipo = productoTipoOptional.get();
            productoTipo.setCodigo(codigo);
            productoTipo = productoTipoRepository.save(productoTipo);
        }else{
            productoTipo = guardarProducto(productoTicket, tipoIdentificador, codigo);
        }
        return productoTipo.getProductoTicket();
    }

    ProductoTipo guardarProducto( ProductoTicket productoTicket, TipoIdentificador tipoIdentificador, String codigo) {
        ProductoTipo productoTipo = new ProductoTipo();
        productoTipo.setCodigo(codigo);
        productoTipo.setProductoTicket(productoTicket);
        productoTipo.setTipoIdentificador(tipoIdentificador);
        return productoTipoRepository.save(productoTipo);

    }
}
