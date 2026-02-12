package View;

import java.util.Scanner;
import Controler.GestionarCliente_Implement;
import Controler.GestionCliente;
import Controler.Validaciones;
import Model.Cliente;
import java.util.ArrayList;

public class MenuCliente {

    public void MenuCliente() {
        int op = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("""
                                ========================================
                                =         TECNOSTORE - CLIENTE         = 
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
                    Listar();
                case 5 ->
                    Buscar();
            }
        } while (op != 6);
    }

    GestionCliente Gc = new GestionarCliente_Implement();

    private void Registrar() {
        Cliente Cl = new Cliente();

        System.out.println("Nombre: ");
        Cl.setNombre(new Scanner(System.in).nextLine());

        System.out.println("ID: ");
        Cl.setIdentificacion(new Scanner(System.in).nextInt());

        System.out.println("Correo Electronico: ");
        Scanner sc = new Scanner(System.in);
        String correo;

        do {
            System.out.print("Ingrese el correo: ");
            correo = sc.nextLine();

            if (!Validaciones.correoValidador(correo)) {
                System.out.println("Correo inválido. Intente nuevamente.");
            }

        } while (!Validaciones.correoValidador(correo));

        Cl.setCorreo(correo);

        System.out.println("Telefono/Celular: ");
        Cl.setTelefono(new Scanner(System.in).nextLine());

        Gc.Registrar(Cl);
    }

    private void Actualizar() {
        System.out.println("========================================");
        System.out.print("  ID Cliente a Actualizar: ");
        int Id = new Scanner(System.in).nextInt();

        Cliente Cl = Gc.Buscar(Id);

        if (Cl != null) {
            System.out.println("========================================");
            System.out.println("   Datos de " + Cl.getNombre() + ": ");
            System.out.println(Cl);
            System.out.println("""
                                ========================================
                                = Seleccione opcion a modificar:       =
                                = [1] Nombre.                          =
                                = [2] Identificacion.                  =
                                = [3] Correo.                          =
                                = [4] Telefono.                        =
                                ========================================
                               """);
            int op = new Scanner(System.in).nextInt();

            while (op < 1 || op > 4) {
                System.out.println("Error! Ingrese una opcion valida!");
                op = new Scanner(System.in).nextInt();
            }
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nuevo Nombre: ");
                    Cl.setNombre(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion Realizada!");
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo ID: ");
                    Cl.setIdentificacion(new Scanner(System.in).nextInt());
                    System.out.println("Actualizacion Realizada!");
                case 3:
                    System.out.println("Ingrese el nuevo Correo: ");
                    Cl.setCorreo(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion Realizada!");
                case 4:
                    System.out.println("Ingrese el nuevo Telefono: ");
                    Cl.setTelefono(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion Realizada!");
            }
            Gc.Actualizar(Cl, Id);
        } else {
            System.out.println("Cliente no Encontrado!");
        }
    }

    private void Eliminar() {
        System.out.println("""
                               ========================================
                               =               ELIMINAR               =
                               ========================================
                               """);
        System.out.print("ID Cliente a Eliminar: ");
        int Id = new Scanner(System.in).nextInt();

        Cliente Cl = Gc.Buscar(Id);

        if (Cl != null) {
            System.out.println("========================================");
            System.out.println("   Datos de " + Cl.getNombre() + ": ");
            System.out.println(Cl);
            System.out.println("========================================");

            Gc.Eliminar(Id);
        } else {
            System.out.println("Cliente no Encontrado!");
        }
    }

    private void Listar() {
        ArrayList<Cliente> clientes = Gc.Listar();
        System.out.println("""
                               ========================================
                               =                LISTAR                =
                               ========================================
                                 Lista de Clientes:
                               """);
        for (Cliente Cl : clientes) {
            System.out.println(Cl);
        }
    }

    private void Buscar() {
        System.out.println("""
                           ========================================
                           =                BUSCAR                =
                           ========================================
                           """);
        System.out.print("Ingrese el ID de la persona a buscar: ");
        int Id = new Scanner(System.in).nextInt();
        Cliente Cl = Gc.Buscar(Id);
        if (Cl != null) {
            System.out.println("========================================");
            System.out.println("   Datos de " + Cl.getNombre() + ": ");
            System.out.println(Cl);
            System.out.println("========================================");
        } else {
            System.out.println("Cliente no Encontrado!");
        }
    }
}
