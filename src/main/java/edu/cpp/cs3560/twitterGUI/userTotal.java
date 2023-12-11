package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class userTotal implements visitor {

    @Override
    public int visitUser(user user) {
        int count = 0;

        if (user.getClass() == single.class) {
            count += visitSingle(user);
        } else if (user.getClass() == group.class) {
            count += visitGroup(user);
        }

        return count;
    }

    @Override
    public int visitSingle(user user) {
        return 1;
    }

    @Override
    public int visitGroup(user user) {
        int count = 0;

        for (user u : ((group) user).getGroupUsers().values()) {
            count += visitUser(u);
        }

        return count;
    }

    @Override
    public String visitValUser(user user) {
        String count = "";

        if (user.getClass() == single.class) {
            count = visitValSingle(user);
        } else if (user.getClass() == group.class) {
            count = visitValGroup(user);
        }

        return count;
    }

    @Override
    public String visitValSingle(user user) {
        return user.getID();
    }

    @Override
    public String visitValGroup(user user) {
        String count = "";

        for (user u : ((group) user).getGroupUsers().values()) {
            count = visitValUser(u);
        }

        return count;
    }
}