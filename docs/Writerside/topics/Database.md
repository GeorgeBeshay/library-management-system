# Database

The library management system is designed to streamline the organization and retrieval of library resources. 
At the heart of this system lies a robust database schema, implemented using Microsoft SQL Server, which ensures 
efficient data management and integrity. 
The schema is composed of three primary entities: PATRON, BOOK, and BORROWING. 
The PATRON entity captures essential information about library members, including a unique ID, personal details, 
and contact information. 
The BOOK entity records the details of the library’s book inventory, such as title, author, 
and publication year, each identified by a unique ID. The BORROWING entity serves as the linchpin 
that connects patrons to the books they borrow, tracking the borrowing period with start and end dates. 
This relational schema facilitates a one-to-many relationship between patrons and books, allowing for 
multiple borrowings while maintaining a clear record of transactions. 
By leveraging the capabilities of SQL Server, the system provides a scalable and reliable foundation for 
managing the library’s operations.

## ERD
![ERD.svg](ERD.svg)

## Backup And Recovery
The backup and recovery aspect of the DB was implemented to allow the DBA the opportunity to backup
the current state of the DB and copy it to any external secondary storage medium.
![backup_recovery.png](backup_recovery.png)

## Borrowing Trigger
A Trigger has been developed to insure the DB consistency when adding a borrow
record, that is, we need to ensure the book being borrowed is not currently
borrowed by someone else.
![borrow_trigger.png](borrow_trigger.png)