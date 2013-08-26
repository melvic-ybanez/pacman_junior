/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;
import javax.swing.*;
/**
 *
 * @author Melvic
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowUI();
            }
        });
    }
    public static void createAndShowUI(){
        boolean nimbusFound = false;
        for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()){
            if(info.getName().equals("Nimbus")){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "An error occured!!","Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                nimbusFound = true;
                break;
            }
        }
        if(!nimbusFound){
            int option = JOptionPane.showConfirmDialog(null,
                    "Nimbus Look and Feel not found.\n"
                    + "Do you want to proceed?", "Warning",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if(option == JOptionPane.NO_OPTION) return;
        }
        PacmanJunior game = new PacmanJunior();
        game.pack();
        game.setLocationRelativeTo(null);
        game.setResizable(false);
        game.setVisible(true);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
