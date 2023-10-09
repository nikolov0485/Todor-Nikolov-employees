import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Employee {
    private final int id;
    private final int projectId;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;


    public Employee(int id, int projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.id = id;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Employee otherEmployee = (Employee) obj;
        return this.id == otherEmployee.getId()
                && this.projectId == otherEmployee.getProjectId();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }

    public long commonWorkDays(Employee other) {
        if (this.dateFrom.isEqual(other.dateFrom) && this.dateTo.isEqual(other.dateTo)) {
            return ChronoUnit.DAYS.between(this.dateFrom, this.dateTo); // same dates

        } else if (this.dateFrom.isAfter(other.dateFrom) && this.dateTo.isBefore(other.dateTo)) {
            return ChronoUnit.DAYS.between(this.dateFrom, this.dateTo); // dates of this between other

        } else if (other.dateFrom.isAfter(this.dateFrom) && other.dateTo.isBefore(this.dateTo)) {
            return ChronoUnit.DAYS.between(other.dateFrom, other.dateTo); // dates of other between this

        } else if ((this.dateFrom.isEqual(other.dateFrom) || this.dateFrom.isBefore(other.dateFrom))
                && (this.dateTo.isEqual(other.dateTo) || this.dateTo.isBefore(other.dateTo))
                && this.dateTo.isAfter(other.dateFrom)) {
            return ChronoUnit.DAYS.between(other.dateFrom, this.dateTo); // other date from --> this date to

        } else if ((other.dateFrom.isEqual(this.dateFrom) || other.dateFrom.isBefore(this.dateFrom))
                && (other.dateTo.isEqual(this.dateTo) || other.dateTo.isBefore(this.dateTo))
                && other.dateTo.isAfter(this.dateFrom)) {
            return ChronoUnit.DAYS.between(this.dateFrom, other.dateTo); // this date from --> other date to
        }

        return 0;
    }
}
