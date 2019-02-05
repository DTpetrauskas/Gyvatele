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

    private int[] snakexlenght = new int[750];
    private int[] snakeylenght = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean pause = false;

    private ImageIcon rightmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    private ImageIcon leftmouth;

    private int lengthofsnake = 3;
    private int score = 0;

    private int[] foodxpos = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275,
        300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625,
        650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] foodypos = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475, 500, 525, 550, 575, 600, 625};

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

            snakexlenght[2] = 50;
            snakexlenght[1] = 75;
            snakexlenght[0] = 100;

            snakeylenght[2] = 100;
            snakeylenght[1] = 100;
            snakeylenght[0] = 100;

        }

//nupiesti siena
        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 852, 55);
        //nupiestas paveiksliukas title
        titleImage = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);

        //gameplay paveiksliukas
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);
        // backgroundas

        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);

        //taskai
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Score: " + score, 780, 30);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("LENGHT: " + lengthofsnake, 780, 50);

        //pause
        if (pause == true) {

            right = false;
            left = false;
            up = false;
            down = false;
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial", Font.PLAIN, 50));
            g.drawString("Pause", 320, 340);

        }

        rightmouth = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/rightmouth.png");
        rightmouth.paintIcon(this, g, snakexlenght[0], snakeylenght[0]);

        for (int a = 0; a < lengthofsnake; a++) {
            if (a == 0 && right) {
                rightmouth = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
            }
            if (a == 0 && left) {
                leftmouth = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
            }
            if (a == 0 && up) {
                upmouth = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/upmouth.png");
                upmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
            }
            if (a == 0 && down) {
                downmouth = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/downmouth.png");
                downmouth.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
            }

            if (a != 0) {
                snakeImage = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/snakeimage.png");
                snakeImage.paintIcon(this, g, snakexlenght[a], snakeylenght[a]);
            }

        }

        foodImage = new ImageIcon("src/main/java/com/mycompany/gyvatele/Pictures/enemy.png");
        if (foodxpos[xpos] == snakexlenght[0] && foodypos[ypos] == snakeylenght[0]) {
            score += lengthofsnake * 2;
            lengthofsnake++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }
        foodImage.paintIcon(this, g, foodxpos[xpos], foodypos[ypos]);

        for (int b = 1; b < lengthofsnake; b++) {

            if (snakexlenght[b] == snakexlenght[0] && snakeylenght[b] == snakeylenght[0]) {

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_P) {
            synchronized ("a") {
                if (pause == true) {
                    pause = false;

                } else {
                    pause = true;
                }

            }
        }

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
                    pause = false;
                }

                left = false;
                right = false;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right) {
            for (int r = lengthofsnake - 1; r >= 0; r--) {

                snakeylenght[r + 1] = snakeylenght[r];
            }

            for (int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {

                    snakexlenght[r] = snakexlenght[r] + 25;
                } else {
                    snakexlenght[r] = snakexlenght[r - 1];
                }
                if (snakexlenght[r] > 850) {
                    snakexlenght[r] = 25;
                }
            }
            repaint();

        }
        if (left) {
            for (int r = lengthofsnake - 1; r >= 0; r--) {

                snakeylenght[r + 1] = snakeylenght[r];
            }

            for (int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {

                    snakexlenght[r] = snakexlenght[r] - 25;
                } else {
                    snakexlenght[r] = snakexlenght[r - 1];
                }
                if (snakexlenght[r] < 25) {
                    snakexlenght[r] = 850;
                }
            }
            repaint();

        }
        if (up) {
            for (int r = lengthofsnake - 1; r >= 0; r--) {

                snakexlenght[r + 1] = snakexlenght[r];
            }

            for (int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {

                    snakeylenght[r] = snakeylenght[r] - 25;
                } else {
                    snakeylenght[r] = snakeylenght[r - 1];
                }
                if (snakeylenght[r] < 75) {
                    snakeylenght[r] = 625;
                }
            }
            repaint();
        }
        if (down) {
            for (int r = lengthofsnake - 1; r >= 0; r--) {

                snakexlenght[r + 1] = snakexlenght[r];
            }

            for (int r = lengthofsnake; r >= 0; r--) {
                if (r == 0) {

                    snakeylenght[r] = snakeylenght[r] + 25;
                } else {
                    snakeylenght[r] = snakeylenght[r - 1];
                }
                if (snakeylenght[r] > 625) {
                    snakeylenght[r] = 75;
                }
            }
            repaint();
        }
    }

}
