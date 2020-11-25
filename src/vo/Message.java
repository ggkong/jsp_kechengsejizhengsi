package vo;

public class Message {
    private String time;
    private String state;

    public Message(String time, String state) {
        this.time = time;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Message{" +
                "time='" + time + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
