package src.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import src.main.ActionBox;
import src.main.GamePanel;
import src.main.Keyboard;

public class Player extends Entity {
    GamePanel gp;
    Keyboard kb;
    private Map<Action, ActionBox> actions;

    public Player(GamePanel gp, Keyboard kb) {
        this.gp = gp;
        this.kb = kb;
        setDefaultValues();
        getPlayerImage();

        int x = (int)this.x;
        int y = (int)this.y;
        Rectangle zeroBox = new Rectangle(0, 0, 0, 0);

		int entityLeftX = x + 24;
		int entityRightX = x + 88; // 24 toi le trai, 88 toi le phai 
		int entityTopY = y + 24;
		int entityBotY = y + 120; // 24 toi dau, 120 toi chan
        actions = new HashMap<>();

        actions.put(Action.Punch, new ActionBox(new Rectangle(x + 80, y + 62, 48, 17), new Rectangle(x + 48, y + 35, 48, 85)));
        actions.put(Action.Kick, new ActionBox(new Rectangle(x + 56, y + 64, 70, 16), new Rectangle(x + 16, y + 32, 46, 80)));
        actions.put(Action.Idle, new ActionBox(zeroBox, new Rectangle(entityLeftX, entityTopY, 46, 72)));
        actions.put(Action.Up, new ActionBox(zeroBox, new Rectangle(entityLeftX, entityTopY, 46, 72)));
        actions.put(Action.Down, new ActionBox(zeroBox, new Rectangle(entityLeftX, entityTopY, 46, 72)));
        actions.put(Action.Left, new ActionBox(zeroBox, new Rectangle(entityLeftX, entityTopY, 104, 72)));
        actions.put(Action.Right, new ActionBox(zeroBox, new Rectangle(entityLeftX, entityTopY, 104, 72)));
    }
    
    public void setDefaultValues() {
        x = 100.0f;
        y = 100.0f;
        velX = 0.0f;
        velY = 0.0f;
        speed = 8;
        action = Action.Idle;
        reverse = false;
        doJump = false;
        direction = 1;
    }
    

    public Rectangle getHitbox() {
        return actions.get(action).getHitbox();
    }

    public Rectangle getHurtbox() {
        return actions.get(action).getHurtbox();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File("./res/gokuu/up_1.png"));
            
            down1 = ImageIO.read(new File("./res/gokuu/down_1.png"));
            
            left1 = ImageIO.read(new File("./res/gokuu/left_1.png"));
            left2 = ImageIO.read(new File("./res/gokuu/left_2.png"));
            right1 = ImageIO.read(new File("./res/gokuu/right_1.png"));
            right2 = ImageIO.read(new File("./res/gokuu/right_2.png"));
            idle = ImageIO.read(new File("./res/gokuu/idle.png"));
            idle2 = ImageIO.read(new File("./res/gokuu/idle2.png"));
            punch1 = ImageIO.read(new File("./res/gokuu/punch1.png"));
            punch2 = ImageIO.read(new File("./res/gokuu/punch2.png"));
            punch3 = ImageIO.read(new File("./res/gokuu/punch3.png"));
            punch4 = ImageIO.read(new File("./res/gokuu/punch4.png"));
            kick1 = ImageIO.read(new File("./res/gokuu/kick1.png"));
            kick2 = ImageIO.read(new File("./res/gokuu/kick2.png"));
            kick3 = ImageIO.read(new File("./res/gokuu/kick3.png"));
            kick4 = ImageIO.read(new File("./res/gokuu/kick4.png"));

            //reverse image
            rup = ImageIO.read(new File("./res/gokuu/rup.png"));
            rdown = ImageIO.read(new File("./res/gokuu/rdown.png"));
            ridle = ImageIO.read(new File("./res/gokuu/ridle.png"));
            ridle2 = ImageIO.read(new File("./res/gokuu/ridle2.png"));
            rpunch1 = ImageIO.read(new File("./res/gokuu/rpunch1.png"));
            rpunch2 = ImageIO.read(new File("./res/gokuu/rpunch2.png"));
            rpunch3 = ImageIO.read(new File("./res/gokuu/rpunch3.png"));
            rpunch4 = ImageIO.read(new File("./res/gokuu/rpunch4.png"));
            rkick1 = ImageIO.read(new File("./res/gokuu/rkick1.png"));
            rkick2 = ImageIO.read(new File("./res/gokuu/rkick2.png"));
            rkick3 = ImageIO.read(new File("./res/gokuu/rkick3.png"));
            rkick4 = ImageIO.read(new File("./res/gokuu/rkick4.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if (kb.up == true) {
            action = Action.Up;
            velY = -speed;
            y += velY;
            if(direction == 1) {
                actions.get(action).getHurtbox().setLocation((int)this.x + 24, (int)this.y + 24);

            } else {
                actions.get(action).getHurtbox().setLocation((int)this.x + 120 - 24 - 46, (int)this.y + 24);
            }
            // velY = -speed;
            // y += velY;
            doJump = true;
        }
        else if (kb.down == true) {
            action = Action.Down;
            velY = speed;
            if(direction == 1) {
                actions.get(action).getHurtbox().setLocation((int)this.x + 24, (int)this.y + 24);
            } else {
                actions.get(action).getHurtbox().setLocation((int)this.x + 120 - 24 - 46, (int)this.y + 24);
            }
        }
        else if (kb.left == true) {
            direction = -1;
            action = Action.Left;
            velX = -speed;
            x += velX;
            if(direction == 1) {
                actions.get(action).getHurtbox().setLocation((int)this.x + 16, (int)this.y + 48);
            } else {
                actions.get(action).getHurtbox().setLocation((int)this.x , (int)this.y + 48);
            }
        }
        else if (kb.right == true) {
            direction = 1;
            action = Action.Right;
            velX = speed;
            x += velX;
            if(direction == 1) {
                actions.get(action).getHurtbox().setLocation((int)this.x , (int)this.y + 48);
            } else {
                actions.get(action).getHurtbox().setLocation((int)this.x + 16, (int)this.y + 48);
            }
        }
        else if (kb.punch == true) {
            actionNum = 1;
            actionCounter = 4;
            actionDo = 1;
            action = Action.Punch;
            if(direction == 1) {
                actions.get(action).getHitbox().setLocation((int)this.x + 80, (int)this.y + 62);
                actions.get(action).getHurtbox().setLocation((int)this.x + 48, (int)this.y + 35);
            } else {
                actions.get(action).getHitbox().setLocation((int)this.x + 128 - 80 - 48, (int)this.y + 62);
                actions.get(action).getHurtbox().setLocation((int)this.x + 128 - 48 - 48, (int)this.y + 35);
            }

            // actionCounter++;
            // if (actionCounter > 3) {
            //     // if (actionNum == 1) actionNum = 2;
            //     // else if (actionNum == 2) actionNum = 3;
            //     // else if (actionNum == 3) actionNum = 4;
            //     // else if (actionNum == 4) actionNum = 1;
            //     actionNum = actionNum % 4 + 1;
            //     actionCounter = 0;
            // }
            
        }
        else if (kb.kick == true){
            kickNum = 1;
            kickCounter = 4;
            kickDo = 1;
            action = Action.Kick;
            if(direction == 1) {
                actions.get(action).getHitbox().setLocation((int)this.x + 56, (int)this.y + 64);
                actions.get(action).getHurtbox().setLocation((int)this.x + 16, (int)this.y + 32);
            } else {
                actions.get(action).getHitbox().setLocation((int)this.x + 120 - 56 - 70, (int)this.y + 64);
                actions.get(action).getHurtbox().setLocation((int)this.x + 120 - 16 - 46, (int)this.y + 32);
            }

            // kickCounter++;
            // if (kickCounter > 3) {
            //     // if (kickNum == 1) kickNum = 2;
            //     // else if (kickNum == 2) kickNum = 3;
            //     // else if (kickNum == 3) kickNum = 4;
            //     // else if (kickNum == 4) kickNum = 1;
            //     kickNum = kickNum % 4 + 1;
            //     kickCounter = 0;
            // }
            
        }
        else {
            if(actionDo == 1 || kickDo == 1 || doJump == true){
            if(actionDo == 1){
                actionCounter++;
                if(actionCounter > 3){
                actionNum++;
                actionCounter = 0;
                }
                if(actionNum > 4) {
                action = Action.Idle;
                if(direction == 1) {
                    actions.get(action).getHurtbox().setLocation((int)this.x + 38, (int)this.y + 32);
                } else {
                    actions.get(action).getHurtbox().setLocation((int)this.x + 120 - 38 - 46, (int)this.y + 32);
                }
                actionDo = 0;
                }
            }
            if(kickDo == 1){
                kickCounter++;
                if(kickCounter > 3){
                kickNum++;
                kickCounter = 0;
                }
                if(kickNum > 4) {
                action = Action.Idle;
                if(direction == 1) {
                    actions.get(action).getHurtbox().setLocation((int)this.x + 38, (int)this.y + 32);
                } else {
                    actions.get(action).getHurtbox().setLocation((int)this.x + 120 - 38 - 46, (int)this.y + 32);
                }
                kickDo = 0;
                }
            }
            if(doJump==true){
                velY -= speed;
                JumpCounter++;
                if(JumpCounter>1){
                    y += velY;
                }
                if(y<380) doJump = false;
            }
            }
            else action = Action.Idle;
            if(direction == 1) {
                actions.get(action).getHurtbox().setLocation((int)this.x + 38, (int)this.y + 32);
            } else {
                actions.get(action).getHurtbox().setLocation((int)this.x + 120 - 38 - 46, (int)this.y + 32);
            }

        }

        spriteCounter++;
        if (spriteCounter > 15) {
            // if(spriteNum == 1) spriteNum = 2;
            // else if (spriteNum == 2) spriteNum = 1;
            spriteNum = spriteNum % 2 + 1;
            spriteCounter = 0;
        }

       // this.x += this.velX;
       // this.y += this.velY;
       velY = 0;
        if(y < 500) {
            if(action != Action.Up){
                action = Action.Down;
                this.velY += speed;
                y += velY;
                if(direction == 1) {
                    actions.get(action).getHurtbox().setLocation((int)this.x + 24, (int)this.y + 24);
                } else {
                    actions.get(action).getHurtbox().setLocation((int)this.x + 120 - 24 - 46, (int)this.y + 24);
                }
            } 
        } else if (y == 500 && kb.down == true) {
            action = Action.Idle;
            if(direction == 1) {
                actions.get(action).getHurtbox().setLocation((int)this.x + 38, (int)this.y + 32);
            } else {
                actions.get(action).getHurtbox().setLocation((int)this.x + 120 - 38 - 46, (int)this.y + 32);
            }
        }
    }



    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (action) {
            case Up:
                if(direction == 1){ 
                image = up1;
                }
                else if(direction == -1){
                    image = rup;
                }
                break;
            case Down:
            if(direction == 1){ 
                image = down1;
                }
                else if(direction == -1){
                    image = rdown;
                }
                break;
            case Left:
                image = left1;
                break;
            case Right:
                image = right1;
                break;
            case Idle:
            if(direction == 1){
                if (spriteNum == 1) {
                    image = idle;
                }
                if (spriteNum == 2) {
                    image = idle2;
                }
            }
            else if(direction == -1){
                if (spriteNum == 1) {
                    image = ridle;
                }
                if (spriteNum == 2) {
                    image = ridle2;
                }
            }
                break;
            case Punch:
            if(direction == 1){
                if (actionNum == 1) {
                    image = punch1;
                }
                else if (actionNum == 2) {
                    image = punch2;
                }
                else if (actionNum == 3) {
                    image = punch3;
                }
                else if (actionNum == 4) {
                    image = punch4;
                }
                else if (actionNum == 0) {
                    image = idle;
                }
            }
            else if (direction == -1){
                if (actionNum == 1) {
                    image = rpunch1;
                }
                else if (actionNum == 2) {
                    image = rpunch2;
                }
                else if (actionNum == 3) {
                    image = rpunch3;
                }
                else if (actionNum == 4) {
                    image = rpunch4;
                }
                else if (actionNum == 0) {
                    image = ridle;
                }
            }
                break;
            case Kick:
            if(direction == 1){
                if (kickNum == 1) {
                    image = kick1;
                }
                else if (kickNum == 2) {
                    image = kick2;
                }
                else if (kickNum == 3) {
                    image = kick3;
                }
                else if (kickNum == 4) {
                    image = kick4;
                }
                else if (kickNum == 0) {
                    image = idle;
                }
            }
            else if (direction == -1){
                if (kickNum == 1) {
                    image = rkick1;
                }
                else if (kickNum == 2) {
                    image = rkick2;
                }
                else if (kickNum == 3) {
                    image = rkick3;
                }
                else if (kickNum == 4) {
                    image = rkick4;
                }
                else if (kickNum == 0) {
                    image = ridle;
                }
            }
                break;
        }

        g2.drawImage(image, (int)x, (int)y, 128, 120, null);
    }
}
