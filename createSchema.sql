# Create database if not exists
CREATE SCHEMA IF NOT EXISTS abcis;

# Select default DB
USE abcis;

# Create company table
CREATE TABLE IF NOT EXISTS company (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	address VARCHAR(100) NOT NULL
)   ENGINE=INNODB;

# Create employee table
CREATE TABLE IF NOT EXISTS employee (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    company_id INT NOT NULL,
    
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	age SMALLINT,
	address VARCHAR(100),
    email VARCHAR(10),
	contact_number VARCHAR(15),
	job_title VARCHAR(30),
	salary VARCHAR(30),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX (company_id),
    CONSTRAINT fk_company
		FOREIGN KEY (company_id)
		REFERENCES companys(id)
)   ENGINE=INNODB;