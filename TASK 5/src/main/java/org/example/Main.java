package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    String db_url = System.getenv("DB_URL");
    String user = System.getenv("USER");
    String password = System.getenv("PASSWORD");

    List<String> dbInitializationQueries;

    try {
      dbInitializationQueries = Files.readAllLines(
          Path.of("C:\\Users\\grata\\IdeaProjects\\TASK 5\\src\\main\\resources\\data.sql"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try(Connection conn = DriverManager.getConnection(db_url, user, password)) {
      Statement statement = conn.createStatement();

      // Doing initialization (create tables and insert data)
      for(String script: dbInitializationQueries) {
        statement.execute(script);
        System.out.println("Query: " + script);
      }

      System.out.println();

      // Select product with product name that begins with ‘C’.
      ResultSet res1 = statement.executeQuery("select * from products where product_name like 'C%'");
      while (res1.next()) {
        System.out.println(res1.getString(1) + "  " + res1.getString(2) + "  "
            + res1.getString(3) + "  " + res1.getString(4) + "  " + res1.getString(5));
      }

      System.out.println();

      // Select product with the smallest price.
      ResultSet res2 = statement.executeQuery("select * from products where price = (SELECT MIN(price) from products)");
      while (res2.next()) {
        System.out.println(res2.getString(1) + "  " + res2.getString(2) + "  "
            + res2.getString(3) + "  " + res2.getString(4) + "  " + res2.getString(5));
      }

      System.out.println();

      // Select cost of all products from the USA.
      ResultSet res3 = statement.executeQuery("select Sum(g.Price) as SummaryCost, g.Country from"
          + "(select p.Price, s.Country from Products p inner join Suppliers s on p.supplier_id = s.supplier_id)"
          + "g group by g.Country having g.Country = 'USA'");
      while (res3.next()) {
        System.out.println(res3.getString(1) + "  " + res3.getString(2));
      }

      System.out.println();

      // Select suppliers that supply Condiments.
      ResultSet res4 = statement.executeQuery("select g.supplier_name, g.City, g.Country, g.category_name from"
          + "(select c.category_name, s.supplier_name, s.city, s.country from products p "
          + "inner join suppliers s on p.supplier_id = s.supplier_id inner join categories c on p.categories_id = c.category_id)"
          + "g where g.category_name = 'Condiments'");
      while (res4.next()) {
        System.out.println(res4.getString(1) + "  " + res4.getString(2) + "  "
            + res4.getString(3) + "  " + res4.getString(4));
      }

      System.out.println();

      // Add to database new supplier with name: ‘Norske Meierier’, city: ‘Lviv’, country: ‘Ukraine’ which
      // will supply new product with name: ‘Green tea’, price: 10, and related to category with name:
      // ‘Beverages’.
      statement.execute("insert into suppliers(supplier_name, city, country) values ('Norske Meierier', 'Lviv', 'Ukraine')");
      statement.execute("insert into products(product_name, supplier_id, categories_id, price) values"
          + "('Green tea', (select supplier_id from suppliers where supplier_name = 'Norske Meierier'), "
          + "(select category_id from categories where category_name = 'Beverages'), 10)");
      ResultSet res5 = statement.executeQuery("select * from products");
      while (res5.next()) {
        System.out.println(res5.getString(1) + "  " + res5.getString(2) + "  "
            + res5.getString(3) + "  " + res5.getString(4) + "  " + res5.getString(5));
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}