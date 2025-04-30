package escom.admin.servicioAlCliente.entities;


public enum Rol {
    ADMIN,
    AGENTE;


    public static Rol obtenerRol(short valor){
        return switch (valor) {
            case 0 -> ADMIN;
            case 1 -> AGENTE;
            default -> null;
        };
    }


}


