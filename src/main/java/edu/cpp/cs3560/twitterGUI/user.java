package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public abstract class user extends DefaultMutableTreeNode implements observe {

    private String id;
    private int messageCount;

    public abstract boolean contains(String id);
    public abstract int getSingleCount();
    public abstract int getGroupCount();

    public user(String id) {
        super(id);
        this.id = id;
        this.setMessageCount(0);
    }

    
    public String getID() {
        return id;
    }

    
    public int getMessageCount() {
        return messageCount;
    }

    
    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public abstract void accept(visitor visitor);

}