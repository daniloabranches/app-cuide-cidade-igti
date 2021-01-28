package com.cuidedacidade.domain.usecase

import com.cuidedacidade.domain.entity.Request
import com.cuidedacidade.domain.exception.ValidationException
import com.cuidedacidade.domain.repository.RequestRepository
import io.reactivex.rxjava3.core.Completable
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import org.mockito.Mockito.`when` as whenT

private const val EMPTY_STRING = ""
private const val USER_ID_FAKE = "0"

@ExtendWith(MockitoExtension::class)
class SaveRequestUseCaseTest {
    @Mock
    private lateinit var requestRepository: RequestRepository

    private fun configureRequestRepository(userId: String, request: Request) {
        whenT(requestRepository.saveRequest(userId, request, null))
            .thenReturn(Completable.create { })
    }

    @Test
    fun should_SaveSuccessfully() {
        val request = Request(
            EMPTY_STRING, "any category", "any description", "any image",
            Date(), null, Request.Status.PENDING
        )
        val saveRequestUseCase = SaveRequestUseCase(requestRepository)
        configureRequestRepository(USER_ID_FAKE, request)

        val resultOperation = saveRequestUseCase.invoke(USER_ID_FAKE, request, null)

        resultOperation.test().assertNoErrors()
    }

    @Test
    fun should_ThrowException_When_UserIsBlank() {
        val request = Request(
            EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, EMPTY_STRING, Date(),
            null, Request.Status.PENDING
        )
        val saveRequestUseCase = SaveRequestUseCase(requestRepository)
        configureRequestRepository(EMPTY_STRING, request)

        val resultOperation = saveRequestUseCase.invoke(userId = EMPTY_STRING, request, null)

        resultOperation.test().assertError {
            it is ValidationException
                    && it.message?.equals("Informe o usuário") ?: false
        }
    }

    @Test
    fun should_ThrowException_When_CategoryIsBlank() {
        val request = Request(
            EMPTY_STRING, categoryName = EMPTY_STRING, EMPTY_STRING, EMPTY_STRING,
            Date(), null, Request.Status.PENDING
        )
        val saveRequestUseCase = SaveRequestUseCase(requestRepository)
        configureRequestRepository(USER_ID_FAKE, request)

        val resultOperation = saveRequestUseCase.invoke(USER_ID_FAKE, request, null)

        resultOperation.test().assertError {
            it is ValidationException
                    && it.message?.equals("Informe a categoria para a solicitação") ?: false
        }
    }

    @Test
    fun should_ThrowException_When_DescriptionIsBlank() {
        val request = Request(
            EMPTY_STRING, "any category", description = EMPTY_STRING, EMPTY_STRING,
            Date(), null, Request.Status.PENDING
        )
        val saveRequestUseCase = SaveRequestUseCase(requestRepository)
        configureRequestRepository(USER_ID_FAKE, request)

        val resultOperation = saveRequestUseCase.invoke(USER_ID_FAKE, request, null)

        resultOperation.test().assertError {
            it is ValidationException
                    && it.message?.equals("Informe a descrição para a solicitação") ?: false
        }
    }

    @Test
    fun should_ThrowException_When_ImageIsBlank() {
        val request = Request(
            EMPTY_STRING, "any category", "any description", EMPTY_STRING,
            Date(), null, Request.Status.PENDING
        )
        val saveRequestUseCase = SaveRequestUseCase(requestRepository)
        configureRequestRepository(USER_ID_FAKE, request)

        val resultOperation = saveRequestUseCase.invoke(USER_ID_FAKE, request, null)

        resultOperation.test().assertError {
            it is ValidationException
                    && it.message?.equals("Informe a imagem da categoria para a solicitação") ?: false
        }
    }
}