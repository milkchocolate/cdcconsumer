package org.mad.cdcconsumer.item

import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate

@Service
class ItemService(private val restTemplate: RestTemplate) {
    fun getItemById(id: Int): Item {
        try {
            val item = restTemplate.getForObject("/items/{id}", Item::class.java, id)
            return item ?: throw ItemNotFoundException(buildErrorMessage(id))
        } catch (e: HttpClientErrorException) {
            throw ItemNotFoundException(buildErrorMessage(id), e)
        }
    }

    private fun buildErrorMessage(itemId: Int): String {
        return "Item: $itemId doesn't exist"
    }
}