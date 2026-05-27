package co.udc.desarrollo.web.calificationsRest.domain.models;

import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Value
@AllArgsConstructor
@Data
public final class CalificationModel {

    CalificationId id;
    CalificationFecha fecha;
    CalificationDocente docente;
    CalificationAsignatura asignatura;
    CalificationCarrera carrera;
    CalificationUniversidad universidad;
    CalificationPeriodo periodo;
    CalificationAcvitidadEvaluada actividadEvaluada;
    CalificationPorcentaje porcentaje;
    StudentId idEstudiante;
    CalificationNota nota;


}
