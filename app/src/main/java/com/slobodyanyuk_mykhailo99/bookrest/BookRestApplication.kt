package com.slobodyanyuk_mykhailo99.bookrest

import android.app.Application
import com.slobodyanyuk_mykhailo99.bookrest.data.db.BookRestDatabase
import com.slobodyanyuk_mykhailo99.bookrest.data.network.BookRestApi
import com.slobodyanyuk_mykhailo99.bookrest.data.network.ConnectionInterceptor
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.preference.PreferenceProvider
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login.LoginViewModelFactory
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup.SignUpViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class BookRestApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@BookRestApplication))
        bind() from singleton { ConnectionInterceptor(instance()) }
        bind() from singleton { BookRestApi(instance()) }
        bind() from singleton { BookRestDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        bind() from singleton { UserRepository(instance(), instance(), instance()) }
        bind() from provider { SignUpViewModelFactory(instance()) }
        bind() from provider { LoginViewModelFactory(instance()) }
    }

}