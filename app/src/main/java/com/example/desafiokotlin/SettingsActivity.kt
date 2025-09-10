// SettingsActivity.kt (Tela de Configurações)
package com.example.desafiokotlin

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class SettingsActivity : Activity() {

    // Views da tela
    private lateinit var textViewUser: TextView
    private lateinit var textViewGender: TextView
    private lateinit var checkBoxNotifications: CheckBox
    private lateinit var checkBoxDarkMode: CheckBox
    private lateinit var radioGroupLanguage: RadioGroup
    private lateinit var radioPortuguese: RadioButton
    private lateinit var radioEnglish: RadioButton
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var radioMasculine: RadioButton
    private lateinit var radioFeminine: RadioButton
    private lateinit var buttonSave: Button
    private lateinit var buttonBack: Button

    // Dados do usuário
    private var userName: String? = null
    private var userGender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initializeViews()
        loadUserData()
        setupButtons()
    }

    private fun initializeViews() {
        textViewUser = findViewById(R.id.textViewUser)
        textViewGender = findViewById(R.id.textViewGender)
        checkBoxNotifications = findViewById(R.id.checkBoxNotifications)
        checkBoxDarkMode = findViewById(R.id.checkBoxDarkMode)
        radioGroupLanguage = findViewById(R.id.radioGroupLanguage)
        radioPortuguese = findViewById(R.id.radioPortuguese)
        radioEnglish = findViewById(R.id.radioEnglish)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        radioMasculine = findViewById(R.id.radioMasculine)
        radioFeminine = findViewById(R.id.radioFeminine)
        buttonSave = findViewById(R.id.buttonSave)
        buttonBack = findViewById(R.id.buttonBack)
    }

    private fun loadUserData() {
        userName = intent.getStringExtra("USER_NAME")
        userGender = intent.getStringExtra("USER_GENDER")

        // Exibe informações na tela
        textViewUser.text = "Configurações de $userName"
        textViewGender.text = "Gênero atual: ${userGender ?: "Não informado"}"

        // Define o gênero no RadioGroup baseado no valor recebido
        when (userGender) {
            "Masculino" -> radioMasculine.isChecked = true
            "Feminino" -> radioFeminine.isChecked = true
        }
    }

    private fun setupButtons() {
        buttonSave.setOnClickListener {
            saveSettings()
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun saveSettings() {
        // Coleta todas as configurações
        val notifications = if (checkBoxNotifications.isChecked) "Ativadas" else "Desativadas"
        val darkMode = if (checkBoxDarkMode.isChecked) "Ativado" else "Desativado"

        val selectedLanguage = when (radioGroupLanguage.checkedRadioButtonId) {
            R.id.radioPortuguese -> "Português"
            R.id.radioEnglish -> "Inglês"
            else -> "Não selecionado"
        }

        val selectedGender = when (radioGroupGender.checkedRadioButtonId) {
            R.id.radioMasculine -> "Masculino"
            R.id.radioFeminine -> "Feminino"
            else -> "Não selecionado"
        }

        textViewGender.text = "Gênero atual: $selectedGender"

        val message = buildString {
            appendLine("✅ Configurações salvas!")
            appendLine("📱 Notificações: $notifications")
            appendLine("🌙 Modo Escuro: $darkMode")
            appendLine("🌍 Idioma: $selectedLanguage")
            appendLine("👤 Gênero: $selectedGender")
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}