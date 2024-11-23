INSERT INTO sec_companies (id, address, contact, NAME)
VALUES
(1, 'Dhaka', '01700000000', 'ABC Real Estate Solutions');

INSERT INTO sec_users (id, address, blood_group, cell, date_of_birth, email, gender, joining_date, NAME, ROLE, STATUS, company_id)
VALUES 
(1, '123 Main St, Cityville', 'O+', '01700000001', '1990-05-15', 'customer1@company.com', 'Male', '2020-01-01', 'John Doe', 'ROLE_CUSTOMER', 'ACTIVE', 1),
(2, '456 Elm St, Cityville', 'A+', '01700000002', '1985-08-20', 'customer2@company.com', 'Female', '2020-02-01', 'Jane Smith', 'ROLE_CUSTOMER', 'ACTIVE', 1),
(3, '789 Pine St, Cityville', 'B+', '01700000003', '1992-11-12', 'customer3@company.com', 'Male', '2021-03-01', 'Mike Johnson', 'ROLE_CUSTOMER', 'ACTIVE', 1),
(4, '321 Oak St, Cityville', 'AB+', '01700000004', '1991-02-22', 'customer4@company.com', 'Female', '2021-04-01', 'Emily Davis', 'ROLE_CUSTOMER', 'ACTIVE', 1),
(5, '654 Maple St, Cityville', 'O-', '01700000005', '1993-04-18', 'customer5@company.com', 'Male', '2021-05-01', 'David Clark', 'ROLE_CUSTOMER', 'ACTIVE', 1),
(6, '987 Cedar St, Cityville', 'A-', '01700000006', '1994-07-25', 'customer6@company.com', 'Female', '2021-06-01', 'Sophia Walker', 'ROLE_CUSTOMER', 'ACTIVE', 1),
(7, '123 Birch St, Cityville', 'B-', '01700000007', '1990-09-12', 'customer7@company.com', 'Male', '2020-07-01', 'James Allen', 'ROLE_CUSTOMER', 'ACTIVE', 1),
(8, '456 Pine St, Cityville', 'O+', '01700000008', '1995-10-05', 'customer8@company.com', 'Female', '2021-08-01', 'Olivia Taylor', 'ROLE_CUSTOMER', 'ACTIVE', 1),
(9, '789 Oak St, Cityville', 'AB-', '01700000009', '1992-12-30', 'customer9@company.com', 'Male', '2021-09-01', 'Liam Thomas', 'ROLE_CUSTOMER', 'ACTIVE', 1),
(10, '321 Maple St, Cityville', 'A+', '01700000010', '1996-11-20', 'customer10@company.com', 'Female', '2021-10-01', 'Emma Harris', 'ROLE_CUSTOMER', 'ACTIVE', 1);

INSERT INTO sec_users (id, address, blood_group, cell, date_of_birth, email, gender, joining_date, NAME, ROLE, STATUS, company_id)
VALUES 
(11, '111 High St, Cityville', 'O+', '01700000011', '1980-01-01', 'owner1@company.com', 'Male', '2015-01-01', 'Johnathan Black', 'ROLE_OWNER', 'ACTIVE', 1),
(12, '222 Low St, Cityville', 'B-', '01700000012', '1975-03-15', 'owner2@company.com', 'Female', '2016-03-01', 'Anna White', 'ROLE_OWNER', 'ACTIVE', 1);


INSERT INTO sec_users (id, address, blood_group, cell, date_of_birth, email, gender, joining_date, NAME, ROLE, STATUS, company_id)
VALUES 
(13, '111 First St, Cityville', 'AB+', '01700000013', '1994-05-10', 'employee1@company.com', 'Male', '2021-01-01', 'Tom Green', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(14, '222 Second St, Cityville', 'O-', '01700000014', '1991-07-22', 'employee2@company.com', 'Female', '2021-02-01', 'Sophia Lee', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(15, '333 Third St, Cityville', 'A+', '01700000015', '1993-03-17', 'employee3@company.com', 'Male', '2021-03-01', 'Daniel Brown', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(16, '444 Fourth St, Cityville', 'B+', '01700000016', '1992-06-10', 'employee4@company.com', 'Female', '2021-04-01', 'Olivia White', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(17, '555 Fifth St, Cityville', 'O+', '01700000017', '1990-05-12', 'employee5@company.com', 'Male', '2021-05-01', 'Luke Harris', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(18, '666 Sixth St, Cityville', 'A-', '01700000018', '1992-09-23', 'employee6@company.com', 'Female', '2021-06-01', 'Mia Lewis', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(19, '777 Seventh St, Cityville', 'B-', '01700000019', '1994-02-18', 'employee7@company.com', 'Male', '2021-07-01', 'Liam Carter', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(20, '888 Eighth St, Cityville', 'AB-', '01700000020', '1995-12-14', 'employee8@company.com', 'Female', '2021-08-01', 'Emma Adams', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(21, '999 Ninth St, Cityville', 'O+', '01700000021', '1991-03-22', 'employee9@company.com', 'Male', '2021-09-01', 'James Miller', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(22, '1010 Tenth St, Cityville', 'A+', '01700000022', '1993-10-19', 'employee10@company.com', 'Female', '2021-10-01', 'Emily Clark', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(23, '1111 Eleventh St, Cityville', 'B+', '01700000023', '1994-08-03', 'employee11@company.com', 'Male', '2021-11-01', 'Joshua King', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(24, '1212 Twelfth St, Cityville', 'O-', '01700000024', '1992-07-16', 'employee12@company.com', 'Female', '2021-12-01', 'Isabella Turner', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(25, '1313 Thirteenth St, Cityville', 'AB+', '01700000025', '1990-04-05', 'employee13@company.com', 'Male', '2022-01-01', 'Michael Scott', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(26, '1414 Fourteenth St, Cityville', 'A-', '01700000026', '1995-01-13', 'employee14@company.com', 'Female', '2022-02-01', 'Sophia Robinson', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(27, '1515 Fifteenth St, Cityville', 'B-', '01700000027', '1996-09-25', 'employee15@company.com', 'Male', '2022-03-01', 'William White', 'ROLE_EMPLOYEE', 'ACTIVE', 1),
(28, '1616 Sixteenth St, Cityville', 'O+', '01700000028', '1993-11-30', 'employee16@company.com', 'Female', '2022-04-01', 'Charlotte Moore', 'ROLE_EMPLOYEE', 'ACTIVE', 1);


INSERT INTO sec_users (id, address, blood_group, cell, date_of_birth, email, gender, joining_date, NAME, ROLE, STATUS, company_id)
VALUES 
(29, '111 Manager St, Cityville', 'AB+', '01700000029', '1985-02-01', 'manager1@company.com', 'Male', '2018-01-01', 'John King', 'ROLE_MANAGER', 'ACTIVE', 1),
(30, '222 Manager St, Cityville', 'O-', '01700000030', '1980-09-10', 'manager2@company.com', 'Female', '2019-03-01', 'Sarah Evans', 'ROLE_MANAGER', 'ACTIVE', 1),
(31, '333 Manager St, Cityville', 'A+', '01700000031', '1987-07-17', 'manager3@company.com', 'Male', '2020-01-01', 'Michael Carter', 'ROLE_MANAGER', 'ACTIVE', 1),
(32, '444 Manager St, Cityville', 'B+', '01700000032', '1983-12-25', 'manager4@company.com', 'Female', '2021-02-01', 'Jessica Taylor', 'ROLE_MANAGER', 'ACTIVE', 1);


-- Insert tokens for Customers
INSERT INTO sec_tokens (PASSWORD, username, user_id)
VALUES 
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'customer1', 1),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'customer2', 2),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'customer3', 3),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'customer4', 4),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'customer5', 5),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'customer6', 6),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'customer7', 7),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'customer8', 8),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'customer9', 9),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'customer10', 10);

-- Insert tokens for Owners
INSERT INTO sec_tokens (PASSWORD, username, user_id)
VALUES 
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'owner1', 11),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'owner2', 12);

-- Insert tokens for Employees
INSERT INTO sec_tokens (PASSWORD, username, user_id)
VALUES 
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee1', 13),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee2', 14),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee3', 15),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee4', 16),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee5', 17),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee6', 18),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee7', 19),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee8', 20),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee9', 21),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee10', 22),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee11', 23),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee12', 24),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee13', 25),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee14', 26),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee15', 27),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'employee16', 28);

-- Insert tokens for Managers
INSERT INTO sec_tokens (PASSWORD, username, user_id)
VALUES 
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'manager1', 29),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'manager2', 30),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'manager3', 31),
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'manager4', 32);


INSERT INTO sec_users (id, address, blood_group, cell, date_of_birth, email, gender, joining_date, NAME, ROLE, STATUS, company_id)
VALUES 
(33, 'Dhaka', 'B+', '01700000000', '2000-09-28', 'admin@company.com', 'Male', '2020-01-01', 'Shabab Ahmed', 'ROLE_ADMIN', 'ACTIVE', 1);
INSERT INTO sec_tokens (PASSWORD, username, user_id)
VALUES 
('$2a$12$HlpXqCFYqZcPS1e7qCC0KuDkXpYGrXRyCnplWS8qZakH9WcVCFDK2', 'admin1', 33);
