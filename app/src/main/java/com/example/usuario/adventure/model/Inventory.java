package com.example.usuario.adventure.model;

import java.util.LinkedList;


public class Inventory {
    private LinkedList<Item> inventory = new LinkedList<>();

    public Inventory(){

    }

    public String print(){
        String result = "";
        result = result + "Inventario:\n";
        for (Item item: inventory){
            result = result + item.getName() + "\n";
        }
        return result;
    }

    public void add(Item item){
        inventory.add(item);
    }
    public LinkedList<Item> getItems(){
        return inventory;
    }




}
