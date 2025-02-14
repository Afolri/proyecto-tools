package escom.admin.servicioAlCliente.utils;

public class UtilidadesDeValidacion {

    public static boolean validarCorreo(String correo){
        return correo.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }
    public static boolean validarTelefono(String telefono){
        return telefono.matches("^[0-9]{10}$");
    }
    public static boolean validarNombre(String nombre){
        return nombre.matches("^[a-zA-Z ]+$");
    }
}
