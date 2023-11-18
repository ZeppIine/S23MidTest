package kr.ac.kumoh.ce.s20180147.s23midtest

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import kr.ac.kumoh.ce.s20180147.s23midtest.ui.theme.S23MidTestTheme

open class SecondActivity : ComponentActivity() {
    companion object{
        const val BUTTON_PRESSED = "ButtonPressed"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S23MidTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (intent.getIntExtra(MainActivity.MOLLU_KEY, MainActivity.MOLLU_NONE)){
                        MainActivity.MOLLU_IMAGE -> MolluImage()
                        MainActivity.MOLLU_BUTTON -> MolluButton()
                        else -> finish()
                    }
                }
            }
        }
    }
}

@Composable
fun MolluImage() {
    Image(painter = painterResource(id = R.drawable.mollu),
        contentDescription = "Mollu Image")
}

@Composable
fun MolluButton() {
    val activity = LocalContext.current as Activity
    Button(onClick = {
        val result = Intent()
        result.putExtra(SecondActivity.BUTTON_PRESSED, true)
        activity.setResult(RESULT_OK, result)
        activity.finish()
    }) {
        Text("Mol?lu")
    }
}