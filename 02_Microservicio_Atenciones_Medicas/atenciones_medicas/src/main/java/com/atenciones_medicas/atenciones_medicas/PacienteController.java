package com.atenciones_medicas.atenciones_medicas;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atenciones_medicas.atenciones_medicas.Clases.AtencionMedica;
import com.atenciones_medicas.atenciones_medicas.Clases.EspecialidadMedica;
import com.atenciones_medicas.atenciones_medicas.Clases.InfoAtencionesDto;
import com.atenciones_medicas.atenciones_medicas.Clases.Paciente;
import com.atenciones_medicas.atenciones_medicas.Excepciones.CustomException;

@RestController 
public class PacienteController {
    //#region Cargar datos
    //Variables
    List<Paciente> lstPacientes = new ArrayList<>();
    List<EspecialidadMedica> lstEspecialidades = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public PacienteController(){
        //Cargar especialidades medicas
        lstEspecialidades.add(new EspecialidadMedica(1, "MEDICINA GENERAL"));
        lstEspecialidades.add(new EspecialidadMedica(2, "CARDIOLOGÍA"));
        lstEspecialidades.add(new EspecialidadMedica(3, "OFTALMOLOGÍA"));
        //Fechas de nacimiento Pacientes
        LocalDate fechaNacimiento_p1 = LocalDate.of( 1999, 1, 1);
        LocalDate fechaNacimiento_p2 = LocalDate.of( 2000, 2, 10);
        LocalDate fechaNacimiento_p3 = LocalDate.of( 2001, 3, 15);
        LocalDate fechaNacimiento_p4 = LocalDate.of( 2002, 4, 20);
        LocalDate fechaNacimiento_p5 = LocalDate.of( 2003, 5, 25);
        //Fechas de atenciones medicas Pacientes
        LocalDate fechaAtencion_p1 = LocalDate.of( 2024, 03, 18);
        LocalDate fechaAtencion_p2 = LocalDate.of( 2024, 03, 18);
        LocalDate fechaAtencion_p3 = LocalDate.of( 2024, 03, 20);
        LocalDate fechaAtencion_p4 = LocalDate.of( 2024, 03, 21);
        LocalDate fechaAtencion_p5 = LocalDate.of( 2024, 03, 21);
        //Llenar lista de Pacientes
        lstPacientes.add(new Paciente(1, "20554685-5", "Manuel", "Valdés", "Guerra", "Masculino", fechaNacimiento_p1, (new AtencionMedica(1, fechaAtencion_p1, "Diagnóstico 1", "Tratamiento 1", (new EspecialidadMedica(1, "MEDICINA GENERAL"))))));
        lstPacientes.add(new Paciente(2, "17962733-7", "Valeria", "Torres", "Aguirre", "Femenino", fechaNacimiento_p2, (new AtencionMedica(2, fechaAtencion_p2, "Diagnóstico 2", "Tratamiento 2", (new EspecialidadMedica(3, "OFTALMOLOGÍA"))))));
        lstPacientes.add(new Paciente(3, "15228889-1", "Pedro", "Sanchez", "Sosa", "Masculino", fechaNacimiento_p3, (new AtencionMedica(3, fechaAtencion_p3, "Diagnóstico 3", "Tratamiento 3", (new EspecialidadMedica(2, "CARDIOLOGÍA"))))));
        lstPacientes.add(new Paciente(4, "8607893-7", "Cecilia", "Rodriguez", "Alvarez", "Femenino", fechaNacimiento_p4, (new AtencionMedica(4, fechaAtencion_p4, "Diagnóstico 4", "Tratamiento 4", (new EspecialidadMedica(1, "MEDICINA GENERAL"))))));
        lstPacientes.add(new Paciente(5, "14394002-0", "Ricardo", "Gonzalez", "Suarez", "Masculino", fechaNacimiento_p5, (new AtencionMedica(5, fechaAtencion_p5, "Diagnóstico 5", "Tratamiento 5", (new EspecialidadMedica(3, "OFTALMOLOGÍA"))))));
    
    }
    //#endregion

    @GetMapping("/pacientes")
    public List<Paciente> getPacientes(){
        return lstPacientes;
    }

    //Obtener paciente por rut
    @GetMapping("/pacientes/{rut}")
    public Paciente getPacientesByRut(@PathVariable String rut){
        for(Paciente paciente : lstPacientes){
            if (paciente.getRut().equals(rut)) {
                return paciente;
            }
        }

        throw new CustomException("El rut ingresado no existe.");
    }

    //Obtener lista de pacientes por rango de fecha en la que fue atendido
    @GetMapping("/pacientes/atencion/rango-fecha/{fechaDesde}/{fechaHasta}")
    public List<Paciente> getPacientesRangoFecha(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta){
        if (fechaHasta.isBefore(fechaDesde)) {
            throw new CustomException("Rango de fecha no válido.");
        }
        List<Paciente> lstRetorno = new ArrayList<>();
        for(Paciente paciente : lstPacientes){
            if (!paciente.getAtencionMedica().getFechaAtencion().isBefore(fechaDesde) && !paciente.getAtencionMedica().getFechaAtencion().isAfter(fechaHasta)) {
                    lstRetorno.add(paciente);
                }
        }
        if(!lstRetorno.isEmpty()){
            return lstRetorno;
        }else{
            throw new CustomException("No existen atenciones médicas entre " + fechaDesde.format(formatter) + " y " + fechaHasta.format(formatter) + ".");
        }
    }

    //Obtener información de la cantidad de pacientes que se han tenido atenciones médicas por cada especialidad
    @GetMapping("/cantidad-atenciones")
    public List<InfoAtencionesDto> getCantidasPacientesPorDeparamento(){
        List<InfoAtencionesDto> lstRetorno= new ArrayList<>();
        int contadorPacientes = 0;

        for (EspecialidadMedica especialidad : lstEspecialidades){
            for(Paciente Paciente : lstPacientes){
                if (Paciente.getAtencionMedica().getEspecialidad().getIdEspecialidadMedica() == especialidad.getIdEspecialidadMedica()) {
                    contadorPacientes++;
                }
            }
            lstRetorno.add(new InfoAtencionesDto(especialidad.getNombreEspecialidad(), contadorPacientes));
            contadorPacientes = 0;
        }
        if(!lstRetorno.isEmpty()){
            return lstRetorno;
        }else{
            throw new CustomException("No existen registros.");
        }
    }
}
