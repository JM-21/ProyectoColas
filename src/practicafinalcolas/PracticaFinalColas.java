package practicafinalcolas;

import javax.swing.JOptionPane;

public class PracticaFinalColas {

    public static void main(String[] args) {
        int op = 0;
        Cola cola = new Cola();
        Cola cola1 = new Cola();
        Cola cola2 = new Cola();
        Cola colaUnida = new Cola();
        int turno = 1;
        boolean partidaCola = false;

        do {
            try {
                boolean entradaInvalida = true;
                while (entradaInvalida) {
                    try {
                        op = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "          Macondo EPS\n\n"
                                + "Seleccione una Opcion:\n"
                                + "1. Ingresar turno\n"
                                + "2. Finalizar turno\n"
                                + "3. Mostrar personas en cola\n"
                                + "4. Cancelar turno\n"
                                + "5. Partir cola\n"
                                + "6. Unir Colas\n"
                                + "0. Salir"));
                        entradaInvalida = false;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Valor inválido. Por favor, ingrese solo números enteros.");
                    }
                }
                switch (op) {
                    case 1:
                        if (!partidaCola) {
                            NodoPersona nuevoTurno = new NodoPersona();
                            nuevoTurno = nuevoTurno.crearTurno(turno);
                            boolean agregado = cola.insertar(nuevoTurno);
                            if (agregado) {
                                JOptionPane.showMessageDialog(null, "Turno agendado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al agendar turno");
                            }
                            turno += 1;
                        } else {
                            NodoPersona nuevoTurno2 = new NodoPersona();
                            nuevoTurno2 = nuevoTurno2.crearTurno(turno);
                            boolean agg = cola2.insertar(nuevoTurno2);
                            turno += 1;
                            if (agg) {
                                JOptionPane.showMessageDialog(null, "Turno agendado con exito");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al agendar turno");
                            }
                        }
                        break;
                    case 2:
                        if (partidaCola) {
                            if (!cola1.colaVacia()) {
                                JOptionPane.showMessageDialog(null, "Finalizo el turno de:\n"
                                        + cola1.extraerDeCola().toString());
                                turno--;
                            } else if (!cola2.colaVacia()) {
                                JOptionPane.showMessageDialog(null, "Finalizo el turno de:\n"
                                        + cola2.extraerDeCola().toString());
                                turno--;
                            } else if (!colaUnida.colaVacia()) {
                                JOptionPane.showMessageDialog(null, "Finalizo el turno de:\n"
                                        + colaUnida.extraerDeCola().toString());
                                turno--;
                            } else {
                                JOptionPane.showMessageDialog(null, "La cola esta vacia");
                            }
                        } else {

                            if (!cola.colaVacia()) {
                                JOptionPane.showMessageDialog(null, "Finalizo el turno de:\n"
                                        + cola.extraerDeCola().toString());
                                turno--;
                            } else {
                                JOptionPane.showMessageDialog(null, "La cola esta vacia");
                            }
                        }
                        break;
                    case 3:

                        if (partidaCola) {
                            if (!cola1.colaVacia()) {
                                System.out.println("Cola 1:");
                                cola1.mostrarCola();
                            } else {
                                JOptionPane.showMessageDialog(null, "La cola 1 esta vacia");

                            }

                            if (!cola2.colaVacia()) {
                                System.out.println("Cola 2:");
                                cola2.mostrarCola();
                            } else {
                                JOptionPane.showMessageDialog(null, "La cola 2 esta vacia");

                            }

                        } else if (!colaUnida.colaVacia()) {
                            System.out.println("Cola Unida:");
                            colaUnida.mostrarCola();
                        } else {
                            cola.mostrarCola();
                        }
                        break;
                    case 4:
                        if (partidaCola) {
                            if (!cola1.colaVacia()) {
                                JOptionPane.showMessageDialog(null, "Buscando en la cola 1");
                                String cc = JOptionPane.showInputDialog(null, "Ingrese su documento: ");
                                cola1.eliminarDeCola(cc);
                                if (cola1.eliminarDeCola(cc) == null) {
                                    if (!cola2.colaVacia()) {
                                        JOptionPane.showMessageDialog(null, "Buscando en la cola 2");
                                        cola2.eliminarDeCola(cc);
                                    }
                                }
                                turno--;
                            }
                        } else {

                            if (!cola.colaVacia()) {
                                String cc = JOptionPane.showInputDialog(null, "Ingrese su documento: ");
                                cola.eliminarDeCola(cc);
                                turno--;
                            } else {
                                JOptionPane.showMessageDialog(null, "La cola esta vacia");
                            }
                        }
                        break;

                    case 5:
                        int turno2 = turno - 1;
                        JOptionPane.showMessageDialog(null, "El tamaño de la cola es de: " + turno2);
                        boolean entradaInvalida5 = true;
                        int num1 = 0;
                        while (entradaInvalida5) {
                            String num = JOptionPane.showInputDialog(null, "Ingrese el numero de elementos que quiere en la cola 1, el resultante se guardara en la cola 2 ");
                            num1 = Integer.parseInt(num);
                            if (num.matches("\\d+") || turno2 > num1 || num1 != 0) {
                                entradaInvalida5 = false;
                            } else {
                                JOptionPane.showMessageDialog(null, "Ingrese un numero valido");
                            }
                        }
                        int num2 = turno - num1;

                        //  while (entradaInvalida2) {
                        //    String num = JOptionPane.showInputDialog(null, "Ingrese el numero de elementos que quiere en la cola 2 ");
                        //  num2 = Integer.parseInt(num);
                        //if (turno2 > num2) {
                        //  entradaInvalida2 = false;
                        //} else {
                        //  JOptionPane.showMessageDialog(null, "Ingrse un numero valido que no supere el tamaño de la cola");
                        //}
                        //}
                        partidaCola = true;
                        cola.partirCola(num1, num2, cola1, cola2);
                        break;

                    case 6:
                        if (cola1.colaVacia() || cola2.colaVacia()) {
                            JOptionPane.showMessageDialog(null, "Una de las colas está vacía. No se puede realizar la unión.");
                        } else {

                            cola = colaUnida.unirColas(cola1, cola2);
                            System.out.println("Cola Unida:");
                            cola.actualizarTurnos();
                            cola.mostrarCola();
                            cola1.reiniciarCola();
                            cola2.reiniciarCola();
                            partidaCola = false;
                        }
                        break;
                    case 0:
                        op = 0;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opcion invalida");
                        break;
                }

            } catch (NumberFormatException e) {

            }

        } while (op != 0);
    }

}
