/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gyvatele;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author TAURAS
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private int[] snakeXlenght = new int[750];
    private int[] snakeYlenght = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean pause = false;
    
    //pause booleanai
    private boolean pasueLeft = false;
    private boolean pauseRight = false;
    private boolean pauseUp = false;
    private boolean pauseDown = false;
    
    

    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;

    private int lengthofsnake = 3;
    private int score = 0;

    private int[] foodXpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275,
        300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625,
        650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] foodYpos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

    private ImageIcon foodImage;

    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    private Timer timer;
    private int delay = 100;
    private ImageIcon snakeImage;

    private int moves = 0;

    private ImageIcon titleImage;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();

    }

    public void paint(Graphics g) {

        if (moves == 0) {

            snakeXlenght[2] = 50;
            snakeXlenght[1] = 75;
            snakeXlenght[0] = 100;

            snakeYlenght[2] = 100;
            snakeYlenght[1] = 100;
            snakeYlenght[0] = 100;

        }

//nupiesti siena
        g.setColor(Color.BLACK);
        g.drawRect(24, 10, 852, 55);
        //nupiestas paveiksliukas title
        titleImage = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        //gameplay paveiksliukas
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);
        // backgroundas

        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);

        //taskai
        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score: " + score, 780, 30);

        g.setColor(Color.BLACK);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("LENGHT: " + lengthofsnake, 780, 50);

        //pause
        if (pause == true) {

            right = false;
            left = false;
            up = false;
            down = false;
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.PLAIN, 30));
            g.drawString("Pause", 340, 350);

        }

        leftmouth = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/leftmouth.png");
        rightmouth = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/rightmouth.png");
        upmouth = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/upmouth.png");
        downmouth = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/downmouth.png");
        snakeImage = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/snakeimage.png");
        rightmouth.paintIcon(this, g, snakeXlenght[0], snakeYlenght[0]);

        for (int a = 0; a < lengthofsnake; a++) {
            if (a == 0 && right) {
                
                rightmouth.paintIcon(this, g, snakeXlenght[a], snakeYlenght[a]);
            }
            if (a == 0 && left) {
                
                leftmouth.paintIcon(this, g, snakeXlenght[a], snakeYlenght[a]);
            }
            if (a == 0 && up) {
                
                upmouth.paintIcon(this, g, snakeXlenght[a], snakeYlenght[a]);
            }
            if (a == 0 && down) {
                
                downmouth.paintIcon(this, g, snakeXlenght[a], snakeYlenght[a]);
            }

            if (a != 0) {
                
                snakeImage.paintIcon(this, g, snakeXlenght[a], snakeYlenght[a]);
            }

        }

        foodImage = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/food.png");
        if (foodXpos[xpos] == snakeXlenght[0] && foodYpos[ypos] == snakeYlenght[0]) {
            score += lengthofsnake * 2;
            lengthofsnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        foodImage.paintIcon(this, g, foodXpos[xpos], foodYpos[ypos]);

        for (int b = 1; b < lengthofsnake; b++) {

            if (snakeXlenght[b] == snakeXlenght[0] && snakeYlenght[b] == snakeYlenght[0]) {

                right = false;
                left = false;
                up = false;
                down = false;
                pause = false;

                g.setColor(Color.white);
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.drawString("Game Over", 300, 300);

                g.setFont(new Font("arial", Font.BOLD, 20));
                g.drawString("(Press the SPACE to restart)", 300, 340);

            }
        }

        g.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            synchronized ("a") {
                moves = 0;
                score = 0;
                lengthofsnake = 3;
                repaint();

            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            synchronized ("a") {
                moves++;
                right = true;

                if (!left) {
                    right = true;
                } else {
                    right = false;
                    left = true;
                }

                up = false;
                down = false;
                pause = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            synchronized ("a") {
                moves++;
                left = true;

                if (!right) {
                    left = true;
                } else {
                    left = false;
                    right = true;
                }

                up = false;
                down = false;
                pause = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            synchronized ("a") {
                moves++;
                up = true;

                if (!down) {
                    up = true;
                } else {
                    up = false;
                    down = true;

                }

                left = false;
                right = false;
                pause = false;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            synchronized ("a") {
                moves++;
                down = true;

                if (!up) {
                    down = true;
                } else {
                    down = false;
                    up = true;
                }

                left = false;
                right = false;
                pause = false;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (pause) {
                timer.start();

            } else {
                timer.stop();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {
            for (int r = lengthofsnake - 1; r >= 0; r--) {

                snakeYlenght[r + 1] = snakeYlenght[r];
            }

            for (int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {

                    snakeXlenght[r] = snakeXlenght[r] + 25;
                } else {
                    snakeXlenght[r] = snakeXlenght[r - 1];
                }
                if (snakeXlenght[r] > 850) {
                    snakeXlenght[r] = 25;
                }
            }
            repaint();

        }
        if (left) {
            for (int r = lengthofsnake - 1; r >= 0; r--) {

                snakeYlenght[r + 1] = snakeYlenght[r];
            }

            for (int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {

                    snakeXlenght[r] = snakeXlenght[r] - 25;
                } else {
                    snakeXlenght[r] = snakeXlenght[r - 1];
                }
                if (snakeXlenght[r] < 25) {
                    snakeXlenght[r] = 850;
                }
            }
            repaint();

        }
        if (up) {
            for (int r = lengthofsnake - 1; r >= 0; r--) {

                snakeXlenght[r + 1] = snakeXlenght[r];
            }

            for (int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {

                    snakeYlenght[r] = snakeYlenght[r] - 25;
                } else {
                    snakeYlenght[r] = snakeYlenght[r - 1];
                }
                if (snakeYlenght[r] < 75) {
                    snakeYlenght[r] = 625;
                }
            }
            repaint();
        }
        if (down) {
            for (int r = lengthofsnake - 1; r >= 0; r--) {

                snakeXlenght[r + 1] = snakeXlenght[r];
            }

            for (int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {

                    snakeYlenght[r] = snakeYlenght[r] + 25;
                } else {
                    snakeYlenght[r] = snakeYlenght[r - 1];
                }
                if (snakeYlenght[r] > 625) {
                    snakeYlenght[r] = 75;
                }
            }
            repaint();
        }
    }

}
