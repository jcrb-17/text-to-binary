package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public int binaryToDecimal(String binary){
        int sum = 0;
        int temp = 0;
        for(int i = binary.length()-1;i>=0;i--){
            if(binary.charAt(i)=='1'){
                sum += Math.pow(2,temp);
                temp += 1;
                continue;
            }else
                temp+=1;

        }
        return sum;
    }
    public HashMap createHashMap(){
        HashMap<Integer,String> map = new HashMap<>();
        String alpha = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM,.-;:_+/*!\"$%&/()=?1234567890";
        for(int i=0;i<alpha.length();i++){
            map.put(String.valueOf(alpha.charAt(i)).hashCode(),String.valueOf(alpha.charAt(i)));
        }
        map.put(32," ");
        return map;
    }
    public String reverseString(String n){
        String temp = "";
        for (int i=n.length()-1;i>=0;i--){
            temp += n.charAt(i);
        }
        return temp;
    }
    public String toBinaryASCII(int n){
        int div = 0;
        int coc = 0;
        String bin = "";
        div = n / 2;
        coc = n % 2;
        n = div;
        bin += Integer.toString(coc);
        while(div>=2){
            div = n / 2;
            coc = n % 2;
            n = div;
            bin += Integer.toString(coc);
        }
        bin += "1";
        if(bin.length()!=7){
            for(int i=bin.length();i<7;i++){
                bin += "0";
            }
        }
        bin = reverseString(bin);
        return bin;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input = (EditText) findViewById(R.id.input);
        TextView text = (TextView) findViewById(R.id.text);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        HashMap map = createHashMap();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String x = "";

                String entrada = input.getText().toString();
                for(int i=0;i<entrada.length();i++){
                    x += toBinaryASCII(entrada.charAt(i));
                }

                text.setText(x);
            }
        });

        //Decode
        button2.setOnClickListener(new View.OnClickListener() {
            Pattern distintoaUnoYCero= Pattern.compile("[23456789A-Za-z]");
            @Override
            public void onClick(View view) {
                String string = "";

                Matcher matcher = distintoaUnoYCero.matcher(input.getText().toString());
                boolean found = matcher.find();//contiene otros numeros o letras, para decode, en el input
                if (input.getText().toString().length() % 7 == 0 && input.getText().toString().length() !=0 && found == false ) {
                    int div = input.getText().toString().length() / 7; //La cantidad de veces
                    int aux = 7;
                    int begin = 0;
                    int temp2 = 0;
                    String res = "";
                    String bin;
                    for(int i=0;i<div;i++){
                        bin = input.getText().toString().substring(begin,aux);//7 bit binary code
                        temp2 = binaryToDecimal(bin);
                        res+=map.get(temp2);
                        begin = begin+7;
                        aux = aux+7;
                    }
                    text.setText(res);
                }
                else if(found){
                    text.setText("For decoding, values need to be 0 or 1");
                }

            }
        });


    }
}
