import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteUDPGUI extends JFrame {

    private JTextField textFieldId;
    private JTextArea textAreaRespuesta;
    private JButton btnBuscar;

    public ClienteUDPGUI() {

        setTitle("Buscar Estudiante");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new FlowLayout()); 

        textFieldId = new JTextField(20); 
        textAreaRespuesta = new JTextArea(10, 30); 
        textAreaRespuesta.setEditable(false); 
        btnBuscar = new JButton("Buscar Estudiante");

        add(new JLabel("Ingrese ID del Estudiante:"));
        add(textFieldId);
        add(btnBuscar);
        add(new JScrollPane(textAreaRespuesta)); 


        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idEstudiante = textFieldId.getText();
                if (!idEstudiante.isEmpty()) {
                    enviarSolicitud(idEstudiante);
                } else {
                    textAreaRespuesta.setText("Por favor, ingrese una ID válida.");
                }
            }
        });
    }

    private void enviarSolicitud(String idEstudiante) {
        try {
            
            DatagramSocket socketUDP = new DatagramSocket();
            InetAddress hostServidor = InetAddress.getByName("localhost");

           
            byte[] buffer = idEstudiante.getBytes();

       
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, hostServidor, 9876);
            socketUDP.send(peticion);

            
            byte[] bufferRespuesta = new byte[1024];
            DatagramPacket respuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
            socketUDP.receive(respuesta);

            
            String respuestaServidor = new String(respuesta.getData(), 0, respuesta.getLength());
            textAreaRespuesta.setText("Datos del estudiante: " + "\n" + respuestaServidor);

            
            socketUDP.close();
        } catch (Exception e) {
            textAreaRespuesta.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClienteUDPGUI().setVisible(true);
            }
        });
    }
}
