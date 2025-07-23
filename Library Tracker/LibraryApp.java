import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryApp extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private Library library = new Library();

  
    private JPanel menuPanel;
    private JPanel studentPanel;
    private JPanel adminPanel;

    
    private JTextArea studentBookList = new JTextArea(15, 40);
    private JTextArea adminBookList = new JTextArea(15, 40);

    public LibraryApp() {
        setTitle("📚 Library Tracker App");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        createMenuPanel();
        createStudentPanel();
        createAdminPanel();

        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(studentPanel, "STUDENT");
        mainPanel.add(adminPanel, "ADMIN");

        add(mainPanel);
        cardLayout.show(mainPanel, "MENU"); 
    }

    private void createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel titleLabel = new JLabel("Welcome to the Library", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton studentModeBtn = new JButton("Student Mode");
        studentModeBtn.addActionListener(e -> {
            updateBookLists();
            cardLayout.show(mainPanel, "STUDENT");
        });

        JButton adminModeBtn = new JButton("Admin Mode");
        adminModeBtn.addActionListener(e -> {
            updateBookLists();
            cardLayout.show(mainPanel, "ADMIN");
        });

        menuPanel.add(titleLabel);
        menuPanel.add(studentModeBtn);
        menuPanel.add(adminModeBtn);
    }

    private void createStudentPanel() {
        studentPanel = new JPanel(new BorderLayout(10, 10));
        studentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        studentBookList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(studentBookList);
        studentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JTextField bookIdField = new JTextField(10);
        JButton borrowBtn = new JButton("Borrow Book");
        JButton returnBtn = new JButton("Return Book");
        JButton searchBtn = new JButton("Search by Name");
        JButton backBtn = new JButton("Back to Menu");

        actionPanel.add(new JLabel("Book ID/Name:"));
        actionPanel.add(bookIdField);
        actionPanel.add(borrowBtn);
        actionPanel.add(returnBtn);
        actionPanel.add(searchBtn);
        actionPanel.add(backBtn);
        
        studentPanel.add(new JLabel("Available Books", SwingConstants.CENTER), BorderLayout.NORTH);
        studentPanel.add(actionPanel, BorderLayout.SOUTH);

        borrowBtn.addActionListener(e -> handleBookAction(bookIdField.getText(), "borrow"));
        returnBtn.addActionListener(e -> handleBookAction(bookIdField.getText(), "return"));
        searchBtn.addActionListener(e -> handleBookSearch(bookIdField.getText()));
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));
    }

    private void createAdminPanel() {
        adminPanel = new JPanel(new BorderLayout(10, 10));
        adminPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        adminBookList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(adminBookList);
        adminPanel.add(scrollPane, BorderLayout.CENTER);
        adminPanel.add(new JLabel("Library Management (Admin)", SwingConstants.CENTER), BorderLayout.NORTH);

        JPanel addPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        addPanel.setBorder(BorderFactory.createTitledBorder("Add New Book"));
        
        JTextField newIdField = new JTextField();
        JTextField newTitleField = new JTextField();
        JTextField newAuthorField = new JTextField();
        JButton addBtn = new JButton("Add Book");

        addPanel.add(new JLabel("ID:"));
        addPanel.add(newIdField);
        addPanel.add(new JLabel("Title:"));
        addPanel.add(newTitleField);
        addPanel.add(new JLabel("Author:"));
        addPanel.add(newAuthorField);
        addPanel.add(new JLabel()); 
        addPanel.add(addBtn);

        JPanel bottomContainer = new JPanel(new BorderLayout());
        bottomContainer.add(addPanel, BorderLayout.CENTER);
        
        JButton backBtn = new JButton("Back to Menu");
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "MENU"));
        JPanel backBtnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backBtnPanel.add(backBtn);
        
        bottomContainer.add(backBtnPanel, BorderLayout.SOUTH);

        adminPanel.add(bottomContainer, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> {
            String id = newIdField.getText();
            String title = newTitleField.getText();
            String author = newAuthorField.getText();

            if (id.isEmpty() || title.isEmpty() || author.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String result = library.addBook(id, title, author);
            JOptionPane.showMessageDialog(this, result);
            updateBookLists();

            newIdField.setText("");
            newTitleField.setText("");
            newAuthorField.setText("");
        });
    }


    private void updateBookLists() {
        String allBooks = library.getAllBooksAsString();
        studentBookList.setText(allBooks);
        adminBookList.setText(allBooks);
    }

    private void handleBookAction(String bookId, String action) {
        if (bookId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Book ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String result = "";
        if ("borrow".equals(action)) {
            result = library.borrowBook(bookId);
        } else if ("return".equals(action)) {
            result = library.returnBook(bookId);
        }
        JOptionPane.showMessageDialog(this, result);
        updateBookLists();
    }
    
    private void handleBookSearch(String bookName) {
         if (bookName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a book name to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Book foundBook = library.findBookByName(bookName);
        if (foundBook != null) {
            JOptionPane.showMessageDialog(this, "Book Found:\n" + foundBook.toString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No book found with that name.", "Search Result", JOptionPane.WARNING_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryApp app = new LibraryApp();
            app.setVisible(true);
        });
    }
}