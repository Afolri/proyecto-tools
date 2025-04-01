package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TicketRequestDTO {
    @JsonProperty("codigo")
    private String codigo;
    @JsonProperty("numero_identificador")
    private Long numeroIdentificador;
    @JsonProperty("nombre_cliente")
    private String nombreCliente;
    private String correo;
    private String telefono;
    private String asunto;
    @JsonProperty("numero_compra_cot")
    private String numeroCompraCot;
    private String descripcion;



}
