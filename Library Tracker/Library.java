import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Library {
    private Map<String, Book> books = new HashMap<>();

    public Library() {
        addBook("B001", "The Great Gatsby", "F. Scott Fitzgerald");
        addBook("B002", "To Kill a Mockingbird", "Harper Lee");
        addBook("B003", "1984", "George Orwell");
        addBook("B004", "Pride and Prejudice", "Jane Austen");
    }

    public String addBook(String id, String title, String author) {
        if (books.containsKey(id)) {
            return "Error: Book with this ID already exists.";
        }
        books.put(id, new Book(id, title, author));
        return "Success: Book added to the library.";
    }

    public String borrowBook(String id) {
        Book book = findBookById(id);
        if (book == null) {
            return "Error: Book not found.";
        }
        if (!book.isAvailable()) {
            return "Info: This book is already borrowed.";
        }
        book.setAvailable(false);
        return "Success: You have borrowed '" + book.getTitle() + "'.";
    }

    public String returnBook(String id) {
        Book book = findBookById(id);
        if (book == null) {
            return "Error: Book not found.";
        }
        if (book.isAvailable()) {
            return "Info: This book is already in the library.";
        }
        book.setAvailable(true);
        return "Success: You have returned '" + book.getTitle() + "'.";
    }
    
    public Book findBookById(String id) {
        return books.get(id);
    }
    
    public Book findBookByName(String name) {
        for (Book book : books.values()) {
            if (book.getTitle().equalsIgnoreCase(name)) {
                return book;
            }
        }
        return null; 
    }

    public String getAllBooksAsString() {
        if (books.isEmpty()) {
            return "The library is empty.";
        }
       
        return books.values().stream()
                .map(Book::toString)
                .collect(Collectors.joining("\n"));
    }
}