/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.awt.Graphics;
import java.awt.Color;

/**
 *
 * @author Akshay
 */
public class Map {
    final int w;
    final int h;
    private final int row;
    private final int col;
    int map[][];
    
    public Map(int row,int col){
        this.row=row;
        this.col=col;
        
        
        w=1000/col;
        h=250/row;
        map=new int[row][col];
        for(int i=0;i<row-1;i++)
            for(int j=0;j<col;j++)
                map[i][j]=1;
        for(int j=0;j<col;j++)
            map[row-1][j]=2;
    }
    
    public void drawMap(Graphics g){
        for (int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(map[i][j]==1){
                    g.setColor(Color.WHITE);
                    g.fill3DRect(j*w+100, i*h+20, w-5, h-5, false);
                }
                else if(map[i][j]==2){
                    g.setColor(Color.RED);
                g.fill3DRect(j*w+100, i*h+20, w-5, h-5, false);
            }}
        }
    }
    
}
