package com.example.paktunes.di

import android.content.Context
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {



//    @ServiceScoped
//    @Provides
//    fun provideAudioAttributes() = AudioAttributes.Builder()
//        .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
//        .setUsage(C.USAGE_MEDIA)
//        .build()

//    @ServiceScoped
//    @Provides
//    fun provideExoPlayer(
//        @ApplicationContext context: Context,
//        audioAttributes: AudioAttributes
//    ) = ExoPlayer.Builder(context).build().apply {
//        setAudioAttributes(audioAttributes, true)
//    }

    @ServiceScoped
    @Provides
    fun provideDataSourceFactory(
        @ApplicationContext context: Context
    ) = DefaultDataSource.Factory(context)

    @ServiceScoped
    @Provides
    fun provideDefaultHttpDataSource(
    ): DefaultHttpDataSource.Factory = DefaultHttpDataSource.Factory()


}
