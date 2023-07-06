package com.knasirayaz.nanohealthsuitedemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.knasirayaz.nanohealthsuitedemo.data.repository.LoginRepositoryImpl
import com.knasirayaz.nanohealthsuitedemo.data.source.remote.Webservice
import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.model.LoginResponse
import com.knasirayaz.nanohealthsuitedemo.domain.repository.LoginRepository
import com.knasirayaz.nanohealthsuitedemo.domain.usecase.Login
import com.knasirayaz.nanohealthsuitedemo.presentation.login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.given
import org.mockito.kotlin.inOrder

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class LoginFeature {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var mObserver : Observer<ResultStates<Any?>>

    @Mock
    lateinit var webService : Webservice

    private lateinit var mRepository : LoginRepository
    private lateinit var mLoginUseCase : Login
    private lateinit var mViewModel : LoginViewModel
    private lateinit var loginResult: LoginResponse

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup(){
        loginResult =  LoginResponse("test")
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `observer should work fine`() = runTest{
        mRepository  = Mockito.mock(LoginRepository::class.java)
        mLoginUseCase = Login(mRepository)
        mViewModel = LoginViewModel(mLoginUseCase)
        mViewModel.getLoginObserver().observeForever(mObserver)
        mViewModel.doLogin(hashMapOf())
        given(mRepository.doLogin(hashMapOf())).willReturn(ResultStates.Success(loginResult))

        launch {
            inOrder(mObserver){
                verify(mObserver).onChanged(ResultStates.Loading(true))
                verify(mObserver).onChanged(ResultStates.Success(loginResult))
                verify(mObserver).onChanged(ResultStates.Loading(false))
            }
        }
    }

    @Test
    fun `login is working fine`() = runTest{
        given(webService.login(hashMapOf())).willReturn(loginResult)
        mRepository = LoginRepositoryImpl(webService)
        mLoginUseCase = Login(mRepository)
        mViewModel = LoginViewModel(mLoginUseCase)
        mViewModel.getLoginObserver().observeForever(mObserver)
        mViewModel.doLogin(hashMapOf())
        advanceUntilIdle()

        launch {
            inOrder(mObserver){
                verify(mObserver).onChanged(ResultStates.Loading(true))
                advanceUntilIdle()
                verify(mObserver).onChanged(ResultStates.Success(loginResult))
                verify(mObserver).onChanged(ResultStates.Loading(false))
            }
        }
    }

    @Test
    fun `failed to login`() = runTest{
        given(webService.login(hashMapOf())).willReturn(LoginResponse(null))
        mRepository = LoginRepositoryImpl(webService)
        mLoginUseCase = Login(mRepository)
        mViewModel = LoginViewModel(mLoginUseCase)
        mViewModel.getLoginObserver().observeForever(mObserver)
        mViewModel.doLogin(hashMapOf())
        advanceUntilIdle()

        launch {
            inOrder(mObserver){
                verify(mObserver).onChanged(ResultStates.Loading(true))
                advanceUntilIdle()
                verify(mObserver).onChanged(ResultStates.Failed("Something went wrong"))
                verify(mObserver).onChanged(ResultStates.Loading(false))
            }
        }

    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

}