package com.example.tp1article.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.tp1article.R;
import com.example.tp1article.ViewModel.ArticleViewModel;
import com.example.tp1article.activity.adapter.ArticleAdapter;
import com.example.tp1article.bo.Article;

import java.util.List;

public class listeArticleActivity extends AppCompatActivity {

    private ListView maListe = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Shop", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_article);
        maListe = findViewById(R.id.list_article);
        //afficher la page Affichage de l'article
        //maListe.setOnItemClickListener();
    }

    @Override
    protected void onResume() {
        Log.i("Shop", "onResume");
        super.onResume();

        ArticleViewModel vm = ViewModelProviders.of(this).get(ArticleViewModel.class);
        LiveData<List<Article>> observateur = vm.getArticles();

        observateur.observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                ArticleAdapter adapter = new ArticleAdapter(listeArticleActivity.this, R.layout.style_ligne_article, articles);
                maListe.setAdapter(adapter);
            }
        });

    }




    public void onClickReturn(View view) {
    }
}