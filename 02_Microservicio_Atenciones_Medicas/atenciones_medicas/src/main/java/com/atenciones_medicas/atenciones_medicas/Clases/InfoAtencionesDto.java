package com.atenciones_medicas.atenciones_medicas.Clases;

public class InfoAtencionesDto {
    private String especialidad;
    private int cantidadPacientes;

    public InfoAtencionesDto(String especialidad, int cantidadPacientes) {
        this.especialidad = especialidad;
        this.cantidadPacientes = cantidadPacientes;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public int getCantidadPacientes() {
        return cantidadPacientes;
    }
}
