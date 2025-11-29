package com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util

import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util.NetworkConstants.ERROR
import com.santiago.fabricio.tmdbchallenge.core.data.remote.service.util.NetworkConstants.HTTP_STATUS_500

const val DEFAULT_ERROR_MESSAGE = "Infelizmente ocorreu algum erro. Tente novamente"

data class NetworkErrorMessage(
    var title: String = ERROR,
    var message: String = DEFAULT_ERROR_MESSAGE,
    var httpCode: Int = HTTP_STATUS_500,
    var actionErrorType: ActionErrorTypeEnum = ActionErrorTypeEnum.HTTP_ERROR
)