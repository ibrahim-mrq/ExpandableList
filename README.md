### Working example

Working example
For more details on how to use this library please refer to the example in this repository. You can see a video of the example working here:

![](https://raw.githubusercontent.com/tamzi/AndroidExpandingViewLibrary/master/art/shoping_list.gif)

## Adding the Library to gradle file

```
dependencies {
    implementation 'com.github.ibrahim-mrq:expandable:1.0.0'
}
```
or 
```
implementation project(path: ':expandable')
```

## Using the Library

### Layouts
###### ExpandingList in activity.xml

```
<com.diegodobelo.expandingview.ExpandingList
        android:id="@+id/expanding_list_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```
expanding_item.xml

```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="94dp"
    android:layout_marginBottom="5dp"
    tools:ignore="MissingDefaultResource">

    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        android:gravity="center_vertical|start"
        android:textColor="@color/white"
        android:textSize="22sp"
        app:fontFamily="Muli-Regular.ttf" />

    <ImageView
        android:id="@+id/add_more_sub_items"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_alignParentEnd="true"
        android:layout_alignParentRight="true"
        android:layout_centerVertical="true"
        android:layout_marginEnd="16dp"
        android:layout_marginRight="16dp"
        android:src="@drawable/ic_arrow_down" />

</RelativeLayout>
```
expanding_sub_item.xml
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <TextView
        android:id="@+id/sub_id"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_marginStart="8dp"
        android:textColor="@color/white"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/sub_end"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/sub_id"
        android:layout_alignParentStart="true"
        android:layout_marginStart="8dp"
        android:textColor="@color/white"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/sub_start"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/sub_end"
        android:layout_alignParentStart="true"
        android:layout_marginStart="8dp"
        android:textColor="@color/white"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/sub_phone"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/sub_start"
        android:layout_alignParentStart="true"
        android:layout_marginStart="8dp"
        android:textColor="@color/white"
        android:textSize="18sp" />

</RelativeLayout>
```
expanding_layout.xml
```
<?xml version="1.0" encoding="utf-8"?>
<com.mrq.expandable.model.ExpandingItem xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:animation_duration="250"
    app:indicator_margin_left="16dp"
    app:indicator_margin_right="16dp"
    app:indicator_size="42dp"
    app:item_layout="@layout/expanding_item"
    app:separator_layout="@layout/separator_layout"
    app:show_animation="true"
    app:show_indicator="true"
    app:start_collapsed="true"
    app:sub_item_layout="@layout/expanding_sub_item" />
```
separator_layout.xml

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <View
        android:layout_width="match_parent"
        android:layout_height="2dp"
        android:background="@color/white" />

</LinearLayout>
```
    
### Java code

```
private ExpandingList mExpandingList;
private ArrayList<Product> list = new ArrayList<>();
```
onCreate 

```
    mExpandingList = findViewById(R.id.expanding_list_main);
    
    list.add(new Product("1", "ibrahim", "22/10/2000", "0592440530", R.drawable.apple, R.color.colorAccent));
    list.add(new Product("2", "ibrahim", "22/10/2000", "0592440530", R.drawable.apple, R.color.colorAccent));
    list.add(new Product("3", "ibrahim", "22/10/2000", "0592440530", R.drawable.apple, R.color.colorAccent));  
    list.add(new Product("4", "ibrahim", "22/10/2000", "0592440530", R.drawable.apple, R.color.colorAccent));
    list.add(new Product("5", "ibrahim", "22/10/2000", "0592440530", R.drawable.apple, R.color.colorAccent));
    
    addItem(list);
    
```

fun addItem
```
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
```
Product.java
```
package com.mrq.expandablelist;

public class Product {

    String id, name, date, phone;
    int img,color;

    public Product(String id, String name, String date, String phone, int img, int color) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.img = img;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

```









