package pkg01.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class ChatFrame extends JFrame {

    public JPanel jPanel = new JPanel();

    public SettingJFrame settingJFrame;

    public MyTextArea jTextArea = new MyTextArea();

    public JScrollPane jScrollPane = new JScrollPane(jTextArea);

    public JTextField jTextField = new JTextField(10);

    public JButton sendButton = new JButton("发送");

    public JLabel loginUsernameLabel = new JLabel();

    public Socket socket = null;

    public JButton settingJButton = new JButton("设置");

    public ChatFrame mChatJFame;

    public ChatFrame() {

        mChatJFame = this;

        setBounds(600, 100, 440,685);

        setTitle("Java基于socket的OurChat");

        jTextArea.setRows(38);

        jTextArea.setColumns(42);

        jTextArea.setLineWrap(true);

        try {
            this.socket = new Socket("127.0.0.1" , 9000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        jTextArea.setEditable(false);
        jTextArea.setRows(38);
        jTextArea.setColumns(42);

        jTextArea.setLineWrap(true);

        jPanel.add(jScrollPane);
        jPanel.add(loginUsernameLabel);
        jPanel.add(jTextField);

        jPanel.add(sendButton);
        add(jPanel);

        jPanel.add(settingJButton);

        ReceiveThread receiveThread = new ReceiveThread(socket, jTextArea);
        receiveThread.start();

        settingJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);

                SettingJFrame settingJFrame=new SettingJFrame(mChatJFame);
                settingJFrame.setVisible(true);

            }
        });

        sendButton.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sandMessage = jTextField.getText();

                sandMessage = sandMessage.trim();

                if (sandMessage.equals("")) {

                    JOptionPane.showMessageDialog(null, "输入字符不能为空！", "警告", JOptionPane.ERROR_MESSAGE);

                    return;
                }
                if (sandMessage.length() > 100) {

                    JOptionPane.showMessageDialog(null, "发送内容过长！", "警告", JOptionPane.ERROR_MESSAGE);

                    return;
                }

                String sendMessage = jTextField.getText();
                String usernameLabelText = loginUsernameLabel.getText();
                LocalDateTime now = LocalDateTime.now();

                String finalMessage = usernameLabelText + "(" + now + ")\r\n" + sendMessage + "\r\n";

                String oldMessage = jTextArea.getText();

                String otherMessage = finalMessage;

                finalMessage = oldMessage + "\r\n" + finalMessage;

                jTextArea.setText(finalMessage);

                try {

                    OutputStream outputStream = socket.getOutputStream();

                    outputStream.write(otherMessage.getBytes());

                } catch (Exception ex) {
                    System.out.println("消息发送到服务器端异常");
                }

                jTextField.setText("");
            }
        },KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sandMessage = jTextField.getText();

                sandMessage = sandMessage.trim();

                if (sandMessage.equals("")) {

                    JOptionPane.showMessageDialog(null, "输入字符不能为空！", "警告", JOptionPane.ERROR_MESSAGE);

                    return;
                }
                if (sandMessage.length() > 100) {

                    JOptionPane.showMessageDialog(null, "发送内容过长！", "警告", JOptionPane.ERROR_MESSAGE);

                    return;
                }

                String sendMessage = jTextField.getText();
                String usernameLabelText = loginUsernameLabel.getText();
                LocalDateTime now = LocalDateTime.now();

                String finalMessage = usernameLabelText + "(" + now + ")\r\n" + sendMessage + "\r\n";

                String oldMessage = jTextArea.getText();

                String otherMessage = finalMessage;

                finalMessage = oldMessage + "\r\n" + finalMessage;

                jTextArea.setText(finalMessage);

                try {

                    OutputStream outputStream = socket.getOutputStream();

                    outputStream.write(otherMessage.getBytes());

                } catch (Exception ex) {
                    System.out.println("消息发送到服务器端异常");
                }

                jTextField.setText("");
            }
        });


       try {
            for (UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
}catch(Exception e) {
        System.out.println(e);
        }



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        }

public static void main(String[] args) {

        ChatFrame chatFrame = new ChatFrame();

        chatFrame.loginUsernameLabel.setText("veexi");

        chatFrame.setVisible(true);
    }
}

class ReceiveThread extends Thread {
    public Socket socket;

    public JTextArea jTextArea;

    public ReceiveThread(Socket socket, JTextArea jTextArea) {
        this.socket = socket;

        this.jTextArea = jTextArea;
    }

    public void run() {
        try {

            while (true) {
                TimeUnit.SECONDS.sleep(1);
                InputStream inputStream = null;
                if (!socket.isClosed()) {
                    inputStream = socket.getInputStream();
                } else {
                    continue;
                }

                byte[] bytes = new byte[1024];

                int read = 0;
                if (!socket.isClosed()) {
                    read = inputStream.read(bytes);
                }

                if (read != -1) {
                    String message = new String(bytes, 0, read);

                    System.out.println("我接收到了服务端发送来的消息 -> " + message);

                    jTextArea.append("\r\n");

                    jTextArea.append(message);
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}

