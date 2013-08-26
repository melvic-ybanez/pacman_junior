/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pacman;
/**
 *
 * @author Melvic
 */
public class WiseGhost extends Ghost{
    Pacman pacman;

    public WiseGhost(PacmanMap map, String ghostType,Pacman pacman){
        super(map,ghostType);
        this.pacman = pacman;
    }
    public void findPacman(){
        int dx = Math.abs(pacman.x - x);
        int dy = Math.abs(pacman.y - y);

        if(dx>dy){
            if(x<pacman.x){
                x+=speed;
            }else{
                x-=speed;
            }
        }else{
            if(y<pacman.y){
                y+=speed;
            }else{
                y-=speed;
            }
        }
    }
}
