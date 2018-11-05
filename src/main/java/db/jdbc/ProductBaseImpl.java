package db.jdbc;

import db.ProductBase;
import domain.Product;
import services.SetProductDetailsService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductBaseImpl extends PostgreJDBC implements ProductBase {

    private SetProductDetailsService productDetailsService;

    public ProductBaseImpl(SetProductDetailsService productDetailsService) {
        this.productDetailsService = productDetailsService;
    }

    @Override
    public void addToDataBase(Product product) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO java2.products (ID, TITLE, DESCRIPTION, CATEGORY, PRICE) VALUES (default, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getTitle());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setBigDecimal(4, product.getPrice());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Long productId = resultSet.getLong(1);
                productDetailsService.modifyProductId(productId, product);
            }
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductBaseimpl.addProductToDB");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public Optional<Product> findByTitle(String title) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM java2.products WHERE TITLE = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            //executequery() returns ResultSet object
            ResultSet resultSet = preparedStatement.executeQuery();
            Product product = null;
            if (resultSet.next()) {
                product = new Product();
                productDetailsService.modifyProductId(resultSet.getLong("id"), product);
                productDetailsService.modifyProductTitle(resultSet.getString("title"), product);
                productDetailsService.modifyProductCategory(resultSet.getString("description"), product);
                productDetailsService.modifyProductPrice(resultSet.getBigDecimal("price"), product);
                productDetailsService.modifyProductCategory(resultSet.getString("category"), product);
            }
            return Optional.ofNullable(product);
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductBaseimpl.findByTitle");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean remove(Product product) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "DELETE FROM java2.products WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, product.getId());
            //executeUpdate() returns number of rows affected by sql query
            int numberOfRowsDeleted = preparedStatement.executeUpdate();
            return numberOfRowsDeleted == 1;
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductBaseimpl.remove");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeConnection(connection);

        }
    }

    @Override
    public List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM java2.products";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                productDetailsService.modifyProductId(resultSet.getLong("id"), product);
                productDetailsService.modifyProductTitle(resultSet.getString("title"), product);
                productDetailsService.modifyProductCategory(resultSet.getString("description"), product);
                productDetailsService.modifyProductPrice(resultSet.getBigDecimal("price"), product);
                productDetailsService.modifyProductCategory(resultSet.getString("category"), product);
                products.add(product);
            }
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductBaseimpl.getProductList");
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            closeConnection(connection);
        }
        return products;
    }
}

