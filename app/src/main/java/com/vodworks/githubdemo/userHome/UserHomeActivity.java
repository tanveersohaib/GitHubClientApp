package com.vodworks.githubdemo.userHome;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.vodworks.githubdemo.auth.LoginActivity;
import com.vodworks.githubdemo.models.AccessTokenPOJO;
import com.vodworks.githubdemo.models.UserPOJO;
import com.vodworks.githubdemo.R;
import com.vodworks.githubdemo.search.SearchResultsActivity;
import com.vodworks.githubdemo.util.GitHubService;
import com.vodworks.githubdemo.util.RCallback;
import com.vodworks.githubdemo.util.RetrofitClient;
import com.vodworks.githubdemo.util.SharedData;
import com.squareup.picasso.Picasso;

import retrofit2.Call;

public class UserHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static String userAccessToken = null;
    private static UserPOJO user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        setContents();

        if(isUserActive()) {
            userAccessToken = SharedData.getAccessToken();
            getUser();
        }
        else {
            String code = getCode();
            if (code != null) {
                final GitHubService service = RetrofitClient.getClient("https://github.com").create(GitHubService.class);
                Call<AccessTokenPOJO> call = service.getAccessToken("25a2190a925d5982a5ae", "feb32616d4c1331e755fe5a33193c3deafc4fa48", code);
                call.enqueue(new RCallback<AccessTokenPOJO>() {
                    @Override
                    public void success(AccessTokenPOJO object) {
                        userAccessToken = object.getAccessToken();
                        getUser();
                        SharedData.setAccessToken(userAccessToken);
                    }

                    @Override
                    public void error(String error) {
                        showError(error);
                    }
                });
            }
        }
    }

    private void setContents(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

   private void getUser(){
        if(userAccessToken!=null){
            GitHubService serviceUser = RetrofitClient.getClient("https://api.github.com").create(GitHubService.class);
            Call<UserPOJO> callUser = serviceUser.getUser(userAccessToken);
            callUser.enqueue(new RCallback<UserPOJO>() {
                @Override
                public void success(UserPOJO object) {
                    user = object;
                    updateUI();
                }

                @Override
                public void error(String error) {
                    showError(error);
                }
            });
        }
    }

    private void updateUI(){
        if(user!=null) {
            ImageView avatar = (ImageView) findViewById(R.id.userAvatar);
            Picasso.get().load(Uri.parse(user.getAvatarUrl())).resize(100, 100).into(avatar);
            TextView username = (TextView) findViewById(R.id.username);
            username.setText(user.getLogin());
        }
    }

    private void showError(String error){
        Toast.makeText(this,error,Toast.LENGTH_LONG).show();
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
        getMenuInflater().inflate(R.menu.user_home, menu);
        SearchView search = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        search.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, SearchResultsActivity.class)));
        search.setQueryHint(getResources().getString(R.string.search_hint));
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
/*
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getCode() {
        Intent intent = getIntent();
        String code = null;
        if (intent.getAction() == Intent.ACTION_VIEW) {
            code = intent.getData().getQueryParameter("code");
        }
        return code;
    }

    public static boolean isUserActive(){
        return SharedData.hasAccessToken();
    }

    public void logout(){
        SharedData.removeAccessToken();
        //Redirect to LoginScreen
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
