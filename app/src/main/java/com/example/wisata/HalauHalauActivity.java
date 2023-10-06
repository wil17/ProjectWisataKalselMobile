package com.example.wisata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wisata.modelData.Artikel;

public class HalauHalauActivity extends AppCompatActivity {
    TextView Judul;
    TextView konten;
    ImageView gambar;
    WebView mapWebView;
    @SuppressLint("SetJavaScriptEnabled")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halau_halau);

        Artikel[] artikels = {
                new Artikel("Gunung Halau-Halau", R.drawable.gunung_halau_halau, R.string.halau_halau)
        };
        Judul = findViewById(R.id.judul);
        konten = findViewById(R.id.konten);
        gambar = findViewById(R.id.gambar);
        mapWebView = findViewById(R.id.mapWebView);

        WebSettings webSettings = mapWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String htmlData = "<html><body><iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1019757.9424312488!2d114.55142006062715!3d-3.2428782811806594!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x2de57dd35ea2fab9%3A0x5fa75b5a0ab6cca5!2sGunung%20Besar!5e0!3m2!1sen!2sid!4v1685239587836!5m2!1sen!2sid\" width=\"400\" height=\"300\" style=\"border:0;\" allowfullscreen=\"\" loading=\"lazy\" referrerpolicy=\"no-referrer-when-downgrade\"></iframe></body></html>";
        String mimeType = "text/html";
        String encoding = "UTF-8";
        mapWebView.loadDataWithBaseURL(null, htmlData, mimeType, encoding, null);
    }
}