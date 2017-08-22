package tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vudinhthanh.wallpaper.wallpaper_store.Fast_search;
import vudinhthanh.wallpaper.wallpaper_store.R;

/**
 * Created by Thanh-PC on 8/10/2017.
 */

public class Tab5 extends Fragment {
    Button btAbstrac, btAnime, btAnimal, btBran, btCartoo, btFantan, btGame, btPic, btFun, btSex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab5_activity, container, false);
        btAbstrac = (Button) view.findViewById(R.id.pcAbstract);
        btAnime = (Button) view.findViewById(R.id.pcAnime);
        btAnimal = (Button) view.findViewById(R.id.pcAnimals);
        btBran = (Button) view.findViewById(R.id.pcBrands);
        btCartoo = (Button) view.findViewById(R.id.pcCartoon);
        btFantan = (Button) view.findViewById(R.id.pcFantasy);
        btGame = (Button) view.findViewById(R.id.pcGames);
        btPic = (Button) view.findViewById(R.id.pcPictures);
        btFun = (Button) view.findViewById(R.id.pcFunny);
        btSex = (Button) view.findViewById(R.id.pcSexy);



        addEvent();
        return view;
    }

    private void addEvent() {
        btAbstrac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), Fast_search.class);
                it.putExtra("Fast","Abstract");
                startActivity(it);
            }
        });

        btAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), Fast_search.class);
                it.putExtra("Fast","Anime");
                startActivity(it);
            }
        });


        btAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), Fast_search.class);
                it.putExtra("Fast","Animals");
                startActivity(it);
            }
        });

        btBran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), Fast_search.class);
                it.putExtra("Fast","Brands");
                startActivity(it);
            }
        });

        btCartoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), Fast_search.class);
                it.putExtra("Fast","Cartoon");
                startActivity(it);
            }
        });

        btFantan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), Fast_search.class);
                it.putExtra("Fast","Fantasy");
                startActivity(it);
            }
        });


        btGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), Fast_search.class);
                it.putExtra("Fast","Games");
                startActivity(it);
            }
        });

        btPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), Fast_search.class);
                it.putExtra("Fast","Pictures");
                startActivity(it);
            }
        });

        btFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), Fast_search.class);
                it.putExtra("Fast","Funny");
                startActivity(it);
            }
        });


        btSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getActivity(), Fast_search.class);
                it.putExtra("Fast","Sexy");
                startActivity(it);
            }
        });

    }




}
