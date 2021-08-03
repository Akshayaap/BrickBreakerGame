/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.Math.random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Akshay
 */
public class GamePanel extends JPanel implements ActionListener,KeyListener{
    private Timer time;
    private final int delay=30;
    
   
   
        
    private int pw=100;
    private int r=20;
    private int bricks;
    private int score=0;
    private int row,col;
    
    private Map map;
    
    
    private int a,a0,b;
    private String str="";
    private boolean bonus;
    
    
    
    public GamePanel(){
        row=6;col=10;
        map=new Map(row,col);
        bricks=row*col+row;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
                
	time=new Timer(delay,this);
	time.start();
    }
    
    @Override
    public void paint(Graphics g){
        
        
                    
        g.setColor(Color.white);
        g.fillRect(0,0,1200,600);
        g.setColor(Color.BLACK);
        g.fillRect(5,5,1185,562);
        map.drawMap(g);
        g.setColor(Color.BLUE);
        g.fillOval(x, y, r, r);
        g.setColor(Color.GREEN);
        g.fill3DRect(pade, 550, pw, 10, true);
        
       g.setColor(Color.white);
                g.setFont(new Font("serif",Font.BOLD,25));
                g.drawString("Score="+score,1090,500);
                g.drawString("Bricks="+bricks, 50, 500);
                g.setColor(Color.pink);
                g.drawString(str, a, b);
                
                
                
                
                if(y>570){
                    g.setColor(Color.red);
                    g.setFont(new Font("serif",Font.BOLD,50));
                    g.drawString("Game Over ! !",400 , 300);
                }
        
        if(bricks==0){
                    g.setColor(Color.green);
                    g.setFont(new Font("serif",Font.BOLD,50));
                    g.drawString("Congrats ! You Won !", 400, 300);
                    y=600;
                }
                g.dispose();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //time.start();
        pade=x-10;
        x+=dx;
        y+=dy;
        if(x>1170||x<5)
            dx=-dx;
        if(y<5)
            dy=-dy;
        int p=pade;
        if(new Rectangle(x,y,r,r).intersects(new Rectangle(p,550,pw,10)))
            dy=-dy;
        A:for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(map.map[i][j]>0){
                    int X=j*map.w+100;
                    int Y=i*map.h+20;
                    int w=map.w;
                    int h=map.h;
                            
                            Rectangle brickrect =new Rectangle(X,Y,w,h);
                            Rectangle ballrect =new Rectangle(x,y,r,r);
                            
                
                if(ballrect.intersects(brickrect)){
                                map.map[i][j]--;
                                bricks--;
                                score+=5;
                            
                            if(x+19<=brickrect.x||x+1>=brickrect.x+brickrect.width)
                                dx=-dx;
                            else
                                dy=-dy;
                            break A;
                }
                }
            }
        }
        
        
         if(!bonus){
                a0=(int) (random() * 100);
                
                
                
                switch(a0){
                    
                    case 50 :
                        str="Expand";
                        bonus=true;
                        
                        break;
                    case 25 :
                        str="Shrink";
                        bonus=true;
                        break;
                    case 75 :
                        str="Fast";bonus=true;
                        break;
                    case 1 :
                        str="Slow";bonus=true;
                        break;
                    
                    default : 
                        break;
                }
                
                if(bonus){
                    a=x;
                    b=y;
                }
            }else{
                
                Rectangle bnsRect=new Rectangle(a,b,88,8);
                Rectangle playRect=new Rectangle(pade,550,pw,8);
                if(bnsRect.intersects(playRect)){
                    bonus=false;
                    switch(a0){
                        case 50:
                            pw+=40;
                            break;
                        case 25:
                            pw-=40;
                            break;
                        case 75 :
                            dx*=2;
                            dy*=2;
                            break;
                        case 1:
                            dy/=2;
                            dx/=2;
                            break;
                        
                        default:
                            break;
                    }
                }
                if(b<580)
                    b++;
                else
                    bonus=false;
            }
                
                
                if(y<=10)
                    dy=-dy;
		x=x+dx;
                y=y+dy;
		if(y>574){
                    time.stop();
                    return;
                }
        
        
        
        
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_RIGHT){
        if(pade<1200-pw){
            pade+=20;
        }
    }
     if(e.getKeyCode()==KeyEvent.VK_LEFT){
        if(pade>0){
            pade-=20;
        }
    }
    
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    private int x=250,y=300;
    private int dx=-1,dy=-2;
    private int pade=260;
    
}
