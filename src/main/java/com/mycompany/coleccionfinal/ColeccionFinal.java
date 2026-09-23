package com.mycompany.coleccionfinal;

import java.util.ArrayList;
import java.util.Scanner;

class Producto {
    private int id;
    private String nombre;
    private double precio;

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Precio: $" + String.format("%.2f", precio);
    }
}

class GestorProductos {
    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("Producto agregado exitosamente");
    }

    public void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados");
            return;
        }
        System.out.println("LISTA DE PRODUCTOS");
        for (Producto prod : productos) {
            System.out.println(prod);
        }
    }

    public boolean actualizarProducto(int id, double nuevoPrecio) {
        for (Producto prod : productos) {
            if (prod.getId() == id) {
                prod.setPrecio(nuevoPrecio);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarProducto(int id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == id) {
                productos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean existeProducto(int id) {
        for (Producto prod : productos) {
            if (prod.getId() == id) return true;
        }
        return false;
    }
}

public class ColeccionFinal {

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero entero valido");
            }
        }
    }

    private static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un valor numerico o decimal valido, intenta de nuevo");
            }
        }
    }

    public static void main(String[] args) {
        GestorProductos gestor = new GestorProductos();
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("sistema de gestion de productos");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Actualizar precio de producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            
            opcion = leerEntero(scanner, "Seleccione una opcion: ");

            switch (opcion) {
                case 1:
                    int id = leerEntero(scanner, "Ingrese ID unico: ");

                    if (gestor.existeProducto(id)) {
                        System.out.println("Error: El ID " + id + " ya existe");
                        break;
                    }

                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();

                    double precio = leerDouble(scanner, "Ingrese precio: ");

                    gestor.agregarProducto(new Producto(id, nombre, precio));
                    break;

                case 2:
                    gestor.mostrarProductos();
                    break;

                case 3:
                    int idAct = leerEntero(scanner, "Ingrese ID a actualizar: ");
                    double nPrecio = leerDouble(scanner, "Ingrese nuevo precio: ");

                    if (!gestor.actualizarProducto(idAct, nPrecio)) {
                        System.out.println("Error: Producto no encontrado");
                    } else {
                        System.out.println("Precio actualizado correctamente");
                    }
                    break;

                case 4:
                    int idElim = leerEntero(scanner, "Ingrese ID a eliminar: ");

                    if (!gestor.eliminarProducto(idElim)) {
                        System.out.println("Error: Producto no encontrado");
                    } else {
                        System.out.println("Producto eliminado correctamente");
                    }
                    break;

                case 5:
                    System.out.println("Final");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}