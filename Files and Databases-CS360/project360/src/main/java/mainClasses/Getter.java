package project360_try1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Getter {

    // -----------------------------------------------------------------------------------------------------

    static public double getBasicAdministrativeSalary() throws SQLException {

        Connection conn = Connector.getConnection();

        Statement statement = conn.createStatement();
        ResultSet result_set = statement.executeQuery("SELECT BasicAdministrativeSalary FROM BasicSalaries");

        result_set.next();
        return result_set.getDouble(1);
    }

    static public double getBasicTeachingSalary() throws SQLException {

        Connection conn = Connector.getConnection();

        Statement statement = conn.createStatement();
        ResultSet result_set = statement.executeQuery("SELECT BasicTeachingSalary FROM BasicSalaries");

        result_set.next();
        return result_set.getDouble(1);
    }

    static public double getFamilyAllowancePercentage() throws SQLException {

        Connection conn = Connector.getConnection();

        Statement statement = conn.createStatement();
        ResultSet result_set = statement.executeQuery("SELECT FamilyAllowancePercentage FROM Allowances");

        result_set.next();
        return result_set.getDouble(1);
    }

    static public double getResearchAllowance() throws SQLException {

        Connection conn = Connector.getConnection();

        Statement statement = conn.createStatement();
        ResultSet result_set = statement.executeQuery("SELECT ResearchAllowance FROM Allowances");

        result_set.next();
        return result_set.getDouble(1);
    }

    static public double getLibraryAllowance() throws SQLException {

        Connection conn = Connector.getConnection();

        Statement statement = conn.createStatement();
        ResultSet result_set = statement.executeQuery("SELECT LibraryAllowance FROM Allowances");

        result_set.next();
        return result_set.getDouble(1);
    }



    static public String getString(Connection conn, int eid, String field, String table) throws SQLException {

        Statement statement = conn.createStatement();

        String query = "SELECT " + field + " FROM " + table + " WHERE eid = " + eid;

        ResultSet result_set = statement.executeQuery(query);
        result_set.next();

        return result_set.getString(1);
    }

    static public int getInt(Connection conn, int eid, String field, String table) throws SQLException {

        Statement statement = conn.createStatement();

        String query = "SELECT " + field + " FROM " + table + " WHERE eid = " + eid;

        ResultSet result_set = statement.executeQuery(query);
        result_set.next();

        return result_set.getInt(1);
    }

    static public double getDouble(Connection conn, int eid, String field, String table) throws SQLException {

        Statement statement = conn.createStatement();

        String query = "SELECT " + field + " FROM " + table + " WHERE eid = " + eid;

        ResultSet result_set = statement.executeQuery(query);
        result_set.next();

        return result_set.getDouble(1);
    }

    static public boolean getBoolean(Connection conn, int eid, String field, String table) throws SQLException {

        Statement statement = conn.createStatement();

        String query = "SELECT " + field + " FROM " + table + " WHERE eid = " + eid;

        ResultSet result_set = statement.executeQuery(query);
        result_set.next();

        return result_set.getBoolean(1);
    }

}
