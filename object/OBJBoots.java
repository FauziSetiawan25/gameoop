package learnGame.object;

import learnGame.Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJBoots extends SuperObject{
    GamePanel gp;
    public OBJBoots(GamePanel gp){
        setName("Boots");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/learnGame/Assets/object/boots.png")));
            getuTool().scaleImage(getImage(), gp.getTileSize(), gp.getTileSize());
        }catch (
                IOException e){
            e.printStackTrace();
        }
    }
}
