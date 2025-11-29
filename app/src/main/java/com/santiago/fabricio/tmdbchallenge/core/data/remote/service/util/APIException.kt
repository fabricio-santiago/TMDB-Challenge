package com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util

import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util.NetworkConstants.HTTP_STATUS_401
import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util.NetworkConstants.HTTP_STATUS_500
import retrofit2.Response

class APIException internal constructor(
    message: String? = null,
    val httpStatusCode: Int?,
    val actionErrorType: ActionErrorTypeEnum,
    val response: Response<*>? = null,
    val networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
) : RuntimeException(message) {

    companion object {
        fun httpError(
            response: Response<*>?,
            httpStatusCode: Int?,
            actionErrorType: ActionErrorTypeEnum,
            networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
        ): APIException {
            networkErrorMessage.actionErrorType = actionErrorType
            val message = response?.code().toString() + " " + response?.message()
            return APIException(
                message = message,
                response = response,
                httpStatusCode = httpStatusCode,
                actionErrorType = actionErrorType,
                networkErrorMessage = networkErrorMessage
            )
        }

        fun unexpectedError(
            actionErrorType: ActionErrorTypeEnum,
            networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
        ): APIException {
            networkErrorMessage.actionErrorType = actionErrorType

            return APIException(
                httpStatusCode = HTTP_STATUS_500,
                actionErrorType = actionErrorType,
                networkErrorMessage = networkErrorMessage
            )
        }

        fun networkError(
            message: String,
            networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
        ): APIException {
            networkErrorMessage.actionErrorType = ActionErrorTypeEnum.NETWORK_ERROR

            return APIException(
                message = message,
                HTTP_STATUS_500,
                actionErrorType = ActionErrorTypeEnum.NETWORK_ERROR,
                networkErrorMessage = networkErrorMessage
            )
        }

        fun unauthorizedError(
            message: String,
            networkErrorMessage: NetworkErrorMessage = NetworkErrorMessage()
        ): APIException {
            networkErrorMessage.actionErrorType = ActionErrorTypeEnum.NETWORK_ERROR

            return APIException(
                message = message,
                HTTP_STATUS_401,
                actionErrorType = ActionErrorTypeEnum.UNAUTHORIZED_ERROR,
                networkErrorMessage = networkErrorMessage
            )
        }
    }
}