package db_firebase_firestore;

public class Model {

    private String position;
    private String id;
    private String name;
    private String description;

    public Model(){

    }

    public Model(String position, String id, String name, String description) {
        this.position = position;
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getPosition() {return position;}
    public void setPosition(String position) {this.position = position;}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
