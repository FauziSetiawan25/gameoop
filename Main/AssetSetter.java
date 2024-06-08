package learnGame.Main;

import learnGame.Entity.NPColdMan;
import learnGame.object.*;

public class AssetSetter {
    private GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJKey(gp);
        gp.obj[0].setWorldX(23*gp.getTileSize());
        gp.obj[0].setWorldY(7*gp.getTileSize());

        gp.obj[1] = new OBJKey(gp);
        gp.obj[1].setWorldX(23*gp.getTileSize());
        gp.obj[1].setWorldY(40*gp.getTileSize());

        gp.obj[2] = new OBJKey(gp);
        gp.obj[2].setWorldX(37*gp.getTileSize());
        gp.obj[2].setWorldY(7*gp.getTileSize());

        gp.obj[3] = new OBJDoor(gp);
        gp.obj[3].setWorldX(10*gp.getTileSize());
        gp.obj[3].setWorldY(12*gp.getTileSize());

        gp.obj[4] = new OBJDoor(gp);
        gp.obj[4].setWorldX(8*gp.getTileSize());
        gp.obj[4].setWorldY(28*gp.getTileSize());

        gp.obj[5] = new OBJDoor(gp);
        gp.obj[5].setWorldX(12*gp.getTileSize());
        gp.obj[5].setWorldY(23*gp.getTileSize());

        gp.obj[6] = new OBJChest(gp);
        gp.obj[6].setWorldX(10*gp.getTileSize());
        gp.obj[6].setWorldY(8*gp.getTileSize());

        gp.obj[7] = new OBJBoots(gp);
        gp.obj[7].setWorldX(37*gp.getTileSize());
        gp.obj[7].setWorldY(42*gp.getTileSize());
    }
    public void setNPC(){
        gp.npc[0] = new NPColdMan(gp);
        gp.npc[0].setWorldX(gp.getTileSize()*21);
        gp.npc[0].setWorldY(gp.getTileSize()*21);
    }
}
