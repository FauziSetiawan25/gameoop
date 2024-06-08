package learnGame.Entity;

import learnGame.Main.GamePanel;

import java.util.Random;

public class NPColdMan extends Entity{
    public NPColdMan(GamePanel gp){
        super(gp);
        setDirection("down");
        setSpeed(1);

        getImage();
    }
    public void getImage(){
        setUp1(setup("npc/oldman_up_1"));
        setUp2(setup("npc/oldman_up_2"));
        setDown1(setup("npc/oldman_down_1"));
        setDown2(setup("npc/oldman_down_2"));
        setLeft1(setup("npc/oldman_left_1"));
        setLeft2(setup("npc/oldman_left_2"));
        setRight1(setup("npc/oldman_right_1"));
        setRight2(setup("npc/oldman_right_2"));
    }
    public void setAction(){
        setActionLockCounter(getActionLockCounter()+1);
        if(getActionLockCounter() == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;//1 to 100
            if(i <= 25){
                setDirection("up");
            }
            if(i > 25 && i <= 50){
                setDirection("down");
            }
            if (i > 50 && i <= 75){
                setDirection("left");
            }
            if (i > 75){
                setDirection("right");
            }
            setActionLockCounter(0);
        }



    }

}
