package learnGame.Entity;

import learnGame.Main.GamePanel;
import learnGame.Main.KeyHandler;
import learnGame.Main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity{
    KeyHandler keyhandler;
    private int screenX, screenY, hasKey = 0;
    public Player(GamePanel gp, KeyHandler keyhandler){
        super(gp);
        this.keyhandler = keyhandler;

        screenX = gp.getScreenWidth()/2 -(gp.getTileSize()/2);
        screenY = gp.getScreenHeight()/2 -(gp.getTileSize()/2);

        setSolidArea(new Rectangle(8, 16, 32, 32));
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);

        setDefaultValues();
        getPlayerImage();


    }
    public void setDefaultValues(){
        setWorldX(gp.getTileSize() * 23);
        setWorldY(gp.getTileSize() * 21);
        setSpeed(4);
        setDirection("down");
    }
    public void getPlayerImage(){
        setUp1(setup("character/boy_up_1"));
        setUp2(setup("character/boy_up_2"));
        setDown1(setup("character/boy_down_1"));
        setDown2(setup("character/boy_down_2"));
        setLeft1(setup("character/boy_left_1"));
        setLeft2(setup("character/boy_left_2"));
        setRight1(setup("character/boy_right_1"));
        setRight2(setup("character/boy_right_2"));
    }
    public void update(){
        if(keyhandler.isUpPressed() == true || keyhandler.isDownPressed() == true || keyhandler.isLeftPressed() == true || keyhandler.isRightPressed() == true){
            if(keyhandler.isUpPressed() == true){
                setDirection("up");

            }else if(keyhandler.isDownPressed() == true){
                setDirection("down");

            }else if(keyhandler.isLeftPressed() == true){
                setDirection("left");

            }else if(keyhandler.isRightPressed() == true){
                setDirection("right");

            }
            //cek tile collision
            setCollision(false);
            gp.getcChecker().checkTile(this);

            //cek obj collision
            int objIndex = gp.getcChecker().checkObject(this, true);
            pickUpObj(objIndex);

            //cek npc collision
            int npcIndex = gp.getcChecker().checkEntity(this, gp.getNpc());
            interactNPC(npcIndex);

            if(isCollision() == false){
                switch (getDirection()){
                    case "up":
                        setWorldY(getWorldY()-getSpeed());
                        break;
                    case "down":
                        setWorldY(getWorldY() + getSpeed());
                        break;
                    case "left":
                        setWorldX(getWorldX() - getSpeed());
                        break;
                    case "right":
                        setWorldX(getWorldX() + getSpeed());
                        break;
                }
            }

            setSpriteCounter(getSpriteCounter() + getSpeed());
            if(getSpriteCounter() > 17){
                if(getSpriteNum() == 1){
                    setSpriteNum(2);
                } else if (getSpriteNum() == 2) {
                    setSpriteNum(1);
                }
                setSpriteCounter(0);
            }
        }
    }
    public void pickUpObj(int i){
        if(i != 999){
            String OBJname = gp.getObj()[i].getName();
            switch (OBJname){
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.getObj()[i] = null;
                    gp.getUi().showMassage("Kamu mendapatkan kunci");
                    break;
                case "Door":
                    if(hasKey>0){
                        gp.playSE(3);
                        gp.getObj()[i] = null;
                        hasKey--;
                        gp.getUi().showMassage("Kamu membuka pintu");
                    }else {
                        gp.getUi().showMassage("Kamu membutuhkan kunci");
                    }

                    break;
                case "Boots":
                    gp.playSE(2);
                    setSpeed(getSpeed()+2);
                    gp.getObj()[i] = null;
                    gp.getUi().showMassage("Kamu jadi lebih cepat");
                    break;
                case "Chest":
                    gp.getUi().setGameFinished(true);
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }
    }
    public void interactNPC(int i){
        if(i != 999){
            System.out.println("Kamu menyentuh kakek kakek");
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.WHITE);
//        g2.fillRect(getX(),getY(), gp.getTileSize(),gp.getTileSize());
        BufferedImage image = null;
        switch (getDirection()){
            case ("up"):
                if(getSpriteNum() == 1){
                    image = getUp1();
                }else {
                    image = getUp2();
                }
                break;
            case ("down"):
                if(getSpriteNum() == 1){
                    image = getDown1();
                }else {
                    image = getDown2();
                }
                break;
            case ("left"):
                if(getSpriteNum() == 1){
                    image = getLeft1();
                }else {
                    image = getLeft2();
                }
                break;
            case ("right"):
                if(getSpriteNum() == 1){
                    image = getRight1();
                }else {
                    image = getRight2();
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, null);
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getHasKey() {
        return hasKey;
    }
}
