package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;

    boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    void update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean exist(String id) throws SQLException, ClassNotFoundException;

    String search(String newValue) throws SQLException, ClassNotFoundException;
}
