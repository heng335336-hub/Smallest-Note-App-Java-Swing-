import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.HashMap;
import com.formdev.flatlaf.FlatDarkLaf;

public class Main{
    static HashMap<String, String> map = new HashMap<>();
    public static void main(String[] args){

        FlatDarkLaf.setup();

        JFrame frame = new JFrame("MBoys Note");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon("src/MBoys No Bg.png");
        frame.setIconImage(img.getImage());

        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(100, 30));
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JMenuItem New = new JMenuItem("New");
        JMenuItem Open = new JMenuItem("Open");
        JMenuItem Save = new JMenuItem("Save");
        JMenuItem Info = new JMenuItem("Info");
        menuBar.add(New);
        menuBar.add(Open);
        menuBar.add(Save);
        menuBar.add(Info);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton btn = new JButton("x");
        btn.setForeground(new Color(94, 94, 94));
        btn.setMargin(new Insets(0,1,0,1));
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        JLabel label = new JLabel("Note");
        JLabel label1 = new JLabel("");
        label1.setVisible(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(label1);
        panel.add(btn);
        panel.setOpaque(false);

        JTabbedPane tab = new JTabbedPane();
        tab.addTab("Note", scrollPane);
        tab.setTabComponentAt(tab.indexOfComponent(scrollPane), panel);

        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int index = tab.indexOfComponent(scrollPane);
                if(index != -1){
                    tab.removeTabAt(index);
                }
            }
        });

        New.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                NewTab(frame, tab);
            }
        });

        Open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                OpenTab(frame, tab);
            }
        });

        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                SaveTab(frame, tab);
            }
        });

        Info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                info(frame);
            }
        });

        frame.add(menuBar, BorderLayout.NORTH);
        frame.add(tab, BorderLayout.CENTER);

        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_T){

                    tab.removeTabAt(tab.indexOfComponent(scrollPane));
                }
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){

                    SaveTab(frame, tab);
                }
            }
        });
        frame.setVisible(true);
    }

    public static void NewTab(JFrame frame, JTabbedPane tab){
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton btn = new JButton("x");
        btn.setForeground(new Color(94, 94, 94));
        btn.setMargin(new Insets(0,1,0,1));
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        JLabel label = new JLabel("Note");
        JLabel label1 = new JLabel("");
        label1.setVisible(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
        panel.add(label1);
        panel.add(btn);
        panel.setOpaque(false);

        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_T){
                    System.out.println(e.getKeyCode());
                    tab.removeTabAt(tab.indexOfComponent(scrollPane));
                }
                if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S){
                    System.out.println(e.getKeyCode());
                    SaveTab(frame, tab);
                }
            }
        });

        tab.addTab("Note", scrollPane);
        tab.setTabComponentAt(tab.indexOfComponent(scrollPane), panel);

        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int index = tab.indexOfComponent(scrollPane);
                if(index != -1){
                    tab.removeTabAt(index);
                }
            }
        });
    }

    public static void OpenTab(JFrame frame, JTabbedPane tab){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("D:\\D Documents"));
        int index = chooser.showOpenDialog(frame);
        if(index != JFileChooser.APPROVE_OPTION){
            return;
        }else{
            JTextArea textArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);
            JButton btn = new JButton("x");
            btn.setForeground(new Color(94, 94, 94));
            btn.setMargin(new Insets(0,1,0,1));
            btn.setFocusPainted(false);
            btn.setFocusable(false);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);

            btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    int index = tab.indexOfComponent(scrollPane);
                    if(index != -1){
                        tab.removeTabAt(index);
                    }
                }
            });

            File file = chooser.getSelectedFile();
            String name = file.getName();
            System.out.println(name);
            System.out.println(file.getAbsolutePath());

            map.put(file.getAbsolutePath(), name);

            JLabel label = new JLabel(name);
            JLabel label_path = new  JLabel(file.getAbsolutePath());
            label_path.setVisible(false);
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            panel.add(label);
            panel.add(label_path);
            panel.add(btn);
            panel.setOpaque(false);

            tab.addTab(name, scrollPane);
            tab.setTabComponentAt(tab.indexOfComponent(scrollPane), panel);
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while((line = reader.readLine()) != null){
                    textArea.append(line + "\n");
                }
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void SaveTab(JFrame frame, JTabbedPane tab){
        JPanel panel = (JPanel) tab.getTabComponentAt(tab.getSelectedIndex());
        JLabel label = (JLabel) panel.getComponent(1);
        JScrollPane scroll_content = (JScrollPane) tab.getSelectedComponent();
        JTextArea content_store = (JTextArea) scroll_content.getViewport().getView();
        String content = content_store.getText();
        if(map.containsKey(label.getText())){
            System.out.println("Yes");
            System.out.println(label.getText());
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(label.getText()));
                writer.write(content);
                writer.close();
            }catch(Exception e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }else{
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("D:\\D Documents"));
            int index = chooser.showSaveDialog(frame);
            if(index != JFileChooser.APPROVE_OPTION){
                return;
            }else{
                File file = chooser.getSelectedFile();
                JScrollPane content_scroll = (JScrollPane) tab.getSelectedComponent();
                JTextArea content_text = (JTextArea) content_scroll.getViewport().getView();
                String content_final = content_text.getText();
                String contenter = file.getName();
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
                    writer.write(content_final);
                    writer.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
                JLabel label_update = (JLabel) panel.getComponent(0);
                label_update.setText(contenter);
                tab.setTabComponentAt(tab.indexOfComponent(content_scroll), panel);
            }
        }
    }

    public static void info(JFrame frame){
        JLabel label = new  JLabel();
        label.setText("<html>Ctrl + T = Delete Tab<br>Ctrl + S = Save<html>");
        JOptionPane.showMessageDialog(frame, label, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}