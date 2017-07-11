package com.et.et.customcontrolsdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wangxiongfeng
 * @date 2017/7/10 0010 11:38
 */

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Bean> list;

    public ListViewAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.itemName.setText(list.get(i).getName());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.item_name)
        TextView itemName;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
