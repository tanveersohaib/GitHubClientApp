package com.example.sohaibtanveer.githubdemo.userHome;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sohaibtanveer.githubdemo.auth.LoginActivity;
import com.example.sohaibtanveer.githubdemo.models.AccessTokenPOJO;
import com.example.sohaibtanveer.githubdemo.models.UserPOJO;
import com.example.sohaibtanveer.githubdemo.R;
import com.example.sohaibtanveer.githubdemo.repositoryView.codeFragment.CodeFragment;
import com.example.sohaibtanveer.githubdemo.repositoryView.issuesFragment.IssuesFragment;
import com.example.sohaibtanveer.githubdemo.repositoryView.pullRequestsFragment.PullRequestsFragment;
import com.example.sohaibtanveer.githubdemo.search.SearchResultsActivity;
import com.example.sohaibtanveer.githubdemo.util.GitHubService;
import com.example.sohaibtanveer.githubdemo.util.RCallback;
import com.example.sohaibtanveer.githubdemo.util.RetrofitClient;
import com.example.sohaibtanveer.githubdemo.util.SharedData;
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
        login();
        setBottomNavBar();
    }

    private void login() {
        if (isUserActive()) {
            userAccessToken = SharedData.getAccessToken();
            getUser();
        } else {
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

    private void setContents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.nav_tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("MENU"));
        tabLayout.addTab(tabLayout.newTab().setText("ACCOUNT"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.nav_pager);
        final NavigationPagerAdapter adapter = new NavigationPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void getUser() {
        if (userAccessToken != null) {
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

    private void updateUI() {
        if (user != null) {
            ImageView avatar = (ImageView) findViewById(R.id.userAvatar);
            if (user.getAvatarUrl() != null)
                Picasso.get().load(Uri.parse(user.getAvatarUrl())).resize(100, 100).into(avatar);
            TextView username = (TextView) findViewById(R.id.username);
            if (user.getLogin() != null)
                username.setText(user.getLogin());
            TextView name = (TextView) findViewById(R.id.name);
            if (user.getName() != null)
                name.setText(user.getName());
        }
    }

    private void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
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

    public static boolean isUserActive() {
        return SharedData.hasAccessToken();
    }

    private void setBottomNavBar() {
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.userHomeBottomNav);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.feeds:
                                selectedFragment = IssuesFragment.newInstance();
                                break;
                            case R.id.issues:
                                selectedFragment = IssuesFragment.newInstance();
                                break;
                            case R.id.pullReq:
                                selectedFragment = PullRequestsFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.userHomeContent, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.userHomeContent, IssuesFragment.newInstance());
        transaction.commit();

    }
}
