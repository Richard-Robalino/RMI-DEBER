package rmi.servidor.clase;

import java.rmi.Remote;
import java.util.List;

public interface Servidor extends Remote {
    Persona consultar(int id) throws Exception;
    List<Persona> listar() throws Exception;
    String insertar(Persona persona) throws Exception;
    String actualizar(Persona persona) throws Exception;
    String eliminar(int id) throws Exception;
}
