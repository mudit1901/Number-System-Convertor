package com.example.codeconvertor;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_TEXT;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Final extends AppCompatActivity {
    private Spinner spinner1;
    private Spinner spinner2;

    private EditText editText;
    private TextView textView;
    private String Text,Text1;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spinner1=findViewById(R.id.spineer1);
        spinner2=findViewById(R.id.spineer2);

        editText=findViewById(R.id.input);

        textView=findViewById(R.id.output);
        button=findViewById(R.id.butoon1);




        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.input, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        button.setEnabled(false);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Text=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Final.this, "From : " + Text, Toast.LENGTH_SHORT).show();

                if(Text.equals("Hexadecimal"))
                {
                    editText.setInputType(TYPE_CLASS_TEXT);
                    editText.setHint("Enter Hexadecimal Number");

                }
                else if(Text.equals("Decimal"))
                {
                    editText.setInputType(TYPE_CLASS_NUMBER);
                    editText.setHint("Enter Decimal Number");

                }
                else if(Text.equals("Binary"))
                {
                    editText.setInputType(TYPE_CLASS_NUMBER);
                    editText.setHint("Enter Binary Number");

                }
                else if(Text.equals("Octal"))
                {
                    editText.setInputType(TYPE_CLASS_NUMBER);
                    editText.setHint("Enter Octal Number");
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("Please Select Something");


            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Text1=adapterView.getItemAtPosition(i).toString();
                Toast.makeText(Final.this, "To : " + Text1, Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("Please Select Something");

            }
        });


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence.toString().isEmpty())
                {
                   button.setEnabled(false);
                    Toast.makeText(Final.this, "Please Enter Value", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    button.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

       button.setOnClickListener(new View.OnClickListener() {

           @SuppressLint("SetTextI18n")
           @Override

           public void onClick(View view) {
               String Mudit=editText.getText().toString();
               int toDeci=Integer.parseInt(Mudit);




               if(Text.equals("Decimal") && Text1.equals("Binary") && !Mudit.isEmpty())
               {
                       String Mud=Integer.toBinaryString(toDeci);
                       textView.setText("Binary : " + Mud);
               }

               else if(Text.equals("Decimal") && Text1.equals("Octal") && !Mudit.isEmpty())
               {
                   String Mud=Integer.toOctalString(toDeci);
                   textView.setText("Octal : " + Mud);
               }

               else if(Text.equals("Decimal") && Text1.equals("Hexadecimal") && !Mudit.isEmpty())
               {
                   String Mud=Integer.toHexString(toDeci).toUpperCase();
                   textView.setText("Hexadecimal : " + Mud);
               }
               else if(Text.equals("Decimal") && Text1.equals("Decimal") && !Mudit.isEmpty())
               {
                   textView.setText("Please Select Valid Operation");
               }

               else if(Text.equals("Binary") && Text1.equals("Decimal")  && !Mudit.isEmpty())
               {
                   if(isBinaryNumber(toDeci))
                   {
                       Long Number=Long.parseLong(Mudit);
                       Long Fan= Long.valueOf(convertBinaryToDecimal(Number));
                       String finaloutput=Fan.toString();

                       textView.setText("Decimal : " + finaloutput);
                   }
                   else if(Mudit.equals("01") || Mudit.equals("1"))
                   {
                       textView.setText("Decimal : " + " 1");
                   }
                   else if(Mudit.equals("00") || Mudit.equals("0"))
                   {
                       textView.setText("Decimal : " + " 0");

                   }
                   else
                   {
                       textView.setText("Please Enter the Valid Binary String");
                   }


               }

               else if(Text.equals("Binary") && Text1.equals("Octal") &&  !Mudit.isEmpty())
               {
                   if(isBinaryNumber(toDeci))
                   {
                       Long Number=Long.parseLong(Mudit);
                       Long Fan= Long.valueOf(convertBinarytoOctal(Number));
                       String finaloutput=Fan.toString();

                       textView.setText("Octal : " + finaloutput);
                   }

                   else if(Mudit.equals("01") || Mudit.equals("1"))
                   {
                       textView.setText("Octal : "+ " 1");
                   }
                   else if(Mudit.equals("00") || Mudit.equals("0"))
                   {
                       textView.setText("Octal : " + " 0");

                   }
                   else
                   {
                       textView.setText("Please Enter the Valid Binary String");
                   }

               }

               else if(Text.equals("Binary") && Text1.equals("Hexadecimal")  && !Mudit.isEmpty())
               {
                   if(isBinaryNumber(toDeci))
                   {
                       Long Number=Long.parseLong(Mudit);
                       Long Fan= Long.valueOf(convertBinaryToDecimal(Number));
                       String finaloutput=Long.toHexString(Fan).toUpperCase();

                       textView.setText("HexaDecimal : " + finaloutput);
                   }

                   else if(Mudit.equals("01") || Mudit.equals("1"))
                   {
                       textView.setText("Hexadecimal : " + "1");
                   }
                   else if(Mudit.equals("00") || Mudit.equals("0"))
                   {
                       textView.setText("Hexadecimal : " + " 0");

                   }
                   else
                   {
                       textView.setText("Please Enter the Valid Binary String");
                   }

               }

               else if(Text.equals("Binary") && Text1.equals("Binary") && !Mudit.isEmpty())
               {

                   textView.setText("Please Select Valid Operation and Check Input");
               }

               else if(Text.equals("Octal") && Text1.equals("Decimal") && !Mudit.isEmpty())
               {
                   int decimal=Integer.parseInt(Mudit,8);
                   textView.setText("Decimal : " + decimal);
               }

               else if(Text.equals("Octal") && Text1.equals("Binary") && !Mudit.isEmpty())
               {
                   int decimal=Integer.parseInt(Mudit,8);
                   String Binary=Integer.toBinaryString(decimal);
                   textView.setText("Binary : " + Binary);
               }

               else if(Text.equals("Octal") && Text1.equals("Hexadecimal") && !Mudit.isEmpty())
               {


                   int decimal=Integer.parseInt(Mudit,8);
                   String Hexa=Integer.toHexString(decimal).toUpperCase();
                   textView.setText("Hexadecimal : " + Hexa);

               }

               else if(Text.equals("Octal") && Text1.equals("Octal") && !Mudit.isEmpty())
               {

                   textView.setText("Please Select Valid Operation");
               }


               else if(Text.equals("Hexadecimal") && Text1.equals("Decimal"))
               {

                       int decimal=Integer.parseInt(Mudit,16);
                       textView.setText("Decimal : " + decimal);
               }

               else if(Text.equals("Hexadecimal") && Text1.equals("Binary") && !Mudit.isEmpty())
               {
                   int decimal=Integer.parseInt(Mudit,16);
                   String Binary=Integer.toBinaryString(decimal);
                   textView.setText("Binary : " + Binary);
               }

               else if(Text.equals("Hexadecimal") && Text1.equals("Octal") && !Mudit.isEmpty())
               {
                   int decimal=Integer.parseInt(Mudit,16);
                   String Octal=Integer.toOctalString(decimal);
                   textView.setText("Octal : " + Octal);
               }

               else if(Text.equals("Hexadecimal") && Text1.equals("Hexadecimal") && !Mudit.isEmpty())
               {

                   textView.setText("Please Select Valid Operation");
               }
           }
       });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder alertdialog=new AlertDialog.Builder(Final.this,R.style.MyDialog);
                alertdialog.setTitle("Confirm Exit...!!");
                alertdialog.setMessage("Do you want to Exit App ?");
                alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();
                    }
                });

                alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                alertdialog.show();

            }
        });

    }



    public static boolean isBinaryNumber(int num)
    {


        if (num == 0 || num == 1
                || num < 0) {
            return false;
        }

        while (num != 0) {

            if (num % 10 > 1) {
                return false;
            }
            num = num / 10;
        }
        return true;
    }


    public static int convertBinaryToDecimal(long num) {
        int decimalNumber = 0, i = 0;
        long remainder;

        while (num != 0) {
            remainder = num % 10;
            num /= 10;
            decimalNumber += remainder * Math.pow(2, i);
            ++i;
        }

        return decimalNumber;
    }


    public static int convertBinarytoOctal(long binaryNumber) {
        int octalNumber = 0, decimalNumber = 0, i = 0;

        while (binaryNumber != 0) {
            decimalNumber += (binaryNumber % 10) * Math.pow(2, i);
            ++i;
            binaryNumber /= 10;
        }

        i = 1;

        while (decimalNumber != 0) {
            octalNumber += (decimalNumber % 8) * i;
            decimalNumber /= 8;
            i *= 10;
        }

        return octalNumber;
    }

}