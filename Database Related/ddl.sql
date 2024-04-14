USE LibraryManagementSystemDB;
GO

DECLARE @inputTableName
VARCHAR(125) = 'PATRON';
IF
dbo.relation_exists(@inputTableName) = 0
BEGIN
    CREATE TABLE PATRON (
      id int PRIMARY KEY IDENTITY(1, 1),
      first_name nvarchar(50) NOT NULL,
      last_name nvarchar(50) NOT NULL,
      email nvarchar(255) CONSTRAINT CK_Patron_Email UNIQUE NOT NULL,
      CHECK (email LIKE '%@%')
    );
END
ELSE
BEGIN
    PRINT 'PATRON relation already exists.';
END
GO


DECLARE @inputTableName
VARCHAR(125) = 'BOOK';
IF
dbo.relation_exists(@inputTableName) = 0
BEGIN
    CREATE TABLE BOOK (
      id int PRIMARY KEY IDENTITY(1, 1),
      title nvarchar(255) NOT NULL,
      author nvarchar(255) NOT NULL,
      publication_year int,
      isbn nvarchar(13) CONSTRAINT CK_Book_ISBN UNIQUE NOT NULL,
      CHECK (LEN(isbn) = 13)
    );
END
ELSE
BEGIN
    PRINT 'BOOK relation already exists.';
END
GO


DECLARE @inputTableName
VARCHAR(125) = 'BORROWING';
IF
dbo.relation_exists(@inputTableName) = 0
BEGIN
    CREATE TABLE BORROWING (
      patron_id int NOT NULL FOREIGN KEY REFERENCES PATRON(ID),
      book_id int NOT NULL FOREIGN KEY REFERENCES BOOK(ID),
      start_date date NOT NULL,
      end_date date,
      CONSTRAINT PK_Borrowing PRIMARY KEY (patron_id, book_id, start_date)
    );
END
ELSE
BEGIN
    PRINT 'BORROWING relation already exists.';
END
GO
