package escom.admin.servicioAlCliente.Projection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TicketProjection {
    @JsonProperty("numero_ticket")
    Long getNumeroTicket();
    String getAsunto();
    String getDescripcion();
    @JsonProperty("numero_agente")
    Long getNumeroAgente();
    @JsonProperty("numero_cliente")
    String getNumeroCliente();
    LocalDate getFecha();
    String getEstado();
    LocalTime getHora();
    @JsonProperty("nombre_cliente")
    String getNombreCliente();
    String getCorreo();
    String getTelefono();
    @JsonProperty("numero_producto")
    Long getNumeroProducto();
    @JsonProperty("numero_compra_cot")
    String getNumeroCompraCot();
    String getTipoCodigo();
}
