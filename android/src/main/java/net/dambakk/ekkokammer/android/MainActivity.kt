package net.dambakk.ekkokammer.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.dambakk.ekkokammer.common.Greeting
import android.widget.TextView
import androidx.compose.foundation.Text
import androidx.compose.ui.platform.setContent

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text(
                text = greet()
            )
        }
    }
}
