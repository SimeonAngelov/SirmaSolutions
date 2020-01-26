import model.EmployeeIdPair;
import services.EmployeeCooperationCalculator;
import services.FileParser;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            FileParser fileParser = new FileParser(Main.class.getClassLoader().getResource("sirmaTask.txt").getFile());
            String fileText = fileParser.replaceNullValues(fileParser.readFile());
            EmployeeCooperationCalculator employeeCooperationCalculator = new EmployeeCooperationCalculator(fileText);
            List<EmployeeIdPair> employeesWorkedLongestTogether = employeeCooperationCalculator.getEmployeesWorkedLongestTogether();

            System.out.println("Employees worked longest together are: ");
            for (int i = 0; i < employeesWorkedLongestTogether.size(); i++) {
                System.out.println(employeesWorkedLongestTogether.get(i).getId() + " and " + employeesWorkedLongestTogether.get(i).getColleagueId());
            }

        } catch (FileNotFoundException | ParseException e) {
            //TODO inform user about error occurred after UI is implemented
            e.printStackTrace();
        }
    }

}



