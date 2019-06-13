package com.example.ubereats;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private TextView debug;
    public class Data{
        String name;
        HashMap<String, Double> items = new HashMap<String, Double>();
        HashMap<String, String> list = new HashMap<String, String>();
        Double tax;
        Double delivery;
        Double service;

        public Data(){

        }

        public void addItem(String name, Double price){
            if (items.containsKey(name)) {
                items.put(name, items.get(name) + price);
                list.put(name, list.get(name) + ", " + Double.toString(price));
            }else{
                items.put(name, price);
                list.put(name, Double.toString(price));
            }
        }

        public void setTax(Double tax) {
            this.tax = tax;
        }

        public void setDelivery(Double delivery){
            this.delivery = delivery;
        }

        public void setService(Double service) {
            this.service = service;
        }

        public String toStringer(){
            String ans = "\n";
            for (String n : items.keySet()){
                Double curr = items.get(n) + ((tax + delivery + service)/items.keySet().size());
                ans = ans + n + " is paying $" + curr + "\n" + list.get(n) + "\n\n";
            }

            return ans;
        }

    }

    public Data data = new Data();
    private TextView tv;
    private Button addBtn;
    private Button genBtn;
    private EditText num;
    private EditText quant;
    private EditText tax;
    private EditText del;
    private EditText ser;
    private AutoCompleteTextView name;

    public Double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] skwad = new String[]{
                "Arjun J", "Nealyn","Rohith","TK", "Arjun N", "Prateek", "Srivats", "Geoffrey",
                "Rishabh", "Chris Kim", "Kareem", "Jeff", "Liam", "Ruky", "Swapna"
        };
        name = findViewById(R.id.name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, skwad);
        name.setAdapter(adapter);

        quant = (EditText) findViewById(R.id.quantity);
        num = (EditText) findViewById(R.id.price);
        tax = (EditText) findViewById(R.id.tax);
        del = (EditText) findViewById(R.id.delivery);
        ser = (EditText) findViewById(R.id.service);

        addBtn = findViewById(R.id.add);
        tv = findViewById(R.id.order);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price = (Double.parseDouble(num.getText().toString()) * Integer.parseInt(quant.getText().toString()));
                data.addItem(name.getText().toString(), price);
                data.setTax(Double.parseDouble(tax.getText().toString()));
                data.setService(Double.parseDouble(ser.getText().toString()));
                data.setDelivery(Double.parseDouble(del.getText().toString()));
                tv.setText(data.toStringer());
            }
        });
    }
}
