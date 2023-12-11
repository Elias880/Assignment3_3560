package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class treePanel extends JPanel {

    private DefaultMutableTreeNode rootNode;
    private DefaultTreeModel treeModel;
    private JTree tree;
    private JScrollPane scrollPane;

    public treePanel(DefaultMutableTreeNode root) {
        super(new GridLayout(1,0));

        rootNode = root;
        initializeComponents();
        addComponents();
    }

    
    public JTree getTree() {
        return this.tree;
    }


    public DefaultMutableTreeNode getRoot() {
        return this.rootNode;
    }

    
    public void addGroup(DefaultMutableTreeNode child) {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        if (parentPath == null) {
            parentNode = rootNode;
        } else {
            parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
        }

        if (parentNode.getUserObject().getClass() == single.class) {
            parentNode = (DefaultMutableTreeNode) parentNode.getParent();
        }
        addUser(parentNode, child, true);
    }

    
    public void addSingle(DefaultMutableTreeNode child) {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        if (parentPath == null) {
            parentNode = rootNode;
        } else {
            parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
        }

        if (parentNode.getUserObject().getClass() == single.class) {
            parentNode = (DefaultMutableTreeNode) parentNode.getParent();
        }
        addUser(parentNode, child, true);
    }

    
    private void addUser(DefaultMutableTreeNode parent, DefaultMutableTreeNode child, boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

        if (parent == null) {
            parent = rootNode;
        }

        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }

        if (parent.getClass() != group.class) {
            parent = (DefaultMutableTreeNode) parent.getUserObject();
        }
        ((group) parent).addUserInGroup((user) child);
    }

    private void addComponents() {
        add(scrollPane);
    }

    private void initializeComponents() {
        treeModel = new DefaultTreeModel(rootNode);
        treeModel.addTreeModelListener(new MyTreeModelListener());

        tree = new JTree(treeModel);
        formatTree();

        scrollPane = new JScrollPane(tree);
    }

    private void formatTree() {
        tree.setEditable(true);
        tree.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        tree.setSelectionRow(0);
    }

    
    private class MyTreeModelListener implements TreeModelListener {
        public void treeNodesChanged(TreeModelEvent e) {
        }
        public void treeNodesInserted(TreeModelEvent e) {
        }
        public void treeNodesRemoved(TreeModelEvent e) {
        }
        public void treeStructureChanged(TreeModelEvent e) {
        }
    }

}