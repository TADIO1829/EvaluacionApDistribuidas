import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class HiloAtencionCliente implements Runnable {
    private DatagramPacket peticion;
    private DatagramSocket socketUDP;

    public HiloAtencionCliente(DatagramPacket peticion, DatagramSocket socketUDP) {
        this.peticion = peticion;
        this.socketUDP = socketUDP;
    }

    @Override
    public void run() {
        try {
            String mensaje = new String(peticion.getData(), 0, peticion.getLength()).trim();
            System.out.println("Atendiendo solicitud para ID: " + mensaje);

            String respuesta;

            try {
                int idBuscado = Integer.parseInt(mensaje);
                Estudiante estudiante = buscarPorId(idBuscado);
                respuesta = (estudiante != null) ? estudiante.toString() : "Estudiante no encontrado";
            } catch (NumberFormatException e) {
                respuesta = "ID inválido";
            }

            byte[] datosRespuesta = respuesta.getBytes();
            DatagramPacket respuestaPacket = new DatagramPacket(
                datosRespuesta, datosRespuesta.length,
                peticion.getAddress(), peticion.getPort()
            );

            socketUDP.send(respuestaPacket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Estudiante buscarPorId(int id) {
        for (Estudiante e : servidor.estudiantes) {
            if (e.getId() == id) return e;
        }
        return null;
    }
}
