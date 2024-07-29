




<div align="center">

## Classes ⚙🛠

</div>



```java
//Models
public class Artist {
    private int id;
    private String name;
    private ArrayList<Presentation> presentations;
    private ArrayList<String> type;
    private String biography;
}

public class Presentation {
	private int id;
	private LocalDate date;
	private Artist artist;
	private City city;
	private double priceTicket;
	private int duration;
	private int ticketsSold;
	private int ticketsTotal;
}

public class City {
	private int id;
	private String name;
	private List<Presentation> presentations;
}
```

```java
//Facade

```