package com.kpouer.wktparser.demo.swing;

import com.kpouer.wkt.ParseException;
import com.kpouer.wkt.WKT;
import com.kpouer.wkt.shape.Shape;

import javax.swing.*;
import java.awt.*;

public class DemoFrame extends JFrame {
    private final WKTPanel wktPanel;
    public DemoFrame() {
        super("WKT Parser Demo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wktPanel = new WKTPanel();
        JTextField textField = new JTextField();
        textField.addActionListener(e -> parseWkt(textField.getText()));
        getContentPane().add(textField, BorderLayout.NORTH);
        getContentPane().add(wktPanel);
    }

    private void parseWkt(String text) {
        try {
            Shape shape = WKT.parseShape(text);
            wktPanel.setShape(shape);
            wktPanel.repaint();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
