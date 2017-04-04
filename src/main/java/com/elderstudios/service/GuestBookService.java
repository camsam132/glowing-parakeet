package com.elderstudios.service;

import com.elderstudios.domain.GuestBookEntry;
import com.elderstudios.domain.GuestBookEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookService {

    @Autowired
    protected GuestBookEntryRepository guestBookEntryRepository;

    public List<GuestBookEntry> findAll() {
        return guestBookEntryRepository.findAll();
    }

    public GuestBookEntry save(GuestBookEntry entry) {
        return guestBookEntryRepository.save(entry);
    }

    public void delete(Long id) {
        guestBookEntryRepository.delete(id);
    }

    public GuestBookEntry findOne(Long id) {
        return guestBookEntryRepository.findOne(id);
    }
}
