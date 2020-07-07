package pkg01.Client;
import java.awt.BorderLayout;
import java.awt.Color;
import  javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.swing.ImageIcon;


public class LoginJFrame extends JFrame {

    public WhitePanel loginJpanel = new WhitePanel();

    public JLabel usernameJlabel = new JLabel("请输入用户名");

    public JTextField usernameJtextfield = new JTextField();

    public JLabel passwordJlabel = new JLabel("请输入密码");

    public JPasswordField passwordField = new JPasswordField();

    public JButton confirmButton = new JButton("登录");

    public JButton toRegister = new JButton("去注册");

    public RegisterJFrame registerJFrame;

    public LoginJFrame mLogin;


    public LoginJFrame() {


        mLogin=this;

        setBounds(700, 250, 225, 400);
        usernameJtextfield.setColumns(15);
        passwordField.setColumns(15);

        setTitle("登录窗口");

        add(loginJpanel);
        loginJpanel.add(usernameJlabel);
        loginJpanel.add(usernameJtextfield);
        loginJpanel.add(passwordJlabel);
        loginJpanel.add(passwordField);

        loginJpanel.add(confirmButton);


        confirmButton.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginUserName = usernameJtextfield.getText();

                String passwordFieldText = passwordField.getText();

                File userFile = new File(loginUserName);

                if (!userFile.exists()) {
                    JOptionPane.showMessageDialog(null, "该用户未注册.", "登录界面", JOptionPane.ERROR_MESSAGE);

                    return;
                } else {

                    try {

                        FileInputStream fileInputStream = new FileInputStream(userFile);

                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

                        Properties properties = new Properties();

                        properties.load(inputStreamReader);

                        String actualPassword = properties.getProperty("密码");

                        if (actualPassword.equals(passwordFieldText)) {

                            setVisible(false);

                            ChatFrame chatFrame=new ChatFrame();
                            chatFrame.setVisible(true);
                            chatFrame.loginUsernameLabel.setText(loginUserName);

                        } else {
                            JOptionPane.showMessageDialog(null, "输入密码错误.", "登录界面", JOptionPane.ERROR_MESSAGE);
                            return;
                        }


                    } catch (Exception ex) {
                        System.out.println("文件读取错误");
                    }


                }


            }
        },KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginUserName = usernameJtextfield.getText();

                String passwordFieldText = passwordField.getText();

                File userFile = new File(loginUserName);

                if (!userFile.exists()) {
                    JOptionPane.showMessageDialog(null, "该用户未注册.", "登录界面", JOptionPane.ERROR_MESSAGE);

                    return;
                } else {

                    try {

                        FileInputStream fileInputStream = new FileInputStream(userFile);

                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

                        Properties properties = new Properties();

                        properties.load(inputStreamReader);

                        String actualPassword = properties.getProperty("密码");

                        if (actualPassword.equals(passwordFieldText)) {

                            setVisible(false);

                            ChatFrame chatFrame=new ChatFrame();
                            chatFrame.setVisible(true);
                            chatFrame.loginUsernameLabel.setText(loginUserName);

                        } else {
                            JOptionPane.showMessageDialog(null, "输入密码错误.", "登录界面", JOptionPane.ERROR_MESSAGE);
                            return;
                        }


                    } catch (Exception ex) {
                        System.out.println("文件读取错误");
                    }


                }


            }
        });

        toRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);

                RegisterJFrame registerJFrame=new RegisterJFrame(mLogin);
                registerJFrame.setVisible(true);
            }
        });

        loginJpanel.add(toRegister);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    protected void paintComponent(Graphics g) {
    }

}
