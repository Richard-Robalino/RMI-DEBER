package rmi.servidor.test;

import rmi.servidor.clase.ServidorImpl;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TestServidor {
    public static void main(String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        ServidorImpl obj = new ServidorImpl();
        Naming.rebind("rmi://localhost/ServidorEmpleado", obj);
        System.out.println("Servidor RMI activo");
    }
}
