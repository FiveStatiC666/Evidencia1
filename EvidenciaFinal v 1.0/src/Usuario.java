public class Usuario
{
    private String nombre;
    private String contraseña;

    public Usuario(String nom, String cont)
    {
        nombre=nom;
        contraseña=cont;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Usuario)
        {
            Usuario Admin = (Usuario)obj;
            if(nombre.equals(Admin.nombre) && contraseña.equals(Admin.contraseña))
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
