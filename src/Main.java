import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        llenarDatos();

        String[] opciones = {"Agregar Tarea", "Eliminar Tarea", "Cambiar Estado",
                "Listar Por Prioridad", "Listar por ESTADO", "Actualizar"};


        int opc = -1;
        do {
            opc = JOptionPane.showOptionDialog(null, Tareas.listaTareas, null,
                    0, 0, null, opciones, 0);
            switch (opc) {
                case 0:
                    Tareas.addTarea();
                    break;
                case 1:
                    Tareas.eliminar();
                    break;
                case 2:
                    Tareas.cambiarEstado();
                    break;
                case 3:
                    Tareas.listarPorPrioridad();
                    break;
                case 4:
                    Tareas.listarPorEstado();
                    break;
                case 5:
                    break;

            }
        } while (opc != -1);


    }

    private static void llenarDatos() {
        Estado estadoTarea1 = new Estado();
        Estado estadoTarea2 = new Estado();
        Estado estadoTarea3 = new Estado();
        Estado estadoTarea4 = new Estado();
        Estado estadoTarea5 = new Estado();
        Estado estadoTarea6 = new Estado();
        Estado estadoTarea7 = new Estado();
        estadoTarea1.setPendiente();
        estadoTarea2.setPendiente();
        estadoTarea3.setPendiente();
        estadoTarea4.setPendiente();
        estadoTarea5.setPendiente();
        estadoTarea6.setPendiente();
        estadoTarea7.setPendiente();

        Tareas tarea1 = new Tareas("Caminar", Prioridad.ALTA, estadoTarea1, 23);
        Tareas tarea2 = new Tareas("Comprar comida", Prioridad.MEDIA, estadoTarea2, 15);
        Tareas tarea3 = new Tareas("Estudiar para el examen", Prioridad.ALTA, estadoTarea3, 5);
        Tareas tarea4 = new Tareas("Llamar al electricista", Prioridad.BAJA, estadoTarea4, 8);
        Tareas tarea5 = new Tareas("Limpiar la casa", Prioridad.MEDIA, estadoTarea5, 10);
        Tareas tarea6 = new Tareas("Hacer ejercicio", Prioridad.ALTA, estadoTarea6, 12);
        Tareas tarea7 = new Tareas("Enviar el informe", Prioridad.ALTA, estadoTarea7, 20);

        Tareas.listaTareas.add(tarea1);

        Tareas.listaTareas.add(tarea2);

        Tareas.listaTareas.add(tarea3);

        Tareas.listaTareas.add(tarea4);

        Tareas.listaTareas.add(tarea5);

        Tareas.listaTareas.add(tarea6);

        Tareas.listaTareas.add(tarea7);

    }
}