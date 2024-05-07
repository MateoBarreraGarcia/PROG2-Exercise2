package at.ac.fhcampuswien.fhmdb.Interfaces;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;

import java.sql.SQLException;

@FunctionalInterface
public interface ClickEventHandler <T> {
    void onClick(T t) throws DatabaseException;
}
