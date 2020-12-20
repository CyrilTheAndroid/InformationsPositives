package com.pinalopes.informationspositives.feed.model;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pinalopes.informationspositives.LoadingService;
import com.pinalopes.informationspositives.R;
import com.pinalopes.informationspositives.databinding.FeedFragmentBinding;
import com.pinalopes.informationspositives.feed.dagger.DaggerFeedComponent;
import com.pinalopes.informationspositives.feed.dagger.LoadingModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FeedFragment extends Fragment {

    @Inject LoadingService loadingService;
    private FeedFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FeedFragmentBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        DaggerFeedComponent
                .builder()
                .loadingModule(new LoadingModule(rootView.getContext(), binding.loadingMainLayout, binding.loadingImageView))
                .build()
                .inject(this);
        initStoriesFragment((FragmentActivity) rootView.getContext());
        initFeedRecyclerView(rootView.getContext());
        return rootView;
    }

    private void initStoriesFragment(FragmentActivity fragmentActivity) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        Fragment mapFragment = new StoryFragment();
        fragmentManager.beginTransaction().add(R.id.storiesFrameLayout, mapFragment).commit();
    }

    private void initFeedRecyclerView(Context context) {
        RecyclerView feedRecyclerView = binding.feedRecyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        List<String> test = new ArrayList<>();

        test.add("deecz");
        test.add("suuu");
        test.add("dedecdz");
        test.add("deezcdz");
        test.add("deecxadz");
        test.add("deecdxz");
        test.add("deecdfz");
        test.add("deecdlz");
        test.add("deecdpz");
        test.add("deecdzo");

        feedRecyclerView.setLayoutManager(layoutManager);
        FeedRecyclerAdapter adapter = new FeedRecyclerAdapter(test);
        feedRecyclerView.setAdapter(adapter);
    }
}