package View;

import java.util.Scanner;
import Controler.GestionVenta;
import Controler.GestionarVenta_Implement;
import Model.Celular;
import Model.Venta;
import Model.Cliente;
import Model.Gama;
import Model.Marca;
import java.util.ArrayList;

public class MenuGestionVentas {
    
    public void MenuGestionVentas() {
        
        int op = 0;
        Scanner sc = new Scanner(System.in);
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
                case 2 -> System.out.println("  En Desarrollo, Vuelva Pronto!"); //Actualizar();
                case 3 -> System.out.println("  En Desarrollo, Vuelva Pronto!"); //Eliminar();
                case 4 -> System.out.println("  En Desarrollo, Vuelva Pronto!"); //Listar();
                case 5 -> System.out.println("  En Desarrollo, Vuelva Pronto!"); //Buscar();
            }
        } while (op != 6);
    }
    
    GestionVenta Gv = new GestionarVenta_Implement();
    
    private void Registrar() {
        Venta Ve = new Venta();
        Scanner sc = new Scanner(System.in);

        System.out.println("""
                           ========================================
                           =           REGISTRAR VENTA            = 
                           ========================================
                           """);
        System.out.println("Fecha(YYYY/MM/DD): ");
        Ve.setFecha(new Scanner(System.in).nextLine());

        System.out.println("Total: ");
        Ve.setTotal(new Scanner(System.in).nextDouble());

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
                                = [1] Fecha.                           =
                                = [2] Total.                           =
                                = [3] Cliente.                         =
                                ========================================
                               """);
            int op = new Scanner(System.in).nextInt();

            while (op < 1 || op > 3) {
                System.out.println("Error! Ingrese una opcion valida!");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    System.out.println("Ingrese la nueva Fecha: ");
                    Ve.setFecha(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion Realizada!");
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo Total: ");
                    Ve.setTotal(new Scanner(System.in).nextInt());
                    System.out.println("Actualizacion Realizada!");
                    break;
                case 3:
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
            System.out.println("Celular no Encontrado!");
        }
    }
    
    private void Eliminar() {
        
    }
    
    private void Listar() {
        
    }
    
    private void Buscar() {
        
    }
}
