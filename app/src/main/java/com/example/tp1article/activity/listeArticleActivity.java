package com.example.tp1article.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tp1article.R;
import com.example.tp1article.ViewModel.ArticleViewModel;
import com.example.tp1article.activity.adapter.ArticleAdapter;
import com.example.tp1article.bo.Article;
import com.example.tp1article.repository.ArticleBddRepository;
import com.example.tp1article.repository.InterfaceArticleRepository;

import java.util.List;

public class listeArticleActivity extends AppCompatActivity {

    private ListView maListe = null;
    Article articleClicked = null;
    InterfaceArticleRepository repoArticle = new ArticleBddRepository(this);
    //menu
    //ICI : ON LIE L’ACTION BARRE À L'ACTIVITÉ
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //on décompresse le xml du menu
        getMenuInflater().inflate(R.menu.mon_menu, menu);
        return true;
    }
    //ICI : ON DÉFINIT LES ACTIONS DE L’ACTION BARRE
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this,"Préférences", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_recherche:
                Toast.makeText(this,"Recherche", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_add_item:
                Intent intentInsert = new Intent(this, InsertArticleActivity.class);
                startActivity(intentInsert);
                return true;
            case R.id.action_list_item:
                Intent intentList = new Intent(this, listeArticleActivity.class);
                startActivity(intentList);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Shop", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_article);
        maListe = findViewById(R.id.list_article);
        //afficher la page Affichage de l'article+

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
        Intent intentHome = new Intent(this, MainActivity.class);
        startActivity(intentHome);
    }

    public void onClickDelete(View v){
        //récupère la view de style_ligne et récupère le parent de celui-ci avec getParent dc récupère la view liste article
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Article articleClicked = (Article) maListe.getAdapter().getItem(position);
        Log.i("Shop", "fonctionne"+ articleClicked);
        Toast.makeText(listeArticleActivity.this, "L'article '"+articleClicked.getName()+"' a été supprimé.", Toast.LENGTH_LONG).show();
        repoArticle.delete(articleClicked);
        finish();

    }

    public void onClickModify(View v){
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Article articleClicked = (Article) maListe.getAdapter().getItem(position);
        Intent intentModify = new Intent(listeArticleActivity.this, ModifyArticleActivity.class);
        intentModify.putExtra("articleClicked", articleClicked);
        startActivity(intentModify);

    }


}