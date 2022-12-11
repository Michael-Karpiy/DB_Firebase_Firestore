package db_firebase_firestore;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivitySecond extends AppCompatActivity {

    EditText position, id, name, description;
    CardView cancel, edit, accept;

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        position = findViewById(R.id.position);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);

        edit = findViewById(R.id.edit);
        accept = findViewById(R.id.accept);
        cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(view -> onBackPressed());

        Intent intent = getIntent();
        String str = intent.getStringExtra("position");
        id.setText(str);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("DB_Firebase_Firestore").document(intent.getStringExtra("position"));
        documentReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                position.setText(document.getString("position"));
                id.setText(document.getString("id"));
                name.setText(document.getString("name"));
                description.setText(document.getString("description"));

            }
        });

        edit.setOnClickListener(view -> {

            id.setEnabled(true);
            id.setTextColor(getResources().getColor(R.color.black));

            name.setEnabled(true);
            name.setTextColor(getResources().getColor(R.color.black));

            description.setEnabled(true);
            description.setTextColor(getResources().getColor(R.color.black));

            accept.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
        });

        accept.setOnClickListener(view -> {
            documentReference.update("id",id.getText().toString());
            documentReference.update("name",name.getText().toString());
            documentReference.update("description",description.getText().toString());

            id.setEnabled(false);
            id.setTextColor(getResources().getColor(android.R.color.system_accent1_300));

            name.setEnabled(false);
            name.setTextColor(getResources().getColor(android.R.color.system_accent1_300));

            description.setEnabled(false);
            description.setTextColor(getResources().getColor(android.R.color.system_accent1_300));

            accept.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
        });
    }
}