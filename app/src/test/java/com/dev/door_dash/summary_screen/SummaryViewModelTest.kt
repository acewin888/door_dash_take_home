package com.dev.door_dash.summary_screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dev.door_dash.Event.Event
import com.dev.door_dash.data.DashDetail
import com.dev.door_dash.data.DashStoreItem
import com.dev.door_dash.data.ErrorType
import com.dev.door_dash.repo.DashRepo
import com.dev.door_dash.rxSchedulers.RxScheduler
import com.dev.door_dash.rxSchedulers.TestScheduler
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.Exception

class SummaryViewModelTest {

    companion object {
        private const val IMAGE_URL = "www.google.com"
        private const val NAME = "restaurant"
        private const val DESCRIPTION = "short description"
        private const val LOCATION_STATUS = "location status"
        private const val EXCEPTION_STRING = "This is a mock exception"
        private const val ID = 1234
        private const val PHONE_NUMBER = 12345
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val subscription: CompositeDisposable = mockk(relaxed = true)
    private val repo: DashRepo = mockk()
    private val rxScheduler: RxScheduler = TestScheduler()

    private val storesObserver: Observer<List<DashStoreItem>> = mockk(relaxed = true)
    private val detailObserver: Observer<Event<DashDetail>> = mockk(relaxed = true)
    private val progressObserver: Observer<Boolean> = mockk(relaxed = true)
    private val errorObserver: Observer<ErrorType> = mockk(relaxed = true)

    private lateinit var store: DashStoreItem
    private lateinit var listOfStores: List<DashStoreItem>
    private lateinit var exception: Exception
    private lateinit var dashDetail: DashDetail

    private lateinit var viewModel: SummaryViewModel

    @Before
    fun setUp() {
        mockData()
        viewModel = SummaryViewModel(repo, rxScheduler, subscription)
        viewModel.storesLiveData.observeForever(storesObserver)
        viewModel.detailLiveData.observeForever(detailObserver)
        viewModel.progressLiveData.observeForever(progressObserver)
        viewModel.errorLiveData.observeForever(errorObserver)

    }

    private fun mockData() {
        store = DashStoreItem(
            image_url = IMAGE_URL,
            name = NAME,
            short_description = DESCRIPTION,
            location_status = LOCATION_STATUS,
            id = ID
        )
        listOfStores = listOf(store)
        exception = Exception(EXCEPTION_STRING)
        dashDetail = DashDetail(phone_number = PHONE_NUMBER.toString())
    }

    @Test
    fun `test getNearByRestaurants when api call success`() {
        every { repo.getRestaurants() } returns Single.just(listOfStores)

        viewModel.getNearbyRestaurants()

        verify(exactly = 1) { progressObserver.onChanged(false) }
        verify(exactly = 1) { storesObserver.onChanged(listOfStores) }
        verify(exactly = 0) { errorObserver.onChanged(any()) }
    }

    @Test
    fun `test getNearByRestaurants when api call failed`() {
        every { repo.getRestaurants() } returns Single.error(exception)

        viewModel.getNearbyRestaurants()

        verify(exactly = 1) { progressObserver.onChanged(false) }
        verify(exactly = 0) { storesObserver.onChanged(listOfStores) }
        verify(exactly = 1) { errorObserver.onChanged(ErrorType.Network(exception.toString())) }
    }

    @Test
    fun `test getDetailRestaurant detail when api call success`() {
        every { repo.getRestaurant(ID) } returns Single.just(dashDetail)

        viewModel.getDetailRestaurant(ID)

        verify(exactly = 1) { detailObserver.onChanged(any()) }
        verify(exactly = 0) { errorObserver.onChanged(any()) }
    }

    @Test
    fun `test getDetailRestaurant detail when api call failed`() {
        every { repo.getRestaurant(ID) } returns Single.error(exception)

        viewModel.getDetailRestaurant(ID)

        verify(exactly = 0) { detailObserver.onChanged(any()) }
        verify(exactly = 1) { errorObserver.onChanged(ErrorType.Network(exception.toString())) }
    }
}