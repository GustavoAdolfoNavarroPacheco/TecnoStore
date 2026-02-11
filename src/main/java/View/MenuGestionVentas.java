package View;

import java.util.Scanner;
import Controler.GestionVenta;
import Controler.GestionarVenta_Implement;
import Controler.GestionDetalleVenta;
import Controler.GestionarDetalleVenta_Implement;
import Model.Venta;
import Model.Cliente;
import Model.DetalleVenta;
import java.util.ArrayList;

public class MenuGestionVentas {
    
    Scanner sc = new Scanner(System.in);
    public void MenuGestionVentas() {
        
        int op = 0;
        do {
            System.out.println("""
                                ========================================
                                =         TECNOSTORE - VENTAS          = 
                                ========================================
                                = [1] Registrar.                       =
                                = [2] Actualizar.                      =
                                = [3] Eliminar.                        =
                                = [4] Listar.                          =
                                = [5] Buscar.                          =
                                = [6] Regresar.                        =
                                ========================================
                                """);
            System.out.print("  Selecciona una opcion: ");
            op = sc.nextInt();
            while (op < -1 || op > 6) {
                System.out.println("Error! Ingrese una opcion valida!");
                op = sc.nextInt();
            }
            switch (op) {
                case 1 -> Registrar();
                case 2 -> Actualizar();
                case 3 -> Eliminar();
                case 4 -> Listar();
                case 5 -> Buscar();
            }
        } while (op != 6);
    }
    
    GestionVenta Gv = new GestionarVenta_Implement();
    
    private void Registrar() {
        Venta Ve = new Venta();

        System.out.println("""
                           ========================================
                           =           REGISTRAR VENTA            = 
                           ========================================
                           """);
        
        Ve.setFecha(java.sql.Date.valueOf(java.time.LocalDate.now()));

        ArrayList<Cliente> clientes = Gv.ListarCliente();

        clientes.forEach(System.out::println);

        System.out.print("Ingrese una opcion: ");
        int idCliente = sc.nextInt();

        Cliente clienteSeleccionado = clientes.stream()
                .filter(m -> m.getId() == idCliente)
                .findFirst()
                .orElse(null);

        if (clienteSeleccionado == null) {
            System.out.println("El cliente seleccionada no existe!");
            return;
        }
        
        Ve.setId_Cliente(clienteSeleccionado);

        Gv.Registrar(Ve);
    }
    
    private void RegistrarDv() {
        DetalleVenta Dv = new DetalleVenta();

        System.out.println("""
                           ========================================
                           =    REGISTRAR DETALLE DE LA VENTA     = 
                           ========================================
                           """);
        ListarCelular();
    }
    
    private void Actualizar() {
        System.out.println("========================================");
        System.out.print("  ID Venta a Actualizar: ");
        int Id = new Scanner(System.in).nextInt();

        Venta Ve = Gv.Buscar(Id);

        if (Ve != null) {
            System.out.println("========================================");
            System.out.println("   Datos de " + Ve.getId() + ": ");
            System.out.println(Ve);
            System.out.println("""
                                ========================================
                                = Seleccione opcion a modificar:       =
                                = [1] Total.                           =
                                = [2] Cliente.                         =
                                ========================================
                               """);
            int op = new Scanner(System.in).nextInt();

            while (op < 1 || op > 3) {
                System.out.println("Error! Ingrese una opcion valida!");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nuevo Total: ");
                    Ve.setTotal(new Scanner(System.in).nextInt());
                    System.out.println("Actualizacion Realizada!");
                    break;
                case 2:
                    ArrayList<Cliente> Clientes = Gv.ListarCliente();

                    Clientes.forEach(System.out::println);

                    System.out.print("Ingrese una opcion: ");
                    int idCliente = sc.nextInt();

                    Cliente clienteSeleccionado = Clientes.stream()
                            .filter(m -> m.getId() == idCliente)
                            .findFirst()
                            .orElse(null);

                    if (clienteSeleccionado == null) {
                        System.out.println("La marca seleccionada no existe!");
                        return;
                    }

                    Ve.setId_Cliente(clienteSeleccionado);

                    System.out.println("Actualizacion Realizada!");
                    break;
            }
            Gv.Actualizar(Ve, Id);
        } else {
            System.out.println("Venta no Encontrada!");
        }
    }
    
    private void Eliminar() {
        System.out.println("""
                               ========================================
                               =               ELIMINAR               =
                               ========================================
                               """);
        System.out.print("Venta a Eliminar: ");
        int Id = new Scanner(System.in).nextInt();
        
        Venta Ve = Gv.Buscar(Id);
        
        if (Ve != null) {
            System.out.println("========================================");
            System.out.println("   Datos de " + Ve.getId() + ": ");
            System.out.println(Ve);
            System.out.println("========================================");

            Gv.Eliminar(Id);
        } else{
            System.out.println("Cliente no Encontrado!");
        }
    }
    
    private void Listar() {
        ArrayList<Venta> Ventas = Gv.Listar();
        System.out.println("""
                               ========================================
                               =                LISTAR                =
                               ========================================
                                 Lista de Clientes:
                               """);
        for (Venta Ve : Ventas) {
            System.out.println(Ve);
        }
    }
    
    private void Buscar() {
        System.out.println("""
                           ========================================
                           =                BUSCAR                =
                           ========================================
                           """);
        System.out.print("Ingrese el ID de la venta a buscar: ");
        int Id = new Scanner(System.in).nextInt();
        Venta Ve = Gv.Buscar(Id);
        if (Ve != null) {
            System.out.println("========================================");
            System.out.println("   Datos de " + Ve.getId() + ": ");
            System.out.println(Ve);
            System.out.println("========================================");
        } else{
            System.out.println("Venta no Encontrada!");
        } 
    }
}
