import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The main class that simulates a cinema booking system.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Vykoupení z věznice Shawshank", "95%", "Frank Darabont", false));
        movieList.add(new Movie("Kmotr", "92%", "Francis Ford Coppola", false));
        movieList.add(new Movie("Pulp Fiction: Historky z podsvětí", "91%", "Quentin Tarantino", true));
        movieList.add(new Movie("Matrix", "90%", "Lana Wachowski", true));
        movieList.add(new Movie("Temný rytíř", "90%", "Christopher Nolan", true));
        movieList.add(new Movie("Titanic", "85%", "James Cameron", true));
        movieList.add(new Movie("Need for Speed", "61%", "Scott Waugh", true));
        movieList.add(new Movie("Le Mans '66", "90%", "James Mangold", true));
        movieList.add(new Movie("Gran Torino", "90%", "Clint Eastwood", false));
        movieList.add(new Movie("Šílený Max: Zběsilá cesta", "80%", "George Miller", false));
        movieList.add(new Movie("Grindhouse: Auto zabiják", "66%", "Quentin Tarantino", false));
        movieList.add(new Movie("Loupež po italsku", "77%", "Felix Gary Grey", true));
        movieList.add(new Movie("Kurýr", "72%", "Louis Leterrier", true));
        movieList.add(new Movie("Hele vole, kde mám káru?", "55%", "Danny Leiner", false));


        List<CinemaHall> cinemaHalls = new ArrayList<>();
        CinemaHall hall1 = new CinemaHall(1, 10, 20, true);
        CinemaHall hall2 = new CinemaHall(2, 8, 8, false);
        CinemaHall hall3 = new CinemaHall(3, 12, 10, false);
        CinemaHall hall4 = new CinemaHall(4, 4, 10, true);

        for (int i = 0; i < 10; i++) {
            hall1.addFilm(movieList.get(random.nextInt(movieList.size())));
            hall2.addFilm(movieList.get(random.nextInt(movieList.size())));
            hall3.addFilm(movieList.get(random.nextInt(movieList.size())));
            hall4.addFilm(movieList.get(random.nextInt(movieList.size())));
        }

        cinemaHalls.add(hall1);
        cinemaHalls.add(hall2);
        cinemaHalls.add(hall3);
        cinemaHalls.add(hall4);

        try {
            System.out.println("Dostupné filmy:");
            for (int i = 0; i < movieList.size(); i++) {
                System.out.println((i + 1) + ". " + movieList.get(i).getName());
            }
            System.out.print("Vyberte film: ");
            int selectedMovieNumber = Integer.parseInt(sc.nextLine());

            if (selectedMovieNumber < 1 || selectedMovieNumber > movieList.size()) {
                throw new InvalidMovieSelectionException("Vybraný film není k dispozici.");
            }

            Movie chosenMovie = movieList.get(selectedMovieNumber - 1);

            System.out.println("Dostupné sály pro " + chosenMovie.getName() + ":");
            List<CinemaHall> hallsForMovie = new ArrayList<>();
            for (CinemaHall hall : cinemaHalls) {
                if (hall.getFilms().contains(chosenMovie)) {
                    System.out.println("Sál č. " + hall.getHallNumber());
                    hallsForMovie.add(hall);
                }
            }
            System.out.print("Vyberte sál: ");
            int selectedHallNumber = Integer.parseInt(sc.nextLine());

            CinemaHall chosenHall = null;
            for (CinemaHall hall : hallsForMovie) {
                if (hall.getHallNumber() == selectedHallNumber) {
                    chosenHall = hall;
                    break;
                }
            }

            if (chosenHall == null) {
                throw new InvalidHallSelectionException("Vybraný sál není k dispozici pro tento film.");
            }

            chosenHall.drawSeatingArrangement();

            System.out.print("Vyberte sedadlo: ");
            String selectedSeat = sc.nextLine();

            System.out.println("Rezervace na " + chosenMovie.getName() + " v " + chosenHall.getHallNumber() + ". sále" + " na sedadlo " + selectedSeat + " byla uspěšně zaregistrována.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Zadejte platné číslo pro výběr filmu.");
        } catch (InvalidMovieSelectionException | InvalidHallSelectionException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}

/**
 * Custom exception class for invalid movie selections.
 */
class InvalidMovieSelectionException extends Exception {
    /**
     * Constructs a new InvalidMovieSelectionException with the specified error message.
     *
     * @param message The error message.
     */
    public InvalidMovieSelectionException(String message) {
        super(message);
    }
}

/**
 * Custom exception class for invalid hall selections.
 */
class InvalidHallSelectionException extends Exception {
    /**
     * Constructs a new InvalidHallSelectionException with the specified error message.
     *
     * @param message The error message.
     */
    public InvalidHallSelectionException(String message) {
        super(message);
    }
}
