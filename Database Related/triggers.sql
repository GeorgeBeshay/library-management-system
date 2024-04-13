USE LibraryManagementSystemDB;
GO

CREATE TRIGGER CheckBookAvailability
ON BORROWING
INSTEAD OF INSERT
AS
BEGIN
  SET NOCOUNT ON;
  DECLARE @BookID INT;
  SELECT @BookID = BookID FROM INSERTED;

  IF EXISTS (
    SELECT 1
    FROM BORROWING b WITH (UPDLOCK, HOLDLOCK)
    WHERE b.BookID = @BookID
  )
  BEGIN
    RAISERROR ('Book with ID %d is currently borrowed and unavailable.', 16, 1, @BookID);
  END;
  ELSE
  BEGIN
    INSERT INTO BORROWING (BookID, PatronID, StartDate, EndDate)
    SELECT BookID, PatronID, StartDate, EndDate FROM INSERTED;
  END;
END;
