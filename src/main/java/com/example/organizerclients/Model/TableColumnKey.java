package com.example.organizerclients.Model;

import java.time.LocalDate;
import java.util.Objects;

public class TableColumnKey {
    private Integer idUser;
    private LocalDate localDate;
    private String login;

    public TableColumnKey(Integer idUser, LocalDate localDate, String login) {
        this.idUser = idUser;
        this.localDate = localDate;
        this.login = login;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setName(Integer idUser) {
        this.idUser = idUser;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableColumnKey tableColumnKey = (TableColumnKey) o;
        return Objects.equals(idUser, tableColumnKey.idUser) && Objects.equals(localDate, tableColumnKey.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, localDate);
    }

    @Override
    public String toString() {
        return "TableColumnKey{" +
                "idUser=" + idUser +
                ", localDate=" + localDate +
                ", login='" + login + '\'' +
                '}';
    }
}
