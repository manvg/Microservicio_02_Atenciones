package com.atenciones_medicas.atenciones_medicas.Clases;

import java.time.LocalDate;

public class Paciente {
    private int idPaciente;
    private String rut;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String genero;
    private LocalDate fechaNacimiento;
    private AtencionMedica atencionMedica;

    public Paciente(int idPaciente, String rut, String nombres, String apellidoPaterno, String apellidoMaterno, String genero, LocalDate fechaNacimiento, AtencionMedica atencionMedica){
        this.idPaciente = idPaciente;
        this.rut = rut;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.atencionMedica = atencionMedica;
    }

    public int getIdPaciente(){
        return idPaciente;
    }

    public String getRut(){
        return rut;
    }

    public String getNombres(){
        return nombres;
    }

    public String getApellidoPaterno(){
        return apellidoPaterno;
    }

    public String getApellidoMaterno(){
        return apellidoMaterno;
    }

    public String getGenero(){
        return genero;
    }

    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }

    public AtencionMedica getAtencionMedica(){
        return atencionMedica;
    }
}
