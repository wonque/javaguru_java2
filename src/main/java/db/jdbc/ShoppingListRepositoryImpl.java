package db.jdbc;

import db.ShoppingListRepository;
import domain.Product;
import domain.ShoppingList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ShoppingListRepositoryImpl extends PostgreJDBC implements ShoppingListRepository {


    @Override
    public void addToDataBase(ShoppingList shoppingList) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO java2.shopping_lists (ID, TITLE, CREATED, MODIFIED) VALUES (default, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, shoppingList.getTitle());
            preparedStatement.setTimestamp(2, shoppingList.getDateCreated());
            preparedStatement.setTimestamp(3, shoppingList.getDateModified());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Long shoppingListId = resultSet.getLong(1);
                shoppingList.setId(shoppingListId);
            }
        } catch (Throwable e) {
            System.out.println("Exception acquired while executing ProductBaseimpl.addProductToDB");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
