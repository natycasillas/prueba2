import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Formulario {
    private JPanel rootPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JButton busquedaCodigoButton;
    private JButton busquedaNombreButton;
    private JButton busquedaSignoButton;
    private JButton Borrar;
    private JButton Actualizar;
    private JButton Ingresar;
    private JButton Limpiar;
    static final String DB_URL = "jdbc:mysql://localhost/prueba";
    static final String USER = "root";
    static final String PASS = "root_bas3";
    public Formulario() {
        busquedaCodigoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               /* String busqueda = textField1.getText();
                String codigou = textField1.getText();
                String cedulau = textField2.getText();
                String nombreu = textField3.getText();
                String fechau = textField4.getText();
                if (busqueda==1){

                }*/
            }



        });
        busquedaNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String busquedaNombre = textField1.getText();
                
            }
        });
        busquedaSignoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Borrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String borrar = textField1.getText();
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                ) {
                    String sql = "DELETE FROM PERSONAS WHERE CODpers = " + borrar;
                    Statement statement = null;
                    try {
                        statement = conn.createStatement();
                        int filasAfectadas = statement.executeUpdate(sql);

                        if (filasAfectadas > 0) {
                            System.out.println("Registro eliminado con éxito.");
                        } else {
                            System.out.println("No se encontró el registro con el ID especificado.");
                        }
                    } finally {
                        if (statement != null) {
                            statement.close();
                        }
                    }
                }catch (SQLException e3){
                    throw new RuntimeException(e3);
                }
            }
        });
        Actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigou = textField1.getText();
                String cedulau = textField2.getText();
                String nombreu = textField3.getText();
                String fechau = textField4.getText();
                String signou = comboBox1.getSelectedItem().toString();
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                ){
                    String sql = "UPDATE PERSONAS SET cedula = ?, Nombre = ?, fechaN = ?, signo_zodiaco = ? WHERE CODpers = ?";

                    PreparedStatement preparedStatement = conn.prepareStatement(sql);

                    preparedStatement.setString(1, cedulau);
                    preparedStatement.setString(2, nombreu);
                    preparedStatement.setString(3, fechau);
                    preparedStatement.setString(4, signou);
                    preparedStatement.setString(5, codigou);

                    int filasAfectadas = preparedStatement.executeUpdate();

                    if (filasAfectadas > 0) {
                        System.out.println("Registro actualizado con éxito.");
                    } else {
                        System.out.println("No se encontró el registro con el ID especificado.");
                    }

                    preparedStatement.close();
                    conn.close();
                } catch (SQLException n2) {
                    n2.printStackTrace();
                }
            }
        });
        Ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = textField1.getText();
                String cedula = textField2.getText();
                String nombre = textField3.getText();
                String fecha = textField4.getText();
                String signo = comboBox1.getSelectedItem().toString();
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                ) {
                    String sql = "INSERT INTO PERSONAS (CODpers, cedula, Nombre, fechaN, signo_zodiaco) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    try {
                        preparedStatement.setString(1, codigo);
                        preparedStatement.setString(2, cedula);
                        preparedStatement.setString(3, nombre);
                        preparedStatement.setString(4, fecha);
                        preparedStatement.setString(5, signo);
                        int filasAfectadas = preparedStatement.executeUpdate();

                        if (filasAfectadas > 0) {
                            System.out.println("Registro insertado con éxito.");
                        } else {
                            System.out.println("Error al insertar el registro.");
                        }
                    } finally {
                        if (preparedStatement != null) {
                            preparedStatement.close();
                        }
                    }
                }catch (SQLException n1){
                    n1.printStackTrace();
                }
            }
        });
        Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario");
        frame.setContentPane(new Formulario().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
