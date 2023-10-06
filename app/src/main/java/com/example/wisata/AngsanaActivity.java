package com.example.wisata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.wisata.modelData.Artikel;

public class AngsanaActivity extends AppCompatActivity {
    TextView Judul;
    TextView konten;
    ImageView gambar;
    WebView mapWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angsana);

        Artikel[] artikels = {
                new Artikel("Pantai Angsana", R.drawable.pantai_angsana, R.string.angsana)
        };
        Judul = findViewById(R.id.judul);
        konten = findViewById(R.id.konten);
        gambar = findViewById(R.id.gambar);
        mapWebView = findViewById(R.id.mapWebView);

        WebSettings webSettings = mapWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String htmlData = "<html><body><iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d63699.567143207874!2d115.57041492081065!3d-3.7615899817925844!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x2de626ebe1cd8b4d%3A0xcd2bd9049aa15ce9!2sAngsana%20Beach!5e0!3m2!1sen!2sid!4v1685233115855!5m2!1sen!2sid\" width=\"400\" height=\"300\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe></body></html>";
        String mimeType = "text/html";
        String encoding = "UTF-8";
        mapWebView.loadDataWithBaseURL(null, htmlData, mimeType, encoding, null);
    }
}