package com.example.smartpixabay.presentation.di

import com.example.smartpixabay.App
import com.example.smartpixabay.data.remotelogin.LoginRemoteService
import com.example.smartpixabay.data.remotelogin.MockedLoginRemoteService
import com.example.smartpixabay.data.database.getDatabase
import com.example.smartpixabay.data.repository.DefaultImagesRepository
import com.example.smartpixabay.data.repository.ImagesRepository
import com.example.smartpixabay.domain.usecases.GetImageDetailUseCase
import com.example.smartpixabay.domain.usecases.GetImagesUseCase
import com.example.smartpixabay.domain.usecases.GetLoginUseCase
import com.example.smartpixabay.domain.usecases.GetRegistrationUseCase
import com.example.smartpixabay.domain.usecases.ImageDetailUseCase
import com.example.smartpixabay.domain.usecases.ImageUseCase
import com.example.smartpixabay.domain.usecases.LoginUseCase
import com.example.smartpixabay.domain.usecases.RegistrationUseCase
import com.example.smartpixabay.presentation.home.viewmodel.HomeViewModel
import com.example.smartpixabay.presentation.imagedetail.viewmodel.ImageDetailViewModel
import com.example.smartpixabay.presentation.login.viewmodel.LoginViewModel
import com.example.smartpixabay.presentation.registration.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<LoginRemoteService> { MockedLoginRemoteService() }
    single { getDatabase(App.INSTANCE.applicationContext) }
    single<ImagesRepository> { DefaultImagesRepository(get()) }
    factory<ImageDetailUseCase> { GetImageDetailUseCase(get()) }
    factory<RegistrationUseCase> { GetRegistrationUseCase(get()) }
    factory<LoginUseCase> { GetLoginUseCase(get()) }
    factory<ImageUseCase> { GetImagesUseCase(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegistrationViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ImageDetailViewModel(get()) }
}
