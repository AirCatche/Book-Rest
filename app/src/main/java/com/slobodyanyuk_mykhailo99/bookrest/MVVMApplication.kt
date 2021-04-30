package com.slobodyanyuk_mykhailo99.bookrest

import android.app.Application
import com.slobodyanyuk_mykhailo99.bookrest.data.db.BookRestDatabase
import com.slobodyanyuk_mykhailo99.bookrest.data.network.BookRestApi
import com.slobodyanyuk_mykhailo99.bookrest.data.network.NetworkConnInterceptor
import com.slobodyanyuk_mykhailo99.bookrest.data.repositories.UserRepository
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.login.LoginViewModelFactory
import com.slobodyanyuk_mykhailo99.bookrest.ui.auth.signup.SignUpViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class MVVMApplication: Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnInterceptor(instance()) }
        bind() from singleton { BookRestApi(instance()) }
        bind() from singleton { BookRestDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { SignUpViewModelFactory(instance()) }
        bind() from provider { LoginViewModelFactory(instance()) }



    }

}