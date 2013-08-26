/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;
/**
 *
 * @author Melvic
 */
public class RandomGhost extends Ghost{
    public RandomGhost(PacmanMap map, String ghostType){
        super(map,ghostType);
    }
    public void updatePosition(){        
        validPath = false;
        switch(direction){
            case RIGHT:
                if(map.model[_y][_x+1] != PacmanMap.BRICK && x<gameArea.maxX){
                    x += speed;
                    validPath = true;
                }
                break;
            case LEFT:
                if(map.model[_y][_x-1] != PacmanMap.BRICK && x>gameArea.minX){
                    x -= speed;
                    validPath = true;
                }
                break;
            case UP:
                if(map.model[_y-1][_x] !=PacmanMap.BRICK && y>gameArea.minY){
                    y -= speed;
                    validPath = true;
                }               
                break;
            case DOWN:
                if(map.model[_y+1][_x] != PacmanMap.BRICK && y<gameArea.maxY){
                    y += speed;
                    validPath = true;
                }                
                break;
        }        
    }    
    public void setNextDirection(){
        java.util.Random random = new java.util.Random();
        direction = random.nextInt(4);
        if(Math.abs(gameArea.getWidth()/2-x) > 100 || Math.abs(gameArea.getHeight()/2-y) >100){
            range = random.nextInt(20);
        }
    }
}
