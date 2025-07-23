~ Library Tracker App
A simple desktop application for managing a small library, built with Java and the Swing GUI toolkit. This project demonstrates the use of core Java concepts, including the HashMap data structure for efficient book management, and provides a clean user interface for both students and administrators.

=> Features
The application operates in two distinct modes:

-> Student Mode
View All Books: See a real-time list of all books in the library and their availability status.

Borrow a Book: Borrow a book by entering its unique ID.

Return a Book: Return a previously borrowed book using its ID.

Search for a Book: Find a book by its title.

-> Admin Mode
Includes all features available in Student Mode.

Add a New Book: Add new books to the library collection by providing an ID, title, and author.

=> Technologies Used
Backend: Java

Frontend: Java Swing (for the graphical user interface)

Core Data Structure: HashMap for storing and looking up book data.

=> Getting Started
Follow these instructions to get a copy of the project up and running on your local machine.

Prerequisites
You must have the Java Development Kit (JDK) version 8 or later installed on your system.

Installation & Running
Download the Files

Download the three source files:

Book.java

Library.java

LibraryApp.java

Place all three files in the same directory.

Compile the Code

Open a terminal or command prompt.

Navigate to the directory containing the files.

Compile the source code using the javac command:

Bash

javac LibraryApp.java
Run the Application

In the same terminal window, run the application using the java command:

Bash

java LibraryApp
The application window should now appear.

Alternatively, using an IDE (Eclipse, IntelliJ, VS Code):

Create a new Java project.

Add the three .java files to the project's src folder.

Right-click on LibraryApp.java and select "Run" or "Run as Java Application".

=> File Structure
The project is organized into three simple classes:

Book.java: The data model class that represents a single book with properties like ID, title, author, and availability status.

Library.java: The backend logic class that manages the collection of books using a HashMap. It handles all operations like adding, borrowing, and returning books.

LibraryApp.java: The main class containing the entire Swing GUI, event handling, and the main method to launch the application.