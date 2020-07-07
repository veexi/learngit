package pkg01.Client;

import javax.swing.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

public class SettingJFrame extends JFrame {

    public JPanel jPanel = new JPanel();

    public LoginJFrame loginJFrame;


    public JButton exitbutton = new JButton("退出登录");

    public JButton returnbutton = new JButton("返回");

    public SettingJFrame  mSettingJFrame;

    public ChatFrame chatFrame;

    public SettingJFrame(ChatFrame chatFrame) {

        this.chatFrame = chatFrame;

        mSettingJFrame = this;

        setBounds(750, 250, 300, 400);

        setTitle("设置");

        jPanel.add(exitbutton);

        jPanel.add(returnbutton);

        add(jPanel);

        exitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginJFrame().setVisible(true);
            }
        });

        returnbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                chatFrame.setVisible(true);
            }
        });






        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }









}
