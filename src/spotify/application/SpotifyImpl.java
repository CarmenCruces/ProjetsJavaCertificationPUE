package spotify.application;

import spotify.domain.Playlist;
import spotify.domain.Track;

import java.time.LocalDate;
import java.util.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;

public class SpotifyImpl implements Spotify {

    private final Map<Playlist, List<Track>> map = new HashMap<>();

    @Override
    public boolean addPlaylist(Playlist playlist) {

        if (map.containsKey(playlist)) return false;
        map.put(playlist, new ArrayList<>());

        return true;
    }

    @Override
    public void addTrackToPlaylist(Playlist playlist, Track track) {

        if (!map.containsKey(playlist)) {
            throw new IllegalArgumentException("The playlist " + playlist.getId() + " does not exist in Spotify");
        }
        var tracks = map.get(playlist);
        tracks.add(track);
        //map.computeIfAbsent(playlist, k -> new ArrayList<>()).add(track);
    }

    @Override
    public void addTrackToPlaylist(Playlist playlist, int position, Track track) {

        if (!map.containsKey(playlist)) {
            throw new IllegalArgumentException("The playlist " + playlist.getId() + " does not exist in Spotify");
        }
        List<Track> tracks = map.get(playlist);

        if (position < 0 || position > tracks.size()) {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
        tracks.add(position, track);

    }

    @Override
    public List<Track> getTracks(Playlist playlist) {
        return map.get(playlist);
        //return map.getOrDefault(playlist, Collections.emptyList());
    }

    @Override
    public Track findLongestTrack(Playlist playlist) {
        var tracks = getTracks(playlist);
        Track longestTrack = null;
        int longestDuration = 0;

        for (var track : tracks) {
            if (track.getSeconds() > longestDuration) {
                longestDuration = track.getSeconds();
                longestTrack = track;
            }
        }
        return longestTrack;
    }
    //------------------------------FUNCIONAL
   /* @Override
    public Track findLongestTrack(Playlist playlist) {
        var tracks = getTracks(playlist);
        return tracks.stream()
                .max(Comparator.comparingInt(Track::getSeconds))
                .orElse(null);
    }*/

    @Override
    public Track findShortestTrack(Playlist playlist) {
        var tracks = getTracks(playlist);
        return tracks.stream()
                .min(Comparator.comparingInt(Track::getSeconds))
                .orElse(null);
    }

    //------------------------------------FUNCIONAL

/*    @Override
    public Track findShortestTrack(Playlist playlist) {
        var tracks = getTracks(playlist);
        Track shortestTrack = null;
        int shortestDuration = Integer.MAX_VALUE;

        for (var track : tracks) {
            if (track.getSeconds() < shortestDuration) {
                shortestDuration = track.getSeconds();
                shortestTrack = track;
            }
        }

        return shortestTrack;
    }*/

    @Override
    public Double getAverageDurationTrack(Playlist playlist) {
        var tracks = getTracks(playlist);
        int totalDuration = 0;

        for (var track : tracks) {
            totalDuration += track.getSeconds();
        }

        return (double) totalDuration / tracks.size();
    }

    //----------------------------FUNCIONAL
 /*   @Override
    public Double getAverageDurationTrack(Playlist playlist) {
        var tracks = getTracks(playlist);
        return tracks.stream()
                .mapToInt(Track::getSeconds)
                .average()
                .orElse(0.0);
    }*/

    @Override
    public List<Playlist> findByDates(LocalDate start, LocalDate end) {
        List<Playlist> playlistsInRange = new ArrayList<>();

        for (var entry : map.entrySet()) {
            Playlist playlist = entry.getKey();
            List<Track> tracks = entry.getValue();

            for (var track : tracks) {
                LocalDate releaseDate = track.getReleaseDate();
                if (releaseDate != null && !releaseDate.isBefore(start) && !releaseDate.isAfter(end)) {
                    playlistsInRange.add(playlist);
                    break;
                }
            }
        }

        return playlistsInRange;
    }

    //---------------------------------FUNCIONAL
 /*   @Override
    public List<Playlist> findByDates(LocalDate start, LocalDate end) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .anyMatch(track -> {
                            LocalDate releaseDate = track.getReleaseDate();
                            return releaseDate != null && !releaseDate.isBefore(start) && !releaseDate.isAfter(end);
                        }))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }*/

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

    //-----------------------FUNCIONAL
/*    @Override
    public List<Playlist> findByGenre(String genre) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().stream().anyMatch(track -> track.getGenres().contains(genre)))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }*/

    @Override
    public List<Playlist> findByArtist(String artist) {
        List<Playlist> playlistsWithArtist = new ArrayList<>();

        for (var entry : map.entrySet()) {
            List<Track> tracks = entry.getValue();
            for (var track : tracks) {
                if (track.getArtists().contains(artist)) {
                    playlistsWithArtist.add(entry.getKey());
                    break;
                }
            }
        }
        return playlistsWithArtist;
    }

    //------------------------------FUNCIONAL
/*    @Override
    public List<Playlist> findByArtist(String artist) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().stream().anyMatch(track -> track.getArtists().contains(artist)))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }*/


    @Override
    public List<Playlist> findByTrackDates(LocalDate start, LocalDate end, String artist) {
        List<Playlist> playlistsWithTrackDates = new ArrayList<>();

        for (var entry : map.entrySet()) {
            Playlist playlist = entry.getKey();
            List<Track> tracks = entry.getValue();
            for (var track : tracks) {
                LocalDate releaseDate = track.getReleaseDate();
                if (releaseDate != null && !releaseDate.isBefore(start) && !releaseDate.isAfter(end) && track.getArtists().contains(artist)) {
                    playlistsWithTrackDates.add(playlist);
                    break;
                }
            }
        }

        return playlistsWithTrackDates;
    }

    //--------------------------------------FUNCIONAL
/*    @Override
    public List<Playlist> findByTrackDates(LocalDate start, LocalDate end, String artist) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .anyMatch(track -> {
                            LocalDate releaseDate = track.getReleaseDate();
                            return releaseDate != null && !releaseDate.isBefore(start) && !releaseDate.isAfter(end) && track.getArtists().contains(artist);
                        }))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }*/

    @Override
    public Set<String> getGenres(Playlist playlist) {
        Set<String> genres = new HashSet<>();

        List<Track> tracks = map.getOrDefault(playlist, Collections.emptyList());
        for (Track track : tracks) {
            genres.addAll(track.getGenres());
        }

        return genres;
    }
    //--------------------------FUNCIONAL
/*    @Override
    public Set<String> getGenres(Playlist playlist) {
        List<Track> tracks = map.getOrDefault(playlist, Collections.emptyList());
        return tracks.stream()
                .flatMap(track -> track.getGenres().stream())
                .collect(Collectors.toSet());
    }*/

    @Override
    public SortedSet<String> getSortedGenres(Playlist playlist) {
        Set<String> genres = getGenres(playlist);
        return new TreeSet<>(genres);
    }

    //--------------------------FUNCIONAL
/*  @Override
    public SortedSet<String> getSortedGenres(Playlist playlist) {
        Set<String> genres = getGenres(playlist);
        return new TreeSet<>(genres);

    }*/

    @Override
    public List<String> getTopArtists(Playlist playlist) {
        Map<String, Integer> artistCounts = new HashMap<>();

        List<Track> tracks = map.getOrDefault(playlist, Collections.emptyList());
        for (Track track : tracks) {
            for (String artist : track.getArtists()) {
                artistCounts.put(artist, artistCounts.getOrDefault(artist, 0) + 1);
            }
        }
        List<String> topArtists = new ArrayList<>(artistCounts.keySet());
        topArtists.sort((a1, a2) -> artistCounts.get(a2).compareTo(artistCounts.get(a1)));
        return topArtists;
    }
    //---------------------------------FUNCIONAL
/*    @Override
    public List<String> getTopArtists(Playlist playlist) {
        Map<String, Long> artistCounts = map.getOrDefault(playlist, Collections.emptyList()).stream()
                .flatMap(track -> track.getArtists().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return artistCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }*/

}
