package project360_try1;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;

public class Initializer {



    static public void insertInitialValues(Connection conn) throws SQLException, ParseException {

//        insertInitialEmployees(conn);
//        insertInitialPermanentEmployees(conn);
//        insertInitialContractEmployees(conn);
//        insertInitialContracts(conn);
        insertInitialAllowances(conn);
        insertInitialBasicSalaries(conn);
//        insertInitialChildren(conn);
//
//        insertAdditionalValues(conn);
    }

    static private void insertInitialEmployees(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String insert =
            "INSERT INTO Employees(FirstName, LastName, Role, Category, Active, Married)" +
            "VALUES " +
            "('Giorgos', 'Papadopoulos', 'permanent', 'administrative', true, false)," +
            "('Maria', 'Alexiadou', 'permanent', 'teaching', true, true)," +
            "('Jane', 'Fonda', 'contract', 'administrative', true, false)," +
            "('James', 'Johnson', 'contract', 'teaching', true, true)";

        statement.executeUpdate(insert);

    }

    static private void insertInitialPermanentEmployees(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        // insert initial values
        String insert =
            "INSERT INTO PermanentEmployees(eid, DateOfEmployment)" +
            "VALUES " +
            "(1, '2021-01-26')," +
            "(2, '2021-01-26')";

        statement.executeUpdate(insert);
    }

    static private void insertInitialContractEmployees(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String insert =
            "INSERT INTO ContractEmployees(eid)" +
            "VALUES " +
            "(3)," +
            "(4)";

        statement.executeUpdate(insert);
    }

    static private void insertInitialContracts(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String insert =
            "INSERT INTO Contracts(eid, StartOfContract, EndOfContract)" +
            "VALUES " +
            "(3, '2023-01-01', '2024-01-01')," +
            "(4, '2023-01-15', '2023-05-15')";

        statement.executeUpdate(insert);
    }

    static private void insertInitialAllowances(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String insert =
            "INSERT INTO Allowances(ResearchAllowance, LibraryAllowance, FamilyAllowancePercentage)" +
            "VALUES " +
            "(400, 200, 0.05)";

        statement.executeUpdate(insert);
    }

    static private void insertInitialBasicSalaries(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String insert =
            "INSERT INTO BasicSalaries(BasicAdministrativeSalary, BasicTeachingSalary)" +
            "VALUES " +
            "(1200, 1000)";

        statement.executeUpdate(insert);
    }

    static private void insertInitialChildren(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String insert =
            "INSERT INTO Children(eid, FirstName, LastName, Age)" +
            "VALUES " +
            "(4, 'James Junior', 'Johnson', 7)," +
            "(4, 'Alicia', 'Johnson', 16)," +
            "(4, 'Freddie', 'Johnson', 21)";

        statement.executeUpdate(insert);
    }

//    static private void insertAdditionalValues(Connection conn) throws SQLException, ParseException {
//
//        Statement statement = conn.createStatement();
//
//        // set num of children for each Employee
//        ResultSet result_set = statement.executeQuery("SELECT eid FROM Employees");
//        while(result_set.next())
//            Setter.setNumOfChildren(conn, result_set.getInt("eid"));
//
//
//        // set years of employment for each Permanent Employee
//        result_set = statement.executeQuery("SELECT eid FROM PermanentEmployees");
//        while(result_set.next())
//            Setter.setYearsOfEmployment(conn, result_set.getInt("eid"));
//
//
//        // set salary of Permanent Employees
//        result_set = statement.executeQuery("SELECT eid FROM PermanentEmployees");
//        while(result_set.next()) {
//            int eid = result_set.getInt("eid");
//            Setter.setPermanentEmployeeSalary(conn, eid);
//        }
//
//
//        // set salary of Contract Employees
//        Setter.setContractEmployeeSalary(conn, 3, 950);
//        Setter.setContractEmployeeSalary(conn, 4, 800);
//
//
//        // set family allowance
//        result_set = statement.executeQuery("SELECT eid FROM Employees");
//        while(result_set.next()) {
//            int eid = result_set.getInt("eid");
//            Setter.setFamilyAllowance(conn, eid);
//            Setter.setTotalAllowance(conn, eid);
//            Setter.setTotalPayment(conn, eid);
//        }
//
////        result_set = statement.executeQuery("SELECT eid FROM Employees");
////        while(result_set.next()) {
////            int eid = result_set.getInt("eid");
////            Setter.setTotalAllowance(conn, eid);
////        }
//    }

}
