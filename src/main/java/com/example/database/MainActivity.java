package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sdb;
    ListView listView;
    EditText editText;
    OpenHelper dbOpenHelper;
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinkedList<Book> listBook = new LinkedList<>();

        dbOpenHelper = new OpenHelper(this);
        sdb = dbOpenHelper.getWritableDatabase();

        listBook.add(new Book("Л. Толстой", "Война и мир", 1972, R.drawable.a));
        listBook.add(new Book("Ф. Достоевский", "Идиот", 1867, R.drawable.a));
        listBook.add(new Book("Н. Лесков", "Очарованный странник", 1995, R.drawable.a));
        listBook.add(new Book("Н. Лесков", "Левша", 2004, R.drawable.a));
        listBook.add(new Book("А. Чехов", "Вишневый сад", 2008, R.drawable.a));
        listBook.add(new Book("А. Пушкин", "Дубровский", 1833, R.drawable.a));
        listBook.add(new Book("Н. Гоголь", "Ночь перед Рождеством", 1832, R.drawable.a));
        listBook.add(new Book("А. Пушкин", "Капитанская дочка", 1833, R.drawable.a));
        listBook.add(new Book("Ф. Достоевский", "Идиот", 1983, R.drawable.a));
        listBook.add(new Book("Ф. Достоевский", "Бесы", 1872, R.drawable.a));
        listBook.add(new Book("Ф. Достоевский", "Бедные Люди", 1967, R.drawable.a));
        listBook.add(new Book("Ф. Достоевский", "Белые Ночи", 1878, R.drawable.a));
        listBook.add(new Book("Ф. Достоевский", "Игрок", 1967, R.drawable.a));
        listBook.add(new Book("М. Булгаков", "Мастер и Маргарита", 1966, R.drawable.a));
        listBook.add(new Book("Карл Маркс", "Капитал", 1867, R.drawable.a));


        ArrayList<HashMap<String, Object>> bookList = new ArrayList<>();
        HashMap<String, Object> map;

        for (int i=0; i<listBook.size(); i++) {
            map = new HashMap<String, Object>();
            map.put("name", listBook.get(i).name);
            map.put("author", listBook.get(i).author);
            map.put("year", listBook.get(i).year);
            map.put("cover", listBook.get(i).cover);
            bookList.add(map);
            if (simpleAdapter != null) {
                simpleAdapter.notifyDataSetChanged();
            }

            // sdb.execSQL("");  todo SQLite

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", listBook.get(i).name);
            contentValues.put("author", listBook.get(i).author);
            contentValues.put("year", listBook.get(i).year);
            contentValues.put("cover", listBook.get(i).cover);

            // sdb.insert(OpenHelper.DATABASE_TABLE, null, contentValues); todo homework
        }

        String[] id = {"name", "author", "year", "cover"};
        int to[] = {R.id.name, R.id.author, R.id.year, R.id.cover};

        simpleAdapter = new SimpleAdapter(this, bookList, R.layout.list_view, id, to);

        listView = (ListView) findViewById (R.id.listView);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("Was click", "Was click");
                createAlertDialog(listBook.get(position).author + " " + listBook.get(position).name, listBook.get(position).year+"");

            }
        });

    }


    public void createAlertDialog(String theme, String rule) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle(theme);
        builder.setMessage(rule);

        builder.setPositiveButton("Ок",

                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });

        builder.show();
    }
}

class Book {
    String name;
    String author;
    int year;
    int cover;

    public Book(String name, String author, int year, int cover){
        this.name = name;
        this.author = author;
        this.year = year;
        this.cover = cover;
    }
}
