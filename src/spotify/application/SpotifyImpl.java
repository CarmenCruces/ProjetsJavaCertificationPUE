package spotify.application;

import spotify.domain.Playlist;
import spotify.domain.Track;

import java.time.LocalDate;
import java.util.*;

public class SpotifyImpl implements Spotify {

    private final Map<Playlist, List<Track>> map = new HashMap<>();

    @Override
    public boolean addPlaylist(Playlist playlist) {

        if (map.containsKey(playlist)) return false;
        map.put(playlist, new ArrayList<>());

        return true;

        //map.putIfAbsent devuelve null si no existe y lo asocia.
        //https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
        //return map.putIfAbsent(playlist, new ArrayList<>())==null;
    }

    @Override
    public void addTrackToPlaylist(Playlist playlist, Track track) {

        if (!map.containsKey(playlist)) {
            throw new IllegalArgumentException("La playlist " + playlist.getId() + " no existe en Spotify");
        }
        var tracks = map.get(playlist);
        tracks.add(track);

    }

    @Override
    public void addTrackToPlaylist(Playlist playlist, int position, Track track) {

    }

    @Override
    public List<Track> getTracks(Playlist playlist) {
        return map.get(playlist);
        //  return Collections.unmodifiableList(map.get(playlist));

    }

    @Override
    public Track findLongestTrack(Playlist playlist) {
        return null;
    }

    @Override
    public Track findShortestTrack(Playlist playlist) {
        return null;
    }

    @Override
    public Double getAverageDurationTrack(Playlist playlist) {
        return null;
    }

    @Override
    public List<Playlist> findByDates(LocalDate start, LocalDate end) {
        return null;
    }

    @Override
    public List<Playlist> findByGenre(String genre) {

        var allPlaylists = map.keySet();

        List<Playlist> playlistsWithGenre = new ArrayList<>();

        for (var playlistActual : allPlaylists) {
            var tracks = map.get(playlistActual);

            for (var track : tracks) {
                var trackGenres = track.getGenres();
                if (trackGenres.contains(genre)) {
                    playlistsWithGenre.add(playlistActual);
                }
            }
        }


        return playlistsWithGenre;
    }

    @Override
    public List<Playlist> findByArtist(String artist) {
        return null;
    }

    @Override
    public List<Playlist> findByTrackDates(LocalDate start, LocalDate end, String artista) {
        return null;
    }

    @Override
    public Set<String> getGenres(Playlist playlist) {


        return null;
    }

    @Override
    public SortedSet<String> getSortedGenres(Playlist playlist) {
        return null;
    }

    @Override
    public List<String> getTopArtists(Playlist playlist) {
        return null;
    }

    public void metodoChungoNoEstandar() {
    }
}
