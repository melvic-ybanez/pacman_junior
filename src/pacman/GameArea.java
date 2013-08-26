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
public class GameArea extends JPanel{
    protected int maxX;
    protected int maxY;
    protected int minX;
    protected int minY;
    protected PacmanJunior game;
    protected Pacman pacman;
    protected PacmanMap map;
    protected RandomGhost[] randomGhosts;
    protected WiseGhost wiseGhost;
    protected JProgressBar life = new JProgressBar();
    protected JProgressBar candies = new JProgressBar();
    private int maxCandyCount = -1;
    protected int candyCount;
            
    public GameArea(PacmanJunior game){
        setLayout(new BorderLayout());
        this.game = game;
        this.map = game.map;
        this.randomGhosts = game.randomGhosts;
        this.wiseGhost = game.wiseGhost;
        this.pacman = game.pacman;
        setPreferredSize(new Dimension(PacmanMap.WIDTH*Pacman.DIAMETER,
                PacmanMap.HEIGHT*Pacman.DIAMETER+
                Pacman.DIAMETER));
        maxX = getPreferredSize().width-Pacman.DIAMETER*2;
        minX = Pacman.DIAMETER;
        maxY = getPreferredSize().height-Pacman.DIAMETER*3;
        minY = Pacman.DIAMETER;

        add(createSouthPane(),BorderLayout.SOUTH);
    }
    public JPanel createSouthPane(){
        JPanel mainPane = new JPanel(new GridLayout(1,2));
        Resources resources = new Resources();
        JLabel lifeIcon = new JLabel(new ImageIcon(
                resources.getResource("right_open")));
        JLabel candyIcon = new JLabel(new ImageIcon(
                resources.getResource("big_candy")));

        JPanel lifePane = new JPanel();
        lifePane.add(lifeIcon);
        lifePane.add(life);
        lifePane.setBackground(Color.BLACK);

        JPanel candyPane = new JPanel();
        candyPane.add(candyIcon);
        candyPane.add(candies);
        candyPane.setBackground(Color.BLACK);
        
        life.setStringPainted(true);
        candies.setStringPainted(true);

        mainPane.add(lifePane);
        mainPane.add(candyPane);
        mainPane.setBackground(Color.BLACK);
        return mainPane;
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(PacmanMap.BGCOLOR);
        g.fillRect(0,0,getWidth(),getHeight());

        int x = 0;
        int y = 0;
        candyCount = 0;
        for(int i=0; i<map.model.length; i++){
            for(int j=0; j<map.model[i].length; j++){
                switch(map.model[i][j]){
                    case PacmanMap.BRICK:
                        g.drawImage(PacmanMap.BRICK_IMAGE,x,y,this);
                        break;
                    case PacmanMap.SMALL_CANDY:
                        g.drawImage(PacmanMap.SMALL_CANDY_IMAGE,x,y,this);
                        candyCount++;
                        break;
                    case PacmanMap.BIG_CANDY:
                        g.drawImage(PacmanMap.BIG_CANDY_IMAGE,x,y,this);
                        candyCount++;
                        break;
                }
                x+=Pacman.DIAMETER;
            }
            x = 0;
            y+=Pacman.DIAMETER;
        }
        if(maxCandyCount == -1){
            maxCandyCount = candyCount;
            candies.setMaximum(maxCandyCount);
        }
        g.drawImage(pacman.getImage(),pacman.x, pacman.y, this);
        wiseGhost.findPacman();
        g.drawImage(wiseGhost.image,wiseGhost.x,wiseGhost.y,this);
        for(RandomGhost ghost:randomGhosts){
            g.drawImage(ghost.image,ghost.x,ghost.y,this);
        }
    }
}
