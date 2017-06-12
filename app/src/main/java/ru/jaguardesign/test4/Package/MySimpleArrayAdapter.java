package ru.jaguardesign.test4.Package;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ru.jaguardesign.test4.R;

/**
 * Created by x on 04.06.2017.
 */
public class MySimpleArrayAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Meet> objects;

    MySimpleArrayAdapter(Context context, ArrayList<Meet> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // пункт списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.li, parent, false);
        }

        Meet p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.textView2)).setText(p.title);
        ((TextView) view.findViewById(R.id.textView3)).setText(p.titlec + "");
      //  ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);

      //  CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        // присваиваем чекбоксу обработчик
      //  cbBuy.setOnCheckedChangeListener(myCheckChangeList);
        // пишем позицию
     //   cbBuy.setTag(position);
        // заполняем данными из товаров: в корзине или нет
      //  cbBuy.setChecked(p.box);
        return view;
    }

    // товар по позиции
    Meet getProduct(int position) {
        return ((Meet) getItem(position));
    }
}
