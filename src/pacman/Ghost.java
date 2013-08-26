/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;

import java.awt.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Melvic
 */
public class Ghost {
    protected int x;
    protected int y;
    protected int _x;
    protected int _y;
    protected int count;
    protected int speed;
    protected int _count;
    protected int range;
    protected int direction;
    protected PacmanMap map;
    protected Image image;
    protected String ghostType;
    protected boolean validPath;
    protected GameArea gameArea;
    final static String RED = "red";
    final static String PINK = "pink";
    final static String GREEN = "green";
    final static String BROWN = "brown";
    final static String FALLEN = "fallen";
    final static String BLUE = "blue";
    final static int RIGHT = 0;
    final static int LEFT = 1;
    final static int UP = 2;
    final static int DOWN = 3;
    int damage;
    
    public Ghost(PacmanMap map, String ghostType){
        this.map = map;
        this.ghostType = ghostType;
        try{
            image = ImageIO.read(new Resources().getResource(ghostType+"_ghost"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void update_X_Y(){
        switch (direction) {
            case UP:
                _y--;
                break;
            case DOWN:
                _y++;
                break;
            case RIGHT:
                _x++;
                break;
            case LEFT:
                _x--;
                break;
        }
    }
}
