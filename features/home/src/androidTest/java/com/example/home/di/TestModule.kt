////package com.example.home.di
//
//import android.util.Log
//import com.example.core.sharedData.MarvelCharacter
//import com.example.core.sharedData.home.CityWeather
//import com.example.home.domain.useCase.CityWeatherUseCase
//import com.example.home.domain.useCase.MarvelCharactersUseCase
//import com.example.home.utils.FakeData.charactersList
//import com.example.home.utils.FakeData.cityWeather
//import com.example.utils.core.UiState
//import com.example.utils.usecases.FlowUseCase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.android.components.ViewModelComponent
//import dagger.hilt.components.SingletonComponent
//import dagger.hilt.testing.TestInstallIn
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.runBlocking
//import org.mockito.Mockito
//import org.mockito.Mockito.mock
//
//
//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [CityWeatherUseCaseModule::class]
//)
//object TestModule {
//
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Provides
//    fun provideCityWeatherUseCase(): FlowUseCase<String?, CityWeather?> {
//        return mock(CityWeatherUseCase::class.java).apply {
//            runBlocking {
//                Mockito.`when`(invoke("Cairo")).thenReturn(
//                    MutableStateFlow(
//                        UiState.Success(cityWeather)
//                    )
//                )
//            }
//        }
//    }
//
//
//}
