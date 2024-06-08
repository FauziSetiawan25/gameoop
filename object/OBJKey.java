package learnGame.object;

import learnGame.Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJKey extends SuperObject{
    GamePanel gp;
    public OBJKey(GamePanel gp){
        setName("Key");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/learnGame/Assets/object/key.png")));
            getuTool().scaleImage(getImage(), gp.getTileSize(), gp.getTileSize());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
