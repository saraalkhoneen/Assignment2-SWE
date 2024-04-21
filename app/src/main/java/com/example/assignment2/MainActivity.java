package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText sendTo, subject, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendTo = findViewById(R.id.editText1);
        subject = findViewById(R.id.editText2);
        body = findViewById(R.id.editText3);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            String emailSend = sendTo.getText().toString();
            String emailSubject = subject.getText().toString();
            String emailBody = body.getText().toString();

            if (!emailSend.isEmpty() && emailSend.contains("@") && !emailSubject.isEmpty() && !emailBody.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailSend});
                intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
                intent.putExtra(Intent.EXTRA_TEXT, emailBody);
                intent.setType("message/rfc822");

                try {
                    startActivity(Intent.createChooser(intent, "Choose an Email client :"));
                } catch (android.content.ActivityNotFoundException ex) {
                    // Handle case where no email client is available
                    // You can use Toast to show a message to the user
                }
            } else {
                // Show an error message to the user
                // You can use Toast here to inform user to fill all fields correctly
            }
        });
    }
}
