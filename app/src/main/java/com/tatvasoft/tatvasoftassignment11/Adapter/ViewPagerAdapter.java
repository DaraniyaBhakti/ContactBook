package com.tatvasoft.tatvasoftassignment11.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.tatvasoft.tatvasoftassignment11.Fragment.AudioFilesFragment;
import com.tatvasoft.tatvasoftassignment11.Fragment.ContactsFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private ContactsFragment contactsFragment = null;
    private AudioFilesFragment audioFilesFragment = null;
    List<Fragment> fragmentList = new ArrayList<>();
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            if (audioFilesFragment == null) {
                audioFilesFragment = new AudioFilesFragment();
            }
            return audioFilesFragment;
        } else  {
            if (contactsFragment == null) {
                contactsFragment = new ContactsFragment();
            }
            return contactsFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }
}