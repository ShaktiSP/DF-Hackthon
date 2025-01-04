package com.example.dfhackthon.network

import com.example.dfhackthon.model.availableSeat.AvailableSeatResponse
import com.example.dfhackthon.model.bookingResponse.BookingsHistoryResponse
import com.example.dfhackthon.model.confirmation.BookingConfirmationRequest
import com.example.dfhackthon.model.confirmation.BookingConfirmationResponse
import com.example.dfhackthon.model.login.LogInRequest
import com.example.dfhackthon.model.login.LogInResponse
import com.example.dfhackthon.model.signUp.SignUpRequest
import com.example.dfhackthon.model.signUp.SignUpResponse
import com.example.dfhackthon.model.slots.SlotsResponse
import retrofit2.Response

class AuthRepository {

    suspend fun login(email: String, password: String): Response<LogInResponse> {
        return RetrofitClient.getApi().onLogIn((LogInRequest(email, password)))
    }

    suspend fun signup(email: String, name: String, mobile: String): Response<SignUpResponse> {
        return RetrofitClient.getApi().onLogIn((SignUpRequest(email, name, mobile)))
    }

    suspend fun bookingHistory(user_id: String): Response<BookingsHistoryResponse> {
        return RetrofitClient.getApi().onBookingHistory(user_id)
    }

    suspend fun slotBook(date: String): Response<SlotsResponse> {
        return RetrofitClient.getApi().onSlots(date)
    }

    suspend fun slotBook(
        date: String,
        slot_id: String,
        type: String
    ): Response<AvailableSeatResponse> {
        return RetrofitClient.getApi().onAvail(date, slot_id, type)
    }

    suspend fun bookConfirm(request: BookingConfirmationRequest): Response<BookingConfirmationResponse> {
        return RetrofitClient.getApi().onBooking(request)
    }

//    suspend fun bookConfirm(
//        date: String,
//        slot_id: String,
//        workspace_id: String,
//        type: String
//    ): Response<BookingConfirmationResponse> {
//        return RetrofitClient.getApi().onBooking(date, slot_id, workspace_id, type)
//    }

}