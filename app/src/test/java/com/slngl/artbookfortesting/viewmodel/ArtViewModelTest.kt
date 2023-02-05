package com.slngl.artbookfortesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.slngl.artbookfortesting.MainCoroutineRule
import com.slngl.artbookfortesting.getOrAwaitValueTest
import com.slngl.artbookfortesting.repository.FakeArtRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import com.slngl.artbookfortesting.util.Status
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {

    @get:Rule
    var instantTestExecutorRule = InstantTaskExecutorRule()
/*
    @get:Rule
    val mainDispatcherRule = MainCoroutineRule()
*/
    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup() {
        //Test Doubles
        viewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun `ìnsert art without year returns error`() {
        viewModel.makeArt("Mona Lisa", "Da vinci", "")
        //livedata async old. için testler sırasında mainthreadde olmuyor diye alttaki methodu kullanıyoruz
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `ìnsert art without name returns error`() {
        viewModel.makeArt("", "Da Vinci", "1500")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `ìnsert art without artistName returns error`() {
        viewModel.makeArt("Mona Lisa", "", "1500")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}