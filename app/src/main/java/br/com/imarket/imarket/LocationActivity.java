package br.com.imarket.imarket;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.stephentuso.welcome.WelcomeScreenHelper;

import br.com.imarket.imarket.font.Font;
import br.com.imarket.imarket.util.IMarketUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.imarket.imarket.util.LocationUtil.isGPSEnabled;

public class LocationActivity extends AppCompatActivity {

    WelcomeScreenHelper welcomeScreen;

    @BindView(R.id.tv_imarket)
    TextView tvImarket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        welcomeScreen = new WelcomeScreenHelper(this, WelcomeScreenActivity.class);
        welcomeScreen.show(savedInstanceState);
        welcomeScreen.forceShow();

        ButterKnife.bind(this);
        IMarketUtils.animateHeader(this);

        tvImarket.setTypeface(Font.amatic(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }

    @OnClick(R.id.bt_activate_location)
    public void activateLocation() {
        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    @OnClick(R.id.bt_later)
    public void later(View view) {
        startActivity(new Intent(view.getContext(), HomeActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isGPSEnabled(this)) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
