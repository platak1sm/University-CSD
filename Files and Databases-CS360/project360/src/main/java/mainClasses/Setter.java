package project360_try1;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class Setter {

    static public void setYearsOfEmployment(Connection conn, int eid) throws SQLException, ParseException {

        Statement statement = conn.createStatement();

        String update_employee =
            "UPDATE PermanentEmployees" +
            " SET YearsOfEmployment = " + Calculator.calculateYearsOfEmployment(conn, eid) +
            " WHERE eid =" + eid;

        statement.executeUpdate(update_employee);

    }

    static public void increaseNumOfChildren(Connection conn, int eid) throws SQLException {

        int numOfChildren = Getter.getInt(conn, eid, "NumOfChildren", "Employees");
        numOfChildren++;

        Statement statement = conn.createStatement();

        String update_employee =
            "UPDATE Employees" +
            " SET NumOfChildren = " + numOfChildren +
            " WHERE eid =" + eid;

        statement.executeUpdate(update_employee);

    }

    static public void setChildAge(Connection conn, int cid) throws SQLException, ParseException {

        Statement statement = conn.createStatement();

        String update =
            "UPDATE Children" +
            " SET Age = " + Calculator.calculateAgeOfChild(conn, cid) +
            " WHERE cid =" + cid;

        statement.executeUpdate(update);
    }

    static public void insertPermanentEmployeeSalary(Connection conn, int eid) throws SQLException, ParseException {

        Statement statement = conn.createStatement();

        String insert =
            "INSERT INTO PaymentInfo(eid, Salary) VALUES (" + eid + ", "
            + Calculator.calculatePermanentEmployeeSalary(conn, eid) + ")";

        statement.executeUpdate(insert);
    }

    // called after basic salaries have been changed
    static public void setPermanentEmployeeSalary(Connection conn, int eid) throws SQLException, ParseException {

        Statement statement = conn.createStatement();
        String insert =
            "UPDATE PaymentInfo SET Salary = "
            + Calculator.calculatePermanentEmployeeSalary(conn, eid) +
            " WHERE eid = " + eid;

        statement.executeUpdate(insert);
    }

    static public void insertContractEmployeeSalary(Connection conn, int eid, double salary) throws SQLException {

        Statement statement = conn.createStatement();

        String insert =
            "INSERT INTO PaymentInfo(eid, Salary) VALUES (" + eid + ", " + salary + ")";

        statement.executeUpdate(insert);
    }

    static public void setContractEmployeeSalary(Connection conn, int eid, double salary) throws SQLException, ParseException {

        Statement statement = conn.createStatement();
        String insert =
            "UPDATE PaymentInfo SET Salary = " + salary + "WHERE eid = " + eid;

        statement.executeUpdate(insert);
    }

    static public void setFamilyAllowance(Connection conn, int eid) throws SQLException {

        Statement statement = conn.createStatement();

        String set =
            "UPDATE PaymentInfo"+
            " SET FamilyAllowance = "+ Calculator.calculateFamilyAllowance(conn, eid) +
            " WHERE eid = " + eid;

        statement.executeUpdate(set);
    }

    static public void setTotalAllowance(Connection conn, int eid) throws SQLException {

        Statement statement = conn.createStatement();

        String set =
            "UPDATE PaymentInfo"+
            " SET TotalAllowance = "+ Calculator.calculateTotalAllowance(conn, eid) +
            " WHERE eid = " + eid;

        statement.executeUpdate(set);
    }

    static public void setTotalPayment(Connection conn, int eid) throws SQLException {

        Statement statement = conn.createStatement();

        String set =
            "UPDATE PaymentInfo"+
            " SET TotalPayment = "+ Calculator.calculateTotalPayment(conn, eid) +
            " WHERE eid = " + eid;

        statement.executeUpdate(set);
    }

    static public void setActive(Connection conn, int eid, boolean Active) throws SQLException {

        Statement statement = conn.createStatement();

        String set =
            "UPDATE Employees"+
            " SET Active = "+ Active +
            " WHERE eid = " + eid;

        statement.executeUpdate(set);
    }

    static public void setToBeFiredOrRetired(Connection conn, int eid, boolean toBeFiredOrRetired) throws SQLException {

        Statement statement = conn.createStatement();

        String set =
                "UPDATE Employees"+
                        " SET toBeFiredOrRetired = "+ toBeFiredOrRetired +
                        " WHERE eid = " + eid;

        statement.executeUpdate(set);
    }

    // -------------------------------------------------------------------------------



}
