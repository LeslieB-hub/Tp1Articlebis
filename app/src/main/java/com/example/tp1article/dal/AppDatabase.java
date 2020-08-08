package com.example.tp1article.dal;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tp1article.bo.Article;

/**
 * On veut qu'une instance de cette classe (on veut l'appeler qu'une fois) donc singleton
 * permet d'avoir qu'une seule connexion à la bdd et donc de ne pas surcharger celle-ci.
 * avec @database on met tte les entités qu'on a besoin pr construire la bdd
 */
@Database(entities = {Article.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;



    /**
     * Permet de fournir une instance de la dao utilisateur aux couches supérieures.
     */
    public abstract ArticleDao getArticleDao();

    public static AppDatabase getInstance(Context context){
        //Si l'instance n'est pas créé
        if (INSTANCE == null){
            //On l'a créé
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "shop.db")
                    //ajout un callback pr créer ou action ce qu'on veut la créer
                    //.addCallback(roomFixture)
                    //.allowMainThreadQueries() permet d'utiliser les asynctacks et nom les livedata donc pas l'architecture component
                    .build();
        }
        return INSTANCE;
    }

/*    private static Callback roomFixture = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new AsyncTask<AppDatabase,Void,Void>(){
                @Override
                protected Void doInBackground(AppDatabase... appDatabases) {
                    ArticleDao dao = INSTANCE.getArticleDao();
                    dao.insert(new Article(0, "Télécommande", 52f, "Elle s'adapte à toute les télévisions", 2.5f, false, "www.telecommande.fr")); //id, String name, Float price, String description, Float rating, Boolean isBought, String link
                    dao.insert(new Article(0, "Verre", 2f, "Elle s'adapte à tout", 5f, false, "www.verre.fr"));
                    return null;
                }
            }.execute(INSTANCE);
        }
    }.onCreate(SupportSQLiteDatabase INSTANCE);*/

}
