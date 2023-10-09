import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static HashMap<EmployeePair, Long> longestPair(File file) {
        List<Employee> employees = new ArrayList<>();
        Map<EmployeePair, Long> tempMap = new HashMap<>();
        EmployeePair finalPair = null;
        long tempPeriod = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",\\s*");
                LocalDate dateTo = row[3].equals("NULL") ? LocalDate.now() : LocalDate.parse(row[3]);
                Employee tempEmployee = new Employee(Integer.parseInt(row[0]),
                        Integer.parseInt(row[1]), LocalDate.parse(row[2]), dateTo);
                employees.add(tempEmployee);
            }

            for (int i = 0; i < employees.size(); i++) {
                for (int j = i + 1; j < employees.size(); j++) {
                    Employee e1 = employees.get(i);
                    Employee e2 = employees.get(j);
                    if (e1.getProjectId() == e2.getProjectId() && e1.getId() != e2.getId()) {
                        long commonTime = e1.commonWorkDays(e2);
                        EmployeePair tempPair = new EmployeePair(e1, e2);
                        if (tempMap.containsKey(tempPair)) {
                            tempMap.put(tempPair, tempMap.get(tempPair) + commonTime);
                        } else {
                            tempMap.put(tempPair, commonTime);
                        }
                    }
                }
            }

            for (var pair : tempMap.keySet()) {
                long currentPeriod = tempMap.get(pair);

                if (currentPeriod > tempPeriod) {
                    finalPair = pair;
                    tempPeriod = currentPeriod;
                }
            }

            System.out.println("Employee " + finalPair.getEmp1().getId() + " and employee "
                    + finalPair.getEmp2().getId() + " have have worked together on common projects"
                    + " for the longest period of time");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<EmployeePair, Long> resMap = new HashMap<>();
        resMap.put(finalPair, tempPeriod);
        return resMap;
    }
}
