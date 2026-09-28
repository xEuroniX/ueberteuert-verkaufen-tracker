package de.euronix.tracker;

import java.sql.*;

public class Trades {

    //Trade erstellen
    public void createTrade(Connection connection,String name, String rarity, int price) throws SQLException {
        String sql = """
               INSERT INTO trades (player_name, card_type, buy_price, buy_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP)
               """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, rarity);
            preparedStatement.setInt(3, price);
            preparedStatement.executeUpdate();
        }
    }

    //Trades anzeigen in Konsole
    public void getAllTrades(Connection connection) throws SQLException {

        String sql = """
            SELECT *
            FROM trades
            """;

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("player_name");
                String rarity = resultSet.getString("card_type");
                int buyPrice = resultSet.getInt("buy_price");

                System.out.println(
                        id + " | " +
                                name + " | " +
                                rarity + " | " +
                                buyPrice + " | "
                );
            }
        }
    }
}
