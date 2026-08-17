CREATE DATABASE deliciasmolina;

USE deliciasmolina;

CREATE TABLE users(
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      username VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      role ENUM("ADMIN", "SUPER_ADMIN") NOT NULL,
                      is_active BOOLEAN NOT NULL DEFAULT TRUE,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE categories(
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           category_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE offers(
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       offer_title VARCHAR(50) UNIQUE NOT NULL,
                       description TEXT NOT NULL,
                       discount_percentage DECIMAL(5,2) NOT NULL,
                       start_date DATE NOT NULL,
                       end_date DATE NOT NULL,
                       is_active BOOLEAN DEFAULT TRUE,

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NULL
);

CREATE TABLE products(
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         product_name VARCHAR(100) NOT NULL,
                         description TEXT NOT NULL,
                         base_price DECIMAL(10,2) NOT NULL,
                         image_url VARCHAR(255) NOT NULL,
                         category_id BIGINT NOT NULL,
                         offer_id BIGINT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NULL,

                         CONSTRAINT fk_category
                             FOREIGN KEY (category_id) REFERENCES categories(id),
                         CONSTRAINT fk_offer
                             FOREIGN KEY (offer_id) REFERENCES offers(id)
);

CREATE TABLE orders(
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       client_name VARCHAR(80) NOT NULL,
                       telephone VARCHAR(10) NOT NULL,
                       order_type ENUM("STANDARD", "CUSTOM") NOT NULL,
                       custom_quoted_price DECIMAL(10,2) NULL,
                       flavor VARCHAR(50),
                       people_quantity INT,
                       image_reference VARCHAR(255),
                       comments TEXT,
                       delivery_date DATE NOT NULL,
                       order_status ENUM("PENDING", "QUOTED", "ACCEPTED", "CANCELLED", "DELIVERED") NOT NULL DEFAULT 'PENDING',
                       total DECIMAL(10,2) NULL,

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NULL
);

CREATE TABLE order_details(
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              order_id BIGINT NOT NULL,
                              product_id BIGINT NOT NULL,
                              quantity INT NOT NULL,
                              unit_price DECIMAL(10,2) NOT NULL,
                              subtotal DECIMAl(10,2) NOT NULL,

                              CONSTRAINT fk_order
                                  FOREIGN KEY (order_id) REFERENCES orders(id),
                              CONSTRAINT fk_product
                                  FOREIGN KEY (product_id) REFERENCES products(id)
);