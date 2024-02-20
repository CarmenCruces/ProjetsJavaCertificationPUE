
import spotify.application.Spotify;
import spotify.application.SpotifyImpl;
import spotify.domain.Playlist;
import spotify.domain.Track;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Boolean resultado;

        Playlist playlist1 = new Playlist(1, "Lista 1", "Esta es una lista de pruebas", List.of("Lo mas de lo mas", "Lauren Postigo approved"));
        Playlist playlist2 = new Playlist(2, "Lista 2", "Esta es otra lista de pruebas", List.of("Lo menos de lo menos", "Belcebu approved"));
        Playlist playlist3 = new Playlist(3, "Friday Night Party", "Pop and Dance Hits", List.of("Let's dance all night!", "Great vibes!"));
        Playlist playlist4 = new Playlist(4, "Chill Out Vibes", "Relaxing Lounge Music", List.of("Perfect for a lazy afternoon", "Soothing melodies"));
        Playlist playlist5 = new Playlist(5, "Road Trip Anthems", "Rock and Indie Classics", List.of("Epic tunes for the road", "Rock on!"));
        Playlist playlist6 = new Playlist(6, "Morning Motivation", "Upbeat Pop and Hip Hop", List.of("Start your day right!", "Positive energy"));
        Playlist playlist7 = new Playlist(7, "Summer Beach Vibes", "Reggae and Tropical Beats", List.of("Feel the sunshine!", "Good vibes only"));
        Playlist playlist8 = new Playlist(8, "Late Night Jazz", "Smooth Jazz and Blues", List.of("Soulful melodies", "Relaxing jazz tunes"));
        Playlist playlist9 = new Playlist(9, "Throwback Hits", "Classic 90s and 2000s", List.of("Nostalgia overload", "Best of the past"));
        Playlist playlist10 = new Playlist(10, "Indie Discovery", "Up-and-coming Indie Artists", List.of("Discover new music", "Indie vibes"));
        Playlist playlist11 = new Playlist(11, "Workout Power", "Energetic Workout Tracks", List.of("Pump up the volume!", "Get fit with music"));
        Playlist playlist12 = new Playlist(12, "Study Focus", "Instrumental Study Music", List.of("Boost your productivity", "Concentration music"));

        Track track1 = new Track(1, "Zambombá jerezana", List.of("Pepe el caja"), List.of("Navidad"), LocalDate.now(), "url", 120);
        Track track2 = new Track(2, "Cumpleaños", List.of("Parchís"), List.of("Fiesta"), LocalDate.now(), "url", 110);
        Track track3 = new Track(3, "Up", List.of("Madonna"), List.of("Bailoteo"), LocalDate.now(), "url", 130);
        Track track4 = new Track(4, "The Trooper", List.of("Iron Maiden"), List.of("Lo más"), LocalDate.now(), "url", 150);
        Track track5 = new Track(5, "Sunset Dreams", List.of("Summer Vibes"), List.of("Chillout"), LocalDate.now(), "url", 125);
        Track track6 = new Track(6, "City Lights", List.of("Urban Groove"), List.of("Hip Hop"), LocalDate.now(), "url", 115);
        Track track7 = new Track(7, "Ocean Breeze", List.of("The Waves"), List.of("Ambient"), LocalDate.now(), "url", 140);
        Track track8 = new Track(8, "Moonlit Serenade", List.of("Jazz Trio"), List.of("Jazz"), LocalDate.now(), "url", 135);
        Track track9 = new Track(9, "Midnight Romance", List.of("Smooth Sax"), List.of("Romantic"), LocalDate.now(), "url", 130);
        Track track10 = new Track(10, "Starlight Symphony", List.of("Orchestra Ensemble"), List.of("Classical"), LocalDate.now(), "url", 180);
        Track track11 = new Track(11, "Electric Pulse", List.of("DJ Electro"), List.of("Electronic"), LocalDate.now(), "url", 150);
        Track track12 = new Track(12, "Mountain Trek", List.of("Nature Sounds"), List.of("Ambient"), LocalDate.now(), "url", 160);
        Track track13 = new Track(13, "Tropical Paradise", List.of("Island Beats"), List.of("Reggae"), LocalDate.now(), "url", 145);
        Track track14 = new Track(14, "Firestorm", List.of("Heavy Riffs"), List.of("Metal"), LocalDate.now(), "url", 170);
        Track track15 = new Track(15, "Crystal Waters", List.of("Enigma Vibes"), List.of("New Age"), LocalDate.now(), "url", 155);
        Track track16 = new Track(16, "Rhythm Revolution", List.of("Salsa Kings"), List.of("Latin"), LocalDate.now(), "url", 135);
        Track track17 = new Track(17, "Soulful Journey", List.of("Smooth Jazz Trio"), List.of("Jazz"), LocalDate.now(), "url", 145);
        Track track18 = new Track(18, "Desert Mirage", List.of("Sands of Time"), List.of("Arabic"), LocalDate.now(), "url", 120);
        Track track19 = new Track(19, "Epic Adventure", List.of("Film Orchestra"), List.of("Soundtrack"), LocalDate.now(), "url", 190);
        Track track20 = new Track(20, "Golden Memories", List.of("Vintage Sounds"), List.of("Oldies"), LocalDate.now(), "url", 125);

        Spotify spotify = new SpotifyImpl();

        resultado = spotify.addPlaylist(playlist1);
        resultado = spotify.addPlaylist(playlist2);
        spotify.addTrackToPlaylist(playlist1, track1);
        spotify.addTrackToPlaylist(playlist1, track2);
        spotify.addTrackToPlaylist(playlist1, track17);
        spotify.addTrackToPlaylist(playlist2, track8);

       /* spotify.addPlaylist(playlist1);
        spotify.addTrackToPlaylist(playlist1, track1);
        spotify.addTrackToPlaylist(playlist1, track2);
        */

        var tracks = spotify.getTracks(playlist1); // listamos la playlist

        tracks.forEach(track -> System.out.println(track));

        System.out.println("**********************************");

        spotify.addTrackToPlaylist(playlist1, track3); // añadimos pista 3

        tracks.forEach(track -> System.out.println(track)); // listamos la playlist

        // Debe dar un error esperado

        var playlist = spotify.findByGenre("Jazz");
        System.out.println("La playlist que tiene canciones de Jazz es: " + playlist);

    }

}
