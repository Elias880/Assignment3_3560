package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;


public class openUserView extends newPanel {

    private JButton openUserView;

    private JPanel treePanel;
    private Map<String, observe> allUsers;
    private Map<String, JPanel> openPanels;

    
    public openUserView(JPanel treePanel, Map<String, observe> allUsers) {
        super();

        this.treePanel = treePanel;
        this.allUsers = allUsers;
        initComps();
        addComps();
    }

    

    private void addComps() {
        addComp(this, openUserView, 1, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        
    }

    private void initComps() {
        openPanels = new HashMap<String, JPanel>();

        openUserView = new JButton("Open User View");
        initOpenUserViewListener();

        
        
    }

    private DefaultMutableTreeNode getSelectedNode() {
        JTree tree = ((treePanel) treePanel).getTree();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
        if (!((treePanel) treePanel).getRoot().equals(selectedNode)) {
            selectedNode = (DefaultMutableTreeNode) selectedNode.getUserObject();
        }

        return selectedNode;
    }

    

    
    private void initOpenUserViewListener() {
        openUserView.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                if (selectedNode.getClass() == single.class) {
                    userView userView = new userView(allUsers, openPanels, selectedNode);
                    openPanels.put(((user) selectedNode).getID(), userView);
                }
            }
        });
    }
}