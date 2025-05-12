public class Estudiante {
    private int id;
    private String nombre;
    private String telefono;
    private String carrera;
    private int semestre;
    private boolean gratuidad;

    public Estudiante(int id, String nombre, String telefono, String carrera, int semestre, boolean gratuidad) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.carrera = carrera;
        this.semestre = semestre;
        this.gratuidad = gratuidad;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getCarrera() { return carrera; }
    public int getSemestre() { return semestre; }
    public boolean tieneGratuidad() { return gratuidad; }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +  "Nombre: " + nombre +"\n"+ "Teléfono: " + telefono+"\n" +
               "Carrera: " + carrera +"\n"+ "Semestre: " + "\n"+semestre +
               "Gratuidad: " + (gratuidad ? "Sí" : "No");
    }
}
