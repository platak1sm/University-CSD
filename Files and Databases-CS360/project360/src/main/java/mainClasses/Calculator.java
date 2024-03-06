package project360_try1;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class Calculator {

    // -----------------------------------------------------------------------------------------------------

    static public int calculateYearsOfEmployment(Connection conn, int eid) throws SQLException, ParseException {

//        LocalDate today = LocalDate.now();

        LocalDate today = Day.getToday();

        Statement statement = conn.createStatement();

        ResultSet result_set = statement.executeQuery("SELECT DateOfEmployment FROM PermanentEmployees WHERE eid = " + eid);
        String date = "";
        while (result_set.next()) {
            date = result_set.getString("DateOfEmployment");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date dayOfEmployment = sdf.parse(date);

        Period yearsOfEmployment = Period.between(dayOfEmployment.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), today);

        return yearsOfEmployment.getYears();

    }

    // -----------------------------------------------------------------------------------------------------

    static public int calculateUnderageChildren(Connection conn, int eid) throws SQLException {

        Statement statement = conn.createStatement();
        ResultSet result_set = statement.executeQuery("SELECT COUNT(cid) FROM Children WHERE Age < 18 AND eid = " + eid);
        result_set.next();

        return result_set.getInt(1);
    }

    static public int calculateNumOfChildren(Connection conn, int eid) throws SQLException {

        Statement statement = conn.createStatement();
        ResultSet result_set = statement.executeQuery("SELECT COUNT(cid) FROM Children WHERE eid = " + eid);
        result_set.next();

        return result_set.getInt(1);
    }

    // -----------------------------------------------------------------------------------------------------

    static public double findBasicSalary(Connection conn, int eid) throws SQLException {

        String category = Getter.getString(conn, eid, "Category", "Employees");
        double basic_salary = 0;

        if (category.equals("administrative"))
            basic_salary = Getter.getBasicAdministrativeSalary();
        else if (category.equals("teaching"))
            basic_salary = Getter.getBasicTeachingSalary();

        return basic_salary;
    }

    // -----------------------------------------------------------------------------------------------------


    static public double calculatePermanentEmployeeSalary(Connection conn, int eid) throws SQLException, ParseException {

        int yearsOfEmployment = Calculator.calculateYearsOfEmployment(conn, eid);

        double salary = findBasicSalary(conn, eid);

        if (yearsOfEmployment != 0) {
            for (int i = 0; i < yearsOfEmployment; i++) {
                salary = salary * 1.15;
            }
        }

        return salary;
    }


    static public double calculateFamilyAllowance(Connection conn, int eid) throws SQLException {

        double family_allowance_percentage = Getter.getFamilyAllowancePercentage();
        double family_allowance = 0;

        boolean married = Getter.getBoolean(conn, eid, "Married", "Employees");

        if (married) {

            double basic_salary = findBasicSalary(conn, eid);
            int numOfUnderageChildren = Calculator.calculateUnderageChildren(conn, eid);

            // salary increase for spouse + number of underage children
            family_allowance = family_allowance_percentage * basic_salary * (numOfUnderageChildren + 1);
        }

        return family_allowance;
    }

    static public double calculateTotalAllowance(Connection conn, int eid) throws SQLException {

        double total_allowance = Getter.getDouble(conn, eid, "FamilyAllowance", "PaymentInfo");

        String category = Getter.getString(conn, eid, "Category", "Employees");

        // administrative employees only have family allowance
        if (category.equals("administrative"))
            return total_allowance;

        String role = Getter.getString(conn, eid, "Role", "Employees");

        // teaching employees get extra allowances based on their categories
        if (role.equals("permanent"))
            total_allowance += Getter.getResearchAllowance();
        else if (role.equals("contract"))
            total_allowance += Getter.getLibraryAllowance();


        return total_allowance;
    }

    static public double calculateTotalPayment(Connection conn, int eid) throws SQLException {

        double salary  = Getter.getDouble(conn, eid, "Salary", "PaymentInfo");
        double total_allowances = Getter.getDouble(conn, eid, "TotalAllowance", "PaymentInfo");

        return salary + total_allowances;
    }

    static public int calculateAgeOfChild(Connection conn, int cid) throws SQLException, ParseException {

        LocalDate today = LocalDate.now();

        Statement statement = conn.createStatement();

        ResultSet result_set = statement.executeQuery("SELECT Birthday FROM Children WHERE cid = " + cid);
        String date = "";
        while (result_set.next()) {
            date = result_set.getString("Birthday");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date birthday = sdf.parse(date);

        Period age = Period.between(birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), today);

        return age.getYears();
    }

    // -----------------------------------------------------------------------------------------------------


    static public void payEmployees() throws SQLException, ParseException {

        Connection conn = Connector.getConnection();
        Statement statement = conn.createStatement();
        Statement s2 = conn.createStatement();

        String date = Day.getToday().toString();


        String role, category, bank, iban;
        double salary, family_allowance, total_allowance, total_payment;
        int eid;

        // find all active employees
        String query = "SELECT eid FROM Employees WHERE Active = true";
        ResultSet result_set = statement.executeQuery(query);
        while (result_set.next()) {

            eid = result_set.getInt(1);
            role = Getter.getString(conn, eid, "Role", "Employees");
            category = Getter.getString(conn, eid, "Category", "Employees");

            salary = Getter.getDouble(conn, eid, "Salary", "PaymentInfo");
            family_allowance = Getter.getDouble(conn, eid, "FamilyAllowance", "PaymentInfo");
            total_allowance = Getter.getDouble(conn, eid, "TotalAllowance", "PaymentInfo");
            total_payment = Getter.getDouble(conn, eid, "TotalPayment", "PaymentInfo");

            bank = Getter.getString(conn ,eid, "Bank", "Employees");
            iban = Getter.getString(conn ,eid, "IBAN", "Employees");


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            if (role.equals("permanent")) {

//                query = "SELECT DateOfEmployment FROM PermanentEmployees WHERE eid = " + eid;
//                ResultSet rs1 = s2.executeQuery(query);
//                rs1.next();
//                String dateStr = rs1.getString(1);

                String dateStr = Getter.getString(conn, eid, "DateOfEmployment", "PermanentEmployees");

                Date dateOfEmployment = sdf.parse(dateStr);
                java.util.Date today = sdf.parse(Day.getToday().toString());

                // if employee is hired but starts next month
                if(dateOfEmployment.after(today))
                    continue;

                if (category.equals("administrative"))
                    payPermanentAdministrativeEmployee(conn, eid, date, salary, family_allowance, total_allowance, total_payment, bank, iban);
                else if (category.equals("teaching"))
                    payPermanentTeachingEmployee(conn, eid, date, salary, family_allowance, total_allowance, total_payment, bank, iban);

//                query = "SELECT ToBeFiredOrRetired FROM PermanentEmployees WHERE eid = " + eid;
//                rs1.next();

                boolean toBeFiredOrRetired = Getter.getBoolean(conn, eid, "ToBeFiredOrRetired", "PermanentEmployees");
                // fire/retire employ
                if (toBeFiredOrRetired)
                    Setter.setActive(conn, eid, false);


            }
            else if(role.equals("contract")) {

                // find last contract of employee
                query = "SELECT MAX(cid) FROM Contracts WHERE eid = " + eid;
                ResultSet rs1 = s2.executeQuery(query);
                rs1.next();
                int last_contract = rs1.getInt(1);

                query = "SELECT StartOfContract, EndOfContract FROM Contracts WHERE eid = " + eid + " AND cid = " + last_contract;
                rs1 = s2.executeQuery(query);

                String dateStartStr="", dateEndStr="";
                while (rs1.next()) {
                    dateStartStr = rs1.getString(1);
                    dateEndStr = rs1.getString(2);
                }

                Date startOfContract = sdf.parse(dateStartStr);
                Date endOfContract = sdf.parse(dateEndStr);
                java.util.Date today = sdf.parse(Day.getToday().toString());


                // if employee has contract but starts next month
                if(startOfContract.after(today))
                    continue;

                // if contract expires today (and there is no future contract) deactivate employee
                if(endOfContract.equals(today))
                    Setter.setActive(conn, eid, false);


                if (category.equals("administrative"))
                    payContractAdministrativeEmployee(conn, eid, date, salary, family_allowance, total_allowance, total_payment, bank, iban);
                if (category.equals("teaching"))
                    payContractTeachingEmployee(conn, eid, date, salary, family_allowance, total_allowance, total_payment, bank, iban);

            }

        }
    }

    static public void payPermanentAdministrativeEmployee(Connection conn, int eid, String Date, double Salary,
                          double FamilyAllowance, double TotalAllowance, double TotalPayment, String Bank, String IBAN) throws SQLException {

        String insert = "INSERT INTO PaymentLogPermanentAdministrative(eid, Date, Salary, FamilyAllowance, TotalAllowance, TotalPayment, Bank, IBAN)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement prepared_statement = conn.prepareStatement(insert);

        prepared_statement.setInt(1, eid);
        prepared_statement.setString(2, Date);
        prepared_statement.setDouble(3, Salary);
        prepared_statement.setDouble(4, FamilyAllowance);
        prepared_statement.setDouble(5, TotalAllowance);
        prepared_statement.setDouble(6, TotalPayment);
        prepared_statement.setString(7, Bank);
        prepared_statement.setString(8, IBAN);


        prepared_statement.executeUpdate();

    }


    static public void payPermanentTeachingEmployee(Connection conn, int eid, String Date, double Salary,
                            double FamilyAllowance, double TotalAllowance, double TotalPayment, String Bank, String IBAN) throws SQLException {

        String insert = "INSERT INTO PaymentLogPermanentTeaching(eid, Date, Salary, FamilyAllowance, ResearchAllowance, TotalAllowance, TotalPayment, Bank, IBAN)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement prepared_statement = conn.prepareStatement(insert);

        prepared_statement.setInt(1, eid);
        prepared_statement.setString(2, Date);
        prepared_statement.setDouble(3, Salary);
        prepared_statement.setDouble(4, FamilyAllowance);
        prepared_statement.setDouble(5, Getter.getResearchAllowance());
        prepared_statement.setDouble(6, TotalAllowance);
        prepared_statement.setDouble(7, TotalPayment);
        prepared_statement.setString(8, Bank);
        prepared_statement.setString(9, IBAN);

        prepared_statement.executeUpdate();

    }

    static public void payContractAdministrativeEmployee(Connection conn, int eid, String Date, double Salary,
                            double FamilyAllowance, double TotalAllowance, double TotalPayment, String Bank, String IBAN) throws SQLException {

        String insert = "INSERT INTO PaymentLogContractAdministrative(eid, Date, Salary, FamilyAllowance, TotalAllowance, TotalPayment, Bank, IBAN)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement prepared_statement = conn.prepareStatement(insert);

        prepared_statement.setInt(1, eid);
        prepared_statement.setString(2, Date);
        prepared_statement.setDouble(3, Salary);
        prepared_statement.setDouble(4, FamilyAllowance);
        prepared_statement.setDouble(5, TotalAllowance);
        prepared_statement.setDouble(6, TotalPayment);
        prepared_statement.setString(7, Bank);
        prepared_statement.setString(8, IBAN);

        prepared_statement.executeUpdate();
    }

    static public void payContractTeachingEmployee(Connection conn, int eid, String Date, double Salary,
                           double FamilyAllowance, double TotalAllowance, double TotalPayment, String Bank, String IBAN) throws SQLException {

        String insert = "INSERT INTO PaymentLogContractTeaching(eid, Date, Salary, FamilyAllowance, LibraryAllowance, TotalAllowance, TotalPayment, Bank, IBAN)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement prepared_statement = conn.prepareStatement(insert);

        prepared_statement.setInt(1, eid);
        prepared_statement.setString(2, Date);
        prepared_statement.setDouble(3, Salary);
        prepared_statement.setDouble(4, FamilyAllowance);
        prepared_statement.setDouble(5, Getter.getLibraryAllowance());
        prepared_statement.setDouble(6, TotalAllowance);
        prepared_statement.setDouble(7, TotalPayment);
        prepared_statement.setString(8, Bank);
        prepared_statement.setString(9, IBAN);

        prepared_statement.executeUpdate();
    }



}