package com.exampl.login_home

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.exampl.login_home.databinding.ActivityKabaddiBinding
import com.exampl.login_home.R
import com.exampl.login_home.ScoreViewModel

class KabaddiActivity : AppCompatActivity() {
    private lateinit var binding:ActivityKabaddiBinding
    val viewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_kabaddi)
        binding  = DataBindingUtil.setContentView(this, R.layout.activity_kabaddi)
        binding.lifecycleOwner = this
        binding.scoreViewModel = viewModel

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.scoreA.observe(this, Observer{ scoreA ->
            binding.skorA.text = scoreA.toString()
        })

        viewModel.scoreB.observe(this, Observer{ scoreB ->
            binding.skorB.text = scoreB.toString()
        })

        binding.tambah1a.setOnClickListener {viewModel.incrementSkorA()}
        binding.tambah2a.setOnClickListener {
            viewModel.incrementSkorA()
            viewModel.incrementSkorA()
        }

        binding.tambah1b.setOnClickListener {viewModel.incrementSkorB()}
        binding.tambah2b.setOnClickListener {
            viewModel.incrementSkorB()
            viewModel.incrementSkorB()
        }

        binding.btnreset.setOnClickListener {viewModel.resetSkor()}

    }
}
