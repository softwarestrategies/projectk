package io.softwarestrategies.projectk.resource.service

import io.softwarestrategies.projectk.common.data.ErrorResponse
import io.softwarestrategies.projectk.resource.exception.EntityNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ApiExceptionResponseHandler {

    @Value("\${application.logging.msgPrefix}")
    private val MSG_PREFIX: String? = null

    @ExceptionHandler(value = [Exception::class])
    fun handleException(request: WebRequest, ex: Exception): ResponseEntity<ErrorResponse> {
        val uri = getUri(request)
        LOG.error(MSG_PREFIX + " Exception occurred during call" + (if (uri != null) " to $uri" else ""), ex)
        return ResponseEntity(ErrorResponse(ex.message), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [EntityNotFoundException::class])
    fun handleEntityNotFoundException(request: WebRequest, ex: EntityNotFoundException): ResponseEntity<ErrorResponse> {
        val uri = getUri(request)
        LOG.error(MSG_PREFIX + " EntityNotFoundException occurred during call" + (if (uri != null) " to $uri" else "") + " : " + ex.message)
        return ResponseEntity(ErrorResponse(ex.message), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [AccessDeniedException::class])
    fun handleAccessDeniedException(request: WebRequest, ex: AccessDeniedException): ResponseEntity<ErrorResponse> {
        val uri = getUri(request)
        LOG.error(MSG_PREFIX + " AccessDeniedException occurred during call" + (if (uri != null) " to $uri" else "") + " : " + ex.message)
        return ResponseEntity(ErrorResponse(ex.message), HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun handleIllegalArgumentException(request: WebRequest, ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val uri = getUri(request)
        LOG.error(MSG_PREFIX + " IllegalArgumentException occurred during call" + (if (uri != null) " to $uri" else "") + " : " + ex.message)
        return ResponseEntity(ErrorResponse(ex.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [IllegalStateException::class])
    fun handleIllegalStateException(request: WebRequest, ex: IllegalStateException): ResponseEntity<ErrorResponse> {
        val uri = getUri(request)
        LOG.error(MSG_PREFIX + " IllegalStateException occurred during call" + (if (uri != null) " to $uri" else "") + " : " + ex.message)
        return ResponseEntity(ErrorResponse(ex.message), HttpStatus.BAD_REQUEST)
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(ApiExceptionResponseHandler::class.java)

        private fun getUri(request: WebRequest): String? {
            return try {
                (request as ServletWebRequest).request.requestURI
            } catch (ignore: RuntimeException) {
                // ignore, we do not want to interrupt logging of the exception for this
                null
            }
        }
    }
}
