package Actividades.AVLTREE;

public class Test {
    public static void main(String[] args) {
        AVLtree<Integer> arbol = new AVLtree<>();

        int[] valores = {39, 27, 50, 18, 35, 46, 87, 7, 24};
        System.out.println("Insertando nodos:");
        for (int v : valores) {
            try {
                arbol.insert(v);
                System.out.print("Insertado: " + v + " - ");
                arbol.recorridoPorAmplitud();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\nRecorrido por niveles (después de inserciones):");
        arbol.recorridoPorAmplitud();

        System.out.println("\nEliminando nodos 50 y 18...");
        try {
            arbol.remove(50);
            System.out.print("Eliminado: 50 - ");
            arbol.recorridoPorAmplitud();

            arbol.remove(18);
            System.out.print("Eliminado: 18 - ");
            arbol.recorridoPorAmplitud();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nRecorrido por niveles (después de eliminaciones):");
        arbol.recorridoPorAmplitud();
    }
}
