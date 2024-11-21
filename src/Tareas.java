import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tareas {
    String nombre;
    Prioridad prioridad;
    Estado estado;
    int secundosParaHacerlo;
    boolean vencido;
    static List<Tareas> listaTareas = new ArrayList<>();


    public Tareas(String nombre, Prioridad prioridad, Estado estado, int secundosParaHacerlo) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.estado = estado;
        this.secundosParaHacerlo = secundosParaHacerlo;
        this.vencido = false;

    }

    public static void addTarea() {

        String nombreTarea = MisFunciones.pedirStrNoVacio("nombreTarea?");
        if (nombreTarea==null) return;

        int idPrioridad = JOptionPane.showOptionDialog(null, "Elige prioridad", null,
                0, 0, null, Prioridad.values(), 0);
        if (idPrioridad == -1) return;
        Prioridad prioridadTarea = Prioridad.values()[idPrioridad];

        Estado estadoTarea = new Estado();
        estadoTarea.setPendiente();

        int sec = MisFunciones.pedirNumeroMasCero("Secundos para hacerlo?");
        if (sec == -1) return;

        Tareas tarea = new Tareas(nombreTarea, prioridadTarea, estadoTarea, sec);

        Tareas.listaTareas.add(tarea);


    }

    public static List<Tareas> getListaTareas() {
        return listaTareas;
    }

    public static void eliminar() {

        if (listaTareas.isEmpty()) return;

        List<JCheckBox> checkBoxes = new ArrayList<>();
        for (Tareas tarea : listaTareas) {
            checkBoxes.add(new JCheckBox(tarea.toString()));
        }

        int idTarea = JOptionPane.showConfirmDialog(null, checkBoxes.toArray(), "Selecciona las tareas PARA ELIMINAR", JOptionPane.OK_CANCEL_OPTION, 0);
        if (idTarea != JOptionPane.OK_OPTION) return;

        int iter = 0;
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                listaTareas.remove(iter);
                iter--;
            }
            iter++;
        }

    }

    public static void cambiarEstado() {
        if (listaTareas.isEmpty()) return;

        JComboBox<String> comboBox = new JComboBox<>();
        for (Tareas tarea : listaTareas) {
            comboBox.addItem(tarea.toString());
        }


        int idTarea = JOptionPane.showConfirmDialog(null, comboBox, "Selecciona una tarea PARA CAMBIAR estado",
                JOptionPane.OK_CANCEL_OPTION, 0);
        if (idTarea != JOptionPane.OK_OPTION) return;

        int opc = JOptionPane.showOptionDialog(null, null, null, 0, 0,
                null, BotonEstado.values(), 0);

        switch (BotonEstado.values()[opc]) {
            case BotonEstado.EN_PROCESO:
                listaTareas.get(comboBox.getSelectedIndex()).estado.setEn_proceso();
                break;
            case BotonEstado.COMPLETADA:
                listaTareas.get(comboBox.getSelectedIndex()).estado.setCompletada();
                break;
            case BotonEstado.PENDIDA:
                listaTareas.get(comboBox.getSelectedIndex()).estado.setPendiente();
                break;

        }


    }

    public static void listarPorPrioridad() {
        JOptionPane.showMessageDialog(null,
                "Lista de tareas ordenada por PRIORIDAD: \n" + ordenarPrioridad());
    }

    public static List<Tareas> ordenarPrioridad() {

        List<Tareas> copiaListaTareas = new ArrayList<>(Tareas.listaTareas);

        List<Tareas> tareasPorPrioridad = new ArrayList<>();

        for (Prioridad prioridad : Prioridad.values()) {

            Iterator<Tareas> iterator = copiaListaTareas.iterator();
            while (iterator.hasNext()){
                Tareas tarea = iterator.next();
                if (tarea.prioridad == prioridad) {
                    tareasPorPrioridad.add(tarea);
                    iterator.remove();
                }
            }

        }
        return tareasPorPrioridad;
    }

    public static void listarPorEstado() {
        List<Tareas> copiaListaTareas = new ArrayList<>(ordenarPrioridad());
        List<Tareas> tareasPorEstado = new ArrayList<>();
        for (BotonEstado botonEstado : BotonEstado.values()) {
            Iterator<Tareas> iterator = copiaListaTareas.iterator();
            while (iterator.hasNext()){
                Tareas tarea = iterator.next();
                if (tarea.estado.getEstado()== botonEstado) {
                    tareasPorEstado.add(tarea);
                    iterator.remove();
                }
            }
        }
        JOptionPane.showMessageDialog(null,"Lista por ESTADO: \n" + tareasPorEstado);
    }

    public void actualizar() {
        boolean hecha = (estado.getEstado().equals(BotonEstado.COMPLETADA));
        vencido = estado.pendiente_fecha.plusSeconds(secundosParaHacerlo).isBefore(LocalDateTime.now()) && !hecha;
    }

    @Override
    public String toString() {
        actualizar();
        return "\nTarea: " + nombre + " || " + prioridad + " || " + estado + " || Sec para hacerlo: " + secundosParaHacerlo +
                (vencido ? " ||  !!!VENCIDA!!!" : "") + (estado.getEstado().equals(BotonEstado.COMPLETADA) ? "  ||  HECHA:))" : "") + "\n";
    }
}
