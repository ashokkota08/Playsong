package com.task.model;

import jakarta.persistence.*;

@Entity
@Table(name = "song_schedule")
public class SongSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String time;  // ✅ Stores the time of the song schedule
    private String path;  // ✅ Stores the song file path

    // Constructors
    public SongSchedule() {}

    public SongSchedule(String time, String path) {
        this.time = time;
        this.path = path;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

	@Override
	public String toString() {
		return "SongSchedule [id=" + id + ", time=" + time + ", path=" + path + "]";
	}
    
}
