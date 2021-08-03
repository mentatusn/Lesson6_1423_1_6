package calculator.calulation.lesson6_1423_1_6;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CoatOfArmsFragment extends Fragment {

    public static String ARG_CITY = "city";
    private City city;

    public static CoatOfArmsFragment newInstance(City city){
        CoatOfArmsFragment fragment=  new CoatOfArmsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_CITY,city);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            this.city = getArguments().getParcelable(ARG_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coat_of_arms,container,false);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        textView.setText(this.city.getName());

        TypedArray typedArray = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
        imageView.setImageResource(typedArray.getResourceId(this.city.getImageIndex(),-1));
        return view;
    }
}
