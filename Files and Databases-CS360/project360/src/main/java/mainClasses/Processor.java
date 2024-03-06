package project360_try1;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.time.ZoneId;

public class Processor {

    static public int findEidOfLastInsertedEmployee() throws SQLException {

        Connection conn = Connector.getConnection();

        Statement statement = conn.createStatement();
        statement.executeQuery("SELECT MAX(eid) FROM Employees");
        ResultSet result_set = statement.getResultSet();
        result_set.next();

        return result_set.getInt(1);
    }

    static private String findEmploymentDay() {

//         LocalDate today = LocalDate.now();
        LocalDate today = Day.getToday();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // first day of the month - employee starts working from today
        if (today.getDayOfMonth() == 1) {
            return today.format(formatter);
        }

        // not first day of the month - employee starts working from first of next month
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate firstDayOfNextMonth = nextMonth.with(TemporalAdjusters.firstDayOfMonth());

        return firstDayOfNextMonth.format(formatter);
    }

    // -------------------------------------------------------------------------------
    static public void insertNewPermanentEmployee(String FirstName, String LastName, String Category,
            boolean Married, String Department, String Street, int StreetNumber, String Bank, String IBAN) throws SQLException, ParseException {

        Connection conn = Connector.getConnection();

        String insert
                = "INSERT INTO Employees(FirstName, LastName, Role, Category, Active, Married, NumOfChildren, Department, "
                + " Street, StreetNumber, Bank, IBAN)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement prepared_statement = conn.prepareStatement(insert);

        prepared_statement.setString(1, FirstName);         // first name
        prepared_statement.setString(2, LastName);          // last name
        prepared_statement.setString(3, "permanent");    // role
        prepared_statement.setString(4, Category);          // category
        prepared_statement.setBoolean(5, true);          // active
        prepared_statement.setBoolean(6, Married);          // married
        prepared_statement.setInt(7, 0);                 // num of children
        prepared_statement.setString(8, Department);        // department
        prepared_statement.setString(9, Street);            // street
        prepared_statement.setInt(10, StreetNumber);        // street number
        prepared_statement.setString(11, Bank);             // bank
        prepared_statement.setString(12, IBAN);             // IBAN

        prepared_statement.executeUpdate();

        int eid = findEidOfLastInsertedEmployee();
        String dateOfEmployment = findEmploymentDay();

        insert
                = "INSERT INTO PermanentEmployees(eid, DateOfEmployment, ToBeFiredOrRetired) "
                + "VALUES ( " + eid + ", '" + dateOfEmployment + "', false )";

        Statement statement = conn.createStatement();
        statement.executeUpdate(insert);

        Setter.setYearsOfEmployment(conn, eid);
        Setter.insertPermanentEmployeeSalary(conn, eid);
        Setter.setFamilyAllowance(conn, eid);
        Setter.setTotalAllowance(conn, eid);
        Setter.setTotalPayment(conn, eid);

    }

    static public boolean insertNewContractEmployee(String FirstName, String LastName, String Category,
            boolean Married, String Department, String Street, int StreetNumber, String Bank, String IBAN, String endOfContract, double salary) throws SQLException, ParseException {

        Connection conn = Connector.getConnection();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        java.util.Date end = sdf.parse(endOfContract);

        LocalDate end_local = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end_last_day = end_local.with(TemporalAdjusters.lastDayOfMonth());

        // end of contract must be at the end of a month
        if (end_local != end_last_day) {
            return false;
        }

        String insert
                = "INSERT INTO Employees(FirstName, LastName, Role, Category, Active, Married, NumOfChildren, Department, "
                + " Street, StreetNumber, Bank, IBAN)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement prepared_statement = conn.prepareStatement(insert);

        prepared_statement.setString(1, FirstName);         // first name
        prepared_statement.setString(2, LastName);          // last name
        prepared_statement.setString(3, "contract");    // role
        prepared_statement.setString(4, Category);          // category
        prepared_statement.setBoolean(5, true);          // active
        prepared_statement.setBoolean(6, Married);          // married
        prepared_statement.setInt(7, 0);                 // num of children
        prepared_statement.setString(8, Department);        // department
        prepared_statement.setString(9, Street);            // street
        prepared_statement.setInt(10, StreetNumber);        // street number
        prepared_statement.setString(11, Bank);             // bank
        prepared_statement.setString(12, IBAN);             // IBAN

        prepared_statement.executeUpdate();

        int eid = findEidOfLastInsertedEmployee();
        String startOfContract = findEmploymentDay();

        Statement statement = conn.createStatement();

        insert = "INSERT INTO ContractEmployees(eid) VALUES (" + eid + ")";
        statement.executeUpdate(insert);

        insert = "INSERT INTO Contracts(eid, StartOfContract, EndOfContract) "
                + "VALUES ( " + eid + ",'" + startOfContract + "','" + endOfContract + "')";
        statement.executeUpdate(insert);

        Setter.insertContractEmployeeSalary(conn, eid, salary);
        Setter.setFamilyAllowance(conn, eid);
        Setter.setTotalAllowance(conn, eid);
        Setter.setTotalPayment(conn, eid);

        return true;
    }

    static public void addChild(int eid, String FirstName, String LastName, String Birthday) throws SQLException, ParseException {

        Connection conn = Connector.getConnection();

        String insert
                = "INSERT INTO Children(eid, FirstName, LastName, Birthday)"
                + " VALUES (?, ?, ?, ?)";

        PreparedStatement prepared_statement = conn.prepareStatement(insert);
        prepared_statement.setInt(1, eid);              // eid of parent employee
        prepared_statement.setString(2, FirstName);     // first name
        prepared_statement.setString(3, LastName);      // last name
        prepared_statement.setString(4, Birthday);      // birthday
        prepared_statement.executeUpdate();

        Statement statement = conn.createStatement();
        statement.executeQuery("SELECT MAX(cid) FROM Children");
        ResultSet result_set = statement.getResultSet();
        result_set.next();

        int cid = result_set.getInt(1);

        Setter.setChildAge(conn, cid);
        Setter.increaseNumOfChildren(conn, eid);
        Setter.setFamilyAllowance(conn, eid);
        Setter.setTotalAllowance(conn, eid);
        Setter.setTotalPayment(conn, eid);

    }

    // -------------------------------------------------------------------------------
    static public void setFirstName(int eid, String FirstName) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        String set = "UPDATE Employees SET FirstName = ' " + FirstName + " ' WHERE eid = " + eid;
        statement.executeUpdate(set);
    }

    static public void setLastName(int eid, String LastName) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        String set = "UPDATE Employees SET LastName = ' " + LastName + " ' WHERE eid = " + eid;
        statement.executeUpdate(set);
    }

    static public void setMarried(int eid, boolean Married) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        String set = "UPDATE Employees SET Married = " + Married + " WHERE eid = " + eid;
        statement.executeUpdate(set);

        // recalculate family allowance based on new marital status
        Setter.setFamilyAllowance(conn, eid);
        Setter.setTotalAllowance(conn, eid);
        Setter.setTotalPayment(conn, eid);
    }

    static public void setDepartment(int eid, String Department) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        String set = "UPDATE Employees SET Department = ' " + Department + " ' WHERE eid = " + eid;
        statement.executeUpdate(set);
    }

    static public void setStreet(int eid, String Street) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        String set = "UPDATE Employees SET Street = ' " + Street + " ' WHERE eid = " + eid;
        statement.executeUpdate(set);
    }

    static public void setStreetNumber(int eid, int StreetNumber) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        String set = "UPDATE Employees SET StreetNumber = " + StreetNumber + " WHERE eid = " + eid;
        statement.executeUpdate(set);
    }

    static public void setBank(int eid, String Bank) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        String set = "UPDATE Employees SET Bank = ' " + Bank + " ' WHERE eid = " + eid;
        statement.executeUpdate(set);
    }

    static public void setIBAN(int eid, String IBAN) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        String set = "UPDATE Employees SET IBAN = ' " + IBAN + " ' WHERE eid = " + eid;
        statement.executeUpdate(set);
    }

    // -------------------------------------------------------------------------------
    static public boolean setBasicAdministrativeSalary(double BasicAdministrativeSalary) throws SQLException, ParseException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();

        String query = "SELECT BasicAdministrativeSalary FROM BasicSalaries";
        ResultSet result_set = statement.executeQuery(query);
        result_set.next();
        double currentBasicSalary = result_set.getDouble(1);
        if (currentBasicSalary >= BasicAdministrativeSalary) {
            return false;
        }

        String set = "UPDATE BasicSalaries SET BasicAdministrativeSalary = " + BasicAdministrativeSalary;
        statement.executeUpdate(set);

        // recalculate salary for permanent administrative employees
        query = "SELECT eid FROM Employees WHERE Role = 'permanent' AND Category = 'administrative' ";
        result_set = statement.executeQuery(query);
        while (result_set.next()) {
            int eid = result_set.getInt(1);
            Setter.setPermanentEmployeeSalary(conn, eid);
            Setter.setTotalPayment(conn, eid);
        }

        return true;

    }

    static public boolean setBasicTeachingSalary(double BasicTeachingSalary) throws SQLException, ParseException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();

        String query = "SELECT BasicTeachingSalary FROM BasicSalaries";
        ResultSet result_set = statement.executeQuery(query);
        result_set.next();
        double currentBasicSalary = result_set.getDouble(1);
        if (currentBasicSalary >= BasicTeachingSalary) {
            return false;
        }

        String set = "UPDATE BasicSalaries SET BasicTeachingSalary = " + BasicTeachingSalary;
        statement.executeUpdate(set);

        // recalculate salary for permanent teaching employees
        query = "SELECT eid FROM Employees WHERE Role = 'permanent' AND Category = 'teaching' ";
        result_set = statement.executeQuery(query);
        while (result_set.next()) {
            int eid = result_set.getInt("eid");
            Setter.setPermanentEmployeeSalary(conn, eid);
            Setter.setTotalPayment(conn, eid);
        }

        return true;

    }

    static public boolean setResearchAllowance(double ResearchAllowance) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();

        String query = "SELECT ResearchAllowance FROM Allowances";
        ResultSet result_set = statement.executeQuery(query);
        result_set.next();
        double currentAllowance = result_set.getDouble(1);
        if (currentAllowance >= ResearchAllowance) {
            return false;
        }

        String set = "UPDATE Allowances SET ResearchAllowance = " + ResearchAllowance;
        statement.executeUpdate(set);

        // recalculate allowance for permanent teaching employees
        query = "SELECT eid FROM Employees WHERE Role = 'permanent' AND Category = 'teaching' ";
        result_set = statement.executeQuery(query);
        while (result_set.next()) {
            int eid = result_set.getInt("eid");
            Setter.setTotalAllowance(conn, eid);
            Setter.setTotalPayment(conn, eid);
        }

        return true;

    }

    static public boolean setLibraryAllowance(double LibraryAllowance) throws SQLException, ParseException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();

        String query = "SELECT LibraryAllowance FROM Allowances";
        ResultSet result_set = statement.executeQuery(query);
        result_set.next();
        double currentAllowance = result_set.getDouble(1);
        if (currentAllowance >= LibraryAllowance) {
            return false;
        }

        String set = "UPDATE Allowances SET LibraryAllowance = " + LibraryAllowance;
        statement.executeUpdate(set);

        // recalculate allowance for contract teaching employees
        query = "SELECT eid FROM Employees WHERE Role = 'contract' AND Category = 'teaching' ";
        result_set = statement.executeQuery(query);
        while (result_set.next()) {
            int eid = result_set.getInt("eid");
            Setter.setTotalAllowance(conn, eid);
            Setter.setTotalPayment(conn, eid);
        }

        return true;
    }

    static public boolean setFamilyAllowancePercentage(double FamilyAllowancePercentage) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();

        String query = "SELECT FamilyAllowancePercentage FROM Allowances";
        ResultSet result_set = statement.executeQuery(query);
        result_set.next();
        double currentAllowance = result_set.getDouble(1);
        if (currentAllowance >= FamilyAllowancePercentage) {
            return false;
        }

        String set = "UPDATE Allowances SET FamilyAllowancePercentage = " + FamilyAllowancePercentage;
        statement.executeUpdate(set);

        // recalculate allowance all employees
        query = "SELECT eid FROM Employees";
        result_set = statement.executeQuery(query);
        while (result_set.next()) {
            int eid = result_set.getInt("eid");
            Setter.setFamilyAllowance(conn, eid);
            Setter.setTotalAllowance(conn, eid);
            Setter.setTotalPayment(conn, eid);
        }

        return true;
    }

    // -------------------------------------------------------------------------------
    static public boolean renewContract(int eid, String newEndOfContract, double newSalary) throws SQLException, ParseException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();

        String query = "SELECT eid FROM ContractEmployees WHERE eid = " + eid;
        ResultSet result_set = statement.executeQuery(query);
        if (!result_set.next()) {
            return false;           // employee not in database
        }
        query = "SELECT MAX(cid) FROM Contracts WHERE eid = " + eid;
        result_set = statement.executeQuery(query);
        result_set.next();
        int last_contract = result_set.getInt(1);

        query = "SELECT EndOfContract FROM Contracts WHERE eid = " + eid + " AND cid = " + last_contract;
        result_set = statement.executeQuery(query);
        result_set.next();
        String currentEndOfContract = result_set.getString(1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        java.util.Date current_end = sdf.parse(currentEndOfContract);
        java.util.Date new_end = sdf.parse(newEndOfContract);

        java.util.Date today = Date.from(Day.getToday().atStartOfDay(ZoneId.systemDefault()).toInstant());

        // employee already has an active contract
        if (current_end.after(today)) {
            return false;
        }

        if (current_end.after(new_end)) {
            return false;
        }

        LocalDate new_end_local = new_end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate new_end_last_day = new_end_local.with(TemporalAdjusters.lastDayOfMonth());

        // end of contract must be at the end of a month
        if (new_end_local != new_end_last_day) {
            return false;
        }

        String insert = "INSERT INTO Contracts(eid, StartOfContract, EndOfContract) VALUES (?, ?, ?)";
        PreparedStatement prepared_statement = conn.prepareStatement(insert);
        prepared_statement.setInt(1, eid);
        prepared_statement.setString(2, findEmploymentDay());
        prepared_statement.setString(3, newEndOfContract);
        prepared_statement.executeUpdate();

        Setter.setContractEmployeeSalary(conn, eid, newSalary);
        Setter.setActive(conn, eid, true);

        return true;
    }

    static public boolean fireOrRetirePermanentEmployee(int eid) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        LocalDate today = Day.getToday();
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());

        // firing/retiring takes place at the end of a month
        if (!today.equals(lastDayOfMonth)) {
            return false;
        }

        String query = "SELECT eid FROM PermanentEmployees WHERE eid = " + eid;
        ResultSet result_set = statement.executeQuery(query);
        if (!result_set.next()) {
            return false;           // employee not in database
        }
        // employee is already fired/retired
        boolean isActive = Getter.getBoolean(conn, eid, "Active", "Employees");
        if (!isActive) {
            return false;
        }

        String set
                = "UPDATE PermanentEmployees"
                + " SET ToBeFiredOrRetired = " + true
                + " WHERE eid = " + eid;

        statement.executeUpdate(set);

        return true;

    }

    // -------------------------------------------------------------------------------
    static public String getPaymentLogs() throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();

        String logs = "";

        String query = "SELECT * FROM PaymentLogPermanentAdministrative";
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        logs += "** Payment Log - Permanent Administrative **\n\n";
        logs += "pid | eid | Date | Salary | Family Allowance | Total Allowance | Total Payment | Bank | IBAN\n";

        // Iterate through the data in the result set and display it.
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {

                logs += rs.getString(i) + " | ";
            }
            logs += "\n";
        }

        query = "SELECT * FROM PaymentLogPermanentTeaching";
        rs = statement.executeQuery(query);
        rsmd = rs.getMetaData();
        columnsNumber = rsmd.getColumnCount();

        logs += "\n\n** Payment Log - Permanent Teaching **\n\n";
        logs += "pid | eid | Date | Salary | Family Allowance | Research Allowance | Total Allowance | Total Payment | Bank | IBAN\n";

        // Iterate through the data in the result set and display it.
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {

                logs += rs.getString(i) + " | ";
            }
            logs += "\n";
        }

        query = "SELECT * FROM PaymentLogContractAdministrative";
        rs = statement.executeQuery(query);
        rsmd = rs.getMetaData();
        columnsNumber = rsmd.getColumnCount();

        logs += "\n\n** Payment Log - Contract Administrative **\n\n";
        logs += "pid | eid | Date | Salary | Family Allowance | Total Allowance | Total Payment | Bank | IBAN\n";

        // Iterate through the data in the result set and display it.
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {

                logs += rs.getString(i) + " | ";
            }
            logs += "\n";
        }

        query = "SELECT * FROM PaymentLogContractTeaching";
        rs = statement.executeQuery(query);
        rsmd = rs.getMetaData();
        columnsNumber = rsmd.getColumnCount();

        logs += "\n\n** Payment Log - Contract Teaching **\n\n";
        logs += "pid | eid | Date | Salary | Family Allowance | Library Allowance | Total Allowance | Total Payment | Bank | IBAN\n";

        // Iterate through the data in the result set and display it.
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {

                logs += rs.getString(i) + " | ";
            }
            logs += "\n";
        }

        return logs;

    }

    static public String getMaxMinAvgSalary() throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();

        String str = "";

        String query = "SELECT MAX(Salary), MIN(Salary), AVG(Salary) FROM PaymentInfo WHERE eid IN (SELECT eid FROM EMPLOYEES WHERE Role = 'permanent' AND Category = 'administrative')";
        ResultSet result_set = statement.executeQuery(query);

        str += "Permanent Administrative Employees\n";
        while (result_set.next()) {

            double max = result_set.getDouble(1);
            double min = result_set.getDouble(2);
            double avg = result_set.getDouble(3);

            str += "MAX: " + max + " MIN: " + min + " AVERAGE: " + avg + "\n\n";
        }

        query = "SELECT MAX(Salary), MIN(Salary), AVG(Salary) FROM PaymentInfo WHERE eid IN (SELECT eid FROM EMPLOYEES WHERE Role = 'permanent' AND Category = 'teaching')";
        result_set = statement.executeQuery(query);

        str += "Permanent Teaching Employees\n";
        while (result_set.next()) {

            double max = result_set.getDouble(1);
            double min = result_set.getDouble(2);
            double avg = result_set.getDouble(3);

            str += "MAX: " + max + " MIN: " + min + " AVERAGE: " + avg + "\n\n";
        }

        query = "SELECT MAX(Salary), MIN(Salary), AVG(Salary) FROM PaymentInfo WHERE eid IN (SELECT eid FROM EMPLOYEES WHERE Role = 'contract' AND Category = 'administrative')";
        result_set = statement.executeQuery(query);

        str += "Contract Administrative Employees\n";
        while (result_set.next()) {

            double max = result_set.getDouble(1);
            double min = result_set.getDouble(2);
            double avg = result_set.getDouble(3);

            str += "MAX: " + max + " MIN: " + min + " AVERAGE: " + avg + "\n\n";
        }

        query = "SELECT MAX(Salary), MIN(Salary), AVG(Salary) FROM PaymentInfo WHERE eid IN (SELECT eid FROM EMPLOYEES WHERE Role = 'contract' AND Category = 'teaching')";
        result_set = statement.executeQuery(query);

        str += "Contract Teaching Employees\n";
        while (result_set.next()) {

            double max = result_set.getDouble(1);
            double min = result_set.getDouble(2);
            double avg = result_set.getDouble(3);

            str += "MAX: " + max + " MIN: " + min + " AVERAGE: " + avg + "\n\n";
        }

        return str;
    }

    static public String getAvgSalaryAndAllowanceIncrease(String startDayStr, String endDayStr) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        Statement s1 = conn.createStatement();
        Statement s2 = conn.createStatement();

        String str = "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDay = LocalDate.parse(startDayStr, formatter);
        LocalDate endDay = LocalDate.parse(endDayStr, formatter);

        // payments happen at end of month
        if (startDay != startDay.with(TemporalAdjusters.lastDayOfMonth())) {
            startDay = startDay.with(TemporalAdjusters.lastDayOfMonth());
        }

        if (endDay != endDay.with(TemporalAdjusters.lastDayOfMonth())) {
            endDay = endDay.minusMonths(1);
            endDay = endDay.with(TemporalAdjusters.lastDayOfMonth());
        }

        System.out.println("start day" + startDay);
        System.out.println("end day" + endDay);

        double sum_salary_start = 0;
        double sum_salary_end = 0;
        double sum_allowances_start = 0;
        double sum_allowances_end = 0;

        double salary_start = 0;
        double allowance_start = 0;
        double salary_end = 0;
        double allowance_end = 0;

        double sum_salary_rate = 0;
        double sum_allowance_rate = 0;

        int count = 0;

        // Permanent Administrative
        String query = "SELECT eid FROM PaymentLogPermanentAdministrative";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {

            System.out.println("inside while pa");

            int eid = resultSet.getInt(1);

            query = "SELECT Salary, TotalAllowance FROM PaymentLogPermanentAdministrative"
                    + " WHERE eid = " + eid + " AND Date = '" + startDay + "'";
            ResultSet rs1 = s1.executeQuery(query);
            while (rs1.next()) {
                salary_start = rs1.getDouble(1);
                allowance_start = rs1.getDouble(2);
            }

            String query2 = "SELECT Salary, TotalAllowance FROM PaymentLogPermanentAdministrative"
                    + " WHERE eid = " + eid + " AND Date = '" + endDay + "'";
            ResultSet rs2 = s2.executeQuery(query2);
            while (rs2.next()) {

                System.out.println("inside while q2");

                salary_end = rs2.getDouble(1);
                allowance_end = rs2.getDouble(2);

                System.out.println(salary_end + "se" + allowance_end + "ae");
            }

            if (salary_end == 0) {
                continue;
            }
            count++;

            if (allowance_end == 0) {
                continue;
            }

            sum_salary_rate += ((salary_end - salary_start) / salary_start) * 100;
            sum_allowance_rate += ((allowance_end - allowance_start) / allowance_start) * 100;

        }

        System.out.println("count : " + count);
        System.out.println("sum salary rate: " + sum_salary_rate);
        System.out.println("sum allowance rate: " + sum_allowance_rate);

        // Permanent Teaching
        query = "SELECT eid FROM PaymentLogPermanentAdministrative";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {

            int eid = resultSet.getInt(1);

            query = "SELECT Salary, TotalAllowance FROM PaymentLogPermanentTeaching"
                    + " WHERE eid = " + eid + " AND  Date = '" + startDay + "'";
            ResultSet rs1 = s1.executeQuery(query);
            while (rs1.next()) {
                salary_start = rs1.getDouble(1);
                allowance_start = rs1.getDouble(2);
            }

            query = "SELECT Salary, TotalAllowance FROM PaymentLogPermanentTeaching"
                    + " WHERE eid = " + eid + " AND  Date = '" + endDay + "'";
            ResultSet rs2 = s2.executeQuery(query);
            while (rs2.next()) {
                salary_end = rs2.getDouble(1);
                allowance_end = rs2.getDouble(2);
            }

            if (salary_end == 0) {
                System.out.println("no salary");
                continue;
            }

            count++;
            sum_salary_rate += ((salary_end - salary_start) / salary_start) * 100;
            sum_allowance_rate += ((allowance_end - allowance_start) / allowance_start) * 100;

        }

        System.out.println("count : " + count);
        System.out.println("sum salary rate: " + sum_salary_rate);
        System.out.println("sum allowance rate: " + sum_allowance_rate);

        // Contract Administrative
        query = "SELECT eid FROM PaymentLogContractAdministrative";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {

            int eid = resultSet.getInt(1);

            query = "SELECT Salary, TotalAllowance FROM PaymentLogContractAdministrative"
                    + " WHERE eid = " + eid + " AND Date = '" + startDay + "'";
            ResultSet rs1 = s1.executeQuery(query);
            while (rs1.next()) {
                salary_start = rs1.getDouble(1);

                allowance_start = rs1.getDouble(2);
            }

            query = "SELECT Salary, TotalAllowance FROM PaymentLogContractAdministrative"
                    + " WHERE eid = " + eid + " AND Date = '" + endDay + "'";
            ResultSet rs2 = s2.executeQuery(query);
            while (rs2.next()) {
                salary_end = rs2.getDouble(1);
                System.out.println(salary_end + " dandjafjdahfjkdsfbzdhkfdshkfhsdfshf");

                allowance_end = rs2.getDouble(2);
            }

            if (salary_end == 0) {
                continue;
            }

            count++;
            sum_salary_rate += ((salary_end - salary_start) / salary_start) * 100;
            sum_allowance_rate += ((allowance_end - allowance_start) / allowance_start) * 100;

        }

        System.out.println("count : " + count);
        System.out.println("sum salary rate: " + sum_salary_rate);
        System.out.println("sum allowance rate: " + sum_allowance_rate);

        // Contract Teaching
        query = "SELECT eid FROM PaymentLogContractTeaching";
        resultSet = statement.executeQuery(query);

        while (resultSet.next()) {

            int eid = resultSet.getInt(1);

            query = "SELECT Salary, TotalAllowance FROM PaymentLogContractTeaching"
                    + " WHERE eid = " + eid + " AND Date = '" + startDay + "'";
            ResultSet rs1 = s1.executeQuery(query);
            while (rs1.next()) {
                salary_start = rs1.getDouble(1);
                allowance_start = rs1.getDouble(2);
            }

            query = "SELECT Salary, TotalAllowance FROM PaymentLogContractTeaching"
                    + " WHERE eid = " + eid + " AND Date = '" + endDay + "'";
            ResultSet rs2 = s2.executeQuery(query);
            while (rs2.next()) {
                salary_end = rs2.getDouble(1);
                allowance_end = rs2.getDouble(2);
            }

            if (salary_end == 0) {
                continue;
            }

            count++;
            sum_salary_rate += ((salary_end - salary_start) / salary_start) * 100;
            sum_allowance_rate += ((allowance_end - allowance_start) / allowance_start) * 100;

        }

        System.out.println("count : " + count);
        System.out.println("sum salary rate: " + sum_salary_rate);
        System.out.println("sum allowance rate: " + sum_allowance_rate);

        double increase_rate_salary = 0;
        double increase_rate_allowances = 0;

        if (count != 0) {
            increase_rate_salary = (sum_salary_rate - sum_salary_start) / count * 100;
            increase_rate_allowances = (sum_allowances_end - sum_allowances_start) / count * 100;
        }

        str += "Average Salary Rate Increase: " + increase_rate_salary + "\n";
        str += "Average Allowance Rate Increase: " + increase_rate_allowances + "\n";

//        String query = "SELECT SUM(Salary), SUM(TotalAllowance) FROM PaymentLogPermanentAdministrative WHERE Date = '" + startDay + "'";
//        ResultSet rs = statement.executeQuery(query);
//        while(rs.next()) {
//            sum_salary_start += rs.getDouble(1);
//            sum_allowances_start += rs.getDouble(2);
//        }
//
//        query = "SELECT SUM(Salary), SUM(TotalAllowance) FROM PaymentLogPermanentTeaching WHERE Date = '" + startDay + "'";
//        rs = statement.executeQuery(query);
//        while(rs.next()) {
//            sum_salary_start += rs.getDouble(1);
//            sum_allowances_start += rs.getDouble(2);
//        }
//
//        query = "SELECT SUM(Salary), SUM(TotalAllowance) FROM PaymentLogContractAdministrative WHERE Date = '" + startDay + "'";
//        rs = statement.executeQuery(query);
//        while(rs.next()) {
//            sum_salary_start += rs.getDouble(1);
//            sum_allowances_start += rs.getDouble(2);
//        }
//
//
//        query = "SELECT SUM(Salary), SUM(TotalAllowance) FROM PaymentLogContractTeaching WHERE Date = '" + startDay + "'";
//        rs = statement.executeQuery(query);
//        while(rs.next()) {
//            sum_salary_start += rs.getDouble(1);
//            sum_allowances_start += rs.getDouble(2);
//        }
//
//        // -----
//
//        query = "SELECT SUM(Salary), SUM(TotalAllowance) FROM PaymentLogPermanentAdministrative WHERE Date = '" + endDay + "'";
//        rs = statement.executeQuery(query);
//        while(rs.next()) {
//            sum_salary_end += rs.getDouble(1);
//            sum_allowances_end += rs.getDouble(2);
//        }
//
//        query = "SELECT SUM(Salary), SUM(TotalAllowance) FROM PaymentLogPermanentTeaching WHERE Date = '" + endDay + "'";
//        rs = statement.executeQuery(query);
//        while(rs.next()) {
//            sum_salary_end += rs.getDouble(1);
//            sum_allowances_end += rs.getDouble(2);
//        }
//
//        query = "SELECT SUM(Salary), SUM(TotalAllowance) FROM PaymentLogContractAdministrative WHERE Date = '" + endDay + "'";
//        rs = statement.executeQuery(query);
//        while(rs.next()) {
//            sum_salary_end += rs.getDouble(1);
//            sum_allowances_end += rs.getDouble(2);
//        }
//
//        query = "SELECT SUM(Salary), SUM(TotalAllowance) FROM PaymentLogContractTeaching WHERE Date = '" + endDay + "'";
//        rs = statement.executeQuery(query);
//        while(rs.next()) {
//            sum_salary_end += rs.getDouble(1);
//            sum_allowances_end += rs.getDouble(2);
//        }
//
//        System.out.println("sum_salary_start " + sum_salary_start);
//        System.out.println("sum_allowances_start " + sum_allowances_start);
//        System.out.println("sum_salary_end " + sum_salary_end);
//        System.out.println("sum_allowances_end " + sum_allowances_end);
//
//        double increase_rate_salary = (sum_salary_end - sum_salary_start) / sum_salary_start * 100;
//        double increase_rate_allowances = (sum_allowances_end - sum_allowances_start) / sum_allowances_start * 100;
//
//        str += "Average Salary Rate Increase: " + increase_rate_salary + "\n";
//        str += "Average Allowance Rate Increase: " + increase_rate_allowances + "\n";
        return str;
    }

    static public String getEmployeeInfo(int eid) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();

        String str = "";

        str += "Personal Info \n\n";

        str += "eid: " + eid + "\n";
        str += "First Name: " + Getter.getString(conn, eid, "FirstName", "Employees") + "\n";
        str += "Last Name: " + Getter.getString(conn, eid, "LastName", "Employees") + "\n";
        str += "Role: " + Getter.getString(conn, eid, "Role", "Employees") + "\n";
        str += "Category: " + Getter.getString(conn, eid, "Category", "Employees") + "\n";
        String married = Getter.getBoolean(conn, eid, "Married", "Employees") ? "Married" : "Unmarried";
        str += "Married: " + married + "\n";
        str += "Number Of Children: " + Getter.getInt(conn, eid, "NumOfChildren", "Employees") + "\n";
        str += "Department: " + Getter.getString(conn, eid, "Department", "Employees") + "\n";
        str += "Street: " + Getter.getString(conn, eid, "Street", "Employees") + "\n";
        str += "StreetNumber: " + Getter.getInt(conn, eid, "StreetNumber", "Employees") + "\n";
        str += "Bank: " + Getter.getString(conn, eid, "Bank", "Employees") + "\n";
        str += "IBAN: " + Getter.getString(conn, eid, "IBAN", "Employees") + "\n";

        str += "\n\nPayment Info \n\n";

        str += "Salary: " + Getter.getDouble(conn, eid, "Salary", "PaymentInfo") + "\n";
        str += "Family Allowance: " + Getter.getDouble(conn, eid, "FamilyAllowance", "PaymentInfo") + "\n";
        str += "Total Allowance: " + Getter.getDouble(conn, eid, "TotalAllowance", "PaymentInfo") + "\n";
        str += "Total Payment: " + Getter.getDouble(conn, eid, "TotalPayment", "PaymentInfo") + "\n";

        return str;

    }

    static public String getSumOfPayments() throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();

        String str = "";

        double sum_pa = 0;
        double sum_pt = 0;
        double sum_ca = 0;
        double sum_ct = 0;

        String query = "SELECT SUM(Salary), SUM(TotalAllowance), SUM(TotalPayment) FROM PaymentInfo WHERE eid IN "
                + "(SELECT eid FROM Employees WHERE Role = 'permanent' AND Category = 'administrative')";
        ResultSet rs = statement.executeQuery(query);

        str += "Permanent Administrative\n\n";
        while (rs.next()) {
            str += "Sum Of Salaries: " + rs.getDouble(1) + "\n";
            str += "Sum Of Total Allowances: " + rs.getDouble(2) + "\n";
            str += "Sum Of Total Payments: " + rs.getDouble(3) + "\n\n";
        }

        query = "SELECT SUM(Salary), SUM(TotalAllowance), SUM(TotalPayment) FROM PaymentInfo WHERE eid IN "
                + "(SELECT eid FROM Employees WHERE Role = 'permanent' AND Category = 'teaching')";
        rs = statement.executeQuery(query);

        str += "Permanent teaching\n\n";
        while (rs.next()) {
            str += "Sum Of Salaries: " + rs.getDouble(1) + "\n";
            str += "Sum Of Total Allowances: " + rs.getDouble(2) + "\n";
            str += "Sum Of Total Payments: " + rs.getDouble(3) + "\n\n";
        }

        query = "SELECT SUM(Salary), SUM(TotalAllowance), SUM(TotalPayment) FROM PaymentInfo WHERE eid IN "
                + "(SELECT eid FROM Employees WHERE Role = 'contract' AND Category = 'administrative')";
        rs = statement.executeQuery(query);

        str += "Contract Administrative\n\n";
        while (rs.next()) {
            str += "Sum Of Salaries: " + rs.getDouble(1) + "\n";
            str += "Sum Of Total Allowances: " + rs.getDouble(2) + "\n";
            str += "Sum Of Total Payments: " + rs.getDouble(3) + "\n\n";
        }

        query = "SELECT SUM(Salary), SUM(TotalAllowance), SUM(TotalPayment) FROM PaymentInfo WHERE eid IN "
                + "(SELECT eid FROM Employees WHERE Role = 'contract' AND Category = 'teaching')";
        rs = statement.executeQuery(query);

        str += "Contract Teaching\n\n";
        while (rs.next()) {
            str += "Sum Of Salaries: " + rs.getDouble(1) + "\n";
            str += "Sum Of Total Allowances: " + rs.getDouble(2) + "\n";
            str += "Sum Of Total Payments: " + rs.getDouble(3) + "\n\n";
        }

        return str;
    }

    static public String submitSQLQuery(String query) throws SQLException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        String result = "";

        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs.next()) {

            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = rs.getString(i);
                result += rsmd.getColumnName(i) + ": " + columnValue + " ";
            }
            result += "\n";
        }

        return result;

    }

}
