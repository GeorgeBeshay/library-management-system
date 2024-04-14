# How To Use

To interact with the API endpoints, you can use tools like Postman, cURL, or any HTTP client library in your preferred programming language. Here's how you can use each endpoint:

**Book Management Endpoints**:

Send GET requests to `/api/books` to retrieve a list of all books or `/api/books/{id}` to retrieve details of a specific book by its ID.

Use POST requests to `/api/books` to add a new book to the library. Provide the book details in the request body.

Use PUT requests to `/api/books/{id}` to update an existing book's information. Provide the book ID in the URL and the updated book details in the request body.

Send DELETE requests to `/api/books/{id}` to remove a book from the library by its ID.

**Patron Management Endpoints:**

Similar to book management, use GET, POST, PUT, and DELETE requests to interact with patron management endpoints. Replace `/api/books` with `/api/patrons` in the endpoint URLs.

**Borrowing Endpoints:**

To allow a patron to borrow a book, send a POST request to `/api/borrow/{bookId}/patron/{patronId}`. 
Replace `{bookId}` and `{patronId}` with the IDs of the book and patron, respectively.

To record the return of a borrowed book, send a PUT request to `/api/return/{bookId}/patron/{patronId}` with the book 
and patron IDs in the URL.