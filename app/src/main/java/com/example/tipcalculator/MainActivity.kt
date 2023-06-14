package com.example.tipcalculator

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.google.android.material.tabs.TabLayout.TabGravity

private const val TAG= "MainActivity"
private const val INITIAL_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {

    private lateinit var etBaseAmount :EditText
    private lateinit var seekBar: SeekBar
    private lateinit var tvTipPercent: TextView
    private lateinit var tvTipShow: TextView
    private lateinit var tvTotalShow: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etBaseAmount=findViewById(R.id.editText)
        seekBar=findViewById(R.id.seekBar)
        tvTipShow=findViewById(R.id.tvTipShow)
        tvTotalShow=findViewById(R.id.tvTotalShow)
        tvTipPercent=findViewById(R.id.tvTipPercent)

        seekBar.progress= INITIAL_TIP_PERCENT
        tvTipPercent.text= "$INITIAL_TIP_PERCENT%"

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
              Log.i(TAG, "onProgressChanged $progress")
                tvTipPercent.text= "$progress%"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        etBaseAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG,"afterTextChanged $s")
                computeTipAndTotal()
            }

        })
    }

    private fun computeTipAndTotal() {

        if (etBaseAmount.text.isEmpty()){
            tvTipShow.text=""
            tvTotalShow.text=""
            return
        }
       //get value of base and tip
      val baseAmount = etBaseAmount.text.toString().toDouble()
      val tipPercent = seekBar.progress

        //compute tip and total amount
        val tipAmount = baseAmount * tipPercent/100
        val totalAmount = baseAmount+tipAmount

        //update UI
        tvTipShow.text= "%.2f".format(tipAmount)
        tvTotalShow.text= "%.2f".format(totalAmount)
    }
}