package com.br.taximeapi.infra.entrypoint.http.config.exception.handler

import com.br.taximeapi.domain.shared.exceptions.ResourceConflictException
import com.br.taximeapi.domain.shared.exceptions.ResourceNotFoundException
import com.br.taximeapi.infra.entrypoint.http.config.exception.entity.BaseResponseErrorMessage
import com.br.taximeapi.infra.entrypoint.http.config.exception.entity.DetailResponseErrorMessage
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class CustomExceptionHandler {
    companion object {
        private val INVALID_REQUEST_SYNTAX_MESSAGE = "Request is not well-formed, syntactically incorrect, or violates schema"
    }

    private val log = LoggerFactory.getLogger(CustomExceptionHandler::class.java)

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleResourceNotFoundException(ex: ResourceNotFoundException): ResponseEntity<BaseResponseErrorMessage> =
        ResponseEntity(
            BaseResponseErrorMessage(
                slug = "RESOURCE_NOT_FOUND",
                message = ex.message,
            ),
            HttpStatus.NOT_FOUND,
        )

    @ExceptionHandler(ResourceConflictException::class)
    fun handleConflictException(ex: ResourceConflictException): ResponseEntity<BaseResponseErrorMessage> =
        ResponseEntity(
            BaseResponseErrorMessage(
                slug = "CONFLICT",
                message = ex.message,
            ),
            HttpStatus.CONFLICT,
        )

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleInvalidRequestBody(ex: HttpMessageNotReadableException): ResponseEntity<BaseResponseErrorMessage> {
        val cause = ex.cause

        if (cause is MismatchedInputException) {
            val errorDetails =
                cause.path.map { pathElement ->
                    DetailResponseErrorMessage(
                        field = pathElement.fieldName,
                    )
                }

            return ResponseEntity(
                BaseResponseErrorMessage(
                    slug = HttpStatus.UNPROCESSABLE_ENTITY.name,
                    message = INVALID_REQUEST_SYNTAX_MESSAGE,
                    details = errorDetails,
                ),
                HttpStatus.UNPROCESSABLE_ENTITY,
            )
        }

        return ResponseEntity(
            BaseResponseErrorMessage(
                slug = HttpStatus.BAD_REQUEST.name,
                message = INVALID_REQUEST_SYNTAX_MESSAGE,
            ),
            HttpStatus.BAD_REQUEST,
        )
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatchException(ex: MethodArgumentTypeMismatchException): ResponseEntity<BaseResponseErrorMessage> {
        val details =
            DetailResponseErrorMessage(
                field = ex.name,
                message = ex.message,
            )

        return ResponseEntity(
            BaseResponseErrorMessage(
                slug = HttpStatus.BAD_REQUEST.name,
                message = INVALID_REQUEST_SYNTAX_MESSAGE,
                details = listOf(details),
            ),
            HttpStatus.BAD_REQUEST,
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleInvalidArgumentException(ex: MethodArgumentNotValidException): ResponseEntity<BaseResponseErrorMessage> {
        val errorDetails =
            ex.fieldErrors.map { pathElement ->
                DetailResponseErrorMessage(
                    field = pathElement.field,
                    message = pathElement.defaultMessage,
                )
            }

        return ResponseEntity(
            BaseResponseErrorMessage(
                slug = HttpStatus.BAD_REQUEST.name,
                message = INVALID_REQUEST_SYNTAX_MESSAGE,
                details = errorDetails,
            ),
            HttpStatus.BAD_REQUEST,
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleAllException(ex: Exception): ResponseEntity<BaseResponseErrorMessage> {
        log.error("INTERNAL SERVER ERROR IS OCCURRED. EXCEPTION: ${ex.javaClass} -  MESSAGE: ${ex.message} - CAUSE: ${ex.cause}")

        return ResponseEntity(
            BaseResponseErrorMessage(
                slug = HttpStatus.INTERNAL_SERVER_ERROR.name,
                message = "A system or application error occurred",
            ),
            HttpStatus.INTERNAL_SERVER_ERROR,
        )
    }
}
