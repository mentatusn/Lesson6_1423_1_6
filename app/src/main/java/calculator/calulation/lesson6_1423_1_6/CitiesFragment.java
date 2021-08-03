package calculator.calulation.lesson6_1423_1_6;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CitiesFragment extends Fragment {

     City currentCity;
     public static String KEY_CITY = "city";
     boolean isLandScape;

    public static CitiesFragment newInstance(){
        return new CitiesFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLandScape = getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE;

        Log.d("mylogs","savedInstanceState!=null "+(savedInstanceState!=null));
        if(savedInstanceState!=null){
            currentCity = savedInstanceState.getParcelable(KEY_CITY);
        }
        Log.d("mylogs","currentCity!=null "+(currentCity!=null));
        if(isLandScape)
            if(currentCity!=null){
                showCoatOfArms(currentCity.getImageIndex());
            }else{
                showCoatOfArms(0);
            }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(KEY_CITY,currentCity);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities,container,false);
        LinearLayout linearLayout = (LinearLayout) view;

        String[] cities = getResources().getStringArray(R.array.cities);

        for(int i =0;i<cities.length;i++){
            String name = cities[i];
            TextView textView = new TextView(getContext());
            textView.setText(name);
            textView.setTextSize(30);
            linearLayout.addView(textView);
            int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                      showCoatOfArms(finalI);
                }
            });
        }

        return view;
    }

    private void showCoatOfArms(int index) {
        currentCity = new City(index,
                (getResources().getStringArray(R.array.cities)[index]));
        if (isLandScape) {
            showCoatOfArmsLand();
        }else{ // port
            showCoatOfArmsPort();
        }
    }

    private void showCoatOfArmsPort() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.cities_container, CoatOfArmsFragment.newInstance(currentCity))
                .addToBackStack("")
                .commit();
    }

    private void showCoatOfArmsLand() {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.coat_of_arms_container, CoatOfArmsFragment.newInstance(currentCity))
                .commit();
    }
}
