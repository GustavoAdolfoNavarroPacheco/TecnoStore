package View;

import java.util.Scanner;

public class Main {

    public void MenuPrincipal() {
        int op = 0;
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
            System.out.print("  Seleccione una opcion: ");
            op = sc.nextInt();
            while (op < -1 || op > 4) {
                System.out.println("  Error! Ingrese una opcion valida!");
                op = sc.nextInt();
            }
            switch (op) {
                case 1 -> mCe.MenuCelular();
                case 2 -> mCl.MenuCliente();
                case 3 -> mGv.MenuGestionVentas();
                case 4 -> mRa.MenuReportesyAnalisis();
            }
        } while (op != 0);
    }
}
