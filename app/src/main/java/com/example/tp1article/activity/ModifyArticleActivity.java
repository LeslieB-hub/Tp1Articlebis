package com.example.tp1article.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp1article.R;
import com.example.tp1article.bo.Article;
import com.example.tp1article.repository.ArticleBddRepository;
import com.example.tp1article.repository.InterfaceArticleRepository;

public class ModifyArticleActivity extends AppCompatActivity {

    Article articleModify = null;
    EditText etName;
    EditText etDescription;
    EditText etPrice;
    EditText etLink;
    InterfaceArticleRepository repoArticle = new ArticleBddRepository(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_article);
        //récupérer l'article
        Intent intent = getIntent();
        articleModify = intent.getParcelableExtra("articleClicked");
        //Afficher l'article
        etName = findViewById(R.id.et_name_modify);
        etDescription = findViewById(R.id.et_description_modify);
        etPrice = findViewById(R.id.et_price_modify);
        etLink = findViewById(R.id.et_link_modify);

        etName.setText(articleModify.getName());
        etDescription.setText(articleModify.getDescription());
        etPrice.setText(String.valueOf(articleModify.getPrice()));
        etLink.setText(articleModify.getLink());

    }

    public void onClickModifyArticle(View view) {
        Article articleModified = new Article(articleModify.getId(), etName.getText().toString(), Float.parseFloat(etPrice.getText().toString()),
                etDescription.getText().toString(), articleModify.getRating(), articleModify.getIsBought(), etLink.getText().toString());
        Toast.makeText(ModifyArticleActivity.this, "L'article '"+ articleModified.getName() +"' a été modifé", Toast.LENGTH_LONG).show();
        repoArticle.update(articleModified);
        finish();

    }
}