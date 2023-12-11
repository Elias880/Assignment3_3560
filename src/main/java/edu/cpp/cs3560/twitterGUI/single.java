package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class single extends user implements Subject {

    private static final ArrayList<String> positiveWords = new ArrayList<>(Arrays.asList("good", "great", "excellent", "awesome"));
    

    private Map<String, observe> followers;
    private Map<String, Subject> following;
    private ArrayList<String> newsFeed;

    private String latestMessage;
    private int positiveMessageCount;

    public single(String id) {
        super(id);
        followers = new HashMap<String, observe>();
        followers.put(this.getID(), this);
        following = new HashMap<String, Subject>();
        newsFeed = new ArrayList<String>();
    }

    
    public Map<String, observe> getFollowers() {
        return followers;
    }

    
    public Map<String, Subject> getFollowing() {
        return following;
    }

    
    public ArrayList<String> getNewsFeed() {
        return newsFeed;
    }

    
    public void sendMessage(String message) {
        this.latestMessage = message;
        this.setMessageCount(this.getMessageCount() + 1);

        if (isPositiveMessage(message)) {
            ++positiveMessageCount;
        }

        notifyObservers();
    }

    
    public String getLatestMessage() {
        return this.latestMessage;
    }

    
    public int getPositiveMessageCount() {
        return positiveMessageCount;
    }


    @Override
    public boolean contains(String id) {
        return this.getID().equals(id);
    }

    
    @Override
    public int getGroupCount() {
        return 0;
    }

    
    @Override
    public int getSingleCount() {
        return 1;
    }

    
    @Override
    public void update(Subject subject) {
        newsFeed.add(0, (((single) subject).getID() + ": " + ((single) subject).getLatestMessage()));
    }

    
    @Override
    public void attach(observe observer) {
        addFollower(observer);
    }

    
    @Override
    public void notifyObservers() {
        for (observe obs : followers.values()) {
            obs.update(this);
        }
    }

    

    @Override
    public void accept(visitor visitor) {
        visitor.visitSingle(this);
    }
 
    
    private void addFollower(observe user) {
        this.getFollowers().put(((user) user).getID(), user);
        ((single) user).addUserToFollow(this);
    }

    
    private void addUserToFollow(Subject toFollow){
        if (toFollow.getClass() == single.class) {
            getFollowing().put(((user) toFollow).getID(), toFollow);
        }
    }

    
    private boolean isPositiveMessage(String message) {
        boolean positive = false;
        message = message.toLowerCase();
        for (String word : positiveWords) {
            if (message.contains(word)) {
                positive = true;
            }
        }
        return positive;
    }

}