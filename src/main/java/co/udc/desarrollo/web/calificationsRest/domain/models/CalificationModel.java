package co.udc.desarrollo.web.calificationsRest.domain.models;

import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.calification.*;
import co.udc.desarrollo.web.calificationsRest.domain.valueObjects.student.StudentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Value
@AllArgsConstructor
@Data
public final class CalificationModel {

    CalificationId id;
    CalificationDocente docente;
    CalificationAsignatura asignatura;
    CalificationCarrera carrera;
    CalificationUniversidad universidad;
    CalificationPeriodo periodo;
    CalificationActividadEvaluada actividadEvaluada;
    CalificationPorcentaje porcentaje;
    StudentId idEstudiante;
    CalificationNota nota;


}
