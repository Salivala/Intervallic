public class TextView implements Observer{
    @Override
    public void update(short hours, short minutes, short seconds) {
        System.out.println(hours + ":" + minutes + ":" + seconds);
    }
}
