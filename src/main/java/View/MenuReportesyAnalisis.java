package View;

import java.util.Scanner;
import Controler.GestionarAnalisis_Implement;
import Controler.GestionAnalisis;
import Model.Celular;
import java.util.ArrayList;

public class MenuReportesyAnalisis {

    public void MenuReportesyAnalisis() {
        int op = 0;
        Scanner sc = new Scanner(System.in);
        MenuGenerarReporte mGr = new MenuGenerarReporte();
        do {
            System.out.println("""
                                ========================================
                                =          REPORTES Y ANALISIS         = 
                                ========================================
                                = [1] Generar Reportes.                =
                                = [2] Generar Analisis.                =
                                = [3] Regresar.                        =
                                ========================================
                                """);
            System.out.print("  Seleccione una opcion: ");
            op = sc.nextInt();
            while (op < 0 || op > 3) {
                System.out.println("  Error! Ingrese una opcion valida!");
                op = sc.nextInt();
            }
            switch (op) {
                case 1 ->
                    mGr.MenuGenerarReporte();
                case 2 ->
                    MenuGenerarAnalisis();
            }
        } while (op != 3);
    }

    private void MenuGenerarAnalisis() {
        int op = 0;
        GestionAnalisis Ga = new GestionarAnalisis_Implement();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("""
                                ========================================
                                =         ANALISIS / CONSULTAS         = 
                                ========================================
                                = [1] Celulares con Stock Bajo.        =
                                = [2] Celulares mas Vendidos.          =
                                = [3] Ventas por mes.                  =
                                = [4] Regresar.                        =
                                ========================================
                                """);
            System.out.print("  Seleccione una opcion: ");
            op = sc.nextInt();
            while (op < 0 || op > 4) {
                System.out.println("  Error! Ingrese una opcion valida!");
                op = sc.nextInt();
            }
            switch (op) {
                case 1 -> {
                    ArrayList<Celular> lista = Ga.StockBajo();

                    if (lista.isEmpty()) {
                        System.out.println("No hay celulares con stock bajo.");
                    } else {
                        System.out.println("========================================");
                        System.out.println("=       CELULARES CON STOCK BAJO       =");
                        System.out.println("========================================");
                        for (Celular c : lista) {
                            System.out.println("ID: " + c.getId());
                            System.out.println("Modelo: " + c.getModelo());
                            System.out.println("Stock: " + c.getStock());
                            System.out.println("========================================");
                        }
                    }
                }
                case 2 ->
                    Ga.MasVendido();
                case 3 ->
                    Ga.VentasPorMes();
                case 4 ->
                    System.out.println("Regresando...");
            }
        } while (op != 4);
    }
}
