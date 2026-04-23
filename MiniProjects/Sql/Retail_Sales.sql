CREATE DATABASE Retail_Sales;
USE Retail_Sales;
CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    name VARCHAR(100),
    city VARCHAR(50)
);
INSERT INTO Customers VALUES
(1, 'Jayashree', 'Hyderabad'),
(2, 'Anjali', 'Delhi'),
(3, 'Rohith', 'Mumbai');
CREATE TABLE Products (
    product_id INT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(50),
    price DECIMAL(10,2)
);
INSERT INTO Products VALUES
(101, 'Laptop', 'Electronics', 50000),
(102, 'Phone', 'Electronics', 20000),
(103, 'Shoes', 'Fashion', 3000),
(104, 'Watch', 'Accessories', 5000);
CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_id INT,
    date DATE,
    FOREIGN KEY (customer_id) REFERENCES Customers(customer_id)
);
INSERT INTO Orders VALUES
(1001, 1, '2026-04-01'),
(1002, 2, '2026-04-02'),
(1003, 1, '2026-04-05');
CREATE TABLE Order_Items (
    order_id INT,
    product_id INT,
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id)
);
INSERT INTO Order_Items VALUES
(1001, 101, 1),
(1001, 103, 2),
(1002, 102, 1),
(1003, 104, 3);

SELECT p.product_id, p.name,SUM(oi.quantity) AS total_quantity_sold
FROM Products p
JOIN Order_Items oi ON p.product_id = oi.product_id
GROUP BY p.product_id, p.name
ORDER BY total_quantity_sold DESC;

SELECT c.customer_id, c.name,
       SUM(p.price * oi.quantity) AS total_spent
FROM Customers c
JOIN Orders o ON c.customer_id = o.customer_id
JOIN Order_Items oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY c.customer_id, c.name
ORDER BY total_spent DESC;

SELECT DATE_FORMAT(o.date, '%Y-%m') AS month,
       SUM(p.price * oi.quantity) AS monthly_revenue
FROM Orders o
JOIN Order_Items oi ON o.order_id = oi.order_id
JOIN Products p ON oi.product_id = p.product_id
GROUP BY month
ORDER BY month;

SELECT p.category,
       SUM(p.price * oi.quantity) AS total_sales
FROM Products p
JOIN Order_Items oi ON p.product_id = oi.product_id
GROUP BY p.category
ORDER BY total_sales DESC;

SELECT c.customer_id, c.name
FROM Customers c
LEFT JOIN Orders o ON c.customer_id = o.customer_id
WHERE o.order_id IS NULL;
