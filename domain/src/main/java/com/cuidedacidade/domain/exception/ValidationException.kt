package com.cuidedacidade.domain.exception

class ValidationException(val validationMessage: String) : Exception(validationMessage)