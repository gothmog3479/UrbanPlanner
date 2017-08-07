package ru.gothmog.urbanplanner.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface for communication with the database
 * @author d.grushetskiy
 */
public interface DaoFactory {
    Connection getConnection() throws SQLException, IOException;
}
