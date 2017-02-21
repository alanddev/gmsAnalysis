package com.alanddev.gmscall.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by longty on 28/11/2015.
 */
public class CurrencyTextWatcher implements TextWatcher {

    boolean mEditing;
    EditText editText;
    public CurrencyTextWatcher(EditText editText) {
        mEditing = false;
        this.editText = editText;
    }
    @Override
    public  void afterTextChanged(Editable s) {

//            }//            String digits = s.toString().replaceAll("\\D", "");
//            NumberFormat nf = NumberFormat.getCurrencyInstance();
//            try {
//                String formatted = nf.format(Double.parseDouble(digits) / 100);
//                s.replace(0, s.length(), formatted);
//            } catch (NumberFormatException nfe) {
//                s.clear();

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###,###.00", symbols);
            String amt = decimalFormat.format(Integer.parseInt(s.toString()));
            editText.setText(amt);

//                String amt = decimalFormat.format(Integer.parseInt(s.toString()));
//                //amountEdit.setText(amt);
//                EditText amountEdit = (EditText)findViewById(R.id.txtCurrency);
//                //amountEdit.removeTextChangedListener(this);
//                amountEdit.setText(amt);
//                //amountEdit.addTextChangedListener(this);


    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}