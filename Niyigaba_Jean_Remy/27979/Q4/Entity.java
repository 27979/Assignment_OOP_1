import java.util.Date;
public class Entity {
    protected int id;
    protected Date createdDate;
    protected Date updatedDate;
    public Entity(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID must be greater than 0");
        this.id = id;
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }
}


