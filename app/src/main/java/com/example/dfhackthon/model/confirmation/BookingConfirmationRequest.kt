package com.example.dfhackthon.model.confirmation

data class BookingConfirmationRequest(
    val date: String,
    val slot_id: String,
    val workspace_id: String,
    val type: String
)
