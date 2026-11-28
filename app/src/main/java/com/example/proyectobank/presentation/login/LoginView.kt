package com.example.proyectobank.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.proyectobank.R
import com.example.proyectobank.presentation.components.BankButton
import com.example.proyectobank.presentation.components.BankTextField

@Composable
fun ShowLogin(
    viewModel: LoginViewModel = viewModel(),
    navController: NavController
) {
    val documentNumber = viewModel.documentNumber.value
    val password = viewModel.password.value

    val errorMessage = when (viewModel.error.value) {
        LoginError.EMPTY_FIELDS -> stringResource(id = R.string.error_empty_fields)
        LoginError.INVALID_DOCUMENT -> stringResource(id = R.string.error_invalid_document)
        LoginError.INVALID_CREDENTIALS -> stringResource(id = R.string.error_invalid_credentials)
        LoginError.GENERAL -> stringResource(id = R.string.error_general)
        null -> null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.login_title),
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.login_subtitle),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(32.dp))

        BankTextField(
            value = documentNumber,
            onValueChange = { viewModel.changeDocumentNumber(it) },
            label = stringResource(id = R.string.label_document_number),
            keyboardType = KeyboardType.Number,
            isError = errorMessage != null
        )
        Spacer(modifier = Modifier.height(16.dp))
        BankTextField(
            value = password,
            onValueChange = { viewModel.changePassword(it) },
            label = stringResource(id = R.string.label_password),
            keyboardType = KeyboardType.Password,
            isPassword = true,
            isError = errorMessage != null
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = colorResource(id = R.color.errorText),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        BankButton(
            text = stringResource(id = R.string.button_login),
            enabled = !viewModel.isLoading.value,
            onClick = {
                viewModel.login {
                    navController.navigate("home")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.navigate("register") }) {
            Text(text = stringResource(id = R.string.login_no_account))
        }
    }
}
