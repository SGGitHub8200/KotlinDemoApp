package com.pratham.kotlindemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calculator.*

class Activity_Calculator : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        btn_calPPF.setOnClickListener {
            if (!et_monthlyamnt.text.toString().isEmpty() && !et_rateofinterest.text.toString().isEmpty()
                && !et_tenure.text.toString().isEmpty()) {

                tv_heading.setText("PPF Details")

                calculatePPF(
                    et_monthlyamnt.text.toString().toDouble(),
                    et_rateofinterest.text.toString().toDouble(),
                    et_tenure.text.toString().toInt()
                )
            } else {
                Toast.makeText(this, "Please Fill All Details!", Toast.LENGTH_LONG).show()
            }
        }

        btn_calRD.setOnClickListener {
            if (!et_monthlyamnt.text.toString().isEmpty() && !et_rateofinterest.text.toString().isEmpty()
                && !et_tenure.text.toString().isEmpty()) {

                tv_heading.setText("RD Details")
                val monthlyInterestPercent: Double = et_rateofinterest.text.toString().toDouble() / 12

                calculateRD(
                    et_monthlyamnt.text.toString().toDouble(),
                    monthlyInterestPercent,
                    et_tenure.text.toString().toInt()
                )
            } else {
                Toast.makeText(this, "Please Fill All Details!", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun calculatePPF(monthlyDepositAmnt: Double, rateofinteret: Double, tenure: Int) {
        val tenureMonths: Int = tenure * 12
        var monthlyIntersetAmnt: Double
        var monthlyBalance = 0.0
        var sumOfIntrestYearly = 0.0
        var totalSumOfInterest = 0.0

        for (i in 1..tenure) {
            for (j in 1..12) {
                monthlyIntersetAmnt =
                    (monthlyDepositAmnt + monthlyBalance) * (rateofinteret / 12) / 100
                sumOfIntrestYearly = sumOfIntrestYearly + monthlyIntersetAmnt
                monthlyBalance = monthlyBalance + monthlyDepositAmnt
            }
            monthlyBalance = monthlyBalance + sumOfIntrestYearly
            totalSumOfInterest = totalSumOfInterest + sumOfIntrestYearly
            sumOfIntrestYearly = 0.0
        }
        tv_totalAmntInvest.setText("Total Amount Invested : " + monthlyDepositAmnt * tenureMonths)
        tv_totalIntrestAmnt.setText("Total Interest : " + Math.round(totalSumOfInterest))
        tv_totalMaturityAmnt.setText("Total Maturity Amount : " + Math.round(monthlyBalance + sumOfIntrestYearly))
    }

    fun calculateRD(monthlyDepositAmnt: Double, rateofinteret: Double, tenureYear: Int) {
        val tenureMonths: Int = tenureYear * 12
        var totalMaturityAmount = 0.0
        var monthlyinterestAmnt: Double
        var sumOfInterest = 0.0
        var quaterlyInterest = 0.0
        var totalInterest = 0.0
        var quaterCount = 0

        for (i in 1..tenureMonths) {
            totalMaturityAmount = monthlyDepositAmnt + totalMaturityAmount + quaterlyInterest
            quaterlyInterest = 0.0
            monthlyinterestAmnt = (totalMaturityAmount * rateofinteret) / 100
            sumOfInterest = sumOfInterest + monthlyinterestAmnt
            quaterCount++
            if (quaterCount == 3) {
                totalInterest = totalInterest + sumOfInterest
                quaterlyInterest = sumOfInterest
                sumOfInterest = 0.0
                quaterCount = 0
            }
        }
        tv_totalAmntInvest.setText("Total Invested Amount : " + Math.round(monthlyDepositAmnt * tenureMonths))
        tv_totalIntrestAmnt.setText("Total Interest : " + Math.round(totalInterest))
        tv_totalMaturityAmnt.setText("Total Maturity Amount : " + Math.round(totalMaturityAmount + quaterlyInterest))
    }
}
