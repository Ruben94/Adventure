package com.example.usuario.adventure.model;

import java.util.LinkedList;


public class Inventory {
    private LinkedList<Item> inventory = new LinkedList<>();

    public Inventory(){

    }

    public void print(){
        System.out.println("------------\nMochila\n------------");
        for (Item item: inventory){
            System.out.println(item.getName()+": "+item.getDescription());
        }
    }

    public void add(Item item){
        inventory.add(item);
    }
    public LinkedList<Item> getItems(){
        return inventory;
    }




}
