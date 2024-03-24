package com.atenciones_medicas.atenciones_medicas.Clases;

public class EspecialidadMedica {
    private int idEspecialidadMedica;
    private String nombreEspecialidad;

    public EspecialidadMedica(int idEspecialidadMedica, String nombreEspecialidad) {
        this.idEspecialidadMedica = idEspecialidadMedica;
        this.nombreEspecialidad = nombreEspecialidad;
    }
    public int getIdEspecialidadMedica() {
        return idEspecialidadMedica;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }
}
