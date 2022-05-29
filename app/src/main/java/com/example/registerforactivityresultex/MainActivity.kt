package com.example.registerforactivityresultex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var textName : TextView
    private lateinit var textAge : TextView
    private lateinit var nextBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textName = findViewById(R.id.textName)
        textAge = findViewById(R.id.textAge)
        nextBtn = findViewById(R.id.nextBtn)

        setResultNext()

        nextBtn.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            resultLauncher.launch(intent) // startActivityForResult 랑 동일한 기능 이다.

        }
    }

    private fun setResultNext(){
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            // 서브 액티비티로부터 돌아올 때의 결과 값을 받아 올 수 있는 구문
            if (result.resultCode == RESULT_OK){

                val name = result.data?.getStringExtra("name") ?: ""
                val age = result.data?.getStringExtra("age") ?: ""

                textName.text = "이름 : $name"
                textAge.text = "나이 : $age 살"
            }
        }
    }
}