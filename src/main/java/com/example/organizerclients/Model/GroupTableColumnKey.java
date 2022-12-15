package com.example.organizerclients.Model;

import java.time.LocalDate;
import java.util.Objects;

public class GroupTableColumnKey {
    private String name;
    private LocalDate localDate;

    public GroupTableColumnKey(String name, LocalDate localDate) {
        this.name = name;
        this.localDate = localDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupTableColumnKey groupTableColumnKey = (GroupTableColumnKey) o;
        return Objects.equals(name, groupTableColumnKey.name) && Objects.equals(localDate, groupTableColumnKey.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, localDate);
    }

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", localDate=" + localDate +
                '}';
    }
}
