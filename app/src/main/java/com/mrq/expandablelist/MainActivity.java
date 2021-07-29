package com.mrq.expandablelist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mrq.expandable.model.ExpandingItem;
import com.mrq.expandable.model.ExpandingList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ExpandingList mExpandingList;
    private ArrayList<Product> list;
    FloatingActionButton fab;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mExpandingList = findViewById(R.id.expanding_list_main);
        fab = findViewById(R.id.fab);

        list = new ArrayList<>();

        ArrayList<String> listEndDate = new ArrayList<>();
        listEndDate.add("22/07/2021");
        listEndDate.add("23/07/2021");
        listEndDate.add("24/07/2021");
        listEndDate.add("25/07/2021");
        listEndDate.add("26/07/2021");
        listEndDate.add("27/07/2021");
        listEndDate.add("28/07/2021");
        listEndDate.add("29/12/2021");
        listEndDate.add("29/07/2021");
        listEndDate.add("30/07/2021");
        listEndDate.add("31/07/2021");
        listEndDate.add("01/08/2021");
        listEndDate.add("02/08/2021");
        listEndDate.add("03/08/2021");
        listEndDate.add("04/08/2021");
        listEndDate.add("05/08/2021");
        listEndDate.add("06/08/2021");
        listEndDate.add("07/08/2021");
        listEndDate.add("08/08/2021");
        listEndDate.add("09/08/2021");
        listEndDate.add("10/08/2021");
        listEndDate.add("11/08/2021");
        listEndDate.add("12/08/2021");
        listEndDate.add("13/08/2021");
        listEndDate.add("14/08/2021");
        listEndDate.add("15/08/2021");
        listEndDate.add("16/08/2021");
        listEndDate.add("17/08/2021");
        listEndDate.add("18/08/2021");
        listEndDate.add("19/08/2021");
        listEndDate.add("22/07/2021");
        listEndDate.add("23/07/2021");
        listEndDate.add("24/07/2021");
        listEndDate.add("20/08/2021");
        listEndDate.add("21/08/2021");
        listEndDate.add("22/08/2021");
        listEndDate.add("23/08/2021");
        listEndDate.add("24/08/2021");
        listEndDate.add("25/08/2021");
        listEndDate.add("26/08/2021");
        listEndDate.add("27/08/2021");
        listEndDate.add("28/08/2021");
        listEndDate.add("29/08/2021");
        listEndDate.add("29/12/2021");
        listEndDate.add("22/07/2021");
        listEndDate.add("23/07/2021");
        listEndDate.add("24/07/2021");

        String startDate = "26/07/2021";


        for (int i = 0; i < listEndDate.size(); i++) {
            compareDates(startDate, listEndDate.get(i));
            if (startDate.equals(listEndDate.get(i))) {
                Log.d("asdasdasd", "Already exists");
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void compareDates(String d1, String d2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);
            Period diff = Period.between(LocalDate.parse(d1, df), LocalDate.parse(d2, df));

            int a = (30 * diff.getMonths()) + diff.getDays();
            if (date2.after(date1)) {
                if (a < 10) {
                    list.add(new Product("" + a, "ibrahim",
                            d2, "26/07/2021", "0592440530", R.drawable.apple, R.color.colorAccent));
                } else {
                    list.add(new Product("" + a, "ibrahim",
                            d2, "26/07/2021", "0592440530", R.drawable.apple, R.color.blue));
                }
            } else {
                list.add(new Product("" + a, "ibrahim",
                        d2, "26/07/2021", "123456789", R.drawable.apple, R.color.orange));
            }
            addItem(list);
            list.clear();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    private void addItem(ArrayList<Product> lists) {
        final ExpandingItem item = mExpandingList.createNewItem(R.layout.expanding_layout);
        if (item != null) {
            item.createSubItems(lists.size());
            for (int i = 0; i < lists.size(); i++) {
                item.setIndicatorColorRes(lists.get(i).getColor());
                item.setIndicatorIconRes(lists.get(i).getId());
                ((TextView) item.findViewById(R.id.title)).setText(lists.get(i).getName());
                final View view = item.getSubItemView(i);
                ((TextView) view.findViewById(R.id.sub_id)).setText(lists.get(i).getId());
                ((TextView) view.findViewById(R.id.sub_end)).setText(lists.get(i).getDateEnd());
                ((TextView) view.findViewById(R.id.sub_start)).setText(lists.get(i).getDateStart());
                ((TextView) view.findViewById(R.id.sub_phone)).setText(lists.get(i).getPhone());
            }

            item.setOnClickListener(v -> {
                item.toggleExpanded();
                if (item.isExpanded()) {
                    ((ImageView) item.findViewById(R.id.add_more_sub_items)).setImageResource(R.drawable.ic_arrow_right);
                } else {
                    ((ImageView) item.findViewById(R.id.add_more_sub_items)).setImageResource(R.drawable.ic_arrow_down);
                }
            });


            item.findViewById(R.id.add_more_sub_items)
                    .setOnClickListener(vs -> {
                        if (item.isExpanded()) {
                            Log.d("asdasdasd", "tokens");
                        }
                    });

        }
    }
}