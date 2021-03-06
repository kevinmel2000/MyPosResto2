package com.talaga.myposresto.admin;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.talaga.myposresto.util.SqlAdapter;
import com.talaga.myposresto.R;

/**
 * Created by compaq on 03/20/2016.
 */
public class CustomersList extends AppCompatActivity {
    ListView lv1;
    private BaseAdapter mBaseAdapter=null;
    private int[] controls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent4 = new Intent(getBaseContext(),CustomersInput.class);
                startActivity(myIntent4);
            }
        });
        FloatingActionButton fabSearch = (FloatingActionButton) findViewById(R.id.fabSearch);
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText("Customers Master");

        lv1 = (ListView) findViewById(R.id.list1);
        loadData();
    }
    private void loadData(){
        controls= new int[]{R.id.company, R.id.customer_no, R.id.address};
        mBaseAdapter=new SqlAdapter(getBaseContext(), R.layout.row_customer,
                controls, "select company,customer_no,address from ksr_customers");
        lv1.setAdapter(mBaseAdapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView kode = (TextView) view.findViewById(R.id.customer_no);
                Intent myIntent4 = new Intent(getBaseContext(),CustomersInput.class);
                myIntent4.putExtra("kode",kode.getText().toString());
                myIntent4.putExtra("mode","edit");
                startActivity(myIntent4);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}