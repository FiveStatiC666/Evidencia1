import java.time.*;

public class Cita
{
    public Cita(String nom, String pac, String fec, String mot)
    {
        medico=nom;
        paciente=pac;
        fecha=fec;
        motivo=mot;
    }

    public String toString()
    {
        return "Medico: " + medico + "\nPaciente: " + paciente + "\nFecha: " + fecha + "\nMotivo: " + motivo;
    }

    private String medico;
    private String paciente;
    private String fecha;
    private String motivo;
}
