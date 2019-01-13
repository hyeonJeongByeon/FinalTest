package kr.co.tjeit.finaltest;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.finaltest.adapters.CompanyAdapter;
import kr.co.tjeit.finaltest.datas.Company;
import kr.co.tjeit.finaltest.utils.ConnectServer;

public class MainActivity extends BaseActivity {

    List<Company> companyList = new ArrayList<Company>();


    List<Company> company = new ArrayList<Company>();
    CompanyAdapter mAdapter;


    private android.widget.ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        mAdapter = new CompanyAdapter(mContext, companyList);
        


        getCompanyFromServer();
    }

    void getCompanyFromServer(){

        ConnectServer.getRequestBanks(mContext, new ConnectServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("회사 목록", json.toString());

                try {
                    int code = json.getInt("code");

                    if (code == 200) {

                        JSONObject data = json.getJSONObject("data");
                        JSONArray company = data.getJSONArray("company");

                        for (int i = 0; i <company.length(); i++) {
                            JSONObject companyJson = company.getJSONObject(i);

                            Company companyObject = Company.getCompanyFromJson(companyJson);
                            companyList.add(companyObject);

                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    @Override
    public void bindViews() {

        this.list = (ListView) findViewById(R.id.list);

    }
}
