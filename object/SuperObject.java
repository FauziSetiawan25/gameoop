package learnGame.object;

import learnGame.Main.GamePanel;
import learnGame.Main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
      private BufferedImage image;
      private String name;
      private boolean collision = false;
      private int worldX, worldY;
      private Rectangle solidArea = new Rectangle(0,0, 48, 48);
      private int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
      private UtilityTool uTool = new UtilityTool();


      public void draw(Graphics2D g2, GamePanel gp){
          int screenX = worldX - gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX();
          int screenY = worldY - gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY();


          if(worldX + gp.getTileSize() > gp.getPlayer().getWorldX() - gp.getPlayer().getScreenX() && worldX - gp.getTileSize() < gp.getPlayer().getWorldX() + gp.getPlayer().getScreenX()
                  && worldY + gp.getTileSize() > gp.getPlayer().getWorldY() - gp.getPlayer().getScreenY() && worldY - gp.getTileSize() < gp.getPlayer().getWorldY() + gp.getPlayer().getScreenY()){

              g2.drawImage(image, screenX, screenY,gp.getTileSize(),gp.getTileSize(), null );
          }
      }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public UtilityTool getuTool() {
        return uTool;
    }
}
