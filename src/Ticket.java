public class Ticket{
    private Film film;
    private JadwalFilm time;
    private seat seat;

    public Ticket(Film film, JadwalFilm time, seat seat) {
        this.film = film;
        this.time = time;
        this.seat = seat;
    }
}