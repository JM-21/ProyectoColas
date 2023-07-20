package practicafinalcolas;

import javax.swing.JOptionPane;

public class Cola {

    private NodoPersona inicioCola, finalCola;
    private Cola cola2;
    String infoCola = "";

    public Cola() {
        inicioCola = null;
        finalCola = null;
        cola2 = null;
    }

    public NodoPersona getInicioCola() {
        return inicioCola;
    }

    public void setInicioCola(NodoPersona inicioCola) {
        this.inicioCola = inicioCola;
    }

    public NodoPersona getFinalCola() {
        return finalCola;
    }

    public void setFinalCola(NodoPersona finalCola) {
        this.finalCola = finalCola;
    }

    public Cola getCola2() {
        return cola2;
    }

    public void setCola2(Cola cola2) {
        this.cola2 = cola2;
    }

    public String getInfoCola() {
        return infoCola;
    }

    public void setInfoCola(String infoCola) {
        this.infoCola = infoCola;
    }

    public boolean colaVacia() {
        if (inicioCola == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertar(NodoPersona nodoNuevo) {
        boolean agregado = false;
        if (colaVacia()) {
            inicioCola = nodoNuevo;
            finalCola = nodoNuevo;
            agregado = true;
        } else {
            finalCola.setSiguiente(nodoNuevo);
            finalCola = nodoNuevo;
            agregado = true;
        }
        System.out.println("Turno creado:" + "\n" + nodoNuevo.toString());
        return agregado;
    }

    public NodoPersona extraerDeCola() {
        if (!colaVacia()) {
            NodoPersona nodoNuevo = inicioCola;
            if (inicioCola.getSiguiente() == null) {
                inicioCola = null;
                finalCola = null;
            } else {
                inicioCola = inicioCola.getSiguiente();
            }
            
            return nodoNuevo;
        } else {
            return null;
        }
    }

    public NodoPersona eliminarDeCola(String cc) {
        if (colaVacia()) {
            JOptionPane.showMessageDialog(null, "La cola está vacía");
            return null;
        }
        NodoPersona nodoAux = inicioCola;
        if (nodoAux.getCc().equalsIgnoreCase(cc)) {
            String confi = JOptionPane.showInputDialog(null, "¿Seguro que desea cancelar el turno?");
            if (confi.equalsIgnoreCase("si")) {
                inicioCola = nodoAux.getSiguiente();
                if (inicioCola == null) {
                    finalCola = null;
                }
                actualizarTurnos();
                JOptionPane.showMessageDialog(null, "Turno cancelado");
                return nodoAux;
            }
        }

        NodoPersona nodoAnt = inicioCola;
        NodoPersona nodoActual = inicioCola.getSiguiente();

        while (nodoActual != null) {

            if (nodoActual.getCc().equalsIgnoreCase(cc)) {
                String confi = JOptionPane.showInputDialog(null, "¿Seguro que desea cancelar el turno?");
                if (confi.equalsIgnoreCase("si")) {
                    nodoAnt.setSiguiente(nodoActual.getSiguiente());
                    if (nodoActual == finalCola) {
                        finalCola = nodoAnt;
                    }
                    actualizarTurnos();
                    JOptionPane.showMessageDialog(null, "Cliente eliminado de la cola");
                    return nodoActual;
                }
            }
            nodoAnt = nodoActual;
            nodoActual = nodoActual.getSiguiente();
        }
        actualizarTurnos();
        JOptionPane.showMessageDialog(null, "Cliente no encontrado en la cola");
        return null;
    }

    public void mostrarColaRe() {
        if (!colaVacia()) {
            NodoPersona nodoAux = inicioCola;
            String colaInvertida = "";
            while (nodoAux != null) {
                infoCola += nodoAux + " ";
                nodoAux = nodoAux.getSiguiente();
            }

            String cadena[] = infoCola.split(" ");

            for (int i = cadena.length - 1; i >= 0; i--) {
                colaInvertida += " " + cadena[i];
            }
            System.out.println(colaInvertida.toString());
            infoCola = "";
        } else {
            JOptionPane.showMessageDialog(null, "La cola esta vacia");
        }

    }

    public void mostrarCola() {
        if (!colaVacia()) {
            Cola.this.mostrarColaRecursivo(inicioCola);
        } else {
            JOptionPane.showMessageDialog(null, "La cola está vacía");
        }
    }

    public void partirCola(int num1, int num2, Cola cola1, Cola cola2) {
        
        NodoPersona nodoAux = inicioCola;
        

        if (colaVacia()) {
            JOptionPane.showMessageDialog(null, "La cola está vacía");
        } else {
            int posicion = 1;

            while (nodoAux != null) {
                if (posicion <= num1) {
                    cola1.insertar(new NodoPersona(nodoAux.getPosicion(), nodoAux.getNomnbre(), nodoAux.getCc()));
                } else if (posicion > num1 && posicion <= num1 + num2) {
                    cola2.insertar(new NodoPersona(nodoAux.getPosicion(), nodoAux.getNomnbre(), nodoAux.getCc()));
                }

                nodoAux = nodoAux.getSiguiente();
                posicion++;
            }

            System.out.println("Cola 1:");
            //cola1.actualizarTurnos();
            cola1.mostrarCola();
            System.out.println("");
            System.out.println("Cola 2:");
            //cola2.actualizarTurnos();
            cola2.mostrarCola();
        }
        reiniciarCola();
    }

    public Cola unirColas(Cola cola1, Cola cola2) {
        Cola colaResultante = new Cola();

        if (cola1.colaVacia() && cola2.colaVacia()) {
            JOptionPane.showMessageDialog(null, "Ambas colas están vacías");
            return colaResultante;
        } else if (cola1.colaVacia()) {
            JOptionPane.showMessageDialog(null, "La cola 1 está vacía");
            return cola2;
        } else if (cola2.colaVacia()) {
            JOptionPane.showMessageDialog(null, "La cola 2 está vacía");
            return cola1;
        } else {
            NodoPersona nodoAux = cola1.inicioCola;

            while (nodoAux != null) {
                colaResultante.insertar(new NodoPersona(nodoAux.getPosicion(), nodoAux.getNomnbre(), nodoAux.getCc()));
                nodoAux = nodoAux.getSiguiente();
            }

            nodoAux = cola2.inicioCola;

            while (nodoAux != null) {
                colaResultante.insertar(new NodoPersona(nodoAux.getPosicion(), nodoAux.getNomnbre(), nodoAux.getCc()));
                nodoAux = nodoAux.getSiguiente();
            }

            colaResultante.actualizarTurnos();
            return colaResultante;
        }
    }

    private void mostrarColaRecursivo(NodoPersona nodo) {
        if (nodo != null) {
            System.out.println(nodo + " ");
            Cola.this.mostrarColaRecursivo(nodo.getSiguiente());
        }
    }

    public void actualizarTurnos() {
        NodoPersona nodoAux = inicioCola;
        int nuevoTurno = 1;

        while (nodoAux != null) {
            nodoAux.setPosicion(nuevoTurno);
            nodoAux = nodoAux.getSiguiente();
            nuevoTurno++;
        }
    }

    public void reiniciarCola() {
        inicioCola = null;
        finalCola = null;
    }

}
