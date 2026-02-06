package View;

import java.util.Scanner;

public class Main {

    int op = 0;
    public Scanner sc = new Scanner(System.in);

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
        System.out.print(" Selecciona una opcion: ");
        op = new Scanner(System.in).nextInt();
        while (op < -1 || op > 4) {
                System.out.println("Error! Ingrese una opcion valida!");                
        }
        switch (op) {
            case 1:
                
            case 2:
                
                
        }
    }
}
