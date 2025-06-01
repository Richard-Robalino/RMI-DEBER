package rmi.cliente.clase;

import rmi.servidor.clase.Persona;
import rmi.servidor.clase.Servidor;

import java.rmi.Naming;
import java.util.List;

public class Cliente {
    private static Servidor servidor;

    static {
        try {
            servidor = (Servidor) Naming.lookup("rmi://localhost/ServidorEmpleado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Persona consultar(int id) throws Exception {
        return servidor.consultar(id);
    }

    public static List<Persona> listar() throws Exception {
        return servidor.listar();
    }

    public static String insertar(Persona p) throws Exception {
        return servidor.insertar(p);
    }

    public static String actualizar(Persona p) throws Exception {
        return servidor.actualizar(p);
    }

    public static String eliminar(int id) throws Exception {
        return servidor.eliminar(id);
    }
}
