import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.*;
import java.net.URI;

public class JostumNotepad implements ActionListener {
    JMenuItem mnew;
    JMenuItem msaveas;
    JMenuItem mcut;
    JTextArea tpad;
    JMenuItem mpaste;
    JMenuItem mcopy;
    JMenuItem mdelete;
    JMenuItem mexit;
    JMenuItem mopen;
    JMenuItem mprint;
    JMenuItem mnew_window;
    JMenuItem mfind;
    JMenuItem mpage_setup;
    JMenuItem msave;
    JMenuItem search_w_bing;
    JMenuItem FindNext;
    JMenuItem FindPrevious;
    JMenuItem Replace;
    JMenuItem Goto;
    JMenuItem Selectall;
    JMenuItem TimeDate;
    JCheckBoxMenuItem wordwrap;
    JMenuItem font;
    JScrollPane scrollPane;
    JCheckBox statusbar;
    JMenuItem viewhelp;
    JMenuItem sendfeedback;
    JMenuItem aboutnotepad;
    JMenuItem zoomin;
    JMenuItem zoomout;
    JMenuItem resetzoom;
    JFrame frame;
    int fontsize = 14;

    public JostumNotepad() {
        JMenuBar mbar = new JMenuBar();
        JMenu mfile = new JMenu("File");
        JMenu medit = new JMenu("Edit");
        JMenu mformat = new JMenu("Format");
        JMenu mview = new JMenu("View");
        JMenu mhelp = new JMenu("Help");
        JMenu zoom = new JMenu("Zoom");

        // FILE MENU ITEMS
        mnew = new JMenuItem("New");
        mnew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        mnew.addActionListener(this);

        mnew_window = new JMenuItem("New Window");
        mnew_window.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        mnew_window.addActionListener(this);

        mopen = new JMenuItem("Open...");
        mopen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        mopen.addActionListener(this);

        msave = new JMenuItem("Save ");
        msave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        msave.addActionListener(this);

        msaveas = new JMenuItem("Save As...");
        msaveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK|KeyEvent.SHIFT_DOWN_MASK));
        msaveas.addActionListener(this);

        mpage_setup = new JMenuItem("Page Setup...");
        mpage_setup.addActionListener(this);

        mprint = new JMenuItem("Print...");
        mprint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        mprint.addActionListener(this);

        mexit = new JMenuItem("Exit");
        mexit.addActionListener(this);

        // EDIT MENU ITEMS
        mcut = new JMenuItem("Cut");
        mcut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        mcut.addActionListener(this);

        mcopy = new JMenuItem("Copy");
        mcopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        mcopy.addActionListener(this);

        mpaste = new JMenuItem("Paste");
        mpaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        mpaste.addActionListener(this);

        mdelete = new JMenuItem("Delete");
        mdelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        mdelete.addActionListener(this);

        search_w_bing = new JMenuItem("Search with Bing...");
        search_w_bing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        search_w_bing.addActionListener(e -> searchwithbing());

        FindNext = new JMenuItem("Find Next");
        FindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
        FindNext.addActionListener(this);

        FindPrevious = new JMenuItem("Find Previous");
        FindPrevious.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,KeyEvent.SHIFT_DOWN_MASK));
        FindPrevious.addActionListener(this);

        mfind = new JMenuItem("Find");
        mfind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        mfind.addActionListener(e -> openFindDialog());

        Replace = new JMenuItem("Replace");
        Replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        Replace.addActionListener(this);

        Goto = new JMenuItem("Go To...");
        Goto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        Goto.addActionListener(this);

        Selectall = new JMenuItem("Select All");
        Selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        Selectall.addActionListener(this);

        TimeDate = new JMenuItem("Time/Date");
        TimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
        TimeDate.addActionListener(this);

        //zoom edits
        zoomin = new JMenuItem("Zoom In");
        zoomin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK|KeyEvent.SHIFT_DOWN_MASK));
        zoomin.addActionListener(this);

        zoomout = new JMenuItem("Zoom Out");
        zoomout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK|KeyEvent.SHIFT_DOWN_MASK));
        zoomout.addActionListener(this);

        resetzoom= new JMenuItem("Reset Zoom");
        resetzoom.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK|KeyEvent.SHIFT_DOWN_MASK));
        resetzoom.addActionListener(this);

        // format edits
        wordwrap = new JCheckBoxMenuItem("Word Wrap");
        wordwrap.addActionListener(this);

        font = new JMenuItem("Font...");
        font.addActionListener(this);


        statusbar = new JCheckBox("Status Bar");
        statusbar.addActionListener(this);

        viewhelp = new JMenuItem("View Help");
        viewhelp.addActionListener(this);

        sendfeedback = new JMenuItem("Send Feedback");
        sendfeedback.addActionListener(this);

        aboutnotepad = new JMenuItem("About Notepad");
        aboutnotepad.addActionListener(this);
        // Add items to file menu
        mfile.add(mnew);
        mfile.add(mnew_window);
        mfile.add(mopen);
        mfile.add(msave);
        mfile.add(msaveas);
        mfile.addSeparator();
        mfile.add(mpage_setup);
        mfile.add(mprint);
        mfile.addSeparator();
        mfile.add(mexit);
        //add to zoom
        zoom.add(zoomin);
        zoom.add(zoomout);
        zoom.add(resetzoom);

        // Add items to edit menu
        medit.add(mcut);
        medit.add(mcopy);
        medit.add(mpaste);
        medit.add(mdelete);
        medit.addSeparator();
        medit.add(search_w_bing);
        medit.add(mfind);
        medit.add(FindNext);
        medit.add(FindPrevious);
        medit.add(Replace);
        medit.add(Goto);
        medit.addSeparator();
        medit.add(Selectall);
        medit.add(TimeDate);

        //add items to format
        mformat.add(wordwrap);
        mformat.add(font);

        //add items to view
        mview.add(zoom);
        mview.add(statusbar);

        //add items to help
        mhelp.add(viewhelp);
        mhelp.add(sendfeedback);
        mhelp.addSeparator();
        mhelp.add(aboutnotepad);


        // Add menus to menu bar
        mbar.add(mfile);
        mbar.add(medit);
        mbar.add(mformat);
        mbar.add(mview);
        mbar.add(mhelp);

        tpad = new JTextArea();
        JScrollPane sp = new JScrollPane(tpad);
        ImageIcon icon = new ImageIcon(JostumNotepad.class.getResource("Universal Binary.png"));
        Image image = icon.getImage();



        frame = new JFrame("Jostum Notepad");
        frame.setSize(700, 700);
        sp.setBounds(0,0,670,620);
        frame.setLayout(new BorderLayout());
        frame.setJMenuBar(mbar);
        frame.setTitle( "Untitled-Jostum Notepad");
        frame.setIconImage(image);
        frame.add(sp,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == mcut) {
            tpad.cut();
        } else if (ae.getSource() == mpaste) {
            tpad.paste();
        } else if (ae.getSource() == mcopy) {
            tpad.copy();
        } else if (ae.getSource() == mdelete) {
            tpad.setText("");
        } else if (ae.getSource() == mexit) {
            frame.dispose();
        } else if (ae.getSource() == mnew) {
            tpad.setText("");             // Clear text area
            fontsize = 14;                // Reset font size
            tpad.setFont(new Font(tpad.getFont().getFamily(), Font.PLAIN, fontsize));
            frame.setTitle("Jostum Notepad");
        } else if (ae.getSource() == msaveas) {
            saveFile();

        } else if (ae.getSource() == FindNext) {
            showFindDialog();
        } else if (ae.getSource()==FindPrevious) {
            showFindDialog();
        } else if (ae.getSource() == msave) {
            saveFile();

        } else if (ae.getSource()==zoomin) {
            zoomin();
        } else if (ae.getSource()==zoomout) {
            zoomout();
        }
        else if (ae.getSource()==resetzoom) {
            resetzoom();
        }
        else if (ae.getSource()==font) {
            quickFontChooser();
        } else if (ae.getSource()==mprint) {
            try{
                boolean done = tpad.print();
                if (done){
                    JOptionPane.showMessageDialog(null,"Printed Successfully");
                } else {
                    JOptionPane.showMessageDialog(null,"Error printing file");
                }
            } catch (Exception e) {
                System.out.println("Error while printing"+ e.getMessage());
            }
        } else if (ae.getSource()==Selectall) {
            tpad.selectAll();

        } else if (ae.getSource() == TimeDate) {
            String dt = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy").format(java.time.LocalDateTime.now());
            tpad.insert(dt, tpad.getCaretPosition());
        } else if (ae.getSource()==wordwrap) {
            boolean wrap =wordwrap.isSelected();
            tpad.setLineWrap(true);
            tpad.setWrapStyleWord(true);

        } else if (ae.getSource()==mpage_setup) {
            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat pageFormat = job.pageDialog(job.defaultPage());
        }
        else if (ae.getSource() == Replace) {
            showFindDialog();
        } else if (ae.getSource()==mopen) {
            openFile();
        }else if (ae.getSource() == mnew_window) {
            SwingUtilities.invokeLater(() -> new JostumNotepad());
        }
        else if (ae.getSource()==viewhelp) {
            openHelpPage();
        } else if (ae.getSource()==sendfeedback) {
            sendFeedbackEmail();
        } else if (ae.getSource()==aboutnotepad) {
            showAboutDialog();
        } else if (ae.getSource() == Goto) {
            showGoToDialog();
        }
    }

    private void showAboutDialog() {
        String message = """
        Jostum Notepad
        Version 1.0
        
        A simple text editor built with Java Swing.
        
        © 2025 Praise
        """;
        JOptionPane.showMessageDialog(frame, message, "About Notepad", JOptionPane.INFORMATION_MESSAGE);
    }


    private void sendFeedbackEmail() {
        try {
            String email = "support@example.com";  // replace with your actual support email
            String subject = "Feedback for Jostum Notepad";
            String body = "Please write your feedback here...";

            String mailto = String.format("mailto:%s?subject=%s&body=%s",
                    java.net.URLEncoder.encode(email, "UTF-8"),
                    java.net.URLEncoder.encode(subject, "UTF-8"),
                    java.net.URLEncoder.encode(body, "UTF-8"));

            Desktop.getDesktop().mail(new URI(mailto));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Unable to open mail client: " + e.getMessage());
        }
    }


    private void openHelpPage() {
        try {
            Desktop desktop = Desktop.getDesktop();
            URI helpURI = new URI("https://support.microsoft.com/en-us/windows/notepad-help-9c41b79f-8f59-93a6-32f9-2ba1e66a0f9a"); // Example URL
            desktop.browse(helpURI);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Unable to open help page: " + e.getMessage());
        }
    }


    private void openFindDialog(){
        String Searchtext = JOptionPane.showInputDialog(null,"Enter text to find");
        if (Searchtext!=null&& !Searchtext.isEmpty()){
            String content = tpad.getText();
            int id = content.indexOf(Searchtext);
            if (id>=0){
                tpad.requestFocus();
                tpad.select(id,id + Searchtext.length());

            }else {
                JOptionPane.showMessageDialog(null,"Text not found");
            }


        }
    }
    public static void main(String[] args) {
        new JostumNotepad();
    }

    public void saveFile() {
        try {
            if (currentFile == null) {
                JFileChooser chooser = new JFileChooser();
                int option = chooser.showSaveDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    currentFile = chooser.getSelectedFile();
                    frame.setTitle(currentFile.getName()+" -Jostum Notepad");
                } else {
                    return; // user canceled save dialog
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                tpad.write(writer);
            }
            JOptionPane.showMessageDialog(frame, "Saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage());
        }
    }


    public void showFindDialog() {
        JDialog findDialog = new JDialog(frame, "Find Next/Replace/Find Previous", false);
        findDialog.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        findDialog.setSize(400, 400);

        JLabel findLabel = new JLabel("Find what:");
        JTextField findField = new JTextField(20);
        JLabel replaceLabel = new JLabel("Replace with:");
        JTextField replaceField = new JTextField(20);
        JButton findNext = new JButton("Find Next");
        JButton replace = new JButton("Replace");
        JButton replaceAll = new JButton("Replace All");
        JButton cancel = new JButton("Cancel");

        c.insets = new Insets(5, 5, 5, 5);
        c.gridx = 0; c.gridy = 0; findDialog.add(findLabel, c);
        c.gridx = 1; findDialog.add(findField, c);
        c.gridx = 0; c.gridy = 1; findDialog.add(replaceLabel, c);
        c.gridx = 1; findDialog.add(replaceField, c);

        c.gridy = 2; c.gridx = 0; findDialog.add(findNext, c);
        c.gridx = 1; findDialog.add(replace, c);
        c.gridy = 3; c.gridx = 0; findDialog.add(replaceAll, c);
        c.gridx = 1; findDialog.add(cancel, c);

        findNext.addActionListener(e -> {
            String text = tpad.getText();
            String find = findField.getText();
            int start = tpad.getCaretPosition();
            int index = text.indexOf(find, start);
            if (index != -1) {
                tpad.select(index, index + find.length());
            } else {
                JOptionPane.showMessageDialog(findDialog, "Text not found.");
            }
        });

        replace.addActionListener(e -> {
            if (tpad.getSelectedText() != null && tpad.getSelectedText().equals(findField.getText())) {
                tpad.replaceSelection(replaceField.getText());
            }
        });

        replaceAll.addActionListener(e -> {
            String text = tpad.getText();
            String find = findField.getText();
            String replaceStr = replaceField.getText();
            text = text.replace(find, replaceStr);
            tpad.setText(text);
        });

        cancel.addActionListener(e -> findDialog.dispose());

        findDialog.setLocationRelativeTo(frame);
        findDialog.setVisible(true);


    }

    private File currentFile = null;
    public void openFile(){
        JFileChooser fileChooser = new JFileChooser();
        currentFile = fileChooser.getSelectedFile();

        int result = fileChooser.showOpenDialog(frame);
        boolean isModified;
        if(result == JFileChooser.APPROVE_OPTION){
            File currentFile = fileChooser.getSelectedFile();
            try(BufferedReader reader = new BufferedReader(new FileReader(currentFile))){
                tpad.setText("");
                String line;
                while ((line = reader.readLine()) != null){
                    tpad.append(line + "\n");
                }
                isModified =false;

            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(frame, "Error opening file: ");
                System.out.println("Error "+ioe.getMessage());
            }

        }
    }

    private void searchwithbing(){
        try{
            String selectedtext = tpad.getSelectedText();
            if (selectedtext==null||selectedtext.isEmpty()){
                selectedtext = JOptionPane.showInputDialog(null,"ENTER TEXT TO SEARCH");
            }
            if (selectedtext!=null && !selectedtext.isEmpty()){
                String encodedtext = java.net.URLEncoder.encode(selectedtext,"UTF-8");
                Desktop.getDesktop().browse(new URI("https://www.bing.com/search?q=" + encodedtext));
            }
        } catch (Exception e) {
            System.out.println("Error Searching file" + e.getMessage());
        }
    }

    private void zoomin(){
        fontsize +=2;
        tpad.setFont(new Font(tpad.getFont().getFamily(),Font.PLAIN,fontsize));
    }

    private void zoomout() {
        fontsize -= 2;
        tpad.setFont(new Font(tpad.getFont().getFamily(), Font.PLAIN, fontsize));
    }

    private void resetzoom() {
        fontsize = 14;
        tpad.setFont(new Font(tpad.getFont().getFamily(), Font.PLAIN, fontsize));
    }

    private void quickFontChooser(){
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        String fontname = (String) JOptionPane.showInputDialog(null,"Select Font","" +
                "Font Picker",JOptionPane.PLAIN_MESSAGE,null,fonts,tpad.getFont().getFamily());
        if (fontname!=null){
            tpad.setFont(new Font(fontname,Font.PLAIN,14));
        }
    }



    public void showGoToDialog() {
        JDialog goToDialog = new JDialog(frame, "Go To Line", true);
        goToDialog.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        JLabel label = new JLabel("Line number:");
        JTextField lineField = new JTextField(10);
        lineField.setPreferredSize(new Dimension(10,19));

        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.EAST;
        goToDialog.add(label, c);

        c.gridx = 1; c.anchor = GridBagConstraints.WEST;
        goToDialog.add(lineField, c);

        JButton goToBtn = new JButton("Go To");
        JButton cancelBtn = new JButton("Cancel");

        JPanel btnPanel = new JPanel();
        btnPanel.add(goToBtn);
        btnPanel.add(cancelBtn);

        c.gridx = 0; c.gridy = 1; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
        goToDialog.add(btnPanel, c);

        goToBtn.addActionListener(e -> {
            String input = lineField.getText();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(goToDialog, "Please enter a line number.");
                return;
            }
            try {
                int lineNumber = Integer.parseInt(input);
                int totalLines = tpad.getLineCount();

                if (lineNumber < 1 || lineNumber > totalLines) {
                    JOptionPane.showMessageDialog(goToDialog, "Line number must be between 1 and " + totalLines);
                    return;
                }
                // Convert line number to offset
                int offset = tpad.getLineStartOffset(lineNumber - 1);
                tpad.setCaretPosition(offset);
                tpad.requestFocus();
                goToDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(goToDialog, "Invalid number format.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(goToDialog, "Error: " + ex.getMessage());
            }
        });

        cancelBtn.addActionListener(e -> goToDialog.dispose());

        goToDialog.setSize(300, 120);
        goToDialog.setLocationRelativeTo(frame);
        goToDialog.setVisible(true);
    }


}

