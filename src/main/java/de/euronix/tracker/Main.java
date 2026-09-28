package de.euronix.tracker;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:trading.db";

        try(Connection connection = DriverManager.getConnection(url)) {

            System.out.println("Connected to the database");

            String sql = """
                    CREATE TABLE IF NOT EXISTS trades (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        player_name TEXT NOT NULL,
                        card_type TEXT NOT NULL,
                        buy_price INTEGER NOT NULL,
                        sell_price INTEGER,
                        buy_date DATETIME NOT NULL,
                        sell_date DATETIME
                    )
                    """;

            Statement statement = connection.createStatement();
            statement.execute(sql);

            Trades trades = new Trades();
            trades.getAllTrades(connection);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
