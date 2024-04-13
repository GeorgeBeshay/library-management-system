USE LibraryManagementSystemDB;
GO

INSERT INTO BOOK (Title, Author, PublicationYear, ISBN)
VALUES
  ('The Lord of the Rings', 'J.R.R. Tolkien', 1954, '9780261102694'),
  ('Pride and Prejudice', 'Jane Austen', 1813, '9780140439516'),
  ('To Kill a Mockingbird', 'Harper Lee', 1960, '9780446310727'),
  ('The Hitchhiker''s Guide to the Galaxy', 'Douglas Adams', 1979, '9780345391803'),
  ('The Catcher in the Rye', 'J.D. Salinger', 1951, '9780312907070'),
  ('Frankenstein', 'Mary Shelley', 1818, '9781847490697'),
  ('The Great Gatsby', 'F. Scott Fitzgerald', 1925, '9780747531185'),
  ('One Hundred Years of Solitude', 'Gabriel García Márquez', 1967, '9780553278876'),
  ('Don Quixote', 'Miguel de Cervantes', 1615, '9780140449922'),
  ('Crime and Punishment', 'Fyodor Dostoevsky', 1866, '9781491821371');

INSERT INTO PATRON (FirstName, LastName, Email)
VALUES
  ('John', 'Doe', 'johndoe@example.com'),
  ('Jane', 'Smith', 'janesmith@example.com'),
  ('Alice', 'Johnson', 'alicejohnson@example.com'),
  ('Michael', 'Brown', 'michaelbrown@example.com'),
  ('Emily', 'Garcia', 'emilygarcia@example.com'),
  ('David', 'Wilson', 'davidwilson@example.com'),
  ('Elizabeth', 'Taylor', 'elizabethtaylor@example.com'),
  ('Matthew', 'Davis', 'matthewdavis@example.com'),
  ('Jennifer', 'Lopez', 'jenniferlopez@example.com'),
  ('Christopher', 'Clark', 'christopherclark@example.com');

INSERT INTO BORROWING (PatronID, BookID, StartDate, EndDate)
VALUES
  (1, 3, '2024-04-13', '2024-04-27'),  -- John Doe borrows "To Kill a Mockingbird"
  (2, 5, '2024-04-13', '2024-04-27'),  -- Jane Smith borrows "The Catcher in the Rye"
  (3, 1, '2024-04-13', '2024-04-27'),  -- Alice Johnson borrows "The Lord of the Rings"
  (4, 7, '2024-04-13', '2024-04-27'),  -- Michael Brown borrows "The Great Gatsby"
  (5, 9, '2024-04-13', '2024-04-27');   -- Emily Garcia borrows "Don Quixote"
