package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class lastUpdate implements visitor {

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

    @Override
    public int visitUser(user user) {
        return 0;
    }

    @Override
    public int visitSingle(user user) {
        return 0;
    }

    @Override
    public int visitGroup(user user) {
        return 0;
    }
}