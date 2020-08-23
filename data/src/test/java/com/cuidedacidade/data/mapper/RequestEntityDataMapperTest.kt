package com.cuidedacidade.data.mapper

import com.cuidedacidade.data.entity.RequestEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class RequestEntityDataMapperTest {

    private val requestEntityDataMapper = RequestEntityDataMapper()

    @Test
    fun should_TransformRequests() {
        val dataRequests = listOf(
            newRequestEntity(),
            newRequestEntity()
        )

        val expectedRequests = requestEntityDataMapper.transform(dataRequests)

        assertEquals(2, expectedRequests.size)
    }

    @Test(expected = NoSuchElementException::class)
    fun should_TransformRequests_WithStatusAttributeInconsistent() {
        val dataRequests = listOf(
            newRequestEntity().also { it.status = 2 }
        )

        requestEntityDataMapper.transform(dataRequests)
    }

    private fun newRequestEntity() =
        RequestEntity().also {
            it.id = ""
            it.category_name = ""
            it.date = Date()
            it.description = ""
            it.image = ""
            it.status = 1
        }
}