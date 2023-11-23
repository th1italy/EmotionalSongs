package emotionalsongs.backend;

import emotionalsongs.backend.repositories.SongRepository;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    private SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }
}
