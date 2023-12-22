package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.bo.custom.impl.CustomerBoImpl;
import com.example.layeredarchitecture.bo.custom.impl.ItemBoImpl;
import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBoImpl;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.SuperDAO;
import com.example.layeredarchitecture.dao.custom.impl.*;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return boFactory == null ? boFactory= new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,PLACE_ORDER
    }

    public SuperBO getBO(BOFactory.BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBoImpl();
            case ITEM:
                return new ItemBoImpl();
            case PLACE_ORDER:
                return new PlaceOrderBoImpl();
            default:
                return null;
        }
    }
}
