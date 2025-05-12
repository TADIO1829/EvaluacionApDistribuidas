import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class servidor {
    
    public static List<Estudiante> estudiantes = new ArrayList<>();

    public static void main(String[] args) {
        cargarEstudiantes();

        try (DatagramSocket socketUDP = new DatagramSocket(9876)) {
           

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);

                // Lanzar hilo para procesar esta solicitud
                new Thread(new HiloAtencionCliente(peticion, socketUDP)).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cargarEstudiantes() {
        estudiantes.add(new Estudiante(24359, "Adrian Cadena", "987654321", "Desarrollo de software", 3, true));
        estudiantes.add(new Estudiante(36263, "Carlos Perez", "912345678", "Desarrollo de software", 5, true));
        estudiantes.add(new Estudiante(92615, "Madeline Sierra", "934567890", "Psicologia", 2, true));
        estudiantes.add(new Estudiante(86453, "Nayeli Chuquisala", "945678123", "Criminologia", 4, true));
        estudiantes.add(new Estudiante(40650, "Alyson Cadena", "956781234", "Idiomas", 1, false));
        estudiantes.add(new Estudiante(38221, "Fiorela Herrera", "967812345", "Odontología", 6, true));
        estudiantes.add(new Estudiante(94157, "Cesar Abad", "978123456", "Mecatronica", 2, true));
        estudiantes.add(new Estudiante(35704, "Francisco Martinez", "989234567", "Periodismo", 3, false));
        estudiantes.add(new Estudiante(78272, "Eduardo Jacome", "990345678", "Veterinaria", 7, true));
        estudiantes.add(new Estudiante(75834, "Gonzalo Hidalgo", "901456789", "Contabilidad", 5, false));
    }
}
