package rovinproject;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;

public class InititialeTouche {

    static void GetSerialPort(String portID, int frequence) {

        try {
            CommPortIdentifier ididentif = CommPortIdentifier.getPortIdentifier(portID);

            SerialPort myport = (SerialPort) ididentif.open("Connection", 0);

            myport.setSerialPortParams(frequence, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            InputStream inputStream = myport.getInputStream();
             Popup a = null;
             a = new Popup();
            while (true) {

                PointerInfo pointeur = MouseInfo.getPointerInfo();
                int x;
                int y;
                Robot objMouse;
                try {
                    objMouse = new Robot();

                    x = (int) pointeur.getLocation().getX();
                    y = (int) pointeur.getLocation().getY();
                    int valeur_envoye;
                    try {

                        valeur_envoye = (byte) inputStream.read();
                        if(valeur_envoye != 0 && valeur_envoye != -1)
                           System.out.println("valeur;" + valeur_envoye);
                        //valeur_envoye=(int)inputStream.read();
                        switch (valeur_envoye) {

                            case 68:
                                if(a.isVisible() == true)
                                {
                                    a.setVisible(false);
                                }
                                break;
                            case 17:
                               Process runtime = Runtime.getRuntime().exec("cmd /c  start Iexplore.exe \"www.google.fr\"");
                               break;
                            
                            case 8:
                                
                               a.setVisible(true);
                               break;
                          
                            case 2:
                              
                                objMouse.mouseMove(x + 13, y);
                                  System.out.print("droite" + valeur_envoye);
                                break;

                            case 3:
                                objMouse.mouseMove(x - 13, y);
                                System.out.print("gauche" + valeur_envoye);
                                break;

                            case 1:
                                objMouse.mouseMove(x, y + 13);
                                  System.out.print("bas" + valeur_envoye);
                                break;
                            case 0:
                                objMouse.mouseMove(x, y - 13);
                                System.out.print("haut" + valeur_envoye);
                                break;
                           
                        }
                       
                    } catch (IOException ex) {
                        System.out.print("erreur lors de la lecture de puis le port serie");
                    }
                    //System.out.println("send[2] = "+(int)sends[2]);
                    //appel à la fonction convertion vers des degrée celsus
                    //prinTemperature(resultat);	
                    //do with the buffer whatever you want!
                } catch (AWTException e) {
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            GetSerialPort("COM9", 9600);
        } catch (Exception ex) {
            // TODO Auto-generated catch block
            ex.getMessage();
        }

    }

}
