package codepath.examples.tipcalc;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	public double selectedTip = 0.00; // used to store our tip state
	public double grossAmount = 0.00; // used to store our gross amount state

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EditText etGrossAmount = (EditText) findViewById(R.id.etGrossAmount);
		etGrossAmount.setSelection(0);
		etGrossAmount.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				String inputValue = s.toString();
				if(inputValue.length() > 0) {
					grossAmount = Double.parseDouble(inputValue);
				} else {
					grossAmount = 0.00;
				}
				updateTipField();
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				//stub
			}
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String inputValue = s.toString();
				if(inputValue.length() > 0) {
					grossAmount = Double.parseDouble(inputValue);
				} else {
					grossAmount = 0.00;
				}
				updateTipField();
			}
		});

		// lots of code repetition...
		Button btnDef10 = (Button) findViewById(R.id.btnDef10);
		btnDef10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedTip = .10;
				updateTipField();
			}
		});
		
		Button btnDef15 = (Button) findViewById(R.id.btnDef15);
		btnDef15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedTip = .15;
				updateTipField();
			}
		});
		
		Button btnDef20 = (Button) findViewById(R.id.btnDef20);
		btnDef20.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedTip = .20;
				updateTipField();
			}
		});
		
		Button btnOther = (Button) findViewById(R.id.btnOther);
		btnOther.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedTip = getUserInputTip();
				updateTipField();
			}
		});
	}
	
	private double getUserInputTip() {
		EditText etCustomTip = (EditText) findViewById(R.id.etOtherPerc);
		String otherPerc = etCustomTip.getText().toString();
		double tipAmnt = .00;
		if(otherPerc.length() > 0) { // null string check
			tipAmnt = Double.parseDouble(otherPerc) / 100;
		}
		return tipAmnt;
	}
	
	private void updateTipField() {
		double destAmount = 0.00;
		destAmount = grossAmount * selectedTip;
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		TextView tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);
		tvTipAmount.setText(formatter.format(destAmount));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
