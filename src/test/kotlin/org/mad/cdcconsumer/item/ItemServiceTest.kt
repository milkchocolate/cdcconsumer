package org.mad.cdcconsumer.item

import org.assertj.core.api.BDDAssertions
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties

class ItemServiceTest {
    @get:Rule
    var stubRunnerRule: StubRunnerRule = StubRunnerRule()
            .downloadStub("org.mad", "cdcprovider", "0.0.1-SNAPSHOT", "stubs")
            .withPort(8100)
            .stubsMode(StubRunnerProperties.StubsMode.LOCAL)

    @Test
    fun `get item by id when item exists`() {
        // given:
        val restTemplate = RestTemplateBuilder().rootUri("http://localhost:8100").build()
        val itemService = ItemService(restTemplate)

        // when:
        val item = itemService.getItemById(1)

        // then:
        BDDAssertions.then(item.id).isEqualTo(1)
        BDDAssertions.then(item.type).isEqualTo("fruit")
    }

    @Test
    fun `get item by id when item doesn't exist`() {
        // given:
        val restTemplate = RestTemplateBuilder().rootUri("http://localhost:8100").build()
        val itemService = ItemService(restTemplate)

        try {
            // when:
            itemService.getItemById(404)
            fail("Expected an ItemNotFoundException to be thrown")
        } catch (e: ItemNotFoundException) {
            // then:
            assertEquals(ItemNotFoundException::class.java, e.javaClass)
            assertEquals("Item: 404 doesn't exist", e.message)
        }
    }
}
