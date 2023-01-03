package jhu.softwaresleuthers.clueless;

import jhu.softwaresleuthers.clueless.models.Character;
import jhu.softwaresleuthers.clueless.messaging.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.CollationElementIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class GUI extends JFrame {

    private static Client client;
    private static final List<String> CHARACTERS = Character.CharacterName.names();
    private static final List<String> panelLabels = List.of(
            "", "", "", "", "Miss Scarlet", "", "",
            "", "Study", "Hallway 1", "Hall", "Hallway 2", "Lounge", "",
            "Professor Plum", "Hallway 3", "", "Hallway 4", "", "Hallway 5", "Colonel Mustard",
            "", "Library", "Hallway 6", "Billiard Room", "Hallway 7", "Dining Room", "",
            "Mrs. Peacock", "Hallway 8", "", "Hallway 9", "", "Hallway 10", "",
            "", "Conservatory", "Hallway 11", "Ballroom", "Hallway 12", "Kitchen", "",
            "", "", "Mr. Green", "", "Mrs. White", "", ""
    );
    private static final Map<String, Color> playerColors = Map.of(
            "Miss Scarlet", Color.red,
            "Professor Plum", new Color(114, 5, 240),
            "Colonel Mustard", Color.orange,
            "Mrs. Peacock", Color.blue,
            "Mr. Green", Color.green,
            "Mrs. White", Color.white
    );

    private JPanel centerPanel = new JPanel();
    private HashMap<String, JPanel> boardPanels = new HashMap<>();
    private HashMap<String, PlayerPanel> players = new HashMap<>();
    private JTextArea prompt;
    private JFormattedTextField userInput;
    private JButton submitButton = new JButton("Submit");
    private MyActionListener submitButtonListener = new MyActionListener();
    private String inputText = "";
    private JPanel eastPanel = new JPanel();
    private JRadioButton defaultTheme = new JRadioButton("Default");
    private JRadioButton theme1 = new JRadioButton("Theme 1");
    private JRadioButton theme2 = new JRadioButton("Theme 2");
    private JRadioButton theme3 = new JRadioButton("Theme 3");
    private JRadioButton theme4 = new JRadioButton("Theme 4");
    private ButtonGroup group = new ButtonGroup();

    /**
     * Creates a new instance of Main.
     */
    public GUI(Client userClient) {
        setTitle("Clue-Less");

        // Create panels
        JPanel northPanel = createNorthPanel();
        JPanel eastPanel = createEastPanel();
        JPanel themePanel = createThemePanel();
        createBoard();

        // Add panels to main frame
        add(northPanel, BorderLayout.NORTH);
        add(eastPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
        add(themePanel, BorderLayout.WEST);
        createJFrame(this);
        client = userClient;
    }

    /**
     * Create and setup a new JFrame.
     *
     * @param jf JFrame to setup
     */
    private void createJFrame(JFrame jf) {
        Runnable showFrame = new Runnable() {
            public void run() {
                jf.pack();
                jf.setLocationByPlatform(true);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setSize(1600,1000);
                jf.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(showFrame);
    }

    /**
     * Create North content panel that holds the title and subtitle.
     *
     * @return Panel containing heading information
     */
    private JPanel createNorthPanel() {
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.white);

        // Create title
        JLabel title = new JLabel("Clue-Less");

        // Add components to panel
        northPanel.add(title);
        return northPanel;
    }

    /**
     * Setup Center content panel that is responsive to user selections and inputs.
     */
    private void createBoard() {
        centerPanel.setBackground(Color.white);
        centerPanel.setPreferredSize(new Dimension(800, 800));
        centerPanel.setLayout(new GridLayout(7,7));
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        for (String panelLabel : panelLabels) {
            JPanel panel = new JPanel();
            JLabel label =  new JLabel(panelLabel);
            panel.setSize(80,80);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            panel.add(label);

            if (panelLabel.isEmpty()) {
                panel.setBackground(Color.white);
            } else if (panelLabel.contains("Hallway")) {
                panel.setBackground(new Color(255, 221, 153));
            } else if (CHARACTERS.contains(panelLabel)) {
                panel.setBackground(Color.pink);
                PlayerPanel player = new PlayerPanel(playerColors.get(panelLabel));
                player.setBoardLocation(panelLabel);
                players.put(panelLabel, player);
                panel.add(player);
            } else {
                panel.setBackground(new Color(166, 111, 0));
            }
            boardPanels.put(panelLabel, panel);
            centerPanel.add(panel);
        }

        centerPanel.repaint();
    }

    /**
     * Create East content panel that contains hike selection form.
     *
     * @return Panel containing hike selection form
     */
    private JPanel createEastPanel() {
        //JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridBagLayout());
        eastPanel.setBackground(Color.white);
        eastPanel.setPreferredSize(new Dimension(350, 50));
        eastPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Setup Grid bag constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Prompt
        prompt = createTextArea("placeholder", false, true);

        // Input area
        userInput = new JFormattedTextField();
        userInput.setBorder(BorderFactory.createLineBorder(Color.black));

        // Input submit button
        submitButton.addActionListener(submitButtonListener);

        eastPanel.add(prompt, constraints);
        eastPanel.add(userInput, constraints);
        constraints.weighty = 1.0;
        eastPanel.add(submitButton, constraints);

        return eastPanel;
    }

    public void createCardPanel(String[] cards) {
        // Setup Grid bag constraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
//        constraints.insets = new Insets(10,10,10,10);
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel cardPanel = new JPanel(new GridLayout(cards.length, 1));
        for (String card : cards) {
            cardPanel.add(new JLabel(card));
        }

        eastPanel.add(cardPanel, constraints);
        eastPanel.revalidate();
        eastPanel.repaint();
    }

    /**
     * Utility method to create a text area.
     *
     * @param text  Text to display in text area
     * @param editable  Boolean value whether the text area should be editable
     * @param lineWrap  Boolean value whether the text area should wrap lines
     * @return  New text area
     */
    private JTextArea createTextArea(String text, boolean editable, boolean lineWrap) {
        JTextArea textArea = new JTextArea(text);
        textArea.setEditable(editable);
        textArea.setLineWrap(lineWrap);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    public void setClientPrompt(String message) {
        prompt.setText(message);
    }

    public void appendClientPrompt(String message)
    {
        prompt.append("\n" + message);
    }

    public ArrayList<String> extractOptions(String message)
    {
        String[] splitString = message.split("\n");
        Pattern pattern = Pattern.compile("[1-9]");
        ArrayList<String> validOptions = new ArrayList<String>();

        for (String x : splitString)
        {
            Matcher matcher = pattern.matcher(x);
            if (matcher.find())
            {
                validOptions.add(x.substring(3, x.length()));
            }
        }
        return validOptions;
    }

    public void movePlayer(String player, String location) {
        PlayerPanel playerPanel = players.get(player);
        // Get current board location & remove player
        JPanel panel = boardPanels.get(playerPanel.getBoardLocation());
        //System.out.println(playerPanel.getBoardLocation());
        panel.remove(playerPanel);
        panel.revalidate();
        panel.repaint();

        // Get new board location & add player
        JPanel newPanel = boardPanels.get(location);
        playerPanel.setBoardLocation(location);
        newPanel.add(playerPanel);
        newPanel.revalidate();
        newPanel.repaint();
    }

    public String getInputText() {
        String input = inputText;
        inputText = "";
        return input;
    }

    public JPanel createThemePanel()
    {
        JTextArea panelLabel = createTextArea("Please select the theme you would like", false, true);
        defaultTheme.setSelected(true);
        group.add(defaultTheme);
        group.add(theme1);
        group.add(theme2);
        group.add(theme3);
        group.add(theme4);

        defaultTheme.addActionListener(submitButtonListener);
        theme1.addActionListener(submitButtonListener);
        theme2.addActionListener(submitButtonListener);
        theme3.addActionListener(submitButtonListener);
        theme4.addActionListener(submitButtonListener);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;


        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(panelLabel, constraints);
        radioPanel.add(defaultTheme);
        radioPanel.add(theme1, constraints);
        radioPanel.add(theme2, constraints);
        radioPanel.add(theme3, constraints);
        radioPanel.add(theme4, constraints);

        return radioPanel;
    }

    public void setTheme(Color color1, Color color2)
    {
        int i = 0;
        for (var panel : centerPanel.getComponents())
        {
            if (panel.getBackground() != Color.WHITE && playerColors.get(panelLabels.get(i)) == null)
            {
                if (panelLabels.get(i).contains("Hallway")) { panel.setBackground(color1); }
                else { panel.setBackground(color2); }
            }
            i++;
        }
    }

    /**
     * Inner Class implementing Action Listener.
     */
    public class MyActionListener implements ActionListener {

        /**
         * On action performed, determine the object source and react accordingly.
         * For submit query button press, submit the query with the give hike selections.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Object eventSource = e.getSource();
            if (eventSource == submitButton) {
                inputText = userInput.getText();
                userInput.setText("");
            }
            else if (eventSource == defaultTheme)
            {
                setTheme(new Color(255, 221, 153), new Color(166, 111, 0));
            }
            else if (eventSource == theme1)
            {
                setTheme(new Color(60, 246, 254), new Color(16, 137, 31));
            }
            else if (eventSource == theme2)
            {
                setTheme(new Color(202, 0, 0), new Color(82, 227, 227));
            }
            else if (eventSource == theme3)
            {
                setTheme(new Color(237, 237, 0), new Color(47, 237, 0));
            }
            else if (eventSource == theme4)
            {
                setTheme(new Color(88, 83, 238), new Color(203, 68, 68));
            }
        }
    }

    public class PlayerPanel extends JPanel {
        private Color color;
        private String boardLocation;

        public PlayerPanel(Color color) {
            super();
            this.color = color;
        }

        public String getBoardLocation() {
            return boardLocation;
        }

        public void setBoardLocation(String boardLocation) {
            this.boardLocation = boardLocation;
        }


        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(color);
            g.fillOval(0, 0, 25, 25);
        }
    }
}
