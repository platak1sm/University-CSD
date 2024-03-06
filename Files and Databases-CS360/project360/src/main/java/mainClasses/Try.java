package project360_try1;


import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;



public class Try {



    public static void main() {

        try {

            Connector.createConnection();
            Connection conn = Connector.getConnection();
            Statement statement = conn.createStatement();


            statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");

            statement.executeUpdate("DROP TABLE IF EXISTS PaymentLogContractTeaching");
            statement.executeUpdate("DROP TABLE IF EXISTS PaymentLogPermanentTeaching");
            statement.executeUpdate("DROP TABLE IF EXISTS PaymentLogContractAdministrative");
            statement.executeUpdate("DROP TABLE IF EXISTS PaymentLogPermanentAdministrative");
            statement.executeUpdate("DROP TABLE IF EXISTS PaymentInfo");
            statement.executeUpdate("DROP TABLE IF EXISTS Contracts");
            statement.executeUpdate("DROP TABLE IF EXISTS PermanentEmployees");
            statement.executeUpdate("DROP TABLE IF EXISTS ContractEmployees");
            statement.executeUpdate("DROP TABLE IF EXISTS Children");
            statement.executeUpdate("DROP TABLE IF EXISTS Employees");
            statement.executeUpdate("DROP TABLE IF EXISTS Allowances");
            statement.executeUpdate("DROP TABLE IF EXISTS BasicSalaries");

            statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");

            Creator.createTables(conn);
            Initializer.insertInitialValues(conn);
            Day.setToday();
            LocalDate today = Day.getToday();


            Processor.insertNewPermanentEmployee("mitso", "takis", "administrative",
                    false, "CS", "malakia", 69, "piraeus", "0003421534687158GR");

            Processor.insertNewContractEmployee("alexis", "tsipras", "teaching",
                    false, "English", "malakia", 69, "piraeus", "0003421534687158GR", "2023-02-28", 666);

            Processor.insertNewContractEmployee("akis", "kakakis", "administrative",
                    true, "English", "malakia", 69, "piraeus", "0003421534687158GR", "2023-03-31", 700);

            Processor.insertNewPermanentEmployee("fasaia", "gourmet edition", "administrative",
                    false, "CS", "kapou", 100, "piraeus", "43248324623463GR");


            Processor.addChild(1, "Adwnis", "georgiadhs", "2002-03-13");
            Processor.addChild(1, "mpampis", "takis", "2010-05-22");
            Processor.addChild(2, "mpampis2", "tsipras", "2013-07-12");



//            Processor.setFirstName(1, "Pantelis");
//            Processor.setLastName(1, "Pantelidhs");
//            Processor.setMarried(1, true);
//            Processor.setDepartment(1, "Biology");
//            Processor.setStreet(1, "Kalokairinou");
//            Processor.setStreetNumber(1, 62);
//            Processor.setBank(1, "Eurobank");
//            Processor.setIBAN(1, "549824432094203GR");
//
//            Processor.setBasicAdministrativeSalary(1700);
//            Processor.setBasicTeachingSalary(1300);
//
//            Processor.setResearchAllowance(540);
//            Processor.setLibraryAllowance(800);
//
//            Processor.setFamilyAllowancePercentage(0.10);


//            while(true) {
//
//                System.out.println(today);
//
//
//
//
//
//                today = Day.addDay();
//                TimeUnit.SECONDS.sleep(1);
//
//            }

            for (int i=0; i < 100; i++) {


                LocalDate lastDayOfMonth  = today.with(TemporalAdjusters.lastDayOfMonth());
                if (today.equals(lastDayOfMonth)){
                    Calculator.payEmployees();
                }

                if (today.toString().equals("2023-04-04")){
                    Processor.setLibraryAllowance(500);
                    Processor.setResearchAllowance(600);
                    Processor.setBasicTeachingSalary(3000);
                    Processor.setBasicAdministrativeSalary(2000);
                }



                today = Day.addDay();
//                TimeUnit.SECONDS.sleep(1);
            }


//            System.out.println(Processor.getAvgSalaryAndAllowanceIncrease("2023-02-26", "2023-05-03"));
//            System.out.println(Processor.getEmployeeInfo(1));

                Processor.getSumOfPayments();

//            System.out.println("reached end");

        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        catch (SQLException e) {
            System.out.println("Exception");
            while(e != null) {
                System.out.println("Message: " + e.getMessage());
                System.out.println("SQLState: " + e.getSQLState());
                System.out.println("ErrorCode: " + e.getErrorCode());
                e = e.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
