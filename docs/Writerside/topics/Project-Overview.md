# Project Overview

## Project Name: Maids.cc Library Management System

## Description
The Maids.cc Library Management System is a robust API built using Spring Boot. 
It facilitates efficient management of books, patrons, and borrowing records in a library setting. 
Librarians can easily handle book additions, updates, and removals, as well as patron management and borrowing transactions.

## Entities
Book: Represents information about each book in the library, including attributes such as ID, title, author, publication year, ISBN, etc.
Patron: Contains details about library patrons, including ID, name, contact information, etc.
Borrowing Record: Tracks the association between books and patrons, including borrowing and return dates.

## API Endpoints

### Book Management Endpoints:
1. GET /api/books: Retrieve a list of all books.
2. GET /api/books/{id}: Retrieve details of a specific book by ID.
3. POST /api/books: Add a new book to the library.
4. PUT /api/books/{id}: Update an existing book's information.
5. DELETE /api/books/{id}: Remove a book from the library.

### Patron Management Endpoints:
1. GET /api/patrons: Retrieve a list of all patrons.
2. GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
3. POST /api/patrons: Add a new patron to the system.
4. PUT /api/patrons/{id}: Update an existing patron's information.
5. DELETE /api/patrons/{id}: Remove a patron from the system.

### Borrowing Endpoints:
1. POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book.
2. PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.