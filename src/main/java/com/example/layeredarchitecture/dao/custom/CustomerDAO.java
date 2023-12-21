package com.example.layeredarchitecture.dao.custom;

import java.sql.*;

public interface CustomerDAO extends CrudDAO<CustomerDAO> {
    String generateNewId() throws SQLException, ClassNotFoundException;
}
