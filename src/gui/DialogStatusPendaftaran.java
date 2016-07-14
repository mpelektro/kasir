/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package gui;

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.beans.*; //Property change stuff
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import kasir.Clerk;
import net.sf.jasperreports.engine.JRException;
import org.openide.util.Exceptions;

/*
 * DialogDemo.java requires these files:
 *   CustomDialog.java
 *   images/middle.gif
 */
public class DialogStatusPendaftaran extends JPanel {
    JLabel label;
    ImageIcon icon = createImageIcon("images/middle.gif");
    AppFrame appFrame;
    String simpleDialogDesc = "Pilih Print Out Status Pendaftaran";
    String iconDesc = "A JOptionPane has its choice of icons";
    String moreDialogDesc = "Pilih Print Out Berita Acara";
    CustomDialog customDialog;
    
    /** Creates the GUI shown inside the frame's content pane. */
    public DialogStatusPendaftaran(AppFrame frame) {
        super(new BorderLayout());
        this.appFrame = frame;
        customDialog = new CustomDialog(frame, "geisel", this);
        customDialog.pack();

        //Create the components.
        JPanel frequentPanel = createSimpleDialogBox();
        JPanel featurePanel = createFeatureDialogBox();
        JPanel iconPanel = createIconDialogBox();
        label = new JLabel("Klik \"PRINT\" button"
                           + " untuk print pilihan laporan.",
                           JLabel.CENTER);

        //Lay them out.
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        frequentPanel.setBorder(padding);
        featurePanel.setBorder(padding);
        iconPanel.setBorder(padding);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Status Pendaftaran", null,
                          frequentPanel,
                          simpleDialogDesc); //tooltip text
        tabbedPane.addTab("Berita Acara", null,
                          featurePanel,
                          moreDialogDesc); //tooltip text
//        tabbedPane.addTab("Dialog Icons", null,
//                          iconPanel,
//                          iconDesc); //tooltip text

        add(tabbedPane, BorderLayout.CENTER);
        add(label, BorderLayout.PAGE_END);
        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    /** Sets the text displayed at the bottom of the frame. */
    void setLabel(String newText) {
        label.setText(newText);
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DialogStatusPendaftaran.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /** Creates the panel shown by the first tab. */
    private JPanel createSimpleDialogBox() {
        final int numButtons = 5;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        JButton showItButton = null;

        final String defaultMessageCommand = "default";
        final String yesNoCommand = "yesno";
        final String yeahNahCommand = "yeahnah";
        final String yncCommand = "ync";
        final String batalCommand = "batal";
        radioButtons[0] = new JRadioButton("Rekap Status Pendaftaran");
        radioButtons[0].setActionCommand(defaultMessageCommand);

        radioButtons[1] = new JRadioButton("Lunas");
        radioButtons[1].setActionCommand(yesNoCommand);

        radioButtons[2] = new JRadioButton("Proses");
        radioButtons[2].setActionCommand(yeahNahCommand);

        radioButtons[3] = new JRadioButton("Daftar");
        radioButtons[3].setActionCommand(yncCommand);
        
        radioButtons[4] = new JRadioButton("Batal");
        radioButtons[4].setActionCommand(batalCommand);
        
        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("PRINT");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //ok dialog
                if (command == defaultMessageCommand) {
                    try {
                        //                    JOptionPane.showMessageDialog(appFrame,
//                                "Eggs aren't supposed to be green.");
                        appFrame.printStatusPendaftaran(Clerk.current);
//yes/no dialog
                    } catch (JRException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (PrinterException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                } else if (command == yesNoCommand) {
                    try {
                        appFrame.printDetailStatusPendaftaran(Clerk.current, "LUNAS");
                        //yes/no (not in those words)
                    } catch (JRException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (PrinterException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                } else if (command == yeahNahCommand) {
                    try {
                        appFrame.printDetailStatusPendaftaran(Clerk.current, "PROSES");
                        //yes/no (not in those words)
                    } catch (JRException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (PrinterException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                //yes/no/cancel (not in those words)
                } else if (command == yncCommand) {
                    try {
                        appFrame.printDetailStatusPendaftaran(Clerk.current, "DAFTAR");
                        //yes/no (not in those words)
                    } catch (JRException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (PrinterException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }else if (command == batalCommand) {
                    try {
                        appFrame.printDetailStatusPendaftaran(Clerk.current, "BATAL");
                        //yes/no (not in those words)
                    } catch (JRException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (PrinterException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
                return;
            }
        });

        return createPane(simpleDialogDesc + ":",
                          radioButtons,
                          showItButton);
    }

    /**
     * Used by createSimpleDialogBox and createFeatureDialogBox
     * to create a pane containing a description, a single column
     * of radio buttons, and the Show it! button.
     */
    private JPanel createPane(String description,
                              JRadioButton[] radioButtons,
                              JButton showButton) {

        int numChoices = radioButtons.length;
        JPanel box = new JPanel();
        JLabel label = new JLabel(description);

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);

        for (int i = 0; i < numChoices; i++) {
            box.add(radioButtons[i]);
        }

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);
        return pane;
    }

    /**
     * Like createPane, but creates a pane with 2 columns of radio
     * buttons.  The number of buttons passed in *must* be even.
     */
     private JPanel create2ColPane(String description,
                                  JRadioButton[] radioButtons,
                                  JButton showButton) {
        JLabel label = new JLabel(description);
        int numPerColumn = radioButtons.length/2;

        JPanel grid = new JPanel(new GridLayout(0, 2));
        for (int i = 0; i < numPerColumn; i++) {
            grid.add(radioButtons[i]);
            grid.add(radioButtons[i + numPerColumn]);
        }

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);
        grid.setAlignmentX(0.0f);
        box.add(grid);

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);

        return pane;
    }

    /*
     * Creates the panel shown by the 3rd tab.
     * These dialogs are implemented using showMessageDialog, but
     * you can specify the icon (using similar code) for any other
     * kind of dialog, as well.
     */
    private JPanel createIconDialogBox() {
        JButton showItButton = null;

        final int numButtons = 6;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        final String plainCommand = "plain";
        final String infoCommand = "info";
        final String questionCommand = "question";
        final String errorCommand = "error";
        final String warningCommand = "warning";
        final String customCommand = "custom";

        radioButtons[0] = new JRadioButton("Plain (no icon)");
        radioButtons[0].setActionCommand(plainCommand);

        radioButtons[1] = new JRadioButton("Information icon");
        radioButtons[1].setActionCommand(infoCommand);

        radioButtons[2] = new JRadioButton("Question icon");
        radioButtons[2].setActionCommand(questionCommand);

        radioButtons[3] = new JRadioButton("Error icon");
        radioButtons[3].setActionCommand(errorCommand);

        radioButtons[4] = new JRadioButton("Warning icon");
        radioButtons[4].setActionCommand(warningCommand);

        radioButtons[5] = new JRadioButton("Custom icon");
        radioButtons[5].setActionCommand(customCommand);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Show it!");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //no icon
                if (command == plainCommand) {
                    JOptionPane.showMessageDialog(appFrame,
                                    "Eggs aren't supposed to be green.",
                                    "A plain message",
                                    JOptionPane.PLAIN_MESSAGE);
                //information icon
                } else if (command == infoCommand) {
                    JOptionPane.showMessageDialog(appFrame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane informational dialog",
                                    JOptionPane.INFORMATION_MESSAGE);

            //XXX: It doesn't make sense to make a question with
            //XXX: only one button.
            //XXX: See "Yes/No (but not in those words)" for a better solution.
                //question icon
                } else if (command == questionCommand) {
                    JOptionPane.showMessageDialog(appFrame,
                                    "You shouldn't use a message dialog "
                                    + "(like this)\n"
                                    + "for a question, OK?",
                                    "Inane question",
                                    JOptionPane.QUESTION_MESSAGE);
                //error icon
                } else if (command == errorCommand) {
                    JOptionPane.showMessageDialog(appFrame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane error",
                                    JOptionPane.ERROR_MESSAGE);
                //warning icon
                } else if (command == warningCommand) {
                    JOptionPane.showMessageDialog(appFrame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane warning",
                                    JOptionPane.WARNING_MESSAGE);
                //custom icon
                } else if (command == customCommand) {
                    JOptionPane.showMessageDialog(appFrame,
                                    "Eggs aren't supposed to be green.",
                                    "Inane custom dialog",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    icon);
                }
            }
        });

        return create2ColPane(iconDesc + ":",
                              radioButtons,
                              showItButton);
    }

    /** Creates the panel shown by the second tab. */
    private JPanel createFeatureDialogBox() {
        final int numButtons = 4;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        JButton showItButton = null;

        final String pickOneCommand = "pickone";
        final String textEnteredCommand = "textfield";
        final String nonAutoCommand = "nonautooption";
        final String customOptionCommand = "customoption";
        final String nonModalCommand = "nonmodal";

        radioButtons[0] = new JRadioButton("Daftar");
        radioButtons[0].setActionCommand(pickOneCommand);

        radioButtons[1] = new JRadioButton("Proses");
        radioButtons[1].setActionCommand(textEnteredCommand);

        radioButtons[2] = new JRadioButton("Lunas");
        radioButtons[2].setActionCommand(nonAutoCommand);

        radioButtons[3] = new JRadioButton("Batal");
        radioButtons[3].setActionCommand(customOptionCommand);

//        radioButtons[4] = new JRadioButton("Non-modal dialog");
//        radioButtons[4].setActionCommand(nonModalCommand);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("Print");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //pick one of many
                if (command == pickOneCommand) {
                    try {
                        //                    JOptionPane.showMessageDialog(appFrame,
//                                "Eggs aren't supposed to be green.");
                        appFrame.printDetailBeritaAcara(Clerk.current, "DAFTAR");
//yes/no dialog
                    } catch (JRException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (PrinterException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                //text input
                } else if (command == textEnteredCommand) {
                    try {
                        //                    JOptionPane.showMessageDialog(appFrame,
//                                "Eggs aren't supposed to be green.");
                        appFrame.printDetailBeritaAcara(Clerk.current, "PROSES");
//yes/no dialog
                    } catch (JRException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (PrinterException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                //non-auto-closing dialog
                } else if (command == nonAutoCommand) {
                    try {
                        //                    JOptionPane.showMessageDialog(appFrame,
//                                "Eggs aren't supposed to be green.");
                        appFrame.printDetailBeritaAcara(Clerk.current, "LUNAS");
//yes/no dialog
                    } catch (JRException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (PrinterException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                //non-auto-closing dialog with custom message area
                //NOTE: if you don't intend to check the input,
                //then just use showInputDialog instead.
                } else if (command == customOptionCommand) {
                    try {
                        //                    JOptionPane.showMessageDialog(appFrame,
//                                "Eggs aren't supposed to be green.");
                        appFrame.printDetailBeritaAcara(Clerk.current, "BATAL");
//yes/no dialog
                    } catch (JRException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (PrinterException ex) {
                        Exceptions.printStackTrace(ex);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                //non-modal dialog
                } else if (command == nonModalCommand) {
                    //Create the dialog.
                    final JDialog dialog = new JDialog(appFrame,
                                                       "A Non-Modal Dialog");

                    //Add contents to it. It must have a close button,
                    //since some L&Fs (notably Java/Metal) don't provide one
                    //in the window decorations for dialogs.
                    JLabel label = new JLabel("<html><p align=center>"
                        + "This is a non-modal dialog.<br>"
                        + "You can have one or more of these up<br>"
                        + "and still use the main window.");
                    label.setHorizontalAlignment(JLabel.CENTER);
                    Font font = label.getFont();
                    label.setFont(label.getFont().deriveFont(font.PLAIN,
                                                             14.0f));

                    JButton closeButton = new JButton("Close");
                    closeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dialog.setVisible(false);
                            dialog.dispose();
                        }
                    });
                    JPanel closePanel = new JPanel();
                    closePanel.setLayout(new BoxLayout(closePanel,
                                                       BoxLayout.LINE_AXIS));
                    closePanel.add(Box.createHorizontalGlue());
                    closePanel.add(closeButton);
                    closePanel.setBorder(BorderFactory.
                        createEmptyBorder(0,0,5,5));

                    JPanel contentPane = new JPanel(new BorderLayout());
                    contentPane.add(label, BorderLayout.CENTER);
                    contentPane.add(closePanel, BorderLayout.PAGE_END);
                    contentPane.setOpaque(true);
                    dialog.setContentPane(contentPane);

                    //Show it.
                    dialog.setSize(new Dimension(300, 150));
                    dialog.setLocationRelativeTo(appFrame);
                    dialog.setVisible(true);
                }
            }
        });

        return createPane(moreDialogDesc + ":",
                          radioButtons,
                          showItButton);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
}
