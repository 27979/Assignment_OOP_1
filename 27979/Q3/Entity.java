public class Entity {
    protected int id;
    protected String createdAt;

    public Entity() {
        this.createdAt = java.time.LocalDateTime.now().toString();
    }

    public int getId() { return id; }
    public String getCreatedAt() { return createdAt; }
}
