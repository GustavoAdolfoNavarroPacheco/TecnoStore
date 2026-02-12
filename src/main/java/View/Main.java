package View;

import java.util.Scanner;

public class Main {

    public void MenuPrincipal() {
        int op;
        Scanner sc = new Scanner(System.in);
        MenuCliente mCl = new MenuCliente();
        MenuCelular mCe = new MenuCelular();
        MenuGestionVentas mGv = new MenuGestionVentas();
        MenuReportesyAnalisis mRa = new MenuReportesyAnalisis();

        do {
            System.out.println("""
                                ========================================
                                =              TECNOSTORE              = 
                                ========================================
                                = [1] Gestionar Celulares              =
                                = [2] Gestionar Clientes.              =
                                = [3] Gestionar Ventas.                =
                                = [4] Reportes y Analisis.             =
                                = [0] Salir.                           =
                                ========================================
                                """);
            while (true) {
                System.out.print("  Seleccione una opcion: ");
                if (sc.hasNextInt()) {
                    op = sc.nextInt();
                    sc.nextLine();

                    if (op >= 0 && op <= 4) {
                        break;
                    } else {
                        System.out.println("  Error! Ingrese una opcion valida (0-4).");
                    }
                } else {
                    System.out.println("  Error! Debe ingresar un numero.");
                    sc.nextLine();
                }
            }
            switch (op) {
                case 1 -> mCe.MenuCelular();
                case 2 -> mCl.MenuCliente();
                case 3 -> mGv.MenuGestionVentas();
                case 4 -> mRa.MenuReportesyAnalisis();
                case 0 -> System.out.println("Saliendo del sistema...");
            }

        } while (op != 0);
    }
}