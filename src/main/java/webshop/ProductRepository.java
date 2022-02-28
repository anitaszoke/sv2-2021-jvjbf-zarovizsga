package webshop;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    MariaDbDataSource dataSource;

    public ProductRepository(MariaDbDataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = null;
    }

    public long insertProduct(String productName, int price, int stock) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("insert into products(product_name, price, stock) values (?,?,?,?);",
                     Statement.RETURN_GENERATED_KEYS)
        ) {

            stmt.setString(1, (productName));
            stmt.setInt(2, price);
            stmt.setInt(3, stock);


            stmt.executeUpdate();
            return executeAndGetGeneratedKey(stmt);
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
    }

    private long executeAndGetGeneratedKey(PreparedStatement stmt) {
        try (
                ResultSet rs = stmt.getGeneratedKeys();
        ) {
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                throw new SQLException("No key has generated");
            }
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
    }

    public Product findProductById(long id) {
        Product p = null;
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt =
                        conn.prepareStatement("select * from employees where id = ?");
        ) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Long iD = rs.getLong("id");
                    String productName = rs.getString("product_name");
                    int price = rs.getInt("price");
                    int stock = rs.getInt("stock");
                    p = new Product(iD,productName,price,stock);
                }
            }


        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Error by insert", sqle);
        }
        return p;
    }

    public void updateProductStock(long id, int amount) {
        jdbcTemplate.update("update products set stock = stock-? where id = ?", amount, id);
    }
}


