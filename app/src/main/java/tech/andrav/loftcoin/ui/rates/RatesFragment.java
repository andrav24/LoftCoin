package tech.andrav.loftcoin.ui.rates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.andrav.loftcoin.data.CmcApi;
import tech.andrav.loftcoin.data.CmcApiProvider;
import tech.andrav.loftcoin.data.Listings;
import tech.andrav.loftcoin.databinding.FragmentRatesBinding;
import timber.log.Timber;

public class RatesFragment extends Fragment {

    private CmcApi api;
    private FragmentRatesBinding binding;
    private RatesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = new CmcApiProvider().get();
        adapter = new RatesAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRatesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Timber.d("%s", this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recycler.setHasFixedSize(true);
        binding.recycler.swapAdapter(adapter, false);
        binding.refresher.setOnRefreshListener(this::refresh);
        refresh();
    }

    @Override
    public void onDestroy() {
        binding.recycler.swapAdapter(null, false);
        super.onDestroy();
    }

    private void refresh() {
        binding.refresher.setRefreshing(true);
        api.listings().enqueue(new Callback<Listings>() {
            @Override
            public void onResponse(Call<Listings> call, Response<Listings> response) {
                binding.refresher.setRefreshing(false);
                final Listings body = response.body();
                if (body != null) {
                    adapter.submitList(body.coins());
                }
            }

            @Override
            public void onFailure(Call<Listings> call, Throwable t) {
                binding.refresher.setRefreshing(false);
                Timber.d(t);
            }
        });
    }
}
