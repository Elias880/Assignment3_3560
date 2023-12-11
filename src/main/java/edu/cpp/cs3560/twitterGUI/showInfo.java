package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class showInfo extends newPanel {

    private JButton userTotal;
    private JButton groupTotal;
    private JButton messageTotal;
    private JButton positivePercent;
    private JButton validate;
    private JButton lastUpdate;

    private JPanel treePanel;

    
    public showInfo(JPanel treePanel) {
        super();

        this.treePanel = treePanel;
        initializeComponents();
        addComponents();
    }

    private void addComponents() {
        addComp(this, userTotal, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(this, groupTotal, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(this, messageTotal, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(this, positivePercent, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(this, validate, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(this, lastUpdate, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    private void initializeComponents() {
        userTotal = new JButton("Show User Total");
        initUserTotalListener();

        groupTotal = new JButton("Show Group Total");
        initGroupTotalListener();

        messageTotal = new JButton("Show Messages Total");
        initMessageTotalListener();

        positivePercent = new JButton("Show Positive Percentage");
        initPositivePercentListener();

        validate = new JButton("Validate Users");
        initValidateListener();

        lastUpdate = new JButton("Last Updated User");
        initUpdateListener();
    }

    
    private DefaultMutableTreeNode getSelectedNode() {
        JTree tree = ((treePanel) treePanel).getTree();
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getSelectionPath().getLastPathComponent();
        if (!((treePanel) treePanel).getRoot().equals(selectedNode)) {
            selectedNode = (DefaultMutableTreeNode) selectedNode.getUserObject();
        }

        return selectedNode;
    }

    
    private void initUserTotalListener() {
        userTotal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                userTotal visitor = new userTotal();
                ((user) selectedNode).accept(visitor);
                String message = "User Total: " + Integer.toString(visitor.visitUser(((user) selectedNode)));

                JOptionPane.showMessageDialog(null, message, "User information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    
    private void initGroupTotalListener() {
        groupTotal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                groupTotal visitor = new groupTotal();
                ((user) selectedNode).accept(visitor);
                String message = "Group Total: " + Integer.toString(visitor.visitUser(((user) selectedNode)));

                JOptionPane.showMessageDialog(null, message, "Group information", JOptionPane.INFORMATION_MESSAGE);


            }
        });
    }

    
    private void initMessageTotalListener() {
        messageTotal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                messageTotal visitor = new messageTotal();
                ((user) selectedNode).accept(visitor);
                String message = "Message Total: " + Integer.toString(visitor.visitUser(((user) selectedNode)));

                JOptionPane.showMessageDialog(null, message, "Messages information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    
    private void initPositivePercentListener() {
        positivePercent.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                positiveTotal positiveCountVisitor = new positiveTotal();
                ((user) selectedNode).accept(positiveCountVisitor);
                int positiveCount = positiveCountVisitor.visitUser(((user) selectedNode));

                messageTotal messageCountVisitor = new messageTotal();
                ((user) selectedNode).accept(messageCountVisitor);
                int messageCount = messageCountVisitor.visitUser(((user) selectedNode));

                
                double percentage = 0;
                percentage = ((double) positiveCount / messageCount) * 100;

                String percentageString = String.format("%.2f", percentage);

                String message = percentageString + "% positive";

                JOptionPane.showMessageDialog(null, message, "Positive Percent", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void initValidateListener() {
        validate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                validate visitor = new validate();
                ((user) selectedNode).accept(visitor);

                String message = "Validation Status: " + (visitor.visitValUser(((user) selectedNode)));

                JOptionPane.showMessageDialog(null, message, "Validation Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void initUpdateListener() {
        lastUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                DefaultMutableTreeNode selectedNode = getSelectedNode();

                lastUpdate visitor = new lastUpdate();
                ((user) selectedNode).accept(visitor);

                String message = "Last Updated User: " + (visitor.visitValUser(((user) selectedNode)));

                JOptionPane.showMessageDialog(null, message, "Update Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}