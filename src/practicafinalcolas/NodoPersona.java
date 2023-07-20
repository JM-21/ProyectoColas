package practicafinalcolas;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class NodoPersona {

    int posicion;
    String nombre;
    String cc;
    NodoPersona siguiente;

    public NodoPersona(int posicion, String nomnbre, String cc) {
        this.posicion = posicion;
        this.nombre = nomnbre;
        this.cc = cc;
    }

    public NodoPersona() {
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNomnbre() {
        return nombre;
    }

    public void setNomnbre(String nomnbre) {
        this.nombre = nomnbre;
    }

    public NodoPersona getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPersona siguiente) {
        this.siguiente = siguiente;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    @Override
    public String toString() {
        return "Paciente:" + nombre + "\n" + "Documento:" + cc + "\n" + "Turno:" + posicion + "\n";
    }

    public NodoPersona crearTurno(int turno) {
        String cc1 = null;
        JOptionPane.showMessageDialog(null,
                "Su turno en la cola es: " + turno);

        String nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre: ");
        boolean entradaInvalida = true;
        while (entradaInvalida) {
            String cc = JOptionPane.showInputDialog(null, "Ingrese su numero de documento: ");
            if (cc.matches("\\d+")) {
                cc1 = cc;
                entradaInvalida = false;
            }else{
                JOptionPane.showMessageDialog(null, "Numero de documneto invalido, solo numero porfavor");
            }
        }
        NodoPersona nuevoTurno = new NodoPersona(turno, nombre, cc1);
        return nuevoTurno;

    }

}
