package com.atenciones_medicas.atenciones_medicas.Clases;

import java.time.LocalDate;

public class AtencionMedica {
    private int idAtencionMedica;
    private LocalDate fechaAtencion;
    private String diagnostico;
    private String tratamiento;
    private EspecialidadMedica especialidad;

    public AtencionMedica(int idAtencionMedica, LocalDate fechaAtencion, String diagnostico, String tratamiento, EspecialidadMedica especialidad) {
        this.idAtencionMedica = idAtencionMedica;
        this.fechaAtencion = fechaAtencion;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.especialidad = especialidad;
    }

    public int getIdAtencionMedica() {
        return idAtencionMedica;
    }

    public LocalDate getFechaAtencion() {
        return fechaAtencion;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public EspecialidadMedica getEspecialidad() {
        return especialidad;
    }
}
