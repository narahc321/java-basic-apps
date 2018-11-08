/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake_game;
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
 * @author charan
 */
public class Gameplay extends JPanel implements KeyListener, ActionListener{
    
    private int[] snakexlength = new int[750];
    private int[] snakeylength = new int[750];
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    
    private int moves =0;
    private ImageIcon titleImage;
    
    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;
    
    private int lengthofsnake = 3;
    private Timer timer;
    private int delay=80;
    
    private ImageIcon snakeimage;
    
    public int [] enemyxpos={25,50,75,100,125,150,175,200,225,250,275,300,
            325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,
            725,750,775,800,825,850};
    public int [] enemyypos={75,100,125,150,175,200,225,250,275,300,
            325,350,375,400,425,450,475,500,525,550,575,600,625};
    
    private ImageIcon enemyicon;
    
    private Random random= new Random();
    
    private int xpos=random.nextInt(34);
    private int ypos=random.nextInt(23);
    
    private int score=0;
    private int previousscore=0;
    private int best=0;
    
    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        timer = new Timer(delay, this);
        timer.start();
    }
    
    
    
    public void paint(Graphics g){
        
        if(moves == 0 ){
            snakexlength[2]= 50;
            snakexlength[1]= 75;
            snakexlength[0]= 100;
            
            snakeylength[2]= 100;
            snakeylength[1] =100;
            snakeylength[0] =100;
            
        }
        //title image border
        g.setColor(Color.red);
        g.drawRect(24, 10, 851, 55);
        //draw title image
        titleImage =new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g, 25, 11);
        //draw border for plane area
        g.setColor(Color.white);
        g.drawRect(24, 74, 851, 576);
        //back ground color
        g.setColor(Color.black);
        g.fillRect(25, 75, 850, 575);
        
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Scores:"+score, 780, 30);
        
        //draw length of snake;
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("Length:"+lengthofsnake, 780, 50);
        
        
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("previous score:"+previousscore, 50, 50);
        
        
        g.setColor(Color.white);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("BEST:"+best, 50, 30);
        
        
        
        
        for(int a=0; a < lengthofsnake; a++){
            if(a==0 && (right || moves==0)){
                rightmouth= new ImageIcon("rightmouth.png");
                rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
            }
            if(a==0 && left){
                leftmouth= new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
            }
            if(a==0 && down){
                downmouth= new ImageIcon("downmouth.png");
                downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
            }
            if(a==0 && up){
                upmouth= new ImageIcon("upmouth.png");
                upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
            }
            if(a!=0){
                snakeimage= new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
            }
        }
        
        
        enemyicon =new ImageIcon("enemy.png");
        
        if(enemyxpos[xpos]==snakexlength[0] && enemyypos[ypos]==snakeylength[0]){
            lengthofsnake++;
            score++;
            xpos =random.nextInt(34);
            ypos =random.nextInt(23);
        }
        
        enemyicon.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
        
        for(int b=1;b<lengthofsnake;b++){
            if(snakexlength[b]==snakexlength[0] && snakeylength[b]==snakeylength[0]){
                right=false;
                left=false;
                up=false;
                down=false;
                previousscore=score;
                if(score>best)
                    best=score;
                score=0;
                lengthofsnake=3;
                moves=0;
                repaint();
            }
        }
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        /*if(e.getKeyCode() == KeyEvent.VK_SPACE){
            moves=0;
            score=0;
            lengthofsnake=3;
            right=false;
            left=false;
            up=false;
            down=false;
            repaint();
        }*/
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            //System.out.println("12");
            moves++;
            if(!left){
                right=true;
                up=false;
                down=false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moves++;
            if(!right){
                left=true;
                up=false;
                down=false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            moves++;
            if(!down){
                up=true;
                right=false;
                left=false;
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            moves++;
            if(!up){
                down=true;
                left=false;
                right=false;
            }
        }
        
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        //System.out.println(1);
        if(right){
            for(int r=lengthofsnake-1;r>=0;r-- ){
                snakeylength[r+1]=snakeylength[r];
            }
            
            for(int r=lengthofsnake;r>=0;r-- ){
                if(r==0){
                    snakexlength[0]=snakexlength[0]+25;
                }
                else{
                    snakexlength[r]=snakexlength[r-1];
                }
                if(snakexlength[r]>850){
                    snakexlength[r]=25;
                }
            }
            repaint();
        }
        if(left){
            for(int r=lengthofsnake-1;r>=0;r-- ){
                snakeylength[r+1]=snakeylength[r];
            }
            
            for(int r=lengthofsnake;r>=0;r-- ){
                if(r==0){
                    snakexlength[0]=snakexlength[0]-25;
                }
                else{
                    snakexlength[r]=snakexlength[r-1];
                }
                if(snakexlength[r]<25){
                    snakexlength[r]=850;
                }
            }
            repaint();
        }
        if(up){
            for(int r=lengthofsnake-1;r>=0;r-- ){
                snakexlength[r+1]=snakexlength[r];
            }
            
            for(int r=lengthofsnake;r>=0;r-- ){
                if(r==0){
                    snakeylength[0]=snakeylength[0]-25;
                }
                else{
                    snakeylength[r]=snakeylength[r-1];
                }
                if(snakeylength[r]<75){
                    snakeylength[r]=625;
                }
            }
            repaint();
        }
        if(down){
            for(int r=lengthofsnake-1;r>=0;r-- ){
                snakexlength[r+1]=snakexlength[r];
            }
            
            for(int r=lengthofsnake;r>=0;r-- ){
                if(r==0){
                    snakeylength[0]=snakeylength[0]+25;
                }
                else{
                    snakeylength[r]=snakeylength[r-1];
                }
                if(snakeylength[r]>625){
                    snakeylength[r]=75;
                }
            }
            repaint();
        }
    }
}
