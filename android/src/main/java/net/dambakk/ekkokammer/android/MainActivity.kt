package net.dambakk.ekkokammer.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.dambakk.ekkokammer.common.Greeting
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.ui.platform.setContent
import net.dambakk.ekkokammer.android.theme.EkkoTheme

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EkkoTheme {
                Scaffold {
                    Text(
                        text = greet()
                    )

                }
            }
        }
    }
}
