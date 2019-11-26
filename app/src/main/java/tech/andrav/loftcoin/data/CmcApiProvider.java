package tech.andrav.loftcoin.data;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Moshi;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Provider;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import tech.andrav.loftcoin.BuildConfig;

// from library "javax.inject:javax.inject:1"
// like Callable, Supplier interfaces from Java
public class CmcApiProvider implements Provider<CmcApi> {

    private static volatile CmcApi sApi;

    @Override
    public CmcApi get() {
        return Holder.API;
    }


    private static final class Holder {
        static final CmcApi API;

        static {
            final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
            okHttpBuilder.addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    return chain.proceed(chain.request().newBuilder()
                            .addHeader(CmcApi.API_KEY, BuildConfig.CMC_API_KEY)
                            .build());
                }
            });

            if (BuildConfig.DEBUG) {
                final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.level(HttpLoggingInterceptor.Level.HEADERS);
                interceptor.redactHeader(CmcApi.API_KEY);
                okHttpBuilder.addInterceptor(interceptor);
            }

            final Moshi moshi = new Moshi.Builder().build();
            final Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpBuilder.build())
                    .baseUrl(BuildConfig.CMC_API_ENDPOINT)
                    .addConverterFactory(MoshiConverterFactory.create(moshi.newBuilder()
                            .add(Listings.class, moshi.adapter(AutoValue_Listings.class))
                            .build()))
                    .build();
            API = retrofit.create(CmcApi.class);

            /*
            API = new CmcApi() {
                @Override
                public Call<Listings> listings() {
                    return null;
                }
            };*/
        }
    }

}
