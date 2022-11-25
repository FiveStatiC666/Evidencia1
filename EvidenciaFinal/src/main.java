import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class main
{
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    public static HashMap<Integer, Medico> listaMedicos = new HashMap<Integer, Medico>();
    public static HashMap<Integer, Paciente> listaPacientes = new HashMap<Integer, Paciente>();
    public static HashMap<Integer, Cita> listaCitas=new HashMap<Integer, Cita>();

    private static Scanner scanner=new Scanner(System.in);

    public static void main(String[] args)
    {
        crearCatalagos(listaMedicos, listaPacientes);

        if(validarAcceso())
        {
            System.out.println("Usuario valido");
            menu();
        }
        else
            System.out.println("Usuario no valido");

        System.out.println("\nFin del programa");
    }

    public static void crearCatalagos(HashMap listaMedicos, HashMap listaPacientes)
    {
        String inputFilenameMedicos= "src/CitaMedica/medicos.txt";
        String inputFilenamePacientes= "src/CitaMedica/pacientes.txt";

        BufferedReader bufferedReader = null;

        String Nombre="";
        String Especialidad="";

        try
        {
            bufferedReader=new BufferedReader(new FileReader(inputFilenameMedicos));

            String linea;

            while ((linea = bufferedReader.readLine()) != null)
            {
                int coma= linea.indexOf(',');
                Nombre=linea.substring(0,coma);
                Especialidad=linea.substring(coma+1, linea.length());
                int identificador=listaMedicos.size();
                listaMedicos.put(identificador+1, new Medico(Nombre,Especialidad));
            }
        }
        catch (IOException e)
        {
            System.out.println("Excepcion detectada mientras se leia: " + e.getMessage());
        }
        finally
        {
            try
            {
                if(bufferedReader != null)
                {
                    bufferedReader.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Excepcion detectado mientras se cerraba: " + e.getMessage());
            }
        }

        try
        {
            bufferedReader=new BufferedReader(new FileReader(inputFilenamePacientes));

            String linea;

            while((linea=bufferedReader.readLine()) != null)
            {
                Nombre = linea;
                int identificador = listaPacientes.size();
                listaPacientes.put(identificador+1, new Paciente(linea));
            }
        }
        catch (IOException e)
        {
            System.out.println("Excepcion detectada mientras se leia: " + e.getMessage());
        }
        finally
        {
            try
            {
                if(bufferedReader != null)
                {
                    bufferedReader.close();
                }
            }
            catch (IOException e)
            {
                System.out.println("Excepcion detectada mientras se cerraba: " + e.getMessage());
            }
        }
    }

    private static boolean validarAcceso()
    {
        usuarios.add(new Usuario("Usuario1", "123"));
        usuarios.add(new Usuario("Usuario2", "123"));

        System.out.println("-----Agendar Cita-----");
        System.out.println("Nombre: ");
        String nombre= scanner.nextLine();
        System.out.println("Contraseña: ");
        String contraseña = scanner.nextLine();

        Usuario Admin =new Usuario(nombre, contraseña);

        return usuarios.contains(Admin);
    }

    private static void menu()
    {
        Scanner escaner=new Scanner(System.in);

        boolean terminar = false;
        String opcion;

        while(!terminar)
        {
            System.out.println("\n-----Funciones del sistema-----");
            System.out.println("1.- Dar de alta doctores");
            System.out.println("2.- Dar de alta pacientes");
            System.out.println("3.- Crear Cita");
            System.out.println("4.- Salir");

            try
            {
                System.out.println("\nSelecciona una opcion: ");
                opcion = escaner.nextLine();
                int identificador=0;
                String nombre="";

                switch (opcion)
                {
                    case "1":
                        String especialidad="";
                        System.out.println("Nombre del doctor: ");
                        nombre=scanner.nextLine();
                        System.out.println("Especialidad del doctor: ");
                        especialidad=scanner.nextLine();
                        identificador=listaMedicos.size();
                        listaMedicos.put(identificador+1, new Medico(nombre,especialidad));
                        break;

                    case "2":
                        System.out.println("Nombre del paciente: ");
                        nombre = escaner.nextLine();
                        identificador=listaPacientes.size();
                        listaPacientes.put(identificador+1,new Paciente(nombre));
                        break;

                    case "3":
                        int medico;
                        int paciente;
                        String fecha;
                        String motivo;
                        boolean valido=false;
                        do
                        {
                            System.out.println("-----Agenda de doctores-----");
                            for(Iterator<Map.Entry<Integer,Medico>> entries= listaMedicos.entrySet().iterator(); entries.hasNext();)
                            {
                                Map.Entry<Integer,Medico> entry=entries.next();
                                String output=String.format("%s. %s", entry.getKey(), entry.getValue());
                                System.out.println(output);
                            }
                            System.out.println("Seleccionar a un doctor: ");
                            medico=Integer.parseInt(scanner.nextLine());
                            valido=listaMedicos.containsKey(medico);
                        }
                        while (valido != true);
                        valido=false;

                        do
                        {
                            System.out.println("-----Agenda de pacientes-----");
                            for(Iterator<Map.Entry<Integer,Paciente>> entries = listaPacientes.entrySet().iterator(); entries.hasNext();)
                            {
                                Map.Entry<Integer, Paciente> entry= entries.next();
                                String output = String.format("%s. %s", entry.getKey(), entry.getValue());
                                System.out.println(output);
                            }
                            System.out.println("Seleccionar al paciente: ");
                            paciente=Integer.parseInt(scanner.nextLine());
                            valido=listaPacientes.containsKey(paciente);
                        }
                        while (valido != true);
                        valido=false;

                        Date testDate = null;

                        do
                        {
                            System.out.println("Seleccionar una fecha: ");
                            System.out.println("Introducir la fecha en el siguiente formato yyyy-MM-ddHH:mm:ss");
                            Scanner scanner1 =new Scanner(System.in);

                            fecha= scanner1.nextLine();
                            SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
                            String date=fecha;

                            try
                            {
                                testDate=df.parse(date);
                                valido=true;
                            }
                            catch (Exception e)
                            {
                                System.out.println("Formato invalido de fecha");
                            }

                            if (df != null)
                            {
                                if(!df.format(testDate).equals(date))
                                {
                                    System.out.println("Formato invalido de fecha");
                                }
                            }
                            else
                            {
                                valido=true;
                            }
                        }
                        while(valido != true);

                        System.out.println("Motivo de consulta: ");
                        motivo= scanner.nextLine();
                        identificador=listaCitas.size();
                        String Medico = listaMedicos.get(medico).toString();
                        int coma=Medico.indexOf(':');
                        Medico=Medico.substring(coma+2, Medico.length()).toString();
                        coma=Medico.indexOf(':');
                        Medico=Medico.substring(0,coma).toString();
                        Medico=Medico.substring(0,Medico.length()-13);
                        String Paciente = listaPacientes.get(paciente).toString();
                        coma=Paciente.indexOf(':');
                        Paciente=Paciente.substring(coma+2, Paciente.length()).toString();
                        listaCitas.put(identificador+1, new Cita(Medico, Paciente, testDate.toString(),motivo));
                        System.out.println(listaCitas.get(identificador+1));
                        break;

                    case "4":
                        terminar=true;
                        break;

                    default:
                        System.out.println("Numero no valido");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("No se escribio un numero");
                escaner.next();
            }
        }
    }
}
