/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import javax.swing.JFrame;

/**
 *
 * @author Akshay
 */
public class GameFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GamePanel panel=new GamePanel();
        JFrame frame1=new JFrame("Brick Breaker Game !!");
        frame1.setBounds(100, 50, 1200, 600);
        frame1.setResizable(false);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(panel);
    }
    
}
