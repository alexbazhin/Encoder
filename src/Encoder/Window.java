package Encoder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Константин on 23.02.2016.
 */

public class Window extends JFrame {
    JTextArea textArea = new JTextArea(100,100);
    JTextField textField = new JTextField("5",3);

    Window(String name) {
        super(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textField.setHorizontalAlignment(JTextField.CENTER);

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(6,6,6,6));

        JButton encode = new JButton("Зашифровать");
        JButton decode = new JButton("Расшифровать");
        encode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                permutationEncoder();
            }
        });
        decode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                permutationDecoder();
            }
        });

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(new JLabel("Введите текст"));
        horizontalBox.add(Box.createHorizontalStrut(230));
        horizontalBox.add(new JLabel("Ключ"));
        horizontalBox.add(Box.createHorizontalStrut(6));
        horizontalBox.add(textField);

        Box horizontalBox1 = Box.createHorizontalBox();
        horizontalBox1.add(Box.createHorizontalGlue());
        horizontalBox1.add(encode);
        horizontalBox1.add(decode);

        mainBox.add(horizontalBox);
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(new JScrollPane(textArea));
        mainBox.add(Box.createVerticalStrut(6));
        mainBox.add(horizontalBox1);

        setContentPane(mainBox);
        setSize(405, 300);
    }

    private void permutationEncoder() {
        String str = new String();
        for (int i = 0; i < textArea.getText().length(); i++) {
            char temp = textArea.getText().charAt(i);
            String text = Character.toString(temp);
            if ((temp>='a' && temp<='z')||(temp>='A' && temp<='Z')) {
                if (temp>='a' && temp<='z'){
                    if ('z'-temp > Integer.parseInt(textField.getText())) {
                        str = str.concat(Character.toString((char) (textArea.getText().charAt(i) + Integer.parseInt(textField.getText()))));
                    } else {
                        str = str.concat(Character.toString((char) ('a' + Integer.parseInt(textField.getText()) - ('z' - temp))));
                    }
                }
                if (temp>='A' && temp<='Z') {
                    if ('Z'-temp > Integer.parseInt(textField.getText())) {
                        str = str.concat(Character.toString((char) (textArea.getText().charAt(i) + Integer.parseInt(textField.getText()))));
                    } else {
                        str = str.concat(Character.toString((char) ('A' + Integer.parseInt(textField.getText()) - ('Z' - temp))));
                    }
                }
            }
            else str += text;
        }
        System.out.println(str);
        }

    private void permutationDecoder() {
        String str = new String();
        for (int i = 0; i < textArea.getText().length(); i++) {
            char temp = textArea.getText().charAt(i);
            String text = Character.toString(temp);
            if ((temp>='a' && temp<='z')||(temp>='A' && temp<='Z')) {
                if (temp>='a' && temp<='z'){
                    if (temp - 'a' >= Integer.parseInt(textField.getText())) {
                        str = str.concat(Character.toString((char) (textArea.getText().charAt(i) - Integer.parseInt(textField.getText()))));
                    } else {
                        str = str.concat(Character.toString((char) ('z' - Integer.parseInt(textField.getText()) + (temp-'a'))));
                    }
                }
                if (temp>='A' && temp<='Z') {
                    if (temp - 'A' >= Integer.parseInt(textField.getText())) {
                        str = str.concat(Character.toString((char) (textArea.getText().charAt(i) - Integer.parseInt(textField.getText()))));
                    } else {
                        str = str.concat(Character.toString((char) ('Z' - Integer.parseInt(textField.getText()) + (temp-'A'))));
                    }
                }

            }
                else str += text;

        }
        System.out.println(str);
    }
}