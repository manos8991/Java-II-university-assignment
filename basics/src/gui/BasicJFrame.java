package gui;

import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import basics.Person;
import basics.Release;
import db.Database;
import static db.Database.count_myartist;
import static db.Database.count_myrelease;
import static db.Database.id_count_person;
import static db.Database.id_count_release;
import files.APIWrapper;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import org.json.JSONException;

public class BasicJFrame extends javax.swing.JFrame {

    boolean b = true; // for MouseListener in jTextFieldSearch
    DefaultListModel dlm;
    DefaultListModel mydlmPersons;
    DefaultListModel mydlmReleases;

    ArrayList<Person> persons = new ArrayList();
    ArrayList<Person> mypersons = new ArrayList();
    ArrayList<Release> releases = new ArrayList();
    ArrayList<Release> myreleases = new ArrayList();

    public BasicJFrame() throws IOException, JSONException, ParseException, SQLException, ClassNotFoundException {

        getContentPane().setBackground(Color.GRAY);
        dlm = new DefaultListModel();
        mydlmPersons = new DefaultListModel();
        mydlmReleases = new DefaultListModel();

        initComponents();

        for (int i = 0; i < Database.getPersonsfromDB().size(); i++) { //--------------------------------------------------------------------------------------
            mydlmPersons.addElement(Database.getPersonsfromDB().get(i));
            mypersons.add(Database.getPersonsfromDB().get(i));

        }
        for (int i = 0; i < Database.getReleasesfromDB().size(); i++) {
            mydlmReleases.addElement(Database.getReleasesfromDB().get(i));
            myreleases.add(Database.getReleasesfromDB().get(i));
        }

        jTextFieldSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (b == true) {
                    jTextFieldSearch.setText("");
                    b = false;
                }
            }
        });

        jListResults.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) // detect double click
            {
                JList jListResults = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    //  int index = jListResults.locationToIndex(evt.getPoint());
                    int selRow = jListResults.getSelectedIndex();
                    if (selRow > -1) {

                        if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Artist") == 0)// --------------------------------------------------------------------------------------
                        {
                            JOptionPane.showMessageDialog(jListResults,
                                    "Name: " + persons.get(selRow).getName()
                                    + "\nGender: " + persons.get(selRow).getGender()
                                    + "\nCountry: " + persons.get(selRow).getCountry()
                                    + "\nBegin City: " + persons.get(selRow).getBeginCity()
                                    + "\nEnd City: " + persons.get(selRow).getEndCity()
                                    + "\nID: " + persons.get(selRow).getId()
                                    + "\nBirth Date: " + datetoString(persons.get(selRow).getBirthDate())
                                    + "\nDeath Date: " + datetoString(persons.get(selRow).getDeathDate())
                                    + "\nTags: " + persons.get(selRow).getTags()
                                    + "\nAliases: " + persons.get(selRow).getAliases(),
                                    "Additional Info", JOptionPane.PLAIN_MESSAGE);
                        } else if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Release") == 0)// -------------------------------------------------------------------------------
                        {
                            JOptionPane.showMessageDialog(jListResults,
                                    "Title: " + releases.get(selRow).getTitle()
                                    + "\nAtist's Name: " + releases.get(selRow).getArtistName()
                                    + "\nLanguage: " + releases.get(selRow).getLanguage()
                                    + "\nFormat: " + releases.get(selRow).getFormat()
                                    + "\nRelease Date: " + datetoString(releases.get(selRow).getReleaseDate())
                                    + "\nStatus: " + releases.get(selRow).isStatus()
                                    + "\nID: " + releases.get(selRow).getId()
                                    + "\nTrack Count: " + releases.get(selRow).getTrackCount()
                                    + "\nMedia: " + releases.get(selRow).getMediaArrayList(),
                                    "Additional Info", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                }
            }
        });

        jListMyListArtists.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) // detect double click
            {
                JList jListMyList = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    //  int index = jListResults.locationToIndex(evt.getPoint());
                    int selRow = jListMyList.getSelectedIndex();
                    if (selRow > -1) {

                        JOptionPane.showMessageDialog(jListMyList,
                                "Name: " + mypersons.get(selRow).getName()
                                + "\nGender: " + mypersons.get(selRow).getGender()
                                + "\nCountry: " + mypersons.get(selRow).getCountry()
                                + "\nBegin City: " + mypersons.get(selRow).getBeginCity()
                                + "\nEnd City: " + mypersons.get(selRow).getEndCity()
                                + "\nID: " + mypersons.get(selRow).getId()
                                + "\nBirth Date: " + datetoString(mypersons.get(selRow).getBirthDate())
                                + "\nDeath Date: " + datetoString(mypersons.get(selRow).getDeathDate())
                                + "\nTags: " + mypersons.get(selRow).getTags()
                                + "\nAliases: " + mypersons.get(selRow).getAliases(),
                                "Additional Info", JOptionPane.PLAIN_MESSAGE);

                    }
                }
            }

        });

        jListMyListReleases.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) // detect double click
            {
                JList jListMyList = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    // Double-click detected
                    //  int index = jListResults.locationToIndex(evt.getPoint());
                    int selRow = jListMyList.getSelectedIndex();
                    if (selRow > -1) {

                        JOptionPane.showMessageDialog(jListMyList,
                                "Title: " + myreleases.get(selRow).getTitle()
                                + "\nAtist's Name: " + myreleases.get(selRow).getArtistName()
                                + "\nLanguage: " + myreleases.get(selRow).getLanguage()
                                + "\nFormat: " + myreleases.get(selRow).getFormat()
                                + "\nRelease Date: " + datetoString(myreleases.get(selRow).getReleaseDate())
                                + "\nStatus: " + myreleases.get(selRow).isStatus()
                                + "\nID: " + myreleases.get(selRow).getId()
                                + "\nTrack Count: " + myreleases.get(selRow).getTrackCount()
                                + "\nMedia: " + myreleases.get(selRow).getMediaArrayList(),
                                "Additional Info", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }

        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jTextFieldSearch = new javax.swing.JTextField();
        jComboBoxDropdown = new javax.swing.JComboBox<>();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListMyListReleases = new javax.swing.JList<>();
        jButtonSave = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListResults = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListMyListArtists = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jButtonDeleteFromMyArtists = new javax.swing.JButton();
        jButtonDeleteFromMyReleases = new javax.swing.JButton();

        popupMenu1.setLabel("popupMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextFieldSearch.setText("Search");
        jTextFieldSearch.setToolTipText("Search");
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyPressed(evt);
            }
        });

        jComboBoxDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Artist", "Album", "Release", "Copilation" }));
        jComboBoxDropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDropdownActionPerformed(evt);
            }
        });

        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jListMyListReleases.setModel(mydlmReleases
        );
        jListMyListReleases.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jListMyListReleases);

        jButtonSave.setText("Save to my List");
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/photo.png"))); // NOI18N

        jListResults.setModel(dlm);
        jListResults.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListResults.setToolTipText("Double click for more information");
        jScrollPane2.setViewportView(jListResults);

        jLabel1.setText("My Releases");

        jListMyListArtists.setModel(mydlmPersons);
        jListMyListArtists.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jListMyListArtists);

        jLabel3.setText("My Artits");

        jButtonDeleteFromMyArtists.setText("Delete Artist");
        jButtonDeleteFromMyArtists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteFromMyArtistsActionPerformed(evt);
            }
        });

        jButtonDeleteFromMyReleases.setText("Delete Release");
        jButtonDeleteFromMyReleases.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteFromMyReleasesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jComboBoxDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(362, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(497, 497, 497))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonDeleteFromMyArtists, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonDeleteFromMyReleases, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(108, 108, 108)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(163, 163, 163))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDeleteFromMyArtists, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButtonDeleteFromMyReleases, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        // TODO add your handling code here:jTextFieldSearch

    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void jComboBoxDropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDropdownActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jComboBoxDropdownActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:

        if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Artist") == 0) {
            try {
                mydlmPersons.addElement(persons.get(jListResults.getSelectedIndex()));
                mypersons.add(persons.get(jListResults.getSelectedIndex()));

                Database.InsertToMyArtists(persons.get(jListResults.getSelectedIndex()));
            } catch (IOException ex) {
                Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Release") == 0) {
            try {
                mydlmReleases.addElement(releases.get(jListResults.getSelectedIndex()));
                myreleases.add(releases.get(jListResults.getSelectedIndex()));

                Database.InsertToMyReleases((releases.get(jListResults.getSelectedIndex())));
            } catch (IOException ex) {
                Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Album") == 0) {
            JOptionPane.showMessageDialog(null, "Not supported yet");

        } else {
            JOptionPane.showMessageDialog(null, "Not supported yet");

        }


    }//GEN-LAST:event_jButtonSaveActionPerformed


    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Artist") == 0) {
            try {
                if (Database.ifSearchNameExists(jTextFieldSearch.getText()) == false) {
                    try {

                        // TODO add your handling code here
                        if (!dlm.isEmpty()) {
                            dlm.removeAllElements();
                            persons.clear();

                        }
                        for (Person person : APIWrapper.getPerson(jTextFieldSearch.getText(), (String) jComboBoxDropdown.getSelectedItem())) {
                            dlm.addElement(person);
                            persons.add(person);

                        }
                        Database.InsertToPerson(APIWrapper.getPerson(jTextFieldSearch.getText(), "Artist"));
                        Database.InsertToSearchNamePerson(jTextFieldSearch.getText(), id_count_person);

                    } catch (IOException ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (java.lang.IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null, "Artists not Found");
                    } catch (Exception ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else { // if its true
                    System.out.println("Retrieved from DB");
                    try {

                        // TODO add your handling code here
                        if (!dlm.isEmpty()) {
                            dlm.removeAllElements();
                            persons.clear();

                        }
                        for (Person person : Database.selectFromPerson(Database.getIdCountFromSearchName(jTextFieldSearch.getText()))) {
                            dlm.addElement(person);
                            persons.add(person);

                        }
                    } catch (IOException ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (java.lang.IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null, "Artists not Found");
                    } catch (Exception ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Release") == 0) {
            try {
                if (Database.ifSearchTitleExists(jTextFieldSearch.getText()) == false) {

                    try {
                        // TODO add your handling code here

                        if (!dlm.isEmpty()) {
                            dlm.removeAllElements();
                            releases.clear();
                        }
                        for (Release release : APIWrapper.getRelease(jTextFieldSearch.getText(), (String) jComboBoxDropdown.getSelectedItem())) {
                            dlm.addElement(release);
                            releases.add(release);
                        }
                        Database.InsertToRelease(APIWrapper.getRelease(jTextFieldSearch.getText(), "Release"));
                        Database.InsertToSearchTitle(jTextFieldSearch.getText(), id_count_release);

                    } catch (IOException ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JSONException ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (java.lang.IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null, "Releases not Found");
                    }

                } else { // if its true
                    System.out.println("Retrieved from DB");
                    try {

                        // TODO add your handling code here
                        if (!dlm.isEmpty()) {
                            dlm.removeAllElements();
                            releases.clear();

                        }
                        for (Release release : Database.selectFromRelease(Database.getIdCountFromSearchTitle(jTextFieldSearch.getText()))) {
                            dlm.addElement(release);
                            releases.add(release);

                        }
                    } catch (IOException ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (java.lang.IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null, "Releases not Found");
                    } catch (Exception ex) {
                        Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Album") == 0) {
            JOptionPane.showMessageDialog(null, "Not supported yet");

        } else {
            JOptionPane.showMessageDialog(null, "Not supported yet");

        }


    }//GEN-LAST:event_jButtonSearchActionPerformed

    private void jTextFieldSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {        //when enter is pressed search button is activated
            if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Artist") == 0) {
                try {
                    if (Database.ifSearchNameExists(jTextFieldSearch.getText()) == false) {
                        try {

                            // TODO add your handling code here
                            if (!dlm.isEmpty()) {
                                dlm.removeAllElements();
                                persons.clear();

                            }
                            for (Person person : APIWrapper.getPerson(jTextFieldSearch.getText(), (String) jComboBoxDropdown.getSelectedItem())) {
                                dlm.addElement(person);
                                persons.add(person);

                            }
                            Database.InsertToPerson(APIWrapper.getPerson(jTextFieldSearch.getText(), "Artist"));
                            Database.InsertToSearchNamePerson(jTextFieldSearch.getText(), id_count_person);

                        } catch (IOException ex) {
                            Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JSONException ex) {
                            Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (java.lang.IndexOutOfBoundsException ex) {
                            JOptionPane.showMessageDialog(null, "Artists not Found");
                        } catch (Exception ex) {
                            Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else { // if its true
                        System.out.println("Retrieved from DB");
                        try {

                            // TODO add your handling code here
                            if (!dlm.isEmpty()) {
                                dlm.removeAllElements();
                                persons.clear();

                            }
                            for (Person person : Database.selectFromPerson(Database.getIdCountFromSearchName(jTextFieldSearch.getText()))) {
                                dlm.addElement(person);
                                persons.add(person);

                            }
                        } catch (IOException ex) {
                            Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (java.lang.IndexOutOfBoundsException ex) {
                            JOptionPane.showMessageDialog(null, "Artists not Found");
                        } catch (Exception ex) {
                            Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Release") == 0) {
                try {
                    // TODO add your handling code here

                    if (!dlm.isEmpty()) {
                        dlm.removeAllElements();
                        releases.clear();
                    }
                    for (Release release : APIWrapper.getRelease(jTextFieldSearch.getText(), (String) jComboBoxDropdown.getSelectedItem())) {
                        dlm.addElement(release);
                        releases.add(release);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (java.lang.IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Releases not Found");
                }

            } else if (((String) jComboBoxDropdown.getSelectedItem()).compareTo("Album") == 0) {
                JOptionPane.showMessageDialog(null, "Not supported yet");

            } else {
                JOptionPane.showMessageDialog(null, "Not supported yet");

            }
        }
    }//GEN-LAST:event_jTextFieldSearchKeyPressed

    private void jButtonDeleteFromMyArtistsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteFromMyArtistsActionPerformed
        // TODO add your handling code here:
        
        int temp = jListMyListArtists.getSelectedIndex();
        
        mydlmPersons.remove(temp);
        mypersons.remove(temp);
        try {
            System.out.println(temp);
            Database.deleteFromMyArtitst(temp);
            count_myartist--;
        } catch (SQLException ex) {
            Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonDeleteFromMyArtistsActionPerformed

    private void jButtonDeleteFromMyReleasesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteFromMyReleasesActionPerformed
        // TODO add your handling code here:
        
            
        int temp = jListMyListReleases.getSelectedIndex();
        
        mydlmReleases.remove(temp);
        myreleases.remove(temp);
        try {
            System.out.println(temp);
            Database.deleteFromMyReleases(temp);
            count_myrelease--;
        } catch (SQLException ex) {
            Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonDeleteFromMyReleasesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void showBasicWindow() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BasicJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BasicJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BasicJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BasicJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BasicJFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BasicJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static String datetoString(Date date) {
        String stringDate;

        if (date != null) {
            stringDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
            return stringDate;
        } else // if (date == null)
        {
            return stringDate = "Not Found";
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDeleteFromMyArtists;
    private javax.swing.JButton jButtonDeleteFromMyReleases;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JComboBox<String> jComboBoxDropdown;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jListMyListArtists;
    private javax.swing.JList<String> jListMyListReleases;
    private javax.swing.JList<String> jListResults;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextFieldSearch;
    private java.awt.PopupMenu popupMenu1;
    // End of variables declaration//GEN-END:variables
}
