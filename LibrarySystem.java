import java.util.*;

// Book class banayi hai jisme book ka title, author aur issued status store hota hai
class Book {
    private String title;
    private String author;
    private boolean isIssued;
    private User issuedTo; // ye user ko store karega jise book di gayi hai

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = null;
    }

    String getTitle() {
        return title;
    }

    boolean isIssued() {
        return isIssued;
    }

    User getIssuedTo() {
        return issuedTo;
    }

    void issueTo(User user) {
        isIssued = true;
        issuedTo = user;
    }

    void returnBook() {
        isIssued = false;
        issuedTo = null;
    }

    public String toString() {
        if (isIssued) {
            return title + " by " + author + " (Issued to " + issuedTo.getName() + ")";
        } else {
            return title + " by " + author + " (Available)";
        }
    }
}

// User class banayi hai jisme user ka naam aur id store hota hai
class User {
    private String name;
    private int id;

    User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }

    public String toString() {
        return "User: " + name + " (ID: " + id + ")";
    }
}

// Library class jisme books list hai aur methods hai books ko manage karne ke liye
class Library {
    private List<Book> books;

    Library() {
        books = new ArrayList<>();
    }

    void addBook(Book book) {
        books.add(book);
    }

    void showBooks() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    void issueBook(String title, User user) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isIssued()) {
                    book.issueTo(user);
                    System.out.println("Book issued to " + user.getName());
                } else {
                    System.out.println("Book already issued to " + book.getIssuedTo().getName());
                }
                return;
            }
        }
        System.out.println("Book not found in library.");
    }

    void returnBook(String title, User user) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isIssued()) {
                    if (book.getIssuedTo().getId() == user.getId()) {
                        book.returnBook();
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("You can't return this book. It was issued to someone else.");
                    }
                } else {
                    System.out.println("This book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book not found in library.");
    }
}

// Main class jisme menu driven program banaya gaya hai
public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Kuch starting books add kar di
        library.addBook(new Book("Java Basics", "James Gosling"));
        library.addBook(new Book("OOP in Depth", "Bjarne Stroustrup"));
        library.addBook(new Book("Data Structures", "Mark Allen Weiss"));

        int choice;

        do {
            System.out.println("\n====== Library Menu ======");
            System.out.println("1. Show all books");
            System.out.println("2. Issue a book");
            System.out.println("3. Return a book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // buffer clear karne ke liye

            switch (choice) {
                case 1:
                    library.showBooks();
                    break;

                case 2:
                    System.out.print("Enter your name: ");
                    String name1 = sc.nextLine();
                    System.out.print("Enter your ID: ");
                    int id1 = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter book title to issue: ");
                    String title1 = sc.nextLine();

                    User user1 = new User(name1, id1);
                    library.issueBook(title1, user1);
                    break;

                case 3:
                    System.out.print("Enter your name: ");
                    String name2 = sc.nextLine();
                    System.out.print("Enter your ID: ");
                    int id2 = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter book title to return: ");
                    String title2 = sc.nextLine();

                    User user2 = new User(name2, id2);
                    library.returnBook(title2, user2);
                    break;

                case 0:
                    System.out.println("Thanks for using the Library System!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
