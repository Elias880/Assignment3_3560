package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class addUser extends newPanel {

    private JPanel treePanel;
    private Map<String, observe> allUsers;

    private JButton addUser;
    private JButton addGroup;
    private JTextField userId;
    private JTextField groupId;

    
    public addUser(JPanel treePanel, Map<String, observe> allUsers) {
        super();
        this.treePanel = treePanel;
        this.allUsers = allUsers;

        initComps();
        addComps();
    }

    
    private void addComps() {
        addComp(this, userId, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(this, addUser, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(this, groupId, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(this, addGroup, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    private void initComps() {
        userId = new JTextField("");
        groupId = new JTextField("");

        addUser = new JButton("Add User");
        initAddUserListener();

        addGroup = new JButton("Add Group");
        initAddGroupListener();
    }

    



    private void initAddUserListener() {
        addUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                
                observe child = new single(userId.getText());

                allUsers.put(((user) child).getID(), child);
                ((treePanel) treePanel).addSingle((DefaultMutableTreeNode) child);
                
            }
        });
    }

    
    private void initAddGroupListener() {
        addGroup.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 
                observe child = new group(groupId.getText());

                allUsers.put(((user) child).getID(), child);
                ((treePanel) treePanel).addGroup((DefaultMutableTreeNode) child);
                
            }
        });
    }

}