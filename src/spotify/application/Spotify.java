package spotify.application;


import spotify.domain.Playlist;
import spotify.domain.Track;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;


/*Como obtener la canción con duración máxima, mínima y la media de duración de una playlist.
        -Introducir nuevas estructuras como el set y el TreeSet para colecciones sin repetidos.
        -Practicar con la obtención de artistas y géneros de una playlist sin repeticiones y luego ordenados
          pero alfabeticamente con el TreeSet.
        -Realizar ejercicios más avanzados, como clasificar géneros por ocurrencia en una playlist.
        -Trabajar con fechas para realizar búsquedas.
        -Implementar la reproducción de canciones mediante URLs con el JDK de Java.
        -Explorar la posibilidad de añadir más atributos o enriquecer el sistema con más entidades.*/

public interface Spotify {
    boolean addPlaylist(Playlist playlist);

    void addTrackToPlaylist(Playlist playlist, Track track);

    void addTrackToPlaylist(Playlist playlist, int position, Track track);

    List<Track> getTracks(Playlist playlist);

    Track findLongestTrack(Playlist playlist);

    Track findShortestTrack(Playlist playlist);

    Double getAverageDurationTrack(Playlist playlist);

    List<Playlist> findByDates(LocalDate start, LocalDate end);

    // devolver las playlist que contengan alguna cancion que a su vez tenga el genero pasado por parametro.
    List<Playlist> findByGenre(String genre);

    // devolver las playlist que contengan alguna cancion que a su vez tenga el artista pasado por parametro
    List<Playlist> findByArtist(String artist);

    // Devuelve las playlist que tengan una cancion del artista publicada en ese rango de fechas
    List<Playlist> findByTrackDates(LocalDate start, LocalDate end, String artista);
    // Devolver el conjunto de generos que aparecen en la playlist, es decir en las canciones de la playlist.
    // los generos tienen que aparecer sin repeticiones, por esos se utiliza un set
    Set<String> getGenres(Playlist playlist);

    SortedSet<String> getSortedGenres(Playlist playlist);

    // Devolver una lista con los artistas que mas aparecen en la playlist. Se refierea a que una playlist tiene
    // una lista de canciones y asu vez tiene su lista de artistas, por tanto hay que explorar todas las playlist
    // de cada una todas las canciones y de cada una todos los artistas. finalmente devolver una lista de artistas
    // ordenados de mayor a menor por el nivel de participacion en la playlist
    List<String> getTopArtists(Playlist playlist);

    List<String> getGenresByFrequency(Playlist playlist);

   // int getAllPlaylists();

    //URLS AND JDK JAVA
    String getAlbumInfo(String albumId) throws IOException;
}
