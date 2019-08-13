package pe.demo.papayin.remote.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import pe.demo.papayin.remote.PDNetworkApiInterface
import pe.demo.papayin.remote.PDNetworkConstants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideLoggingInterceptor() }
    single { providesGson() }
    single { providesOkHttpClient(get()) }
    single { providesRetrofit(get(), get()) }
    single { providesApiInterface(get()) }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun providesGson(): Gson {
    return GsonBuilder().serializeNulls()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
}

fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        //.addInterceptor(new SANetworkInterceptor (sessionUtil))
        .build()
}

fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(PDNetworkConstants.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun providesApiInterface(retrofit: Retrofit): PDNetworkApiInterface = retrofit.create(
    PDNetworkApiInterface::class.java
)