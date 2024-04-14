USE LibraryManagementSystemDB;
GO

CREATE TRIGGER CheckBookAvailability
ON BORROWING
INSTEAD OF INSERT
AS
BEGIN
  SET NOCOUNT ON;
  DECLARE @BookID INT;
  SELECT @BookID = book_id FROM INSERTED;

  IF EXISTS (
    SELECT 1
    FROM BORROWING b WITH (UPDLOCK, HOLDLOCK)
    WHERE b.book_id = @BookID AND b.end_date = null
  )
  BEGIN
    RAISERROR ('Book with ID %d is currently borrowed and unavailable.', 16, 1, @BookID);
  END;
  ELSE
  BEGIN
    INSERT INTO BORROWING (patron_id, book_id, start_date, end_date)
    SELECT patron_id, book_id, start_date, end_date FROM INSERTED;
  END;
END;
