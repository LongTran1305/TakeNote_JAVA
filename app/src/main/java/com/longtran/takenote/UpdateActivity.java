package com.longtran.takenote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.longtran.takenote.databinding.ActivityUpdateBinding;

public class UpdateActivity extends AppCompatActivity {

    private static final int RESULT_UPDATE = 2;
    private ActivityUpdateBinding binding;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().setTitle("Update Note");

        getData();


        binding.btnCancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNote();
            }
        });
    }

    public void updateNote(){
        String titleLast = binding.updateTitle.getText().toString();
        String desLast = binding.updateDes.getText().toString();

        Log.i("updateTitle",titleLast);
        Log.i("updateDes",desLast);
        Intent intent = new Intent();
        intent.putExtra("titleUpdate",titleLast);
        intent.putExtra("desUpdate",desLast);
        if(noteId != -1){
            intent.putExtra("noteid",noteId);
            setResult(RESULT_UPDATE,intent);
            finish();
        }
    }

    public void getData(){
        Intent intent  = getIntent();
        noteId = intent.getIntExtra("id",-1);
        String noteTitle = intent.getStringExtra("title");
        String noteDes = intent.getStringExtra("des");

        binding.updateTitle.setText(noteTitle);
        binding.updateDes.setText(noteDes);
    }
}