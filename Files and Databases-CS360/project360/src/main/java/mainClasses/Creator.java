package project360_try1;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Creator {



    // --------------------------------------------------------------------

    static public void createTables(Connection conn) throws SQLException {

        createEmployeesTable(conn);
        createPermanentEmployeesTable(conn);
        createContractEmployeesTable(conn);
        createContractsTable(conn);
        createChildrenTable(conn);
        createAllowancesTable(conn);
        createBasicSalariesTable(conn);
        createPaymentInfoTable(conn);
        createPaymentLogPermanentAdministrativeTable(conn);
        createPaymentLogContractAdministrativeTable(conn);
        createPaymentLogPermanentTeachingTable(conn);
        createPaymentLogContractTeachingTable(conn);
    }

    static private void createPermanentEmployeesTable(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String create =
            "CREATE TABLE PermanentEmployees(" +
            "eid INT NOT NULL," +
            "DateOfEmployment DATE," +
            "YearsOfEmployment INT," +
            "ToBeFiredOrRetired BOOLEAN," +
            "FOREIGN KEY(eid) REFERENCES Employees(eid)"   +
            ")";

        statement.executeUpdate(create);
    }

    static private void createContractEmployeesTable(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String create =
            "CREATE TABLE ContractEmployees(" +
            "eid INT NOT NULL," +
            "FOREIGN KEY(eid) REFERENCES Employees(eid)"  +
            ")";

        statement.executeUpdate(create);
    }

    static private void createEmployeesTable(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String create =
            "CREATE TABLE Employees(" +
            "eid INT AUTO_INCREMENT NOT NULL," +
            "FirstName VARCHAR(50), LastName VARCHAR(50)," +
            "Role VARCHAR(20) CHECK (Role = 'permanent' or Role = 'contract')," +
            "Category VARCHAR(20)  CHECK (Category = 'administrative' or Category = 'teaching')," +
            "Active BOOLEAN," +
            "Married BOOLEAN," +
            "NumOfChildren INT," +
            "Department VARCHAR(50)," +
            "Street VARCHAR(50)," +
            "StreetNumber INT," +
            "Bank VARCHAR(50)," +
            "IBAN VARCHAR(50)," +
            "PRIMARY KEY(eid) " +
            ")";

        statement.executeUpdate(create);
    }

    static private void createChildrenTable(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String create =
            "CREATE TABLE Children(" +
            "cid INT AUTO_INCREMENT," +
            "eid INT NOT NULL," +
            "FirstName VARCHAR(50), LastName VARCHAR(50)," +
            "Birthday DATE," +
            "Age INT," +
            "PRIMARY KEY(cid, eid)," +
            "FOREIGN KEY(eid) REFERENCES Employees(eid)" +
            ")";

        statement.executeUpdate(create);
    }

    static private void createContractsTable(Connection conn) throws SQLException {

        Statement statement = conn.createStatement();

        String create =
            "CREATE TABLE Contracts(" +
            "cid INT AUTO_INCREMENT," +
            "eid INT NOT NULL," +
            "StartOfContract DATE NOT NULL," +
            "EndOfContract DATE NOT NULL," +
            "PRIMARY KEY(cid, eid)," +
            "FOREIGN KEY(eid) REFERENCES Employees(eid)" +
            ")";

        statement.executeUpdate(create);
    }

    static private void createAllowancesTable(Connection conn) throws  SQLException {

        Statement statement = conn.createStatement();

        String create =
            "CREATE TABLE Allowances(" +
            "ResearchAllowance DOUBLE NOT NULL," +
            "LibraryAllowance DOUBLE NOT NULL," +
            "FamilyAllowancePercentage DOUBLE NOT NULL" +
            ")";

        statement.executeUpdate(create);
    }

    static private void createBasicSalariesTable(Connection conn) throws  SQLException {

        Statement statement = conn.createStatement();

        String create =
            "CREATE TABLE BasicSalaries(" +
            "BasicAdministrativeSalary DOUBLE NOT NULL," +
            "BasicTeachingSalary DOUBLE NOT NULL" +
            ")";

        statement.executeUpdate(create);
    }

    static private void createPaymentInfoTable(Connection conn) throws  SQLException {

        String create =
            "CREATE TABLE PaymentInfo(" +
            "eid INT NOT NULL," +
            "Salary DOUBLE(10, 2)," +
            "FamilyAllowance DOUBLE(10, 2)," +
            "TotalAllowance DOUBLE(10, 2)," +
            "TotalPayment DOUBLE(10, 2)," +
            "FOREIGN KEY(eid) REFERENCES Employees(eid)" +
            ")";

        Statement statement = conn.createStatement();

        statement.executeUpdate(create);
    }

    static private void createPaymentLogPermanentAdministrativeTable(Connection conn) throws  SQLException {

        String create =
            "CREATE TABLE PaymentLogPermanentAdministrative(" +
            "pid INT AUTO_INCREMENT," +
            "eid INT NOT NULL," +
            "Date DATE," +
            "Salary DOUBLE(10, 2)," +
            "FamilyAllowance DOUBLE(10, 2)," +
            "TotalAllowance DOUBLE(10, 2)," +
            "TotalPayment DOUBLE(10, 2)," +
            "Bank VARCHAR(50)," +
            "IBAN VARCHAR(50)," +
            "PRIMARY KEY(pid, eid)," +
            "FOREIGN KEY(eid) REFERENCES Employees(eid)" +
            ")";

        Statement statement = conn.createStatement();

        statement.executeUpdate(create);
    }

    static private void createPaymentLogContractAdministrativeTable(Connection conn) throws  SQLException {

        String create =
            "CREATE TABLE PaymentLogContractAdministrative(" +
            "pid INT AUTO_INCREMENT," +
            "eid INT NOT NULL," +
            "Date DATE," +
            "Salary DOUBLE," +
            "FamilyAllowance DOUBLE," +
            "TotalAllowance DOUBLE," +
            "TotalPayment DOUBLE," +
            "Bank VARCHAR(50)," +
            "IBAN VARCHAR(50)," +
            "PRIMARY KEY(pid, eid)," +
            "FOREIGN KEY(eid) REFERENCES Employees(eid)" +
            ")";

        Statement statement = conn.createStatement();

        statement.executeUpdate(create);
    }

    static private void createPaymentLogPermanentTeachingTable(Connection conn) throws  SQLException {

        String create =
            "CREATE TABLE PaymentLogPermanentTeaching(" +
            "pid INT AUTO_INCREMENT," +
            "eid INT NOT NULL," +
            "Date DATE," +
            "Salary DOUBLE," +
            "FamilyAllowance DOUBLE," +
            "ResearchAllowance DOUBLE," +
            "TotalAllowance DOUBLE," +
            "TotalPayment DOUBLE," +
            "Bank VARCHAR(50)," +
            "IBAN VARCHAR(50)," +
            "PRIMARY KEY(pid, eid)," +
            "FOREIGN KEY(eid) REFERENCES Employees(eid)" +
            ")";

        Statement statement = conn.createStatement();

        statement.executeUpdate(create);
    }

    static private void createPaymentLogContractTeachingTable(Connection conn) throws  SQLException {

        String create =
            "CREATE TABLE PaymentLogContractTeaching(" +
            "pid INT AUTO_INCREMENT," +
            "eid INT NOT NULL," +
            "Date DATE," +
            "Salary DOUBLE," +
            "FamilyAllowance DOUBLE," +
            "LibraryAllowance DOUBLE," +
            "TotalAllowance DOUBLE," +
            "TotalPayment DOUBLE," +
            "Bank VARCHAR(50)," +
            "IBAN VARCHAR(50)," +
            "PRIMARY KEY(pid, eid)," +
            "FOREIGN KEY(eid) REFERENCES Employees(eid)" +
            ")";

        Statement statement = conn.createStatement();

        statement.executeUpdate(create);
    }

}
