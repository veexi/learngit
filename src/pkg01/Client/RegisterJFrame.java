package pkg01.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;


public class RegisterJFrame extends JFrame {

    public RegisterJFrame mRegister;

    public LoginJFrame loginJFrame;
    public JLabel label1 = new JLabel("请选择身份类型:");

    public WhitePanel registerJpanel = new WhitePanel();

    public JTextField showDate1 = new JTextField("单机选择日期");

    public JLabel usernameJlabel = new JLabel("请输入用户名");

    public JTextField usernameJtextfield = new JTextField();

    public JRadioButton womanButton = new JRadioButton();

    public JLabel label2 = new JLabel("请选择出生年月日");

    public JComboBox cmb = new JComboBox();
    public JRadioButton manButton = new JRadioButton();
    public JLabel passwordJlabel = new JLabel("请输入密码");

    public JLabel jLabel3  = new JLabel("请选择性别");
    public JLabel manlabel = new JLabel("男");
    public JLabel womanlanel = new JLabel("女");
    public ButtonGroup buttonGroup = new ButtonGroup();

    public JPasswordField passwordField = new JPasswordField();

    public JLabel passwordAgainLabel = new JLabel("请再次输入密码");

    public JPasswordField passwordFieldAgain = new JPasswordField();

    public DateSelector dateSelector  = new DateSelector();

    public JButton confirmButton = new JButton("注册");

    public JButton returnButton = new JButton("返回");

    public RegisterJFrame(LoginJFrame loginJFrame) {

        manButton.setSelected(true);
        buttonGroup.add(manButton);
        buttonGroup.add(womanButton);
        this.loginJFrame = loginJFrame;

        mRegister=this;
        cmb.addItem("--请选择--");
        cmb.addItem("教师");
        cmb.addItem("学生");
        cmb.addItem("其他");

        cmb.setPreferredSize(new Dimension(200,25));
        setBounds(700, 250, 225, 400);
        usernameJtextfield.setColumns(15);
        passwordField.setColumns(15);
        passwordFieldAgain.setColumns(15);


        setTitle("注册窗口");


        add(registerJpanel);

        registerJpanel.add(usernameJlabel);
        registerJpanel.add(usernameJtextfield);
        registerJpanel.add(passwordJlabel);
        registerJpanel.add(passwordField);
        registerJpanel.add(passwordAgainLabel);
        registerJpanel.add(passwordFieldAgain);
        registerJpanel.add(label2);
        registerJpanel.add(dateSelector);
        registerJpanel.add(jLabel3);
        registerJpanel.add(manButton);
        registerJpanel.add(manlabel);
        registerJpanel.add(womanButton);
        registerJpanel.add(womanlanel);
        registerJpanel.add(label1);
        registerJpanel.add(cmb);

        registerJpanel.add(returnButton);
        registerJpanel.add(confirmButton);


        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                loginJFrame.setVisible(true);
            }
        });

        confirmButton.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String registerUsername = usernameJtextfield.getText();


                registerUsername = registerUsername.trim();


                if (registerUsername.equals("")) {
                    JOptionPane.showMessageDialog(null, "注册名称不能为空白.", "注册界面", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (registerUsername.length() < 2 || registerUsername.length() > 15) {
                    JOptionPane.showMessageDialog(null, "名称长度不符合规范.", "注册界面", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String registerPassword = passwordField.getText();

                String registerPasswordAgain = passwordFieldAgain.getText();

                if (!registerPassword.equals(registerPasswordAgain)) {
                    JOptionPane.showMessageDialog(null, "两次输入的密码不相同.", "注册界面", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                File userFile = new File(registerUsername);

                if (userFile.exists()) {
                    JOptionPane.showMessageDialog(null, "该用户已注册,请重试.", "注册界面", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {

                    try {

                        userFile.createNewFile();

                        FileOutputStream fileOutputStream = new FileOutputStream(userFile);

                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                        Properties properties = new Properties();
                        properties.setProperty("用户名", registerUsername);
                        properties.setProperty("密码", registerPassword);

                        properties.store(outputStreamWriter, null);

                        setVisible(false);
                        ChatFrame chatFrame = new ChatFrame();
                        chatFrame.loginUsernameLabel.setText(registerUsername);
                        chatFrame.setVisible(true);

                    } catch (Exception ex) {

                    }

                }



            }
        },KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String registerUsername = usernameJtextfield.getText();


                registerUsername = registerUsername.trim();


                if (registerUsername.equals("")) {
                    JOptionPane.showMessageDialog(null, "注册名称不能为空白.", "注册界面", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (registerUsername.length() < 2 || registerUsername.length() > 15) {
                    JOptionPane.showMessageDialog(null, "名称长度不符合规范.", "注册界面", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String registerPassword = passwordField.getText();

                String registerPasswordAgain = passwordFieldAgain.getText();

                if (!registerPassword.equals(registerPasswordAgain)) {
                    JOptionPane.showMessageDialog(null, "两次输入的密码不相同.", "注册界面", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                File userFile = new File(registerUsername);

                if (userFile.exists()) {
                    JOptionPane.showMessageDialog(null, "该用户已注册,请重试.", "注册界面", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {

                    try {

                        userFile.createNewFile();

                        FileOutputStream fileOutputStream = new FileOutputStream(userFile);

                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                        Properties properties = new Properties();
                        properties.setProperty("用户名", registerUsername);
                        properties.setProperty("密码", registerPassword);

                        properties.store(outputStreamWriter, null);

                        setVisible(false);
                        ChatFrame chatFrame = new ChatFrame();
                        chatFrame.loginUsernameLabel.setText(registerUsername);
                        chatFrame.setVisible(true);

                    } catch (Exception ex) {
                        System.out.println("文件创建异常");
                    }

                }



            }
        });




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
