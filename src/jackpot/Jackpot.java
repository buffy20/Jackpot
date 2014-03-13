/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jackpot;

import java.text.NumberFormat;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class Jackpot {

    private static double saldo = 0;
    private int[] numAleatorios = new int[3];
    private Random generador = new Random();
    public static final int MONEDA50 = 0;
    public static final int MONEDA1 = 1;
    public static final int MONEDA2 = 2;
    private NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance();
    private static double deposito = 1000;
    private static double premio;

    public Jackpot() {
    }
    
    public boolean isEmpty() {
        if (saldo == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    private void disableButton(JButton jButtonPalanca, JButton jButtonCobrar){
        jButtonPalanca.setEnabled(false);
        jButtonCobrar.setEnabled(false);
    }

    public void play(JLabel jLabelImagen1, JLabel jLabelImagen2, JLabel jLabelImagen3, JLabel jLabelPremio, JLabel jLabelSaldo) {
        if (!this.isEmpty()) {
            saldo -= 0.50;
            for (int i = 0; i < numAleatorios.length; i++) {
                int numeroAleatorio = generador.nextInt(5);
                numAleatorios[i] = numeroAleatorio;
                System.out.println("Random: " + numeroAleatorio);
                System.out.println("Array: " + numAleatorios[i]);
            }

            jLabelImagen1.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + numAleatorios[0] + ".png")));
            jLabelImagen2.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + numAleatorios[1] + ".png")));
            jLabelImagen3.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + numAleatorios[2] + ".png")));

            if (numAleatorios[0] == 4 && numAleatorios[1] == 4 && numAleatorios[2] == 4) {
                premio += 100;
                saldo += 100;
            }
            if (numAleatorios[0] == 3 && numAleatorios[1] == 3 && numAleatorios[2] == 4) {
                premio += 20;
                saldo += 20;
            }
            if (numAleatorios[0] == 3 && numAleatorios[1] == 3 && numAleatorios[2] == 3) {
                premio += 20;
                saldo += 20;
            }
            if (numAleatorios[0] == 2 && numAleatorios[1] == 2 && numAleatorios[2] == 4) {
                premio += 15;
                saldo += 15;
            }
            if (numAleatorios[0] == 2 && numAleatorios[1] == 2 && numAleatorios[2] == 2) {
                premio += 15;
                saldo += 15;
            }
            if (numAleatorios[0] == 1 && numAleatorios[1] == 1 && numAleatorios[2] == 1) {
                premio += 10;
                saldo += 10;
            }
            if (numAleatorios[0] == 1 && numAleatorios[1] == 1 && numAleatorios[2] == 4) {
                premio += 10;
                saldo += 10;
            }
            if (numAleatorios[0] == 0 && numAleatorios[1] == 0) {
                premio += 5;
                saldo += 5;
            }
            if (numAleatorios[0] == 0) {
                premio += 1;
                saldo += 1;
            }
        }
        
        System.out.println("Saldo depósito: " + deposito);
        jLabelPremio.setText("" + formatoMoneda.format(premio));
        jLabelSaldo.setText("" + formatoMoneda.format(saldo));
    }

    public void payment(JLabel jLabelSaldo) {
        
        deposito -= saldo;
        saldo = 0;
        this.checkDeposit(deposito);
        jLabelSaldo.setText("" + formatoMoneda.format(saldo));
        System.out.println("Saldo depósito: " + deposito);

    }

    public void insertCoin(int moneda, JLabel jLabelSaldo) {
        switch (moneda) {
            case MONEDA50:
                saldo += 0.50;
                deposito += 0.50;
                break;
            case MONEDA1:
                saldo += 1;
                deposito += 1;
                break;
            case MONEDA2:
                saldo += 2;
                deposito += 2;
                break;
        }
        jLabelSaldo.setText("" + formatoMoneda.format(saldo));
        System.out.println("Saldo depósito: " + deposito);
    }
    
    public void checkDeposit(double deposito){
        if(deposito == 0 || saldo > deposito){
            JOptionPane.showMessageDialog(null, "Depósito vacío, pase por caja", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
