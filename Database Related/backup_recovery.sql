USE master
GO

CREATE PROCEDURE BackUpLibMangSysDB
AS
BEGIN
    BACKUP DATABASE LibraryManagementSystemDB
    TO DISK = 'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\Backup\LibMangSysDB.bak'
        WITH FORMAT,
        NAME = 'Full Backup of LibMangSysDB ',
        MEDIANAME = 'LibMangSysDB',
        DESCRIPTION = 'This is a backup file of the Library Management System Database'
END
GO

CREATE PROCEDURE RecoverLibMangSysDB
AS
BEGIN
    RESTORE DATABASE LibraryManagementSystemDB
    FROM DISK = 'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\Backup\LibMangSysDB.bak'
    WITH REPLACE;
END;
GO

-- Example for the procedures usage:
-- EXEC BackUpLibMangSysDB
-- GO
--
-- DROP DATABASE LibraryManagementSystemDB;
-- GO
--
-- USE master;
-- GO
--
-- EXEC RecoverLibMangSysDB
-- GO
--
-- USE LibraryManagementSystemDB;
-- GO