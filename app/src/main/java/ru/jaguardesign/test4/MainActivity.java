package ru.jaguardesign.test4;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.jaguardesign.test4.Package.FFra;
import ru.jaguardesign.test4.Package.FMap;

public class MainActivity extends AppCompatActivity {
FFra ffra; FMap fmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ffra=new FFra(); fmap=new FMap();
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        ftrans.replace(R.id.main_c, ffra);
       // ftrans.addToBackStack(null);
        ftrans.commit();
    }
}
