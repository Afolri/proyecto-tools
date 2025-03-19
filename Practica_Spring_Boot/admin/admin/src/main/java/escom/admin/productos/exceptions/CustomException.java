package escom.admin.productos.exceptions;

public class CustomException extends RuntimeException {

    public CustomException(String mensaje){
        super(mensaje);
    }
}
