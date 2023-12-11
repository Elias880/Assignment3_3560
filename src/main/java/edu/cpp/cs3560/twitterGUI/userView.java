package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class userView extends newPanel {

    private static JFrame frame;
    private GridBagConstraints constraints;

    private JTextField follow;

    private JTextArea postTweetTA;
    private JTextArea followingTA;
    private JTextArea newsFeedTA;

    private JScrollPane postTweetSP;
    private JScrollPane followingSP;
    private JScrollPane newsFeedSP;

    private JButton followUser;
    private JButton postTweet;

    private Subject user;
    private Map<String, observe> allUsers;
    private Map<String, JPanel> openPanels;

    
    public userView(Map<String, observe> allUsers, Map<String, JPanel> allPanels, DefaultMutableTreeNode user) {
        super();

        this.user = (Subject) user;
        this.allUsers = allUsers;
        this.openPanels = allPanels;
        initializeComponents();
        addComponents();
    }


    private void addComponents() {
        addComp(frame, follow, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(frame, followUser, 1, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(frame, followingTA, 0, 1, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(frame, postTweetSP, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(frame, postTweet, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComp(frame, newsFeedSP, 0, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    private void initializeComponents() {
        frame = new JFrame("User View");
        formatFrame();

        constraints = new GridBagConstraints();
        constraints.ipady = 100;

        follow = new JTextField("");
        followUser = new JButton("Follow User");
        initFollowUserListener();

        followingTA = new JTextArea("Current Following: ");
        formatTextArea(followingTA);
        followingSP = new JScrollPane(followingTA);
        followingSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        postTweetTA = new JTextArea("");
        postTweetSP = new JScrollPane(postTweetTA);
        postTweetSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        postTweet = new JButton("Post Tweet");
        initPostTweetListener();

        newsFeedTA = new JTextArea("News Feed: ");
        formatTextArea(newsFeedTA);
        newsFeedSP = new JScrollPane(newsFeedTA);
        newsFeedSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        updateFollowing();
        updateNewsFeed();
    }

    private void formatTextArea(JTextArea textArea) {
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setRows(8);
        textArea.setEditable(false);
    }

    private void formatFrame() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(500, 600);
        frame.setVisible(true);
        frame.setTitle(((user) user).getID());

        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                openPanels.remove(((user) user).getID());
            }
        });
    }

    
    private void updateNewsFeed() {
        String list = "News Feed: \n";

        for (String news : ((single) user).getNewsFeed()) {
            list += " - " + news + "\n";
        }

        newsFeedTA.setText(list);
        newsFeedTA.setCaretPosition(0);
    }

    
    private void updateFollowing() {
        String list = "Current Following: \n";
        for (String following : ((single) user).getFollowing().keySet()) {
            list += " - " + following + "\n";
        }
        followingTA.setText(list);
        followingTA.setCaretPosition(0);
    }

    
    private void initPostTweetListener() {
        postTweet.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ((single) user).sendMessage(postTweetTA.getText());

                for (JPanel panel : openPanels.values()) {
                    ((userView) panel).updateNewsFeed();
                }
            }
        });
    }

    
    private void initFollowUserListener() {
        followUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                user toFollow = (user) allUsers.get(follow.getText());

                if (allUsers.containsKey(follow.getText())) {
                    ((Subject) toFollow).attach((observe) user);
                }
                updateFollowing();
            }
        });
    }

}