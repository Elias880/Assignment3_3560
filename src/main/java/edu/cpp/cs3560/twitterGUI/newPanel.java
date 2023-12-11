package edu.cpp.cs3560.twitterGUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public abstract class newPanel extends JPanel{
    private static final Insets insets = new Insets(0, 0, 0, 0);

    public newPanel() {
        super(new GridBagLayout());
    }

    public static void addComp(Container container, Component component, int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0, anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }
}
