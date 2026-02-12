package View;

import Controler.GestionCliente;
import java.util.Scanner;
import Controler.GestionCelular;
import Controler.GestionarCelular_Implement;
import Controler.GestionVenta;
import Controler.GestionarVenta_Implement;
import Controler.GestionDetalleVenta;
import Controler.GestionarCliente_Implement;
import Controler.GestionarDetalleVenta_Implement;
import Model.Celular;
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
                case 1 ->
                    Registrar();
                case 2 ->
                    Actualizar();
                case 3 ->
                    Eliminar();
                case 4 ->
                    MenuListar();
                case 5 ->
                    MenuBuscar();
            }
        } while (op != 6);
    }

    GestionCelular Gc = new GestionarCelular_Implement();
    GestionCliente Gcl = new GestionarCliente_Implement();
    GestionVenta Gv = new GestionarVenta_Implement();
    GestionDetalleVenta Gdv = new GestionarDetalleVenta_Implement();

    private void Registrar() {
        Venta Ve = new Venta();
        DetalleVenta Dv = new DetalleVenta();

        System.out.println("""
                           ========================================
                           =           REGISTRAR VENTA            = 
                           ========================================
                           """);

        Ve.setFecha(java.sql.Date.valueOf(java.time.LocalDate.now()));

        System.out.println(" Clientes: ");
        System.out.println(" ");
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

        System.out.println("""
                           ========================================
                           =    REGISTRAR DETALLE DE LA VENTA     = 
                           ========================================
                           """);
        ArrayList<Celular> celulares = Gdv.ListarCelular();
        celulares.forEach(System.out::println);

        System.out.print("Ingrese una opcion: ");
        int idCelular = sc.nextInt();
        Celular celularSeleccionado = celulares.stream()
                .filter(m -> m.getId() == idCelular)
                .findFirst()
                .orElse(null);

        if (celularSeleccionado == null) {
            System.out.println("El celular seleccionado no existe!");
            return;
        }

        Celular Ce = celularSeleccionado;
        Dv.setId_celular(celularSeleccionado);

        System.out.println("Cantidad");
        int cantidad = sc.nextInt();
        if (Ce.getStock() < cantidad) {
            System.out.println("No hay stock suficiente");
            return;
        }

        double precio = Ce.getPrecio();
        double subtotal = Gdv.SubtotalDv(cantidad, precio);
        double total = Gv.CalcularTotal(subtotal);
        Ve.setTotal(total);
        Ve.setFecha(java.sql.Date.valueOf(java.time.LocalDate.now()));
        int idVenta = Gv.Registrar(Ve);
        Ve.setId(idVenta);

        Dv.setId_venta(Ve);
        Dv.setId_celular(Ce);
        Dv.setCantidad(cantidad);
        Dv.setSubtotal(subtotal);
        Gc.ActualizarStock(Ce.getId(), cantidad);

        Gdv.RegistrarDv(Dv);
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
        int op = 0;
        do {
            System.out.println("""
                               ========================================
                               =               ELIMINAR               =
                               ========================================
                               = Ingrese segun quiera eliminar:       =
                               = [1] Venta.                           =
                               = [2] Detalle Venta.                   =
                               = [3] Regresar.                        =
                               ========================================
                               """);
            System.out.print("  Selecciona una opcion: ");
            op = sc.nextInt();
            while (op < -1 || op > 2) {
                System.out.println("Error! Ingrese una opcion valida!");
                op = sc.nextInt();
            }
            switch (op) {
                case 1:
                    Listar();
                    System.out.print("Venta a Eliminar: ");
                    int Id = new Scanner(System.in).nextInt();

                    Venta Ve = Gv.Buscar(Id);

                    if (Ve != null) {
                        System.out.println("========================================");
                        System.out.println("   Datos de " + Ve.getId() + ": ");
                        System.out.println(Ve);
                        System.out.println("========================================");

                        Gv.Eliminar(Id);
                    } else {
                        System.out.println("Cliente no Encontrado!");
                    }
                    break;
                case 2:
                    ListarDv();
                    System.out.print("Detalle Venta a Eliminar: ");
                    int IdEliminar = new Scanner(System.in).nextInt();

                    DetalleVenta Dv = Gdv.BuscarDv(IdEliminar);

                    if (Dv != null) {
                        System.out.println("========================================");
                        System.out.println("   Datos de " + Dv.getId() + ": ");
                        System.out.println(Dv);
                        System.out.println("========================================");

                        Gv.Eliminar(IdEliminar);
                    } else {
                        System.out.println("Cliente no Encontrado!");
                    }
                    break;
            }
        } while (op != 3);
    }

    public void MenuListar() {
        int op = 0;
        do {
            System.out.println("""
                               ========================================
                               =                LISTAR                =
                               ========================================
                               = Ingrese segun quiera listar:         =
                               = [1] Venta.                           =
                               = [2] Detalle Venta.                   =
                               = [3] Regresar.                        =
                               ========================================
                               """);
            System.out.print("  Selecciona una opcion: ");
            op = sc.nextInt();
            while (op < -1 || op > 3) {
                System.out.println("Error! Ingrese una opcion valida!");
                op = sc.nextInt();
            }
            switch (op) {
                case 1:
                    Listar();
                    break;
                case 2:
                    ListarDv();
                    break;
                case 3:
                    System.out.println("Regresando al menu principal...");
                    break;
            }
        } while (op != 3);
    }

    private void Listar() {
        ArrayList<Venta> Ventas = Gv.Listar();
        System.out.println("""
                               ========================================
                               =                LISTAR                =
                               ========================================
                                 Lista de Ventas:
                               """);
        for (Venta Ve : Ventas) {
            System.out.println(Ve);
        }
    }

    private void ListarDv() {
        ArrayList<DetalleVenta> DetalleVentas = Gdv.ListarDv();
        System.out.println("""
                               ========================================
                               =                LISTAR                =
                               ========================================
                                 Lista de Detalle de Ventas:
                               """);
        for (DetalleVenta Dv : DetalleVentas) {
            System.out.println(Dv);
        }
    }

    public void MenuBuscar() {
        int op = 0;
        do {
            System.out.println("""
                               ========================================
                               =                LISTAR                =
                               ========================================
                               = Ingrese segun quiera buscar:         =
                               = [1] Venta.                           =
                               = [2] Detalle Venta.                   =
                               = [3] Regresar.                        =
                               ========================================
                               """);
            System.out.print("  Selecciona una opcion: ");
            op = sc.nextInt();
            while (op < -1 || op > 3) {
                System.out.println("Error! Ingrese una opcion valida!");
                op = sc.nextInt();
            }
            switch (op) {
                case 1:
                    Buscar();
                    break;
                case 2:
                    BuscarDv();
                    break;
                case 3:
                    System.out.println("Regresando al menu principal...");
                    break;
            }
        } while (op != 3);
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
        } else {
            System.out.println("Venta no Encontrada!");
        }
    }

    private void BuscarDv() {
        System.out.println("""
                           ========================================
                           =                BUSCAR                =
                           ========================================
                           """);
        System.out.print("ID del DetalleVenta a buscar: ");
        int Id = new Scanner(System.in).nextInt();
        DetalleVenta Dv = Gdv.BuscarDv(Id);
        if (Dv != null) {
            System.out.println("========================================");
            System.out.println("   Datos de " + Dv.getId() + ": ");
            System.out.println(Dv);
            System.out.println("========================================");
        } else {
            System.out.println("DetalleVenta no Encontrado!");
        }
    }
}
