package services;

import model.EmployeeIdPair;

import java.text.ParseException;
import java.util.*;

public class EmployeeCooperationCalculator {
    public static final String HYPHEN = "-";
    public static final String NUMERIC_ALPHABETIC_REGEX = "\\W+";

    private HashSet<String> ids = new HashSet<>();
    private HashMap<EmployeeIdPair, Long> allEmployeePairs = new HashMap<>();
    private String fileText;
    private List<String> splicedFileText;
    

    public EmployeeCooperationCalculator(String fileText) {
        this.fileText = fileText;
        this.splicedFileText =   Arrays.asList(fileText.split(NUMERIC_ALPHABETIC_REGEX));
    }
    
    private void calculateDaysWorkedTogether() throws ParseException {
        fillEmployeeIDs();
        fillHashmapWithAllPairOfEmployees();
        fillDaysWorkedTogether();
    }

    public List<EmployeeIdPair> getEmployeesWorkedLongestTogether() throws ParseException {
        calculateDaysWorkedTogether();
        long max = Collections.max(allEmployeePairs.values());
        List<EmployeeIdPair> keys = new ArrayList<>();
        for (Map.Entry<EmployeeIdPair, Long> entry : allEmployeePairs.entrySet()) {
            if (entry.getValue()==max) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    private void fillEmployeeIDs(){
        for (int i = 0; i < splicedFileText.size(); i+=8) {
            ids.add(splicedFileText.get(i));
        }
    }

    private void fillHashmapWithAllPairOfEmployees(){
        List<String> targetList = new ArrayList<>(ids);
        for (int i = 0; i < targetList.size()-1; i++) {
            for (int j = i+1; j < targetList.size(); j++) {
                int id = Integer.valueOf(targetList.get(i));
                int colleagueId = Integer.valueOf(targetList.get(j));
                allEmployeePairs.put(new EmployeeIdPair(id,colleagueId),0l);
            }
        }
    }

    private void fillDaysWorkedTogether() throws ParseException {
        for (int i = 1; i < splicedFileText.size(); i+=8) {
            for (int j = i+8; j < splicedFileText.size(); j+=8) {

                String startDate1 = splicedFileText.get(i+1) + HYPHEN +  splicedFileText.get(i+2) + HYPHEN + splicedFileText.get(i+3);
                String endDate1 = splicedFileText.get(i+4) + HYPHEN +  splicedFileText.get(i+5) + HYPHEN + splicedFileText.get(i+6);
                String startDate2 = splicedFileText.get(j+1) + HYPHEN +  splicedFileText.get(j+2) + HYPHEN + splicedFileText.get(j+3);
                String endDate2 = splicedFileText.get(j+4) + HYPHEN +  splicedFileText.get(j+5) + HYPHEN + splicedFileText.get(j+6);

                //check if projects are same, employeeID is NOT the same and if dates overlap
                if(splicedFileText.get(i).equals(splicedFileText.get(j)) && !(splicedFileText.get(i-1).equals(j-1))
                        && (DateUtils.calculateDatesOverlap(startDate1,endDate1,startDate2,endDate2)>0)){
                    incrementPair(Integer.valueOf(splicedFileText.get(i-1)),Integer.valueOf(splicedFileText.get(j-1)),
                            DateUtils.calculateDatesOverlap(startDate1,endDate1,startDate2,endDate2)   );
                }
            }
        }
    }

    private void incrementPair(int i, int j, long days) {
        EmployeeIdPair pair = new EmployeeIdPair(i,j);
        EmployeeIdPair reversePair = new EmployeeIdPair(j,i);
        if(allEmployeePairs.containsKey(pair)){
            allEmployeePairs.put(pair, allEmployeePairs.get(pair) + days);
        }else{
            allEmployeePairs.put(reversePair, allEmployeePairs.get(reversePair) + days);
        }
    }
}
