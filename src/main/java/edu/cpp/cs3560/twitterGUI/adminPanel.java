package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;


public class adminPanel extends newPanel{
    private static adminPanel INSTANCE;

    private static JFrame frame;
    private JPanel tree;
    private JPanel addUser;
    private JPanel openUserView;
    private JPanel showInfo;

    private DefaultMutableTreeNode root;
    private Map<String, observe> allUsers;

    public static adminPanel getInstance() {
        if (INSTANCE == null) {
            synchronized (Driver.class) {
                if (INSTANCE == null) {
                    INSTANCE = new adminPanel();
                }
            }
        }
        return INSTANCE;
    }

    
    

    private adminPanel() {
        super();

        initComps();
        addComps();
    }

    private void addComps() {
        addComp(frame, tree, 0, 0, 1, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(frame, addUser, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(frame, openUserView, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(frame, showInfo, 1, 3, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    private void initComps() {
        frame = new JFrame("Admin Panel");
        formatFrame();

        allUsers = new HashMap<String, observe>();
        root = new group("Root");
        allUsers.put(((user) root).getID(), (observe) this.root);

        tree = new treePanel(root);
        addUser = new addUser(tree, allUsers);
        openUserView = new openUserView(tree, allUsers);
        showInfo = new showInfo(tree);

        
    }

    private void formatFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }
}
