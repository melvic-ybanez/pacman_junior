/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Melvic
 */
public class PacmanJunior extends JFrame
        implements ActionListener, KeyListener, MouseListener{
    private int key;
    protected int direction;
    protected Pacman pacman = new Pacman();
    protected GameArea gameArea;
    protected PacmanMap map = new PacmanMap();
    protected RandomGhost[] randomGhosts = new RandomGhost[4];
    protected WiseGhost wiseGhost;
    private boolean gameEnded;
    protected CardLayout cl = new CardLayout();
    private JPanel contentPane = new JPanel(cl);
    private MenuPane menuPage = new MenuPane(this);
    final static String GAME_PANE = "GamePanel";
    final static String MENU_PANE = "MenuPanel";
    protected JFrame authorDialog;

    public PacmanJunior(){
        setTitle("PacmanJunior1.3");
        
        reset();
        contentPane.add(gameArea,GAME_PANE);
        contentPane.add(menuPage,MENU_PANE);
        setContentPane(contentPane);
        cl.show(contentPane,MENU_PANE);

        menuPage.play.addMouseListener(this);
        menuPage.quit.addMouseListener(this);
        menuPage.author.addMouseListener(this);

        addKeyListener(this);
        createAuthorDialog();
    }
    public void createAuthorDialog(){
        authorDialog = new JFrame("Auhor");
        authorDialog.setContentPane(new AuthorPane());
        authorDialog.pack();
        authorDialog.setLocationRelativeTo(null);
        authorDialog.setVisible(false);
        authorDialog.setResizable(false);
        authorDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void createGameArea(){
        if(gameArea == null){
            gameArea = new GameArea(this);
            gameArea.setFocusable(true);
            gameArea.addKeyListener(this);
        }
        gameArea.map = map;
    }
    public void linkedGhostAndGameArea(){
        for(RandomGhost randomGhost:randomGhosts){
            randomGhost.gameArea = gameArea;
        }
        wiseGhost.gameArea = gameArea;
        wiseGhost.x = gameArea.maxX;
        wiseGhost.y = gameArea.maxY;
    }
    public void reset(){
        map = new PacmanMap();
        createGhosts();        
        pacman.x = Pacman.DIAMETER;
        pacman.y = Pacman.DIAMETER;
        pacman._x = 1;
        pacman._y = 1;
        pacman.setSpeed(5);
        pacman.setDirection(Pacman.RIGHT);
        pacman.setMouth(Pacman.OPEN);
        pacman.setMap(map);
        key = -1;
        pacman.count = 0;
        direction = -1;
        pacman.validPath = true;
        pacman.life = 100;
        pacman.setGame(this);
        createGameArea();
        linkedGhostAndGameArea();
    }
    public void createGhosts(){
        String[] ghostTypes = {
            Ghost.RED, Ghost.PINK, Ghost.GREEN, Ghost.BROWN
        };
        int[] xs = {270, 240, 270, 300};
        int[] ys = {270, 300, 300, 300};
        for(int i=0; i<randomGhosts.length; i++){
            if(randomGhosts[i] == null)
                randomGhosts[i] = new RandomGhost(map, ghostTypes[i]);
            randomGhosts[i].direction = Ghost.UP;
            randomGhosts[i].x = xs[i];
            randomGhosts[i].y = ys[i];
            randomGhosts[i]._x = xs[i]/Pacman.DIAMETER;
            randomGhosts[i]._y = ys[i]/Pacman.DIAMETER;
            randomGhosts[i].speed = 5;
            randomGhosts[i].count = 0;
            randomGhosts[i].validPath = true;
            randomGhosts[i]._count = 0;
            randomGhosts[i].range = 0;
        }
        if(wiseGhost == null)
            wiseGhost = new WiseGhost(map,Ghost.FALLEN,pacman);
        wiseGhost.speed = 3;
    }
    public void actionPerformed(ActionEvent e){

    }

    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if(source == menuPage.quit){
            System.exit(0);
        }else if(source == menuPage.play){
            reset();            
            cl.show(contentPane, GAME_PANE);
            GameRunner gr = new GameRunner();
        }else if(source == menuPage.author){
            authorDialog.setVisible(true);
        }
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {
        mouseMotion(e.getSource(), menuPage.playHoverImage, menuPage.quitHoverImage,
                menuPage.authorHoverImage);
    }

    public void mouseExited(MouseEvent e) {
        mouseMotion(e.getSource(), menuPage.playImage, menuPage.quitImage, menuPage.authorImage);
    }
    public void mouseMotion(Object source, ImageIcon playIcon,
            ImageIcon quitIcon, ImageIcon authorIcon){
        if(source == menuPage.play){
            menuPage.play.setIcon(playIcon);
        }else if(source == menuPage.quit){
            menuPage.quit.setIcon(quitIcon);
        }else if(source == menuPage.author){
            menuPage.author.setIcon(authorIcon);
        }
    }
    class GameRunner extends Thread {
        GameRunner(){
            start();
        }
        public void run(){            
            while(true){
                if(gameArea.candyCount > 0){
                    gameArea.candies.setValue(gameArea.candyCount);
                }
                if(pacman.life > 0){
                    gameArea.life.setValue(pacman.life);
                }
                if(pacman.life<= 0 || gameArea.candyCount<=0){
                    cl.show(contentPane, MENU_PANE);
                    break;
                }
                pacman.updateMouth();
                pacman.updatePosition();                
                int dx = Math.abs(pacman.x - wiseGhost.x);
                int dy = Math.abs(pacman.y - wiseGhost.y);
                if(dx < Pacman.DIAMETER-10 && dy < Pacman.DIAMETER-10){
                    pacman.life-=3;
                }
                for(RandomGhost ghost:randomGhosts){
                    ghost.updatePosition();
                    if(ghost.validPath){
                        ghost.count++;
                        if(ghost.count == Pacman.DIAMETER/ghost.speed){
                            ghost.update_X_Y();
                            if(ghost._count == ghost.range){
                                if(!ghost.ghostType.equals(Ghost.RED)){
                                    ghost.setNextDirection();
                                    ghost._count = 0;
                                }
                            }else ghost._count++;
                            ghost.count = 0;
                        }
                    }else ghost.setNextDirection();
                    dx = Math.abs(pacman.x - ghost.x);
                    dy = Math.abs(pacman.y - ghost.y);
                    if(dx < Pacman.DIAMETER-10 && dy < Pacman.DIAMETER-10){
                        pacman.life-=5;
                    }
                }
                gameArea.repaint();                
                try{
                    Thread.sleep(45);
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
                if(pacman.validPath){
                    if(direction == KeyEvent.VK_RIGHT || direction == KeyEvent.VK_LEFT){
                        if(pacman.x%Pacman.DIAMETER == 0){
                            pacman.update_X_Y();
                            if(key != -1){
                                direction = key;
                                key = -1;
                            }
                        }
                    }else if(direction == KeyEvent.VK_UP || direction == KeyEvent.VK_DOWN){
                        if(pacman.y%Pacman.DIAMETER == 0){
                            pacman.update_X_Y();
                            if(key != -1){
                                direction = key;
                                key = -1;
                            }
                        }
                    }
                }
            }
        }
    }
    public void keyPressed(KeyEvent e){
        key = e.getKeyCode();
        if(direction == -1 || !pacman.validPath){
            direction = key;
        }
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}
}
