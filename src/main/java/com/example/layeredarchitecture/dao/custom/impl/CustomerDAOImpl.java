package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.SQLUtill;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT * FROM Customer");

        ArrayList<CustomerDTO> dtoList = new ArrayList<>();
        while(rst.next()){
            CustomerDTO customerDTO =new CustomerDTO(rst.getString("id"), rst.getString("name"), rst.getString("address"));
            dtoList.add(customerDTO);
        }
        return dtoList;
    }

    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtill.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
        return isSaved;
    }
    @Override
    public void update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        SQLUtill.execute("UPDATE Customer SET name=?, address=? WHERE id=?", customerDTO.getName(), customerDTO.getAddress(), customerDTO.getId());
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtill.execute("DELETE FROM Customer WHERE id=?",id);
        return isSaved;
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT id FROM Customer WHERE id=?",id);
        return rst.next();
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        String id = null;
        if(rst.next()){
            id = rst.getString("id");
            return id;
        }else{
            return id;
        }
    }

    @Override
    public String search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Customer WHERE id=?",newValue);
        rst.next();
        CustomerDTO customerDTO = new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));
        return customerDTO.getName();
    }
}
