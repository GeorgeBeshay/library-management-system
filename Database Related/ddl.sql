USE LibraryManagementSystemDB;
GO

DECLARE @inputTableName
VARCHAR(125) = 'PATRON';
IF
dbo.relation_exists(@inputTableName) = 0
BEGIN
    CREATE TABLE PATRON (
      ID int PRIMARY KEY IDENTITY(1, 1),
      FirstName nvarchar(50) NOT NULL,
      LastName nvarchar(50) NOT NULL,
      Email nvarchar(255) CONSTRAINT CK_Patron_Email UNIQUE NOT NULL,
      CHECK (Email LIKE '%@%')
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
      ID int PRIMARY KEY IDENTITY(1, 1),
      Title nvarchar(255) NOT NULL,
      Author nvarchar(255) NOT NULL,
      PublicationYear int,
      ISBN nvarchar(13) CONSTRAINT CK_Book_ISBN UNIQUE NOT NULL,
      CHECK (LEN(ISBN) = 13)
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
      PatronID int NOT NULL FOREIGN KEY REFERENCES PATRON(ID),
      BookID int NOT NULL FOREIGN KEY REFERENCES BOOK(ID),
      StartDate date NOT NULL,
      EndDate date NOT NULL,
      CONSTRAINT PK_Borrowing PRIMARY KEY (PatronID, BookID)
    );
END
ELSE
BEGIN
    PRINT 'BORROWING relation already exists.';
END
GO
