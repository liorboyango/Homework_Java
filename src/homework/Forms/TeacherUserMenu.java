package homework.Forms;

import homework.Entities.Solution;
import homework.Entities.Task;
import homework.Users.TeacherUser;
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
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import resources.LocalizationUtil;

public class TeacherUserMenu extends javax.swing.JFrame implements ListSelectionListener, ListCellRenderer {

    private final ImageIcon imgIcon = new ImageIcon(getClass().getResource("/Images/icon.gif"));
    private ImageIcon langIcon = new ImageIcon(getClass().getResource("/Images/LangIcon.png"));
    private static String Language = LocalizationUtil.localizedResourceBundle.getLocale().getLanguage();
    private TeacherUser currentUser = new TeacherUser();
    private DefaultListModel usersListModel = new DefaultListModel();
    private DefaultListModel solutionsListModel = new DefaultListModel();
    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<String> solutions = new ArrayList<>();
    private final Color approvedColor = new Color(0, 102, 0);
    private final Color voteBlue = new Color(0, 153, 153);
    private final Color voteRed = new Color(255, 101, 101);
    private HashSet<Solution> localSolutions = new HashSet<Solution>();

    public TeacherUserMenu(String email, String password) {
        initComponents();

        setIconImage(imgIcon.getImage());
        setVisible(true);
        btnProvideSolution.setVisible(false);
        btnApproveSolution.setVisible(false);
        usersList.addListSelectionListener(this);
        solutionsList.addListSelectionListener(this);

        //set language icon to button
        Image img = langIcon.getImage();
        Image newimg = img.getScaledInstance(btnLanguage.getWidth(), btnLanguage.getHeight(), java.awt.Image.SCALE_SMOOTH);
        langIcon = new ImageIcon(newimg);
        btnLanguage.setIcon(langIcon);

        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                //retrieve permisions
                currentUser = currentUser.getUser(email, password);
                System.out.println("got teacher priviliges");
                currentUser.fillTasks();
                System.out.println("tasks filled");

                updateCaptions();

                refreshSolutions();
                loadUsersList(-1, true);
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
        btnProvideSolution = new javax.swing.JButton();
        btnApproveSolution = new javax.swing.JButton();
        btnAddTask = new javax.swing.JButton();
        btnDeleteTask = new javax.swing.JButton();
        btnEditTask = new javax.swing.JButton();
        btnShowUsersDoneTask = new javax.swing.JButton();
        btnShowUsersNotDoneTask = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tasksTable = new javax.swing.JTable();
        btnChat = new javax.swing.JButton();
        internalUsersTab = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList<>();
        btnLanguage = new javax.swing.JButton();
        TeacherUserMenuPBar = new javax.swing.JProgressBar();
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
        lblWelcome.setText("Welcome Teacher!");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTabbedPane1.setName(""); // NOI18N

        internalTasksTab.setBorder(null);
        internalTasksTab.setVisible(true);

        solutionsList.setFont(new java.awt.Font("Arial", 3, 13)); // NOI18N
        solutionsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(solutionsList);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("resources/uimessages_iw"); // NOI18N
        btnProvideSolution.setText(bundle.getString("btnProvideSolutionProvide")); // NOI18N
        btnProvideSolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvideSolutionActionPerformed(evt);
            }
        });

        btnApproveSolution.setBackground(new java.awt.Color(0, 153, 153));
        btnApproveSolution.setText(bundle.getString("btnApproveApprove")); // NOI18N
        btnApproveSolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveSolutionActionPerformed(evt);
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

        btnShowUsersDoneTask.setText(bundle.getString("btnShowUsersFinished")); // NOI18N
        btnShowUsersDoneTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowUsersDoneTaskActionPerformed(evt);
            }
        });

        btnShowUsersNotDoneTask.setText(bundle.getString("btnShowUsersNotFinished")); // NOI18N
        btnShowUsersNotDoneTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowUsersNotDoneTaskActionPerformed(evt);
            }
        });

        tasksTable.setBackground(new java.awt.Color(102, 204, 255));
        tasksTable.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        tasksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Task ID", "Task Name", "Task Deadline"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tasksTable);

        java.util.ResourceBundle bundle1 = java.util.ResourceBundle.getBundle("resources/uimessages"); // NOI18N
        btnChat.setText(bundle1.getString("btnChat")); // NOI18N
        btnChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout internalTasksTabLayout = new javax.swing.GroupLayout(internalTasksTab.getContentPane());
        internalTasksTab.getContentPane().setLayout(internalTasksTabLayout);
        internalTasksTabLayout.setHorizontalGroup(
            internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalTasksTabLayout.createSequentialGroup()
                .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(internalTasksTabLayout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(btnProvideSolution))
                    .addGroup(internalTasksTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnEditTask, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                .addComponent(btnDeleteTask, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddTask, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnChat, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(internalTasksTabLayout.createSequentialGroup()
                                .addGap(782, 782, 782)
                                .addComponent(btnShowUsersDoneTask, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(btnShowUsersNotDoneTask, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(internalTasksTabLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnApproveSolution))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        internalTasksTabLayout.setVerticalGroup(
            internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalTasksTabLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowUsersDoneTask)
                    .addComponent(btnShowUsersNotDoneTask))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(internalTasksTabLayout.createSequentialGroup()
                        .addComponent(btnAddTask, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditTask, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteTask, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChat))
                    .addGroup(internalTasksTabLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(internalTasksTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(internalTasksTabLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(internalTasksTabLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(btnApproveSolution)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProvideSolution)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("tabTasks"), internalTasksTab); // NOI18N

        internalUsersTab.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalUsersTab.setVisible(true);

        usersList.setBackground(new java.awt.Color(0, 204, 204));
        usersList.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        usersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(usersList);

        javax.swing.GroupLayout internalUsersTabLayout = new javax.swing.GroupLayout(internalUsersTab.getContentPane());
        internalUsersTab.getContentPane().setLayout(internalUsersTabLayout);
        internalUsersTabLayout.setHorizontalGroup(
            internalUsersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, internalUsersTabLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        internalUsersTabLayout.setVerticalGroup(
            internalUsersTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(internalUsersTabLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 168, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("tabUsers"), internalUsersTab); // NOI18N

        btnLanguage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/homework/Images/LangIcon.png"))); // NOI18N
        btnLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanguageActionPerformed(evt);
            }
        });

        TeacherUserMenuPBar.setIndeterminate(true);
        TeacherUserMenuPBar.setString("");

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
                .addGap(285, 285, 285)
                .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(388, 388, 388)
                        .addComponent(TeacherUserMenuPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(TeacherUserMenuPBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

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

    private void btnProvideSolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvideSolutionActionPerformed
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
                setComponentsAvailable(true);
            }
        };
        t.start();
    }//GEN-LAST:event_btnProvideSolutionActionPerformed

    private void btnApproveSolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveSolutionActionPerformed
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
                for (Task t : currentUser.getLocalTasks()) {
                    if (t.getTaskID() == taskID) {
                        for (Solution s : t.getSolutions()) {
                            if (s.getUserEmail().equals(parseSolutionEmail())) {
                                if (s.isApproved()) {
                                    if (currentUser.approveSolution(s, false)) {
                                        System.out.println("Successfully Disapproved Solution");
                                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("disapprovedSolutionSucc"), 3000);
                                        toastMessage.setVisible(true);
                                        setTasksTable();
                                        refreshSolutions();
                                        loadSolutions(taskID);
                                        setComponentsAvailable(true);
                                        return;
                                    } else {
                                        System.out.println("Failed To Disapprove Solution");
                                        ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("disapprovedSolutionFail"), 3000);
                                        toastMessage.setVisible(true);
                                        setTasksTable();
                                        refreshSolutions();
                                        loadSolutions(taskID);
                                        setComponentsAvailable(true);
                                        return;
                                    }
                                } else if (currentUser.approveSolution(s, true)) {
                                    System.out.println("Successfully Approved Solution");
                                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("approvedSolutionSucc"), 3000);
                                    toastMessage.setVisible(true);
                                    setTasksTable();
                                    refreshSolutions();
                                    loadSolutions(taskID);
                                    setComponentsAvailable(true);
                                    return;
                                } else {
                                    System.out.println("Failed To Approve Solution");
                                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("approvedSolutionFail"), 3000);
                                    toastMessage.setVisible(true);
                                    setTasksTable();
                                    refreshSolutions();
                                    loadSolutions(taskID);
                                    setComponentsAvailable(true);
                                    return;
                                }
                            }

                        }
                    }
                }
                setComponentsAvailable(true);
            }
        };
        t.start();
    }//GEN-LAST:event_btnApproveSolutionActionPerformed

    private void btnAddTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTaskActionPerformed
        javax.swing.JFrame context = this;
        Thread t = new Thread() {
            public void run() {
                setComponentsAvailable(false);
                if (currentUser.getClassID() != 0) {
                    AddNewTaskPopUp newTaskPopUp = new AddNewTaskPopUp(currentUser);
                    context.dispose();
                } else {
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("errNotInClass"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                }
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
                setProvideSolutionBtn(taskID);
                if (currentUser.deleteTask(taskID)) {
                    System.out.println("Successfully Deleted Task");
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("deleteTaskSucc"), 3000);
                    toastMessage.setVisible(true);
                    for (Task t : currentUser.getLocalTasks()) {
                        if (t.getTaskID() == taskID) {
                            currentUser.getLocalTasks().remove(t);
                            break;
                        }
                    }
                    TeacherUserMenu adminUSerMenu = new TeacherUserMenu(currentUser.getEmail(), currentUser.getPassword());
                    context.dispose();
                } else {
                    System.out.println("Failed To Delete Task");
                    ToastMessage toastMessage = new ToastMessage(LocalizationUtil.localizedResourceBundle.getString("deleteTaskFail"), 3000);
                    toastMessage.setVisible(true);
                    setComponentsAvailable(true);
                }
                setComponentsAvailable(true);
            }
        };
        t.start();
    }//GEN-LAST:event_btnDeleteTaskActionPerformed

    private void btnShowUsersDoneTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowUsersDoneTaskActionPerformed
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

                        loadUsersList(taskID, true);
                        jTabbedPane1.setSelectedIndex(1);
                        setComponentsAvailable(true);
                        return;
                    }

                }
                setComponentsAvailable(true);
            }
        };
        t.start();
    }//GEN-LAST:event_btnShowUsersDoneTaskActionPerformed

    private void btnShowUsersNotDoneTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowUsersNotDoneTaskActionPerformed
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

                        loadUsersList(taskID, false);
                        jTabbedPane1.setSelectedIndex(1);
                        setComponentsAvailable(true);
                        return;
                    }

                }
                setComponentsAvailable(true);
            }
        };
        t.start();
    }//GEN-LAST:event_btnShowUsersNotDoneTaskActionPerformed

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
                setComponentsAvailable(false);
                EditProfile editProfile = new EditProfile(currentUser);
                context.dispose();
            }
        };
        t.start();
    }//GEN-LAST:event_btnMenuEditProfileActionPerformed

    private void btnChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChatActionPerformed
        openChatScreen();
    }//GEN-LAST:event_btnChatActionPerformed

    private void loadUsersList(int taskID, boolean done) {
        if (currentUser.getClassID() == 0) {    //if classID ==0 means that the teacher is not in a class - therefore cannot see other users that are in the same default classID == 0
            return;
        }
        if (taskID == -1) {     //show all users in group - initial load of all users in group
            users.clear();
            for (User u : currentUser.getAllUsers(currentUser.getClassID())) {
                users.add(u.toString());
            }
            usersListModel.removeAllElements();
            for (String s : users) {
                usersListModel.addElement(s);
            }
            usersList.setModel(usersListModel);
        } else {    //show specific users in group - according to who finished/didn't finish a task
            users.clear();
            for (User u : currentUser.getAllUsers(currentUser.getClassID())) {
                if (u instanceof TeacherUser) //don't show Teachers
                {
                    continue;
                }
                if (done && u.getDBDoneTasks().contains(taskID) && currentUser.getClassID() == u.getClassID()) {      //show user only if done task
                    users.add(u.toString());
                } else if (!done && !u.getDBDoneTasks().contains(taskID) && currentUser.getClassID() == u.getClassID()) {    //show user only if not-done task
                    users.add(u.toString());
                }
            }
            usersListModel.removeAllElements();
            for (String s : users) {
                usersListModel.addElement(s);
            }
            usersList.setModel(usersListModel);

        }
    }

    private void refreshSolutions() {
        localSolutions = currentUser.getSolutions();
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

    private void setProvideSolutionBtn(int taskID) {
        if (!solutionsList.isSelectionEmpty()) {    //if solution is selected
            btnProvideSolution.setVisible(true);
            btnProvideSolution.setEnabled(true);
            for (Task t : currentUser.getLocalTasks()) {
                if (t.getTaskID() == taskID) {
                    for (Solution s : t.getSolutions()) {
                        if (s.getUserEmail().equals(currentUser.getEmail()) && s.getUserEmail().equals(parseSolutionEmail())) {      //user solution
                            btnProvideSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnProvideSolutionEdit"));
                            btnProvideSolution.setVisible(true);
                            btnProvideSolution.setEnabled(true);
                            return;
                        } else {                                                                                                                                //NOT user solution
                            btnProvideSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnProvideSolutionProvide"));
                            btnProvideSolution.setVisible(false);
                            btnProvideSolution.setEnabled(true);

                        }

                    }
                }
            }
        } else {                                              //if task is selected
            btnProvideSolution.setVisible(true);
            btnProvideSolution.setEnabled(true);
            btnProvideSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnProvideSolutionProvide"));
            for (Task t : currentUser.getLocalTasks()) {
                if (t.getTaskID() == taskID) {
                    for (Solution s : t.getSolutions()) {
                        if (s.getUserEmail().equals(currentUser.getEmail())) {                                                             //has user solution
                            btnProvideSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnProvideSolutionEdit"));
                            btnProvideSolution.setVisible(false);
                            btnProvideSolution.setEnabled(false);
                            return;

                        } else {                                                                                                                 //Doesn't have user solution
                            btnProvideSolution.setEnabled(true);
                        }

                    }
                }
            }
        }
    }

    private void setApproveSolutionBtn(int taskID) {
        for (Task t : currentUser.getLocalTasks()) {
            if (t.getTaskID() == taskID) {
                for (Solution s : t.getSolutions()) {
                    if (s.getUserEmail().equals(parseSolutionEmail())) {
                        if (s.isApproved()) {
                            //  btnSuggestSolution.setEnabled(false);
                            btnApproveSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnApproveDisapprove"));
                            btnApproveSolution.setBackground(voteRed);

                        } else {
                            //  btnSuggestSolution.setEnabled(true);
                            btnApproveSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnApproveApprove"));
                            btnApproveSolution.setBackground(voteBlue);
                        }
                        break;
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

    private String parseSolutionEmail() {
        if (solutionsList.getSelectedValue() != null) {
            return solutionsList.getSelectedValue().split(": ")[1];
        }
        return null;
    }

    private void updateCaptions() {

        setTitle(LocalizationUtil.localizedResourceBundle.getString("teacherMenuKey"));
        lblWelcome.setText(LocalizationUtil.localizedResourceBundle.getString("lblWelcome") + currentUser.getUsername());
        btnAddTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnAddTask"));
        btnEditTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnEditTask"));
        btnDeleteTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnDeleteTask"));
        btnChat.setText(LocalizationUtil.localizedResourceBundle.getString("btnChat"));
        menuMenu.setText(LocalizationUtil.localizedResourceBundle.getString("menuMenu"));
        menuHelp.setText(LocalizationUtil.localizedResourceBundle.getString("menuHelp"));
        btnHelpAbout.setText(LocalizationUtil.localizedResourceBundle.getString("helpAbout"));
        btnMenuEditProfile.setText(LocalizationUtil.localizedResourceBundle.getString("btnEditProfile"));
        btnMenuExit.setText(LocalizationUtil.localizedResourceBundle.getString("btnExit"));
        btnMenuLogout.setText(LocalizationUtil.localizedResourceBundle.getString("btnLogout"));
        btnApproveSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnApproveApprove"));
        btnProvideSolution.setText(LocalizationUtil.localizedResourceBundle.getString("btnProvideSolutionProvide"));
        btnShowUsersDoneTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnShowUsersFinished"));
        btnShowUsersNotDoneTask.setText(LocalizationUtil.localizedResourceBundle.getString("btnShowUsersNotFinished"));
        internalTasksTab.setTitle(LocalizationUtil.localizedResourceBundle.getString("tabTasks"));
        internalUsersTab.setTitle(LocalizationUtil.localizedResourceBundle.getString("tabUsers"));
        jTabbedPane1.setTitleAt(0, LocalizationUtil.localizedResourceBundle.getString("tabTasks"));
        jTabbedPane1.setTitleAt(1, LocalizationUtil.localizedResourceBundle.getString("tabUsers"));
        tasksTable.getColumnModel().getColumn(0).setHeaderValue(LocalizationUtil.localizedResourceBundle.getString("lblTaskTableC1"));
        tasksTable.getColumnModel().getColumn(1).setHeaderValue(LocalizationUtil.localizedResourceBundle.getString("lblTaskTableC2"));
        tasksTable.getColumnModel().getColumn(2).setHeaderValue(LocalizationUtil.localizedResourceBundle.getString("lblTaskTableC3"));

    }

    private void setTasksTable() {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

        //clear all rows
        for (int i = 0; i < tasksTable.getRowCount(); i++) {
            ((DefaultTableModel) tasksTable.getModel()).removeRow(i);
        }

        DefaultTableModel dtm = (DefaultTableModel) tasksTable.getModel();

        int taskIndex = 0;
        //set amount of rows
        dtm.setRowCount(currentUser.getLocalTasks().size());
        //fill table rows
        for (Task t : currentUser.getLocalTasks()) {
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    tasksTable.setValueAt(t.getTaskID(), taskIndex, i);
                } else if (i == 1) {
                    tasksTable.setValueAt(t.getTaskName(), taskIndex, i);
                } else if (i == 2) {
                    tasksTable.setValueAt(formater.format(t.getDeadline()), taskIndex, i);
                }
            }
            taskIndex++;
        }

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

                btnApproveSolution.setVisible(false);
                btnProvideSolution.setVisible(false);

                //Load Solutions
                loadSolutions(taskID);
                //Set Suggest Solution Button
                setProvideSolutionBtn(taskID);

            }

        });

        TasksTableRenderer myRenderer = new TasksTableRenderer();
        tasksTable.setDefaultRenderer(Object.class, myRenderer);
    }

    private void setComponentsAvailable(boolean available) {
        TeacherUserMenuPBar.setVisible(!available);
        btnMenuLogout.setEnabled(available);
        tasksTable.setEnabled(available);
        btnAddTask.setEnabled(available);
        btnEditTask.setEnabled(available);
        btnDeleteTask.setEnabled(available);
        btnShowUsersDoneTask.setEnabled(available);
        btnShowUsersNotDoneTask.setEnabled(available);
        btnLanguage.setEnabled(available);
        btnHelpAbout.setEnabled(available);
        btnMenuEditProfile.setEnabled(available);
    }

    private void openChatScreen() {
        Thread t = new Thread() {
            public void run() {
                try {
                    ChatScreen simpleToAdminChat = new ChatScreen(currentUser);
                    // ChatClient.main(currentUser);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        t.start();

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
            java.util.logging.Logger.getLogger(TeacherUserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherUserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherUserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherUserMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new TeacherUserMenu(email, password).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar TeacherUserMenuPBar;
    private javax.swing.JButton btnAddTask;
    private javax.swing.JButton btnApproveSolution;
    private javax.swing.JButton btnChat;
    private javax.swing.JButton btnDeleteTask;
    private javax.swing.JButton btnEditTask;
    private javax.swing.JMenuItem btnHelpAbout;
    private javax.swing.JButton btnLanguage;
    private javax.swing.JMenuItem btnMenuEditProfile;
    private javax.swing.JMenuItem btnMenuExit;
    private javax.swing.JMenuItem btnMenuLogout;
    private javax.swing.JButton btnProvideSolution;
    private javax.swing.JButton btnShowUsersDoneTask;
    private javax.swing.JButton btnShowUsersNotDoneTask;
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
                btnProvideSolution.setEnabled(false);

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
                setProvideSolutionBtn(taskID);

                btnApproveSolution.setVisible(true);
                setApproveSolutionBtn(taskID);

                if (solutionEmail.equals(currentUser.getEmail())) {
                    btnApproveSolution.setEnabled(false);
                    return;
                }

                btnApproveSolution.setEnabled(true);

            }
        }
    }

}
