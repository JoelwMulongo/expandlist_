package com.apk_devops_testproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Adapter adapter;
    ExpandableListView lview;
    List<String> header;
    HashMap<String, List<String>> category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lview = (ExpandableListView) findViewById(R.id.expand);
        getready();
        adapter = new Adapter(this, header, category);
        lview.setAdapter(adapter);
    }

    private void getready() {
        header = new ArrayList<>();
        category = new HashMap<>();

        //Headers
        header.add("Tools");
        header.add("Library");
        header.add("Services");

        List<String> tools = new ArrayList<>();
        tools.add("Space Analyzer");
        tools.add("Cleaner");
        tools.add("Music player");
        tools.add("Remote Connect");

        List<String> library = new ArrayList<>();
        library.add("Photos");
        library.add("Videos");
        library.add("Apps");
        library.add("Musics");

        List<String> services = new ArrayList<>();
        services.add("FTP Server");
        services.add("Sender");
        services.add("Firewall");
        services.add("VPN");

        //Attach lists to headers by their position
        category.put(header.get(0), tools);
        category.put(header.get(1), library);
        category.put(header.get(2), services);

        lview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(getApplicationContext(),header.get(groupPosition)+ " : " + Objects.requireNonNull(category.get(
                                header.get(groupPosition))).get(childPosition), Toast.LENGTH_SHORT).show();

                //The code below allows you to start a new Activity on list item click.

                //if (Objects.requireNonNull(category.get(header.get(groupPosition))).get(childPosition).equals("Space Analyzer")) {
                //   startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //}
                return false;
            }
        });

        lview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(), " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        lview.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(), "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
