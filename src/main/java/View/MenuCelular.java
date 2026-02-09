package View;

import Controler.Conexion;
import java.util.Scanner;
import Controler.GestionarCelular_Implement;
import Controler.GestionCelular;
import Model.Celular;
import java.sql.Statement;
import java.util.ArrayList;

public class MenuCelular {
    
    
    public void MenuCelular() {
        int op = 0;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("""
                                ========================================
                                =         TECNOSTORE - CELULAR         = 
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
    
    GestionCelular Gc = new GestionarCelular_Implement();
    
    private void Registrar() {
        Celular Ce = new Celular();
        
        System.out.println("Modelo: ");
        Ce.setModelo(new Scanner(System.in).nextLine());
        
        System.out.println("Sistema Operativo: ");
        Ce.setSistema_operativo(new Scanner(System.in).nextLine());
        
        System.out.println("Gama: ");
        Ce.setGama(new Scanner(System.in).nextLine());
        
        System.out.println("Precio: ");
        Ce.setPrecio(new Scanner(System.in).nextLine());
        
        System.out.println("Stock: ");
        Ce.setStock(new Scanner(System.in).nextLine());
        
        System.out.println("Marca: ");
        Ce.setId_marca(new Scanner(System.in).nextLine());
        
        Gc.Registrar(Ce);
    }
    
    private void Actualizar() {
        System.out.println("========================================");
        System.out.print("  ID Celular a Actualizar: ");
        int Id = new Scanner(System.in).nextInt();
        
        Celular Ce = Gc.Buscar(Id);
        
        if (Ce != null) {
            System.out.println("========================================");
            System.out.println("   Datos de " + Ce.getModelo() + ": ");
            System.out.println(Ce);
            System.out.println("""
                                ========================================
                                = Seleccione opcion a modificar:       =
                                = [1] Modelo.                          =
                                = [2] Sistema Operativo.               =
                                = [3] Gama.                            =
                                = [4] Precio.                          =
                                = [5] Stock.                           =
                                = [6] Marca.                        =
                                ========================================
                               """);
            int op = new Scanner(System.in).nextInt();
            
            while (op < 1 || op > 6) {
                System.out.println("Error! Ingrese una opcion valida!");
                op = new Scanner(System.in).nextInt();
            }
            switch(op){
                case 1:
                    System.out.println("Ingrese el nuevo Modelo: ");
                    Ce.setModelo(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion Realizada!");
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo Sistema Operativo: ");
                    Ce.setSistema_operativo(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion Realizada!");
                case 3:
                    System.out.println("Ingrese la nueva Gama: ");
                    Ce.setGama(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion Realizada!");
                case 4:
                    System.out.println("Ingrese el nuevo Precio: ");
                    Ce.setPrecio(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion Realizada!");
                case 5:
                    System.out.println("Ingrese el nuevo Stock: ");
                    Ce.setStock(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion Realizada!");
                case 6:
                    System.out.println("Ingrese la nueva Marca ");
                    Ce.setId_marca(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion Realizada!");
            }
            Gc.Actualizar(Ce, Id);
        } else{
            System.out.println("Celular no Encontrado!");
        }
    }
    
    private void Eliminar() {
        System.out.println("""
                               ========================================
                               =               ELIMINAR               =
                               ========================================
                               """);
        System.out.print("ID Celular a Eliminar: ");
        int Id = new Scanner(System.in).nextInt();
        
        Celular Ce = Gc.Buscar(Id);
        
        if (Ce != null) {
            System.out.println("========================================");
            System.out.println("   Datos de " + Ce.getModelo() + ": ");
            System.out.println(Ce);
            System.out.println("========================================");

            Gc.Eliminar(Id);
        } else{
            System.out.println("Celular no Encontrado!");
        }
    }
    
    private void Listar() {
        ArrayList<Celular> Celulares = Gc.Listar();
        System.out.println("""
                               ========================================
                               =                LISTAR                =
                               ========================================
                                 Lista de Celulares:
                               """);
        for (Celular Ce : Celulares) {
            System.out.println(Ce);
        }
    }

    
    private void Buscar() {
        System.out.println("""
                           ========================================
                           =                BUSCAR                =
                           ========================================
                           """);
        System.out.print("Ingrese el ID del Celular a buscar: ");
        int Id = new Scanner(System.in).nextInt();
        Celular Ce = Gc.Buscar(Id);
        if (Ce != null) {
            System.out.println("========================================");
            System.out.println("   Datos de " + Ce.getModelo() + ": ");
            System.out.println(Ce);
            System.out.println("========================================");
        } else{
            System.out.println("Celular no Encontrado!");
        } 
    }
}
