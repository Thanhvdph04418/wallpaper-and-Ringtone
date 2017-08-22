package adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import model.Item;
import vudinhthanh.wallpaper.wallpaper_store.R;

/**
 * Created by Thanh-PC on 8/8/2017.
 */

public class AdapterFeatured extends ArrayAdapter<Item> {
    Activity context;
    int resource;
    List<Item> objects;
    ArrayList<Item> arraylist;
    public AdapterFeatured(@NonNull Activity context, @LayoutRes int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);

        this.context=context;
        this.resource=resource;
        this.objects=objects;
        this.arraylist=new ArrayList<Item>();
        this.arraylist.addAll(objects);
    }

    private class ViewHoder{
        TextView textView;
        ImageView img1;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHoder viewHoder;
        if(convertView==null){
            viewHoder=new ViewHoder();
            convertView=View.inflate(context,resource,null);
            viewHoder.img1= (ImageView) convertView.findViewById(R.id.img1);
            viewHoder.textView= (TextView) convertView.findViewById(R.id.txtdowload);
            convertView.setTag(viewHoder);

        }else {
            viewHoder= (ViewHoder) convertView.getTag();

        }
        Item it = this.objects.get(position);

        viewHoder.textView.setText(it.getDownload());
        Picasso.with(context).load(it.getUrlImages()).into(viewHoder.img1);
        Animation animation= AnimationUtils.loadAnimation(context,R.anim.up_from_bottom);
        convertView.startAnimation(animation);

        return convertView;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        objects.clear();
        if(charText.length() == 0){
            objects.addAll(arraylist);
        } else {
            for (Item it : arraylist){
                if(it.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){
                    objects.add(it);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults result = new FilterResults();

                if(constraint != null && constraint.toString().length() > 0){
                    List<Item> founded = new ArrayList<Item>();
                    for(Item item: arraylist){
                        if(item.toString().toLowerCase().contains(constraint)){
                            founded.add(item);
                        }
                    }
                    result.values = founded;
                    result.count = founded.size();
                } else {
                    result.values = arraylist;
                    result.count = arraylist.size();
                }

                return result;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                for (Item item : (List<Item>) results.values){
                    add(item);
                }
                notifyDataSetChanged();
            }
        };

    }
}
