package modelo;


public class Alumno 
{
    private int NL;
    private String nombre;
    private String paterno;
    private String materno;

    public Alumno() 
    {
        this.NL=0;
        this.nombre="";
        this.paterno="";
        this.materno="";
    }

    public Alumno(int nL, String nombre, String paterno, String materno) 
    {
        this.NL = nL;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
    }

    public int getNL() 
    {
        return NL;
    }

    public void setNL(int NL) 
    {
        this.NL = NL;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getPaterno() 
    {
        return paterno;
    }

    public void setPaterno(String paterno) 
    {
        this.paterno = paterno;
    }

    public String getMaterno() 
    {
        return materno;
    }

    public void setMaterno(String materno) 
    {
        this.materno = materno;
    }
}