package learnGame.object;

import learnGame.Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJChest extends SuperObject{
    GamePanel gp;
    public OBJChest(GamePanel gp){
        setName("Chest");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/learnGame/Assets/object/chest(OLD).png")));
            getuTool().scaleImage(getImage(), gp.getTileSize(), gp.getTileSize());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
