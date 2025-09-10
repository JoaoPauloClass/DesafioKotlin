package com.example.desafiokotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class WelcomeActivity : Activity() {

    // Views da tela
    private lateinit var textViewWelcome: TextView
    private lateinit var layoutUserInfo: LinearLayout
    private lateinit var buttonSettings: Button
    private lateinit var buttonLogout: Button

    // Dados do usuário
    private var userName: String? = null
    private var userGender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        initializeViews()
        loadUserData()
        displayUserInfo()
        setupButtons()
    }

    private fun initializeViews() {
        textViewWelcome = findViewById(R.id.textViewWelcome)
        layoutUserInfo = findViewById(R.id.layoutUserInfo)
        buttonSettings = findViewById(R.id.buttonSettings)
        buttonLogout = findViewById(R.id.buttonLogout)
    }

    private fun loadUserData() {
        userName = intent.getStringExtra("USER_NAME")
        userGender = intent.getStringExtra("USER_GENDER")
    }

    private fun displayUserInfo() {
        textViewWelcome.text = "Bem-vindo, $userName! 👋"

        addUserInfoText("👤 Nome: $userName")
        addUserInfoText("⚧ Gênero: ${userGender ?: "Não informado"}")
        addUserInfoText("✅ Login realizado com sucesso!")
    }

    private fun addUserInfoText(text: String) {
        val textView = TextView(this).apply {
            this.text = text
            textSize = 16f
            setTextColor(0xFF333333.toInt())
            setPadding(0, 8, 0, 8)
        }
        layoutUserInfo.addView(textView)
    }

    private fun setupButtons() {
        buttonSettings.setOnClickListener {
            navigateToSettings()
        }

        buttonLogout.setOnClickListener {
            performLogout()
        }
    }

    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java).apply {
            putExtra("USER_NAME", userName)
            putExtra("USER_GENDER", userGender)
        }
        startActivity(intent)
    }

    private fun performLogout() {
        finish() // Fecha a activity atual e volta para o login
    }

    override fun onResume() {
        super.onResume()
    }
}