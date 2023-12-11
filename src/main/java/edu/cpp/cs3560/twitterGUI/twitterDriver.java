package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class twitterDriver extends JFrame {


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {
                    adminPanel admin = adminPanel.getInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        


    }


}