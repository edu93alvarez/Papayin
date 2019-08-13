package pe.demo.papayin.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


open abstract class PDBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setupView(savedInstanceState)
    }

    protected abstract fun getLayout(): Int
    protected abstract fun setupView(savedInstanceState: Bundle?)

}
