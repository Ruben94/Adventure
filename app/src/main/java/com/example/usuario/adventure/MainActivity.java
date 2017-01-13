package com.example.usuario.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.adventure.model.Inventory;
import com.example.usuario.adventure.model.Item;
import com.example.usuario.adventure.model.MapGenerator;
import com.example.usuario.adventure.model.Room;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    ImageButton helpButton;
    TextView roomDescription;
    ImageView roomImage;



    ImageButton northButton;
    ImageButton westButton;
    ImageButton eastButton;
    ImageButton southButton;
    ImageButton lookButton;
    ImageButton inventoryButton;
    ImageButton takeButton;
    ImageButton dropButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomImage = (ImageView) findViewById(R.id.activity_main_scene_image);
        northButton = (ImageButton) findViewById(R.id.activity_main_north_button);
        westButton = (ImageButton) findViewById(R.id.activity_main_west_button);
        eastButton = (ImageButton) findViewById(R.id.activity_main_east_button);
        southButton = (ImageButton) findViewById(R.id.activity_main_south_button);
        lookButton = (ImageButton) findViewById(R.id.activity_main_center_button);
        inventoryButton = (ImageButton) findViewById(R.id.activity_main_inventory);
        takeButton = (ImageButton) findViewById(R.id.activity_main_take);
        dropButton = (ImageButton) findViewById(R.id.activity_main_drop);
        roomDescription = (TextView) findViewById(R.id.activity_main_scene_text);
        helpButton = (ImageButton) findViewById(R.id.activity_main_help);

        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomNorth();
                repaintScene();
            }
        });

        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomWest();
                repaintScene();
            }
        });


        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomEast();
                repaintScene();
            }
        });

        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomSouth();
                repaintScene();
            }
        });

        lookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               lookRoom();

            }
        });

        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInventory();

            }
        });

        takeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TakeActivity.class);
                startActivity(i);

            }
        });

        dropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);

            }
        });

        initGame();
        repaintScene();

    }

    private void lookRoom() {
        String roomText = "";
        roomText = roomText + currentRoom.getDescription() + "\n";

        LinkedList<Item> roomItems = currentRoom.getItems();
        if ( roomItems == null){
            roomText = roomText + ("No hay nada");
        }else {
            for (Item item : roomItems) {
                roomText = roomText + item.getName() + "\n";
            }
        }

        roomDescription.setText(roomText);

    }

    private void showInventory() {
        String inventoryText = inventory.print();
        roomDescription.setText(inventoryText);

    }

    Inventory inventory = new Inventory();
    Room currentRoom;


    private void initGame() {

            Item sword = new Item("Espada de madera", "Una espada que no sirve para mucho.");
            Item shield = new Item("Escudo de madera", "Escudo inutil.");
            Item rubberChiken = new Item("Pollo de goma", "Puede que te salve la vida algun dia.");

            inventory.add(sword);
            inventory.add(shield);
            inventory.add(rubberChiken);

            MapGenerator.generate();

            currentRoom = MapGenerator.initialRoom;

    }

    private void repaintScene(){
        roomDescription.setText(currentRoom.getDescription());
        String sceneImage = currentRoom.getImage();
        int id = getResources().getIdentifier(sceneImage, "drawable", getPackageName());
        roomImage.setImageResource(id);

        if(currentRoom.getRoomNorth() !=null){
            northButton.setVisibility(View.VISIBLE);
        }else{
            northButton.setVisibility(View.INVISIBLE);
        }

        if(currentRoom.getRoomWest() !=null){
            westButton.setVisibility(View.VISIBLE);
        }else{
            westButton.setVisibility(View.INVISIBLE);
        }

        if(currentRoom.getRoomEast() !=null){
            eastButton.setVisibility(View.VISIBLE);
        }else{
            eastButton.setVisibility(View.INVISIBLE);
        }

        if(currentRoom.getRoomSouth() !=null){
            southButton.setVisibility(View.VISIBLE);
        }else{
            southButton.setVisibility(View.INVISIBLE);
        }

    }
}
