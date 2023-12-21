package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.SQLUtill;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Item");

        ArrayList<ItemDTO> dtoList = new ArrayList<>();
        while(rst.next()){
            ItemDTO itemDTO =new ItemDTO(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
            dtoList.add(itemDTO);
        }
        return dtoList;
    }
    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        boolean isSaved = SQLUtill.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)", itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand());
        return isSaved;
    }
    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        boolean isUpdated = SQLUtill.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand(), itemDTO.getCode());
        return isUpdated;
    }
    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        boolean isDeleted = SQLUtill.execute("DELETE FROM Item WHERE code=?", code);
        return isDeleted;
    }
    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT code FROM Item WHERE code=?", code);
        return rst.next();
    }
    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        String id = null;
        if(rst.next()){
            id = rst.getString("code");
            return id;
        }else{
            return id;
        }
    }

    @Override
    public ItemDTO findItems(String newItemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Item WHERE code=?", newItemCode);
        rst.next();
        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        return item;
    }
}
