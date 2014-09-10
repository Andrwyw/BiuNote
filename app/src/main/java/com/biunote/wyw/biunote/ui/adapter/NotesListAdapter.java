package com.biunote.wyw.biunote.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.biunote.wyw.biunote.R;
import com.biunote.wyw.biunote.model.NoteEntryModel;

import java.util.List;
import java.util.Random;

/**
 * Created by wang on 2014/8/19.
 */
public class NotesListAdapter extends BaseAdapter {


    private List<NoteEntryModel> mModelList;
    private Context mContext;

    public NotesListAdapter(Context context, List<NoteEntryModel> modelList) {

        if (modelList == null) {
            throw new IllegalArgumentException(MODEL_LIST_CANT_BE_NULL);
        }

        mContext = context;
        mModelList = modelList;
    }

    public NotesListAdapter(Context context) {
        mContext = context;
    }

    public void setDataSource(List<NoteEntryModel> modelList) {

        if (modelList == null) {
            throw new IllegalArgumentException(MODEL_LIST_CANT_BE_NULL);
        }
        mModelList = modelList;
    }

    @Override
    public int getCount() {
        return mModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return mModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_notes,null);
            holder = new ViewHolder();
            holder.rl_listview_row_container = (RelativeLayout) convertView.findViewById(R.id.container);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.tv_crated_time = (TextView) convertView.findViewById(R.id.tv_created_time);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        NoteEntryModel model = mModelList.get(position);
        holder.tv_content.setText(model.getContent());
        holder.tv_crated_time.setText(model.getCreatedTime());
        setRandomBg(holder);

        return convertView;
    }

    static int sLastIndex = 0;
    @SuppressLint("NewApi")
    private void setRandomBg(ViewHolder holder) {
        int[] colorIntArray = mContext.getResources().getIntArray(R.array.grident_color_array);
        Random random = new Random();
        int index = 0;

        do {
            index = random.nextInt(colorIntArray.length);
            //渐变颜色两两匹配,由奇->偶
            if (index % 2 == 1){
                index -= 1;
            }
        }while (sLastIndex == index);
        sLastIndex = index;

        int[] gradientColorArray = {colorIntArray[index],colorIntArray[index+1]};
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,gradientColorArray);
        drawable.setCornerRadius(5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            holder.rl_listview_row_container.setBackground(drawable);
        }else{
            holder.rl_listview_row_container.setBackgroundDrawable(drawable);
        }
    }

    private final class ViewHolder {
        public RelativeLayout rl_listview_row_container;
        public TextView tv_content;
        public TextView tv_crated_time;
    }

    private static final String MODEL_LIST_CANT_BE_NULL = "modelList cant be null!";

}
