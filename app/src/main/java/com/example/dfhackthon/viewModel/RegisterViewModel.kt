package com.example.dfhackthon.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dfhackthon.model.availableSeat.AvailableSeatResponse
import com.example.dfhackthon.model.bookingResponse.BookingsHistoryResponse
import com.example.dfhackthon.model.confirmation.BookingConfirmationRequest
import com.example.dfhackthon.model.confirmation.BookingConfirmationResponse
import com.example.dfhackthon.model.login.LogInResponse
import com.example.dfhackthon.model.signUp.SignUpResponse
import com.example.dfhackthon.model.slots.SlotsResponse
import com.example.dfhackthon.network.AuthRepository
import com.example.dfhackthon.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(val application: Application, var authRepository: AuthRepository) :
    ViewModel() {

    var logInResponse = MutableLiveData<Resource<LogInResponse>>()
    var signUpResponse = MutableLiveData<Resource<SignUpResponse>>()
    var bookingResponse = MutableLiveData<Resource<BookingsHistoryResponse>>()
    var slotsResponse = MutableLiveData<Resource<SlotsResponse>>()
    var availsResponse = MutableLiveData<Resource<AvailableSeatResponse>>()
    var bookingConfResponse = MutableLiveData<Resource<BookingConfirmationResponse>>()

    fun logInApi(email: String, password: String) {
        logInResponse.value = Resource.loading(data = null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.login(email, password)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        // Check if responseBody is not null and check success field
                        if (response.code() == 200) {
                            logInResponse.value = Resource.success(
                                data = responseBody,
                                message = responseBody!!.message.toString()
                            )
                        } else {
                            logInResponse.value = Resource.error(
                                data = responseBody,
                                message = responseBody!!.message.toString()
                            )
                        }
                    } else {
                        logInResponse.value = Resource.error(
                            data = null,
                            message = response.message()
                        )
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    logInResponse.value = Resource.error(
                        data = null,
                        message = t.message.toString()
                    )
                }
            }
        }
    }

    fun signUpApi(email: String, name: String, mobile: String) {
        signUpResponse.value = Resource.loading(data = null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.signup(email, name, mobile)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        // Check if responseBody is not null and check success field
                        if (response.code() == 200) {
                            signUpResponse.value = Resource.success(
                                data = responseBody,
                                message = responseBody!!.message.toString()
                            )
                        } else {
                            signUpResponse.value = Resource.error(
                                data = responseBody,
                                message = responseBody!!.message.toString()
                            )
                        }
                    } else {
                        signUpResponse.value = Resource.error(
                            data = null,
                            message = response.message()
                        )
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    signUpResponse.value = Resource.error(
                        data = null,
                        message = t.message.toString()
                    )
                }
            }
        }
    }

    fun bookingApi(user_id: String) {
        bookingResponse.value = Resource.loading(data = null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.bookingHistory(user_id)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        // Check if responseBody is not null and check success field
                        if (response.code() == 200) {
                            bookingResponse.value = Resource.success(
                                data = responseBody,
                                message = responseBody.toString()
                            )
                        } else {
                            bookingResponse.value = Resource.error(
                                data = responseBody,
                                message = responseBody.toString()
                            )
                        }
                    } else {
                        bookingResponse.value = Resource.error(
                            data = null,
                            message = response.message()
                        )
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    logInResponse.value = Resource.error(
                        data = null,
                        message = t.message.toString()
                    )
                }
            }
        }
    }

    fun slotsApi(date: String) {
        slotsResponse.value = Resource.loading(data = null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.slotBook(date)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        // Check if responseBody is not null and check success field
                        if (response.code() == 200) {
                            slotsResponse.value = Resource.success(
                                data = responseBody,
                                message = responseBody.toString()
                            )
                        } else {
                            slotsResponse.value = Resource.error(
                                data = responseBody,
                                message = responseBody.toString()
                            )
                        }
                    } else {
                        slotsResponse.value = Resource.error(
                            data = null,
                            message = response.message()
                        )
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    slotsResponse.value = Resource.error(
                        data = null,
                        message = t.message.toString()
                    )
                }
            }
        }
    }

    fun availableApi(date: String, slot_id: String, type: String) {
        availsResponse.value = Resource.loading(data = null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.slotBook(date, slot_id, type)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        // Check if responseBody is not null and check success field
                        if (response.code() == 200) {
                            availsResponse.value = Resource.success(
                                data = responseBody,
                                message = responseBody.toString()
                            )
                        } else {
                            availsResponse.value = Resource.error(
                                data = responseBody,
                                message = responseBody.toString()
                            )
                        }
                    } else {
                        availsResponse.value = Resource.error(
                            data = null,
                            message = response.message()
                        )
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    availsResponse.value = Resource.error(
                        data = null,
                        message = t.message.toString()
                    )
                }
            }
        }
    }

    fun confirmApi(request: BookingConfirmationRequest) {
        bookingConfResponse.value = Resource.loading(data = null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = authRepository.bookConfirm(request)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (response.code() == 200) {
                            bookingConfResponse.value = Resource.success(
                                data = responseBody,
                                message = responseBody!!.message
                            )
                        } else {
                            bookingConfResponse.value = Resource.error(
                                data = responseBody,
                                message = responseBody!!.message.toString()
                            )
                        }
                    } else {
                        bookingConfResponse.value = Resource.error(
                            data = null,
                            message = response.message()
                        )
                    }
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    bookingConfResponse.value = Resource.error(
                        data = null,
                        message = t.message.toString()
                    )
                }
            }
        }
    }
}