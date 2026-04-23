
CREATE DATABASE Digital_Library;
USE Digital_Library;
CREATE TABLE Books (
    Book_Id INT PRIMARY KEY,
    Book_Name VARCHAR(100),
    Category VARCHAR(50),
    Total_Copies INT
);
INSERT INTO Books VALUES
(1, 'Ancient History', 'History', 20),
(2, 'Modern Physics', 'Science', 15),
(3, 'World Wars', 'History', 10),
(4, 'Space Science', 'Science', 20),
(5, 'The Adventure', 'Fiction', 15);
CREATE TABLE Students (
    Student_Id INT PRIMARY KEY,
    Student_Name VARCHAR(100),
    Status VARCHAR(20) DEFAULT 'Active'
);
INSERT INTO Students (Student_Id, Student_Name) VALUES
(101, 'Jaya'),
(102, 'Ravi'),
(103, 'Kiran'),
(104, 'Keerthana'),
(105, 'Nethra'),
(106, 'Rohtih'),
(107, 'Rahul'),
(108, 'Divya'),
(109, 'Manoj'),
(110, 'Priyanka');
CREATE TABLE Issued_Books (
    Issue_Id INT PRIMARY KEY,
    Student_Id INT,
    Book_Id INT,
    Issue_Date DATE,
    Return_Date DATE,
    FOREIGN KEY (Student_Id) REFERENCES Students(Student_Id),
    FOREIGN KEY (Book_Id) REFERENCES Books(Book_Id)
);
INSERT INTO Issued_Books VALUES
(1, 101, 1, '2026-03-01', NULL),
(2, 102, 2, '2026-03-05', NULL),
(3, 103, 3, '2026-03-10', NULL),
(4, 104, 4, '2025-11-01', NULL),
(5, 105, 5, '2024-06-15', '2024-06-25'),
(6, 106, 1, '2022-05-10', '2022-05-20'),
(7, 107, 2, '2021-07-01', '2021-07-10');
SELECT s.Student_Name, b.Book_Name, i.Issue_Date
FROM Issued_Books i
JOIN Students s ON i.Student_Id = s.Student_Id
JOIN Books b ON i.Book_Id = b.Book_Id
WHERE i.Return_Date IS NULL
AND i.Issue_Date < CURRENT_DATE - INTERVAL 14 DAY;
SELECT b.Category, COUNT(*) AS Borrow_Count
FROM Issued_Books i
JOIN Books b ON i.Book_Id = b.Book_Id
GROUP BY b.Category
ORDER BY Borrow_Count DESC;
UPDATE Students
SET Status = 'Inactive'
WHERE Student_Id NOT IN (
    SELECT DISTINCT Student_Id
    FROM Issued_Books
    WHERE Issue_Date >= CURRENT_DATE - INTERVAL 3 YEAR
);
