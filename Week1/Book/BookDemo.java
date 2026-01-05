class Book{
    private int bookId;
    private String title;
    private String author;
    private Double price;
    private Boolean availability;
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        if(price<0){
            System.out.println("The price should always be more than 0");
            System.exit(0);
        }
        this.price = price;
    }
    public Boolean getAvailability() {
        return availability;
    }
    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}

public class BookDemo{
    public static void main(String[] args) {
        Book book1 = new Book();
        book1.setAuthor("J.K. Rowling");
        book1.setAvailability(true);
        book1.setBookId(1001);
        book1.setPrice(100.0);
        book1.setTitle("Harry Potter and the Chamber of Secrets");
        System.out.println(book1.getTitle());
        System.out.println(book1.getAuthor());
        System.out.println(book1.getPrice());
        System.out.println(book1.getBookId());
        System.out.println(book1.getAvailability());
    }
}