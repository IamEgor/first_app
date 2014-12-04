package com.example.egor_gruk.first_app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Егор on 06.11.2014.
 */
public class SomeAdapter extends ListActivity {
    private static final String[] items = {"lorem", "ipsum", "dolor",
            "sit", "amet",
            "consectetuer", "adipiscing", "elit", "morbi", "vel",
            "ligula", "vitae", "arcu", "aliquet", "mollis",
            "etiam", "vel", "erat", "placerat", "ante",
            "porttitor", "sodales", "pellentesque", "augue", "purus"};

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setListAdapter(new IconicAdapter());
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, "item " + item + " was selected", Toast.LENGTH_LONG).show();
        if (item.equals("lorem")) {
            Intent intent = new Intent("com.example.egor_gruk.first_app.SwipeActiv");
            startActivity(intent);
        }
        if(item.equals("ipsum"))
        {
            Intent intent = new Intent("com.example.egor_gruk.first_app.start_for_result_package.FirstActivity");
            startActivity(intent);
        }
    }

    private String getModel(int position) {
        return (((IconicAdapter) getListAdapter()).getItem(position));
    }

    class IconicAdapter extends ArrayAdapter<String> {
        IconicAdapter() {
            super(SomeAdapter.this, R.layout.row, R.id.label_tv,
                    items);
        }

        @Override
        public View getView(int position, View convertView,
                            ViewGroup parent) {
            View row = super.getView(position, convertView, parent);
            ViewHolder holder = (ViewHolder) row.getTag();
            if (holder == null) {
                holder = new ViewHolder(row);
                row.setTag(holder);
            }
            if (this.getItem(position).length() > 4) {
                //if (getModel(position).length()>4) { //способ из книги
                holder.icon.setImageResource(R.drawable.vk);
            } else {
                holder.icon.setImageResource(R.drawable.facebook);
            }
            holder.size.setText(String.format(getString(R.string.size_template), items[position].length()));
            return (row);
        }
    }


    class ViewHolder {
        ImageView icon = null;
        TextView size = null;

        ViewHolder(View row) {
            this.icon = (ImageView) row.findViewById(R.id.icon_iv);
            this.size = (TextView) row.findViewById(R.id.size_tv);
        }
    }
}
