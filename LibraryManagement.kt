import java.util.*

data class Book(
    val id: Int,
    val title: String,
    var isIssued: Boolean = false
)

data class Issue(
    val bookTitle: String,
    val studentName: String
)

fun main() {
    val scanner = Scanner(System.`in`)

    val books = mutableListOf<Book>()
    val issuedBooks = mutableListOf<Issue>()

    while (true) {
        println("\n===== LIBRARY MANAGEMENT SYSTEM =====")
        println("1. Add Book")
        println("2. View Books")
        println("3. Issue Book")
        println("4. Return Book")
        println("5. View Issued Books")
        println("6. Exit")
        print("Enter your choice: ")

        when (scanner.nextInt()) {

            1 -> {
                print("Enter Book ID: ")
                val id = scanner.nextInt()

                scanner.nextLine()
                print("Enter Book Title: ")
                val title = scanner.nextLine()

                books.add(Book(id, title))
                println("Book added successfully!")
            }

            2 -> {
                println("\n--- Book List ---")
                if (books.isEmpty()) {
                    println("No books available.")
                } else {
                    for (b in books) {
                        val status = if (b.isIssued) "Issued" else "Available"
                        println("ID: ${b.id}, Title: ${b.title}, Status: $status")
                    }
                }
            }

            3 -> {
                scanner.nextLine()
                print("Enter Student Name: ")
                val student = scanner.nextLine()

                print("Enter Book ID to issue: ")
                val id = scanner.nextInt()

                val book = books.find { it.id == id }

                if (book == null) {
                    println("Book not found!")
                } else if (book.isIssued) {
                    println("Book already issued!")
                } else {
                    book.isIssued = true
                    issuedBooks.add(Issue(book.title, student))
                    println("Book issued successfully!")
                }
            }

            4 -> {
                print("Enter Book ID to return: ")
                val id = scanner.nextInt()

                val book = books.find { it.id == id }

                if (book == null) {
                    println("Book not found!")
                } else if (!book.isIssued) {
                    println("Book is not issued!")
                } else {
                    book.isIssued = false
                    issuedBooks.removeIf { it.bookTitle == book.title }
                    println("Book returned successfully!")
                }
            }

            5 -> {
                println("\n--- Issued Books ---")
                if (issuedBooks.isEmpty()) {
                    println("No books issued.")
                } else {
                    for (i in issuedBooks) {
                        println("Book: ${i.bookTitle}, Issued to: ${i.studentName}")
                    }
                }
            }

            6 -> {
                println("Thank you! Exiting...")
                break
            }

            else -> println("Invalid choice! Try again.")
        }
    }
}