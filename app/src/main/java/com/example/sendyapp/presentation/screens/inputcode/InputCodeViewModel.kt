package com.example.sendyapp.presentation.screens.inputcode

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.sendyapp.domain.network.isInternetAvailable
import com.example.sendyapp.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import land.sendy.pfe_sdk.api.API
import land.sendy.pfe_sdk.model.pfe.response.AuthActivateRs
import land.sendy.pfe_sdk.model.pfe.response.BResponse
import land.sendy.pfe_sdk.model.types.ApiCallback
import javax.inject.Inject

@HiltViewModel
class InputCodeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
): ViewModel() {

    /** Обработка результата запроса на проверку кода,
     * который должен был прийти пользователю на номер телефона **/
    private val checkCodeCallback = object : ApiCallback() {
        override fun <T : BResponse?> onSuccess(data: T) {
            super.onSuccess(data)
            if (data != null) {
                if (this.errNo == 0) {
                    Toast.makeText(context, "Код верный!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Код неверный!", Toast.LENGTH_LONG).show()
                    API.outLog("Сервер вернул ошибку; " + this.toString());
                }
            }
            else {
                API.outLog("onSuccess. Проблема: сервер не вернул данные!");
            }
        }
    }

    /** Метод для проверки кода **/
    fun checkCode(code: String) {
        if (isInternetAvailable(context)) {
            if(code != "" && code.toCharArray().size == 6) {
                API.outLog("Проверка кода: ${code}")
                val runResult = API.api.activateWllet(context, code, "sms", checkCodeCallback)
                if (runResult != null && runResult.hasError()) {
                    API.outLog("Запрос на проверку кода не был запущен:\r\n" + runResult.toString());
                }
            }
            else Toast.makeText(context, "Код не заполнен полностью", Toast.LENGTH_SHORT).show()
        }
        else Toast.makeText(context, "Отсутствует подключение к интернету", Toast.LENGTH_SHORT).show()
    }

    /** Вернуться назда на экран авторизации **/
    fun goBack(controller: NavHostController) {
        controller.navigate(NavigationRoutes.AUTH) {
            popUpTo(NavigationRoutes.INPUT_CODE) {
                inclusive = true
            }
        }
    }

}