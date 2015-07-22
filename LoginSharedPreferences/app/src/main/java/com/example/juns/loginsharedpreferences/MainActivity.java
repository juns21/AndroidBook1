package com.example.juns.loginsharedpreferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class MainActivity extends Activity {

    // User Session Manager Class
    UserSessionManager session;

    // Button Logout
    Button btnLogout;

    // List Button
    Button btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session Class instance
        session = new UserSessionManager(getApplicationContext());

        TextView lblName = (TextView) findViewById(R.id.lblName);
        TextView lblEmail = (TextView) findViewById(R.id.lblEmail);

        // Button logout
        btnLogout = (Button) findViewById(R.id.btnLogout);

        btnList = (Button) findViewById(R.id.btnList);

        Toast.makeText(getApplicationContext(), "User Login Status: "+ session.isUserLoggedIn(), Toast.LENGTH_LONG).show();

        // Check user login(this is the important point)
        // If user is not logged in, This will redirect user to LoginActivity
        // and finish current activity from activity stack.
        if (session.checkLogin()) {
            finish();
        }

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // get name
        String name = user.get(UserSessionManager.KEY_NAME);

        // get Email
        String email = user.get(UserSessionManager.KEY_EMAIL);

        // Show user data on activity
        lblName.setText(Html.fromHtml("Name: <b>"+ name +"</b>"));
        lblEmail.setText(Html.fromHtml("Email: <b>"+ email +"</b>"));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear the User session data
                // and redirect user to LoginActivity
                session.logoutUser();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }
}
