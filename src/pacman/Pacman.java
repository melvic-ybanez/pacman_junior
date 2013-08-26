/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;

/**
 *
 * @author Melvic
 */
public class Pacman {
    protected int x;
    protected int y;
    protected int _x;
    protected int _y;
    protected int life;
    private int speed;
    protected int count;
    private PacmanMap map;
    private GameArea gameArea;
    private PacmanJunior game;
    protected boolean validPath;
    private String mouth;
    private String direction;
    final static int DIAMETER = 30;
    final static String OPEN = "open";
    final static String SEMIOPEN = "semiopen";
    final static String CLOSE = "close";
    final static String RIGHT = "right";
    final static String LEFT = "left";
    final static String UP_RIGHT = "up_right";
    final static String UP_LEFT = "up_left";
    final static String DOWN_RIGHT = "down_right";
    final static String DOWN_LEFT = "down_left";

    public void setGame(PacmanJunior game){
        this.game = game;
        this.gameArea = game.gameArea;
    }
    public void setMap(PacmanMap map){
        this.map = map;
    }
    public void setDirection(String direction){
        this.direction = direction;
    }
    public void setMouth(String mouth){
        this.mouth = mouth;
    }
    public String getMouth(){
        return mouth;
    }
    public String getDirection(){
        return direction;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public int getSpeed(){
        return speed;
    }
    public Image getImage(){
        Image image = null;
        try{
            image = ImageIO.read(new Resources().getResource(
                    direction+"_"+mouth));
        }catch(Exception e){}
        return image;
    }
    public void updateMouth(){
        switch (count) {
            case 1:
            case 5:
                mouth = OPEN;
                break;
            case 2:
            case 4:
                mouth = SEMIOPEN;
                break;
            case 3:
                mouth = CLOSE;
                break;
            case 6:
                count = 1;
                break;
        }
        count++;
    }
    public void update_X_Y(){
        switch (game.direction) {
            case KeyEvent.VK_UP:
                _y--;
                break;
            case KeyEvent.VK_DOWN:
                _y++;
                break;
            case KeyEvent.VK_RIGHT:
                _x++;
                break;
            case KeyEvent.VK_LEFT:
                _x--;
                break;
        }
    }
    public void updatePosition(){
        validPath = false;
        if(map.model[_y][_x] == PacmanMap.SMALL_CANDY || map.model[_y][_x] == PacmanMap.BIG_CANDY){
            map.model[_y][_x] = PacmanMap.PATH;
        }
        switch(game.direction){
            case KeyEvent.VK_RIGHT:
                if(map.model[_y][_x+1] != PacmanMap.BRICK && x<gameArea.maxX){
                    x += speed;
                    validPath = true;
                }
                setDirection(RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                if(map.model[_y][_x-1] != PacmanMap.BRICK && x>gameArea.minX){
                    x -= speed;
                    validPath = true;
                }
                setDirection(LEFT);
                break;
            case KeyEvent.VK_UP:
                if(map.model[_y-1][_x] !=PacmanMap.BRICK && y>gameArea.minY){
                    y -= speed;
                    validPath = true;
                }
                if(direction.equals(RIGHT) || direction.equals(DOWN_LEFT)){
                    setDirection(UP_RIGHT);
                }else if(direction.equals(LEFT) || direction.equals(DOWN_RIGHT)){
                    setDirection(UP_LEFT);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(map.model[_y+1][_x] != PacmanMap.BRICK && y<gameArea.maxY){
                    y += speed;
                    validPath = true;
                }
                if(direction.equals(RIGHT) || direction.equals(UP_LEFT)){
                    setDirection(DOWN_RIGHT);
                }else if(direction.equals(LEFT)|| direction.equals(UP_RIGHT)){
                    setDirection(DOWN_LEFT);
                }
                break;
        }
    }
}
