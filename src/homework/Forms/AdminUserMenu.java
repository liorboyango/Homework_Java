package homework.Forms;

import homework.Entities.Solution;
import homework.Entities.Task;
import homework.Users.AdminUser;
import homework.Users.User;
import homework.Utils.TasksTableRenderer;
import homework.Utils.ToastMessage;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Image;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import resources.LocalizationUtil;

public class AdminUserMenu extends javax.swing.JFrame implements ListSelectionListener, ListCellRenderer, TableModelListener {

    private AdminUser currentUser = new AdminUser();
    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private ImageIcon langIcon = new ImageIcon(getClass().getResource("/Images/LangIcon.png"));
    private static String Language = LocalizationUtil.localizedResourceBundle.getLocale().getLanguage();
    private DefaultListModel usersListModel = new DefaultListModel();
    private DefaultListModel solutionsListModel = new DefaultListModel();
    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<String> solutions = new ArrayList<>();
    private final Color markGreen = new Color(155, 255, 155);
    private final Color approvedColor = new Color(0, 102, 0);
    private final Color markRed = new Color(255, 101, 101);
    private final Color voteBlue = new Color(0, 153, 153);
    private final Color voteRed = new Color(255, 101, 101);
    private HashSet<Solution> localSolutions = new HashSet<Solution>();

    public AdminUserMenu(String email, String password) {

        initComponents();
        usersList.addListSelectionListener(this);
        solutionsList.addListSelectionListener(this);
        setIconImage(imgIcon.getImage());
        setVisible(true);

        //set language icon to button
        Image img = langIcon.getImage();
        Image newimg = img.getScaledInstance(btnLanguage.getWidth(), btnLanguage.getHeight(), java.awt.Image.SCALE_SMOOTH);
        langIcon = new ImageIcon(newimg);
        btnLanguage.setIcon(langIcon);

        btnSuggestSolution.setVisible(false);
        btnVote.setVisible(false);

        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                //retrieve permisions
                currentUser = currentUser.getUser(email, password);
                System.out.println("got admin priviliges");
                currentUser.fillTasks();
                System.out.println("tasks filled");

                updateCaptions();

                refreshSolutions();
                loadUsersList();
                setTasksTable();
                setComponentsAvailable(true);
            }
        };
        t.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWelcome = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        internalTasksTab = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        solutionsList = new javax.swing.JList<>();
        btnSuggestSolution = new javax.swing.JButton();
        btnVote = new javax.swing.JButton();
        showOrHideDoneTasks = new javax.swing.JCheckBox();
        btnMarkTask = new javax.swing.JButton();
        btnAddTask = new javax.swing.JButton();
        btnDeleteTask = new javax.swing.JButton();
        btnEditTask = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tasksTable = new javax.swing.JTable();
        internalUsersTab = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList<>();
        btnAddUser = new javax.swing.JButton();
        btnRemoveTeacher = new javax.swing.JButton();
        btnRemoveUser = new javax.swing.JButton();
        btnAddTeacher = new javax.swing.JButton();
        btnLanguage = new javax.swing.JButton();
        AdminUserMenuPBar = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMenu = new javax.swing.JMenu();
        btnMenuEditProfile = new javax.swing.JMenuItem();
        btnMenuLogout = new javax.swing.JMenuItem();
        btnMenuExit = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        btnHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Homework");
        setBackground(new java.awt.Color(153, 204, 255));
        setResizable(false);
        setSize(new java.awt.Dimension(1100, 650));

        lblWelcome.setFont(new java.awt.Font("Serif", 3, 20)); // NOI18N
        lblWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWelcome.setText("Welcome Admin!");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setName(""); // NOI18N

        internalTasksTab.setBorder(null);
        internalTasksTab.setVisible(true);

        solutionsList.setFont(new java.awt.Font("Arial", 3, 13)); // NOI18N
        solutionsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(solutionsList);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/uimessages_iw"); // NOI18N
        btnSuggestSolution.setText(bundle.getString("btnSuggestSolutionSuggest")); // NOI18N
        btnSuggestSolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuggestSolutionActionPerformed(evt);
            }
        });

        btnVote.setBackground(new java.awt.Color(0, 153, 153));
        btnVote.setText(bundle.getString("btnVoteVote")); // NOI18N
        btnVote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoteActionPerformed(evt);
            }
        });

        showOrHideDoneTasks.setText(bundle.getString("chbShowOrHideDoneTasks")); // NOI18N
        showOrHideDoneTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showOrHideDoneTasksActionPerformed(evt);
            }
        });

        btnMarkTask.setBackground(new java.awt.Color(155, 255, 155));
        btnMarkTask.setText(bundle.getString("btnMarkTaskDone")); // NOI18N
        btnMarkTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarkTaskActionPerformed(evt);
            }
        });

        btnAddTask.setText(bundle.getString("btnAddTask")); // NOI18N
        btnAddTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTaskActionPerformed(evt);
            }
        });

        btnDeleteTask.setText(bundle.getString("btnDeleteTask")); // NOI18N
        btnDeleteTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTaskActionPerformed(evt);
            }
        });

        btnEditTask.setText(bundle.getString("btnEditTask")); // NOI18N
        btnEditTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditTaskActionPerformed(evt);
            }
        });

        tasksTable.setBackground(new java.awt.Color(102, 204, 255));
        tasksTable.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        tasksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Task ID", "Task Name", "Task Deadline", "Task Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tasksTable);

        javax.swing.GroupLayout internalTasksTabLayout = new javax.swing.GroupLayout(internalTasksTab.getContentPane());
        internalTasksTab.getContentPane().setLayout(internalTasksTabLayout);
        internalTasksTabLayout.setHorizontalGroup(
            internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalTasksTabLayout.createSequentialGroup()
                .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalTasksTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEditTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(internalTasksTabLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnMarkTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAddTask, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)))
                            .addComponent(btnDeleteTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(internalTasksTabLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVote))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showOrHideDoneTasks, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(internalTasksTabLayout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(btnSuggestSolution)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        internalTasksTabLayout.setVerticalGroup(
            internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalTasksTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showOrHideDoneTasks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalTasksTabLayout.createSequentialGroup()
                        .addComponent(btnMarkTask, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddTask, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditTask, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteTask, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(internalTasksTabLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(internalTasksTabLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(internalTasksTabLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(btnVote)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSuggestSolution)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("tabTasks"), internalTasksTab); // NOI18N

        internalUsersTab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalUsersTab.setVisible(true);

        usersList.setBackground(new java.awt.Color(0, 204, 204));
        usersList.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        usersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(usersList);

        btnAddUser.setText(bundle.getString("btnAddUser")); // NOI18N
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnRemoveTeacher.setText(bundle.getString("btnRemoveTeacher")); // NOI18N
        btnRemoveTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveTeacherActionPerformed(evt);
            }
        });

        btnRemoveUser.setText(bundle.getString("btnRemoveUser")); // NOI18N
        btnRemoveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveUserActionPerformed(evt);
            }
        });

        btnAddTeacher.setText(bundle.getString("btnAddTeacher")); // NOI18N
        btnAddTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTeacherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout internalUsersTabLayout = new javax.swing.GroupLayout(internalUsersTab.getContentPane());
        internalUsersTab.getContentPane().setLayout(internalUsersTabLayout);
        internalUsersTabLayout.setHorizontalGroup(
            internalUsersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, internalUsersTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(internalUsersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRemoveTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRemoveUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        internalUsersTabLayout.setVerticalGroup(
            internalUsersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalUsersTabLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(internalUsersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(internalUsersTabLayout.createSequentialGroup()
                        .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRemoveUser, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRemoveTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 168, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("tabUsers"), internalUsersTab); // NOI18N

        btnLanguage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/homework/Images/LangIcon.png"))); // NOI18N
        btnLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanguageActionPerformed(evt);
            }
        });

        AdminUserMenuPBar.setIndeterminate(true);
        AdminUserMenuPBar.setString("");

        menuMenu.setText(bundle.getString("menuMenu")); // NOI18N

        btnMenuEditProfile.setText(bundle.getString("btnEditProfile")); // NOI18N
        btnMenuEditProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuEditProfileActionPerformed(evt);
            }
        });
        menuMenu.add(btnMenuEditProfile);

        btnMenuLogout.setText(bundle.getString("btnLogout")); // NOI18N
        btnMenuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuLogoutActionPerformed(evt);
            }
        });
        menuMenu.add(btnMenuLogout);

        btnMenuExit.setText(bundle.getString("btnExit")); // NOI18N
        btnMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuExitActionPerformed(evt);
            }
        });
        menuMenu.add(btnMenuExit);

        jMenuBar1.add(menuMenu);

        menuHelp.setText(bundle.getString("menuHelp")); // NOI18N

        btnHelpAbout.setText(bundle.getString("helpAbout")); // NOI18N
        btnHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpAboutActionPerformed(evt);
            }
        });
        menuHelp.add(btnHelpAbout);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(390, 390, 390)
                                .addComponent(AdminUserMenuPBar, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AdminUserMenuPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("jTabbedPane1");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnMenuExitActionPerformed

    private void btnMenuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLogoutActionPerformed
        MainScreen mainMenuTest = new MainScreen();
        this.dispose();
    }//GEN-LAST:event_btnMenuLogoutActionPerformed

    private void btnSuggestSolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuggestSolutionActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                int taskID;
                if (parseTaskID() < 0) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("chooseTaskFirst"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                    return;
                }
                taskID = parseTaskID();

                for (Task t : currentUser.getLocalTasks()) {
                    if (t.getTaskID() == taskID) {
                        if (!t.getSolutions().isEmpty()) {
                            for (Solution s : t.getSolutions()) {
                                if (s.getUserEmail().equals(currentUser.getEmail()) && s.getUserEmail().equals(parseSolutionEmail())) {
                                    EditSolutionPopUp editSolutionPopUp = new EditSolutionPopUp(s, currentUser);
                                    context.dispose();
                                    return;
                                }
                            }
                            AddNewSolutionPopUp addNewSolutionPopUp = new AddNewSolutionPopUp(currentUser, taskID);
                            context.dispose();
                            return;
                        } else {
                            AddNewSolutionPopUp addNewSolutionPopUp = new AddNewSolutionPopUp(currentUser, taskID);
                            context.dispose();
                            return;
                        }
                    }
                }
            }
        };
        t.start();
    }//GEN-LAST:event_btnSuggestSolutionActionPerformed

    private void btnVoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoteActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                String solutionEmail;
                if (parseSolutionEmail() == null) {
                    setComponentsAvailable(true);
                    return;
                }
                solutionEmail = parseSolutionEmail();
                int taskID;
                if (parseTaskID() < 0) {
                    setComponentsAvailable(true);
                    return;
                }
                taskID = parseTaskID();
                for (Task t : currentUser.getLocalTasks()) {
                    if (t.getTaskID() == taskID) {
                        for (Solution s : t.getSolutions()) {
                            if (s.getUserEmail().equals(solutionEmail)) {
                                if (!currentUser.isAlreadyVoted(s.getSolutionID())) {
                                    if (currentUser.voteSolution(s.getSolutionID())) {
                                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("addVoteSucc"), 3000);
                                        toastMessage.setVisible(true);
                                        refreshSolutions();
                                        setComponentsAvailable(true);
                                        return;
                                    } else {
                                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("addVoteFail"), 3000);
                                        toastMessage.setVisible(true);
                                        refreshSolutions();
                                        setComponentsAvailable(true);
                                        return;
                                    }
                                } else if (currentUser.unVoteSolution(s.getSolutionID())) {
                                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("removeVoteSucc"), 3000);
                                    toastMessage.setVisible(true);
                                    refreshSolutions();
                                    setComponentsAvailable(true);
                                    return;
                                } else {
                                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("removeVoteFail"), 3000);
                                    toastMessage.setVisible(true);
                                    refreshSolutions();
                                    setComponentsAvailable(true);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        };
        t.start();
    }//GEN-LAST:event_btnVoteActionPerformed

    private void btnMarkTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMarkTaskActionPerformed
        markTask();
    }//GEN-LAST:event_btnMarkTaskActionPerformed

    private void showOrHideDoneTasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showOrHideDoneTasksActionPerformed
        setTasksTable();
    }//GEN-LAST:event_showOrHideDoneTasksActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        String email = JOptionPane.showInputDialog(LocalizationUtil.localizedResourceBundle.getString("enterUserEmail"));
        if (email == null) {
            return;
        }
        HashSet<User> allUsers = new HashSet<>();
        allUsers = currentUser.getAllUsers(-1);
        for (User u : allUsers) {
            if (u.getEmail().equals(email) && u.getClassID() == currentUser.getClassID()) {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errUserAlreadyInGroup"), 3000);
                toastMessage.setVisible(true);
                return;

            } else if (u.getEmail().equals(email) && u.getUserType() != 3) {
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errUserIsTeacherOrAdmin"), 3000);
                toastMessage.setVisible(true);
                return;
            } else if (u.getEmail().equals(email) && u.getUserType() == 3) {
                if (currentUser.addUserToGroup(u)) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("userAddedToGroupSucc"), 3000);
                    toastMessage.setVisible(true);
                    loadUsersList();
                    return;
                } else {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("userAddedToGroupFail"), 3000);
                    toastMessage.setVisible(true);
                    return;
                }
            }
        }
        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errNoUserWasFound"), 3000);
        toastMessage.setVisible(true);
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnRemoveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveUserActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                String email;
                if (parseUserEmail() == null) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("chooseUserFirst"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                    return;
                }
                if (isTeacher() || isAdmin()) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("pleaseChooseUser"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                    return;
                }
                email = parseUserEmail();
                HashSet<User> allUsers = new HashSet<>();
                allUsers = currentUser.getAllUsers(currentUser.getClassID());
                for (User u : allUsers) {
                    if (u.getEmail().equals(email)) {
                        if (currentUser.removeUserFromGroup(u)) {
                            ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("userRemovedFromGroupSucc"), 3000);
                            toastMessage.setVisible(true);
                            loadUsersList();
                            setComponentsAvailable(true);
                            return;
                        }
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("userRemovedFromGroupFail"), 3000);
                        toastMessage.setVisible(true);
                        setComponentsAvailable(true);
                    }
                }
            }
        };
        t.start();
    }//GEN-LAST:event_btnRemoveUserActionPerformed

    private void btnRemoveTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveTeacherActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                String email;
                if (parseUserEmail() == null) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("chooseTeacherFirst"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                    return;
                }
                if (!isTeacher()) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("pleaseChooseTeacher"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                    return;
                }
                email = parseUserEmail();
                HashSet<User> allUsers = new HashSet<>();
                allUsers = currentUser.getAllUsers(currentUser.getClassID());
                for (User u : allUsers) {
                    if (u.getEmail().equals(email)) {
                        if (currentUser.removeUserFromGroup(u)) {
                            ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("teacherRemovedFromGroupSucc"), 3000);
                            toastMessage.setVisible(true);
                            loadUsersList();
                            setComponentsAvailable(true);
                            return;
                        }
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("teacherRemovedFromGroupFail"), 3000);
                        toastMessage.setVisible(true);
                        setComponentsAvailable(true);
                    }
                }
            }
        };
        t.start();
    }//GEN-LAST:event_btnRemoveTeacherActionPerformed

    private void btnAddTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTaskActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                AddNewTaskPopUp newTaskPopUp = new AddNewTaskPopUp(currentUser);
                context.dispose();
            }
        };
        t.start();
    }//GEN-LAST:event_btnAddTaskActionPerformed

    private void btnEditTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditTaskActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                int taskID;
                if (parseTaskID() < 0) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("chooseTaskFirst"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                    return;
                }
                taskID = parseTaskID();

                for (Task t : currentUser.getLocalTasks()) {
                    if (t.getTaskID() == taskID) {
                        EditTaskPopUp editTaskPopUp = new EditTaskPopUp(t, currentUser);
                        context.dispose();
                        return;
                    }

                }
                setComponentsAvailable(true);
            }
        };
        t.start();
    }//GEN-LAST:event_btnEditTaskActionPerformed

    private void btnDeleteTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTaskActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                int taskID;
                if (parseTaskID() < 0) {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("chooseTaskFirst"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                    return;
                }
                taskID = parseTaskID();
                setSuggestSolutionBtn(taskID);
                if (currentUser.deleteTask(taskID)) {
                    System.out.println("Task Deleted Successfully");
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("deleteTaskSucc"), 3000);
                    toastMessage.setVisible(true);
                    for (Task t : currentUser.getLocalTasks()) {
                        if (t.getTaskID() == taskID) {
                            currentUser.getLocalTasks().remove(t);
                            break;
                        }
                    }
                    AdminUserMenu adminUSerMenu = new AdminUserMenu(currentUser.getEmail(), currentUser.getPassword());
                    context.dispose();
                } else {
                    System.out.println("Failed To Delete Task");
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("deleteTaskFail"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                }
            }
        };
        t.start();
    }//GEN-LAST:event_btnDeleteTaskActionPerformed

    private void btnAddTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTeacherActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                String email = JOptionPane.showInputDialog(LocalizationUtil.localizedResourceBundle.getString("enterTeacherEmail"));
                if (email == null) {
                    setComponentsAvailable(true);
                    return;
                }
                HashSet<User> allUsers = new HashSet<>();
                allUsers = currentUser.getAllUsers(-1);
                for (User u : allUsers) {
                    if (u.getEmail().equals(email) && u.getClassID() == currentUser.getClassID()) {
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errTeacherAlreadyInGroup"), 3000);
                        toastMessage.setVisible(true);
                        setComponentsAvailable(true);
                        return;

                    } else if (u.getEmail().equals(email) && u.getUserType() != 2) {
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errUserIsNotTeacher"), 3000);
                        toastMessage.setVisible(true);
                        setComponentsAvailable(true);
                        return;
                    } else if (u.getEmail().equals(email) && u.getUserType() == 2) {
                        if (currentUser.addUserToGroup(u)) {
                            ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("teacherAddedToGroupSucc"), 3000);
                            toastMessage.setVisible(true);
                            loadUsersList();
                            setComponentsAvailable(true);
                            return;
                        } else {
                            ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("teacherAddedToGroupFail"), 3000);
                            toastMessage.setVisible(true);
                            setComponentsAvailable(true);
                            return;
                        }
                    }
                }
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errNoTeacherWasFound"), 3000);
                toastMessage.setVisible(true);
                setComponentsAvailable(true);
            }
        };
        t.start();
    }//GEN-LAST:event_btnAddTeacherActionPerformed

    private void btnLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanguageActionPerformed
        if (Language.equals("iw")) {
            Language = "en";
        } else if (Language.equals("en")) {
            Language = "iw";
        } else {
            Language = LocalizationUtil.localizedResourceBundle.getLocale().getLanguage();
        }
        LocalizationUtil.localizedResourceBundle = ResourceBundle.getBundle("resources.uimessages", new Locale(Language));
        updateCaptions();
    }//GEN-LAST:event_btnLanguageActionPerformed

    private void btnHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpAboutActionPerformed
        AboutPopUp aboutPopUp = new AboutPopUp();
    }//GEN-LAST:event_btnHelpAboutActionPerformed

    private void btnMenuEditProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuEditProfileActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                EditProfile editProfile = new EditProfile(currentUser);
                context.dispose();
            }
        };
        t.start();
    }//GEN-LAST:event_btnMenuEditProfileActionPerformed

    private void loadUsersList() {
        users.clear();
        for (User u : currentUser.getAllUsers(currentUser.getClassID())) {
            users.add(u.toString());
        }
        usersListModel.removeAllElements();
        for (String s : users) {
            usersListModel.addElement(s);
        }
        usersList.setModel(usersListModel);
    }

    private void loadSolutions(int taskID) {

        HashSet<Solution> tempLocalSolutions = new HashSet<>();
        for (Task t : currentUser.getLocalTasks()) {
            tempLocalSolutions.clear();
            for (Solution s : localSolutions) {
                if (s.getTaskID() == t.getTaskID()) {
                    tempLocalSolutions.add(s);
                }
            }
            t.setSolutions(tempLocalSolutions);
        }

        solutions.clear();
        for (Task t : currentUser.getLocalTasks()) {
            if (t.getTaskID() == taskID) {  //found the relevant task
                for (Solution s : t.getSolutions()) {   //add task's solutions to solutions array
                    if (s.isApproved()) {
                        solutions.add(s.toString() + " approved");
                    } else {
                        solutions.add(s.toString());
                    }
                }
                break;
            }
        }
        //clear solutions list
        solutionsListModel.removeAllElements();
        //fill soultion list
        for (String s : solutions) {
            solutionsListModel.addElement(s);
        }
        solutionsList.setCellRenderer(this);
        solutionsList.setModel(solutionsListModel);
        solutionsList.setVisible(true);
    }

    private void refreshSolutions() {
        localSolutions = currentUser.getSolutions();
    }

    private void setMarkTaskBtn(int taskID) {
        for (Task t : currentUser.getLocalTasks()) {
            if (t.getTaskID() == taskID) {
                if (t.getStatus() == false) {
                    btnMarkTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnMarkTaskDone"));
                    btnMarkTask.setBackground(markGreen);

                } else {
                    btnMarkTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnMarkTaskUndone"));
                    btnMarkTask.setBackground(markRed);
                }
                return;
            }
        }
    }

    private void setSuggestSolutionBtn(int taskID) {
        if (!solutionsList.isSelectionEmpty()) {    //if solution is selected
            btnSuggestSolution.setVisible(true);
            btnSuggestSolution.setEnabled(true);
            for (Task t : currentUser.getLocalTasks()) {
                if (t.getTaskID() == taskID) {
                    for (Solution s : t.getSolutions()) {
                        if (s.getUserEmail().equals(currentUser.getEmail()) && s.getUserEmail().equals(parseSolutionEmail())) {      //user solution
                            btnSuggestSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnSuggestSolutionEdit"));
                            btnSuggestSolution.setVisible(true);
                            btnSuggestSolution.setEnabled(true);
                            return;
                        } else {                                                                                                                                //NOT user solution
                            btnSuggestSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnSuggestSolutionSuggest"));
                            btnSuggestSolution.setVisible(false);
                            btnSuggestSolution.setEnabled(true);

                        }

                    }
                }
            }
        } else {                                              //if task is selected
            btnSuggestSolution.setVisible(true);
            btnSuggestSolution.setEnabled(true);
            btnSuggestSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnSuggestSolutionSuggest"));
            for (Task t : currentUser.getLocalTasks()) {
                if (t.getTaskID() == taskID) {
                    for (Solution s : t.getSolutions()) {
                        if (s.getUserEmail().equals(currentUser.getEmail())) {                                                             //has user solution
                            btnSuggestSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnSuggestSolutionEdit"));
                            btnSuggestSolution.setVisible(false);
                            btnSuggestSolution.setEnabled(false);
                            return;

                        } else {                                                                                                                 //Doesn't have user solution
                            btnSuggestSolution.setEnabled(true);
                        }

                    }
                }
            }
        }
    }

    private void setVoteBtn(int taskID) {       ////fix  dubble vote button
        for (Task t : currentUser.getLocalTasks()) {
            if (t.getTaskID() == taskID) {
                for (Solution s : t.getSolutions()) {
                    if (s.getUserEmail().equals(parseSolutionEmail())) {
                        if (!currentUser.isAlreadyVoted(s.getSolutionID())) {
                            btnVote.setText(LocalizationUtil.localizedResourceBundle.getString("btnVoteVote"));
                            btnVote.setBackground(voteBlue);
                        } else {
                            btnVote.setText(LocalizationUtil.localizedResourceBundle.getString("btnVoteUnvote"));
                            btnVote.setBackground(voteRed);
                        }
                    }
                }
            }
        }
    }

    private int parseTaskID() {
        int taskID = -1;
        int selectedRow = tasksTable.getSelectedRow();
        if (selectedRow >= 0) {
            taskID = Integer.valueOf(tasksTable.getValueAt(selectedRow, 0).toString());
        }
        return taskID;

    }

    private String parseUserEmail() {
        if (usersList.getSelectedValue() != null) {
            if (usersList.getSelectedValue().toString().endsWith("TEACHER")
                    || usersList.getSelectedValue().toString().endsWith("ADMIN")) {
                return usersList.getSelectedValue().toString().split("Email: ")[1].split(" ")[0];
            } else {
                return usersList.getSelectedValue().toString().split("Email: ")[1];
            }
        }
        return null;
    }

    private boolean isTeacher() {
        if (usersList.getSelectedValue() != null) {
            if (usersList.getSelectedValue().endsWith("TEACHER")) {
                return true;
            }
        }
        return false;
    }

    private boolean isAdmin() {
        if (usersList.getSelectedValue() != null) {
            if (usersList.getSelectedValue().endsWith("ADMIN")) {
                return true;
            }
        }
        return false;
    }

    private String parseSolutionEmail() {
        if (solutionsList.getSelectedValue() != null) {
            return solutionsList.getSelectedValue().split(": ")[1];
        }
        return null;
    }

    private void updateCaptions() {

        setTitle(LocalizationUtil.localizedResourceBundle.getString("adminMenuKey"));
        lblWelcome.setText(LocalizationUtil.localizedResourceBundle.getString("lblWelcome") + currentUser.getUsername());
        btnMarkTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnMarkTaskDone"));
        btnAddTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnAddTask"));
        btnEditTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnEditTask"));
        btnDeleteTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnDeleteTask"));
        btnAddUser.setText(LocalizationUtil.localizedResourceBundle.getString("btnAddUser"));
        btnAddTeacher.setText(LocalizationUtil.localizedResourceBundle.getString("btnAddTeacher"));
        btnRemoveUser.setText(LocalizationUtil.localizedResourceBundle.getString("btnRemoveUser"));
        btnRemoveTeacher.setText(LocalizationUtil.localizedResourceBundle.getString("btnRemoveTeacher"));
        menuMenu.setText(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
        menuHelp.setText(LocalizationUtil.localizedResourceBundle.getString("menuHelp"));
        btnHelpAbout.setText(LocalizationUtil.localizedResourceBundle.getString("helpAbout"));
        btnMenuEditProfile.setText(LocalizationUtil.localizedResourceBundle.getString("btnEditProfile"));
        btnMenuExit.setText(LocalizationUtil.localizedResourceBundle.getString("btnExit"));
        btnMenuLogout.setText(LocalizationUtil.localizedResourceBundle.getString("btnLogout"));
        btnVote.setText(LocalizationUtil.localizedResourceBundle.getString("btnVoteVote"));
        btnSuggestSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnSuggestSolutionSuggest"));
        showOrHideDoneTasks.setText(LocalizationUtil.localizedResourceBundle.getString("chbShowOrHideDoneTasks"));
        internalTasksTab.setTitle(LocalizationUtil.localizedResourceBundle.getString("tabTasks"));
        internalUsersTab.setTitle(LocalizationUtil.localizedResourceBundle.getString("tabUsers"));
        jTabbedPane1.setTitleAt(0, LocalizationUtil.localizedResourceBundle.getString("tabTasks"));
        jTabbedPane1.setTitleAt(1, LocalizationUtil.localizedResourceBundle.getString("tabUsers"));
        tasksTable.getColumnModel().getColumn(0).setHeaderValue(LocalizationUtil.localizedResourceBundle.getString("lblTaskTableC1"));
        tasksTable.getColumnModel().getColumn(1).setHeaderValue(LocalizationUtil.localizedResourceBundle.getString("lblTaskTableC2"));
        tasksTable.getColumnModel().getColumn(2).setHeaderValue(LocalizationUtil.localizedResourceBundle.getString("lblTaskTableC3"));
        tasksTable.getColumnModel().getColumn(3).setHeaderValue(LocalizationUtil.localizedResourceBundle.getString("lblTaskTableC4"));
    }

    private void setTasksTable() {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

        //clear all rows
        for (int i = 0; i < tasksTable.getRowCount(); i++) {
            ((DefaultTableModel) tasksTable.getModel()).removeRow(i);
        }

        DefaultTableModel dtm = (DefaultTableModel) tasksTable.getModel();

        int taskIndex = 0;
        if (showOrHideDoneTasks.isSelected()) {     //show all tasks
            //set amount of rows
            dtm.setRowCount(currentUser.getLocalTasks().size());
            //fill table rows
            for (Task t : currentUser.getLocalTasks()) {
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        tasksTable.setValueAt(t.getTaskID(), taskIndex, i);
                    } else if (i == 1) {
                        tasksTable.setValueAt(t.getTaskName(), taskIndex, i);
                    } else if (i == 2) {
                        tasksTable.setValueAt(formater.format(t.getDeadline()), taskIndex, i);
                    } else if (i == 3) {
                        tasksTable.setValueAt(t.getStatus(), taskIndex, i);
                    }
                }
                taskIndex++;
            }
        } else {     //show not-done tasks 
            //get the number of not-done tasks
            int amountOfNotDoneTasks = 0;
            for (Task t : currentUser.getLocalTasks()) {
                if (!t.getStatus()) {
                    amountOfNotDoneTasks++;
                }
            }
            //set amount of rows
            dtm.setRowCount(amountOfNotDoneTasks);
            //fill table rows
            for (Task t : currentUser.getLocalTasks()) {
                if (!t.getStatus()) {
                    for (int i = 0; i < 4; i++) {
                        if (i == 0) {
                            tasksTable.setValueAt(t.getTaskID(), taskIndex, i);
                        } else if (i == 1) {
                            tasksTable.setValueAt(t.getTaskName(), taskIndex, i);
                        } else if (i == 2) {
                            tasksTable.setValueAt(formater.format(t.getDeadline()), taskIndex, i);
                        } else if (i == 3) {
                            tasksTable.setValueAt(t.getStatus(), taskIndex, i);
                        }
                    }
                } else {
                    taskIndex--;
                }
                taskIndex++;
            }
        }
        dtm.addTableModelListener(this);
        tasksTable.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = tasksTable.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int taskID = -1;
                String selectedData = null;

                int[] selectedRow = tasksTable.getSelectedRows();
                int[] selectedColumns = tasksTable.getSelectedColumns();

                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        selectedData = tasksTable.getValueAt(selectedRow[i], selectedColumns[j]).toString();
                        if (parseTaskID() < 0) {
                            return;
                        }
                        taskID = parseTaskID();
                    }
                }
                System.out.println("Selected: " + selectedData);

                btnVote.setVisible(false);
                btnSuggestSolution.setVisible(false);

                //Load Solutions
                loadSolutions(taskID);
                //Set Suggest Solution Button
                setSuggestSolutionBtn(taskID);

                //Set Mark Task Button
                setMarkTaskBtn(taskID);
            }

        });

        TasksTableRenderer myRenderer = new TasksTableRenderer();
        tasksTable.setDefaultRenderer(Object.class, myRenderer);

    }

    private void markTask() {
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                int taskID;
                if (parseTaskID() < 0) {
                    setComponentsAvailable(true);
                    return;
                }
                taskID = parseTaskID();
                for (Task t : currentUser.getLocalTasks()) //go through all local tasks
                {
                    if (taskID == t.getTaskID() && t.getStatus() == false) { //check if id exists and if the status is the oppesit from required action
                        t.setStatus(true);
                        currentUser.getLocalDoneTasks().add(taskID);
                        currentUser.markTask(taskID, true);
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("taskMarkAsDoneSucc"), 3000);
                        toastMessage.setVisible(true);
                        setTasksTable();
                        btnMarkTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnMarkTaskDone"));
                        btnMarkTask.setBackground(markGreen);
                        setComponentsAvailable(true);
                        return;
                    } else if (parseTaskID() == t.getTaskID() && t.getStatus() == true) {
                        t.setStatus(false);
                        currentUser.getLocalDoneTasks().remove(taskID);
                        currentUser.markTask(taskID, false);
                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("taskMarkAsUndoneSucc"), 3000);
                        toastMessage.setVisible(true);
                        setTasksTable();
                        btnMarkTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnMarkTaskDone"));
                        btnMarkTask.setBackground(markGreen);
                        setComponentsAvailable(true);
                        return;
                    }
                }
                ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("chooseTaskFirst"), 3000);
                toastMessage.setVisible(true);
                setComponentsAvailable(true);
            }
        };
        t.start();
    }

    private void setComponentsAvailable(boolean available) {
        AdminUserMenuPBar.setVisible(!available);
        btnMenuLogout.setEnabled(available);
        tasksTable.setEnabled(available);
        btnMarkTask.setEnabled(available);
        showOrHideDoneTasks.setEnabled(available);
        btnAddUser.setEnabled(available);
        btnRemoveUser.setEnabled(available);
        btnRemoveTeacher.setEnabled(available);
        btnAddTask.setEnabled(available);
        btnEditTask.setEnabled(available);
        btnDeleteTask.setEnabled(available);
        btnAddTeacher.setEnabled(available);
        btnLanguage.setEnabled(available);
        btnHelpAbout.setEnabled(available);
        btnMenuEditProfile.setEnabled(available);
    }

    public static void main(String email, String password) {
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
            java.util.logging.Logger.getLogger(AdminUserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminUserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminUserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminUserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminUserMenu(email, password).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar AdminUserMenuPBar;
    private javax.swing.JButton btnAddTask;
    private javax.swing.JButton btnAddTeacher;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnDeleteTask;
    private javax.swing.JButton btnEditTask;
    private javax.swing.JMenuItem btnHelpAbout;
    private javax.swing.JButton btnLanguage;
    private javax.swing.JButton btnMarkTask;
    private javax.swing.JMenuItem btnMenuEditProfile;
    private javax.swing.JMenuItem btnMenuExit;
    private javax.swing.JMenuItem btnMenuLogout;
    private javax.swing.JButton btnRemoveTeacher;
    private javax.swing.JButton btnRemoveUser;
    private javax.swing.JButton btnSuggestSolution;
    private javax.swing.JButton btnVote;
    private javax.swing.JInternalFrame internalTasksTab;
    private javax.swing.JInternalFrame internalUsersTab;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    protected javax.swing.JLabel lblWelcome;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuMenu;
    protected javax.swing.JCheckBox showOrHideDoneTasks;
    private javax.swing.JList<String> solutionsList;
    private javax.swing.JTable tasksTable;
    private javax.swing.JList<String> usersList;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel solutionRow;
        solutionRow = new JLabel();
        solutionRow.setFont(new java.awt.Font("Arial", 3, 14));
        solutionRow.setOpaque(true);
        if (isSelected) {
            solutionRow.setBackground(list.getSelectionBackground());
        } else {
            solutionRow.setBackground(list.getBackground());
        }
        if (value.toString().endsWith(" approved")) {
            solutionRow.setForeground(approvedColor);
            solutionRow.setText(value.toString().split(" approved")[0]);
        } else {
            solutionRow.setText(value.toString());
            solutionRow.setForeground(Color.BLACK);
        }
        if (Language.equals("iw")) {
            solutionRow.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        } else {
            solutionRow.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }
        return solutionRow;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (e.getSource() == solutionsList) {
                btnSuggestSolution.setEnabled(false);

                String solutionEmail;
                if (parseSolutionEmail() == null) {
                    return;
                }
                //get Email from the list string
                solutionEmail = parseSolutionEmail();

                int taskID;
                if (parseTaskID() < 0) {
                    return;
                }

                //get TaskID from the list string
                taskID = parseTaskID();

                //Set Suggest Solution Button
                setSuggestSolutionBtn(taskID);

                btnVote.setVisible(true);
                setVoteBtn(taskID);

                if (solutionEmail.equals(currentUser.getEmail())) {
                    btnVote.setEnabled(false);
                    return;
                }

                btnVote.setEnabled(true);

            }
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        //   System.out.println(e);
        int row = e.getFirstRow()/* tasksTable.getSelectedRow()*/;
        int column = e.getColumn()/*tasksTable.getSelectedColumn()*/;
        //   TableModel model = (TableModel) e.getSource();
        //    String columnName = model.getColumnName(column);
        //   Object data = model.getValueAt(row, column);
        /*     if (column==3) {
            markTask();
            if (tasksTable.getValueAt(row, 3).equals(true)) {
                System.out.println("CHECKED");
            } else {
                System.out.println("UNCHECKED");
            }
        }
         */
    }

}
