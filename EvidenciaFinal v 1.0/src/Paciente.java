public class Paciente
{
    public Paciente(String nom)
    {
        nombre=nom;
    }

    public String toString()
    {
        return "Paciente: " + nombre;
    }

    private String nombre;
}
