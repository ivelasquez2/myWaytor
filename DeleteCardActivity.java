package com.example.lap.mywaytor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DeleteCardActivity extends AppCompatActivity {
    DBController db;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_card);
        db = new DBController(this);

        final TextView helloTextView = findViewById(R.id.Dgreeting);
        helloTextView.setText(R.string.user_greeting);

        final TextView CNTextView = findViewById(R.id.CNum);
        final String CardNumber = db.getCardNumber();
        CNTextView.setText(CardNumber);

        final Button bDeleteCard = findViewById(R.id.deleteCard);

        bDeleteCard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String PID = db.getPID();
                db.deleteCard(PID);
                startActivity(new Intent(DeleteCardActivity.this, ManageCardActivity.class));
            }
        });
    }
}