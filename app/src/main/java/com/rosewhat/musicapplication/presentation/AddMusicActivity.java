package com.rosewhat.musicapplication.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rosewhat.musicapplication.R;
import com.rosewhat.musicapplication.domain.Music;

public class AddMusicActivity extends AppCompatActivity {

    private Spinner spinnerMusic;
    private Spinner spinnerLanguage;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextYear;
    private Button buttonAdd;

    private int checkmusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);

        spinnerLanguage = findViewById(R.id.spinnerLanguage);
        spinnerMusic = findViewById(R.id.spinnerMusic);
        editTextTitle = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextYear = findViewById(R.id.editTextYear);
        buttonAdd = findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String musicItem = spinnerMusic.getSelectedItem().toString().trim();
                String language = spinnerLanguage.getSelectedItem().toString().trim();
                String name = editTextTitle.getText().toString().trim();
                String description = editTextDescription.getText().toString();
                String year = editTextYear.getText().toString().trim();

                if (musicItem.equals("Freak")) {
                    checkmusic = R.raw.freaks;
                } else if (musicItem.equals("Группа крови")) {
                    checkmusic = R.raw.blood;
                } else if (musicItem.equals("Hdmi")) {
                    checkmusic = R.raw.bones;
                }else if (musicItem.equals("Ginseng Strip 2002")) {
                    checkmusic = R.raw.streap;
                }else if (musicItem.equals("Звезда по Имени Солнце")) {
                    checkmusic = R.raw.star;
                }

                Music music = new Music(name, description, year, language, checkmusic);
                MainActivity.music.add(music);



                Intent intent = new Intent(AddMusicActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}