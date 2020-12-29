package com.dev.door_dash.repo

import com.dev.door_dash.data.*
import com.dev.door_dash.network.DashStoreAPI
import com.dev.door_dash.network.NetworkingManager
import com.dev.door_dash.rxSchedulers.RxScheduler
import com.dev.door_dash.rxSchedulers.TestScheduler
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class DashRepoImplTest {

    companion object {
        private const val NAME = "name"
        private const val IMAGE_URL = "www.google.com"
        private const val DESCRIPTION = "description"
        private const val CLOSED = "Closed"
        private const val ID = 1234
        private const val NUMBER_OF_RESPOSNE = 1
        private const val PHONE_NUMBER = 12345
    }

    private val networkingManager: NetworkingManager = mockk()
    private val dashStoreAPI: DashStoreAPI = mockk()

    private lateinit var dashStoreResponse: DashResponse
    private lateinit var dashStore: DashStore
    private lateinit var storeStatus: StoreStatus
    private lateinit var listOfStore: List<DashStore>
    private lateinit var dashDetail: DashDetail

    private val rxScheduler: RxScheduler = TestScheduler()

    private lateinit var dashRepo: DashRepo

    @Before
    fun setUp() {
        mockData()
        every { networkingManager.getDashStoreAPI() } returns dashStoreAPI
        every { dashStoreAPI.getStoreSummary() } returns Observable.just(dashStoreResponse)
        every { dashStoreAPI.getDetail(ID) } returns Single.just(dashDetail)
        dashRepo = DashRepoImpl(networkManager = networkingManager)

    }

    private fun mockData() {
        storeStatus = StoreStatus(
            asap_available = false
        )
        dashStore = DashStore(
            name = NAME,
            cover_img_url = IMAGE_URL,
            description = DESCRIPTION,
            status = storeStatus,
            id = ID
        )
        listOfStore = listOf(dashStore)
        dashStoreResponse = DashResponse(
            num_results = NUMBER_OF_RESPOSNE,
            stores = listOfStore
        )
        dashDetail = DashDetail(phone_number = PHONE_NUMBER.toString())
    }

    @Test
    fun `test getRestaurants`() {
        val result = dashRepo.getRestaurants()

        result
            .subscribeOn(rxScheduler.getIO())
            .observeOn(rxScheduler.getMain())
            .subscribe({
                assert(
                    it[0] == DashStoreItem(
                        name = NAME,
                        short_description = DESCRIPTION,
                        image_url = IMAGE_URL,
                        location_status = CLOSED,
                        id = ID
                    )
                )
            }, {

            })
    }

    @Test
    fun `test get detail restaurant`() {
        dashRepo.getRestaurant(ID)

        verify(exactly = 1) { dashStoreAPI.getDetail(ID) }
    }
}