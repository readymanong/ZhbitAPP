package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.SchoolNews;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class SchoolNewAdapter extends BaseAdapter {

    private List<SchoolNews> newsList;
    private View view;
    private Context mContext;
    private ViewHolder viewHolder;

    public SchoolNewAdapter(Context mContext, List<SchoolNews> newsList) {
        this.newsList = newsList;
        this.mContext= mContext;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.schoolnews_item,
                    null);
            viewHolder = new ViewHolder();
            viewHolder.newsTitle = (TextView) view
                    .findViewById(R.id.news_title);
            viewHolder.newsTime = (TextView)view.findViewById(R.id.news_time);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.newsTitle.setText(newsList.get(position).getNewsTitle());
        viewHolder.newsTime.setText("来自 : "+newsList.get(position).getNewsTime());
        return view;
    }

    class ViewHolder{
        TextView newsTitle;
        TextView newsTime;
    }

}
