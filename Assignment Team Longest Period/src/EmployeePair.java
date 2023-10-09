import java.util.Objects;

public class EmployeePair {
    private final Employee emp1;
    private final Employee emp2;

    public EmployeePair(Employee emp1, Employee emp2) {
        this.emp1 = emp1;
        this.emp2 = emp2;
    }

    public Employee getEmp1() {
        return emp1;
    }

    public Employee getEmp2() {
        return emp2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(emp1, emp2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        EmployeePair otherEmployee = (EmployeePair) obj;
        return (this.emp1.equals(otherEmployee.emp1) || this.emp1.equals(otherEmployee.emp2))
                && (this.emp2.equals(otherEmployee.emp1) || this.emp2.equals(otherEmployee.emp2));
    }
}
