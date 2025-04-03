package com.task.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.model.SongSchedule;
import com.task.repository.SongRepository;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public void save(String time, String path) {
        SongSchedule songSchedule = new SongSchedule(time, path);
        songRepository.save(songSchedule);
    }

    public Optional<SongSchedule> getLatestSchedule() {
        return songRepository.findTopByOrderByIdDesc();
    }

    public void deleteSchedule() {
        songRepository.deleteAll();
    }
}
