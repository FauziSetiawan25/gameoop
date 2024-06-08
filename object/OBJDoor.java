package learnGame.object;

import learnGame.Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJDoor extends SuperObject{
    GamePanel gp;
    public OBJDoor(GamePanel gp){
        setName("Door");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/learnGame/Assets/object/door.png")));
            getuTool().scaleImage(getImage(), gp.getTileSize(), gp.getTileSize());
        }catch (IOException e){
            e.printStackTrace();
        }
        setCollision(true);

    }
}
