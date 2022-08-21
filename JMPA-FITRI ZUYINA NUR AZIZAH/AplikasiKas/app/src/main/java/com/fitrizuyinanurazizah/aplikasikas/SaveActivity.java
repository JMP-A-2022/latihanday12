package com.fitrizuyinanurazizah.aplikasikas;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SaveActivity extends AppCompatActivity implements View.OnClickListener {
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        add = findViewById(R.id.fab);
        add.setOnClickListener(this);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            item = extras.getParcelable(MainActivity.TRANSACTION_KEY);
            index = extras.getInt(MainActivity.INDEX_KEY, 0);
            descriptionInput.setText(item.getDescription());
            amountInput.setText(String.valueOf(item.getAmount()));
            if (item.getType() == SurfaceControl.Transaction.Type.DEBIT) {
                RadioGroup.check(R.id.rb_debit);
            } else if (item.getType() == SurfaceControl.Transaction.Type.CREDIT) {
                RadioGroup.check(R.id.rb_kredit);
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
    public void onClick(View v) {
        if(R.id.fab){
            Intent intent = new Intent(SaveActivity.this, MainActivity.class);
            intent.putExtra(TRANSACTION_KEY, new SurfaceControl.Transaction());
            startActivityForResult(intent, INSERT_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            SurfaceControl.Transaction transaction = data.getParcelableExtra(TRANSACTION_KEY);
            if (requestCode == INSERT_REQUEST) {
                account.addTransaction(transaction);
            }
            adapter.notifyDataSetChanged();
            welcome_text.setText(String.valueOf(account.getBalance()));
        }else if (requestCode == UPDATE_REQUEST) {
            int index = data.getIntExtra(INDEX_KEY, 0);
            account.updateTransaction(index, transaction);
        }
    }
}
