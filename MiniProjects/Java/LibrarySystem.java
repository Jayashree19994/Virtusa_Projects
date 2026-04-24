import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Book {
    int id;
    String title;
    String author;
    int copies;

    Book(int id, String title, String author, int copies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.copies = copies;
    }
}

class User {
    String id;
    String name;

    User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Issue {
    int bookId;
    String userId;
    int days;

    Issue(int bookId, String userId, int days) {
        this.bookId = bookId;
        this.userId = userId;
        this.days = days;
    }
}

public class LibrarySystem {

    static ArrayList<Book> books = new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Issue> issues = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    static String ADMIN_USER = "admin";
    static String ADMIN_PASS = "1234";

    
    static void loadDefaultData() {
        books.add(new Book(101, "Java Basics", "James Gosling", 3));
        books.add(new Book(102, "Python Guide", "Guido van Rossum", 2));

        users.add(new User("Jaya12", "Alice"));

        System.out.println("Default data loaded!");
    }

    static boolean isValidUser(String userId) {
        for (User u : users) {
            if (u.id.equalsIgnoreCase(userId)) return true;
        }
        return false;
    }

    static void addBook() {
        System.out.print("Book id: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Title: ");
        String title = sc.nextLine();

        System.out.print("Author: ");
        String author = sc.nextLine();

        System.out.print("Copies: ");
        int copies = sc.nextInt();

        books.add(new Book(id, title, author, copies));
        System.out.println("Book added!");
    }

    static void registerUser() {
        System.out.print("User id: ");
        String id = sc.next();

        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();

        users.add(new User(id, name));
        System.out.println("User registered!");
    }

    static void issueBook(String userId) {

        System.out.print("Enter book id: ");
        int bookId = sc.nextInt();

        for (Book b : books) {
            if (b.id == bookId && b.copies > 0) {
                b.copies--;

                issues.add(new Issue(bookId, userId, 0)); 
               

                System.out.println("Book issued for 7 days only!");
                return;
            }
        }

        System.out.println("Book not available!");
    }

    static void returnBook(String userId) {

        System.out.print("Enter book id: ");
        int bookId = sc.nextInt();

        System.out.print("Enter days used (simulate return days): ");
        int daysUsed = sc.nextInt();

        Iterator<Issue> it = issues.iterator();

        while (it.hasNext()) {
            Issue i = it.next();

            if (i.bookId == bookId && i.userId.equalsIgnoreCase(userId)) {

                for (Book b : books) {
                    if (b.id == bookId) {
                        b.copies++;
                    }
                }

                if (daysUsed > 7) {
                    int fine = (daysUsed - 7) * 10;
                    System.out.println("Fine: ₹" + fine);
                } else {
                    System.out.println("Returned within 7 days. No fine!");
                }

                it.remove();
                return;
            }
        }

        System.out.println("No record found!");
    }
    static void searchBook() {

        sc.nextLine();
        System.out.print("Search: ");
        String key = sc.nextLine().toLowerCase();

        boolean found = false;

        for (Book b : books) {
            if (b.title.toLowerCase().contains(key) ||
                b.author.toLowerCase().contains(key)) {

                System.out.println(b.id + " | " + b.title + " | " + b.author +
                        " | Copies: " + b.copies);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found!");
        }
    }

    static void adminMenu() {

        while (true) {
            System.out.println("\nADMIN MENU");
            System.out.println("1.Add Book");
            System.out.println("2.Register User");
            System.out.println("3.Search Book");
            System.out.println("4.Back");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: addBook(); break;
                case 2: registerUser(); break;
                case 3: searchBook(); break;
                case 4: return;
                default: System.out.println("Invalid");
            }
        }
    }

    static void userMenu() {

        System.out.print("\nEnter your user ID: ");
        String userId = sc.next();

        if (!isValidUser(userId)) {
            System.out.println("User not registered!");
            return;
        }

        while (true) {
            System.out.println("\nUSER MENU");
            System.out.println("1.Issue Book");
            System.out.println("2.Return Book");
            System.out.println("3.Search Book");
            System.out.println("4.Logout");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: issueBook(userId); break;
                case 2: returnBook(userId); break;
                case 3: searchBook(); break;
                case 4: return;
                default: System.out.println("Invalid");
            }
        }
    }

    public static void main(String[] args) {

        loadDefaultData();

        while (true) {
            System.out.println("\n1.Admin Login");
            System.out.println("2.User");
            System.out.println("3.Exit");

            int ch = sc.nextInt();

            if (ch == 1) {

                sc.nextLine();
                System.out.print("Username: ");
                String u = sc.nextLine();

                System.out.print("Password: ");
                String p = sc.nextLine();

                if (u.equals(ADMIN_USER) && p.equals(ADMIN_PASS)) {
                    adminMenu();
                } else {
                    System.out.println("Wrong credentials!");
                }

            } else if (ch == 2) {
                userMenu();
            } else {
                break;
            }
        }
    }
}
