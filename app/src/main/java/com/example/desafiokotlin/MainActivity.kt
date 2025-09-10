package com.example.desafiokotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : Activity() {

    // Views da tela
    private lateinit var editTextName: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    // Usuário de exemplo (em app real viria de banco de dados)
    private val validUser = User(
        name = "Joao",
        password = "123456",
        gender = "Masculino"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupLoginButton()
    }

    private fun initializeViews() {
        editTextName = findViewById(R.id.editTextName)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
    }

    private fun setupLoginButton() {
        buttonLogin.setOnClickListener {
            performLogin()
        }
    }

    private fun performLogin() {
        val inputName = editTextName.text.toString().trim()
        val inputPassword = editTextPassword.text.toString().trim()

        // Valida campos vazios
        if (!validateInputs(inputName, inputPassword)) {
            return
        }

        // Verifica credenciais
        if (authenticateUser(inputName, inputPassword)) {
            navigateToWelcome()
        } else {
            showLoginError()
        }
    }

    private fun validateInputs(name: String, password: String): Boolean {
        return when {
            name.isEmpty() -> {
                showError("Por favor, digite seu nome")
                false
            }
            password.isEmpty() -> {
                showError("Por favor, digite sua senha")
                false
            }
            else -> true
        }
    }

    private fun authenticateUser(name: String, password: String): Boolean {
        return name.equals(validUser.name, ignoreCase = true) &&
                password == validUser.password
    }

    private fun navigateToWelcome() {
        val intent = Intent(this, WelcomeActivity::class.java).apply {
            putExtra("USER_NAME", validUser.name)
            putExtra("USER_PASSWORD", validUser.password)
            putExtra("USER_GENDER", validUser.gender)
        }

        // Limpa os campos após login bem-sucedido
        clearInputFields()

        startActivity(intent)
        showSuccess("Login realizado com sucesso!")
    }

    private fun clearInputFields() {
        editTextName.setText("")
        editTextPassword.setText("")
    }

    private fun showLoginError() {
        showError("❌ Nome ou senha incorretos!")
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}