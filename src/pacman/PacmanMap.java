/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Melvic
 */
public class PacmanMap {
    final static int SMALL_CANDY = 2;
    final static int BIG_CANDY = 3;
    final static int PATH = 1;
    final static int BRICK = 0;

    final static Resources resources = new Resources();
    final static Color BGCOLOR = Color.black;
    static Image SMALL_CANDY_IMAGE;
    static Image BIG_CANDY_IMAGE;
    static Image BRICK_IMAGE;
    static final int HEIGHT = 22;
    static final int WIDTH = 19;
    int[][] model = new int[][]{
        {BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK},//BRICK
        {BRICK, PATH, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK},
        {BRICK, BIG_CANDY, BRICK, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, BRICK, BIG_CANDY, BRICK},
        {BRICK, SMALL_CANDY, BRICK, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, BRICK, SMALL_CANDY, BRICK},
        {BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK},
        {BRICK, SMALL_CANDY, BRICK, BRICK, SMALL_CANDY, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, SMALL_CANDY, BRICK, BRICK, SMALL_CANDY, BRICK},//5
        {BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK},
        {BRICK, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, PATH, BRICK, PATH, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, BRICK},
        {PATH, PATH, PATH, BRICK, SMALL_CANDY, BRICK, PATH, PATH, PATH, PATH, PATH, PATH, PATH, BRICK, SMALL_CANDY, BRICK, PATH, PATH, PATH},
        {BRICK, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, PATH, BRICK, BRICK, PATH, BRICK, BRICK, PATH, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, BRICK},
        {BRICK, PATH, PATH, PATH, SMALL_CANDY, PATH, PATH, BRICK, PATH, PATH, PATH, BRICK, PATH, PATH, SMALL_CANDY, PATH, PATH, PATH, BRICK},//SMALL_CANDYBRICK
        {BRICK, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, PATH, BRICK, BRICK, BRICK, BRICK, BRICK, PATH, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, BRICK},
        {PATH, PATH, PATH, BRICK, SMALL_CANDY, BRICK, PATH, PATH, PATH, PATH, PATH, PATH, PATH, BRICK, SMALL_CANDY, BRICK, PATH, PATH, PATH},
        {BRICK, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, PATH, BRICK, BRICK, BRICK, BRICK, BRICK, PATH, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, BRICK},
        {BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK},
        {BRICK, BIG_CANDY, BRICK, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, BRICK, BIG_CANDY, BRICK},//SMALL_CANDY5
        {BRICK, SMALL_CANDY, SMALL_CANDY, BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK, SMALL_CANDY, SMALL_CANDY, BRICK},
        {BRICK, BRICK, SMALL_CANDY, BRICK, SMALL_CANDY, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, SMALL_CANDY, BRICK, SMALL_CANDY, BRICK, BRICK},
        {BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK},
        {BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK, SMALL_CANDY, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, SMALL_CANDY, BRICK},
        {BRICK, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, SMALL_CANDY, BRICK},//PATHBRICK
        {BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK, BRICK}
    };
    static{
        try{
            SMALL_CANDY_IMAGE = ImageIO.read(resources.getResource("small_candy"));
            BIG_CANDY_IMAGE = ImageIO.read(resources.getResource("big_candy"));
            BRICK_IMAGE = ImageIO.read(resources.getResource("bricks"));
        }catch(Exception e){
            System.out.println("Error loading image");
            e.printStackTrace();
        }
    }
}
