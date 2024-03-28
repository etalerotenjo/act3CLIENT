package org.co.ibero;

import org.co.ibero.persistence.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        ClientApplication client = new ClientApplication();
        if (client.insertBook(args.length > 0 && args[0] != null ? args[0] : "Esteban Talero", args.length > 0 && args[1] != null ? args[1] : "IBERO"))
            client.getAllBooks();
        SpringApplication.run(ClientApplication.class, args);
    }

    public void getAllBooks() {
        RestTemplate restTemplate = new RestTemplate();
        String serverUrl = "http://localhost:8090/api/books"; // URL del servidor
        ResponseEntity<String> response = restTemplate.getForEntity(serverUrl, String.class);
        System.out.println("Los libros de la base de datos son: " + response.getBody());
    }

    public boolean insertBook(String author, String title) {
        RestTemplate restTemplate = new RestTemplate();
        String serverUrl = "http://localhost:8090/api/books"; // URL del servidor
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);

        ResponseEntity<Book> response = restTemplate.postForEntity(serverUrl, book, Book.class);
        System.out.println("Inserción Exitosa: " + response.getBody());
        return true;
    }
}
