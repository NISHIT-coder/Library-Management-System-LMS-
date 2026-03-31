public class Main {

    public static void main(String[] args) {

        System.out.println("===== LIBRARY SYSTEM =====");

        // Add Books
        AddBook.addBook("Java Programming", "James Gosling", 10);
        AddBook.addBook("DBMS", "Korth", 5);

        // Add Members
        AddMember.addMember("Nishit", "9876543210");
        AddMember.addMember("Jai", "9123456780");

        // View Data
        ViewBooks.viewBooks();
        ViewMembers.viewMembers();

        // Issue Book
        IssueBook.issue(1, 1);

        // Return Book
        ReturnBook.returnBook(1);

        System.out.println("\n===== DONE =====");
    }
}