import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Frame extends JFrame{
    JPanel mainPnl, titlePnl, displayPnl, searchPnl, inputPnl;
    JLabel titleLbl, searchLbl;
    JButton searchStrBtn, getFileBtn, quitBtn;
    JTextArea getSearch;
    static JTextArea displayTA, displayFile;
    JScrollPane scroller, scroller2;

    String searchedString;
    Boolean fileChosen = false;

    public Frame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());

        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createFrameComponents();
        add(mainPnl);
        setVisible(true);
    }

    private void createFrameComponents(){
        createTitlePnl();
        createInputPnl();
        createDisplayPnl();
        createSearchPnl();
    }

    private void createTitlePnl() {
        titlePnl = new JPanel();
        titlePnl.setLayout(new GridLayout(2, 1));
        titleLbl = new JLabel("Search a file!", JLabel.CENTER);
        titleLbl.setFont(new Font("Arial",Font.BOLD,30));
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titlePnl.add(titleLbl);

        mainPnl.add(titlePnl, BorderLayout.NORTH);
    }

    private void createInputPnl() {
        inputPnl = new JPanel();
        inputPnl.setLayout(new GridLayout(1, 1));
        displayFile = new JTextArea(10, 80);
        displayFile.setFont(new Font("Courier", Font.PLAIN, 12));
        displayFile.setEditable(false);
        scroller = new JScrollPane(displayFile);
        inputPnl.add(scroller);

        mainPnl.add(inputPnl, BorderLayout.WEST);
    }

    private void createDisplayPnl() {
        displayPnl = new JPanel();
        displayPnl.setLayout(new GridLayout(1, 1));
        displayTA = new JTextArea(10, 80);
        displayTA.setFont(new Font("Courier", Font.PLAIN, 12));
        displayTA.setEditable(false);
        scroller2 = new JScrollPane(displayTA);
        displayPnl.add(scroller2);

        mainPnl.add(displayPnl, BorderLayout.EAST);
    }

    private void createSearchPnl() {
        searchPnl = new JPanel();
        searchPnl.setLayout(new GridLayout(1, 5));
        searchLbl = new JLabel("Search:");
        getSearch = new JTextArea(3, 30);

        getFileBtn = new JButton("Choose File!");
        quitBtn = new JButton("Quit!");
        searchStrBtn = new JButton("Search File!");

        searchStrBtn.addActionListener((ActionEvent ae) ->{
            displayTA.setText("");
            if (fileChosen) {
                searchedString = getSearch.getText();
                getSearch.setText("");
                displayTA.append(FilePicker.getSearched(searchedString));
            }else{
                displayTA.append("No file chosen. Please Choose file");
            }
        });

        getFileBtn.addActionListener((ActionEvent ae) ->{
            displayFile.append(FilePicker.getFile()+"\n");
            fileChosen = true;
        });

        quitBtn.addActionListener((ActionEvent ae) -> {
            FilePicker.getFileClose();
            System.exit(0);
        });

        searchPnl.add(searchLbl);
        searchPnl.add(getSearch);
        searchPnl.add(searchStrBtn);
        searchPnl.add(getFileBtn);
        searchPnl.add(quitBtn);

        mainPnl.add(searchPnl, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        new Frame();
    }
}
