package kr.co.tjeit.finaltest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.co.tjeit.finaltest.R;
import kr.co.tjeit.finaltest.datas.Company;

public class CompanyAdapter extends ArrayAdapter<Company> {

    Context mContext;
    List<Company> mList;
    LayoutInflater inf;

    public CompanyAdapter(Context context, List<Company> list) {
        super(context, R.layout.company_list, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }



    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inf.inflate(R.layout.company_list, null);
        }

        ImageView logo = row.findViewById(R.id.logo);
        TextView name = row.findViewById(R.id.name);

        Company data = mList.get(position);

        Glide.with(mContext).load(data.getLogo()).into(logo);

        name.setText(data.getName());




        return row;

    }
}
