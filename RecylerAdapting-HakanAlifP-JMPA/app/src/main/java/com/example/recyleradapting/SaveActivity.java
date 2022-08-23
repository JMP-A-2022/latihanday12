package com.example.recyleradapting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SaveActivity extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Parcelable item = extras.getParcelable(MainActivity.TRANSACTION_KEY);
            index = extras.getInt(MainActivity.INDEX_KEY, 0);
            descriptionInput.setText(item.getDescription());
            amountInput.setText(String.valueOf(item.getAmount()));
            if (item.getType() == Transaction.Type.DEBIT) {
                typeRadioGroup.check(R.id.radio_debit);
            } else if (item.getType() == Transaction.Type.CREDIT) {
                typeRadioGroup.check(R.id.radio_credit);
            }
        }
    }

    private SurfaceControl.Transaction.Type getCheckedType() {
        if (typeRadioGroup.getCheckedRadioButtonId() == R.id.radio_debit) {
            return SurfaceControl.Transaction.Type.DEBIT;
        } else if (typeRadioGroup.getCheckedRadioButtonId() == R.id.radio_credit) {
            return SurfaceControl.Transaction.Type.CREDIT;
        }
        return SurfaceControl.Transaction.Type.EMPTY;
    }

    @Override
    public void onClick(View view) {

    }
}
