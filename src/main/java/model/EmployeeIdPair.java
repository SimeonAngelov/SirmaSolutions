package model;

import java.util.Objects;

public class EmployeeIdPair {
    private int id;
    private int colleagueId;

    public EmployeeIdPair(int id, int colleagueId) {
        this.id = id;
        this.colleagueId = colleagueId;
    }

    public int getId() {
        return id;
    }

    public int getColleagueId() {
        return colleagueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeIdPair that = (EmployeeIdPair) o;
        return (id == that.id &&
                colleagueId == that.colleagueId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, colleagueId);
    }
}
