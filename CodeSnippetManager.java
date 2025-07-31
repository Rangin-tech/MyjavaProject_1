import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class CodeSnippetManager extends JFrame {
    private ArrayList<Snippet> snippets = new ArrayList<>();
    private JTextField titleField, langField, tagField;
    private JTextArea codeArea, outputArea;

    public CodeSnippetManager() {
        setTitle("Code Snippet Manager");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Title:"));
        titleField = new JTextField(20); add(titleField);
        add(new JLabel("Language:"));
        langField = new JTextField(20); add(langField);
        add(new JLabel("Tags:"));
        tagField = new JTextField(20); add(tagField);

        add(new JLabel("Code:"));
        codeArea = new JTextArea(10, 40); add(new JScrollPane(codeArea));
        outputArea = new JTextArea(10, 40); outputArea.setEditable(false); add(new JScrollPane(outputArea));

        JButton saveBtn = new JButton("Save Snippet");
        JButton viewBtn = new JButton("View Snippets");
        add(saveBtn); add(viewBtn);

        saveBtn.addActionListener(e -> saveSnippet());
        viewBtn.addActionListener(e -> viewSnippets());
    }

    private void saveSnippet() {
        snippets.add(new Snippet(
                titleField.getText(),
                langField.getText(),
                tagField.getText(),
                codeArea.getText()
        ));
        saveToFile(); outputArea.setText("Snippet saved!");
    }

    private void viewSnippets() {
        loadFromFile();
        outputArea.setText("");
        for (Snippet s : snippets) {
            outputArea.append(s.toString() + "\n-------------------\n");
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("snippets.ser"))) {
            oos.writeObject(snippets);
        } catch (IOException e) {
            outputArea.setText("Error saving.");
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("snippets.ser"))) {
            snippets = (ArrayList<Snippet>) ois.readObject();
        } catch (Exception e) {
            outputArea.setText("No snippets found.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CodeSnippetManager().setVisible(true));
    }
}
