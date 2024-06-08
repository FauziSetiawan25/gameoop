package learnGame.Main;

import learnGame.object.OBJKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font Arial40, Arial80B;
    BufferedImage keyImage;
    private boolean massageOn = false, gameFinished = false;
    private String massage = "";
    private int massageCounter = 0;
    private double playtime;
    DecimalFormat df = new DecimalFormat("0.00");
    public  UI(GamePanel gp){
        this.gp = gp;
        Arial40 = new Font("Arial", Font.PLAIN, 40);
        Arial80B = new Font("Arial", Font.BOLD, 80);
        OBJKey key = new OBJKey(gp);
        keyImage = key.getImage();
    }
    public void showMassage(String text){
        massage = text;
        massageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        if (gameFinished){
            g2.setFont(Arial40);
            g2.setColor(Color.WHITE);

            String text;
            int x, y;

            text = "Kamu menemukan harta karun";
            x = getXforCenter(text);
            y = gp.getScreenHeight()/2 - (gp.getTileSize()*3);
            g2.drawString(text, x, y);

            text = "Waktu kamu adalah : " + df.format(playtime) + " detik";
            x = getXforCenter(text);
            y = gp.getScreenHeight()/2 + (gp.getTileSize()*3);
            g2.drawString(text, x, y);

            g2.setFont(Arial80B);
            g2.setColor(Color.YELLOW);
            text = "SELAMAT";
            x = getXforCenter(text);
            y = gp.getScreenHeight()/2 + (gp.getTileSize()*2);
            g2.drawString(text, x, y);

            gp.gameTherad = null;
        }else {
            g2.setFont(Arial40);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.getTileSize()/2, gp.getTileSize()/2, gp.getTileSize(), gp.getTileSize(), null);
            g2.drawString("x " + gp.player.getHasKey(), 74,65);

            //time
            if(gp.getGameState() == gp.getPlayState()){
                playtime += (double)1/60;
            }
            g2.drawString("Time: " + df.format(playtime), gp.getTileSize()*11, 65);


            //massage
            if(massageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(massage, gp.getTileSize()/2, gp.getTileSize()*5);

                massageCounter++;
            }
            if(massageCounter > 120){
                massageCounter = 0;
                massageOn = false;
            }
        }

        if(gp.getGameState() == gp.getPlayState()){
            //do play state stuff
        }
        if(gp.getGameState() == gp.getPauseState()){
            drawPauseScreen();
        }
    }
    public void drawPauseScreen(){
        String text;
        int x, y;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        text = "PAUSED";
        x = getXforCenter(text);
        y = gp.getScreenHeight()/2;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30F));
        text = "Tekan P untuk memulai kembali";
        x = getXforCenter(text);
        y = gp.getScreenHeight()-gp.getTileSize();
        g2.drawString(text, x, y);
    }
    public int getXforCenter(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.getScreenWidth()/2 - length/2;
        return x;
    }
    public void runtime(){
        playtime += (double)1/60;
    }
    public void stoptime(){
        playtime = playtime;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}
