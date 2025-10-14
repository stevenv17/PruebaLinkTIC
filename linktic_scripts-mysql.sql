
CREATE TABLE producto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  descripcion TEXT
) ENGINE=InnoDB;

CREATE TABLE inventario (
  producto_id INT PRIMARY KEY,
  cantidad INT NOT NULL,
  FOREIGN KEY (producto_id) REFERENCES producto(id)
) ENGINE=InnoDB;



