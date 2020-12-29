package com.dev.door_dash.repo

import com.dev.door_dash.data.DashStore
import com.dev.door_dash.data.StoreStatus
import org.junit.Before
import org.junit.Test

class TransformerTest {

    companion object {
        private const val NAME = "name"
        private const val IMAGE_URL = "www.google.com"
        private const val SINGLE_DESCRIPTION = "description"
        private const val DESCRIPTION_LONG =
            "Vegan, Chicken, Gluten-Free, Vegetarian, Salads, Healthy, Lunch, Tacos, Mexican"
        private const val DESCRIPTION_SHORT = "Vegan, Chicken"
        private const val CLOSED = "Closed"
        private val distance = listOf(1, 2)
        private const val MIN = " Mins"
    }

    private lateinit var transformer: Transformer

    @Before
    fun setUp() {
        transformer = Transformer()
    }

    @Test
    fun `test transform when description is empty`() {
        val store = DashStore(
            cover_img_url = IMAGE_URL,
            name = NAME,
            description = ""
        )

        val result = transformer.transform(store)

        with(result) {
            assert(this.image_url == IMAGE_URL)
            assert(this.name == NAME)
            assert(this.short_description == "")
        }
    }

    @Test
    fun `test transform when description is one character`() {
        val store = DashStore(
            cover_img_url = IMAGE_URL,
            name = NAME,
            description = SINGLE_DESCRIPTION
        )

        val result = transformer.transform(store)

        with(result) {
            assert(this.image_url == IMAGE_URL)
            assert(this.name == NAME)
            assert(this.short_description == SINGLE_DESCRIPTION)
        }
    }

    @Test
    fun `test transform when description is more than two characters`() {
        val store = DashStore(
            cover_img_url = IMAGE_URL,
            name = NAME,
            description = DESCRIPTION_LONG
        )

        val result = transformer.transform(store)

        with(result) {
            assert(this.image_url == IMAGE_URL)
            assert(this.name == NAME)
            assert(this.short_description == DESCRIPTION_SHORT)
        }
    }

    @Test
    fun `test transform when store is closed`() {
        val storeStatus = StoreStatus(asap_available = false)
        val store = DashStore(
            cover_img_url = IMAGE_URL,
            name = NAME,
            description = DESCRIPTION_LONG,
            status = storeStatus
        )

        val result = transformer.transform(store)

        with(result) {
            assert(this.image_url == IMAGE_URL)
            assert(this.name == NAME)
            assert(this.short_description == DESCRIPTION_SHORT)
            assert(this.location_status == CLOSED)
        }
    }

    @Test
    fun `test transform when store is open`() {
        val storeStatus = StoreStatus(
            asap_available = true,
            asap_minutes_range = distance
        )
        val store = DashStore(
            cover_img_url = IMAGE_URL,
            name = NAME,
            description = DESCRIPTION_LONG,
            status = storeStatus
        )

        val result = transformer.transform(store)

        with(result) {
            assert(this.image_url == IMAGE_URL)
            assert(this.name == NAME)
            assert(this.short_description == DESCRIPTION_SHORT)
            assert(this.location_status == distance[0].toString() + MIN)
        }
    }
}