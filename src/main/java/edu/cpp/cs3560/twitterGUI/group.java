package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class group extends user {

    private Map<String,user> groupUsers;

    
    public group(String id) {
        super(id);
        groupUsers = new HashMap<String,user>();
    }

    public Map<String,user> getGroupUsers() {
        return groupUsers;
    }

    

    public user addUserInGroup(user user) {
        if (!this.contains(user.getID())) {
            this.groupUsers.put(user.getID(), user);
        }
        return this;
    }

    

    @Override
    public boolean contains(String id) {
        boolean contains = false;
        for (user user : groupUsers.values()) {
            if (user.contains(id)) {
                contains = true;
            }
        }
        return contains;
    }

    
    @Override
    public int getSingleCount() {
        int count = 0;
        for (user user : this.groupUsers.values()) {
            count += user.getSingleCount();
        }
        return count;
    }

    

    @Override
    public int getGroupCount() {
        int count = 0;
        for (user user : this.groupUsers.values()) {
            if (user.getClass() == group.class) {
                ++count;
                count += user.getGroupCount();
            }
        }
        return count;
    }

    
    @Override
    public int getMessageCount() {
        int msgCount = 0;
        for (user user : this.groupUsers.values()) {
            msgCount += user.getMessageCount();
        }
        return msgCount;
    }

    
    @Override
    public void update(Subject subject) {
        for (user user : groupUsers.values()) {
            ((observe) user).update(subject);
        }
    }

    
    

    @Override
    public void accept(visitor visitor) {
        for (user user : groupUsers.values()) {
            user.accept(visitor);
        }
        visitor.visitGroup(this);
    }


}