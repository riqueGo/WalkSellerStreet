package com.rique.walksellerstreet.di

import android.content.Context
import com.rique.walksellerstreet.handler.LocationHandler
import com.rique.walksellerstreet.repository.ISellerRepository
import com.rique.walksellerstreet.repository.SellerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideLocationRepository(): ISellerRepository {
        return SellerRepository()
    }

    @Provides
    fun provideLocationHandler(@ApplicationContext context: Context): LocationHandler {
        return LocationHandler(context)
    }
}
