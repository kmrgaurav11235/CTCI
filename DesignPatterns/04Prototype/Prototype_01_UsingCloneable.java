import java.util.ArrayList;

/*
Lecture: https://www.journaldev.com/1440/prototype-design-pattern-in-java
Prototype design pattern is used when the Object creation is a costly affair and requires a lot of time 
and resources and you have a similar object already existing. Prototype pattern provides a mechanism to 
copy the original object to a new object and then modify it according to our needs. Prototype design 
pattern uses java cloning to copy the object.

e.g. Suppose we have an Object that loads data from database. Now we need to modify this data in our program 
multiple times, so it’s not a good idea to create the Object using new keyword and load all the data again 
from database. The better approach would be to clone the existing object into a new object and then do the 
data manipulation.

Prototype design pattern mandates that the Object which you are copying should provide the copying feature. 
It should not be done by any other class. However whether to use shallow or deep copy of the Object properties 
depends on the requirements and its a design decision.
*/
class Employees implements Cloneable {
    private ArrayList<String> employees;
    Employees() {
        employees = new ArrayList<>();
    }
    Employees(ArrayList<String> employeeList) {
        employees = employeeList;
    }
    public void loadData() {
        // Load data from database
        employees.add("Manwe");
        employees.add("Orolin");
        employees.add("Tulkas");
    }

    public void addEmployee(String employee) {
        employees.add(employee);
    }

    public void removeEmployee(int index) {
        employees.remove(index);
    }

    // the Object which you are copying should provide the copying feature.
    @Override
    // Change from protected to public, so that it available to everyone
    public Object clone() throws CloneNotSupportedException {
        ArrayList<String> tempEmpList = new ArrayList<>();
        for (String emp : employees) {
            tempEmpList.add(emp);
        }
        return new Employees(tempEmpList);
    }

    @Override
    public String toString() {
        return "Employees: " + employees;
    }
}
class Prototype_01_UsingCloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employees cricketClub = new Employees();
        cricketClub.loadData();

        Employees footballClub = (Employees) cricketClub.clone();
        footballClub.addEmployee("Turin");
        footballClub.removeEmployee(1);

        System.out.println("Cricket Club: " + cricketClub);
        System.out.println("Football Club: " + footballClub);
        // If the object cloning was not provided, we will have to make database call to fetch the employee 
        // list every time. Then do the manipulations that would have been resource and time consuming. 
    }
}