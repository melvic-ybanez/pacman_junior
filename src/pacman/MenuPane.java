/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Melvic
 */
public class MenuPane extends JPanel{
    private Resources resources = new Resources();
    protected JLabel pacmanJunior = new JLabel();
    protected JLabel play = new JLabel();
    protected JLabel quit = new JLabel();
    protected JLabel author = new JLabel();
    protected ImageIcon pacmanJuniorImage;
    protected ImageIcon playImage;
    protected ImageIcon playHoverImage;
    protected ImageIcon quitImage;
    protected ImageIcon quitHoverImage;
    protected ImageIcon authorImage;
    protected ImageIcon authorHoverImage;
    private PacmanJunior game;
    protected boolean playOptionSelected = false;

    public MenuPane(PacmanJunior game){
        this.game = game;
        pacmanJuniorImage = new ImageIcon(resources.getResource("pacmanJunior"));
        playImage = new ImageIcon(resources.getResource("play"));
        quitImage = new ImageIcon(resources.getResource("quit"));
        playHoverImage = new ImageIcon(resources.getResource("playHover"));
        quitHoverImage = new ImageIcon(resources.getResource("quitHover"));
        authorImage = new ImageIcon(resources.getResource("author"));
        authorHoverImage = new ImageIcon(resources.getResource("authorHover"));

        pacmanJunior.setIcon(pacmanJuniorImage);
        play.setIcon(playImage);
        quit.setIcon(quitImage);
        author.setIcon(authorImage);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20,0,60,0);
        c.gridx = 0;
        c.gridy = 0;
        add(pacmanJunior,c);
        c.insets = new Insets(10,0,10,0);
        c.gridy = 1;
        add(play,c);
        c.gridy = 2;
        add(quit,c);
        c.gridy = 3;
        c.insets = new Insets(50,0,0,0);
        add(author,c);

        setBackground(Color.BLACK);
    }         
}
