package com.example.sendyapp.presentation.screens.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.sendyapp.data.states.AuthSt
import com.example.sendyapp.domain.network.isInternetAvailable
import com.example.sendyapp.presentation.navigation.NavigationRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import land.sendy.pfe_sdk.api.API
import land.sendy.pfe_sdk.model.pfe.response.TermsOfUseRs
import land.sendy.pfe_sdk.model.types.ApiCallback
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _state = MutableStateFlow(AuthSt())
    val state: StateFlow<AuthSt> = _state.asStateFlow()

    var stateValue: AuthSt
        get() = _state.value
        set(value) {
            _state.value = value
        }

    /** Обработка результата запроса на отправку кода по номеру телефона **/
    private val loginAtAuthCallback = object : ApiCallback() {
        override fun onCompleted(res: Boolean) {
            if (res) {
                println("Запрос выполнен успешно!")
            }
            else println("Запрос завершился с ошибкой.")
        }
    }

    /** Обработка результата запроса на получение текста пользовательского соглашения **/
    private val getTermsCallback = object : ApiCallback() {
        override fun onCompleted(res: Boolean) {
            if (!res || errNo != 0) {
                API.outLog("Ошибка получения текста пользовательского соглашения" + this.toString());
            }
            else {
                API.outLog((oResponse as TermsOfUseRs).TextTermsOfUse.trimIndent())
                stateValue = stateValue.copy(textOffer = (oResponse as TermsOfUseRs).TextTermsOfUse.trimIndent())
            }
        }
    }

    /** Получение текста пользовательского соглашения **/
    fun getOfferText() {
        if(isInternetAvailable(context)) {
            val runResult = API.api.getTermsOfUse(context, getTermsCallback)
            if (runResult != null && runResult.hasError()) {
                API.outLog("Запрос на получение текста пользовательского соглашения не был запущен:\r\n" + runResult.toString());
            }
        }
        else Toast.makeText(context, "Условия соглашения не получены, нет сети", Toast.LENGTH_LONG).show()
    }

    /** Отправка кода по номеру телефона **/
    fun sendCode(controller: NavHostController) {
        try {
            if(stateValue.phoneNumber != "" &&  stateValue.phoneNumber.length == 11) {
                if(stateValue.offerIsAgree) {
                    if(isInternetAvailable(context)) {
                        API.outLog("Отправка кода по номеру телефона ${stateValue.phoneNumber}")
                        val runResult = API.api.loginAtAuth(context, stateValue.phoneNumber, loginAtAuthCallback)
                        if (runResult != null && runResult.hasError()) {
                            API.outLog("Запрос на отправку кода по номеру телефона ${stateValue.phoneNumber} не был запущен:\r\n" + runResult.toString());
                        }
                        else {
                            controller.navigate(NavigationRoutes.INPUT_CODE) {
                                popUpTo(NavigationRoutes.AUTH) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                    else Toast.makeText(context, "Отсутствует подключение к интернету", Toast.LENGTH_SHORT).show()
                }
                else Toast.makeText(context, "Вы не согласились с условиями пользовательского соглашения!", Toast.LENGTH_SHORT).show()
            }
            else Toast.makeText(context, "Номер телефона некорректно заполнен", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "${e.message}", Toast.LENGTH_SHORT).show()
            Log.e("error ${e.message}", e.toString())
        }
    }

}

