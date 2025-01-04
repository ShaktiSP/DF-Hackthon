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
import com.example.dfhackthon.network.EndPoints.Companion.AVAILABLE_API
import com.example.dfhackthon.network.EndPoints.Companion.BOOKING_HISTORY_API
import com.example.dfhackthon.network.EndPoints.Companion.CONFIRM_API
import com.example.dfhackthon.network.EndPoints.Companion.LOGIN_API
import com.example.dfhackthon.network.EndPoints.Companion.SIGNUP_API
import com.example.dfhackthon.network.EndPoints.Companion.SLOTS_API
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiInterface {

    @POST(LOGIN_API)
    suspend fun onLogIn(@Body request: LogInRequest): Response<LogInResponse>

    @POST(SIGNUP_API)
    suspend fun onLogIn(@Body request: SignUpRequest): Response<SignUpResponse>

    @GET(BOOKING_HISTORY_API)
    suspend fun onBookingHistory(@Query("user_id") user_id: String): Response<BookingsHistoryResponse>

    @GET(SLOTS_API)
    suspend fun onSlots(@Query("date") date: String): Response<SlotsResponse>

    @GET(AVAILABLE_API)
    suspend fun onAvail(
        @Query("date") date: String,
        @Query("slot_id") slot_id: String,
        @Query("type") type: String
    ): Response<AvailableSeatResponse>

    @POST(CONFIRM_API)
    suspend fun onBooking(@Body request: BookingConfirmationRequest): Response<BookingConfirmationResponse>

//    @FormUrlEncoded
//    @POST(AVAILABLE_API)
//    fun onBooking(
//        @Field("date") date : String,
//        @Field("slot_id") slot_id : String,
//        @Field("workspace_id") workspace_id: String,
//        @Field("type") type : String
//    ): Response<BookingConfirmationResponse>

}