package escom.admin.servicioAlCliente.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties (ignoreUnknown = true)
@Builder
public class TicketResponseDTO {
        @JsonProperty("numero_ticket")
        private Long numeroTicket;
        @JsonProperty("numero_compra_cot")
        private String numeroCompraCot;
        @JsonProperty("numero_producto")
        private Long numeroProducto;
        @JsonProperty("tipo_codigo")
        private String tipoCodigo;
        private String asunto;
        @JsonProperty("numero_cliente")
        private Long numeroCliente;
        @JsonProperty("nombre_cliente")
        private String nombreCliente;
        private String correo;
        private String telefono;
        private String descripcion;
        private String estado;
        @JsonProperty("numero_agente")
        private Long numeroAgente;
        private LocalDate fecha;
        private LocalTime hora;

}
