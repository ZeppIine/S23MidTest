package kr.ac.kumoh.ce.s20180147.s23midtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.result.contract.ActivityResultContracts
import kr.ac.kumoh.ce.s20180147.s23midtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    companion object {
        const val MOLLU_KEY = "KeyName"
        const val MOLLU_IMAGE = 100
        const val MOLLU_BUTTON = 200
        const val MOLLU_NONE = 0
    }
    private lateinit var view: ActivityMainBinding
    private val startForResult = registerForActivityResult(ActivityResultContracts
        .StartActivityForResult()){
        if(it.resultCode != RESULT_OK)
            return@registerForActivityResult
        val pressed = it.data?.getBooleanExtra(SecondActivity.BUTTON_PRESSED, false) ?: false
        if (pressed)
            view.btnFourth.text = view.btnFourth.text.toString().plus(" (눌림)")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
        view.btnThird.setOnClickListener(this)
        view.btnFourth.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        val second = Intent(this, SecondActivity::class.java)
        when (v?.id) {
            view.btnThird.id -> second.putExtra(MOLLU_KEY, MOLLU_IMAGE)
            view.btnFourth.id -> second.putExtra(MOLLU_KEY, MOLLU_BUTTON)
        }
        startForResult.launch(second)
    }
}