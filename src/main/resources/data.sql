DROP TABLE IF EXISTS Inventory;
 
CREATE TABLE Inventory (
  product_Id INT AUTO_INCREMENT  PRIMARY KEY,
  available_Quantity BIGINT,
  min_Quantity BIGINT,
  max_Quantity BIGINT,
  total_Quantity BIGINT,
  return_Quantity BIGINT,
  manufacturer VARCHAR
);
 
INSERT INTO Inventory (product_Id, available_Quantity, min_Quantity, max_Quantity, total_Quantity, return_Quantity, manufacturer) VALUES
  ('1', '4', '1', '3', '6', '2', 'Samsung'),
  ('2', '3', '1', '3', '4', '1', 'LG'),
  ('3', '3', '1', '3', '5', '2', 'HP');