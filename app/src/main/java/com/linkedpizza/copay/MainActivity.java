package com.linkedpizza.copay;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String email;
    String name;
    String photoURL;
    UserAccount user;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        //creating a user account
        email=getIntent().getExtras().getString("email");
        name=getIntent().getExtras().getString("name");
        photoURL=getIntent().getExtras().getString("photoURL");
        user = new UserAccount(name,email);

        makeToast("Welcome" +"\n" + user.toString());
        makeToast(photoURL);

        //from navigation drawer sample code
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab
// = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //end of navigation view code
        TextView tv1= (TextView) navigationView.getHeaderView(0).findViewById(R.id.nameTV);
        tv1.setText(name);
        TextView tv2= (TextView) navigationView.getHeaderView(0).findViewById(R.id.emailTV);
        tv2.setText(email);

        if(photoURL!=null){
            user.setPhotoURL(photoURL);

            ImageView userImage = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.profilePicture);

            Picasso.with(getApplicationContext())
                    .load(photoURL)
                    .resize(200, 200)
                    .centerCrop()
                    .transform(new CircleTransform())
                    .into(userImage);

        }
        //Reference
        //http://www.viralandroid.com/2015/09/simple-android-tabhost-and-tabwidget-example.html
        //tabs
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        TabHost.TabSpec spec;
        Intent intent; // Reusable Intent for each tab

        //Tab 1
        spec = host.newTabSpec("Donate");
        spec.setContent(R.id.tab1);
        spec.setIndicator("DONATE");
        //intent = new Intent(this, DonateActivity.class);
        //spec.setContent(intent);
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Request");
        spec.setContent(R.id.tab2);
        spec.setIndicator("REQUEST");
        //intent = new Intent(this, RequestActivity.class);
        //spec.setContent(intent);
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Pool");
        spec.setContent(R.id.tab3);
        spec.setIndicator("POOL");
        //intent = new Intent(this, PoolActivity.class);
        //spec.setContent(intent);
        host.addTab(spec);

<<<<<<< HEAD
        request = (Button) findViewById(R.id.request);

        if (request != null) {
            request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Make request to server.
                    try {
                        String json = server.requestJson(name, email, "10", "informal", null);
                        System.out.println("json: " + json);
                        server.post("https://159.203.1.125", json);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            });
        }
=======
>>>>>>> 00e2c9ad508c6ac9b2d59c04a5bfcfbdde243eb4
        host.setCurrentTab(1);
        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // display the name of the tab whenever a tab is changed
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<UserAccount> users = new ArrayList<UserAccount>();

        for (int i = 0; i < 10; i++) {
            users.add(user);
        }

        TopContributorAdapter adapter = new TopContributorAdapter(getApplicationContext(), users);
        ((ListView) findViewById(R.id.lstTopContributors)).setAdapter(adapter);

<<<<<<< HEAD
        for (int i = 0; i < host.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) host.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#ecf0f1"));
            tv.setTextSize(16);
            tv.setTypeface(Typeface.SANS_SERIF);
        }
        for (int i = 0; i < host.getTabWidget().getChildCount(); i++) {
            host.getTabWidget().getChildAt(i)
                    .setBackgroundColor(Color.parseColor("#2c3e50")); // unselected
        }

        host.getTabWidget().getChildAt(host.getCurrentTab())
                .setBackgroundColor(Color.parseColor("#95a5a6")); // selected
=======
        setTitle("Pool Funds:"); // add pool funds

>>>>>>> 00e2c9ad508c6ac9b2d59c04a5bfcfbdde243eb4
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            logout();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void makeToast(String s){
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
    }
    private void logout(){
        // Get confirmation
        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginIntent);
        makeToast("logout");
        finish();
    }

    private class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {


        protected Long doInBackground(URL... urls) {
            int count = urls.length;
            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                if (isCancelled()) break;
            }
            return totalSize;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Long result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }
}
