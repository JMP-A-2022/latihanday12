package com.example.aplikasikas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SaveActivity extends AppCompatActivity implements View.OnClickListener{
    Button add;
    private Parcelable item, index, descriptionInput, amountInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        add = findViewById(R.id.ic_add);
        add.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            item = extras.getParcelable(MainActivity.TRANSACTION_KEY);
            index = extras.getInt(MainActivity.INDEX_KEY, 0);
            descriptionInput.setText(item.getDescription());
            amountInput.setText(String.valueOf(item.getAmount()));
            if (item.getType() == SurfaceControl.Transaction.Type.DEBIT) {
                typeRadioGroup.check(R.id.radio_debit);
            } else if (item.getType() == SurfaceControl.Transaction.Type.CREDIT) {
                typeRadioGroup.check(R.id.radio_credit);
            }
        }
    }
    private SurfaceControl.Transaction.Type getCheckedType() {
        if (RadioGroup.getCheckedRadioButtonId() == R.id.rb_debit) {
            return SurfaceControl.Transaction.Type.DEBIT;
        } else if (RadioGroup.getCheckedRadioButtonId() == R.id.rb_kredit) {
            return SurfaceControl.Transaction.Type.CREDIT;
        }
        return SurfaceControl.Transaction.Type.EMPTY;
    }
    @Override
    public void onClick(View view) {
        if(R.id.ic_add){
            Intent intent = new Intent(SaveActivity.this, MainActivity.class);
            intent.putExtra(TRANSACTION_KEY, new SurfaceControl.Transaction());
            startActivityForResult(intent, INSERT_REQUEST);
        }

    }
}
