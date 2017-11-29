package com.example.lap.mywaytor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.ImageButton;
import android.widget.*;



public class MenuActivity extends AppCompatActivity {

    private ImageButton friedChicken;
    private Button payment_Button, addCard_Button, manageCard_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        friedChicken = (ImageButton) findViewById(R.id.fried_Chicken);
        payment_Button = (Button) findViewById(R.id.payment_Button);
        manageCard_Button = (Button) findViewById(R.id.manageCard_Button);
        friedChicken.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v)
            {
                Intent fChicken = new Intent(MenuActivity.this, DetailedMenuActivity.class);
                startActivity(fChicken);
            }
         });
        payment_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent payment = new Intent(MenuActivity.this, payActivity.class);
                startActivity(payment);
            }
        });
        manageCard_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent manageCard = new Intent(MenuActivity.this, ManageCardActivity.class);
                startActivity(manageCard);
            }
        });
    }


}
