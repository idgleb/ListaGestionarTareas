import java.time.LocalDateTime;

public class Estado {
    //LocalDateTime.parse("0001-01-01 00:00:00", MisFunciones.formatter);
    LocalDateTime pendiente_fecha = null;
    LocalDateTime en_proceso_fecha = null;
    LocalDateTime completada_fecha = null;

    public BotonEstado getEstado() {

        if (completada_fecha != null) return BotonEstado.COMPLETADA;
        if (en_proceso_fecha != null) return BotonEstado.EN_PROCESO;
        if (pendiente_fecha != null) return BotonEstado.PENDIDA;

        return null;

    }

    public void setPendiente() {
        pendiente_fecha = LocalDateTime.now();
        en_proceso_fecha = null;
        completada_fecha = null;
    }

    public void setEn_proceso() {
        en_proceso_fecha = LocalDateTime.now();
        completada_fecha = null;
    }

    public void setCompletada() {
        completada_fecha = LocalDateTime.now();
    }

    public LocalDateTime getPendiente_fecha() {
        return pendiente_fecha;
    }

    public LocalDateTime getEn_proceso_fecha() {
        return en_proceso_fecha;
    }

    public LocalDateTime getCompletada_fecha() {
        return completada_fecha;
    }

    @Override
    public String toString() {
        String pend = "", en_proceso = "", completada = "";
        if (pendiente_fecha != null)
            pend = BotonEstado.PENDIDA.toString() + "=" + pendiente_fecha.format(MisFunciones.formatter) + " || ";
        if (en_proceso_fecha != null)
            en_proceso = BotonEstado.EN_PROCESO.toString() + "=" + en_proceso_fecha.format(MisFunciones.formatter) + " || ";
        if (completada_fecha != null)
            completada = BotonEstado.COMPLETADA.toString() + "=" + completada_fecha.format(MisFunciones.formatter) + " || ";


        return "Estado: " + pend + en_proceso + completada;
    }
}
