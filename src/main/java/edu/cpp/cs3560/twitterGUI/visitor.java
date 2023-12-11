package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public interface visitor {

    public int visitUser(user user);
    public int visitSingle(user user);
    public int visitGroup(user user);
    public String visitValUser(user user);
    public String visitValSingle(user user);
    public String visitValGroup(user user);
}