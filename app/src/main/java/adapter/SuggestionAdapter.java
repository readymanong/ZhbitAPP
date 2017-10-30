package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dm.zhbit.R;

import com.example.dm.zhbit.beans.Suggestion;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/21.
 */

public class SuggestionAdapter extends ArrayAdapter<Suggestion> {
    private LayoutInflater mInflater;
    private int resource;
    public  SuggestionAdapter(Context context, int resource, ArrayList<Suggestion> objects) {
        super(context, resource, objects);
        mInflater = LayoutInflater.from(context);
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Suggestion suggestion = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view=LayoutInflater.from(getContext()).inflate(resource,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.a_tea= (TextView) view.findViewById(R.id.a_tea);
            viewHolder.a_course= (TextView) view.findViewById(R.id.a_course);
            viewHolder.a_suggest= (TextView) view.findViewById(R.id.a_suggest);
            viewHolder.sg_content= (TextView) view.findViewById(R.id.sg_content);
            view.setTag(viewHolder);//闪退的解决关键
        }else {
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        //viewHolder.sg_content.setText(suggestion.getSuggestionContent());
        viewHolder.a_tea.setText(suggestion.getComment_tea());
        viewHolder.a_course.setText(suggestion.getComment_course());
        viewHolder.a_suggest.setText(suggestion.getSuggestionContent());
        return view;
    }
    class ViewHolder {
        TextView a_tea;
        TextView a_course;
        TextView a_suggest;
        TextView sg_content;
    }
}
