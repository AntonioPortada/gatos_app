/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jorge.gatos_app;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author antonioportada
 */
public class Gatos_app {

    public static void main(String[] args) throws IOException {
        
        int opcionMenu = -1;
        String[] botones = {
            "1.- Ver gatos",
            "2.- Ver favoritos",
            "3.- Salir"
        };
        
        do {
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos Java", "Menú principal", JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);
            
            for(int i=0; i<botones.length; i++) {
                if(opcion.equals(botones[i])) {
                    opcionMenu = i;
                }
            }
            
            switch(opcionMenu) {
                case 0 -> GatoService.verGatos();
                case 1 -> GatoService.verFavoritos();
            }
        } while(opcionMenu != 1);
    }
}
