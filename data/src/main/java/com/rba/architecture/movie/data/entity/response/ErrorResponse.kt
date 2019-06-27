package com.rba.architecture.movie.data.entity.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_code")
    var statusCode: Int = 0,
    @SerializedName("status_message")
    var statusMessage: String? = null,
    var success: Boolean? = false
)